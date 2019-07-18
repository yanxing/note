![请求器](https://github.com/yanxing/note/raw/master/image/4.png)
1. 网络请求器：默认使用okhttp3.Call.Factory。
2. 网络请求适配器：将网络请求执行器转化成适合不同平台调用的网络请求执行器形式。
3. 数据转化器：将返回的数据解析成我们需要的数据类型。
4. 回调方法执行器：切换线程。


#### 请求流程
1. 把网络请求接口注解参数解析成请求参数。
2. 动态代理生成网络请求对象。
![动态代理](https://github.com/yanxing/note/raw/master/image/4.png)
3. 发送网络请求。
4. 处理返回的数据。
