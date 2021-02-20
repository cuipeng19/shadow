## 目录

- [Hystrix](#Hystrix)
    - [工作原理](#工作原理)
    - [仪表盘](#仪表盘)


## Hystrix

底层的实现是RXJava，是流形式的，采用观察者模式(Observer)实现。

### 工作原理

将请求包装成HystrixCommand(同步请求命令)，HystrixObservableCommand(异步请求命令)。

Hystrix命令：execute()、queue()、observe()、toObservable()

observableExecutionMode：
eager(启用HystrixObservableCommand.observe方法，热观察者模式，表示立即执行)、
lazy(启用HystrixObservableCommand.toObservable方法，冷观察者模式，表示延迟执行)

断路器需要实现HystrixCircuitBreaker接口，断路器状态：closed-open-half_open

判定是否打开断路器的算法：时间窗统计法，将时间窗再细分为桶。

隔离采用舱壁模式，将服务调用隔离到了各自的线程池内。

Hystrix的缓存是基于本次请求，可以通过缩小缓存超时时间来减小数据不一致的概率。

启用缓存：覆盖父类的getCacheKey。  
缓存注解：@CacheResult、@CacheRemove、@CacheKey

请求合并：  
高并发时，舱壁模式的线程池会充满请求。  
在一个很短的时间戳内，按照一定的规则进行判断，是同样的请求，就将其合并为同一个请求，只用一条线程进行请求，然后相应多个请求。  
作用域：全局、单次请求  
注解：@HystrixCollapser

线程池划分：  
如果按照组别建分配线程池，就会有很多的请求都被放到同一个线程池内的情况。  
组别建(groupKey)、线程池键(threadPoolKey)从而实现舱壁模式。

### 仪表盘

hystrix-dashboard：单体监控、Turbine聚合监控