## 目录

- [Ribbon](#Ribbon)


## Ribbon

Ribbon是Netflix公司开发的组件，Spring Cloud通过二次封装使得更加简单易用。

Ribbon如何实现负载均衡：  
Ribbon提供了拦截器LoadBalancerInterceptor，对标注@LoadBalanced注解的RestTemplate进行拦截，然后植入LoadBalancerClient的逻辑。

负载均衡器：  
采用ILoadBalancer接口的chooseServer，进一步过滤服务实例清单中不可用、高负载的服务。  
springboot默认ZoneAwareLoadBalancer,过滤掉最高负载20%、故障率大于99.999%的服务。

负载均衡策略：  
采用IRule接口的choose，决定选择服务的方法。  
常用策略：RoundRobinRule、retryRule、WeightedResponseTimeRule、ZoneAvoidanceRule

维护服务实例清单：  
ILoadBalancer接口定义维护实例的方法，实现在BaseLoadBalancer。  
服务实例清单分为：所有服务实例清单AllServerList，可用服务实例清单UpServerList。  

获取服务实例清单：  
从Eureka服务器拉取服务实例清单，采用ServerList接口。  
默认实现DisCoveryEnabledNIWSServerList

更新服务实例清单：  
服务实例清单处于一个不断变化的过程，维护这个清单，提供ServerListUpdater接口更新。  
默认实现PollingServerListUpdater，其中的start方法创建线程定时更新。  

服务实例心跳监测：  
通过心跳判断实例是否可用，在BaseLoadBalancer的内部类Pinger的runPinger方法，使用IPingStrategy的pingServers方法
来监测服务实例，返回监测结果，状态改变则发送给监听者，修改服务实例状态。

IPing接口：  
具体执行心跳监测服务实例的是IPing接口，若配置Eureka客户端，则实现为NIWSDiscoveryPing。