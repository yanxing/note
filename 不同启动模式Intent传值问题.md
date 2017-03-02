1.`standard`没有什么注意的。

2.`singleTop`如果启动的Activity已经位于栈顶，则不会被创建，onCreate()，onStart()不会被调用，onNewIntent()会被调用；如果不存在，
则情况与standard模式相同。此模式可以用来防止快速点击启动了很多同一个Activity，此时需要注意再启动该Activity，需要在onNewIntent()方法中获取intent值。

3.`singleTask`如果启动的Activity已经存在栈中（taskAffinity属性指定对应名字的任务栈寻找该Activity实例），则将它上面的Activity全部出栈，
onNewIntent方法会被调用，onCreate()，onStart()不会被调用。

4.`singleInstance`这种启动的Activity会单独占用一个任务栈，后续再启动该Activity不会创建新的该Activity实例，除非这个任务栈被销毁了，
其他的和`singleTask`一样，onNewIntent方法会被调用。
