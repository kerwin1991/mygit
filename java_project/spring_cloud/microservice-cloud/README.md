## 常见面试题 ##

0. 什么是微服务？
    
    - 微服务架构是一种架构模式，架构风格。提倡将单一应用程序划分成一组小的服务，每个服务运行在独立进程中，服务之间相互配合相互协调，为用户提供最终价值。服务间采用轻量级的通信机制相互沟通（通常基于HTTP的RESTful API）。服务围绕业务功能构建，独立部署。
    - 将传统的一站式应用，根据业务拆分成一个一个的服务，彻底的去耦合，每个微服务提供单个业务功能的服务，一个服务做一件事。从技术角度，就是一个小而独立的处理过程类似进程概念，能够自行单独启动销毁，可连接自己的数据库，选不同的技术，不同的语言实现，将耦合度降低到最小
    - 单机系统：all in one ，商品、订单、交易、库存 war。分布式系统：各个模块/服务，各自微小一个进程，专业的模块做专业的事，独立部署。
    关键字：拆分；各自独立的进程；
    
1. 微服务之间是如何独立通讯的？
2. springcloud 和 dubbo 有哪些区别？

    - 通信机制不同：restful-rpc远程过程调用
    - SpringCloud抛弃了dubbo的RPC通信，采用的是基于HTTP的REST方式。 详见图片

3. springboot springcloud ，谈谈你对他们的理解？

    - boot:关注的是微观，个体，专注于快速方便的开发单个个体微服务；cloud：是关注全局的微服务协调整理治理框架，它将boot开发的一个个单体微服务整合并管理起来。为各个微服务之间提供，配置管理，服务发现，熔断器，路由，微代理，事件总线，全局锁，决策竞选，分布式会话等等集成服务。
    - boot:可以离开cloud独立使用开发项目；cloud：离不开boot，依赖于boot。
    
    总结：SpringBoot专注于快速、方便开发单个微服务个体，SpringCloud关注全局的服务治理框架。

4. 什么是服务熔断？什么是服务降级？
5. 微服务的优缺点是什么？说下你在项目中开发碰到的坑？
6. 你所知道的微服务技术栈有哪些？请列举一二
7. eureke和zookeeper都可以提供服务注册和发现的功能，请说说两个的区别？

## 课堂问题 ##

0. 微服务和微服务架构？
    0. 微服务    
        强调的服务大小，关注某一个点，强调的是一个个的个体，是具体解决某一个问题/提供落地服务的一个服务应用。每个个体完成一个具体的任务，功能。狭义的看，idea中的一个工程。
    1. 微服务架构        
        强调的是整体，如上。。第一个问题。
        
1. 微服务优缺点?
2. 微服务技术栈？
    - 服务治理：dubbo
    - 服务注册：zookeeper
    - 服务调用 
    - 服务负载均衡：Nginx
    - 服务监控
    
    而，springcloud包含不限于以上技术。包含了，分布式架构的落地维度，一个维度对应一个技术。
    
3. spingcloud是什么？

    - 是什么？ 答：分布式微服务架构下的一站式解决方案，是各个微服务架构落地技术的集合体，俗称微服务全家桶！！
    - 能干嘛？
    - 去哪儿下？
    [官网](http://projects.spring.io/spring-cloud/) 
    [中文版](https://springcloud.cc/spring-cloud-netflix.html)
    [API](http://cloud.spring.io/spring-cloud-static/Dalston.SR1/)
    [中文API](https://springcloud.cc/spring-cloud-dalston.html)
    [SpringCloud中文网](https://springcloud.cc/)
    [SpringCloud中文社区](https://springcloud.cn/)
    
    - 怎么玩？21种技术
    - 国内使用情况？



## SpringCloud 学习环境搭建 ##

- 行业规范： 约定 > 配置 > 编码

- 项目构建：

    - 创建工程，维护pom关系，导入依赖，如框架，junit，entity类等等共用的
    - 配置application.yml，spring整合mybatis
    - 数据库表创建，脚本执行
    - 创建dao层，mapper mapper.xml
    - 创建sevice层，*Service *SericeImpl
    - 根据RESTful风格，创建Controller层
    
### 总结 ###

    微服务划的核心就是将统一的一站式应用，根据业务拆分成一个一个的服务，彻底的去耦合，每一个微服务提供单个业务功能的服务，一个服务做一件事。从技术角度看就是一种小而独立的处理过程，类似进程概念，能够自行单独启动或销毁，拥有自己独立的数据库。

## Eureka ##

0. 简介

    Eureka是Netflix的一个子模块，是一个基于REST的服务，用于定位服务，以实现云端中间层服务发现和故障转移。Netflix在设计Eureka时遵守的是AP原则。？
服务注册和发现对于微服务架构是非常重要的，有了服务注册发现，只需要使用服务的标识符，就可以访问到服务，而不需要修改服务调用的配置文件了。
功能类似于dubbo的注册中心，比如Zookeeper。

1. 引入cloud的一个新技术组件步骤

    - 新增一个maven坐标，如：
    ```
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-eureka-server</artifactId>
    </dependency>
    ```
    - 启动类上标注启动该新组件的注解，如：@EnableEurekaServer
    - java业务逻辑编码

2. Eureka自我保护机制

    某时刻某一个微服务，eureka不会立刻清理，依旧会对该微服务的信息进行保存。(好死不如赖活着)。

3. 在Eureka服务监控页面，点击注册的服务名称时，默认http://hostname:port/info，通过修改后，http://ip:port/info，需要引入依赖
    ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    ```
    
    - 对于注册进eureka里面的微服务，可以通过服务发现来获得该服务的信息
    
4. eureka集群配置
    
    在不同的机器/服务器，配置相同的服务，对外提供一个超大运算的整体。
    
    
5. CAP

    - RDBMS(mysql,oracle,sqlServer) ===== ACID 原则
        A atomicity 原子性；C consistency 一致性； I isolation 独立性； D durability 持久性。
    
    - NoSql(redis,mongdb) ===== CAP 原则
        C consistency 强一致性； A availability 可用性； P partition tolerance 分区容错性。

    - 在一个分布式系统里，有一句话，CAP的三进二:任何一个分布式系统，没有办法同时满足，强一致，高可用，分区容错。
      如，对于淘宝网址，1111当天，如果让你来选择，选  AP?   CP?
      当天要选AP，P是绝对要保证的，当天保证可用A。对于一致性C，后面再慢慢核对统计。



## Ribbon 负载均衡 ##

Spring Cloud Ribbon 是基于 Netflix Ribbon实现的一套 客户端 负载均衡 的工具。

[Github源码地址](https://github.com/Netflix/ribbon)

Ribbon和Eureka整合后，Consumer可以直接调用服务而不用再关心地址和端口号。

涉及注解： @LoadBalanced 。(已经简化到一个注解就可以使用负载均衡)

总结：Ribbon其实就是一个软负载均衡客户端组件，他可以和其他所需请求的客户端结合使用，和eureka结合只是其中的一个实例。
    Eureka客户端，DiscoveryClient 查询服务时，会基于负载均衡算法，匹配相同服务的一个服务。默认基于轮询算法，每次查询，都是不同的ip。
    
```
{"services":["microservice-cloud-dept"],"localServiceInstance":{"host":"10.110.5.101","port":8003,"metadata":{},"serviceId":"microservice-cloud-dept","uri":"http://10.110.5.101:8003","secure":false}}
{"services":["microservice-cloud-dept"],"localServiceInstance":{"host":"10.110.5.101","port":8001,"serviceId":"microservice-cloud-dept","metadata":{},"uri":"http://10.110.5.101:8001","secure":false}}
{"services":["microservice-cloud-dept"],"localServiceInstance":{"host":"10.110.5.101","port":8002,"secure":false,"metadata":{},"serviceId":"microservice-cloud-dept","uri":"http://10.110.5.101:8002"}}
```

### @RibbonClient ###

加在主启动类上，在启动该微服务的时候就能去加载我们的自定义Ribbon配置类，从而使配置生效。 

[RandomRule源码](https://github.com/Netflix/ribbon/blob/master/ribbon-loadbalancer/src/main/java/com/netflix/loadbalancer/RandomRule.java)



## Feign 负载均衡 ##

用Ribbon进行负载均衡，功能强大，甚至可以自己定义算法。Feign怎么出来的？

通过Eureka Ribbon 可以实现直接调用微服务名称来进行访问。http://MICROSERICE-CLOUD-DEPT

现在习惯面向接口编程，微服务名字获得调用地址；通过接口 + 注解，获得我们的调用服务。

只需要创建一个接口，然后在上面添加注解即可。 面向接口调用微服务


## Hystrix ##

[源码](https://github.com/Netflix/Hystrix/wiki)

0. 服务熔断

1. 服务降级
    
    0. 简介
    
        - 整体资源快不够了，忍痛将某些服务先关掉，待渡过难关，再开启回来。
        - 服务降级处理是在客户端实现完成的，与服务端没有关系。 Feign-80
    
    1. 测试
     
        - 启动：7001 7002 7003 provider8001 feign80 正常访问没问题后，故意关闭服务8001。测试方法 /dept/get/{id}
        - 提示信息：此时服务provider已经down了，但是我们做了服务降级处理，让客户端在服务端不可用时也会获得提示信息而不会挂起耗死服务器。
    

2. 对比

    - 服务熔断
    
        一般是某个服务故障或者异常引起，类似现实世界中 保险丝 ，当某个异常条件被触发，如 抛 RuntimeException，直接熔断整个服务，而不是一直等到此服务器超时。

    - 服务降级
    
        所谓降级，一般是从整体负荷考虑。就是当某个服务熔断之后，服务器将不再被调用，此时客户端可以自己准备一个本地的fallback回调，返回一个缺省值。这样做，虽然服务水平下降，但好歹可用，比直接挂掉要强。


## Zuul ##

[源码](https://github.com/Netflix/zuul)


## SpringCloud Config ##

分布式配置中心








### 附： ###

- mavne GAV坐标
- 注解 @EnableXXX
- hosts文件路径：C:\Windows\System32\drivers\etc