###AndroidAnnotations与ButterKnife使用比较
两者都有View注解、事件绑定，而`AndroidAnnotations`还有线程切换、网络访问等功能；两者都是预编译，比其他采用运行时反射View注解框架性能优些；
`AndroidAnnotations`中布局可以通过`@EActivity`注解写到Activity类上面，而`ButterKnife`需要自己写代码
setContent或者LayoutInflater加载布局，从而两者的BaseActivity里面的代码量不同，使用`ButterKnife`子类Activity返回一个布局对象给BaseActivity，
然后写上`ButterKnife.bind(this)`，所有的子类就不用重复写`ButterKnife.bind(this)`，而`AndroidAnnotations`不需要这些写这些，一个注解就行了，但是注册和启动
Activity需要给Activity类名加上后缀_;Android studio有`ButterKnife`插件，可以自动生成View注解和事件绑定，而`AndroidAnnotations`没有此类插件，这点直接导致了
`ButterKnife`更节省时间。
