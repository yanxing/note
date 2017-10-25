1.多条点击事件(@OnClick({R2.id.storeImg,R2.id.store_time}))不可以用R2的方式，分开写成多个方法<p>
2.module中需要添加（即使当前module依赖的module包含了一下两句也要添加，否则运行R2找不到，编译可以通过）<br>
apply plugin: 'com.jakewharton.butterknife'<br>
annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1'
