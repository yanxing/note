###以下摘自Android开发艺术探索
#####public boolean dispatchTouchEvent（MotionEvent ev）
用来进行事件的分发。如果时间能够传递给当前View,那么此方法一定会被调用，返回结果受当前View的onTouchEvent和下级View的dispatchTouchEvent方法的影响，
表示是否消耗当前事件。
#####public boolean onInterceptTouchEvent(MotionEvent ev)
在上述方法内部调用，用来判断是否拦截某个事件，如果当前View拦截了某个事件，那么在同一个事件序列当中，
此方法不会被调用，返回结果表示是否拦截当前事件。
#####public boolean onTouchEvent（MotionEvent ev）
在dispatchTouchEvent方法中调用，用来处理点击事件，返回结果表示是否消耗当前事件，如果不消耗，
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
 （3）某个View一旦决定拦截，那么这一个事件序列都只能由它来处理（如果事件序列能够传递给它的话），并且它的onInterceptTouchEvent不会再被调用。这条也很好
 理解，当一个View决定拦截一个事件后，那么系统会把同一个事件序列内的其他方法都直接交给它来处理，因此就不用再调用这个View的onInterceptTouchEvent去询问
 它是否要拦截了。<p>
 （4）某个View一旦开始处理事件，如果它不消耗ACTION_DOWN事件（onTouchEvent返回了false），那么同一事件序列中的其他事件都不会再交给它来处理，并且事件将重新交给它的父元素去处理，即父元素的onTouchEvent方法会被调用。意思就是事件一旦交给一个View处理，那么它就必须消耗掉，否则同一事件序列中剩下的事件就
 不再交给它来处理了，这就好比上级交给程序员一件事，如果这件事没有处理好，短期内上级就不敢再把事情交给这个程序员做了，二者是类似的道理。<p>
 （5) 如果View不消耗除ACTION_DOWN以外的其他事件，那么这个点击事件会消失，此时父元素的onTouchEvent并不会被调用，并且当前View可以持续受到后续的事件
 ，最终这些消失的点击事件会传递给Activity处理。<p>
 （6）ViewGroup默认不拦截任何事件。Android源码中ViewGroup的onInterceptTouchEvent方法默认返回false。<p>
 （7）View没有onInterceptEvent方法，一旦有点击事件传递给它，那么它的onTouchEvent方法就会被调用。<p>
 （8）View的onTouchEvent默认都会消耗事件（返回true），除非它是不可以点击的（clickable和longClickable同时为false）。View的longClickable属性默认都为false，clickable属性要分情况，比如Button的clickable属性默认为true，而TextView的clickable属性默认为false。<p>
 （9）View的enable属性不影响onTouchEvent的默认返回值。哪怕一个View是disable状态的，只要它的clickable或者longClickable有一个为true，那么它的
 onTouchEvent就返回true。<p>
 （10）onClick会发生的前提是当前View是可点击的，并且它收到了down和up的事件。
 （11）事件传递过程是由外向内，即事件总是先传递给父元素，然后再由父元素分发给子View，通过requestDisallowInterceptTouchEvent方法可以在子元素中关于
 干预父元素的事件分发过程，但是ACTION_DOWN事件除外。
