# publicService_platform



**本人的毕业设计**



## 项目介绍：



​	当前广州市内针对关爱特殊儿童的公益服务活动项目存在信息发布渠道分散、市民与志愿者报名方式不统一等问题。这两大问题的存在是公益事业发展的两大阻力。针对上述问题，开发了一款基于微信小程序的公益服务平台。该平台由公益服务平台小程序与后台管理网站两部分组成。其中，公益服务平台小程序的开发使用微信小程序框架实现了用户一站式的公益活动信息收集平台与报名渠道，以解决当前广州市内公益事宜信息渠道分散的问题；后台管理网站主要基于Vue+element-UI+BootStrap等前端框架开发平台管理后台页面，完成平台数据的前端渲染。公益服务平台的后端开发主要使用了Spring+SpringMVC+Mybatis等主流后端开发框架，实现了用户登录、用户报名、用户个人信息管理、活动信息管理、活动内容展示、报名信息管理这六大模块，以实现后台管理网站对活动信息、活动报名信息进行统一的数据化管理。通过该项目实现对社会公益服务活动信息获取渠道的统一化、信息化收集，并对用户报名方式实现信息化的统一管理。



## 项目结构：



##### 	·	微信小程序前端页面代码 - WeChat_Project

##### 	·	后台管理系统前端页面代码 - web

##### 	·	后端代码 - java/com/lin



## 所使用框架：



#### 	·	微信小程序前端页面：

##### 使用了微信开发者工具；使用了 wxml、wxss 等技术；wx.login()、wx.request 等 API.



#### 	·	后台管理系统前端页面代码：

##### 使用 Vue、JSP、Bootstrap、element-UI、jQuery、AJAX 等技术开发.



#### 	·	后端代码：

##### 使用 Spring、SpringMVC、Mybatis、Maven 等技术开发，框架采用 MVC 三层架构，设计模式使用了 Spring 的 IoC 控制反转的设计模式.

##### 在本项目中：

 	1. 定义了 Controller 层，底层运用了 SpringMVC 框架下的 DispacherSerlvet，实现了对微信视图层和管理网站视图层发出的 HTTP 请求的接收与路由分发；
 	2. 定义了 Service 层，完成路由分发的请求会**向上调用**对应 Service 层的方法，完成业务逻辑；
 	3. 定义了 DAO（持久层），由 Service 层调用，使用 Mybatis 框架的注解式开发实现了对 DBMS 进行增删改查；
 	4. 项目整体类对象之间的关系交由 Spring 框架的 IOC 容器管理，通过注解标识类与配置包扫描，实现控制反转的设计模式；



## 运行效果：



### 	一、微信小程序用户界面

​		**活动信息浏览界面：**

![image-1](https://s2.xptou.com/2023/05/18/6465f8eeac887.png)



​		**报名信息浏览界面**

![image-2](https://s2.xptou.com/2023/05/18/6465f8f2b9f12.png)

### 	二、后台管理系统界面

​		**后台登录界面：**

![image-3](https://s2.xptou.com/2023/05/18/6465f8e75dfba.png)



​		**后台管理主界面：**

![image-4](https://s2.xptou.com/2023/05/18/6465f8f6e6288.png)



​		**活动信息修改界面：（使用 Bootstrap 实现的模态窗口）**

![image-5](https://s2.xptou.com/2023/05/18/6465f8f9c4577.png)



​		**删除活动提示模态窗口：**

![image-6](https://s2.xptou.com/2023/05/18/6465f8fc729f0.png)



​		**活动报名信息显示模态窗口：**

![image-7](https://s2.xptou.com/2023/05/18/6465f8ea461ca.png)

