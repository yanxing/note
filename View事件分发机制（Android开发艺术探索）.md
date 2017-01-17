###以下摘自Android开发艺术探索
#####public boolean dispatchTouchEvent（MotionEvent ev）
用来进行事件的分发。如果时间能够传递给当前View,那么此方法一定会被调用，返回结果受当前View的onTouchEvent和下级View的dispatchTouchEvent方法的影响，
表示是否消耗当前事件。
#####public boolean onInterceptTouchEvent(MotionEvent ev)
在上述方法内部调用，用来判断是否拦截某个事件，如果当前View拦截了某个事件，那么在同一个事件序列当中，
此方法不会被调用，返回结果表示是否拦截当前事件。
#####public boolean onTouchEvent（MotionEvent ev）在dispatchTouchEvent方法中调用，用来处理点击事件，返回结果表示是否消耗当前事件，如果不消耗，
则在同一事件序列中，当前View无法再次接受到事件。
<br>伪代码：
```java
  public boolean dispatchTouchEvent（MotionEvent ev）{
     boolean consume=false;
     if(onInterceptTouchEvent(ev)){
        consume=onTouchEvent(ev);
     }else{
        consume=child.dispatchTouchEvent(ev);
     }
     return consume;
 }
 ```
 关于事件传递的机制，这里给出一些结论，根据这些结论可以更好的理解整个传递机制，如下所示：<p>
 （1）同一事件序列是指从手指接触屏幕的那一刻起，到手指离开屏幕的那一刻结束，在这个过程中所产生的一系列事件，这个事件序列以down事件开始，中间含有数量不定的
 move事件，最终以up事件结束。<p>
 （2）正常情况下，一个事件序列只能被一个View拦截且消耗。这一条的原因可以参考（3），因为一旦一个元素拦截了某此事件，那么同一个事件序列内的所有事件都会直接交给
 它处理，因此同一事件序列中的事件不能分别由两个View同时处理，但是通过特殊手段可以做到，比如一个View讲本该自己处理的事件通过onTouchEvent强行传递给
 其他View处理。<p>
 （3）某个View一旦决定
