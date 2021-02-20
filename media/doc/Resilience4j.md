## 目录

- [Resilience4j](#新断路器Resilience4j)
    - [与Hystrix的不同点](#与Hystrix的不同点)


## 新断路器Resilience4j


### 与Hystrix的不同点

* Hystrix将调用封装为Hystrix命令，Resilience4j使用函数式编程和Lambda表达式。
* Hystrix将结果存储在时间窗桶，Resilience4j使用环形位缓冲区。
* Hystrix打开5秒后，修改为伴打开状态，自后只通过一次请求就能关闭断路器，Resilience4j使用阈值来确定断路器的状态。