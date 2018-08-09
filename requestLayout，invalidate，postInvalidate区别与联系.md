*  requestLayout：当View大小、位置发生改变，调用此方法，对View进行重新测量布局绘制。requestLayout方法执行时（如果当前View树布局流程正在执行，则该请求延后处理），会调用父类的requestLayout方法，层层上下传递到DecorView（根View），DecorView又会传递到ViewRootImpl，ViewRootImpl里面重写requestLayout方法，里面的scheduleTraversals方法执行measure，layout，draw方法进行view测量布局绘制。
*  invalidate/postInvalide: 该方法会引起View树重绘，前者用于UI线程调用，后者用于非UI线程中调用。invalidate方法调用不断向父容器请求刷新，直到ViewRootImpl调用performTraversals方法重绘，根据标志位判断只执行draw方法。

<p>![image](https://github.com/yanxing/note/raw/master/image/3.jpg)<br>图片来自下面文章

<p>参考来自：https://blog.csdn.net/qq_28273051/article/details/75043683

