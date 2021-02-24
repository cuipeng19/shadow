## 目录

- [每周一问](#每周一问)
    - [WeeklyQuestion](media/doc/weeklyQuestion.md)
- [服务治理](#服务治理)
    - [Eureka](media/doc/Eureka.md)
- [负载均衡](#负载均衡)
    - [Ribbon](media/doc/Ribbon.md)
- [断路器](#断路器)
    - [Hystrix](media/doc/Hystrix.md)
    - [Resilience4j](media/doc/Resilience4j.md)
- config
- [服务调用](#服务调用)
    - [OpenFeign](media/doc/OpenFeign.md)
- [链路跟踪](#链路跟踪)
- [服务网关](#服务网关)
    - [Zuul](media/doc/Zuul.md)
    - [Gateway](media/doc/Gateway.md)
- actuator
- [容器](#容器)
    - [List](media/doc/List.md)
    - [Map](media/doc/Map.md)
    - [Set](media/doc/Set.md)
- [计算机网络](media/doc/network.md)
- [JVM](media/doc/JVM.md)
- [设计理论及原则](#设计理论及原则)
    - [CAP&Base](media/doc/CAP&BASE.md)
    - [SOLID原则](media/doc/SOLID原则.md)



## 每周一问

重复产生短期记忆，检索、间隔、穿插、多样化有助于长期记忆。

检索、有间隔、有内容穿插、以及内容多样化，其实就是生活本来的面貌。

大脑神经元之间的连接极具可塑性，这会引入更多复杂的网络，这些神经回路会使大脑更加灵活。  --《认知天性》

* [WeeklyQuestion](media/doc/weeklyQuestion.md)



## 服务治理

微服务架构中最核心和基础的模块，用来实现微服务实例的自动化注册与发现。通过静态配置的服务调用，随着系统复杂度的增加会难以维护，需要一个中间代理进行服务调用。

* [Eureka](media/doc/Eureka.md)



## 负载均衡

* [Ribbon](media/doc/Ribbon.md)



## 断路器

使微服务系统在糟糕的情况下，仍然保证可用性。提供熔断、服务降级等功能。

* [Hystrix](media/doc/Hystrix.md)
* [Resilience4j](media/doc/Resilience4j.md)



## 服务调用

在Rest请求方式的基础上，提供接口声明方式的调用。

* [OpenFeign](media/doc/OpenFeign.md)



## 链路跟踪

链路监控组件，使请求能欧追踪到各个服务实例中，在发生异常时可以快速准确地定位。

* [Sleuth](media/doc/Sleuth.md)


## 服务网关

统一进行鉴权、限流、验签等。对外统一提供服务，屏蔽内部独立服务的访问。子服务的添加和移除对外无感知，增加系统扩展性。

* [Gateway](media/doc/Gateway.md)



## 容器

* [List](media/doc/List.md)
* [Map](media/doc/Map.md)
* [Set](media/doc/Set.md)



## 设计理论及原则

* [CAP&Base](media/doc/CAP&BASE.md)
* [SOLID原则](media/doc/SOLID原则.md)