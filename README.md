## spring cloud

Hoxton.SR6


## 目录

- [每周一问](media/doc/weeklyQuestion.md)
- [服务治理](#服务治理)
    - [Eureka](media/doc/Eureka.md)
- [负载均衡](#负载均衡)
    - [Ribbon](media/doc/Ribbon.md)
- [断路器](#断路器)
    - [Hystrix](media/doc/Hystrix.md)
    - [Resilience](media/doc/Resilience4j.md)
- config
- openfeign
- sleuth
- [服务网关](#服务网关)
    - [Gateway](media/doc/Gateway.md)
- actuator
- [容器](media/doc/容器.md)
- [网络](media/doc/network.md)
- [JVM](media/doc/JVM.md)
- [设计理论及原则](#设计理论及原则)
    - [CAP&Base](media/doc/CAP&BASE.md)
    - [SOLID原则](media/doc/SOLID原则.md)



## 服务治理

服务治理是微服务架构中最核心和基础的模块，用来实现微服务实例的自动化注册与发现。通过静态配置的服务调用，随着系统复杂度的增加会难以维护，需要一个中间代理进行服务调用。



## 负载均衡



## 断路器

使微服务系统在糟糕的情况下，仍然保证可用性。提供熔断、服务降级等功能。



## 服务网关

统一进行鉴权、限流、验签等。对外统一提供服务，屏蔽内部独立服务的访问。子服务的添加和移除对外无感知，增加系统扩展性。



## 设计理论及原则