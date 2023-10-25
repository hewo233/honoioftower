# Task: Tower of Hanoi

## 任务说明

​	这是一个使用 java 来实现**可视化**汉诺塔的任务。对于可视化的部分，我们已经为你写好了，你需要做到是实现一个 **LinkStack** （即利用链表实现一个栈的数据结构） 和 递归 解决问题的操作。

## 前置芝士

​	java 的基础语法，**继承**的性质，递归的思维，**链表**和**栈**的理解。

## 任务说明	

​	关于我们已经给你了的东西，我们为你提供了2个jar软件包，一个 student 的 包 和 towerofhanoi 包的大部分功能。你只需要完善 位于 towerofhanoi 包中的 LinkedStack , Tower 和 HanoiSolver 即可，我们在下文会给你详细的实现需求。

​	如果你无法使用位于 lib 目录下的软件包，请自行根据使用环境搜索解决。



## 详细步骤

#### 0.UML

让我们先来看看任务通览。

![image-20231023192814351](https://s2.loli.net/2023/10/23/qPwuMbvW6e74OXY.png)

放心，大部分内容已经为你写好了，你只需要照着以下步骤来完成即可。

#### 1.LinkedStack

类的声明为

```java
public class LinkedStack<T> implements StackInterface<T>
```

（当你成功导入 lib 中的文件后，你应该可以看见 StackInterface 的代码。

##### Node

首先你需要实现一下 Node 这个类，作为一个链表的节点数据。

先回忆下你在 c 语言的链表 Node 是怎么写的。然后恭喜你，在 java 中没有传统指针，你可以自学搜索并且对标UML,来学会如何建立一个 java 链表的Node。

然后你需要实现两个构造函数，一个是只有 T,另外一个是 (T,Node)，作为标准化的写法，我们建议你这样做：

```
 Node(T data) {
            next = null;
            this.data = data;
        }

        Node(T data,Node<T> p) {
            this(data);
            this.setNextNode(p);
        }
```

对于 setNextNode 和 getNextNode,getData ,顾名思义，第一个是为 this 设置一个 next,另外两个返回 next，data; (从 UML 里面，你可以看见一个函数的参数和返回值，请善用)

##### 1.LinkedStack

###### -1.@Override

​	~~自行了解，或者IDEA帮你写了~~

###### 0.定义数据

​	照着UML写就行了（）

###### 1.LinkedStack()

​	初始化，为你的 size ，topNode 赋个你觉得该赋的值（

###### 2.push(T):void

​	往里面塞一个 Node,作为一个栈，你认为该如何使用链表往头上加一个。别忘了加size。

###### 3.pop():T

​	当栈为空的时候，throw new EmptyStackException().

​	然后弹出栈顶的东西，并且返回被你弹出去的东西的data.

###### 4.peek():T

​	当栈为空的时候，throw new EmptyStackException().

​	返回栈顶的东西的data.

###### 5.isEmpty():boolean

​	作为一个布尔型，当栈为空，返回1，否则返回0

###### 6.clear():void

​	删栈跑路。（可以了解下 java 和 c 的内存管理的区别）

###### 7.toString():String

​	如果是空栈，返回 "[]".

​	如果有数据的话，按照栈的顺序，以 "[data1, data2, ..., lastdata]"

###### 8.size():int

​	返回 size;



#### 2.Tower

Tower 作为一个继承自 LinkedStack 的类， 你应该这样声明：

```java
public class Tower extends LinkedStack<Disk>
```

Disk 我们已经给你写好了，具体可以自行查代码。

###### 1.Tower(Position position)

​	构造，使用 super()

###### 2.position(): Position

​	返回这个的 position

###### 3.push(Disk disk):

​	因为 汉诺塔 具有 小的 必须在 大的 上面的特性，所以我们这里需要重构下 push 函数。

​	当 disk 是 null 时，throw new IllegalArgumentException();

​	否则，若塔为空，直接加进去。若塔不空，使用 compareTo 来 判断可不可以放，要是不能的话， throw new IllegalStateException();







#### 3.HanoiSolver

你需要完成的是 solveTower 和 solve 这俩个函数。

###### 1. solveTowers(int currentDisks, Tower startPole, Tower tempPole, Tower endPole)

​	currentDisks 表示现在的disk数量，其余三个是3个塔，表示 从 startPole 转移到 endPole,tempPole作为空置中转用。

​	这是一个标准的递归实现的操作，你需要使用 move 函数，move(startPole, endPole)，来表示转移。这样我们才可以获取并可视化这个操作。

​	关于函数的具体实现，我们希望你自行思考（毕竟没几行）。

###### 2.solve()

​	很简单的一个函数，你只需要在其中调用 solveTowers() 即可。

​	考验下你阅读代码的能力，自行填充 4 个参数。



## 结束

恭喜你完成了任务，你现在可以使用 ProjectRunner 来运行你的程序，看看能否获得一个 汉诺塔的可视化界面。

（如果对此文档有质疑或者想玩点大的，你可以阅读origin.pdf