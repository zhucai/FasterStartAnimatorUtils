# FasterStartAnimatorUtils [Android]

## What?
This is a utils to make animator's starting faster about 16\.7ms\.

## Why?
Look at the normal animator's frame sequences:<br /> <br />
![image](https://raw.githubusercontent.com/zhucai/FasterStartAnimatorUtils/master/doc-resources/before.png)
<br /> <br />
The first frame is doing nothing but wasted 16\.7ms\. There is a test to prove this behavior: [Systrace Result](https://raw.githubusercontent.com/zhucai/FasterStartAnimatorUtils/master/doc-resources/systrace.png) ([Source Code](https://github.com/zhucai/FasterStartAnimatorUtils/blob/master/doc-resources/TestActivity.java))
<br />

Usually, we don't want the first frame wasted:<br /> <br />
![image](https://raw.githubusercontent.com/zhucai/FasterStartAnimatorUtils/master/doc-resources/after.png)
<br /> <br />
We want to start moving immediately!
<br />

## How?
We can use ValueAnimator.ofFloat(0.2f, 1.f) instead of use ValueAnimator.ofFloat(0.f, 1.f) in this example.<br />
In fact, we need calculate the first parameter in practical use. So there is this stuff.
<br /> <br />
If you used ValueAnimator.ofFloat(int... values) before, I recommend you using <br />
[FasterStartAnimatorUtils](https://github.com/zhucai/FasterStartAnimatorUtils/blob/master/FasterStartAnimatorUtils.java).ofFloat(long duration, TimeInterpolator interpolator, float... values) instead.<br /> <br />
If you used View.animate().alpha(float value) before, I recommend you using <br />
[FasterStartAnimatorUtils](https://github.com/zhucai/FasterStartAnimatorUtils/blob/master/FasterStartAnimatorUtils.java).ofFloat(view, Value.ALPHA, duration, interpolator, values) instead.
