内存优化的核心是使用最少的内存完成以前的业务逻辑
#### 内存溢出
Bitmap是内存大户，容易造成OOM，可以对图片进行压缩，BitmapFactory.Options的inSampleSize属性表示图片为原始图片的几分之几，
inJustDecodeBounds为true表示不为Bitmap分配内存空间，但是可以获得图片宽高，这样可以动态设置inSampleSize的值，用过bitmap后及时
调用recycle()方法释放空间。另外可以使用图片加载开源框架Fresco，相较于UniversalImageLoader速度更快，内存使用优化的更好，支持GIF和WebP。<p>
#### 内存泄露
1. 使用线程，当使用线程做耗时任务时，如果线程是匿名内部类，会持有外部Activity，导致Activity不能回收，另外在onDestory中应该停止线程任务。Handler处理消息时需使用静态类，结合弱引用更新UI。推荐使用线程池。
2. 使用单例，由于单例的生命周期比较长，上下文context不应使用Activity的，应该使用Application的context。<p>
3. 使用静态成员变量，如果控件使用static修饰或者静态的资源对象，就会持有Activity，导致内存泄露。<p>
4. 资源未关闭，使用BraodcastReceiver，Cursor，InputStream/OutputStream等未关闭。<p>
#### 布局优化
使用ConstraintLayout减少布局层级，每帧绘制时间不应超过16.67ms，使用GPU过度绘制调试，大部分布局维持在1x-2x范围内比较好，根据现在的旗舰手机配置，3x也行，结合需求，尽量向2x靠齐(一般app大部分界面设计都是偏向2x，1x,3x占比少)。
##### 其他内存优化
* 常量使用static final，少使用枚举
* String变量拼接使用StringBuild（线程不安全，StringBuffer线程安全）
* ViewPager+Fragment不缓存过多的Fragment，考虑到性能和节省流量，可延迟请求网络
* 不要在Application放过多的全局变量
* HashMap若是int型的key，可以使用SparseArray代替，其他类型使用ArrayMap
* ListView改用RecycleView
* 不要使用 ScrollView包裹ListView/GridView/ExpandableListVIew，这样会把ListView 的所有 Item 都加载到内存中，要消耗巨大的内存和cpu去绘制，推荐使用NestedScrollView。
 ##### 参考
 https://www.jianshu.com/p/ab0cf2697236<br>
 https://www.cnblogs.com/matric/p/6942281.html?utm_source=itdadao&utm_medium=referral
