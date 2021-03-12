## 目录

- [CAP](#CAP)
- [BASE](#BASE)


## CAP

起源于2000年，加州大学伯克利分校的Eric Brewer教授在分布式计算原理研讨会(PODC)提出，又称布鲁尔定理(Brewer's theorem)。  
两年后，麻省理工学院的Seth Gilbert 和 Nancy Lynch发表了布鲁尔猜想的证明，CAP理论正式成为分布式领域的定理。
* Consistency(一致性) 所有节点访问同一份最新的数据副本
* Availability(可用性) 非故障节点在合理的时间内返回合理的响应
* Partition Tolerance(分区容忍性) 分布式系统出现网络分区的时候，仍然能够对外提供服务

发生网络分区时，一致性和可用性二选一，即CP/AP架构。

## BASE

起源于2008年，eBay的架构师Dan Pritchett在ACM上发表。是对AP方案的一个补充。
* Basically Available(基本可用) 出现不可预知故障的时候，允许损失部分可用性
* Soft State(软状态) 数据的中间状态，该状态不会影响整体可用性，即允许数据同步存在延时
* Eventually Consistent(最终一致性) 数据同步经过一段时间后，最终能够达到一个一致的状态