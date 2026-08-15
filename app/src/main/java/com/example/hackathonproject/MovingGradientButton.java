package com.example.hackathonproject;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

public class MovingGradientButton extends AppCompatButton {

    private Paint paint;
    private LinearGradient gradient;
    private Matrix matrix;
    private float translate = 0f;
    private ValueAnimator animator;

    public MovingGradientButton(@NonNull Context context) {
        super(context);
        init();
    }

    public MovingGradientButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MovingGradientButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        matrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 0 && h > 0) {
            // Create a gradient that is wider than the view to allow for translation
            gradient = new LinearGradient(
                    0, 0, w * 2, h,
                    new int[]{0xFF00838F, 0xFF00BFA5, 0xFF00838F}, // Teal shades
                    new float[]{0f, 0.5f, 1f},
                    Shader.TileMode.REPEAT
            );
            paint.setShader(gradient);
            startAnimation(w);
        }
    }

    private void startAnimation(int width) {
        if (animator != null) {
            animator.cancel();
        }
        animator = ValueAnimator.ofFloat(0, width * 2);
        animator.setDuration(3000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(animation -> {
            translate = (float) animation.getAnimatedValue();
            invalidate();
        });
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (gradient != null) {
            matrix.setTranslate(-translate, 0);
            gradient.setLocalMatrix(matrix);
        }
        super.onDraw(canvas);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (animator != null) {
            animator.cancel();
        }
    }
}