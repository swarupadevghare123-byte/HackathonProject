
package com.example.hackathonproject;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

    public class MovingGradientButton extends AppCompatButton {

        private final Paint paint = new Paint();
        private float offset = 0f;

        private final int darkTeal = 0xFF1295AE;
        private final int lightMint = 0xFF69C5B8;

        public MovingGradientButton(Context context) {
            super(context);
            init();
        }

        public MovingGradientButton(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public MovingGradientButton(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        private void init() {

            setTextColor(0xFFFFFFFF);
            setAllCaps(false);

            paint.setAntiAlias(true);

            ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);

            animator.setDuration(2500);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.setRepeatMode(ValueAnimator.RESTART);

            animator.addUpdateListener(animation -> {
                offset = (float) animation.getAnimatedValue();
                invalidate();
            });

            animator.start();
        }

        @Override
        protected void onDraw(Canvas canvas) {

            float width = getWidth();
            float height = getHeight();

            LinearGradient gradient = new LinearGradient(-width + (2 * width * offset), 0, width + (2 * width * offset), height, new int[]{darkTeal, lightMint, darkTeal}, new float[]{0f, 0.5f, 1f}, Shader.TileMode.MIRROR);

            paint.setShader(gradient);

            float radius = height / 2f;

            canvas.drawRoundRect(
                    0,
                    0,
                    width,
                    height,
                    radius,
                    radius,
                    paint
            );

            paint.setShader(null);

            super.onDraw(canvas);
        }
    }

