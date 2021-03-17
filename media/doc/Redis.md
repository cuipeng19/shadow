## 目录

- [简介](#简介)
- [数据类型](#数据类型)
    - [String](#String)
    - [Hash](#Hash)
    - [List](#List)
    - [Set](#Set)
    - [SortedSet](#SortedSet)
- [过期时间](#过期时间)
    - [定期删除和惰性删除](#定期删除和惰性删除)
    - [内存淘汰机制](#内存淘汰机制)
- [持久化](#持久化)
- [缓存穿透](#缓存穿透)
- [缓存击穿](#缓存击穿)
- [缓存雪崩](#缓存雪崩)
- [并发竞争key](#并发竞争key)
- [主从复制](#主从复制)


## 简介

C语言开发的内存数据库，采用非阻塞IO多路复用机制，读写速度快，k-v形式存储数据，支持多种数据类型、持久化、集群，提供过期时间和淘汰机制。


## 数据类型

### String

value不仅可以是String，也可以是数字。  
应用：常规计数

### Hash

散列表，适合存储对象。  
应用：存储用户信息，商品信息

### List

双向链表  
应用：关注列表，粉丝列表，消息列表，

### Set

无序集合，实现交集、并集、差集操作。  
应用：共同关注、共同粉丝、共同喜好

### SortedSet  

增加权重参数score，使集合中的元素按照score进行有序排列。  
应用：在线用户列表，礼物排行榜，弹幕消息


## 过期时间

### 定期删除和惰性删除

* 定期删除：默认没隔100ms，随机抽取一些设置了过期时间的key，检查是否过期，是则删除。
* 惰性删除：过期的key没有没定期删除，在查询key的时候发现过期后删除

### 内存淘汰机制

* volatile-lru：从已设置过期时间的数据集中，挑选最近最少使用的数据淘汰
* volatile-ttl：从已设置过期时间的数据集中，挑选即将过期的数据淘汰
* volatile-random：从已设置过期时间的数据集中，任意选择数据淘汰
* allkeys-lru：内存不足以容纳新写入数据时，移除最近最少使用的key
* allkeys-random：从数据集中任意淘汰数据
* no-eviction：禁止淘汰数据
* volatile-flu：从已设置过期时间的数据集中，挑选最少使用的数据淘汰
* allkeys-flu：内存不足以容纳新写入数据时，移除最少使用的key


## 持久化

为了重用数据，或者防止系统故障而将数据备份到一个远程的位置。

* RDB：创建快照来获取内存里的数据，每1/5/15min同步一次
* AOF：将写操作追加到文件中，每秒同步一次
* 混合：RDB+AOF


## 缓存穿透

大量请求访问缓存中不存在的key，导致请求直接落到数据库上。  
解决方案：布隆过滤器


## 缓存击穿

大量请求访问的热点数据失效，导致请求直接落到数据库上。
解决方案：热点数据每次访问增加有效期


## 缓存雪崩

缓存在同一时间大面积失效，导致请求直接落到数据库上。
解决方案：均匀分布失效时间，熔断减少DB瞬间压力


## 并发竞争key

多个系统同时对一个key操作，最后的执行顺序和期望顺序不同。
解决方案：分布式锁，消息队列串行


## 主从复制

一台Redis服务器的数据，复制到其他的Rrdis服务器，前者成为主节点，后者为从节点。

### 作用

故障恢复：主节点故障时，可由从节点提供服务

#### 全量复制
