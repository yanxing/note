1. 多条点击事件(@OnClick({R2.id.storeImg,R2.id.store_time}))需要分开写成多个方法。
2. module中需要添加（即使当前module依赖的module包含了以下两句也要添加，否则运行R2找不到）<br>
apply plugin: 'com.jakewharton.butterknife'<br>
annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1'<br>
app的build.gradle中无需添加apply plugin: 'com.jakewharton.butterknife'<br>
如果module中不使用butterknife，工程build.gradle中无需添加
classpath 'com.jakewharton:butterknife-gradle-plugin:8.8.1'，
