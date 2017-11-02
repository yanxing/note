使用Fresco的pause方法不当导致的坑：<p>
问题：ViewPager加载4个Fragment（采用默认相邻数缓存），第一个Fragment的图片列表显示正常，这时点击第三个Fragment中图片列表不显示（网络、图片地址都没有问题），
而切换到别的Fragment或者点击home键再回到当前Fragment，图片显示出来了。<br>
原因:我在BaseFragment中的onResume调用了`Fresco.getImagePipeline().resume()`
方法恢复加载图片，而在onPause方法中调用`Fresco.getImagePipeline().pause()`方法暂停加载。当切换到第三个Fragment后，开始加载图片列表数据（图片url集合）
，图片列表URL还没有加载完，第一个Fragment执行了onPause方法，即暂停图片加载，随后图片列表URL加载完成，更新适配器，这时就导致了图片不显示，而从桌面回到应用
或者切换到别的Fragment再回来，当前的Fragment执行onResume方法，Fresco恢复了图片加载，图片也就显示了出来。至于点击第二个Fragment图片列表可以显示，
是因为Viewpager的相邻Fragment缓存机制，这时第二个Fragment已经创建其中的图片已经加载完了。
