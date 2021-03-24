## 目录

- [Java并行程序基础](#Java并行程序基础)
    - [概念](#概念)
        - [临界区](#临界区)
        - [阻塞](#阻塞)
        - [死锁、饥饿、活锁](#死锁、饥饿、活锁)
        - [进程](#进程)
        - [线程](#线程)
    - [Java内存模型JMM](#Java内存模型JMM)
        - [原子性](#原子性)
        - [可见性](#可见性)
        - [有序性](#有序性)
    - [线程的生命周期与状态](#线程的生命周期与状态)
    - [线程的基本操作](#线程的基本操作)
        - [新建线程](#新建线程)
        - [终止线程](#终止线程)
        - [线程中断](#线程中断)
        - [等待(wait)和通知(notify)](#等待(wait)和通知(notify))
        - [挂起(suspend)和继续执行(resume)](#挂起(suspend)和继续执行(resume))
        - [等待线程结束(join)和谦让(yield)](#等待线程结束(join)和谦让(yield))
    - [volatile](#Volatile)
    - [守护线程(Daemon)](#守护线程(Daemon))
    - [线程优先级](#线程优先级)
    - [线程安全与synchronized](#线程安全与synchronized)
- [JKD并发包](#JKD并发包)
    - [同步控制](#同步控制)
        - [重入锁ReentrantLock](#重入锁ReentrantLock)
        - [条件Condition](#条件Condition)
        - [信号量Semaphore](#信号量Semaphore)
        - [读写锁ReadWriteLock](#读写锁ReadWriteLock)
        - [倒计时器CountDownLatch](#倒计时器CountDownLatch)
        - [阻塞工具LockSupport](#阻塞工具LockSupport)
    - [线程池](#线程池)
        - [内部实现](#内部实现)
        - [任务队列](#任务队列)
        - [拒绝策略](#拒绝策略)
- [锁](#锁)
    - [锁优化](#锁优化)
    - [无锁](#无锁)
    - [比较交换CAS](#比较交换CAS)
    - [无锁的线程安全整数AtomicInteger](#无锁的线程安全整数AtomicInteger)


## Java并行程序基础


### 概念

#### 临界区

表示一种公共资源/共享数据，每次只能有一个线程使用。

#### 阻塞

临界区资源被占用，导致其他线程挂起等待。

#### 死锁、饥饿、活锁

* 死锁：互相占用对方需要的资源不释放
* 饥饿：线程因为种种原因无法获得资源，如优先级太低
* 活锁：资源不断在线程间跳动，而没有一个线程可以同时拿到所有资源

#### 进程

操作系统进行资源分配、调度的基本单位。

#### 线程

轻量级进程，在进程内分配资源、调度的基本单位。多线程共享进程的堆、方法区，线程私有程序计数器、虚拟机栈、本地方法栈。


### Java内存模型JMM

#### 原子性

一系列操作不可中断，视为一个基本单位。(32位系统中，64位long型数据的读写不是原子性)

#### 可见性

当一个线程修改了共享变量的值，其他线程可立即知道这个修改。

#### 有序性

指令重排序。


### 线程的生命周期与状态

![线程的生命周期与状态](../picture/thread/thead状态.png)
* NEW新建线程
* RUNNABLE新建后可运行
* RUNNING分配时间片运行
* BLOCKED遇到snchronized同步块，进入阻塞状态
* WAITING/TIMED_WAITING等待另一个线程唤醒
* TERMINATED终止


### 线程的基本操作

#### 新建线程

新建线程调用start(),start方法会执行run()。  
直接调用run()是在当前线程中把run当做普通方法调用，没有开启新线程。

#### 终止线程

* 执行完毕后正常结束
* Thread.stop()：强行终止线程，释放线程持有的锁

#### 线程中断

Thread.interrupt()不会使线程立即退出，而是给线程发送退出通知(设置中断标志位)，线程收到通知后自行处理。

#### 等待(wait)和通知(notify)

一个线程调用object.wait()，线程会进入object对象的等待队列，当object.notify()被调用时，从等待队列随机唤醒一个线程。

#### 挂起(suspend)和继续执行(resume)

Thread.suspend()不释放锁。

#### 等待线程结束(join)和谦让(yield)

* join：新建并加入当前线程，让当前线程等待(当前对象调用wait())。
* yield：使当前线程让出CPU


### Volatile

保证单个操作的原子性、数据的可见性、指令的有序性。


### 守护线程(Daemon)

Thread.setDaemon()在后台完成系统性的服务。


### 线程优先级

Thread.setPriority()


### 线程安全与synchronized

保证执行结果的正确性。  
synchronized实现线程间的同步，对同步的代码加锁，使得每次只有一个线程进入同步块。
* 指定对象加锁
* 作用于实例方法
* 作用于静态方法


## JKD并发包

### 同步控制

决定一个线程是否可以访问临界区资源。

#### 重入锁ReentrantLock

显示的操作过程，局限于一个线程可以反复进入临界区。底层实现：
* 原子状态：CAS操作存储当前锁的状态，判断锁是否被别的线程持有
* 等待队列：没有请求到锁的线程进入等待队列，有线程释放锁后，从等待队列唤醒一个线程
* park/unpark：用来挂起和恢复线程

#### 条件Condition

重入锁的好搭档
* await：使线程等待，释放锁
* signal：唤醒线程

#### 信号量Semaphore

信号量是对锁的扩展，锁每次只允许一个线程访问一个资源，信号量可以指定多个线程同时访问一个资源。
* acquire：尝试获得准入许可，若无法获得，线程会等待
* release：释放许可

#### 读写锁ReadWriteLock

读写分离减少锁竞争，读读非阻塞，读写阻塞。

#### 倒计时器CountDownLatch

* await：要求主线程等待所有任务全部完成
* countDown：完成任务，计时器-1

#### 阻塞工具LockSupport

* park：静态方法，阻塞当前线程，释放资源
* unpark：静态方法，唤醒线程


### 线程池

对线程复用，避免频繁创建和销毁线程。

#### 内部实现

![线程池实现原理](../picture/thread/线程池实现原理.png)
ThreadPoolExecutor
* corePoolSize：核心线程数量
* maximumPoolSize：最大线程数量
* keepAliveTime：超过核心线程数量的空闲线程的存活时间
* unit：时间单位
* workQueue：任务队列，被提交但尚未执行的任务
* threadFactory：线程工厂
* handler：拒绝策略

#### 任务队列

* SynchronousQueue：直接提交的队列，没有容量，直接将任务提交给线程执行，没有核心线程则创建新线程
* ArrayBlockingQueue：有界队列，没有核心线程则进入队列，队列满则创建新线程
* LinkedBlockingQueue：无界队列，没有核心线程则进入队列
* PriorityBlockingQueue：优先任务队列，无界，根据任务优先级执行

#### 拒绝策略

* AbortPolicy：直接抛出异常
* CallerRunsPolicy：交给调用主线程执行
* DiscardOldestPolicy：丢弃最老的一个请求
* DiscardPolicy：直接丢弃


## 锁

### 锁优化

* 减小锁粒度
* 读写分离

### 无锁

锁是悲观策略，假设了每一次的临界区操作会产生冲突。无锁是乐观操作，假设了对资源的访问没有冲突。

#### 比较交换CAS

compareAndSet，旧值和预期值相等，把新值更新到旧值，否则什么都不做。存在ABA问题。

#### 无锁的线程安全整数AtomicInteger

使用CAS修改