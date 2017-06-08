package com.example.cai.testlockperformanceinandroid;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Trace;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.view.ViewGroup.LayoutParams;

public class TestActivity extends Activity {

    View mCustomView;
    Paint mPaint = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(50);

        final FrameLayout rootView = new FrameLayout(this);
        setContentView(rootView);
        Button btnDoTest = new Button(this);
        btnDoTest.setText("DoTest");
        rootView.addView(btnDoTest);

        btnDoTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testAnimator();
            }
        });

        mCustomView = new View(this) {
            @Override
            protected void onDraw(Canvas canvas) {
                canvas.drawLine(getWidth()/2.0f, 0, getWidth()/2.0f, getHeight() * mCurrValue, mPaint);
            }
        };
        rootView.addView(mCustomView, 0, new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    float mCurrValue;

    void testAnimator() {
        ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setDuration(17 * 5);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override public void onAnimationUpdate(ValueAnimator animation) {
                mCurrValue = (Float)animation.getAnimatedValue();
                mCustomView.invalidate();
                trace("currValue: "+mCurrValue);
            }
        });
        trace("AnimatorStart");
        animator.start();
    }

    private void trace(String msg) {
        Trace.beginSection(msg);
        android.util.Log.i("TestAnimator", msg);
        Trace.endSection();
    }
}
