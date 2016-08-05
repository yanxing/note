#Android 面试要点
[面试题原文连接](http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650820648&idx=1&sn=cb9ee924f2ded3358dd6c256803cc687&scene=4#wechat_redirect)
，看到这些面试题的时候，觉得这些问题挺不错的，有些我是不知道，有些用过说不出来，有些是记得模糊了，故决定网上搜下和结合自身经历对面试题做出一些回答
（不作详细回答）。
##1.Java技术
###1.1 Java基础
1.对抽象、继承、多态的理解？<br>
抽象是为继承而存在的，抽象类包含了其子类共用的方法和属性，抽象类中的抽象方法，子类给予不同的表现形式，形成了多态，接口中不同的实现类
也实现了多态。<p>
2.泛型的作用及使用场景？<br>
参数化类型，由原来具体的类型参数化。集合使用时，指定添加的类型，编译时会进行检查。JavaEE开发时，Dao层一般会写个基接口，会用泛型。查询某条记录返回对象时，会写成这样形式
 ```java
 public T get(Integer id);
 ```
由其子接口指定类型。Android开发时，后台返回的Json数据，解析成一个对象时，用泛型，可以少很多代码，调用时传入类型。
```java 
    public static <T> T convertJson(String json,Class<T> clazz){
        Gson gson = new Gson();
        T t = gson.fromJson(json,clazz);
        return t;
    }
```
3.枚举的特点及使用场景？<br>
枚举可以代替一组常量，比如一周天数、一年四季，使用一组int型常量会类型不安全。String类型打印时会有性能问题，另外可读性没有enum好。<p>
4.线程sleep和wait的区别？<br>
`sleep`没有释放锁，而`wait`方法释放了锁。<p>
5.JAVA反射机制？<br>
运行状态时，对于一个类，能够知道任意属性和方法，通过对象可以调用任意方法，这种动态获取信息和调用方法的功能称为Java的反射机制。开源框架SSH用的比较多，例如action的实例创建、setter注入和注解。<p>
6.weak/soft/strong引用的区别？<br>
`strong`是Java默认引用方式，没有对象指向时GC回收。`Soft`是JVM内存不足时会被GC回收。`Weak`当所引用的对象在JVM内不再有强引用时会被GC回收。<p>
7.Object的hashCode()与equals()的区别和作用?<br>
`equals`判断是两个对象是否相同，比较是地址，如果是String对象比较，比较的是对象内容。`hashCode`默认是对象内部地址转换成的一个整数，一般用在集合，不由用户调用，加入集合时先判断`hashCode`，再判断`equals`。
###1.2 集合类
1.JAVA常用集合类功能、区别和性能？<br>
List元素可重复，有放入顺序。`ArrayList` 查询效果高，增删慢,线程不安全。`Vector`开销大，多用于查询,线程安全。`LinkedList` 增删快，查询慢,线程不安全；Map键值对，唯一键，无放入顺序。`HashMap`线程不安全。`TreeMap`排序功能，元素需要实现Comparable接口，线程不安全。`HashTable`线程安全,非空元素,`ConcurrentHashMap`线程安全，较HashTable效率高；Set元素不可重复，无放入顺序。`HashSet`通过HashMap实现的。`TreeSet`唯一元素，通过HashMap实现的。<p>
2.并发相关的集合类？<br>
`Vector`、`HashTable`、`ConcurrentHashMap`、`CopyOnWriteArrayList`,后两个实现了更高的并发性。<p>
3.部分常用集合类的内部实现方式<br>
`ArrayList`使用数组保存所有元素；`LinkedList`使用双向链表；`HashMap`哈希表，其哈希表实现方式数组和链表。
###1.3 多线程相关
1.Thread、Runnable、Callable、Futrue类关系与区别？<br>
JDK1.5之前实现多线程有两种方式，一种继承`Thread`重写run方法；一种实现`Runnable`接口，实现run方法，作为`Thread`的构造方法参数，来启动线程。它们的run方法没有返回值并不能抛出异常。之后加了`Callable`这个接口来实现多线程。`Callable`中定义了call方法，有返回值，可抛出异常，`Future`可以拿到这个返回值，并可以对`Callable`取消操作。<p>
2.JDK中默认提供了哪些线程池，有何区别?<br>
`Executors.newFixedThreadPool`创建一个指定工作线程数量的线程池。每当提交一个任务就创建一个工作线程，如果工作线程数量达到线程池初始的最大数，则将提交的任务存入到池队列中，这样可以节省线程创建开销，但空闲时，不会释放工作线程；`Executors.newCachedThreadPool`创建一个可缓存的线程池，创建的数量几乎没有限制，如果长时间线程池空闲，会自动终止，再提交任务，会重新创建一个工作线程，有一定的系统开销；`Executors.newSingleThreadExecutor`只创建唯一的工作线程来执行任务；`Executors.newScheduleThreadPool`创建给定延迟后运行命令或者定期地执行<p>
3.线程同步有几种方式，分别阐述在项目中的用法？<br>
用`synchronized`修饰方法或者代码块；使用特殊域变量`volatile`实现线程同步，修饰变量；使用`ReentrantLock`类,操作时获取锁（`lock`方法），操作完释放（`unlock`方法）；`ThreadLocal`管理变量，每一个线程都有该变量副本；使用`wait`释放锁，`notify`/`notifyAll`唤醒等待线程，需要和关键字synchronized一起使用。<p>
4.在理解默认线程池的前提下，自己实现线程池？<br>
以前写过压缩一个目录路径下所有文件的代码，应用了线程池的思想。大概做法：用四个线程压缩文件，vector存放文件，开始没有文件，线程等待；有文件添加进来，线程开始压缩文件，压缩完一个文件不销毁，继续压缩未压缩文件，直到文件压缩完4个线程才停止。
[代码](https://github.com/yanxing/note/tree/master/%E4%BB%A3%E7%A0%811)
###1.4字符
1.String的不可变性？<br>
`String`类及其变量`final`修饰，不可变。不可变主要从下面几点考虑：字符串常量池的需要,当创建一个`String`对象时,假如此字符串值已经存在于常量池中,则不会创建一个新的对象,而是引用已经存在的对象；允许`String`对象缓存`HashCode`，Java中`String`对象的哈希码被频繁地使用,不可变保证`hashcode`唯一性，可以对其缓存，有利于性能提高；出于安全性考虑，字符串经常作为网络连接、数据库连接等参数，不可变可以保证连接的安全性。<p>
2.StringBuilder和StringBuffer的区别？<br>
由于String是不可变得，不适合频繁字符串操作，`StringBuilder`和`StringBuffer`字符串变量，适合频繁字符串操作，`StringBuilder`线程非安全，`StringBuffer`线程安全，速度弱于`StringBuilder`。<p>
3.StringBuilder和StringBuffer的区别？<br>
`Unicode`为每种语言中的每个字符设定了统一并且唯一的二进制编码，以满足跨语言、跨平台进行文本转换、处理的要求；`UTF-8`编码是一种目前广泛应用于网页的编码,以满足同一页面显示中文简体繁体及其它语言；`GB2312`编码适用于汉字处理、汉字通信等系统之间的信息交换。<p>
4.正则表达式相关问题？<br>
Java里面使用正则表达式需要用到Pattern和Matcher两个类，用到正则表达式时，我基本都是网上搜来，改下，需要注意转义字符。<p>
###1.5 注解<br>
1.注解的使用？<br>
2.注解的级别及意义？<br>
3.如何自定义注解？<br>
三个问题放在一起说。注解是为类、方法、变量等添加的注释。可以用来生成文档、减少配置文件（例如spring、springmvc注解配置可以替代xml中bean和control的配置）、在编译时进行格式检查。自定义注解需要用Java提供的元注解来注解，`@interface`用来声明一个注解，元注解包括：`@Target`：用于描述注解的使用范围，包括ElementType.CONSTRUCTOR（构造器声明）、ElementType.FIELD（域声明，包括enum实例）、ElementType.LOCAL_VARIABLE（局部变量声明 ）、ElementType.METHOD（方法声明 ）、 ElementType.PACKAGE（包声明）、 ElementType.PARAMETER（参数声明 ）、ElementType.TYPE（类、接口，包括注解类型或enum声明）；`@Retention`：表示在什么级别保存该注解信息，包括： RetentionPolicy.SOURCE（注解将被编译器丢弃）、RetentionPolicy.CLASS（注解在class文件中可用，但会被VM丢弃）、RetentionPolicy.RUNTIME（VM将在运行期也保留注释，因此可以通过反射机制读取注解的信息）; `@Document`：将此注解包含在javadoc中; `@Inherited`：允许子类继承父类中的注解。注解的解析依赖于反射，一般平时编程不需要自己写注解，除非写框架，减少配置时。
##2.Android技术
###2.1 Android基础<br>
1.四大组件的意义及使用，生命周期回调及意义<br>
`Activity`是一个单独的屏幕（窗口），可以显示一些控件，需要在AndroidManifest.xml中声明，生命周期主要依次为：onCreate、onRestart(回退到`Activity`时调用)、onStart、onResume、onPause、onStop、onDestroy。启动Activity会先调用onCreate方法，然后调用onStart方法，最后调用onResume，`Activity`进入运行状态；跳转到其他Activity，或按下Home键，会先调用onPause,再调用onStop(如果跳转的`Activity`的主题是透明的，onStop不会调用)；锁屏调用onPause和onStop;按返回键，调用onPause、onStop和onDestroy。<br>
`BroadcastReceive`广播在android中是一种用在应用程序内部或之间传递消息的机制，分为普通广播和有序广播，广播接收器注册方式分为静态注册和动态注册。静态注册生命周期随着应用程序进程销毁er销毁，动态注册随着当前Activity销毁而销毁。广播接受者需要在10S内处理完逻辑，否则报ANR。<br>
`Service`没有UI界面，运行在后台。启动`Service`有两种：startService和bindService，startService方式启动的`Service`生命周期依次为onCreate->onStartCommand->onDestroy，如果没有调用stopService，Service会一直在后台运行，多次启动`Service`，onCreate方法不会多次执行，onStartCommand会多次调用。bindService启动的`Service`生命周期依次为onCreate->onBind->onUnBind->onDestroy，它会随着Activity销毁而销毁，多次启动，onCreate和onBind方法并不会多次执行。<br>
