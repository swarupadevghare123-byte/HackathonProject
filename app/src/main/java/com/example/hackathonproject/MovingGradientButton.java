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
    private LinearGradient shader;
    private Matrix matrix;
    private float translate = 0f;

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
        setBackground(null);

        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(3000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(animation -> {
            translate = (float) animation.getAnimatedValue();
            invalidate();
        });
        animator.start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 0) {
            shader = new LinearGradient(0, 0, w, 0,
                    new int[]{0xFF00A89B, 0xFF00D2C3, 0xFF00A89B, 0xFF00D2C3, 0xFF00A89B},
                    new float[]{0f, 0.25f, 0.5f, 0.75f, 1f}, Shader.TileMode.CLAMP);
            paint.setShader(shader);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float width = getWidth();
        float height = getHeight();

        if (shader != null) {
            matrix.setTranslate(translate * width, 0);
            shader.setLocalMatrix(matrix);
            paint.setStyle(Paint.Style.FILL);

            float radius = 16 * getResources().getDisplayMetrics().density;
            canvas.drawRoundRect(0, 0, width, height, radius, radius, paint);
        }

        super.onDraw(canvas);
    }
}
