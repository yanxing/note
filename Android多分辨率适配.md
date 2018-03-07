# Android多分辨率适配
#### 1.dp、dip、sp、px
dp和dip一个意思，密度无关像素，在Android中，规定以160dp为基准，1dp=1px，如果密度是320dp，则1dp=2px；屏幕分辨率是指在横纵向上的像素点数，
单位是px，1px=1个像素点；sp是字体单位,如果密度是320dp，则1sp=2px；。写布局单位时用dp，如果是高度或宽度为一个单位的一条线时，用px，这样可减少模糊感。dp与屏幕尺寸和px有关，
在单一变化条件下，屏幕尺寸越小、px越高，dp越大，反之越小。换算代码如下：<br>
```java
 /**
  * 转换px为sp
  */
 public static int px2sp(Context context, float pxValue) {
     float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
     return (int) (pxValue / fontScale + 0.5f);
 }
  ```
  ```java
  /**
   * 转换px为dp
   */
  public static int px2dp(Context context, int px) {
      float scale = context.getResources().getDisplayMetrics().density;
      return (int) (px / scale + 0.5f;
  }
  ```
#### 2.mdpi、hdpi、xhdpi、xxhdpi、xxxhdpi
用来修饰Android中的drawable(mipmap)文件夹及values文件夹，用来区分不同像素密度下的图片和dimen值。
![image](https://github.com/yanxing/note/raw/master/image/1.png)![image](https://github.com/yanxing/note/raw/master/image/2.png)
<br>为了减少UI的工作量，只提供一套图,为了在各种分辨率下图片不失真，按照最高分辨率（主流1080p）提供图片，根据现在手机市场状况（主流像素密度在320dp-480dp）
，放在xxhdpi里面，对于可能造成拉伸的图片，采用.9图片（注：xhdpi为720p，xxhdpi为1080p）。
#### 3.layout和values
多使用相对布局RelativeLayout,少使用绝对布局，充分利用”wrap_content” 、”match_parent”以及“weight”。采用一套layout,多套values(特殊地方可以多套layout)，
命名规则比如values-xhdpi、values-xxhdpi，也可以采用values-1900x1080（通知栏假设为20px，减去通知栏），本人采用的是前者。<p>
参考资料：<br>
http://blog.csdn.net/mynameishuangshuai/article/details/52925848#t21<br>
http://zmywly8866.github.io/2015/03/04/android-multi-resolution-adaptation.html<br>
http://blog.csdn.net/jdsjlzx/article/details/45891551


