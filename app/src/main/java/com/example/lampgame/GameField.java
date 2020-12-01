package com.example.lampgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class GameField extends View {
    boolean lampsState[][];
    int n = 5;
    int m = 4;
    int r, ro;
    float x, y;
    float touchX, touchY;
    public GameField(Context context) {
        super(context);
        lampsState = new boolean[n][m];
        Random random = new Random();
        for (int i = 0; i < lampsState.length; i++) {
            for (int j = 0; j < lampsState[i].length; j++) {
                lampsState[i][j] = random.nextBoolean();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(6);
        r = getWidth() / (m * 3);
        ro = r;
        for (int i = 0; i < lampsState.length; i++) {
            for (int j = 0; j < lampsState[i].length; j++) {
                if (lampsState[i][j])
                    paint.setStyle(Paint.Style.FILL_AND_STROKE);
                else paint.setStyle(Paint.Style.STROKE);
                x = ro * 2 + j * (r * 2 + ro);
                y = ro * 2 + i * (r * 2 + ro);
                canvas.drawCircle(x, y, r, paint);
                //TODO_check
//                for (int k = 0; k < lampsState.length; k++) {
//                    for (int l = 0; l < lampsState[i].length; l++) {
//                        if (lampsState[i][j]);
//                    }
//                    invalidate();
//                    canvas.drawText();
                }
            }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touchX = event.getX();
            touchY = event.getY();
            calculate();
        }
        return super.onTouchEvent(event);
    }

    void calculate() {
        int i = Math.round(touchY / (2 * r + ro));
        int j = Math.round(touchX / (2 * r + ro));
        if (i <= n && j <= m) {
            x = ro * 2 + j * (r * 2 + ro);
            y = ro * 2 + i * (r * 2 + ro);
            if (Math.pow(touchX - x, 2) + Math.pow(touchY - y, 2) <= r * r) {
                lampsState[i - 1][j - 1] = !lampsState[i - 1][j - 1];
                if (i - 2 >= 0)
                    lampsState[i - 2][j - 1] = !lampsState[i - 2][j - 1];
                if (i < n)
                    lampsState[i][j - 1] = !lampsState[i][j - 1];
                if (j - 2 >= 0)
                    lampsState[i - 1][j - 2] = !lampsState[i - 1][j - 2];
                if (j < m)
                    lampsState[i - 1][j] = !lampsState[i - 1][j];
            }
            invalidate();
        }
    }
}