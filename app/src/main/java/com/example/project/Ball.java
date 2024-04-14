package com.example.project;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.Random;

public class Ball {

    private final RectF dstRect = new RectF();
    private static final float BALL_RADIUS = 1.0f;
    private static final float SPEED = 7.0f/ 60;
    private float dx,dy;
    private static final Random random = new Random();
    public static Ball random(){
        return new Ball(random.nextFloat() * GameView.SCREEN_WIDTH,
                random.nextFloat() * GameView.SCREEN_HEIGHT,
                random.nextFloat() * 360);
    }

    public Ball(float centerX, float centerY,float angle_degree){
        dstRect.set(centerX - BALL_RADIUS, centerY - BALL_RADIUS,
                centerX + BALL_RADIUS,centerY + BALL_RADIUS);
        double radian = Math.PI * angle_degree / 180;
        this.dx = SPEED * (float) Math.cos(radian);
        this.dy = SPEED * (float) Math.cos(radian);
    }

    private static Bitmap bitmap;
    public static void setBitmap(Bitmap bitmap){Ball.bitmap = bitmap;}

    public void update(){
        dstRect.offset(dx,dy);
        if(dx > 0){
            if(dstRect.right > GameView.SCREEN_WIDTH){
                dx = -dx;
            }
        }
        else {
            if(dstRect.left < 0){
                dx = -dx;
            }
        }

        if (dy > 0) {
            if (dstRect.bottom > GameView.SCREEN_HEIGHT) {
                dy = -dy;
            }
        } else {
            if (dstRect.top < 0) {
                dy = -dy;
            }
        }
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap, null, dstRect,null);
    }
}
