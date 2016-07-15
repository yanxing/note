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
枚举可以代替一组常量，比如一周天数、一年四季，使用一组int型常量会类型不安全。String类型打印时会有性能问题，另外可读性没有enum好。
4.线程sleep和wait的区别？<br>
sleep没有释放锁，而wait方法释放了锁。
5.JAVA反射机制？
运行状态时，对于一个类，能够知道任意属性和方法，通过对象可以调用任意方法，这种动态获取信息和调用方法的功能称为Java的反射机制。开源框架SSH用的比较多，例如action的实例创建、setter注入和注解。
6.weak/soft/strong引用的区别？
strong是Java默认引用方式，没有对象指向时GC回收。Soft是JVM内存不足时会被GC回收。Weak当所引用的对象在JVM内不再有强引用时会被GC回收。
