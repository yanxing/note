Activity A启动Activity B，A执行onPause，接着B执行onCreate->onStart->onResume，最后A执行onStop,如果B的主题是透明的，A不执行onStop<p>
