## 目录
- [Set](#Set)
    - [Set的实现类](#Set的实现类)
    - [HashSet](#HashSet)
        - [简介](#HashSet简介)
    - [TreeSet](#TreeSet)
        - [简介](#TreeSet简介)
        - [核心源码](#TreeSet源码)
        
## Set

无序集合，不允许重复元素。继承Collection。

### Set的实现类

* HashSet  
底层是哈希表

* TreeSet  
底层是红黑树

### HashSet

#### HashSet简介

* 内部维护一个HashMap，元素存于key，value默认Object对象。
* 继承AbstractSet，实现Set，提供了相关的添加、删除、修改、遍历等功能。  
* 实现Cloneable接口，即覆盖了函数clone()，可被克隆。  
* 实现java.io.Serializable接口，支持序列化，能通过序列化去传输。

### TreeSet

#### TreeSet简介

#### TreeSet源码