去年谷歌宣布将Kotlin语言作为Android开发的一级编程语言，直到这周我才学习kotlin，说下感受：<br>
1. 目前身边Andriod开发的（前）同事并没有在实际项目中使用Kotlin,不知道大公司使用kotlin多不多。<br>
2. 官方介绍说Kotlin可与Java进行100%互操作，兼容现有代码。于是创建一个Android工程测试一下，A界面依然使用Java编写，Ａ跳转到B，B使用Kotlin编写，在B界面调用之前用Java封装的类库，测试没问题。
在Android工程中Kotlin和Java并存（不同的文件），可以互相调用，这点很好，有利于向Kotlin转变。<Br>
3. 代码量的减少。由于目前我并未在实际项目中使用，代码量的减少只体现到少了实例化控件上，Kotlin通过import kotlinx.android.synthetic.main.(当前界面的布局文件).*
自动实例化在布局文件中定义的控件，无需再使用findViewById或者butterknife等注解类库实例化控件。调用接口获取数据并显示，
[Java实现](https://github.com/yanxing/android-util/blob/master/app/src/main/java/com/yanxing/ui/NetworkLibraryFragment.java)
| [Kotlin实现](https://github.com/yanxing/android-util/blob/master/app/src/main/java/com/yanxing/ui/NetWorkLibraryKtFragment.kt)，Kotlin文件全部代码比Java实现的少了8行代码，
<p>接下来计划在实际项目中使用Kotlin，先在业务逻辑简单的地方来学习Kotlin，这样也不会耽误工作进度。
