package com.zhucai;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.util.Property;

public class FasterStartAnimatorUtils {

    static public float[] processFloatParams(long duration, TimeInterpolator interpolator, float... values) {
        if (values == null || values.length == 0) return null;

        if (values.length == 1) return processFloatParams(duration, interpolator, 0.f, values[0]);

        float f = 16.7f / (duration + 16.7f);
        if (interpolator != null) {
            f = interpolator.getInterpolation(f);
        }
        values[0] += (values[1] - values[0]) * f;
        return values;
    }

    static public int[] processIntParams(long duration, TimeInterpolator interpolator, int... values) {
        if (values == null || values.length == 0) return null;

        if (values.length == 1) return processIntParams(duration, interpolator, 0, values[0]);

        float f = 16.7f / (duration + 16.7f);
        if (interpolator != null) {
            f = interpolator.getInterpolation(f);
        }
        values[0] += (values[1] - values[0]) * f;
        return values;
    }

    static public ValueAnimator ofFloat(long duration, TimeInterpolator interpolator, float... values) {
        ValueAnimator animator = ValueAnimator.ofFloat(processFloatParams(duration, interpolator, values));
        animator.setDuration(duration);
        animator.setInterpolator(interpolator);
        return animator;
    }

    static public ValueAnimator ofInt(long duration, TimeInterpolator interpolator, int... values) {
        ValueAnimator animator = ValueAnimator.ofInt(processIntParams(duration, interpolator, values));
        animator.setDuration(duration);
        animator.setInterpolator(interpolator);
        return animator;
    }

   public static <T> ObjectAnimator ofFloat(T target, Property<T, Float> property, long duration,
           TimeInterpolator interpolator, float... values) {
       ObjectAnimator animator = ObjectAnimator.ofFloat(target, property, processFloatParams(duration, interpolator, values));
       animator.setDuration(duration);
       animator.setInterpolator(interpolator);
       return animator;
   }

   public static <T> ObjectAnimator ofInt(T target, Property<T, Integer> property, long duration,
           TimeInterpolator interpolator, int... values) {
       ObjectAnimator animator = ObjectAnimator.ofInt(target, property, processIntParams(duration, interpolator, values));
       animator.setDuration(duration);
       animator.setInterpolator(interpolator);
       return animator;
   }
}
