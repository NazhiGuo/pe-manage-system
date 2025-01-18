![请添加图片描述](assets/logo.png)

# 东软体检项目

[TOC]

#  Part I 东软体检项目介绍

**学习目标**

- 基本业务流程
- 项目技术架构
- 页面原型设计
- 数据库设计

---

## 1.1.基本业务流程

本项目为两端架构：

- 用户端：完成体检预约全部业务流程。
- 医生端：完成对用户体检报告的编辑工作。

![请添加图片描述](assets/bus.png)



## 1.2.项目技术架构

![请添加图片描述](assets/jg.png)

弹性布局、响应式布局、栅格系统 === 24 等分

vue & react.  ant design vue

## 1.3.页面原型设计



### 1.3.1.东软体检APP系统

![请添加图片描述](assets/tu01.png)

![请添加图片描述](assets/tu02.png)

![请添加图片描述](assets/tu03.png)



### 1.3.2.东软体检报告管理系统

![请添加图片描述](assets/tu04.png)

![请添加图片描述](assets/tu05.png)

![请添加图片描述](assets/tu06.png)



## 1.4.数据库设计



###1.4.1.ER图

东软体检项目数据库设计-ER图

![请添加图片描述](assets/er.png)



###1.4.2.数据库设计总表

东软体检项目数据库设计书-总表：

![请添加图片描述](assets/db00.png)



###1.4.3.数据库设计

1. 医院信息表

![请添加图片描述](assets/db01.png)

2. 体检套餐信息表

![请添加图片描述](assets/db02.png)

3. 体检套餐项目明细表

![请添加图片描述](assets/db03.png)

4. 体检项信息表

![请添加图片描述](assets/db04.png)

5. 体检项明细表

![请添加图片描述](assets/db05.png)

6. 体检预约订单表

![请添加图片描述](assets/db06.png)

7. 总检结论信息表

![请添加图片描述](assets/db07.png)

8. 体检报告检查项信息表

![请添加图片描述](assets/db08.png)

9. 体检报告检查项明细表

![请添加图片描述](assets/db09.png)

10. 医生信息表

![请添加图片描述](assets/db10.png)

11. 用户表

![请添加图片描述](assets/db11.png)



# Part II 东软体检APP



## 2.1.服务器端项目



### 2.1.1.服务器端项目搭建



#### 2.1.1.1.开发工具检查

1. 开发工具：SpringToolSuite4+（STS）
2. 检查开发工具的 jdk 配置：jdk8
3. 检查 maven 构建工具的配置：maven3+
4. 检查开发工具的文件编码配置：utf-8

#### 2.1.1.2.搭建SpringBoot工程

1. 工程类型：

   ![请添加图片描述](assets/server01.png)

   ![请添加图片描述](assets/server02.png)

2. pom.xml文件

   主要是导入 Web 和 MyBatis 的依赖；

   另外就是 热部署 和 MySQL 驱动的依赖，其中 MySQL 驱动的要注意版本问题。

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
   	<modelVersion>4.0.0</modelVersion>
   	<parent>
   		<groupId>org.springframework.boot</groupId>
   		<artifactId>spring-boot-starter-parent</artifactId>
   		<version>2.6.4</version>
   		<relativePath/> <!-- lookup parent from repository -->
   	</parent>
   	<groupId>com.neusoft</groupId>
   	<artifactId>tijianserver</artifactId>
   	<version>0.0.1-SNAPSHOT</version>
   	<name>tijianserver</name>
   	<description>Demo project for Spring Boot</description>
   	<properties>
   		<java.version>1.8</java.version>
   	</properties>
   	<dependencies>
   		<dependency>
   			<groupId>org.springframework.boot</groupId>
   			<artifactId>spring-boot-starter-web</artifactId>
   		</dependency>
   		<dependency>
   			<groupId>org.mybatis.spring.boot</groupId>
   			<artifactId>mybatis-spring-boot-starter</artifactId>
   			<version>2.2.2</version>
   		</dependency>
   		
   		<dependency>
   			<groupId>org.springframework.boot</groupId>
   			<artifactId>spring-boot-devtools</artifactId>
   		</dependency>
   
   		<dependency>
   			<groupId>mysql</groupId>
   			<artifactId>mysql-connector-java</artifactId>
   			<version>5.1.49</version>
   		</dependency>
   
   		<dependency>
   			<groupId>org.springframework.boot</groupId>
   			<artifactId>spring-boot-starter-test</artifactId>
   			<scope>test</scope>
   		</dependency>
   	</dependencies>
   
   	<build>
   		<plugins>
   			<plugin>
   				<groupId>org.springframework.boot</groupId>
   				<artifactId>spring-boot-maven-plugin</artifactId>
   			</plugin>
   		</plugins>
   	</build>
   
   </project>
   ```

3. 工程目录结构

   - controller 控制层
   - mapper 持久化层（映射）
   - po 实体类
   - service 服务层（业务）
   - 跨域处理（WebMvcConfig）基于前后端分离，必须处理跨域问题，放在与主启动类同级处

   ![请添加图片描述](assets/server03.png)

4. SpringBoot入口文件

   ```java
   package com.neusoft.tijian;

   import org.springframework.boot.SpringApplication;
   import org.springframework.boot.autoconfigure.SpringBootApplication;

   @SpringBootApplication
   public class TijianserverApplication {

   	public static void main(String[] args) {
   		SpringApplication.run(TijianserverApplication.class, args);
   	}

   }
   ```

5. 处理跨域的Cors配置文件

   ```java
   package com.neusoft.tijian;

   import org.springframework.context.annotation.Configuration;
   import org.springframework.web.servlet.config.annotation.CorsRegistry;
   import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

   @Configuration
   public class WebMvcConfig implements WebMvcConfigurer {
       @Override
       public void addCorsMappings(CorsRegistry registry) {
           /*
            * addMapping：配置可以被跨域的路径，可以任意配置，可以具体到直接请求路径。 
            * allowedMethods：允许的请求方式，如：POST、GET、PUT、DELETE等。
            * allowedOrigins：允许访问的url，可以固定单条或者多条内容
            * allowedHeaders：允许的请求header，可以自定义设置任意请求头信息。 
            * maxAge：配置预检请求的有效时间
            */
           registry.addMapping("/**")
                   .allowedOrigins("*")
                   .allowedMethods("*")
                   .allowedHeaders("*")
                   .maxAge(36000);
       }
   }
   ```

6. application.yml 配置文件

   ```yaml
   server:
       port: 8080
       servlet:
           context-path: /tijian
           
   logging:
       level:
           org.springframework: debug
           com.neusoft.tijian.mapper: debug
           
   spring:
       datasource:
           driver-class-name: com.mysql.jdbc.Driver
           url: jdbc:mysql://localhost:3306/tijian?characterEncoding=utf-8
           username: root
           password: 123
           
   mybatis:
       mapper-locations: classpath:mapper/*.xml 
       type-aliases-package: com.neusoft.tijian.po,com.neusoft.tijian.dto   
   ```



### 2.1.2.服务器接口API

共15个接口。

#### 2.1.2.1.users

##### 1. users/getUsersByUserIdByPass（登录）

- 参数：Users对象

- 返回值：Users对象

- 功能：根据手机号码和密码进行登录验证

- 代码：

- 1、添加 PO 类 —— User

- ```java
  public class Users {
  
  	private String userId;
  	private String password;
  	private String realName;
  	private Integer sex;
  	private String identityCard;
  	private String birthday;
  	private Integer userType;
    
    // getter & setter
  }
  ```

  

- 2、添加 Mapper 类 —— UserMapper

- ```java
  @Mapper
  public interface UsersMapper {
  
  	//登录
  	@Select("select * from users where userId=#{userId} and password=#{password}")
  	public Users getUsersByUserIdByPass(Users users);
    
  }
  ```

  

- 3、添加 Service 类 —— UsersService & UsersServiceImpl

- ```java
  public interface UsersService {
  
  	public Users getUsersByUserIdByPass(Users users);
    
  }
  ```

- ```java
  @Service
  public class UsersServiceImpl implements UsersService{
  	
  	@Autowired
  	private UsersMapper usersMapper;
  
  	@Override
  	public Users getUsersByUserIdByPass(Users users) {
  		return usersMapper.getUsersByUserIdByPass(users);
  	}
  }
  ```



- 4、添加 Controller 类 —— UserController

- ```java
  @RestController
  @RequestMapping("/users")
  public class UsersController {
  
  	@Autowired
  	private UsersService usersService;
  	
  	@RequestMapping("/getUsersByUserIdByPass")
  	public Users getUsersByUserIdByPass(@RequestBody Users users) {
  		return usersService.getUsersByUserIdByPass(users);
  	}
  }
  ```

  

- 5、测试

  

##### 2. users/getUsersById（注册1）

- 参数：Users对象
- 返回值：Users对象
- 功能：根据手机号码进行是否已注册验证



##### 3. users/saveUsers（注册2）

- 参数：Users对象
- 返回值：int
- 功能：根据用户输入信息进行注册

- 代码：

- 1、编辑 Mapper 类 —— UserMapper

- ```java
  @Mapper
  public interface UsersMapper {
  
  	//登录
  	@Select("select * from users where userId=#{userId} and password=#{password}")
  	public Users getUsersByUserIdByPass(Users users);
  	
  	//用户电话号码是否已经存在的验证
  	@Select("select * from users where userId=#{userId}")
  	public Users getUsersById(String userId);
  	
  	//注册
  	@Insert("insert into users values(#{userId},#{password},#{realName},#{sex},#{identityCard},#{birthday},#{userType})")
  	public int saveUsers(Users users);
  }
  ```



- 2、编辑 Service 类 —— UsersService & UsersServiceImpl

- ```java
  public interface UsersService {
  
  	public Users getUsersByUserIdByPass(Users users);
  	public Users getUsersById(String userId);
  	public int saveUsers(Users users);
  }
  ```

- ```java
  @Service
  public class UsersServiceImpl implements UsersService{
  	
  	@Autowired
  	private UsersMapper usersMapper;
  
  	@Override
  	public Users getUsersByUserIdByPass(Users users) {
  		return usersMapper.getUsersByUserIdByPass(users);
  	}
  	
  	@Override
  	public Users getUsersById(String userId) {
  		return usersMapper.getUsersById(userId);
  	}
  	
  	@Override
  	public int saveUsers(Users users) {
  		return usersMapper.saveUsers(users);
  	}
  }
  ```



- 3、编辑 Controller 类 —— UserController

- ```java
  @RestController
  @RequestMapping("/users")
  public class UsersController {
  
  	@Autowired
  	private UsersService usersService;
  	
  	@RequestMapping("/getUsersByUserIdByPass")
  	public Users getUsersByUserIdByPass(@RequestBody Users users) {
  		return usersService.getUsersByUserIdByPass(users);
  	}
  	
  	@RequestMapping("/getUsersById")
  	public Users getUsersById(@RequestBody Users users) {
  		return usersService.getUsersById(users.getUserId());
  	}
  	
  	@RequestMapping("/saveUsers")
  	public int saveUsers(@RequestBody Users users) {
  		return usersService.saveUsers(users);
  	}
  }
  ```

- 4、测试



#### 2.1.2.2.setmeal

##### 1. setmeal/listSetmealByType

- 参数：Setmeal对象

- 返回值：List（泛型：Setmeal）

- 功能：根据套餐类型查询医院列表

- 代码

- 1、新建 PO 类 —— Setmeal 类

- ```java
  public class Setmeal {
  
  	private Integer smId;
  	private String name;
  	private Integer type;
  	private Integer price;
    
    // 套餐表是一方，比如现在有 A、B、C 套餐
    // A 套餐对应 A 套餐的明细
    // B 套餐对应 B 套餐的明细
    // 此时：套餐与套餐明细是一对多的关系
    //一对多
    private List<SetmealDetailed> sdList;
    
    // getter & setter 方法
  }
  ```

- 2、新建 PO 类 —— SetmealDetailed 类

- ```java
  public class SetmealDetailed {
  
  	private Integer sdId;
  	private Integer smId;
  	private Integer ciId;
    
    // 医院的检查套餐中含有明细
    // 明细中会标明对应的检查项目
    // 此时是：多对一
    private CheckItem checkItem;
    
    // getter & setter 方法
  }
  ```

- 3、新建 PO 类 —— CheckItem 类

- ```java
  public class CheckItem {
  
  	private Integer ciId;
  	private String ciName;
  	private String ciContent;
  	private String meaning;
  	private String remarks;
    
    // getter & setter 方法
  }
  ```

- 4、新建 Mapper 类 —— SetmealMapper 类

- ```java
  @Mapper
  public interface SetmealMapper {
  
  	// 查询体检套餐列表（包含检查项信息）
    // 此方法的实现稍复杂，需要用到关联查询，所以不用注解来实现，用 xxxmapper.xml 映射文件实现
  	public List<Setmeal> listSetmealByType(Integer type);
  }
  ```

- 5、新建 Mapper 映射文件 —— SetmealMapper.xml 文件

- 在实现查询的时候，相关的实体有关联关系，需要先处理好，然后才能进行映射。

- 添加在 src/main/resources 目录下

- ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
      "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <mapper namespace="com.neusoft.tijian.mapper.SetmealMapper">
  
  </mapper>    
  ```

- 6、编辑 Setmeal 类，添加「一对多」关系

- ```java
  public class Setmeal {
  
  	private Integer smId;
  	private String name;
  	private Integer type;
  	private Integer price;
  	//一对多
  	private List<SetmealDetailed> sdList;
    
    // getter & setter 方法
  }
  ```

- 7、编辑 SetmealMapper.xml 文件，提供方法

- 其中，sdList 用 `<collection>` 来表示，但它也有关联关系，可以先暂时跳过，回头再看。

- 主要就是执行 listSetmealByType() 方法，得到对应的套餐明细，然后通过 resultMap 进行手动封装数据（因为他们有一对多的关系要处理）

- 当我们想获取 sdList 的值时候，需要调用 SetmealDetailedMapper 中的 listSetmealDetailedBySmId() 方法来执行，得到的结果会自动设置给 sdList。

- ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
      "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <mapper namespace="com.neusoft.tijian.mapper.SetmealMapper">
  
      <resultMap type="Setmeal" id="setmealResultMap">
          <id column="smId" property="smId"/>
          <result column="name" property="name"/>
          <result column="type" property="type"/>
          <result column="price" property="price"/>
          <collection property="sdList" ofType="SetmealDetailed"
              select="com.neusoft.tijian.mapper.SetmealDetailedMapper.listSetmealDetailedBySmId" column="smId"></collection>
      </resultMap>
  
  	<select id="listSetmealByType" parameterType="int" resultMap="setmealResultMap">
  	    select * from setmeal where type=#{type} order by smId
  	</select>
  </mapper>    
  ```

- 8、新建 Mapper 类 —— SetmealDetailedMapper 类

- 通过套餐编号查询，此方法中也有关联查询，所以用映射文件来实现

- ```java
  @Mapper
  public interface SetmealDetailedMapper {
  
  	public List<SetmealDetailed> listSetmealDetailedBySmId(Integer smId);
    
  }
  ```

- 9、新建映射文件 —— SetmealDetailedMapper.xml 文件

- 此时所关联的 CheckItem 是多对一关系中的一，我们也先暂时不写，先去完成代码中的关联

- 添加在 src/main/resources 目录下

- ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
      "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <mapper namespace="com.neusoft.tijian.mapper.SetmealDetailedMapper">
  
  </mapper>    
  ```

- 10、新建 CheckItemMapper 类，用 getCheckItemById() 通过 id 来获取

- ```java
  @Mapper
  public interface CheckItemMapper {
  
  	@Select("select * from checkItem where ciId=#{ciId}")
  	public CheckItem getCheckItemById(Integer ciId);
  }
  ```

- 11、编辑 SetDetailedMapper.xml 文件，完成 `<association>` 的设置，还有具体的实现等。

- ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
      "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <mapper namespace="com.neusoft.tijian.mapper.SetmealDetailedMapper">
  
      <resultMap type="SetmealDetailed" id="setmealDetailedResultMap">
          <id column="sdId" property="sdId"/>
          <result column="smId" property="smId"/>
          <result column="ciId" property="ciId"/>
          <association property="checkItem" javaType="CheckItem"
              select="com.neusoft.tijian.mapper.CheckItemMapper.getCheckItemById" column="ciId"></association>
      </resultMap>
  
  	<select id="listSetmealDetailedBySmId" parameterType="int" resultMap="setmealDetailedResultMap">
  	    select * from setmealDetailed where smId=#{smId} order by sdId
  	</select>
  </mapper>    
  ```

- 12、编辑 SetmealMapper.xml 文件，完成 `<collection>` 的设置，还有具体的实现等。

- ```java
  // 见第 7 步中的代码
  ```

- 13、新建 Service 类 —— SetmealService

- ```java
  public interface SetmealService {
  
  	public List<Setmeal> listSetmealByType(Integer type);
  	public Setmeal getSetmealById(Integer smId);
  }
  ```

- ```java
  @Service
  public class SetmealServiceImpl implements SetmealService{
  
  	@Autowired
  	private SetmealMapper setmealMapper;
  	
  	@Override
  	public List<Setmeal> listSetmealByType(Integer type) {
  		return setmealMapper.listSetmealByType(type);
  	}
  	
  	@Override
  	public Setmeal getSetmealById(Integer smId) {
  		return setmealMapper.getSetmealById(smId);
  	}
  }
  ```

- 14、新建 Controller 类 —— SetmealController 

- ```java
  @RestController
  @RequestMapping("/setmeal")
  public class SetmealController {
  
  	@Autowired
  	private SetmealService setmealService;
  	
  	@RequestMapping("/listSetmealByType")
  	public List<Setmeal> listSetmealByType(@RequestBody Setmeal setmeal) {
  		return setmealService.listSetmealByType(setmeal.getType());
  	}
  	
  	@RequestMapping("/getSetmealById")
  	public Setmeal getSetmealById(@RequestBody Setmeal setmeal) {
  		return setmealService.getSetmealById(setmeal.getSmId());
  	}
  }
  ```

  

##### 2. setmeal/getSetmealById

- 参数：Setmeal对象
- 返回值：Setmeal对象
- 功能：根据主键查询套餐信息

#### 2.1.2.3.overallResult

##### 1. overallResult/listOverallResultByOrderId

- 参数：OverallResult对象

- 返回值：List（泛型：OverallResult）

- 功能：根据体检预约编号查询总检结论

- 代码：

- 1、新建 PO 类 —— OverallResultMapper

- ```java
  public class OverallResult {
  
  	private Integer orId;
  	private String title;
  	private String content;
  	private Integer orderId;
    
    // getter & setter 方法
  }
  ```

- 2、新建 Mapper 类 —— OverallResultMapper

- ```java
  @Mapper
  public interface OverallResultMapper {
  
  	@Select("select * from overallResult where orderId=#{orderId} order by orId")
  	public List<OverallResult> listOverallResultByOrderId(Integer orderId);
  }
  ```

- 3、新建 Service 类 —— OverallResultService

- ```java
  public interface OverallResultService {
  
  	public List<OverallResult> listOverallResultByOrderId(Integer orderId);
  }
  ```

  ```java
  @Service
  public class OverallResultServiceImpl implements OverallResultService{
  
  	@Autowired
  	private OverallResultMapper overallResultMapper;
  	
  	@Override
  	public List<OverallResult> listOverallResultByOrderId(Integer orderId) {
  		return overallResultMapper.listOverallResultByOrderId(orderId);
  	}
  }
  ```

- 4、新建 Controller 类 —— OverallResultController

- ```java
  @RestController
  @RequestMapping("/overallResult")
  public class OverallResultController {
  
  	@Autowired
  	private OverallResultService overallResultService;
  
  	@RequestMapping("/listOverallResultByOrderId")
  	public List<OverallResult> listOverallResultByOrderId(@RequestBody OverallResult overallResult) {
  		return overallResultService.listOverallResultByOrderId(overallResult.getOrderId());
  	}
  }
  ```

  

#### 2.1.2.4.orders

##### 1. orders/getOrdersByUserId

- 参数：Orders对象

- 返回值：int

- 功能：根据电话号码查询是否预约过（凡是有未归档的预约记录的用户，不能再次预约）

- 代码

- 1、添加 PO 类 —— Orders 类

- ```java
  public class Orders {
  
  	private Integer orderId;
  	private String orderDate;
  	private String userId;
  	private Integer hpId;
  	private Integer smId;
  	private Integer state;
  
    // getter & setter 方法
  }
  ```

  

- 2、添加 Mapper 类 —— OrdersMapper 类

- ```java
  @Mapper
  public interface OrdersMapper {
  
  	//查询是否预约过（凡是有未归档的预约记录的用户，不能再次预约）
  	@Select("select count(*) from orders where state=1 and userId=#{userId}")
  	public int getOrdersByUserId(String userId);
  }
  ```

  

- 3、添加 Service 类 —— OrdersService & OrdersServiceImpl 类

- ```java
  public interface OrdersService {
  
  	public int getOrdersByUserId(String userId);
  }
  ```

- ```java
  @Service
  public class OrdersServiceImpl implements OrdersService{
  
  	@Autowired
  	private OrdersMapper ordersMapper;
  	
  	@Override
  	public int getOrdersByUserId(String userId) {
  		return ordersMapper.getOrdersByUserId(userId);
  	}
  }
  ```

  

- 4、添加 Controller 类 —— OrdersController

- ```java
  @RestController
  @RequestMapping("/orders")
  public class OrdersController {
  	
  	@Autowired
  	private OrdersService ordersService;
  
  	@RequestMapping("/getOrdersByUserId")
  	public int getOrdersByUserId(@RequestBody Orders orders) {
  		return ordersService.getOrdersByUserId(orders.getUserId());
  	}
  }
  ```

  

- 5、测试

- ```http
  POST http://localhost:8080/tijian/orders/getOrdersByUserId
  Content-Type: application/json
  
  {
    "userId": "12345671111"
  }
  
  ###
  ```

  



##### 2. orders/saveOrders

- 参数：Orders对象

- 返回值：int

- 功能：添加体检预约信息

- 代码：

- 1、编辑 Mapper 类 —— OrdersMapper

- 提供 saveOrders()

- ```java
  @Mapper
  public interface OrdersMapper {
  
  	//查询是否预约过（凡是有未归档的预约记录的用户，不能再次预约）
  	@Select("select count(*) from orders where state=1 and userId=#{userId}")
  	public int getOrdersByUserId(String userId);
  	
  	//根据parameList参数，查询30天预约日期中，每一天的已预约人数
  	public List<CalendarResponseDto> listOrdersAppointmentNumber(List<OrdersMapperDto> list);
  	
  	//创建体检预约订单
  	@Insert("insert into orders values(null,#{orderDate},#{userId},#{hpId},#{smId},1)")
  	public int saveOrders(Orders orders);
  }
  ```

- 2、编辑 Service 类 —— OrdersService

- 提供 saveOrders()

- ```java
  public interface OrdersService {
  
  	public int getOrdersByUserId(String userId);
  	public int saveOrders(Orders orders);
  }
  ```

- ```java
  @Service
  public class OrdersServiceImpl implements OrdersService{
  
  	@Autowired
  	private OrdersMapper ordersMapper;
  	
  	@Override
  	public int getOrdersByUserId(String userId) {
  		return ordersMapper.getOrdersByUserId(userId);
  	}
  	
  	@Override
  	public int saveOrders(Orders orders) {
  		return ordersMapper.saveOrders(orders);
  	}
  }
  ```

- 3、编辑 Controller 类 —— OrdersController

- 提供 saveOrders()

- ```java
  @RestController
  @RequestMapping("/orders")
  public class OrdersController {
  	
  	@Autowired
  	private OrdersService ordersService;
  
  	@RequestMapping("/getOrdersByUserId")
  	public int getOrdersByUserId(@RequestBody Orders orders) {
  		return ordersService.getOrdersByUserId(orders.getUserId());
  	}
  	
  	@RequestMapping("/saveOrders")
  	public int saveOrders(@RequestBody Orders orders) {
  		return ordersService.saveOrders(orders);
  	}
  }
  ```

  

##### 3. orders/listOrdersByUserId

- 参数：Orders对象

- 返回值：List（泛型：Orders）

- 功能：根据用户编号和订单状态查询体检预约列表

- 代码：

- 1、编辑 Mapper 类 —— OrdersMapper

- ```java
  @Mapper
  public interface OrdersMapper {
  
  	//查询是否预约过（凡是有未归档的预约记录的用户，不能再次预约）
  	@Select("select count(*) from orders where state=1 and userId=#{userId}")
  	public int getOrdersByUserId(String userId);
  	
  	//根据parameList参数，查询30天预约日期中，每一天的已预约人数
  	public List<CalendarResponseDto> listOrdersAppointmentNumber(List<OrdersMapperDto> list);
  	
  	//创建体检预约订单
  	@Insert("insert into orders values(null,#{orderDate},#{userId},#{hpId},#{smId},1)")
  	public int saveOrders(Orders orders);
  	
  	//根据用户编号查询预约体检订单列表
  	public List<Orders> listOrdersByUserId(Orders orders);
  	
  }
  ```

- 2、编辑 PO 类 —— Orders

- 添加多对一关系

- ```java
  public class Orders {
  
  	private Integer orderId;
  	private String orderDate;
  	private String userId;
  	private Integer hpId;
  	private Integer smId;
  	private Integer state;
  	//多对一
  	private Setmeal setmeal;
  	private Hospital hospital;
  }
  ```

- 3、编辑 OrdersMapper.xml 文件

- ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
      "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <mapper namespace="com.neusoft.tijian.mapper.OrdersMapper">
  
  	<select id="listOrdersAppointmentNumber" parameterType="OrdersMapperDto" resultType="CalendarResponseDto">
  	    <foreach collection="list" item="dto" separator="union">
  	        select #{dto.date} ymd,count(*) existing from orders where orderDate=#{dto.date} and hpId=#{dto.hpId}
  	    </foreach>
  	</select>
  	
  	<resultMap type="Orders" id="ordersResultMap">
          <id column="orderId" property="orderId"/>
          <result column="orderDate" property="orderDate"/>
          <result column="userId" property="userId"/>
          <result column="hpId" property="hpId"/>
          <result column="smId" property="smId"/>
          <result column="state" property="state"/>
          <association property="setmeal" javaType="Setmeal"
              select="com.neusoft.tijian.mapper.SetmealMapper.getSetmealById" column="smId"></association>
          <association property="hospital" javaType="Hospital"
              select="com.neusoft.tijian.mapper.HospitalMapper.getHospitalById" column="hpId"></association>    
      </resultMap>
  	
  	<select id="listOrdersByUserId" parameterType="Orders" resultMap="ordersResultMap">
  	    select * from orders where userId=#{userId} and state=#{state} order by orderId desc
  	</select>
  </mapper>    
  ```

- 4、编辑 Service 类 —— OrdersService

- ```java
  public interface OrdersService {
  
  	public int getOrdersByUserId(String userId);
  	public int saveOrders(Orders orders);
  	public List<Orders> listOrdersByUserId(Orders orders);
  }
  ```

  ```java
  @Service
  public class OrdersServiceImpl implements OrdersService{
  
  	@Autowired
  	private OrdersMapper ordersMapper;
  	
  	@Override
  	public int getOrdersByUserId(String userId) {
  		return ordersMapper.getOrdersByUserId(userId);
  	}
  	
  	@Override
  	public int saveOrders(Orders orders) {
  		return ordersMapper.saveOrders(orders);
  	}
  	
  	@Override
  	public List<Orders> listOrdersByUserId(Orders orders) {
  		return ordersMapper.listOrdersByUserId(orders);
  	}
  }
  ```

- 5、编辑 Controller 类 —— OrdersController

- ```java
  @RestController
  @RequestMapping("/orders")
  public class OrdersController {
  	
  	@Autowired
  	private OrdersService ordersService;
  
  	@RequestMapping("/getOrdersByUserId")
  	public int getOrdersByUserId(@RequestBody Orders orders) {
  		return ordersService.getOrdersByUserId(orders.getUserId());
  	}
  	
  	@RequestMapping("/saveOrders")
  	public int saveOrders(@RequestBody Orders orders) {
  		return ordersService.saveOrders(orders);
  	}
  	
  	@RequestMapping("/listOrdersByUserId")
  	public List<Orders> listOrdersByUserId(@RequestBody Orders orders) {
  		return ordersService.listOrdersByUserId(orders);
  	}
  }
  ```

  

##### 4. orders/removeOrders

- 参数：Orders对象

- 返回值：int

- 功能：根据体检预约编号删除体检预约信息

- 代码：

- 1、编辑 Mapper 类 —— OrdersMapper

- ```java
  @Mapper
  public interface OrdersMapper {
  
  	//查询是否预约过（凡是有未归档的预约记录的用户，不能再次预约）
  	@Select("select count(*) from orders where state=1 and userId=#{userId}")
  	public int getOrdersByUserId(String userId);
  	
  	//根据parameList参数，查询30天预约日期中，每一天的已预约人数
  	public List<CalendarResponseDto> listOrdersAppointmentNumber(List<OrdersMapperDto> list);
  	
  	//创建体检预约订单
  	@Insert("insert into orders values(null,#{orderDate},#{userId},#{hpId},#{smId},1)")
  	public int saveOrders(Orders orders);
  	
  	//根据用户编号查询预约体检订单列表
  	public List<Orders> listOrdersByUserId(Orders orders);
  	
  	//取消预约体检订单
  	@Delete("delete from orders where orderId=#{orderId}")
  	public int removeOrders(Integer orderId);
  }
  ```

- 2、编辑 Service 类 —— OrdersService

- ```java
  public interface OrdersService {
  
  	public int getOrdersByUserId(String userId);
  	public int saveOrders(Orders orders);
  	public List<Orders> listOrdersByUserId(Orders orders);
  	public int removeOrders(Integer orderId);
  }
  ```

  ```java
  @Service
  public class OrdersServiceImpl implements OrdersService{
  
  	@Autowired
  	private OrdersMapper ordersMapper;
  	
  	@Override
  	public int getOrdersByUserId(String userId) {
  		return ordersMapper.getOrdersByUserId(userId);
  	}
  	
  	@Override
  	public int saveOrders(Orders orders) {
  		return ordersMapper.saveOrders(orders);
  	}
  	
  	@Override
  	public List<Orders> listOrdersByUserId(Orders orders) {
  		return ordersMapper.listOrdersByUserId(orders);
  	}
  	
  	@Override
  	public int removeOrders(Integer orderId) {
  		return ordersMapper.removeOrders(orderId);
  	}
  }
  ```

- 3、编辑 Controller 类 —— OrdersController

- ```java
  @RestController
  @RequestMapping("/orders")
  public class OrdersController {
  	
  	@Autowired
  	private OrdersService ordersService;
  
  	@RequestMapping("/getOrdersByUserId")
  	public int getOrdersByUserId(@RequestBody Orders orders) {
  		return ordersService.getOrdersByUserId(orders.getUserId());
  	}
  	
  	@RequestMapping("/saveOrders")
  	public int saveOrders(@RequestBody Orders orders) {
  		return ordersService.saveOrders(orders);
  	}
  	
  	@RequestMapping("/listOrdersByUserId")
  	public List<Orders> listOrdersByUserId(@RequestBody Orders orders) {
  		return ordersService.listOrdersByUserId(orders);
  	}
  	
  	@RequestMapping("/removeOrders")
  	public int removeOrders(@RequestBody Orders orders) {
  		return ordersService.removeOrders(orders.getOrderId());
  	}
  }
  ```

  

##### 5. orders/getOrdersById

- 参数：Orders对象

- 返回值：Orders对象

- 功能：根据体检预约编号查询体检预约信息

- 代码：

- 1、编辑 Mapper 类 —— OrdersMapper

- 提供 getOrdersById()

- ```java
  @Mapper
  public interface OrdersMapper {
  
  	//查询是否预约过（凡是有未归档的预约记录的用户，不能再次预约）
  	@Select("select count(*) from orders where state=1 and userId=#{userId}")
  	public int getOrdersByUserId(String userId);
  	
  	//根据parameList参数，查询30天预约日期中，每一天的已预约人数
  	public List<CalendarResponseDto> listOrdersAppointmentNumber(List<OrdersMapperDto> list);
  	
  	//创建体检预约订单
  	@Insert("insert into orders values(null,#{orderDate},#{userId},#{hpId},#{smId},1)")
  	public int saveOrders(Orders orders);
  	
  	//根据用户编号查询预约体检订单列表
  	public List<Orders> listOrdersByUserId(Orders orders);
  	
  	//取消预约体检订单
  	@Delete("delete from orders where orderId=#{orderId}")
  	public int removeOrders(Integer orderId);
  	
  	@Select("select * from orders where orderId=#{orderId}")
  	public Orders getOrdersById(Integer orderId);
  	
  }
  ```

- 2、编辑 Service 类 —— OrdersService

- ```java
  public interface OrdersService {
  
  	public int getOrdersByUserId(String userId);
  	public int saveOrders(Orders orders);
  	public List<Orders> listOrdersByUserId(Orders orders);
  	public int removeOrders(Integer orderId);
  	public Orders getOrdersById(Integer orderId);
  }
  ```

  ```java
  @Service
  public class OrdersServiceImpl implements OrdersService{
  
  	@Autowired
  	private OrdersMapper ordersMapper;
  	
  	@Override
  	public int getOrdersByUserId(String userId) {
  		return ordersMapper.getOrdersByUserId(userId);
  	}
  	
  	@Override
  	public int saveOrders(Orders orders) {
  		return ordersMapper.saveOrders(orders);
  	}
  	
  	@Override
  	public List<Orders> listOrdersByUserId(Orders orders) {
  		return ordersMapper.listOrdersByUserId(orders);
  	}
  	
  	@Override
  	public int removeOrders(Integer orderId) {
  		return ordersMapper.removeOrders(orderId);
  	}
  	
  	@Override
  	public Orders getOrdersById(Integer orderId) {
  		return ordersMapper.getOrdersById(orderId);
  	}
  }
  
  ```

- 3、编辑 Controller 类 —— OrdersController

- ```java
  @RestController
  @RequestMapping("/orders")
  public class OrdersController {
  	
  	@Autowired
  	private OrdersService ordersService;
  
  	@RequestMapping("/getOrdersByUserId")
  	public int getOrdersByUserId(@RequestBody Orders orders) {
  		return ordersService.getOrdersByUserId(orders.getUserId());
  	}
  	
  	@RequestMapping("/saveOrders")
  	public int saveOrders(@RequestBody Orders orders) {
  		return ordersService.saveOrders(orders);
  	}
  	
  	@RequestMapping("/listOrdersByUserId")
  	public List<Orders> listOrdersByUserId(@RequestBody Orders orders) {
  		return ordersService.listOrdersByUserId(orders);
  	}
  	
  	@RequestMapping("/removeOrders")
  	public int removeOrders(@RequestBody Orders orders) {
  		return ordersService.removeOrders(orders.getOrderId());
  	}
  	
  	@RequestMapping("/getOrdersById")
  	public Orders getOrdersById(@RequestBody Orders orders) {
  		return ordersService.getOrdersById(orders.getOrderId());
  	}
  }
  
  ```

  

#### 2.1.2.5.hospital

##### 1. hospital/listHospital

- 参数：Hospital对象

- 返回值：List（泛型：Hospital）

- 功能：根据医院状态查询医院列表

- 代码

- 1、添加 PO 类 —— Hospital 类

- ```java
  public class Hospital {
  
  	private Integer hpId;
  	private String name;
  	private String picture;
  	private String telephone;
  	private String address;
  	private String businessHours;
  	private String deadline;
  	private String rule;
  	private Integer state;
  
    // getter & setter 方法
  }
  ```

- 2、添加 Mapper 类 —— HospitalMapper 类 

- ```java
  @Mapper
  public interface HospitalMapper {
  
  	//查询正常营业的医院
  	@Select("select * from hospital where state=#{state} order by hpId")
  	public List<Hospital> listHospital(Integer state);
  }
  ```

- 3、添加 Service 类 —— HospitalService 类

- ```java
  public interface HospitalService {
  
  	public List<Hospital> listHospital(Integer state);
  
  }
  ```

- ```java
  @Service
  public class HospitalServiceImpl implements HospitalService{
  
  	@Autowired
  	private HospitalMapper hospitalMapper;
  	
  	@Override
  	public List<Hospital> listHospital(Integer state) {
  		return hospitalMapper.listHospital(state);
  	}
  }
  ```

- 4、添加 Controller 类 —— HospitalController 类

- ```java
  @RestController
  @RequestMapping("/hospital")
  public class HospitalController {
  
  	@Autowired
  	private HospitalService hospitalService;
  	
  	@RequestMapping("/listHospital")
  	public List<Hospital> listHospital(@RequestBody Hospital hospital) {
  		return hospitalService.listHospital(hospital.getState());
  	}
  }
  ```

##### 2. hospital/getHospitalById

- 参数：Hospital对象

- 返回值：Hospital对象

- 功能：根据医院编号查询医院信息

- 代码：

- 1、编辑 Mapper 类 —— HospitalMapper

- 选择预约日期时已经实现过一次了，这里为了代码完整性也贴出来

  ```java
  @Mapper
  public interface HospitalMapper {
  
      //查询正常营业的医院
      @Select("select * from hospital where state=#{state} order by hpId")
      public List<Hospital> listHospital(Integer state);
  
      // 根据主键查询医院信息
      @Select("select * from hospital where hpId=#{hpId}")
      public Hospital getHospitalById(Integer hpId);
  }
  ```

- 2、编辑 Service 类 —— HospitalService

- 提供 getHospitalById()

- ```java
  public interface HospitalService {
  
  	public List<Hospital> listHospital(Integer state);
  	public Hospital getHospitalById(Integer hpId);
  }
  ```

  ```java
  @Service
  public class HospitalServiceImpl implements HospitalService{
  
  	@Autowired
  	private HospitalMapper hospitalMapper;
  	
  	@Override
  	public List<Hospital> listHospital(Integer state) {
  		return hospitalMapper.listHospital(state);
  	}
  	
  	@Override
  	public Hospital getHospitalById(Integer hpId) {
  		return hospitalMapper.getHospitalById(hpId);
  	}
  }
  ```

- 3、编辑 Controller 类 —— HospitalController

- 提供 getHospitalById()

- ```java
  @RestController
  @RequestMapping("/hospital")
  public class HospitalController {
  
  	@Autowired
  	private HospitalService hospitalService;
  	
  	@RequestMapping("/listHospital")
  	public List<Hospital> listHospital(@RequestBody Hospital hospital) {
  		return hospitalService.listHospital(hospital.getState());
  	}
  	
  	@RequestMapping("/getHospitalById")
  	public Hospital getHospitalById(@RequestBody Hospital hospital) {
  		return hospitalService.getHospitalById(hospital.getHpId());
  	}
  }
  ```

- 4、编辑 Mapper 类 —— SetmealMapper

- 提供 getSetmealById()

- ```java
  @Mapper
  public interface SetmealMapper {
  
  	// 查询体检套餐列表（包含检查项信息）
  	public List<Setmeal> listSetmealByType(Integer type);
    
  	// 根据主键查询体检套餐
  	@Select("select * from setmeal where smId=#{smId}")
  	public Setmeal getSetmealById(Integer smId);
  	
  }
  ```

- 5、编辑 Service 类 —— SetmealService

- ```java
  public interface SetmealService {
  
  	public List<Setmeal> listSetmealByType(Integer type);
  	public Setmeal getSetmealById(Integer smId);
  }
  ```

  ```java
  @Service
  public class SetmealServiceImpl implements SetmealService{
  
  	@Autowired
  	private SetmealMapper setmealMapper;
  	
  	@Override
  	public List<Setmeal> listSetmealByType(Integer type) {
  		return setmealMapper.listSetmealByType(type);
  	}
  	
  	@Override
  	public Setmeal getSetmealById(Integer smId) {
  		return setmealMapper.getSetmealById(smId);
  	}
  }
  ```

- 6、编辑 Controller 类 —— SetmealController 

- ```java
  @RestController
  @RequestMapping("/setmeal")
  public class SetmealController {
  
  	@Autowired
  	private SetmealService setmealService;
  	
  	@RequestMapping("/listSetmealByType")
  	public List<Setmeal> listSetmealByType(@RequestBody Setmeal setmeal) {
  		return setmealService.listSetmealByType(setmeal.getType());
  	}
  	
  	@RequestMapping("/getSetmealById")
  	public Setmeal getSetmealById(@RequestBody Setmeal setmeal) {
  		return setmealService.getSetmealById(setmeal.getSmId());
  	}
  }
  ```

  

#### 2.1.2.6.ciReport

##### 1. ciReport/listCiReport

- 参数：CiReport对象

- 返回值：List（泛型：CiReport）

- 功能：根据体检预约编号查询检查项列表

- 代码：

- 1、新建 PO 类 —— CiReport

- ```java
  public class CiReport {
  
  	private Integer cirId;
  	private Integer ciId;
  	private String ciName;
  	private Integer orderId;
  	//一对多
  	private List<CiDetailedReport> cidrList;
    
    // getter & setter 
  }
  ```

- 2、新建 PO 类 —— CiDetailedReport

- ```java
  public class CiDetailedReport {
  
  	private Integer cidrId;              //检查项明细报告主键
  	private String name;                 //检查项明细名称
  	private String unit;                 //检查项明细单位
  	private double minrange;             //检查项细明正常值范围中的最小值
  	private double maxrange;             //检查项细明正常值范围中的最大值
  	private String normalValue;          //检查项细明正常值（非数字型）不是计数类的，比如脂肪肝？
  	private String normalValueString;    //检查项验证范围说明文字
  	private Integer type;                //检查项明细类型（1：数值围范验证型；2：数值相等验证型；3：无需验证型；4：描述型；5：其它）
  	private String value;                //检查项目明细值
  	private Integer isError;             //此项是否异常（0：无异常；1：异常）
  	private Integer ciId;                //所属检查项报告编号
  	private Integer orderId;             //所属预约编号
   
    // getter & setter 
  }
  ```

- 3、新建 Mapper 类 —— CiReportMapper

- ```java
  @Mapper
  public interface CiReportMapper {
  
  	@Select("select * from ciReport where orderId=#{orderId} order by cirId")
  	public List<CiReport> listCiReport(Integer orderId);
  }
  ```

- 4、新建 Mapper 类—— CiDetailedReportMapper

- ```java
  @Mapper
  public interface CiDetailedReportMapper {
  
  	@Select("select * from CiDetailedReport where orderId=#{orderId} and ciId=#{ciId}")
  	public List<CiDetailedReport> listCiDetailedReportByOrderIdByCiId(CiDetailedReport ciDetailedReport);
  }
  ```

- 5、新建 Service 类 —— CiReportService

- ```java
  public interface CiReportService {
  
  	public List<CiReport> listCiReport(Integer orderId);
  }
  ```

  ```java
  @Service
  public class CiReportServiceImpl implements CiReportService{
  
  	@Autowired
  	private CiReportMapper ciReportMapper;
  	@Autowired
  	private CiDetailedReportMapper ciDetailedReportMapper;
  	
  	@Override
  	public List<CiReport> listCiReport(Integer orderId) {
  		//先查询CiReport。获取报告检查项
  		List<CiReport> crList = ciReportMapper.listCiReport(orderId);
  		
  		//遍历所有检查项，在依次查询检查项明细
  		for(CiReport cr : crList) {
  			CiDetailedReport param = new CiDetailedReport();
  			param.setOrderId(orderId);
  			param.setCiId(cr.getCiId());
  			List<CiDetailedReport> list = ciDetailedReportMapper.listCiDetailedReportByOrderIdByCiId(param);
  			cr.setCidrList(list);
  		}
  		return crList;
  	}
  }
  
  ```

- 6、新建 Controller 类 —— CiReportController

- ```java
  @RestController
  @RequestMapping("/ciReport")
  public class CiReportController {
  
  	@Autowired
  	private CiReportService ciReportService;
  	
  	@RequestMapping("/listCiReport")
  	public List<CiReport> listCiReport(@RequestBody CiReport ciReport) {
  		return ciReportService.listCiReport(ciReport.getOrderId());
  	}
  }
  ```

  

#### 2.1.2.7.calendar

##### 1. calendar/listAppointmentCalendar

- 参数：CalendarRequestDto对象

- 返回值：List（泛型：CalendarResponseDto）

- 功能：根据医院编号、年、月，生成预约日历并返回给前端

- ==先思考一个问题==：

- 先思考一个问题，页面如果需要呈现数据的话，那一定是服务端封装好，并响应返回的。

- 如果在数据库中没有对应的表，在 PO 中没有对应的类，哪些靠动态计算出来的数据结果，如何封装？

- 如果想封装动态计算出来的数据，那么就需要用到 DTO —— 数据传输对象，能满足数据的封装、传输

- 请求的时候，有数据要封装和传输，响应的时候，也有数据要封装和传输，一般就分 request 和 response 两种

- ==代码实现==：

- 1、新建 DTO 类 —— CalendarRequestDto 和 CalendarResponseDto

- DTO 是数据传输对象，一般有 request 和 response 两种。

- ```java
  // 请求预约日历的提交参数
  public class CalendarRequestDto {
  
  	private Integer hpId;
  	private Integer year;
  	private Integer month;
    
    // getter & setter 方法
  }
  ```

- ```java
  //请求预约日历的相应数据
  public class CalendarResponseDto {
  
  	private String ymd;         //预约日期
  	private Integer total;      //最大预约人数
  	private Integer existing;   //现有预约人数
  	private Integer remainder;  //剩余预约人数
  	
    // 构造器（无参、有参）
    public CalendarResponseDto() {}
  	public CalendarResponseDto(String ymd) {
  		this.ymd = ymd;
  	}
    // getter & setter 方法
  }
  ```
  
- 2、新建 Service 类 —— CalendarService

- ```java
  public interface CalendarService {
  
  	//生成预约日历
  	public List<CalendarResponseDto> listAppointmentCalendar(CalendarRequestDto calendarRequestDto);
   
  }
  ```
  
- 首先，要注入 hospitalMapper 获取预约的规则，每家医院可能预约的条件不一样；

- 然后，注入 ordersMapper 查询已经有多少人预约了

- 接着，如果想要生成预约日历，还要再提供 getCalendarList30() 获取30天预约日期列表，getCurrentCalendarList() 获取当前年和当前月的日历，两个私有方法。

- 其实很简单，思路就是先获取当前年、月的信息，然后在里面再看哪些是能够取预约的。

- 接着，先去实现 getCalendarList30() 方法，先确定怎么取可以预约的 30 天。

- 然后，就拿着这 30 天取 Orders 中找 orderDate 和 hpId 两个字段去匹配相应的范围查询，有多少条记录，就意味着有多少个已预约的人数。

- 在这里，我们只是专门挑了 orderDate 和 hpId 等数据进行传递，所以可以专门新增一个 OrdersMapperDto 用于传递参数。 ==

- ==部分代码如下==：

- ```java
  @Service
  public class CalendarServiceImpl implements CalendarService{
  
  	@Autowired
  	private HospitalMapper hospitalMapper;
  	@Autowired
  	private OrdersMapper ordersMapper;
  	
  	// 生成预约日历
  	@Override
  	public List<CalendarResponseDto> listAppointmentCalendar(CalendarRequestDto dto) {
  		// 获取预约日期列表
  		List<CalendarResponseDto> calendarList30 = getCalendarList30(dto.getHpId());
  		// 获取当前日历（当前日历中只有 ymd 属性，total、existing、remainder 需要从 calendarList30 获取）
  		List<CalendarResponseDto> currentCalendarList = getCurrentCalendarList(dto.getYear(),dto.getMonth());
  		
  		// 给当前日历填充 total、existing、remainder 属性
  		for(CalendarResponseDto current : currentCalendarList) {
  			for(CalendarResponseDto calendar : calendarList30) {
  				if(current.getYmd()!=null) {
  					if(current.getYmd().equals(calendar.getYmd())) {
  						current.setTotal(calendar.getTotal());
  						current.setExisting(calendar.getExisting());
  						current.setRemainder(calendar.getRemainder());
  					}
  				}
  			}
  		}
  		return currentCalendarList;
  	}
  	
  	// 第一步：获取 30 天预约日期列表
  	private List<CalendarResponseDto> getCalendarList30(Integer hpId){
      // 设置日期格式
  		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      // 获取日历对象
  		Calendar calendar = Calendar.getInstance();
  		
  		// 做一个临时集合，作为去 Orders 表中查询的参数
  		List<OrdersMapperDto> parameList = new ArrayList<>();
      // 因为只能预约今天开始起之后的 30 天，所以通过循环设置 30 天
  		for(int i=0;i<30;i++) {
        // 使用 OrdersMapperDto 作为订单中数据传递的对象
  			OrdersMapperDto dto = new OrdersMapperDto();
        // 每循环一次，都要往后多计算 1 天的时间
  			calendar.add(Calendar.DATE, 1); 
        // 然后开始设置
  			dto.setDate(sdf.format(calendar.getTime()));
  			dto.setHpId(hpId);
        // 将设置好的内容，添加到临时集合中
  			parameList.add(dto);
  		}
  		
  		// 根据 parameList 参数，查询 30 天预约日期中，每一天的已预约人数（先在 OrdersMapper 中实现，拿到数据先）
  		// 查询结果 CalendarResponseDto 中只有两个属性：ymd、existing，还有两个属性需要填充：total、remainder
  		List<CalendarResponseDto> calendarList30 = ordersMapper.listOrdersAppointmentNumber(parameList);
  	
  		// 根据医院编号，获取预约规则，就能获取每天最多预约人数
      // 需要在 hospitalMapper 中提供 getHospitalById() 来实现
      // 然后将预约规则，拆开放在数组中
  		String[] strArr = hospitalMapper.getHospitalById(hpId).getRule().split(",");
  		
  		// 继续填充 calendarList30 返回值中的 total、remainder 属性
  		for(CalendarResponseDto cd : calendarList30) {
  			// 将预约日期转换为 Calendar 对象
  			try {
  				calendar.setTime(sdf.parse(cd.getYmd()));
  			} catch (ParseException e) {
  				e.printStackTrace();
  			}
  			// 通过 Calendar 对象的 get 方法获取某天为星期几，就可以通过预约规则数组获取某天的总预约人数
        // 日历是从「周日」开始
  			int total = Integer.parseInt(strArr[calendar.get(Calendar.DAY_OF_WEEK)-1]);
  			// 填充 total 属性
  			cd.setTotal(total);
  			// 填充 remainder 属性，也就是剩余预约人数
  			cd.setRemainder(total-cd.getExisting());
  		}
  		// 返回结果
  		return calendarList30;
  	}
  	
  	// 第二步：获取当前年和当前月的日历
  }
  ```
  
- 3、新增 DTO 类 —— OrdersMapperDto

- ```java
  public class OrdersMapperDto {
  
  	private String date;
  	private Integer hpId;
  }
  ```

- 4、编辑 Mapper 类 —— OrdersMapper

- 添加 listOrdersAppointmentNumber() 方法

- ```java
  @Mapper
  public interface OrdersMapper {
  
  	//查询是否预约过（凡是有未归档的预约记录的用户，不能再次预约）
  	@Select("select count(*) from orders where state=1 and userId=#{userId}")
  	public int getOrdersByUserId(String userId);
  	
  	// 根据 parameList 参数，查询 30 天预约日期中，每一天的已预约人数
    // 有关联关系，则使用 OrdersMapper.xml 文件来实现映射
  	public List<CalendarResponseDto> listOrdersAppointmentNumber(List<OrdersMapperDto> list);
  }
  ```

- 5、添加映射文件 —— OrdersMapper.xml 

- ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
      "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <mapper namespace="com.neusoft.tijian.mapper.OrdersMapper">
  
  	<select id="listOrdersAppointmentNumber" parameterType="OrdersMapperDto" resultType="CalendarResponseDto">
      	<!-- 主要是查两个值：
  				1、查到的行数，以别名的方式封装给 existing；
   				2、遍历出来的日期，以别名的方式封装给 ymd -->
  	    <foreach collection="list" item="dto" separator="union">
  	        select #{dto.date} ymd,count(*) existing from orders where orderDate=#{dto.date} and hpId=#{dto.hpId}
  	    </foreach>
  	</select>
  </mapper>    
  ```
  
- 6、编辑 Mapper 类 —— HospitalMapper

- ```java
  @Mapper
  public interface HospitalMapper {
  
  	// 查询正常营业的医院
  	@Select("select * from hospital where state=#{state} order by hpId")
  	public List<Hospital> listHospital(Integer state);
    
  	// 根据主键查询医院信息
  	@Select("select * from hospital where hpId=#{hpId}")
  	public Hospital getHospitalById(Integer hpId);
  }
  ```

- 7、编辑 CalendarServiceImpl 类，实现「获取当前年和当前月的日历」私有方法

- ==完整代码如下==：

- 其中，在 getCurrentCalendarList() 方法中的万年历实现的代码比较常见和固定，可以直接套用即可。

- 然后，再去实现 listAppointmentCalendar() 方法

- ```java
  @Service
  public class CalendarServiceImpl implements CalendarService{
  
  	@Autowired
  	private HospitalMapper hospitalMapper;
  	@Autowired
  	private OrdersMapper ordersMapper;
  	
  	// 生成预约日历
  	@Override
  	public List<CalendarResponseDto> listAppointmentCalendar(CalendarRequestDto dto) {
  		// 获取预约日期列表
  		List<CalendarResponseDto> calendarList30 = getCalendarList30(dto.getHpId());
  		// 获取当前日历（当前日历中只有ymd属性，total、existing、remainder需要从calendarList30获取）
  		List<CalendarResponseDto> currentCalendarList = getCurrentCalendarList(dto.getYear(),dto.getMonth());
  		
  		// 给当前日历填充total、existing、remainder属性
      // 使用一个嵌套循环来读取对应的日历信息
      // 先是把当前的日历读出来
      // 然后把可以预约的 30 天也拿出来
      // 然后填充total、existing、remainder属性
  		for(CalendarResponseDto current : currentCalendarList) {
  			for(CalendarResponseDto calendar : calendarList30) {
  				if(current.getYmd()!=null) {
  					if(current.getYmd().equals(calendar.getYmd())) {
  						current.setTotal(calendar.getTotal());
  						current.setExisting(calendar.getExisting());
  						current.setRemainder(calendar.getRemainder());
  					}
  				}
  			}
  		}
  		return currentCalendarList;
  	}
  	
  	// 第一步：获取 30 天预约日期列表
  	private List<CalendarResponseDto> getCalendarList30(Integer hpId){
      // 设置日期格式
  		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      // 获取日历对象
  		Calendar calendar = Calendar.getInstance();
  		
  		// 做一个临时集合，作为去 Orders 表中查询的参数
  		List<OrdersMapperDto> parameList = new ArrayList<>();
      // 因为只能预约今天开始起之后的 30 天，所以通过循环设置 30 天
  		for(int i=0;i<30;i++) {
        // 使用 OrdersMapperDto 作为订单中数据传递的对象
  			OrdersMapperDto dto = new OrdersMapperDto();
        // 每循环一次，都要往后多计算 1 天的时间
  			calendar.add(Calendar.DATE, 1); 
        // 然后开始设置
  			dto.setDate(sdf.format(calendar.getTime()));
  			dto.setHpId(hpId);
        // 将设置好的内容，添加到临时集合中
  			parameList.add(dto);
  		}
  		
  		// 根据 parameList 参数，查询 30 天预约日期中，每一天的已预约人数（先在 OrdersMapper 中实现，拿到数据先）
  		// 查询结果 CalendarResponseDto 中只有两个属性：ymd、existing，还有两个属性需要填充：total、remainder
  		List<CalendarResponseDto> calendarList30 = ordersMapper.listOrdersAppointmentNumber(parameList);
  	
  		// 根据医院编号，获取预约规则，就能获取每天最多预约人数
      // 需要在 hospitalMapper 中提供 getHospitalById() 来实现
      // 然后将预约规则，拆开放在数组中
  		String[] strArr = hospitalMapper.getHospitalById(hpId).getRule().split(",");
  		
  		// 继续填充 calendarList30 返回值中的 total、remainder 属性
  		for(CalendarResponseDto cd : calendarList30) {
  			// 将预约日期转换为 Calendar 对象
  			try {
  				calendar.setTime(sdf.parse(cd.getYmd()));
  			} catch (ParseException e) {
  				e.printStackTrace();
  			}
  			// 通过 Calendar 对象的 get 方法获取某天为星期几，就可以通过预约规则数组获取某天的总预约人数
  			int total = Integer.parseInt(strArr[calendar.get(Calendar.DAY_OF_WEEK)-1]);
  			// 填充 total 属性
  			cd.setTotal(total);
  			// 填充 remainder 属性，也就是剩余预约人数
  			cd.setRemainder(total-cd.getExisting());
  		}
  		// 返回结果
  		return calendarList30;
  	}
  	
  	// 第二步：获取当前年和当前月的日历
  	private List<CalendarResponseDto> getCurrentCalendarList(Integer year,Integer month){
  		List<CalendarResponseDto> currentCalendar = new ArrayList<>();
  		
  		// 实现万年历（下面的代码，都是非必要掌握的，了解日期的处理即可）
  		boolean isRun = false; 
  		if((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
  			isRun = true;
  		}
  		// 计算从 1900-01-01 到现在的天数
  		int totalDays = 0;
  		for(int i=1900;i<year;i++) {
  			if((i%4==0&&i%100!=0) || (i%400==0)) {
  				totalDays += 366;
  			}else {
  				totalDays += 365;
  			}
  		}
  		int beforeDays = 0;
  		int currentDays = 0;
  		for(int j=1;j<=month;j++) {
  			switch(j) {
  			case 1:
  			case 3:
  			case 5:
  			case 7:
  			case 8:
  			case 10:
  			case 12:
  				currentDays = 31;
  				break;
  			case 4:
  			case 6:
  			case 9:
  			case 11:
  				currentDays = 30;
  				break;
  			case 2:
  				if(isRun) {
  					currentDays = 29;
  				}else {
  					currentDays = 28;
  				}
  				break;
  			}
  			if(j<month) {
  				beforeDays += currentDays;
  			}
  		}
  		totalDays += beforeDays;
  		int firstDayOfMonth = totalDays%7 + 1;
  		if(firstDayOfMonth == 7) {
  			firstDayOfMonth = 0;
  		}
      
      // 很重要的两点：
      // 1、填充日历空白值
  		for(int k=0;k<firstDayOfMonth;k++) {
  			currentCalendar.add(new CalendarResponseDto());
  		}
      // 2、填充本月后面的天数
  		for(int i=1;i<=currentDays;i++) {
  			String m = month < 10 ? "0" + month : month + "";
  			String d = i < 10 ? "0" + i : i + "";
  			currentCalendar.add(new CalendarResponseDto(year+"-"+m+"-"+d));
  		}
      
      // 返回当前日历
  		return currentCalendar;
  	}
  
  }
  ```
  
- 8、添加 Controller 类 —— CalendarController

- ```java
  @RestController
  @RequestMapping("/calendar")
  public class CalendarController {
  
  	@Autowired
  	private CalendarService calendarService;
  	
  	// 生成预约日历
  	@RequestMapping("/listAppointmentCalendar")
  	public List<CalendarResponseDto> listAppointmentCalendar(@RequestBody CalendarRequestDto calendarRequestDto) {
  		return calendarService.listAppointmentCalendar(calendarRequestDto);
  	}
  }
  ```

- 9、编辑 application.yml 文件

- ```yaml
  server:
      port: 8080
      servlet:
          context-path: /tijian
          
  logging:
      level:
          org.springframework: debug
          com.neusoft.tijian.mapper: debug
          
  spring:
      datasource:
          driver-class-name: com.mysql.jdbc.Driver
          url: jdbc:mysql://localhost:3306/tijian?characterEncoding=utf-8
          username: root
          password: 123
          
  mybatis:
      mapper-locations: classpath:mapper/*.xml 
      type-aliases-package: com.neusoft.tijian.po,com.neusoft.tijian.dto #设置别名
  ```

  



### 2.1.3.服务器端代码

参考项目源代码。



## 2.2.前端项目



### 2.2.1.前端项目搭建



#### 2.2.1.1.开发工具检查

1. 检查vscode是否安装成功。

2. 检查 npm 安装环境： 命令行下输入：npm -v 

   1. 主要是安装 node.js，即可获得 npm 包管理器 https://nodejs.org/en

3. 检查VueCli安装环境：命令行下输入：vue -V   （注意：本工程使用VueCli5.0.1版本）

   1. vue-cli 是一个脚手架，相当于是一个空白的项目的结构，里面需要开发者去填充内容。https://cli.vuejs.org/zh/

   2. 安装命令：

   3. ```
      npm install -g @vue/cli
      # OR
      yarn global add @vue/cli
      ```


> 附录：
>
> 1. 安装npm：直接安装node.js  （输入 "npm -v" 测试是否安装成功； 输入 node –v 查看node版本）
> 2. 全局安装vuecli：npm install -g @vue/cli    （或：npm install -g @vue/cli@5.0.1）
> 3. 查看当前安装的vue-cli版本：vue  --version   或   vue –V
> 4. 卸载旧版本的vue-cli：npm uninstall vue-cli -g
> 5. 查看远程仓库中的版本号：npm view @vue/cli versions --json

#### 2.2.1.2.搭建VueCli工程

1. 命令行下进入工作空间目录中，输入： vue create tijianfront  （工程名必须小写）
2. 选择预设模板：这里选择“Manually select features”（手动选择特征）
3. 模块选取：Babel、Router
4. 选择是否使用 history 形式的路由：选择：N
5. 将依赖文件放在 package.json 中：选择：“in package.json”
6. 是否将当前选择保存以备下次使用：选择：N
7. 进入创建好的工程目录：cd tijianfront  
8. 启动工程：npm run serve 
9. 在浏览器中测试：http://localhost:8080

#### 2.2.1.3.添加其它依赖及配置文件

1. 添加font-awesome与axios依赖：
   `npm install font-awesome --save`
   `npm install axios --save`

2. 添加图片到 src 的 assets 中。

3. 在 src 目录下添加 common.js 文件

   ```javascript
   //获取当前时间（XXXX-XX-XX）
   export function getCurDate() {
   	var now = new Date();
   	var year = now.getFullYear();
   	var month = now.getMonth() + 1;
   	var day = now.getDate();
   	month = month < 10 ? "0" + month : month;
   	day = day < 10 ? "0" + day : day;
   	return year + "-" + month + "-" + day;
   }

   //向sessionStorage中存储一个JSON对象
   export function setSessionStorage(keyStr, value) {
   	sessionStorage.setItem(keyStr, JSON.stringify(value));
   }

   //从sessionStorage中获取一个JSON对象（取不到时返回null）
   export function getSessionStorage(keyStr) {
   	var str = sessionStorage.getItem(keyStr);
   	if (str == '' || str == null || str == 'null' || str == undefined) {
   		return null;
   	} else {
   		return JSON.parse(str);
   	}
   }

   //从sessionStorage中移除一个JSON对象
   export function removeSessionStorage(keyStr) {
   	sessionStorage.removeItem(keyStr);
   }

   //向localStorage中存储一个JSON对象
   export function setLocalStorage(keyStr, value) {
   	localStorage.setItem(keyStr, JSON.stringify(value));
   }

   //从localStorage中获取一个JSON对象（取不到时返回null）
   export function getLocalStorage(keyStr) {
   	var str = localStorage.getItem(keyStr);
   	if (str == '' || str == null || str == 'null' || str == undefined) {
   		return null;
   	} else {
   		return JSON.parse(str);
   	}
   }

   //从localStorage中移除一个JSON对象
   export function removeLocalStorage(keyStr) {
   	localStorage.removeItem(keyStr);
   }
   ```

4. 修改vue.config.js文件，修改启动端口

   ```javascript
   const { defineConfig } = require('@vue/cli-service')
   module.exports = defineConfig({
     transpileDependencies: true,
     devServer: {
       port: 8081  //修改启动端口
     }
   })
   
   ```

#### 2.2.1.4.main.js文件

```javascript
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import 'font-awesome/css/font-awesome.min.css'

//使用路由守卫实现登录的权限验证
router.beforeEach(function(to,from,next){
    let users = sessionStorage.getItem('users');
    //除了登录、注册之外，都需要判断是否登录
    if(!(to.path=='/'||to.path=='/login'||to.path=='/register')){
        if(users==null){
            router.push('/login');
        }
    }
    next();
});

createApp(App).use(router).mount('#app')
```

#### 2.2.1.5.App.vue文件

注意：在App.vue文件中，#app的高度也要设置为100%。

```vue
<template>
  <router-view/>
</template>

<style>
/***************** CSS重置 *******************/
html,body,div,span,h1,h2,h3,h4,h5,h6,ul,ol,li,p{
	margin: 0;
	padding: 0;
}
html,body,#app{
	width: 100%;
	height: 100%;
	font-family: "微软雅黑";
}
ul,ol{
	list-style:none;
}
a{
	text-decoration: none;
}
</style>
```

#### 2.2.1.6.路由index.js文件

```javascript
import { createRouter, createWebHashHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import Index from '../views/Index.vue'
import Appointment from '../views/Appointment.vue'
import ReportList from '../views/ReportList.vue'
import Personal from '../views/Personal.vue'
import Hospital from '../views/Hospital.vue'
import Setmeal from '../views/Setmeal.vue'
import SelectDate from '../views/SelectDate.vue'
import ConfirmOrder from '../views/ConfirmOrder.vue'
import AppointmentSuccess from '../views/AppointmentSuccess.vue'
import AppointmentList from '../views/AppointmentList.vue'
import Report from '../views/Report.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: Login
  },{
    path: '/login',
    name: 'Login',
    component: Login
  },{
    path: '/register',
    name: 'Register',
    component: Register
  },{
    path: '/index',
    name: 'Index',
    component: Index
  },{
    path: '/appointment',
    name: 'Appointment',
    component: Appointment
  },{
    path: '/reportList',
    name: 'ReportList',
    component: ReportList
  },{
    path: '/personal',
    name: 'Personal',
    component: Personal
  },{
    path: '/hospital',
    name: 'Hospital',
    component: Hospital
  },{
    path: '/setmeal',
    name: 'Setmeal',
    component: Setmeal
  },{
    path: '/selectDate',
    name: 'SelectDate',
    component: SelectDate
  },{
    path: '/confirmOrder',
    name: 'ConfirmOrder',
    component: ConfirmOrder
  },{
    path: '/appointmentSuccess',
    name: 'AppointmentSuccess',
    component: AppointmentSuccess
  },{
    path: '/appointmentList',
    name: 'AppointmentList',
    component: AppointmentList
  },{
    path: '/report',
    name: 'Report',
    component: Report
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
```



### 2.2.2.前端代码

参考项目源代码。

#### 1、Login.vue

```vue
<template>
  <div class="wrapper">
    <h1>体检预约-登录</h1>
    <section>
      <div class="input-box">
        <i class="fa fa-user-o"></i>
        <input type="text" v-model="users.userId" placeholder="输入手机号" />
      </div>
      <div class="input-box">
        <i class="fa fa-lock"></i>
        <input type="password" v-model="users.password" placeholder="输入登录密码" />
      </div>
      <div class="reg-box">
        <p @click="toRegister">注册</p>
        <p>忘记密码？</p>
      </div>
      <div class="button-box" @click="login">登录</div>
    </section>

    <footer>
      <div>
        <div class="line"></div>
        <p>有疑问请联系客服</p>
        <div class="line"></div>
      </div>
      <p>4008-XXX-XXX</p>
    </footer>
  </div>
</template>

<script>
import { reactive, toRefs } from "vue";
import { useRouter } from "vue-router";
import { setSessionStorage } from "../common.js";
import axios from "axios";
axios.defaults.baseURL = "http://localhost:8080/tijian/";

export default {
  setup() {
    const router = useRouter();

    const state = reactive({
      users: {
        userId: '',
        password: ''
      }
    });

    function login(){
      //表单验证
      if(state.users.userId==''){
        alert('手机号码不能为空！');
        return;
      }
      if(state.users.password==''){
        alert('密码不能为空！');
        return;
      }

      //登录
      axios.post('users/getUsersByUserIdByPass',state.users)
        .then((response) => {
          let users = response.data;
          if(users != ''){
            setSessionStorage('users',users); // key 要跟路由守卫中的名字一致
            router.push('/index');
          }else{
            alert('手机号码或密码不正确！');
          }
        })
        .catch((error) => {
          console.error(error);
        })
    }

    function toRegister(){
      router.push('/register');
    }

    return {
      ...toRefs(state),
      login,
      toRegister
    };
  },
};
</script>

<style scoped>
/*********************** 总容器 ***********************/
.wrapper {
  width: 100%;
  height: 100%;
  background-image: linear-gradient(45deg, #266c9f, #266c9f, #7eb059);
  overflow: hidden;
}

/*********************** 标题部分 ***********************/
h1 {
  text-align: center;
  color: #fff;
  font-size: 9.5vw;
  font-weight: 500;
  margin-top: 13vh;
  margin-bottom: 3vh;
}

/*********************** 内容部分 ***********************/
section {
  width: 86vw;
  margin: 0 auto;
  background-color: #fff;
  border-radius: 2.4vw;

  box-sizing: border-box;
  padding: 6vw;
}

section .input-box {
  width: 100%;
  height: 12vw;
  border: solid 1px #ccc;
  border-radius: 2vw;
  margin-top: 5vw;

  display: flex;
  align-items: center;
}
section .input-box i {
  margin: 0 3.6vw;
  font-size: 5.4vw;
  color: #ccc;
}
section .input-box input {
  border: none;
  outline: none;
}

section .reg-box {
  width: 100%;
  display: flex;
  justify-content: space-between;
  margin-top: 5vw;
  margin-bottom: 2vw;
}
section .reg-box p {
  font-size: 3.3vw;
  color: #2d727e;
  user-select: none;
  cursor: pointer;
}

section .button-box {
  width: 100%;
  height: 13vw;
  border-radius: 2vw;
  background-color: #70b0bc;

  text-align: center;
  line-height: 13vw;
  font-size: 4.6vw;
  color: #fff;
  letter-spacing: 1vw;

  user-select: none;
  cursor: pointer;
}

/*********************** footer部分 ***********************/
footer {
  width: 86vw;
  margin: 0 auto;
  margin-top: 12vw;
  font-size: 3.8vw;
  color: #fff;
}
footer div {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
footer div .line {
  width: 22vw;
  height: 1px;
  background-color: #fff;
}
footer > p {
  text-align: center;
  margin-top: 2vw;
}
</style>
```

#### 2、Register.vue

```vue
<template>
  <div class="wrapper">
    <header>
      <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
      <p>注册</p>
      <div></div>
    </header>
    <div class="top-ban"></div>
    <h1>欢迎注册</h1>
    <table>
      <tr>
        <td>手机号码</td>
        <td>
          <input
            type="text"
            v-model="users.userId"
            @blur="isExist"
            placeholder="请输入手机号码"
          />
        </td>
      </tr>
      <tr>
        <td>真实姓名</td>
        <td>
          <input
            type="text"
            v-model="users.realName"
            placeholder="真实姓名便于查看体检报告"
          />
        </td>
      </tr>
      <tr>
        <td>生日</td>
        <td><input type="date" v-model="users.birthday" /></td>
      </tr>
      <tr>
        <td>性别</td>
        <td>
          <input type="radio" v-model="users.sex" value="1" />男
          <input type="radio" v-model="users.sex" value="0" />女
        </td>
      </tr>
      <tr>
        <td>身份证号</td>
        <td>
          <input
            type="text"
            v-model="users.identityCard"
            placeholder="请输入身份证号"
          />
        </td>
      </tr>
      <tr>
        <td>密码</td>
        <td>
          <input
            type="password"
            v-model="users.password"
            placeholder="请输入密码"
          />
        </td>
      </tr>
      <tr>
        <td>确认密码</td>
        <td>
          <input
            type="password"
            v-model="beginPassword"
            placeholder="请再次输入密码"
          />
        </td>
      </tr>
    </table>
    <div class="btn" @click="register">完成</div>
  </div>
</template>

<script>
import { reactive, toRefs } from "vue";
import { useRouter } from "vue-router";
//import { setSessionStorage } from "../common.js";
import axios from "axios";
axios.defaults.baseURL = "http://localhost:8080/tijian/";

export default {
  setup() {
    const router = useRouter();

    const state = reactive({
      users: {
        userId: "",
        password: "",
        realName: "",
        sex: 1,
        identityCard: "",
        birthday: "",
        userType: 1,
      },
      beginPassword: "",
    });

    function register() {
      if (state.users.userId == "") {
        alert("手机号码不能为空！");
        return;
      }
      if (state.users.realName == "") {
        alert("真实姓名不能为空！");
        return;
      }
      if (state.users.birthday == "") {
        alert("生日不能为空！");
        return;
      }
      if (state.users.identityCard == "") {
        alert("身份证号不能为空！");
        return;
      }
      if (state.users.password == "") {
        alert("密码不能为空！");
        return;
      }
      if (state.users.password != state.beginPassword) {
        alert("两次输入密码不一致！");
        return;
      }

      axios
        .post("users/saveUsers", state.users)
        .then((response) => {
          if(response.data == 1){
            alert('注册成功！');
            router.push('/login');
          }else{
            alert('注册失败！');
          }
        })
        .catch((error) => {
          console.error(error);
        });
    }

    function isExist() {
      axios
        .post("users/getUsersById", {
          userId: state.users.userId
        })
        .then((response) => {
          if(response.data != ''){
            alert('此手机号码已被注册！');
            state.users.userId = '';
          }
        })
        .catch((error) => {
          console.error(error);
        });
    }

    return {
      ...toRefs(state),
      register,
      isExist,
    };
  },
};
</script>

<style scoped>
/*********************** 总容器 ***********************/
.wrapper {
  width: 100%;
  height: 100%;
}

/*********************** header ***********************/
header {
  width: 100%;
  height: 15.7vw;
  background-color: #fff;

  position: fixed;
  left: 0;
  top: 0;

  display: flex;
  align-items: center;
  justify-content: space-between;

  box-sizing: border-box;
  padding: 0 3.6vw;
}
header .fa {
  font-size: 8vw;
}

/*********************** 标题部分 ***********************/
h1 {
  font-size: 7.4vw;
  font-weight: 500;
  box-sizing: border-box;
  padding: 0 3.6vw;
  margin-top: 6vw;
}

/*********************** common样式 ***********************/
.top-ban {
  width: 100%;
  height: 15.7vw;
}

/*********************** table ***********************/
table {
  width: 92.8vw;
  margin: 0 auto;
  margin-top: 5vw;
  border-collapse: collapse;

  font-size: 4.2vw;
}
table tr td {
  height: 12vw;
  border-bottom: solid 1px #ddd;
}
table tr td input {
  border: none;
  outline: none;
}

/*********************** btn ***********************/
.btn {
  width: 92.8vw;
  margin: 0 auto;
  height: 12vw;
  margin-top: 8vw;
  background-color: #137e92;
  border-radius: 2vw;
  color: #fff;
  font-size: 5vw;
  text-align: center;
  line-height: 12vw;

  user-select: none;
  cursor: pointer;
}
</style>
```



#### 3、Index.vue

```vue
<template>
  <div class="wrapper">
    <!-- 头部 -->
    <header>
      <div class="header-content">
        <div class="header-content-left">
          <h1>沈阳云医院</h1>
          <i class="fa fa-angle-down"></i>
        </div>
        <i class="fa fa-envelope-o"></i>
      </div>
      <div class="header-bottom"></div>
    </header>

    <div class="top-ban"></div>
    <!-- 导航部分 -->
    <nav>
      <div class="logo-img">
        <img src="../assets/logo.png" />
      </div>
      <ul>
        <li>
          <img src="../assets/menu01.png" />
          <div class="nav-item-text">
            <h3>免费咨询</h3>
            <p>新型冠状病毒肺炎</p>
          </div>
        </li>
        <li>
          <img src="../assets/menu02.png" />
          <div class="nav-item-text">
            <h3>网络问诊</h3>
            <p>图文视频网络咨询</p>
          </div>
        </li>
        <li>
          <img src="../assets/menu03.png" />
          <div class="nav-item-text">
            <h3><span class="menu03-h3-span">e</span>心门诊</h3>
            <p>复旦医科大学专家</p>
          </div>
        </li>
        <li>
          <img src="../assets/menu04.png" />
          <div class="nav-item-text">
            <h3>慢病管理</h3>
            <p>血压血糖健康管理</p>
          </div>
        </li>
        <li>
          <img src="../assets/menu05.png" />
          <div class="nav-item-text">
            <h3>上门护理</h3>
            <p>网上购买上门服务</p>
          </div>
        </li>
        <li @click="toAppointment">
          <img src="../assets/menu06.png" />
          <div class="nav-item-text">
            <h3>团检预约</h3>
            <p>团体体检套餐定制</p>
          </div>
        </li>
      </ul>
    </nav>

    <!-- 我的健康报告 -->
    <div class="report">
      <div class="item-title">
        <p>我的健康报告</p>
      </div>
      <div class="report-content">
        <p>随时随地查看体检报告</p>
        <div @click="toReportList">立即查看</div>
      </div>
    </div>

    <!-- 推荐医生 -->
    <div class="doctor">
      <div class="item-title">
        <p>推荐医生</p>
      </div>
      <ul>
        <li>
          <img src="../assets/doctor1.jpg" />
          <h3>刘长胜</h3>
          <p>主任医师</p>
        </li>
        <li>
          <img src="../assets/doctor2.jpg" />
          <h3>孙鹿</h3>
          <p>主任医师</p>
        </li>
        <li>
          <img src="../assets/doctor3.jpg" />
          <h3>吕文达</h3>
          <p>主任医师</p>
        </li>
        <li>
          <img src="../assets/doctor4.jpg" />
          <h3>李若辰</h3>
          <p>主任医师</p>
        </li>
        <li>
          <img src="../assets/doctor5.jpg" />
          <h3>张石凡</h3>
          <p>主任医师</p>
        </li>
        <li>
          <img src="../assets/doctor6.jpg" />
          <h3>赵桂凤</h3>
          <p>副主任医师</p>
        </li>
        <li>
          <img src="../assets/doctor7.jpg" />
          <h3>李文</h3>
          <p>主任医师</p>
        </li>
        <li>
          <img src="../assets/doctor8.jpg" />
          <h3>吴晓梦</h3>
          <p>主任医师</p>
        </li>
      </ul>
    </div>

    <!-- 健康评估 -->
    <div class="assess">
      <div class="item-title">
        <p>健康评估</p>
        <span>更多</span>
      </div>
      <div class="assess-content">
        <div class="scroll-box" ref="scrollBoxRef">
          <ul ref="scrollBarRef">
            <li><img src="../assets/assess1.png" /></li>
            <li><img src="../assets/assess2.png" /></li>
            <li><img src="../assets/assess3.png" /></li>
            <li><img src="../assets/assess4.png" /></li>
            <li><img src="../assets/assess5.png" /></li>
            <li><img src="../assets/assess6.png" /></li>
          </ul>
        </div>
      </div>
    </div>

    <!-- 健康咨询 -->
    <div class="info">
      <div class="item-title">
        <p>健康咨询</p>
      </div>
      <ul>
        <li>
          <img src="../assets/info1.png" />
          <div>
            <h3>查出肺结核，我是不是要得肺癌了？</h3>
            <span>肺结核一定会导致肺癌吗？</span>
            <p><i class="fa fa-commenting-o"></i>3699</p>
          </div>
        </li>
        <div class="middle-ban"></div>
        <li>
          <img src="../assets/info2.png" />
          <div>
            <h3>体检发现甲状腺结节，怎么办？</h3>
            <span>日常需注意什么？</span>
            <p><i class="fa fa-commenting-o"></i>4256</p>
          </div>
        </li>
      </ul>
    </div>

    <div class="bottom-ban"></div>

    <Footer></Footer>
  </div>
</template>

<script>
import { useRouter } from "vue-router";
import { ref, onMounted } from "vue";
import Footer from "../components/Footer.vue";

export default {
  setup() {
    const router = useRouter();

    const scrollBoxRef = ref(null);
    const scrollBarRef = ref(null);

    onMounted(() => {
      let startX = 0; //手指接触屏幕时的X坐标
      let endX = 0; //手指在屏幕上滑动时的X坐标
      let scrollBarLeft = 0; //滚动条left值（X坐标值）

      let scrollBox = scrollBoxRef.value; //滚动条容器对象
      let scrollBar = scrollBarRef.value; //滚动条对象

      //滚动条向左滚动的极限位置（滚动条容器宽度-滚动条长度）
      let stop = scrollBox.offsetWidth - scrollBar.offsetWidth;

      //touchStart事件
      scrollBox.addEventListener(
        "touchstart",
        function (event) {
          let touch = event.targetTouches[0];
          startX = touch.pageX;
          scrollBarLeft = scrollBar.offsetLeft;
        },
        false
      );

      //touchMove事件
      scrollBox.addEventListener(
        "touchmove",
        function (event) {
          let touch = event.targetTouches[0];
          endX = touch.pageX;
          let len = endX - startX + scrollBarLeft;
          if (len >= 0) {
            len = 0;
          }
          if (len <= stop) {
            len = stop;
          }
          scrollBar.style.left = len + "px";
        },
        false
      );
    });

    function toAppointment() {
      router.push("/appointment");
    }

    function toReportList() {
      router.push("/reportList");
    }

    return {
      toAppointment,
      toReportList,
      scrollBoxRef,
      scrollBarRef,
    };
  },
  components: {
    Footer,
  },
};
</script>

<style scoped>
/*********************** 总容器 ***********************/
.wrapper {
  width: 100%;
  background-color: #f9f9f9;
}

/*********************** header ***********************/
header {
  width: 100%;
  height: 15.7vw;
  background-color: #fff;

  position: fixed;
  left: 0;
  top: 0;
}
header .header-content {
  width: 100%;
  height: 14.2vw;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
header .header-content .header-content-left {
  display: flex;
  align-items: center;
}
header .header-content .header-content-left h1 {
  font-size: 5vw;
  font-weight: 500;
  margin-left: 3.6vw;
  letter-spacing: 0.4vw;
}
header .header-content .header-content-left .fa-angle-down {
  font-size: 6vw;
  margin-left: 2vw;
}
header .header-content .fa-envelope-o {
  font-size: 5.6vw;
  margin-right: 3.6vw;
}
header .header-bottom {
  width: 100%;
  height: 1.5vw;
  background-color: #f9f9f9;
}

/*********************** nav ***********************/
nav {
  width: 100%;
  height: 110vw;
  background-color: #fff;
  margin-bottom: 3vw;
  overflow: hidden;
}
nav .logo-img {
  width: 92.8vw;
  height: 28vw;
  margin: 0 auto;
  margin-top: 3.6vw;
}
nav .logo-img img {
  width: 92.8vw;
  height: 28vw;
}

nav ul {
  width: 92.8vw;
  margin: 0 auto;
  margin-top: 11vw;

  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}
nav ul li {
  width: 45.5vw;
  height: 19.8vw;

  box-sizing: border-box;
  padding-top: 3vw;
  border: solid 1px #efefef;
  box-shadow: 0 2px 0 rgba(0, 0, 0, 0.04);

  border-radius: 1vw;
  margin-bottom: 1vw;

  display: flex;
}
nav ul li img {
  width: 10vw;
  height: 10vw;
  margin: 0 1.4vw;
}
nav ul li .nav-item-text h3 {
  font-size: 4.8vw;
  font-weight: 500;
  margin-bottom: 1vw;
}
nav ul li .nav-item-text p {
  font-size: 3.4vw;
  color: #888;
}
nav ul li .nav-item-text .menu03-h3-span {
  font-size: 6vw;
}

/*********************** 我的健康报告 ***********************/
.report {
  width: 100%;
  height: 32vw;
  background-color: #fff;
  margin-bottom: 3vw;
}
.report .report-content {
  width: 100%;
  height: 19vw;
  box-sizing: border-box;
  padding: 0 3.6vw;
  font-size: 4.3vw;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.report .report-content > div {
  font-size: 3.6vw;
  color: #fff;
  padding: 1.6vw 3vw;
  background-color: #127a90;
  border-radius: 2vw;

  user-select: none;
  cursor: pointer;
}

/*********************** 推荐医生 ***********************/
.doctor {
  width: 100%;
  height: 79vw;
  background-color: #fff;
  margin-bottom: 3vw;
}
.doctor ul {
  width: 100%;
  height: 66vw;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  align-items: center;
}
.doctor ul li {
  width: 22vw;
  height: 28vw;
  display: flex;
  flex-direction: column;
  align-items: center;

  user-select: none;
  cursor: pointer;
}
.doctor ul li img {
  width: 14vw;
  height: 14vw;
  border-radius: 7vw;
  margin-bottom: 1.8vw;
}
.doctor ul li h3 {
  font-size: 4vw;
  font-weight: 500;
  margin-bottom: 1.8vw;
}
.doctor ul li p {
  font-size: 3vw;
  color: #888;
}

/*********************** 健康评估 ***********************/
.assess {
  width: 100%;
  height: 43vw;
  background-color: #fff;
  margin-bottom: 3vw;
}
.assess .assess-content {
  width: 100%;
  height: 30vw;
  box-sizing: border-box;
  padding: 3.6vw;
  padding-right: 0vw;
}
.assess .assess-content .scroll-box {
  width: 100%;
  height: 22.8vw;
  position: relative;
  overflow: hidden;
}
.assess .assess-content .scroll-box ul {
  width: 270vw;
  height: 22.8vw;
  display: flex;
  overflow: hidden;

  position: absolute;
  left: 0;
  top: 0;
}
.assess .assess-content .scroll-box ul li {
  width: 41vw;
  height: 22.8vw;
  margin-right: 4vw;
}
.assess .assess-content .scroll-box ul li:last-child {
  margin-right: 0;
}
.assess .assess-content .scroll-box ul li img {
  width: 41vw;
  height: 22.8vw;
  display: block;
}

/*********************** 健康咨询 ***********************/
.info {
  width: 100%;
  background-color: #fff;
}
.info ul {
  width: 100%;
}
.info ul li {
  width: 100%;
  height: 27vw;
  box-sizing: border-box;
  padding: 3.6vw;
  display: flex;
  justify-content: space-between;
}
.info ul li img {
  width: 30vw;
  height: 19.8vw;
}
.info ul li div {
  width: 59vw;
}
.info ul li div h3 {
  font-size: 4vw;
  font-weight: 500;

  /*设置字符串超长后截取变省略号*/
  width: 100%;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
.info ul li div span {
  font-size: 3vw;
  color: #999;
}
.info ul li div p {
  text-align: right;
  font-size: 3vw;
  color: #999;
}
.info ul li div p i {
  margin-right: 2vw;
}

/*********************** common样式 ***********************/
.top-ban {
  width: 100%;
  height: 15.7vw;
}
.middle-ban {
  width: 100%;
  height: 1px;
  background-color: #e9e9e9;
}
.bottom-ban {
  width: 100%;
  height: 14.2vw;
}

.item-title {
  width: 100%;
  height: 13vw;
  box-sizing: border-box;
  padding: 0 3.6vw;
  border-bottom: solid 2px #f9f9f9;

  display: flex;
  align-items: center;
  justify-content: space-between;
}
.item-title p {
  height: 4.3vw;
  line-height: 4.3vw;

  box-sizing: border-box;
  padding-left: 3vw;

  font-size: 4.3vw;
  border-left: solid 2px #127a90;
  letter-spacing: 0.2vw;
}
.item-title span {
  font-size: 3vw;
  color: #666;
  user-select: none;
  cursor: pointer;
}
</style>
```



#### 4、Footer.vue

```vue
<template>
  <footer>
    <ul>
      <li @click="toIndex">
        <i class="fa fa-home"></i>
        <p>云医院</p>
      </li>
      <li>
        <i class="fa fa-opencart"></i>
        <p>商城</p>
      </li>
      <li>
        <i class="fa fa-compass"></i>
        <p>发现</p>
      </li>
      <li @click="toPersonal">
        <i class="fa fa-user"></i>
        <p>我</p>
      </li>
    </ul>
  </footer>
</template>

<script>
import { useRouter } from "vue-router";

export default {
  setup() {
    const router = useRouter();

    function toIndex() {
      router.push('/index');  
    }

    function toPersonal() {
      router.push('/personal');  
    }

    return {
      toIndex,
      toPersonal
    };
  },
};
</script>

<style scoped>
/*********************** footer ***********************/
footer {
  width: 100%;
  height: 14.2vw;
  box-sizing: border-box;
  border-top: solid 1px #e9e9e9;
  background-color: #fff;

  position: fixed;
  left: 0;
  bottom: 0;
}
footer ul {
  width: 100%;
  height: 14.2vw;
  display: flex;
  align-items: center;
  justify-content: space-around;
}
footer ul li {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  font-size: 3vw;
  color: #999;

  user-select: none;
  cursor: pointer;
}
footer ul li .fa {
  font-size: 5vw;
}
</style>
```



#### 5、Personal.vue

```vue
<template>
  <div class="wrapper">
        <header>
            <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
            <p>个人中心</p>
            <div></div>
        </header>
        <div class="top-ban"></div>

        <section>
            <div class="info">
                <div class="content">
                    <img src="../assets/user.png">
                    <div>
                        <p>{{users.realName}}</p>
                        <p>账号: {{users.userId}}</p>
                    </div>
                </div>
            </div>
            <ul>
                <li>
                    <div class="left">
                        <i class="fa fa-user-plus"></i>
                        <p>我的预约</p>
                    </div>
                    <div class="right" @click="toAppointmentList">
                        <i class="fa fa-angle-right"></i>
                    </div>
                </li>
                <li>
                    <div class="left">
                        <i class="fa fa-volume-control-phone"></i>
                        <p>我的服务</p>
                    </div>
                    <div class="right">
                        <i class="fa fa-angle-right"></i>
                    </div>
                </li>
                <li>
                    <div class="left">
                        <i class="fa fa-bed"></i>
                        <p>我的医生</p>
                    </div>
                    <div class="right">
                        <i class="fa fa-angle-right"></i>
                    </div>
                </li>
                <li>
                    <div class="left">
                        <i class="fa fa-sticky-note"></i>
                        <p>问诊订单</p>
                    </div>
                    <div class="right">
                        <i class="fa fa-angle-right"></i>
                    </div>
                </li>
                <li>
                    <div class="left">
                        <i class="fa fa-cart-plus"></i>
                        <p>商城订单</p>
                    </div>
                    <div class="right">
                        <i class="fa fa-angle-right"></i>
                    </div>
                </li>
                <li>
                    <div class="left">
                        <i class="fa fa-sign-out"></i>
                        <p>退出登录</p>
                    </div>
                    <div class="right" @click="out">
                        <i class="fa fa-angle-right"></i>
                    </div>
                </li>
            </ul>
        </section>
        
        <div class="bottom-ban"></div>
        <Footer></Footer>
    </div>
</template>

<script>
import Footer from "@/components/Footer.vue";
import { reactive, toRefs } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";
axios.defaults.baseURL = "http://localhost:8080/tijian/";
import { getSessionStorage, removeSessionStorage } from "../common.js";

export default {
  setup() {
    const router = useRouter();

    const state = reactive({
      users: getSessionStorage('users')
    });

    function toAppointmentList(){
      router.push('/appointmentList');
    }

    function out(){
      removeSessionStorage('users');
      router.push('/login');
    }

    return {
      ...toRefs(state),
      toAppointmentList,
      out
    };
  },
  components: { Footer },
};
</script>

<style scoped>
/*********************** 总容器 ***********************/
.wrapper{
    width: 100%;
    height: 100%;
    background-color: #F9F9F9;
}

/*********************** header ***********************/
header{
    width: 100%;
    height: 15.7vw;
    background-color: #FFF;

    position: fixed;
    left: 0;
    top: 0;

    display: flex;
    align-items: center;
    justify-content: space-between;

    box-sizing: border-box;
    padding: 0 3.6vw;
}
header .fa{
    font-size: 8vw;
}

/*********************** common样式 ***********************/
.top-ban{
    width: 100%;
    height: 15.7vw;
}
.bottom-ban{
    width: 100%;
    height: 14.2vw;
}

/*********************** section ***********************/
section{
    width: 100%;
}
section .info{
    width: 100%;
    height: 30vw;
    background-image: linear-gradient(135deg,#7DB35D,#02A6C9,#02A6C9);
    display: flex;
    align-items: center;
}
section .info .content{
    width: 90vw;
    margin: 0 auto;
    display: flex;
}
section .info .content img{
    width: 14vw;
    height: 14vw;
    border-radius: 7vw;
}
section .info .content div{
    height: 14vw;
    margin-left: 3vw;
    color: #FFF;
}
section .info .content div p:first-child{
    font-size: 4.8vw;
}
section .info .content div p:last-child{
    font-size: 3.2vw;
    margin-top: 2.6vw;
}

section ul{
    width: 86vw;
    margin: 0 auto;
}
section ul li{
    width: 100%;
    height: 14vw;
    border-bottom: solid 1px #EEE;
    color: #555;
    font-size: 4.2vw;
    font-weight: 600;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
section ul li .left{
    display: flex;
    align-items: center;
}
section ul li .left p{
    margin-left: 3vw;
}
section ul li .right{
    user-select: none;
    cursor: pointer;
}
section ul li .right i{
    font-size: 6vw;
}
</style>
```



#### 6、Appointment.vue

```vue
<template>
  <div class="wrapper">
    <header>
      <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
      <p>用户体检预约</p>
      <div></div>
    </header>
    <div class="top-ban"></div>

    <section>
      <img src="../assets/yuyue.png" />
      <p>
        <img src="../assets/benrenyuyue.png" @click="toHospital" />
        <img src="../assets/jiashuyuyue.png" />
      </p>
    </section>

    <div class="bottom-ban"></div>

    <Footer></Footer>
  </div>
</template>

<script>
import { useRouter } from "vue-router";
import Footer from "../components/Footer.vue";
import axios from "axios";
axios.defaults.baseURL = "http://localhost:8080/tijian/";
import { getSessionStorage } from "../common.js";

export default {
  setup() {
    const router = useRouter();

    function toHospital() {
      axios
        .post("orders/getOrdersByUserId", {
          userId: getSessionStorage('users').userId
        })
        .then((response) => {
          if(response.data>=1){
            alert('已经预约了！');
          }else{
            router.push('/hospital');
          }
        })
        .catch((error) => {
          console.error(error);
        });
    }

    return {
      toHospital,
    };
  },
  components: {
    Footer,
  },
};
</script>

<style scoped>
/*********************** 总容器 ***********************/
.wrapper {
  width: 100%;
  height: 100%;
}

/*********************** header ***********************/
header {
  width: 100%;
  height: 15.7vw;
  background-color: #fff;

  position: fixed;
  left: 0;
  top: 0;

  display: flex;
  align-items: center;
  justify-content: space-between;

  box-sizing: border-box;
  padding: 0 3.6vw;
}
header .fa {
  font-size: 8vw;
}

/*********************** common样式 ***********************/
.top-ban {
  width: 100%;
  height: 15.7vw;
}
.bottom-ban {
  width: 100%;
  height: 14.2vw;
}

/*********************** section ***********************/
section {
  width: 100%;
}
section img {
  width: 100vw;
}
section p {
  box-sizing: border-box;
  padding: 0 3.6vw;
  margin-top: 10vw;

  display: flex;
  justify-content: space-between;
}
section p img {
  width: 44vw;
  border-radius: 1.6vw;
  display: block;
  cursor: pointer;
}
</style>
```



#### 7、Hospital.vue

```vue
<template>
  <div class="wrapper">
    <header>
      <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
      <p>请您选择体检机构</p>
      <div></div>
    </header>
    <div class="top-ban"></div>

    <ul class="hospital">
      <li v-for="hospital in hospitalArr" :key="hospital.hpId">
        <h3 @click="toSetmeal(hospital.hpId)">
          {{hospital.name}}
          <i class="fa fa-angle-right"></i>
        </h3>
        <div class="hospita-info">
          <img :src="hospital.picture" />
          <table>
            <tr>
              <td>营业时间</td>
              <td>{{hospital.businessHours}}</td>
            </tr>
            <tr>
              <td>采血截止</td>
              <td>{{hospital.deadline}}</td>
            </tr>
            <tr>
              <td>电话</td>
              <td>{{hospital.telephone}}</td>
            </tr>
            <tr>
              <td>地址</td>
              <td>{{hospital.address}}</td>
            </tr>
          </table>
        </div>
        <div class="about">
          <p>
            <i class="fa fa-phone"></i>
            联系我们
          </p>
          <p>
            <i class="fa fa-map-marker"></i>
            查找位置
          </p>
        </div>
      </li>
    </ul>

    <div class="bottom-ban"></div>

    <Footer></Footer>
  </div>
</template>

<script>
import Footer from "@/components/Footer.vue";
import { reactive, toRefs } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";
axios.defaults.baseURL = "http://localhost:8080/tijian/";

export default {
  setup() {
    const router = useRouter();

    const state = reactive({
      hospitalArr: [],	// 用来保存从服务端获取的医院列表
    });

    init();
    
    function init() { //初始化，直接查询
      axios
        .post("hospital/listHospital", {
          state: 1
        })
        .then((response) => {
          state.hospitalArr = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    }

    function toSetmeal(hpId){ // 跳转去选择医院套餐，需要传递参数，才能带着往下走
      router.push({path:'/setmeal',query:{hpId:hpId}});
    }

    return {
      ...toRefs(state),
      toSetmeal
    };
  },
  components: { Footer },
};
</script>

<style>
/*********************** 总容器 ***********************/
.wrapper {
  width: 100%;
  height: 100%;
  background-color: #f9f9f9;
}

/*********************** header ***********************/
header {
  width: 100%;
  height: 15.7vw;
  background-color: #fff;

  position: fixed;
  left: 0;
  top: 0;

  display: flex;
  align-items: center;
  justify-content: space-between;

  box-sizing: border-box;
  padding: 0 3.6vw;
}
header .fa {
  font-size: 8vw;
}

/*********************** common样式 ***********************/
.top-ban {
  width: 100%;
  height: 15.7vw;
}
.bottom-ban {
  width: 100%;
  height: 14.2vw;
}

/*********************** hospital ***********************/
.hospital {
  width: 100%;
  margin-top: 3.6vw;
}
.hospital li {
  width: 92.8vw;
  margin: 0 auto;
  border: solid 1px #eee;
  border-radius: 1vw;
  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.08);
  background-color: #fff;
  margin-bottom: 3.6vw;

  box-sizing: border-box;
  padding: 4vw;
}
.hospital li h3 {
  box-sizing: border-box;
  padding-left: 2.4vw;
  border-left: solid 3px #157999;
  font-size: 4.3vw;
  display: flex;
  justify-content: space-between;

  user-select: none;
  cursor: pointer;
}
.hospital li h3 i {
  font-size: 5vw;
}

.hospital li .hospita-info {
  width: 100%;
  margin-top: 3vw;
  display: flex;
  justify-content: space-between;
}
.hospital li .hospita-info img {
  width: 22vw;
  height: 22vw;
}
.hospital li .hospita-info table {
  width: 59vw;
  font-size: 3.5vw;
  color: #666;
}
.hospital li .hospita-info table tr {
  height: 6vw;
}
.hospital li .hospita-info table tr td {
  vertical-align: top;
}
.hospital li .hospita-info table tr td:first-child {
  width: 15vw;
}
.hospital li .about {
  display: flex;
  justify-content: flex-end;
  margin-top: 2vw;
}
.hospital li .about p {
  width: 30vw;
  height: 8vw;
  border: solid 1px #157999;
  border-radius: 2vw;

  text-align: center;
  line-height: 8vw;
  margin-left: 2vw;

  font-size: 4vw;
  color: #157999;

  user-select: none;
  cursor: pointer;
}
.hospital li .about p i {
  color: #555;
  margin-right: 1vw;
}
</style>
```



#### 8、Setmeal.vue

```vue
<template>
  <div class="wrapper">
    <header>
      <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
      <p>选择体检套餐</p>
      <div></div>
    </header>
    <div class="top-ban"></div>

    <ul class="setmeal">
      <li v-for="(setmeal,index) in setmealArr" :key="setmeal.smId">
        <div class="item">
          <div class="item-left" @click="toSelectDate(setmeal.smId)">
            <h3>{{setmeal.type==1?'男士套餐':'女士套餐'}}</h3>
            <p>{{setmeal.name}}</p>
          </div>
          <div class="item-right" @click="isShowEvent(index)">
            <p>详情</p>
            <i class="fa fa-angle-down"></i>
          </div>
        </div>
        <div class="item-content" v-if="setmeal.isShow">
          <table>
            <tr>
              <th>检查项目</th>
              <th>检查内容</th>
              <th>检查意义</th>
            </tr>
            <tr v-for="setmealDetailed in setmeal.sdList" :key="setmealDetailed.sdId">
              <td>{{setmealDetailed.checkItem.ciName}}</td>
              <td>{{setmealDetailed.checkItem.ciContent}}</td>
              <td>{{setmealDetailed.checkItem.meaning}}</td>
            </tr>
          </table>
        </div>
      </li>
    </ul>

    <div class="bottom-ban"></div>
    <Footer></Footer>
  </div>
</template>

<script>
import Footer from "@/components/Footer.vue";
import { reactive, toRefs } from "vue";
import { useRoute, useRouter } from "vue-router";
import axios from "axios";
axios.defaults.baseURL = "http://localhost:8080/tijian/";
import { getSessionStorage } from "../common.js";

export default {
  setup() {
    const router = useRouter();
    const route = useRoute();

    const state = reactive({
      setmealArr: []
    });

    init();
    function init() {
      axios
        .post("setmeal/listSetmealByType", {
          type: getSessionStorage('users').sex
        })
        .then((response) => {
          let arr = response.data;
          for(let i=0;i<arr.length;i++){
            arr[i].isShow = false;
          }
          state.setmealArr = arr;
        })
        .catch((error) => {
          console.error(error);
        });
    }

    function isShowEvent(index){
      state.setmealArr[index].isShow = !state.setmealArr[index].isShow;
    }

    function toSelectDate(smId){
      router.push({path:'/selectDate',query:{hpId:route.query.hpId,smId:smId}});
    }

    return {
      ...toRefs(state),
      isShowEvent,
      toSelectDate
    };
  },
  components: { Footer },
};
</script>

<style scoped>
/*********************** 总容器 ***********************/
.wrapper {
  width: 100%;
  height: 100%;
  background-color: #f9f9f9;
}

/*********************** header ***********************/
header {
  width: 100%;
  height: 15.7vw;
  background-color: #fff;

  position: fixed;
  left: 0;
  top: 0;

  display: flex;
  align-items: center;
  justify-content: space-between;

  box-sizing: border-box;
  padding: 0 3.6vw;
}
header .fa {
  font-size: 8vw;
}

/*********************** footer ***********************/
footer {
  width: 100%;
  height: 14.2vw;
  box-sizing: border-box;
  border-top: solid 1px #e9e9e9;
  background-color: #fff;

  position: fixed;
  left: 0;
  bottom: 0;
}
footer ul {
  width: 100%;
  height: 14.2vw;
  display: flex;
  align-items: center;
  justify-content: space-around;
}
footer ul li {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  font-size: 3vw;
  color: #999;

  user-select: none;
  cursor: pointer;
}
footer ul li .fa {
  font-size: 5vw;
}

/*********************** common样式 ***********************/
.top-ban {
  width: 100%;
  height: 15.7vw;
}
.bottom-ban {
  width: 100%;
  height: 14.2vw;
}

/*********************** setmeal ***********************/
.setmeal {
  width: 100%;
  margin-top: 10vw;
}
.setmeal li {
  width: 92.8vw;
  margin: 0 auto;
  border: solid 1px #eee;
  border-radius: 1vw;
  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.08);
  background-color: #fff;
  margin-bottom: 3.6vw;
}
.setmeal li .item {
  width: 100%;
  height: 20vw;
  color: #555;

  display: flex;
  align-items: center;
}
.setmeal li .item .item-left {
  flex: 0 0 72vw;
  height: 20vw;
  border-right: solid 1px #eee;
  box-sizing: border-box;
  padding-left: 12vw;

  display: flex;
  flex-direction: column;
  justify-content: center;

  user-select: none;
  cursor: pointer;
}
.setmeal li .item .item-left h3 {
  font-size: 4.3vw;
  font-weight: 600;
}
.setmeal li .item .item-left p {
  font-size: 4vw;
  margin-top: 1vw;
}
.setmeal li .item .item-right {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;

  user-select: none;
  cursor: pointer;
}
.setmeal li .item .item-right p {
  font-size: 4vw;
  margin-right: 2vw;
}

.setmeal li .item-content {
  /*display: none;*/
  width: 100%;
  background-color: #fff;
}
.setmeal li .item-content table {
  width: 100%;
  border-collapse: collapse;
  font-size: 4vw;
  color: #555;
}
.setmeal li .item-content table tr {
  display: flex;
}
.setmeal li .item-content table tr td,
.setmeal li .item-content table tr th {
  flex: 1;
}
.setmeal li .item-content table tr th {
  text-align: center;
  background-color: #eee;
  height: 10vw;
  line-height: 10vw;
}
.setmeal li .item-content table tr td {
  border: solid 1px #efefef;
  box-sizing: border-box;
  padding: 2vw;
}
</style>
```



#### 9、SelectData.vue

```vue
<template>
  <div class="wrapper">
    <header>
      <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
      <p>选择体检日期</p>
      <div></div>
    </header>
    <div class="top-ban"></div>

    <section>
      <div class="date-box">
        <i class="fa fa-caret-left" @click="subtractMonth"></i>
        <p>{{year+"年"+month+"月"}}</p>
        <i class="fa fa-caret-right" @click="addMonth"></i>
      </div>
      <table>
        <tr>
          <th>日</th>
          <th>一</th>
          <th>二</th>
          <th>三</th>
          <th>四</th>
          <th>五</th>
          <th>六</th>
        </tr>
      </table>
      <ul>
        <li v-for="(calendar,index) in calendarArr" :key="calendar.ymd">
          <p
            :class="{
              fontcolor: calendar.remainder != null && calendar.remainder != 0,
              pselect: calendar.selectDay == 1,
            }"
            @click="selectDay(index)"
          >
            {{ calendar.day }}
          </p>
          <p>
            {{calendar.remainder != null && calendar.remainder != 0?"余"+calendar.remainder:""}}
          </p>
        </li>
      </ul>
    </section>

    <div class="bottom-btn">
      <div></div>
      <div @click="toConfirmOrder">下一步</div>
    </div>

    <div class="bottom-ban"></div>
    <Footer></Footer>
  </div>
</template>

<script>
import Footer from "@/components/Footer.vue";
import { reactive, toRefs } from "vue";
import { useRoute, useRouter } from "vue-router";
import axios from "axios";
axios.defaults.baseURL = "http://localhost:8080/tijian/";

export default {
  setup() {
    const router = useRouter();
    const route = useRoute();
    const curDate = new Date();

    const state = reactive({
      hpId: route.query.hpId,
      smId: route.query.smId,
      year: curDate.getFullYear(),
      month: curDate.getMonth() + 1,
      calendarArr: [],
      selectDay: "", // 用户选择的预约日期（这个很重要）
    });

    // 获取日历
    getCalendar();
    function getCalendar() {
      axios
        .post("calendar/listAppointmentCalendar", {
          hpId: state.hpId,
          year: state.year,
          month: state.month,
        })
        .then((response) => {
          state.calendarArr = response.data;
          // 需要对返回数组进行再加工
          for (let i = 0; i < state.calendarArr.length; i++) {
            // 返回数组的前几个元素可能为空
            if (state.calendarArr[i].ymd != null) {
              // 1、需要将日期 day 单独提取出来显示
              state.calendarArr[i].day = parseInt(
                state.calendarArr[i].ymd.substring(8) 
              );
              //2 、给每一个日期添加一个是否选中的标识（0：未选中；1：选中）
              if (state.calendarArr[i].ymd == state.selectDay) {
                state.calendarArr[i].selectDay = 1;
              } else {
                state.calendarArr[i].selectDay = 0;
              }
            }
          }
        })
        .catch((error) => {
          console.error(error);
        });
    }

    // 向左按钮：查看上一个月
    function subtractMonth(){
      if(state.month>1){
        state.month--;
      }else{
        state.year--;
        state.month = 12;
      }
      getCalendar();
    }
    
    // 向右按钮：查看下一个月
    function addMonth(){
      if(state.month<12){
        state.month++;
      }else{
        state.year++;
        state.month = 1;
      }
      getCalendar();
    }

    // 选择日期
    function selectDay(index){
      // 验证当前选中日期是否为可预约日期
      if(state.calendarArr[index].remainder==null||state.calendarArr[index].remainder==0){
        return;
      }
      // 将所有日期的选中状态清空
      for(let i=0;i<state.calendarArr.length;i++){
        state.calendarArr[i].selectDay = 0;
      }
      state.calendarArr[index].selectDay = 1;
      state.selectDay = state.calendarArr[index].ymd;
    }

    // 点下一步，是去确认订单
    // 未选择预约日期时，不让跳转
    function toConfirmOrder(){
      if(state.selectDay==''){
        alert('请选择体检预约日期！');
        return;
      }
      // flag 指的是除了时确认订单页面，其他页面不必出现「确认支付」按钮
      router.push({path:'/confirmOrder',query:{hpId:state.hpId,smId:state.smId,selectDay:state.selectDay,flag:1}});
    }

    return {
      ...toRefs(state),
      subtractMonth,
      addMonth,
      selectDay,
      toConfirmOrder
    };
  },
  components: { Footer },
};
</script>

<style scoped>
/*********************** 总容器 ***********************/
.wrapper {
  width: 100%;
  height: 100%;
  background-color: #f9f9f9;
}

/*********************** header ***********************/
header {
  width: 100%;
  height: 15.7vw;
  background-color: #fff;

  position: fixed;
  left: 0;
  top: 0;

  display: flex;
  align-items: center;
  justify-content: space-between;

  box-sizing: border-box;
  padding: 0 3.6vw;
}
header .fa {
  font-size: 8vw;
}

/*********************** common样式 ***********************/
.top-ban {
  width: 100%;
  height: 15.7vw;
}
.bottom-ban {
  width: 100%;
  height: 14.2vw;
}

/*********************** section ***********************/
section {
  width: 82vw;
  margin: 0 auto;
  margin-top: 12vw;
}
section .date-box {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 4.5vw;
  font-weight: 600;
}
section .date-box p {
  color: #000;
}
section .date-box i {
  color: #999;
  user-select: none;
  cursor: pointer;
}
section table {
  width: 100%;
  font-size: 3.8vw;
}
section table tr th {
  text-align: center;
  font-weight: 600;
  line-height: 12vw;
}

section ul {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
}
section ul li {
  width: 14.28%;
  height: 12vw;
  font-weight: 600;
  color: #999;
}
section ul li p:first-child {
  width: 6vw;
  height: 6vw;
  margin: 0 auto;
  border-radius: 3vw;

  display: flex;
  justify-content: center;
  align-items: center;

  font-size: 4.2vw;

  user-select: none;
  cursor: pointer;
}
section ul li p:last-child {
  font-size: 3vw;
  font-weight: 300;
  text-align: center;
  user-select: none;
}

.fontcolor {
  color: #333;
}

.pselect {
  background-color: #fb902b;
  color: #fff;
}

/*********************** bottom-btn ***********************/
.bottom-btn {
  width: 100%;
  height: 12vw;
  background-color: #fff;

  position: fixed;
  left: 0;
  bottom: 14.2vw;

  display: flex;
}
.bottom-btn div:first-child {
  flex: 2;
}
.bottom-btn div:last-child {
  flex: 1;
  background-color: #117c94;
  text-align: center;
  line-height: 12vw;
  font-size: 5vw;
  color: #fff;

  user-select: none;
  cursor: pointer;
}
/*********************** 为Vue做的样式 ***********************/
.fontcolor {
  color: #333;
}
.pselect {
  background-color: #fb902b;
  color: #fff;
}
</style>
```

#### 10、ConfirmOrder.vue

```vue
<template>
  <div class="wrapper">
        <header>
            <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
            <p>确认您的订单</p>
            <div></div>
        </header>
        <div class="top-ban"></div>

        <section>
            <div class="title">
                <p>体检人信息</p>
            </div>
            <table>
                <tr>
                    <td>姓名:</td>
                    <td>{{users.realName}}</td>
                </tr>
                <tr>
                    <td>证件号码:</td>
                    <td>{{users.identityCard}}</td>
                </tr>
                <tr>
                    <td>出生日期:</td>
                    <td>{{users.birthday}}</td>
                </tr>
                <tr>
                    <td>手机号码:</td>
                    <td>{{users.userId}}</td>
                </tr>
            </table>
            <div class="title">
                <p>体检日期</p>
            </div>
            <table>
                <tr>
                    <td>{{selectDay.split("-")[0]}}年{{selectDay.split("-")[1]}}月{{selectDay.split("-")[2]}}日</td>
                </tr>
            </table>
            <div class="title">
                <p>体检机构</p>
            </div>
            <table>
                <tr>
                    <td colspan="2">{{hospital.name}}</td>
                </tr>
                <tr>
                    <td>营业时间:</td>
                    <td>{{hospital.businessHours}}</td>
                </tr>
                <tr>
                    <td>采血截止:</td>
                    <td>{{hospital.deadline}}</td>
                </tr>
                <tr>
                    <td>机构电话:</td>
                    <td>{{hospital.telephone}}</td>
                </tr>
                <tr>
                    <td>机构地址:</td>
                    <td>{{hospital.address}}</td>
                </tr>
            </table>
            <div class="title">
                <p>套餐类型</p>
            </div>
            <table>
                <tr>
                    <td>{{setmeal.name}}</td>
                </tr>
            </table>
        </section>
        
        <div class="bottom-btn">
            <div class="first">实付款: <span>￥{{users.userType == 2 ? 0 : setmeal.price}}</span></div>
            <div class="last" @click="toSuccess" v-if="flag==1">确认支付</div>
        </div>

        <div class="bottom-ban"></div>
        <Footer></Footer>
    </div>
</template>

<script>
import Footer from "@/components/Footer.vue";
import { reactive, toRefs } from "vue";
import { useRoute, useRouter } from "vue-router";
import axios from "axios";
axios.defaults.baseURL = "http://localhost:8080/tijian/";
import { getSessionStorage } from "../common.js";

export default {
  setup() {
    const router = useRouter();
    const route = useRoute();

    const state = reactive({
      hpId: route.query.hpId,
      smId: route.query.smId,
      selectDay: route.query.selectDay,
      flag: route.query.flag, // 除了时确认订单页面，其他页面不必出现「确认支付」按钮
      users: getSessionStorage('users'),
      hospital: {},
      setmeal: {}
    });

    init();
    function init(){
      axios
        .post("hospital/getHospitalById", {
          hpId: state.hpId
        })
        .then((response) => {
          state.hospital = response.data;
        })
        .catch((error) => {
          console.error(error);
        });

      axios
        .post("setmeal/getSetmealById", {
          smId: state.smId
        })
        .then((response) => {
          state.setmeal = response.data;
        })
        .catch((error) => {
          console.error(error);
        });  
    }

    function toSuccess(){
      axios
        .post("orders/saveOrders", {
          orderDate: state.selectDay,
          userId: state.users.userId,
          hpId: state.hpId,
          smId: state.smId
        })
        .then((response) => {
          if(response.data==1){
            router.push('/appointmentSuccess');
            // 在这里也可以跳转支付页面
          }else{
            alert('生成订单失败！');
          }
        })
        .catch((error) => {
          console.error(error);
        });  
    }

    return {
      ...toRefs(state),
      toSuccess
    };
  },
  components: { Footer },
};
</script>

<style scoped>
/*********************** 总容器 ***********************/
.wrapper{
    width: 100%;
    height: 100%;
    background-color: #F9F9F9;
}

/*********************** header ***********************/
header{
    width: 100%;
    height: 15.7vw;
    background-color: #FFF;

    position: fixed;
    left: 0;
    top: 0;

    display: flex;
    align-items: center;
    justify-content: space-between;

    box-sizing: border-box;
    padding: 0 3.6vw;
}
header .fa{
    font-size: 8vw;
}

/*********************** common样式 ***********************/
.top-ban{
    width: 100%;
    height: 15.7vw;
}
.bottom-ban{
    width: 100%;
    height: 26.2vw;
}

/*********************** section ***********************/
section{
    width: 86vw;
    margin: 0 auto;
}
section .title{
    width: 100%;
    height: 12vw;
    border-bottom: solid 1px #EEE;

    display: flex;
    align-items: center;
}
section .title p{
    height: 3.4vw;
    line-height: 3.4vw;
    font-size: 4.2vw;
    font-weight: 600;
    box-sizing: border-box;
    padding-left: 3vw;
    border-left: solid 2px #127A90;
}
section table{
    font-size: 3.6vw;
    color: #555;
    margin-top: 2vw;
}
section table tr{
    line-height: 8vw;
}
section table tr td:first-child{
    width: 22%;
}

/*********************** bottom-btn ***********************/
.bottom-btn{
    width: 100%;
    height: 12vw;
    background-color: #FFF;

    position: fixed;
    left: 0;
    bottom: 14.2vw;

    display: flex;
}
.bottom-btn .first{
    flex: 2;
    line-height: 12vw;
    font-size: 4.6vw;
    box-sizing: border-box;
    padding-left: 6vw;
}
.bottom-btn .first span{
    color: #F77B2D;
}
.bottom-btn .last{
    flex: 1;
    background-color: #117C94;
    line-height: 12vw;
    text-align: center;
    font-size: 5vw;
    color: #FFF;

    user-select: none;
    cursor: pointer;
}
</style>
```

#### 11、AppointmentSuccess.vue

```vue
<template>
  <div class="wrapper">
        <header>
            <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
            <p>预约成功</p>
            <div></div>
        </header>
        <div class="top-ban"></div>

        <section>
            <div class="success">
                <div class="icon-box">
                    <div class="icon">
                        <i class="fa fa-check"></i>
                    </div>
                </div>
                <h1>恭喜预约成功！</h1>
                <p>请体检用户携带本人身份证到店认证</p>
            </div>
            <div class="order-btn" @click="toAppointmentList">查看订单</div>
            <div class="continue" @click="toAppointment">继续为家人预约</div>
            <div class="info">
                <p>您的信息已经发送至体检机构</p>
                <p>预约成功后会发送短信至您的手机</p>
                <p>客服电话<span>4008-XXX-XXX</span></p>
            </div>
        </section>
        
        <div class="bottom-ban"></div>
        <Footer></Footer>
    </div>
</template>

<script>
import Footer from "@/components/Footer.vue";
import { useRouter } from "vue-router";

export default {
  setup() {
    const router = useRouter();

    function toAppointmentList(){
      router.push('/appointmentList');
    }

    function toAppointment(){
      router.push('/appointment');
    }

    return {
      toAppointmentList,
      toAppointment
    };
  },
  components: { Footer },
};
</script>

<style scoped>
/*********************** 总容器 ***********************/
.wrapper{
    width: 100%;
    height: 100%;
    background-color: #F9F9F9;
}

/*********************** header ***********************/
header{
    width: 100%;
    height: 15.7vw;
    background-color: #FFF;

    position: fixed;
    left: 0;
    top: 0;

    display: flex;
    align-items: center;
    justify-content: space-between;

    box-sizing: border-box;
    padding: 0 3.6vw;
}
header .fa{
    font-size: 8vw;
}

/*********************** common样式 ***********************/
.top-ban{
    width: 100%;
    height: 15.7vw;
}
.bottom-ban{
    width: 100%;
    height: 14.2vw;
}

/*********************** section ***********************/
section{
    width: 100%;
}
section .success{
    width: 100%;
    height: 62vw;
    border-bottom: solid 1px #EEE;
    background-color: #FFF;
}
section .success .icon-box{
    width: 100%;
    height: 30vw;
    background-image: linear-gradient(135deg,#01C7A4,#02A6C9,#02A6C9);
    position: relative;
}
section .success .icon-box .icon{
    width: 16vw;
    height: 16vw;
    border-radius: 8vw;
    background-color: #FFF;

    position: absolute;
    left: 42vw;
    bottom: -8vw;

    font-size: 8vw;
    color: #02A6C9;

    text-align: center;
    line-height: 16vw;
}
section .success h1{
    text-align: center;
    font-size: 5.2vw;
    font-weight: 500;
    color: #02A6C9;
    margin-top: 10vw;
}
section .success p{
    text-align: center;
    font-size: 3.4vw;
    color: #555;
    margin-top: 3vw;
}

section .order-btn{
    width: 100%;
    height: 14vw;
    text-align: center;
    line-height: 14vw;
    font-size: 4vw;
    color: #02A6C9;
    background-color: #FFF;
    user-select: none;
    cursor: pointer;
}
section .continue{
    width: 88vw;
    height: 13vw;
    margin: 0 auto;
    background-image: linear-gradient(135deg,#01C7A4,#02A6C9,#02A6C9);
    border-radius: 1vw;
    text-align: center;
    line-height: 13vw;
    margin-top: 5vw;
    font-size: 4vw;
    color: #FFF;
    user-select: none;
    cursor: pointer;
}
section .info{
    width: 100%;
    margin-top: 16vw;
}
section .info p{
    text-align: center;
    font-size: 3.2vw;
    color: #999;
    margin-top: 1vw;
}
section .info p span{
    font-size: 3.6vw;
    color: #555;
    margin-left: 1vw;
}

</style>
```

#### 12、AppointmentList.vue

```vue
<template>
  <div class="wrapper">
    <header>
      <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
      <p>我的预约</p>
      <div></div>
    </header>
    <div class="top-ban"></div>

    <ul>
      <li v-for="orders in ordersArr" :key="orders.orderId">
        <div class="left" @click="toConfirmOrder(orders)">
          <p>{{orders.orderDate}}</p>
          <p>{{orders.setmeal.name}}</p>
        </div>
        <div class="right" v-if="curDate<orders.orderDate" @click="cancel(orders.orderId)">
          取消预约
        </div>
      </li>
    </ul>

    <div class="bottom-ban"></div>
    <Footer></Footer>
  </div>
</template>

<script>
import Footer from "@/components/Footer.vue";
import { reactive, toRefs } from "vue";
import { useRoute, useRouter } from "vue-router";
import axios from "axios";
axios.defaults.baseURL = "http://localhost:8080/tijian/";
import { getSessionStorage, getCurDate } from "../common.js";

export default {
  setup() {
    const router = useRouter();
    const route = useRoute();

    const state = reactive({
      users: getSessionStorage("users"),
      ordersArr: [],
      curDate: getCurDate()
    });

    init();
    function init() {
      axios
        .post("orders/listOrdersByUserId", {
          userId: state.users.userId,
          state: 1
        })
        .then((response) => {
          state.ordersArr = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    }

    function cancel(orderId){
      if(!confirm('确定取消此次预约吗？')){
        return;
      }

      axios
        .post("orders/removeOrders", {
          orderId: orderId
        })
        .then((response) => {
          if(response.data==1){
            init();
          }else{
            alert('取消预约失败！');
          }
        })
        .catch((error) => {
          console.error(error);
        });
    }

    // 主要是想看看所预约的套餐的相关信息
    // 所以直接跳转回 confirmOrder 并带上相应的参数
    function toConfirmOrder(orders){
      router.push({path:'/confirmOrder',query:{hpId:orders.hpId,smId:orders.smId,selectDay:orders.orderDate}});
    }

    return {
      ...toRefs(state),
      cancel,
      toConfirmOrder
    };
  },
  components: { Footer },
};
</script>

<style scoped>
/*********************** 总容器 ***********************/
.wrapper {
  width: 100%;
  height: 100%;
  background-color: #f9f9f9;
}

/*********************** header ***********************/
header {
  width: 100%;
  height: 15.7vw;
  background-color: #fff;

  position: fixed;
  left: 0;
  top: 0;

  display: flex;
  align-items: center;
  justify-content: space-between;

  box-sizing: border-box;
  padding: 0 3.6vw;
}
header .fa {
  font-size: 8vw;
}

/*********************** common样式 ***********************/
.top-ban {
  width: 100%;
  height: 15.7vw;
}
.bottom-ban {
  width: 100%;
  height: 14.2vw;
}

/*********************** ul ***********************/
ul {
  width: 86vw;
  margin: 0 auto;
}
ul li {
  width: 100%;
  height: 14vw;
  border-bottom: solid 1px #eee;

  display: flex;
  justify-content: space-between;
  align-items: center;
}
ul li .left {
  user-select: none;
  cursor: pointer;
}
ul li .left p:first-child {
  color: #555;
  font-size: 3.3vw;
}
ul li .left p:last-child {
  color: #333;
  font-size: 4.2vw;
  font-weight: 600;
}
ul li .right {
  font-size: 4vw;
  color: #e6a23c;
}
</style>
```

#### 13、ReportList.vue

```vue
<template>
  <div class="wrapper">
        <header>
            <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
            <p>健康档案</p>
            <div></div>
        </header>
        <div class="top-ban"></div>

        <section>
            <img src="../assets/report.png">
            <ul>
                <li v-for="orders in ordersArr" :key="orders.orderId">
                    <div class="left">
                        <i class="fa fa-file-text-o"></i>
                        <div>
                            <p>{{convert(orders.orderDate)}} 体检报告</p>
                            <p>{{orders.hospital.name}}</p>
                        </div>
                    </div>
                    <div class="right" @click="toReport(orders.orderId)">
                        <i class="fa fa-angle-right"></i>
                    </div>
                </li>
            </ul>
        </section>
        
        <div class="bottom-ban"></div>
        <Footer></Footer>
    </div>
</template>

<script>
import Footer from "@/components/Footer.vue";
import { reactive, toRefs } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";
axios.defaults.baseURL = "http://localhost:8080/tijian/";
import { getSessionStorage } from "../common.js";

export default {
  setup() {
    const router = useRouter();

    const state = reactive({
      users: getSessionStorage('users'),
      ordersArr: []
    });

    init();
    function init(){
      axios
        .post("orders/listOrdersByUserId", {
          userId: state.users.userId,
          state: 1
        })
        .then((response) => {
          state.ordersArr = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    }

    function convert(str){
      let arr = str.split('-');
      return arr[0]+'年'+arr[1]+'月'+arr[2]+'日';
    }

    function toReport(orderId){
      router.push({path:'/report',query:{orderId:orderId}});
    }

    return {
      ...toRefs(state),
      convert,
      toReport
    };
  },
  components: { Footer },
};
</script>

<style scoped>
/*********************** 总容器 ***********************/
.wrapper{
    width: 100%;
    height: 100%;
    background-color: #F9F9F9;
}

/*********************** header ***********************/
header{
    width: 100%;
    height: 15.7vw;
    background-color: #FFF;

    position: fixed;
    left: 0;
    top: 0;

    display: flex;
    align-items: center;
    justify-content: space-between;

    box-sizing: border-box;
    padding: 0 3.6vw;
}
header .fa{
    font-size: 8vw;
}

/*********************** common样式 ***********************/
.top-ban{
    width: 100%;
    height: 15.7vw;
}
.bottom-ban{
    width: 100%;
    height: 14.2vw;
}

/*********************** section ***********************/
section{
    width: 100%;
}
section img{
    width: 100%;
    display: block;
}
section ul{
    width: 86vw;
    margin: 0 auto;
}
section ul li{
    width: 100%;
    height: 18vw;
    border-bottom: solid 1px #EEE;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
section ul li .left{
    display: flex;
    align-items: center;
}
section ul li .left i{
    font-size: 8vw;
    color: #6BB9B6;
    margin-right: 3vw;
}
section ul li .left p:first-child{
    color: #555;
    font-size: 4.2vw;
    font-weight: 600;
}
section ul li .left p:last-child{
    color: #999;
    font-size: 3.2vw;
    font-weight: 600;
    margin-top: 1vw;
}
section ul li .right{
    user-select: none;
    cursor: pointer;
}
section ul li .right i{
    color: #999;
    font-size: 6vw;
}
</style>
```

#### 14、Report.vue

```vue
<template>
  <div class="wrapper">
    <header>
      <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
      <p>{{ convert(orders.orderDate) }}体检报告</p>
      <div></div>
    </header>

    <nav>
      <div
        :class="{ active: navFlag == 'general' }"
        @click="navEvent('general')"
      >
        总检结论
      </div>
      <div
        :class="{ active: navFlag == 'detailed' }"
        @click="navEvent('detailed')"
      >
        报告详情
      </div>
    </nav>

    <div class="top-ban"></div>

    <div class="nav-content-item" v-if="navFlag == 'general'">
      <div class="item">
        <div class="title">异常项</div>
        <ul>
          <li v-for="eci in errorCheckItemArr" :key="eci.cidrId">
            <div class="indications">
              <div class="left">
                <div>异</div>
                <p>{{eci.name}}</p>
              </div>
              <div class="right">
                <p>{{eci.value}} {{eci.unit}}</p>
                <p>正常值范围：{{eci.normalValueString}}</p>
              </div>
            </div>
          </li>
        </ul>
      </div>
      <div class="item">
        <div class="title">一、尊敬的顾客，您本次体检结论如下：</div>
        <ul>
          <li
            class="conclusion-title"
            v-for="(or, index) in overallResultArr"
            :key="or.orId"
          >
            {{ index + 1 + "、" + or.title }}
          </li>
        </ul>
      </div>
      <div class="item">
        <div class="title">二、尊敬的顾客，您本次体检建议信息日下：</div>
        <ul>
          <li
            class="conclusion-content"
            v-for="(or, index) in overallResultArr"
            :key="or.orId"
          >
            <h3>{{ index + 1 + "、" + or.title }}</h3>
            <p>
              {{ or.content }}
            </p>
          </li>
        </ul>
      </div>
    </div>

    <div class="nav-content-item" v-if="navFlag == 'detailed'">
      <div class="item" v-for="ci in ciReportArr" :key="ci.cirId">
        <div class="title">{{ ci.ciName }}</div>
        <ul>
          <li v-for="cdr in ci.cidrList" :key="cdr.cidrId">
            <div class="indications" v-if="cdr.type!=4">
              <div class="left">
                <div v-if="cdr.isError==1">异</div>
                <p>{{cdr.name}}</p>
              </div>
              <div class="right">
                <p>{{cdr.value}} {{cdr.unit}}</p>
                <p>正常值范围：{{cdr.normalValueString}}</p>
              </div>
            </div>
            <div class="indications-type-4" v-if="cdr.type==4">
              <div>
                <p>{{cdr.name}}</p>
              </div>
              <div>
                <p>
                  {{cdr.value}}
                </p>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>

    <div class="bottom-ban"></div>
    <Footer></Footer>
  </div>
</template>

<script>
import Footer from "@/components/Footer.vue";
import { reactive, toRefs } from "vue";
import { useRoute, useRouter } from "vue-router";
import axios from "axios";
axios.defaults.baseURL = "http://localhost:8080/tijian/";
import { getSessionStorage } from "../common.js";

export default {
  setup() {
    const router = useRouter();
    const route = useRoute();

    const state = reactive({
      users: getSessionStorage("users"),
      navFlag: "general", // 用来标明页面，是总检结论，还是报告详情 
      orderId: route.query.orderId,
      orders: {},
      overallResultArr: [],
      ciReportArr: [],
      errorCheckItemArr: []
    });

    init();
    function init() {
      axios
        .post("orders/getOrdersById", {
          orderId: state.orderId,
        })
        .then((response) => {
          state.orders = response.data;
        })
        .catch((error) => {
          console.error(error);
        });

      axios
        .post("overallResult/listOverallResultByOrderId", {
          orderId: state.orderId,
        })
        .then((response) => {
          state.overallResultArr = response.data;
        })
        .catch((error) => {
          console.error(error);
        });

      axios
        .post("ciReport/listCiReport", {
          orderId: state.orderId,
        })
        .then((response) => {
          state.ciReportArr = response.data;
          for(let i=0;i<state.ciReportArr.length;i++){
              let cidrArr = state.ciReportArr[i].cidrList;
              for(let j=0;j<cidrArr.length;j++){
                  if(cidrArr[j].isError == 1){
                      state.errorCheckItemArr.push(cidrArr[j]);
                  }
              }
          }
        })
        .catch((error) => {
          console.error(error);
        });
    }

    function navEvent(str) {
      state.navFlag = str;
    }

    function convert(str) {
      let arr = (str + "").split("-");
      return arr[0] + "年" + arr[1] + "月" + arr[2] + "日";
    }

    return {
      ...toRefs(state),
      navEvent,
      convert,
    };
  },
  components: { Footer },
};
</script>

<style scoped>
/*********************** 总容器 ***********************/
.wrapper {
  width: 100%;
  height: 100%;
  background-color: #f9f9f9;
}

/*********************** header ***********************/
header {
  width: 100%;
  height: 15.7vw;
  background-color: #fff;

  position: fixed;
  left: 0;
  top: 0;

  display: flex;
  align-items: center;
  justify-content: space-between;

  box-sizing: border-box;
  padding: 0 3.6vw;
}
header .fa {
  font-size: 8vw;
}

/*********************** common样式 ***********************/
.top-ban {
  width: 100%;
  height: 31.7vw;
}
.bottom-ban {
  width: 100%;
  height: 14.2vw;
}

/*********************** nav ***********************/
nav {
  width: 100%;
  height: 16vw;
  display: flex;
  background-color: #f9f9f9;

  position: fixed;
  left: 0;
  top: 15.7vw;
}
nav div {
  flex: 1;
  height: 14vw;
  box-sizing: border-box;

  text-align: center;
  line-height: 14vw;
  font-size: 4.2vw;
  font-weight: 600;
  color: #555;
}

.active {
  border-bottom: solid 2px #137e92;
  color: #137e92;
}

/*********************** nav-content-item ***********************/
.nav-content-item .item {
  width: 92vw;
  margin: 0 auto;
  margin-top: 3vw;
  margin-bottom: 3vw;
  overflow: hidden;
  border-radius: 3vw;
  background-color: #fff;
}
.nav-content-item .item .title {
  width: 100%;
  height: 10vw;
  background-color: #7bafd7;
  line-height: 10vw;
  box-sizing: border-box;
  padding-left: 4vw;
  font-size: 4.2vw;
  color: #fff;
}

.nav-content-item .item ul {
  width: 100%;
}

/****** 数值型检查项样式 ******/
.nav-content-item .item ul li {
  border-bottom: solid 1px #eee;
}
.nav-content-item .item ul li:last-child {
  border-bottom: none;
}
.nav-content-item .item ul li .indications {
  width: 100%;
  height: 14vw;
  padding: 0 3vw;
  display: flex;
  align-items: center;
  font-size: 3.2vw;
  color: #333;
}

.nav-content-item .item ul li .indications .left {
  flex: 1;
  display: flex;
}
.nav-content-item .item ul li .indications .left div {
  background-color: #ba634e;
  color: #fff;
  padding: 0.2vw 0.6vw;
  border-radius: 0.6vw;
}
.nav-content-item .item ul li .indications .left p {
  font-weight: 600;
  margin-left: 2vw;
}
.nav-content-item .item ul li .indications .right {
  flex: 1;
}
.nav-content-item .item ul li .indications .right p:last-child {
  color: #999;
}
/****** 数值型检查项样式 ******/

/****** 描述型检查项样式 ******/
.nav-content-item .item ul li .indications-type-4 {
  width: 100%;
  box-sizing: border-box;
  padding: 0 3vw;

  font-size: 3.2vw;
  color: #333;
}
.nav-content-item .item ul li .indications-type-4 div {
  margin: 5vw 0;
}
.nav-content-item .item ul li .indications-type-4 div:first-child {
  font-weight: 600;
}
.nav-content-item .item ul li .indications-type-4 div:last-child {
  color: #999;
}
/****** 描述型检查项样式 ******/

.nav-content-item .item ul .conclusion-title {
  width: 100%;
  height: 12vw;
  box-sizing: border-box;
  border-bottom: solid 1px #eee;
  padding: 0 3vw;

  display: flex;
  align-items: center;
  font-size: 4vw;
  color: #555;
}
.nav-content-item .item ul .conclusion-title:last-child {
  border-bottom: none;
}

.nav-content-item .item ul .conclusion-content {
  width: 100%;
  box-sizing: border-box;
  border-bottom: solid 1px #eee;
  padding: 4vw 3vw;
  font-size: 3.6vw;
  color: #555;
}
.nav-content-item .item ul .conclusion-content:last-child {
  border-bottom: none;
}
.nav-content-item .item ul .conclusion-content h3 {
  font-size: 4vw;
  font-weight: 600;
  margin-bottom: 2vw;
}
.nav-content-item .item ul .conclusion-content p {
  text-indent: 8vw;
}
</style>
```



# Part III 东软体检报告管理系统



## 3.1.服务器端项目



### 3.1.1.服务器端项目搭建



#### 3.1.1.1.开发工具检查

1. 开发工具：SpringToolSuite4+（STS）
2. 检查开发工具的jdk配置：jdk8
3. 检查maven构建工具的配置：maven3+
4. 检查开发工具的文件编码配置：utf-8

#### 3.1.1.2.搭建SpringBoot工程

1. 工程类型：

   ![请添加图片描述](assets/server01.png)

   ![请添加图片描述](assets/server04.png)

2. pom.xml文件

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <project xmlns="http://maven.apache.org/POM/4.0.0"
   	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
   	<modelVersion>4.0.0</modelVersion>
   	<parent>
   		<groupId>org.springframework.boot</groupId>
   		<artifactId>spring-boot-starter-parent</artifactId>
   		<version>2.6.4</version>
   		<relativePath /> <!-- lookup parent from repository -->
   	</parent>
   	<groupId>com.neusoft</groupId>
   	<artifactId>tijiancmsserver</artifactId>
   	<version>0.0.1-SNAPSHOT</version>
   	<name>tijiancmsserver</name>
   	<description>Demo project for Spring Boot</description>
   	<properties>
   		<java.version>1.8</java.version>
   	</properties>
   	<dependencies>
   		<dependency>
   			<groupId>org.springframework.boot</groupId>
   			<artifactId>spring-boot-starter-web</artifactId>
   		</dependency>
   		<dependency>
   			<groupId>org.mybatis.spring.boot</groupId>
   			<artifactId>mybatis-spring-boot-starter</artifactId>
   			<version>2.2.2</version>
   		</dependency>

   		<dependency>
   			<groupId>org.springframework.boot</groupId>
   			<artifactId>spring-boot-devtools</artifactId>
   		</dependency>

   		<dependency>
   			<groupId>mysql</groupId>
   			<artifactId>mysql-connector-java</artifactId>
   			<version>5.1.49</version>
   		</dependency>

   		<dependency>
   			<groupId>org.springframework.boot</groupId>
   			<artifactId>spring-boot-starter-test</artifactId>
   			<scope>test</scope>
   		</dependency>
   	</dependencies>

   	<build>
   		<plugins>
   			<plugin>
   				<groupId>org.springframework.boot</groupId>
   				<artifactId>spring-boot-maven-plugin</artifactId>
   			</plugin>
   		</plugins>
   	</build>

   </project>
   ```

3. 工程目录结构

   ![请添加图片描述](assets/server05.png)

4. SpringBoot入口文件

   ```java
   package com.neusoft.tijiancms;

   import org.springframework.boot.SpringApplication;
   import org.springframework.boot.autoconfigure.SpringBootApplication;

   @SpringBootApplication
   public class TijiancmsserverApplication {

   	public static void main(String[] args) {
   		SpringApplication.run(TijiancmsserverApplication.class, args);
   	}

   }
   ```

5. 处理跨域的Cors配置文件

   ```java
   package com.neusoft.tijian;

   import org.springframework.context.annotation.Configuration;
   import org.springframework.web.servlet.config.annotation.CorsRegistry;
   import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

   @Configuration
   public class WebMvcConfig implements WebMvcConfigurer {
       @Override
       public void addCorsMappings(CorsRegistry registry) {
           /*
            * addMapping：配置可以被跨域的路径，可以任意配置，可以具体到直接请求路径。 
            * allowedMethods：允许的请求方式，如：POST、GET、PUT、DELETE等。
            * allowedOrigins：允许访问的url，可以固定单条或者多条内容
            * allowedHeaders：允许的请求header，可以自定义设置任意请求头信息。 
            * maxAge：配置预检请求的有效时间
            */
           registry.addMapping("/**")
                   .allowedOrigins("*")
                   .allowedMethods("*")
                   .allowedHeaders("*")
                   .maxAge(36000);
       }
   }
   ```

6. application.yml配置文件

   ```yaml
   server:
       port: 8088
       servlet:
           context-path: /tijiancms
           
   logging:
       level:
           org.springframework: debug
           com.neusoft.tijiancms.mapper: debug
           
   spring:
       datasource:
           driver-class-name: com.mysql.jdbc.Driver
           url: jdbc:mysql://localhost:3306/tijian?characterEncoding=utf-8&allowMultiQueries=true
           username: root
           password: 123
           
   mybatis:
       mapper-locations: classpath:mapper/*.xml 
       type-aliases-package: com.neusoft.tijiancms.po,com.neusoft.tijiancms.dto     
   ```



### 3.1.2.服务器接口API

共12个接口。

#### 3.1.2.1.setmeal

##### 1、setmeal/listSetmeal

- 参数：无

- 返回值：List（泛型：Setmeal）

- 功能：查询所有套餐类型列表

- 代码：

- 1、添加 PO 类 —— Setmeal

- ```java
  public class Setmeal {
  
  	private Integer smId;
  	private String name;
  	private Integer type;
  	private Integer price;
    
    // getter & setter
  }
  ```

- 2、添加 Mapper 类 —— SetmealMapper

- ```java
  @Mapper
  public interface SetmealMapper {
  
  	@Select("select * from setmeal order by smId")
  	public List<Setmeal> listSetmeal();
  }
  ```

- 3、添加 Service 类 —— SetmealService

- ```java
  public interface SetmealService {
  
  	public List<Setmeal> listSetmeal();
  }
  ```

  ```java
  @Service
  public class SetmealServiceImpl implements SetmealService{
  
  	@Autowired
  	private SetmealMapper setmealMapper;
  	
  	@Override
  	public List<Setmeal> listSetmeal() {
  		return setmealMapper.listSetmeal();
  	}
  }
  ```

- 4、添加 Controller 类 —— SetmealController

- ```java
  @RestController
  @RequestMapping("/setmeal")
  public class SetmealController {
  
  	@Autowired
  	private SetmealService setmealService;
  
  	@RequestMapping("/listSetmeal")
  	public List<Setmeal> listSetmeal() {
  		return setmealService.listSetmeal();
  	}
  }
  ```

  

#### 3.1.2.2.overallResult

##### 1、overallResult/listOverallResultByOrderId

- 参数：OverallResult对象

- 返回值：List（泛型：OverallResult）

- 功能：根据体检预约编号查询总检结论

- 代码：

- 1、添加 PO 类 —— OverallResult

- ```java
  public class OverallResult {
  
  	private Integer orId;
  	private String title;
  	private String content;
  	private Integer orderId;
  
  	// getter & setter
  }
  ```

- 2、添加 Mapper 类 —— OverallResultMapper

- ```java
  @Mapper
  public interface OverallResultMapper {
  
  	@Select("select * from overallResult where orderId=#{orderId} order by orId")
  	public List<OverallResult> listOverallResultByOrderId(Integer orderId);
  }
  ```

- 3、添加 Service 类 —— OverallResultService 

- ```java
  public interface OverallResultService {
  
  	public List<OverallResult> listOverallResultByOrderId(Integer orderId);
  }
  ```

  ```java
  @Service
  public class OverallResultServiceImpl implements OverallResultService{
  
  	@Autowired
  	private OverallResultMapper overallResultMapper;
  	
  	@Override
  	public List<OverallResult> listOverallResultByOrderId(Integer orderId) {
  		return overallResultMapper.listOverallResultByOrderId(orderId);
  	}
  }
  ```

- 4、添加 Controller 类 —— OverallResultController

- ```java
  @RestController
  @RequestMapping("/overallResult")
  public class OverallResultController {
  
  	@Autowired
  	private OverallResultService overallResultService;
  	
  	@RequestMapping("/listOverallResultByOrderId")
  	public List<OverallResult> listOverallResultByOrderId(@RequestBody OverallResult overallResult) {
  		return overallResultService.listOverallResultByOrderId(overallResult.getOrderId());
  	}
  }
  ```

  

##### 2、overallResult/saveOverallResult

- 参数：OverallResult对象
- 返回值：int
- 功能：添加总检结论信息
- 代码：
- 1、

##### 3、overallResult/updateOverallResult

- 参数：OverallResult对象
- 返回值：int
- 功能：更新总检结论信息

##### 4、overallResult/removeOverallResult

- 参数：OverallResult对象
- 返回值：int
- 功能：删除总检结论信息

#### 3.1.2.3.orders

##### 1、orders/listOrders

- 参数：OrdersPageRequestDto对象

- 返回值：OrdersPageResponseDto对象

- 功能：分页查询体检预约列表

- 代码：

- 1、添加 PO 类 —— Orders

- ==其中，在 Orders 表中指明了订单与用户、医院、套餐之间的多对一关系==。

- ```java
  public class Orders {
  
  	private Integer orderId;
  	private String orderDate;
  	private String userId;
  	private Integer hpId;
  	private Integer smId;
  	private Integer state;
  	//多对一
  	private Setmeal setmeal;
  	private Hospital hospital;
  	private Users users;
   
    // getter & setter
  }
  ```

- 2、添加 PO 类 —— Users

- ```java
  public class Users {
  
  	private String userId;
  	private String password;
  	private String realName;
  	private Integer sex;
  	private String identityCard;
  	private String birthday;
  	private Integer userType;
   
    // getter & setter
  }
  ```

- 3、添加 PO 类 —— Hospital

- ```java
  public class Hospital {
  
  	private Integer hpId;
  	private String name;
  	private String picture;
  	private String telephone;
  	private String address;
  	private String businessHours;
  	private String deadline;
  	private String rule;
  	private Integer state;
    
    // getter & setter
  }
  ```

- 4、添加 Mapper 类 —— OrdersMapper

- 当初始的时候，可以只将「为归档」的查出来，但如果是在边栏表单中加了其他参数查询的话，所以「业务条件」有多个需要向服务器提交，如果没有这样的实体类时，这需要添加 DTO 类。

- ```java
  @Mapper
  public interface OrdersMapper {
  
  	// 根据条件查询预约订单总行数
  	public int getOrdersCount(OrdersPageRequestDto request);
    
  	// 根据条件做预约订单的分页查询
  	public List<Orders> listOrders(OrdersPageRequestDto request);
  	
  	public Orders getOrdersById(Integer orderId);
  	
  	@Update("update orders set state=#{state} where orderId=#{orderId}")
  	public int updateOrdersState(Orders orders);
  }
  ```

- 5、添加 DTO 类 —— OrdersPageRequestDto 

- 封装分页请求参数

- ```java
  public class OrdersPageRequestDto {
    
  	//业务查询条件的请求参数
  	private String userId;
  	private String realName;
  	private Integer sex;
  	private Integer smId;
  	private String orderDate;
  	private Integer state;
  	
  	//分页的请求参数
  	private Integer pageNum;      //当前页
  	private Integer maxPageNum;   //每页显示最大行数
  	private Integer beginNum;     //从第几行开始查询
    
    // getter & setter
  }
  ```

- 6、添加 DTO 类 —— OrdersPageResponseDto

- 封装分页查询的响应数据

- ```java
  public class OrdersPageResponseDto {
  
  	private Integer totalRow;        //总行数
  	private Integer totalPageNum;    //总页数
  	private Integer preNum;          //上一页
  	private Integer nextNum;         //下一页
  	private Integer pageNum;         //当前页
  	private Integer maxPageNum;      //每页显示最大行数
  	private Integer beginNum;        //开始查询记录数
  	
  	private List<Orders> list;       //业务数据
  
    // getter & setter
  }
  ```

- 7、添加 OrdersMapper.xml 映射文件

- ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
      "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <mapper namespace="com.neusoft.tijiancms.mapper.OrdersMapper">
  </mapper>    
  ```

- 8、编辑 Mapper 类 —— SetmealMapper

- ```java
  @Mapper
  public interface SetmealMapper {
  
  	@Select("select * from setmeal order by smId")
  	public List<Setmeal> listSetmeal();
  	
  	// 这是专门为OrdersMapper中的listOrders方法做关联查询用的
  	@Select("select * from setmeal where smId=#{smId}")
  	public Setmeal getSetmealByIdByMapper(Integer smId);
  }
  ```

- 9、编辑 Mapper 类 —— HospitalMapper

- ```java
  @Mapper
  public interface HospitalMapper {
  
  	@Select("select * from hospital where hpId=#{hpId}")
  	public Hospital getHospitalById(Integer hpId);
  }
  ```

- 10、编辑 Mapper 类 —— UsersMapper

- ```java
  @Mapper
  public interface UsersMapper {
  
  	@Select("select * from users where userId=#{userId}")
  	public Users getUsersById(String userId);
  }
  ```

- 11、编辑 OrdersMapper.xml 映射文件

- 左连接 left join：返回包括👈左表中的所有记录，以及中联结字段相等的记录；

- 右连接 right join：返回包括右表👉中的所有记录，以及👈左表中联结字段相等的记录；

- 等值连接 inner join：只返回两个表中联结字段相等的记录。

- ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
      "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <mapper namespace="com.neusoft.tijiancms.mapper.OrdersMapper">
  
  	<resultMap type="Orders" id="ordersResultMap">
          <id column="orderId" property="orderId"/>
          <result column="orderDate" property="orderDate"/>
          <result column="userId" property="userId"/>
          <result column="hpId" property="hpId"/>
          <result column="smId" property="smId"/>
          <result column="state" property="state"/>
          <association property="setmeal" javaType="Setmeal"
              select="com.neusoft.tijiancms.mapper.SetmealMapper.getSetmealByIdByMapper" column="smId"></association>
          <association property="hospital" javaType="Hospital"
              select="com.neusoft.tijiancms.mapper.HospitalMapper.getHospitalById" column="hpId"></association>
          <association property="users" javaType="Users"
              select="com.neusoft.tijiancms.mapper.UsersMapper.getUsersById" column="userId"></association>        
      </resultMap>
  	
  	<select id="getOrdersCount" parameterType="OrdersPageRequestDto" resultType="int">
  	    select count(*)
  	    from orders o left join users u
  	    on o.userId = u.userId
  	    <where>
  	        <if test="userId!=null and userId!=''">
  	            and u.userId = #{userId}
  	        </if>
  	        <if test="realName!=null and realName!=''">
  	            and u.realName like concat('%',#{realName},'%')
  	        </if>
  	        <if test="sex!=null">
  	            and u.sex = #{sex}
  	        </if>
  	        <if test="smId!=null">
  	            and o.smId = #{smId}
  	        </if>
  	        <if test="orderDate!=null and orderDate!=''">
  	            and o.orderDate = #{orderDate}
  	        </if>
  	        <if test="state!=null">
  	            and o.state = #{state}
  	        </if>
  	    </where>
  	</select>
  	
  	<select id="listOrders" parameterType="OrdersPageRequestDto" resultMap="ordersResultMap">
  	    select o.*
  	    from orders o left join users u
  	    on o.userId=u.userId
  	    <where>
  	        <if test="userId!=null and userId!=''">
  	            and u.userId = #{userId}
  	        </if>
  	        <if test="realName!=null and realName!=''">
  	            and u.realName like concat('%',#{realName},'%')
  	        </if>
  	        <if test="sex!=null">
  	            and u.sex = #{sex}
  	        </if>
  	        <if test="smId!=null">
  	            and o.smId = #{smId}
  	        </if>
  	        <if test="orderDate!=null and orderDate!=''">
  	            and o.orderDate = #{orderDate}
  	        </if>
  	        <if test="state!=null">
  	            and o.state = #{state}
  	        </if>
  	    </where>
  	    order by o.orderId
  	    limit #{beginNum},#{maxPageNum}
  	</select>
  	
  	<select id="getOrdersById" parameterType="int" resultMap="ordersResultMap">
  	    select * from orders where orderId=#{orderId}
  	</select>
  </mapper>    
  ```

- 12、添加 Service 类 —— OrdersService 

- ```java
  public interface OrdersService {
  
  	public OrdersPageResponseDto listOrders(OrdersPageRequestDto request);
  }
  ```

  ```java
  @Service
  public class OrdersServiceImpl implements OrdersService{
  
  	@Autowired
  	private OrdersMapper ordersMapper;
  	
  	@Override
  	public OrdersPageResponseDto listOrders(OrdersPageRequestDto request) {
  		OrdersPageResponseDto response = new OrdersPageResponseDto();
  		
  		//获取总行数
  		int totalRow = ordersMapper.getOrdersCount(request);
  		response.setTotalRow(totalRow);
  		
  		//如果总行数为0，那么直接返回
  		if(totalRow == 0) {
  			return response;
  		}
  		
  		//计算总页数
  		int totalPageNum = 0;
  		if(totalRow%request.getMaxPageNum()==0) {
  			totalPageNum = totalRow/request.getMaxPageNum();
  		}else {
  			totalPageNum = totalRow/request.getMaxPageNum()+1;
  		}
  		response.setTotalPageNum(totalPageNum);
  		
  		//计算上一页和下一页
  		int pageNum = request.getPageNum();
  		if(pageNum>1) {
  			response.setPreNum(pageNum-1);
  		}
  		if(pageNum<totalPageNum) {
  			response.setNextNum(pageNum+1);
  		}
  		
  		//计算开始查询记录数
  		request.setBeginNum((pageNum-1)*request.getMaxPageNum());
  		//查询业务数据
  		List<Orders> list = ordersMapper.listOrders(request);
  		
  		//给返回值填充余下的数据
  		response.setPageNum(pageNum);
  		response.setMaxPageNum(request.getMaxPageNum());
  		response.setList(list);
  		
  		return response;
  	}
  }
  ```

  

- 13、添加 Controller 类 —— OrdersController

- ```java
  @RestController
  @RequestMapping("/orders")
  public class OrdersController {
  
  	@Autowired
  	private OrdersService ordersService;
  
  	@RequestMapping("/listOrders")
  	public OrdersPageResponseDto listOrders(@RequestBody OrdersPageRequestDto request) {
  		return ordersService.listOrders(request);
  	}
  }
  ```

  

##### 2、orders/getOrdersById

- 参数：Orders对象

- 返回值：Orders对象

- 功能：根据体检预约编号查询体检预约信息

- 代码：

- 1、编辑 Mapper 类 —— OrdersMapper

- ```java
  @Mapper
  public interface OrdersMapper {
  
  	//根据条件查询预约订单总行数
  	public int getOrdersCount(OrdersPageRequestDto request);
  	//根据条件做预约订单的分页查询
  	public List<Orders> listOrders(OrdersPageRequestDto request);
  	
  	public Orders getOrdersById(Integer orderId);
  }
  ```

- 2、编辑 OrdersMapper.xml 映射文件

- ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
      "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <mapper namespace="com.neusoft.tijiancms.mapper.OrdersMapper">
  
  	<resultMap type="Orders" id="ordersResultMap">
          <id column="orderId" property="orderId"/>
          <result column="orderDate" property="orderDate"/>
          <result column="userId" property="userId"/>
          <result column="hpId" property="hpId"/>
          <result column="smId" property="smId"/>
          <result column="state" property="state"/>
          <association property="setmeal" javaType="Setmeal"
              select="com.neusoft.tijiancms.mapper.SetmealMapper.getSetmealByIdByMapper" column="smId"></association>
          <association property="hospital" javaType="Hospital"
              select="com.neusoft.tijiancms.mapper.HospitalMapper.getHospitalById" column="hpId"></association>
          <association property="users" javaType="Users"
              select="com.neusoft.tijiancms.mapper.UsersMapper.getUsersById" column="userId"></association>        
      </resultMap>
  	
  	<select id="getOrdersCount" parameterType="OrdersPageRequestDto" resultType="int">
  	    select count(*)
  	    from orders o left join users u
  	    on o.userId=u.userId
  	    <where>
  	        <if test="userId!=null and userId!=''">
  	            and u.userId = #{userId}
  	        </if>
  	        <if test="realName!=null and realName!=''">
  	            and u.realName like concat('%',#{realName},'%')
  	        </if>
  	        <if test="sex!=null">
  	            and u.sex = #{sex}
  	        </if>
  	        <if test="smId!=null">
  	            and o.smId = #{smId}
  	        </if>
  	        <if test="orderDate!=null and orderDate!=''">
  	            and o.orderDate = #{orderDate}
  	        </if>
  	        <if test="state!=null">
  	            and o.state = #{state}
  	        </if>
  	    </where>
  	</select>
  	
  	<select id="listOrders" parameterType="OrdersPageRequestDto" resultMap="ordersResultMap">
  	    select o.*
  	    from orders o left join users u
  	    on o.userId=u.userId
  	    <where>
  	        <if test="userId!=null and userId!=''">
  	            and u.userId = #{userId}
  	        </if>
  	        <if test="realName!=null and realName!=''">
  	            and u.realName like concat('%',#{realName},'%')
  	        </if>
  	        <if test="sex!=null">
  	            and u.sex = #{sex}
  	        </if>
  	        <if test="smId!=null">
  	            and o.smId = #{smId}
  	        </if>
  	        <if test="orderDate!=null and orderDate!=''">
  	            and o.orderDate = #{orderDate}
  	        </if>
  	        <if test="state!=null">
  	            and o.state = #{state}
  	        </if>
  	    </where>
  	    order by o.orderId
  	    limit #{beginNum},#{maxPageNum}
  	</select>
  	
  	<select id="getOrdersById" parameterType="int" resultMap="ordersResultMap">
  	    select * from orders where orderId=#{orderId}
  	</select>
  </mapper>    
  ```

- 3、编辑 Service 类 —— OrdersService

- ```java
  public interface OrdersService {
  
  	public OrdersPageResponseDto listOrders(OrdersPageRequestDto request);
  	public Orders getOrdersById(Integer orderId);
  }
  ```

- 4、编辑 Controller 类 —— OrdersController

- ```java
  	@RequestMapping("/getOrdersById")
  	public Orders getOrdersById(@RequestBody Orders orders) {
  		return ordersService.getOrdersById(orders.getOrderId());
  	}
  ```
  
  

##### 3、orders/updateOrdersState

- 参数：Orders对象
- 返回值：int
- 功能：根据体检预约编号更新状态

#### 3.1.2.4.doctor

##### 1、doctor/getDoctorByCodeByPass

- 参数：Doctor对象

- 返回值：Doctor对象

- 功能：根据医生编码和密码进行登录

- 代码：

- 1、添加 PO 类 —— Doctor

- ```java
  public class Doctor {
  
  	private Integer docId;
  	private String docCode;
  	private String realName;
  	private String password;
  	private Integer sex;
  	private Integer deptno;
    
    // getter & setter
  }
  ```

- 2、添加 Mapper 类 —— DoctorMapper 

- ```java
  @Mapper
  public interface DoctorMapper {
  
  	@Select("select * from doctor where docCode=#{docCode} and password=#{password}")
  	public Doctor getDoctorByCodeByPass(Doctor doctor);
  }
  ```

- 3、添加 Service 类 —— DoctorService

- ```java
  public interface DoctorService {
  
  	public Doctor getDoctorByCodeByPass(Doctor doctor);
  }
  ```

  ```java
  @Service
  public class DoctorServiceImpl implements DoctorService{
  
  	@Autowired
  	private DoctorMapper doctorMapper;
  	
  	@Override
  	public Doctor getDoctorByCodeByPass(Doctor doctor) {
  		return doctorMapper.getDoctorByCodeByPass(doctor);
  	}
  }
  ```

- 4、添加 Controller 类 —— DoctorController

- ```java
  @RestController
  @RequestMapping("/doctor")
  public class DoctorController {
  	
  	@Autowired
  	private DoctorService doctorService;
  
  	@RequestMapping("/getDoctorByCodeByPass")
  	public Doctor getDoctorByCodeByPass(@RequestBody Doctor doctor) {
  		return doctorService.getDoctorByCodeByPass(doctor);
  	}
  }
  ```

  

#### 3.1.2.5.ciReport

##### 1、ciReport/createReportTemplate

- 参数：Orders对象

- 返回值：int

- 功能：根据预约订单信息创建体检报告模板

- 分析：此时会涉及 6 张表，分别是 setmeal、setmealdetailed、checkitem、checkitemdetailed、cireport、citailedreport。当前体检预约订单对应某个体检套餐，再关联体检套餐明细。进而得到有哪些检查项，再关联检查项目明细表获去细节点，然后把检查项插入到体检报告表中，检查项明细插入到体检报告明细表中。

- 代码：

- 1、添加 PO 类 —— Setmeal

- ```java
  public class Setmeal {
  
  	private Integer smId;
  	private String name;
  	private Integer type;
  	private Integer price;
  	//一对多
  	private List<SetmealDetailed> sdList;
    
    // getter & setter
  }
  ```

- 2、添加 PO 类 —— SetmealDetailed

- ```java
  public class SetmealDetailed {
  
  	private Integer sdId;
  	private Integer smId;
  	private Integer ciId;
  	//多对一
  	private CheckItem checkItem;
    
    // getter & setter
  }
  ```

- 3、添加 PO 类 —— CheckItem

- ```java
  public class CheckItem {
  
  	private Integer ciId;
  	private String ciName;
  	private String ciContent;
  	private String meaning;
  	private String remarks;
  	//一对多
  	private List<CheckItemDetailed> cdList;
    
    // getter & setter
  }
  ```

- 4、添加 PO 类 —— CheckItemDetailed

- ```java
  public class CheckItemDetailed {
  
  	private Integer cdId;
  	private String name;
  	private String unit;
  	private double minrange;
  	private double maxrange;
  	private String normalValue;
  	private String normalValueString;
  	private Integer type;
  	private Integer ciId;
  	private String remarks;
    
    // getter & setter
  }
  ```

- 5、添加 PO 类 —— CiReport

- ```java
  public class CiReport {
  
  	private Integer cirId;
  	private Integer ciId;
  	private String ciName;
  	private Integer orderId;
  	//一对多
  	private List<CiDetailedReport> cidrList;
    
    // getter & setter
  }
  ```

- 6、添加 PO 类 —— CiDetailedReport

- ```java
  public class CiDetailedReport {
  
  	private Integer cidrId;              //检查项明细报告主键
  	private String name;                 //检查项明细名称
  	private String unit;                 //检查项明细单位
  	private double minrange;             //检查项细明正常值范围中的最小值
  	private double maxrange;             //检查项细明正常值范围中的最大值
  	private String normalValue;          //检查项细明正常值（非数字型）
  	private String normalValueString;    //检查项验证范围说明文字
  	private Integer type;                //检查项明细类型（1：数值围范验证型；2：数值相等验证型；3：无需验证型；4：描述型；5：其它）
  	private String value;                //检查项目明细值
  	private Integer isError;             //此项是否异常（0：无异常；1：异常）
  	private Integer ciId;                //所属检查项报告编号
  	private Integer orderId;             //所属预约编号
   
    // getter & setter
  }
  ```

- 7、添加 Service 类 —— CiReportService

- 思路：

- 1）检查当前预约编号对应的体检报告是否已经生成？可以编辑，否则只查看

- 2）查询报告模板（从CheckItem、CheckItemDetailed查询检查项和检查项明细）

- 3）根据查询出的信息，先向CiReport中插入报告检查项模板

- 4）根据查询出的信息，向CiDetailedReport中插入报告检查项明细模板

- ```java
  public interface CiReportService {
  
  	public int createReportTemplate(Orders orders);
    
  }
  ```

  ```java
  @Service
  public class CiReportServiceImpl implements CiReportService{
  
  	@Autowired
  	private CiReportMapper ciReportMapper;
  	@Autowired
  	private SetmealMapper setmealMapper;
  	@Autowired
  	private CiDetailedReportMapper ciDetailedReportMapper;
  	
  	@Transactional
  	@Override
  	public int createReportTemplate(Orders orders) {
  		//1、检查当前预约编号对应的体检报告是否已经生成
  		int count = ciReportMapper.getCiReportByOrderId(orders.getOrderId());
  		if(count>0) {
  			return 1;
  		}
  		
  		//2、查询报告模板（从CheckItem、CheckItemDetailed查询检查项和检查项明细）
  		Setmeal setmeal = setmealMapper.getSetmealById(orders.getSmId());
  		
  		//3、根据查询出的信息，先向CiReport中插入报告检查项模板
  		List<CiReport> cirList = new ArrayList<>();
  		for(SetmealDetailed sd : setmeal.getSdList()) {
  			CiReport cir = new CiReport();
  			cir.setCiId(sd.getCheckItem().getCiId());
  			cir.setCiName(sd.getCheckItem().getCiName());
  			cir.setOrderId(orders.getOrderId());
  			cirList.add(cir);
  		}
  		int result1 = ciReportMapper.saveCiReport(cirList);
  		
  		//4、根据查询出的信息，向CiDetailedReport中插入报告检查项明细模板
  		List<CiDetailedReport> cidrList = new ArrayList<>();
  		for(SetmealDetailed sd : setmeal.getSdList()) {
  			for(CheckItemDetailed cid : sd.getCheckItem().getCdList()) {
  				CiDetailedReport cidr = new CiDetailedReport();
  				cidr.setName(cid.getName());
  				cidr.setUnit(cid.getUnit());
  				cidr.setMinrange(cid.getMinrange());
  				cidr.setMaxrange(cid.getMaxrange());
  				cidr.setNormalValue(cid.getNormalValue());
  				cidr.setNormalValueString(cid.getNormalValueString());
  				cidr.setType(cid.getType());
  				cidr.setValue("");
  				cidr.setIsError(0);
  				cidr.setCiId(sd.getCiId());
  				cidr.setOrderId(orders.getOrderId());
  				cidrList.add(cidr);
  			}
  		}
  		int result2 = ciDetailedReportMapper.saveCiDetailedReport(cidrList);
  		
  		return result1 > 0 && result2 > 0 ? 1 : 0;
  	}
  }
  ```

- 8、添加 Mapper 类 —— CiReportMapper

- 被 步骤7 中的第一小步中调用

- ```java
  @Mapper
  public interface CiReportMapper {
  
  	@Select("select count(*) from ciReport where orderId=#{orderId}")
  	public int getCiReportByOrderId(Integer orderId);
  }
  ```

- 9、编辑 Mapper 类 —— SetmealMapper

- 提供 getSetmealById() 方法

- ```java
  @Mapper
  public interface SetmealMapper {
  
  	@Select("select * from setmeal order by smId")
  	public List<Setmeal> listSetmeal();
  	
  	//这是专门为OrdersMapper中的listOrders方法做关联查询用的
  	@Select("select * from setmeal where smId=#{smId}")
  	public Setmeal getSetmealByIdByMapper(Integer smId);
  	
  	public Setmeal getSetmealById(Integer smId);
  }
  ```

- 10、添加SetmealMapper.xml 映射文件

- ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
      "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <mapper namespace="com.neusoft.tijiancms.mapper.SetmealMapper">
  
  	<resultMap type="Setmeal" id="setmealResultMap">
          <id column="smId" property="smId"/>
          <result column="name" property="name"/>
          <result column="type" property="type"/>
          <result column="price" property="price"/>
          <collection property="sdList" ofType="SetmealDetailed"
              select="com.neusoft.tijiancms.mapper.SetmealDetailedMapper.listSetmealDetailedBySmId" column="smId"></collection>
      </resultMap>
  	
  	<select id="getSetmealById" parameterType="int" resultMap="setmealResultMap">
  	    select * from setmeal where smId=#{smId}
  	</select>
  	
  </mapper>    
  ```

  

- 11、添加 Mapper 类 —— SetmealDetailedMapper

- ```java
  @Mapper
  public interface SetmealDetailedMapper {
  
  	public List<SetmealDetailed> listSetmealDetailedBySmId(Integer smId);
  }
  ```

- 12、添加 SetmealDetailedMapper.xml 映射文件

- ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
      "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <mapper namespace="com.neusoft.tijiancms.mapper.SetmealDetailedMapper">
  
  	<resultMap type="SetmealDetailed" id="setmealDetailedResultMap">
          <id column="sdId" property="sdId"/>
          <result column="smId" property="smId"/>
          <result column="ciId" property="ciId"/>
          <association property="checkItem" javaType="CheckItem"
              select="com.neusoft.tijiancms.mapper.CheckItemMapper.getCheckItemById" column="ciId"></association>
      </resultMap>
  	
  	<select id="listSetmealDetailedBySmId" parameterType="int" resultMap="setmealDetailedResultMap">
  	    select * from setmealDetailed where smId=#{smId} order by sdId
  	</select>
  	
  </mapper>    
  ```

  13、添加 Mapper 类 —— CheckItemMapper

  ```java
  @Mapper
  public interface CheckItemMapper {
  
  	public CheckItem getCheckItemById(Integer ciId);
  }
  ```

- 14、添加 CheckItemMapper.xml 映射文件

- ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
      "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <mapper namespace="com.neusoft.tijiancms.mapper.CheckItemMapper">
  
  	<resultMap type="CheckItem" id="checkItemResultMap">
          <id column="ciId" property="ciId"/>
          <result column="ciName" property="ciName"/>
          <result column="ciContent" property="ciContent"/>
          <result column="meaning" property="meaning"/>
          <result column="remarks" property="remarks"/>
          <collection property="cdList" ofType="CheckItemDetailed"
              select="com.neusoft.tijiancms.mapper.CheckItemDetailedMapper.listCheckItemDetailed" column="ciId"></collection>
      </resultMap>
  	
  	<select id="getCheckItemById" parameterType="int" resultMap="checkItemResultMap">
  	    select * from checkItem where ciId=#{ciId}
  	</select>
  	
  </mapper>    
  ```

- 15、添加 Mapper 类 —— CheckItemDetailedMapper

- ```java
  @Mapper
  public interface CheckItemDetailedMapper {
  
  	@Select("select * from checkItemDetailed where ciId=#{ciId} order by cdId")
  	public List<CheckItemDetailed> listCheckItemDetailed(Integer ciId);
  }
  ```

- 16、编辑 Mapper 类 —— CiReportMapper

- 提供 saveCiReport() 方法

- ```java
  @Mapper
  public interface CiReportMapper {
  
  	@Select("select count(*) from ciReport where orderId=#{orderId}")
  	public int getCiReportByOrderId(Integer orderId);
  	
  	public int saveCiReport(List<CiReport> list);
  }
  ```

- 17、添加 CiReportMapper.xml 映射文件

- ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
      "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <mapper namespace="com.neusoft.tijiancms.mapper.CiReportMapper">
  
      <insert id="saveCiReport" parameterType="CiReport">
          insert into ciReport(ciId,ciName,orderId) values
          <foreach collection="list" item="cir" separator=",">
              (#{cir.ciId},#{cir.ciName},#{cir.orderId})
          </foreach>
      </insert>
  
  </mapper>    
  ```

- 18、编辑 Mapper 类 —— CiDetailedReportMapper

- ```java
  @Mapper
  public interface CiDetailedReportMapper {
  
  	public int saveCiDetailedReport(List<CiDetailedReport> list);
  }
  ```

- 19、添加 CiDetailedReportMapper.xml 映射文件

- ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
      "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <mapper namespace="com.neusoft.tijiancms.mapper.CiDetailedReportMapper">
  
      <insert id="saveCiDetailedReport" parameterType="CiDetailedReport">
          insert into ciDetailedReport(
          name,
          unit,
          minrange,
          maxrange,
          normalValue,
          normalValueString,
          type,
          value,
          isError,
          ciId,
          orderId
          ) values
          <foreach collection="list" item="cidr" separator=",">
              (
              #{cidr.name},
              #{cidr.unit},
              #{cidr.minrange},
              #{cidr.maxrange},
              #{cidr.normalValue},
              #{cidr.normalValueString},
              #{cidr.type},
              #{cidr.value},
              #{cidr.isError},
              #{cidr.ciId},
              #{cidr.orderId}
              )
          </foreach>
      </insert>
      
      <update id="updateCiDetailedReport" parameterType="java.util.List">
          <foreach collection="list" item="cidr" separator=";">
              update ciDetailedReport set value=#{cidr.value},isError=#{cidr.isError} where cidrId=#{cidr.cidrId}
          </foreach>
      </update>
  	
  </mapper>    
  ```

- 20、添加 Controller 类 —— CiReportController

- ```java
  @RestController
  @RequestMapping("/ciReport")
  public class CiReportController {
  
  	@Autowired
  	private CiReportService ciReportService;
  
  	@RequestMapping("/createReportTemplate")
  	public int createReportTemplate(@RequestBody Orders orders) {
  		return ciReportService.createReportTemplate(orders);
  	}
  }
  ```

  

##### 2、ciReport/listCiReport

- 参数：CiReport对象

- 返回值：List（泛型：CiReport）

- 功能：根据体检预约编号查询检查项列表及明细信息

- 代码：

- 1、编辑 Service 类 —— CiReportService

- 提供 listCiReport() 方法，思路如下：

- 1）先查询CiReport表，获取体检报告中的检查项

- 2）根据上面查询获取的检查项，再查询检查项明细（CiDetailedReport）

- ```java
  public interface CiReportService {
  
  	public int createReportTemplate(Orders orders);
  	public List<CiReport> listCiReport(Integer orderId);
  }
  ```

  ```java
  @Service
  public class CiReportServiceImpl implements CiReportService{
  
  	@Autowired
  	private CiReportMapper ciReportMapper;
  	@Autowired
  	private SetmealMapper setmealMapper;
  	@Autowired
  	private CiDetailedReportMapper ciDetailedReportMapper;
  	
  	@Transactional
  	@Override
  	public int createReportTemplate(Orders orders) {
  		//1、检查当前预约编号对应的体检报告是否已经生成
  		int count = ciReportMapper.getCiReportByOrderId(orders.getOrderId());
  		if(count>0) {
  			return 1;
  		}
  		
  		//2、查询报告模板（从CheckItem、CheckItemDetailed查询检查项和检查项明细）
  		Setmeal setmeal = setmealMapper.getSetmealById(orders.getSmId());
  		
  		//3、根据查询出的信息，先向CiReport中插入报告检查项模板
  		List<CiReport> cirList = new ArrayList<>();
  		for(SetmealDetailed sd : setmeal.getSdList()) {
  			CiReport cir = new CiReport();
  			cir.setCiId(sd.getCheckItem().getCiId());
  			cir.setCiName(sd.getCheckItem().getCiName());
  			cir.setOrderId(orders.getOrderId());
  			cirList.add(cir);
  		}
  		int result1 = ciReportMapper.saveCiReport(cirList);
  		
  		//4、根据查询出的信息，向CiDetailedReport中插入报告检查项明细模板
  		List<CiDetailedReport> cidrList = new ArrayList<>();
  		for(SetmealDetailed sd : setmeal.getSdList()) {
  			for(CheckItemDetailed cid : sd.getCheckItem().getCdList()) {
  				CiDetailedReport cidr = new CiDetailedReport();
  				cidr.setName(cid.getName());
  				cidr.setUnit(cid.getUnit());
  				cidr.setMinrange(cid.getMinrange());
  				cidr.setMaxrange(cid.getMaxrange());
  				cidr.setNormalValue(cid.getNormalValue());
  				cidr.setNormalValueString(cid.getNormalValueString());
  				cidr.setType(cid.getType());
  				cidr.setValue("");
  				cidr.setIsError(0);
  				cidr.setCiId(sd.getCiId());
  				cidr.setOrderId(orders.getOrderId());
  				cidrList.add(cidr);
  			}
  		}
  		int result2 = ciDetailedReportMapper.saveCiDetailedReport(cidrList);
  		
  		return result1>0&&result2>0?1:0;
  	}
  	
  	@Override
  	public List<CiReport> listCiReport(Integer orderId) {
      // 先要知道你要填写多少数据，然后再获取数据，依次填写
      
  		// 1、先查询 CiReport 表，获取体检报告中的检查项
  		List<CiReport> cirList = ciReportMapper.listCiReport(orderId);
  		
  		// 2、根据上面查询获取的检查项，再查询检查项明细（CiDetailedReport）
  		for(CiReport cir : cirList) {
  			CiDetailedReport param = new CiDetailedReport();
  			param.setOrderId(orderId);
  			param.setCiId(cir.getCiId());
  			List<CiDetailedReport> list = ciDetailedReportMapper.listCiDetailedReportByOrderIdByCiId(param);
  			cir.setCidrList(list);
  		}
  		
  		return cirList;
  	}
  }
  ```

  
  
- 2、编辑 Mapper 类 —— CiReportMapper

- ```java
  @Mapper
  public interface CiReportMapper {
  
  	@Select("select count(*) from ciReport where orderId=#{orderId}")
  	public int getCiReportByOrderId(Integer orderId);
  	
  	public int saveCiReport(List<CiReport> list);
  	
  	@Select("select * from ciReport where orderId=#{orderId}")
  	public List<CiReport> listCiReport(Integer orderId);
  }
  ```

- 3、编辑 Mapper 类 —— CiDetailedReportMapper

- 实现 listCiDetailedReportByOrderIdByCiId() 方法

- ```java
  @Mapper
  public interface CiDetailedReportMapper {
  
  	public int saveCiDetailedReport(List<CiDetailedReport> list);
  	
  	@Select("select * from ciDetailedReport where orderId=#{orderId} and ciId=#{ciId} order by cidrId")
  	public List<CiDetailedReport> listCiDetailedReportByOrderIdByCiId(CiDetailedReport ciDetailedReport);
  }
  ```

- 4、编辑 Controller 类 —— CiReportController

- ```java
  @RestController
  @RequestMapping("/ciReport")
  public class CiReportController {
  
  	@Autowired
  	private CiReportService ciReportService;
  
  	@RequestMapping("/createReportTemplate")
  	public int createReportTemplate(@RequestBody Orders orders) {
  		return ciReportService.createReportTemplate(orders);
  	}
  	
  	@RequestMapping("/listCiReport")
  	public List<CiReport> listCiReport(@RequestBody CiReport ciReport) {
  		return ciReportService.listCiReport(ciReport.getOrderId());
  	}
  }
  ```

  

#### 3.1.2.6.ciDetailedReport

##### 1、ciDetailedReport/updateCiDetailedReport

- 参数：List（泛型：CiDetailedReport）
- 返回值：int
- 功能：更新检查项信息及检查项明细信息



### 3.1.3.服务器端代码

参考项目源代码。



## 3.2.前端项目



### 3.2.1.前端项目搭建



#### 3.2.1.1.开发工具检查

1. 检查vscode是否安装成功。
2. 检查npm安装环境： 命令行下输入：npm -v
3. 检查VueCli安装环境：命令行下输入：vue -V   （注意：本工程使用VueCli5.0.1版本）

> 附录：
>
> 1. 安装npm：直接安装node.js  （输入 "npm -v" 测试是否安装成功； 输入 node –v 查看node版本）
> 2. 全局安装vuecli：npm install -g @vue/cli    （或：npm install -g @vue/cli@5.0.1）
> 3. 查看当前安装的vue-cli版本：vue  --version   或   vue –V
> 4. 卸载旧版本的vue-cli：npm uninstall vue-cli -g
> 5. 查看远程仓库中的版本号：npm view @vue/cli versions --json

#### 3.2.1.2.搭建VueCli工程

1. 命令行下进入工作空间目录中，输入： vue create tijianfront  （工程名必须小写）
2. 选择预设模板：这里选择“Manually select features”（手动选择特征）
3. 模块选取：Babel、Router
4. 选择是否使用history 形式的路由：选择：N
5. 将依赖文件放在package.json中：选择：“in package.json”
6. 是否将当前选择保存以备下次使用：选择：N
7. 进入创建好的工程目录：cd tijianfront  
8. 启动工程：npm run serve 
9. 在浏览器中测试：http://localhost:8080

#### 3.2.1.3.添加其它依赖及配置文件

1. 添加font-awesome与axios依赖：
   cnpm install element-plus --save
   cnpm install axios --save

2. 在src目录下添加common.js文件

   ```javascript
   //获取当前时间（XXXX-XX-XX）
   export function getCurDate() {
   	var now = new Date();
   	var year = now.getFullYear();
   	var month = now.getMonth() + 1;
   	var day = now.getDate();
   	month = month < 10 ? "0" + month : month;
   	day = day < 10 ? "0" + day : day;
   	return year + "-" + month + "-" + day;
   }

   //向sessionStorage中存储一个JSON对象
   export function setSessionStorage(keyStr, value) {
   	sessionStorage.setItem(keyStr, JSON.stringify(value));
   }

   //从sessionStorage中获取一个JSON对象（取不到时返回null）
   export function getSessionStorage(keyStr) {
   	var str = sessionStorage.getItem(keyStr);
   	if (str == '' || str == null || str == 'null' || str == undefined) {
   		return null;
   	} else {
   		return JSON.parse(str);
   	}
   }

   //从sessionStorage中移除一个JSON对象
   export function removeSessionStorage(keyStr) {
   	sessionStorage.removeItem(keyStr);
   }

   //向localStorage中存储一个JSON对象
   export function setLocalStorage(keyStr, value) {
   	localStorage.setItem(keyStr, JSON.stringify(value));
   }

   //从localStorage中获取一个JSON对象（取不到时返回null）
   export function getLocalStorage(keyStr) {
   	var str = localStorage.getItem(keyStr);
   	if (str == '' || str == null || str == 'null' || str == undefined) {
   		return null;
   	} else {
   		return JSON.parse(str);
   	}
   }

   //从localStorage中移除一个JSON对象
   export function removeLocalStorage(keyStr) {
   	localStorage.removeItem(keyStr);
   }
   ```

3. 修改vue.config.js文件，修改启动端口

   ```javascript
   const { defineConfig } = require('@vue/cli-service')
   module.exports = defineConfig({
     transpileDependencies: true,
     devServer: {
       port: 8089    //修改启动端口
     }
   })
   
   ```

#### 3.2.1.4.main.js文件

```javascript
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

//导入element-plus框架
import ElementPlus from 'element-plus'
import 'element-plus/theme-chalk/index.css'

//使用路由守卫实现登录的权限验证
router.beforeEach(function(to,from,next){
    let doctor = sessionStorage.getItem('doctor');
    //除了登录、注册之外，都需要判断是否登录
    if(!(to.path=='/'||to.path=='/login')){
        if(doctor==null){
            router.push('/login');
        }
    }
    next();
});

createApp(App).use(router).use(ElementPlus).mount('#app')
```

#### 3.2.1.5.App.vue文件

注意：在App.vue文件中，#app的高度也要设置为100%。

```vue
<template>
  <router-view/>
</template>

<style>
/***************** CSS重置 *******************/
html,body,div,span,h1,h2,h3,h4,h5,h6,ul,ol,li,p{
	margin: 0;
	padding: 0;
}
html,body,#app{
	width: 100%;
	height: 100%;
	font-family: "微软雅黑";
}
ul,ol{
	list-style:none;
}
a{
	text-decoration: none;
}
</style>
```

#### 3.2.1.6.路由index.js文件

```javascript
import { createRouter, createWebHashHistory } from 'vue-router'
import Login from '../views/Login.vue'
import OrdersList from '../views/OrdersList.vue'
import OrdersContent from '../views/OrdersContent.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: Login
  },{
    path: '/login',
    name: 'Login',
    component: Login
  },{
    path: '/ordersList',
    name: 'OrdersList',
    component: OrdersList
  },{
    path: '/ordersContent',
    name: 'OrdersContent',
    component: OrdersContent
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
```



### 3.2.2.前端代码

#### Login.vue

```vue
<template>
  <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <span>登录</span>
      </div>
    </template>
    <div class="text item">
      <el-form ref="formRef" :model="loginForm" label-width="120px">
        <el-form-item label="医生编码">
          <el-input v-model="loginForm.docCode"></el-input>
        </el-form-item>
        <el-form-item label="登录密码">
          <el-input v-model="loginForm.password" type="password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="login">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </el-card>
</template>

<script>
import { reactive, toRefs } from "vue";
import { useRouter } from "vue-router";
import { setSessionStorage } from "../common.js";
import axios from "axios";
axios.defaults.baseURL = "http://localhost:8088/tijiancms/";

export default {
  setup() {
    const router = useRouter();

    const state = reactive({
      loginForm: {
        docCode: "",
        password: "",
      },
    });

    const login = () => {
      if(state.loginForm.docCode==''){
        alert('医生编码不能为空！');
        return;
      }
      if(state.loginForm.password==''){
        alert('密码不能为空！');
        return;
      }

      axios
        .post("doctor/getDoctorByCodeByPass", state.loginForm)
        .then((response) => {
          let doctor = response.data;
          if (doctor != "") {
            setSessionStorage("doctor", doctor);
            router.push("/ordersList");
          } else {
            alert("医生编码或密码不正确！");
          }
        })
        .catch((error) => {
          console.error(error);
        });
    };

    return {
      ...toRefs(state),
      login,
    };
  },
};
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.box-card {
  width: 400px;
  margin: 0 auto;
  margin-top: 150px;
}
</style>
```



#### OrdersList.vue

```vue
<template>
  <el-container style="height: 100%">
    <el-header>
      <h1>Neusoft&nbsp;&nbsp;东软体检报告管理系统</h1>
      <p>医生：{{ doctor.realName }}</p>
    </el-header>
    <el-container>
      <el-aside width="260px">
        <h4>体检用户查询</h4>
        <el-form ref="formRef" :model="selectForm" label-width="auto">
          <el-form-item label="手机号码">
            <el-input
              v-model="selectForm.userId"
              placeholder="手机号码"
            ></el-input>
          </el-form-item>
          <el-form-item label="姓名">
            <el-input
              v-model="selectForm.realName"
              placeholder="姓名"
            ></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio-group v-model="selectForm.sex">
              <el-radio label="1">男</el-radio>
              <el-radio label="0">女</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="套餐类型">
            <el-select v-model="selectForm.smId" placeholder="套餐类型">
              <el-option
                v-for="setmeal in setmealArr"
                :key="setmeal.smId"
                :label="setmeal.name"
                :value="setmeal.smId"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="体检日期">
            <el-date-picker
              v-model="selectForm.orderDate"
              type="date"
              placeholder="体检日期"
              style="width: 100%"
              format="YYYY/MM/DD"
              value-format="YYYY-MM-DD"
            ></el-date-picker>
          </el-form-item>
          <el-form-item label="是否归档">
            <el-radio-group v-model="selectForm.state">
              <el-radio border label="1">未归档</el-radio>
              <el-radio border label="2">已归档</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="doSelect">查询</el-button>
            <el-button type="warning" @click="reset">重置</el-button>
          </el-form-item>
        </el-form>
      </el-aside>
      <el-main>
        <el-table :data="ordersPageResponseDto.list" style="width: 100%">
          <el-table-column prop="orderId" label="预约编号" width="120" />
          <el-table-column prop="userId" label="手机号码" width="140" />
          <el-table-column prop="users.realName" label="真实姓名" width="120" />
          <el-table-column label="性别" width="60">
            <template #default="scope">
              <span>{{ scope.row.users.sex == 1 ? "男" : "女" }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="setmeal.name" label="套餐类型" />
          <el-table-column prop="hospital.name" label="体检医院" width="220" />
          <el-table-column prop="orderDate" label="体检日期" />
          <el-table-column label="操作" width="120">
            <template #default="scope">
              <el-button type="text" size="small" @click="ciReport(scope.row)"
                >{{scope.row.state==1?'编辑体检报告':'查看体检报告'}}</el-button
              >
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          background
          layout="prev, pager, next, total"
          :total="ordersPageResponseDto.totalRow"
          :page-size="ordersPageResponseDto.maxPageNum"
          style="margin-top: 20px"
          @current-change="currentChange"
        >
        </el-pagination>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import { reactive, toRefs } from "vue";
import { useRouter } from "vue-router";
import { getSessionStorage } from "../common.js";
import axios from "axios";
axios.defaults.baseURL = "http://localhost:8088/tijiancms/";

export default {
  setup() {
    const router = useRouter();

    const state = reactive({
      doctor: getSessionStorage("doctor"),
      selectForm: {
        userId: "",
        realName: "",
        sex: "",
        smId: "",
        orderDate: "",
        state: "1",
      },
      setmealArr: [],
      ordersPageResponseDto: {},
    });

    listSetmeal();
    function listSetmeal() {
      axios
        .post("setmeal/listSetmeal")
        .then((response) => {
          state.setmealArr = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    }

    listOrders(1);
    function listOrders(pageNum) {
      state.selectForm.pageNum = pageNum;
      state.selectForm.maxPageNum = 10;
      axios
        .post("orders/listOrders", state.selectForm)
        .then((response) => {
          state.ordersPageResponseDto = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    }

    function ciReport(row) {
      axios
        .post("ciReport/createReportTemplate", {
          orderId: row.orderId,
          smId: row.smId
        })
        .then((response) => {
          if(response.data==1){
            router.push({path:'/ordersContent',query:{orderId:row.orderId}});
          }else{
            alert('生成报告模板失败！');
          }
        })
        .catch((error) => {
          console.error(error);
        });
    }

    function doSelect() {
      console.log(state.selectForm);
      listOrders(1);
    }

    function currentChange(pageNum) {
      listOrders(pageNum);
    }

    function reset() {
      state.selectForm = {
        userId: "",
        realName: "",
        sex: "",
        smId: "",
        orderDate: "",
        state: "1",
      };
    }

    return {
      ...toRefs(state),
      ciReport,
      doSelect,
      listOrders,
      currentChange,
      reset,
    };
  },
};
</script>

<style scoped>
.el-header {
  background-color: #b3c0d1;
  color: var(--el-text-color-primary);
  text-align: center;
  line-height: 60px;

  display: flex;
  align-items: center;
  justify-content: space-between;
  color: #1c51a3;
}

.el-header h1 {
  font-size: 22px;
  font-weight: 700;
}
.el-header p {
  font-size: 16px;
}

.el-aside {
  background-color: #d3dce6;
  box-sizing: border-box;
  padding: 20px;
}
.el-aside h4 {
  color: #555;
  margin-bottom: 20px;
}

.el-main {
  background-color: #e9eef3;
}
</style>
```



#### OrdersContent.vue

```vue
<template>
  <el-container style="height: 100%">
    <el-header>
      <h1>Neusoft&nbsp;&nbsp;东软体检报告管理系统</h1>
      <p>医生：{{ doctor.realName }}</p>
    </el-header>
    <el-container>
      <el-aside width="260px">
        <el-descriptions
          class="margin-top"
          title="预约客户信息"
          :column="1"
          border
        >
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">预约编号</div>
            </template>
            {{ orders.orderId }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">手机号码</div>
            </template>
            {{ orders.userId }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">真实姓名</div>
            </template>
            {{ orders.users.realName }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">性别</div>
            </template>
            {{ orders.users.sex == 1 ? "男" : "女" }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">套餐类型</div>
            </template>
            {{ orders.setmeal.name }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">体检日期</div>
            </template>
            {{ orders.orderDate }}
          </el-descriptions-item>
        </el-descriptions>
        <el-button type="primary" style="margin-top: 20px" @click="toOrdersList"
          >查询体检用户</el-button
        >
      </el-aside>
      <el-main>
        <div class="main-box">
          <el-collapse>
            <el-collapse-item
              :title="ci.ciName"
              v-for="(ci, ciIndex) in ciReportArr"
              :key="ci.ciId"
            >
              <el-row style="color: #888">
                <el-col
                  :span="12"
                  style="box-sizing: border-box; padding: 6px 10px"
                  v-for="(cidr, cidrIndex) in ci.cidrList"
                  :key="cidr.ciId"
                >
                  <span
                    style="
                      background-color: #ba634e;
                      color: #fff;
                      box-sizing: border-box;
                      padding: 1px 3px;
                      border-radius: 3px;
                      margin-right: 5px;
                    "
                    v-if="cidr.isError == 1"
                    >异</span
                  >

                  <span style="margin-right: 5px; vertical-align: top">{{
                    cidr.name
                  }}</span>

                  <el-input
                    style="width: 26%; margin-right: 2px"
                    size="small"
                    :placeholder="cidr.name"
                    v-if="cidr.type != 4"
                    v-model="ciReportArr[ciIndex].cidrList[cidrIndex].value"
                    @blur="cidrCheckByBlur(ciIndex, cidrIndex)"
                  />
                  <el-input
                    style="width: 80%"
                    type="textarea"
                    :rows="2"
                    :placeholder="cidr.name"
                    v-model="ciReportArr[ciIndex].cidrList[cidrIndex].value"
                    v-if="cidr.type == 4"
                  />

                  <span style="margin-right: 15px">{{ cidr.unit }}</span>

                  <span v-if="cidr.normalValueString"
                    >正常值范围: {{ cidr.normalValueString }}</span
                  >
                </el-col>
              </el-row>
              <el-button
                type="primary"
                style="margin-top: 8px"
                @click="updateCiDetailedReport(ciIndex)"
                v-if="orders.state==1"
                >{{ ci.ciName }}项保存</el-button
              >
            </el-collapse-item>
          </el-collapse>

          <el-card class="box-card" style="margin-top: 20px">
            <template #header>
              <div class="card-header">
                <span>总检结论</span>
                <el-button class="button" type="danger"
                  @click="updateOrdersState"
                  v-if="orders.state==1">体检套餐总检结果报告归档</el-button
                >
              </div>
            </template>
            <div>
              <el-table :data="overallResultArr" style="width: 100%">
                <el-table-column prop="code" label="编号" width="60" />
                <el-table-column
                  prop="title"
                  label="总检结论项标题"
                  width="180"
                />
                <el-table-column prop="content" label="总检结论项内容" />
                <el-table-column label="操作" width="120">
                  <template #default="scope">
                    <el-button
                      type="text"
                      size="small"
                      @click="toUpdateOverallResult(scope.row)"
                      v-if="orders.state==1"
                      >更新</el-button
                    >
                    <el-button
                      type="text"
                      size="small"
                      @click="removeOverallResult(scope.row)"
                      v-if="orders.state==1"
                      >删除</el-button
                    >
                  </template>
                </el-table-column>
              </el-table>

              <el-form
                ref="formRef"
                :model="overallResultForm"
                style="margin-top: 20px"
                label-width="120px"
                v-if="orders.state==1"
              >
                <el-form-item label="总检结论标题">
                  <el-input
                    v-model="overallResultForm.title"
                    placeholder="总检结论标题"
                  ></el-input>
                </el-form-item>
                <el-form-item label="总检结论内容">
                  <el-input
                    v-model="overallResultForm.content"
                    :rows="2"
                    type="textarea"
                    placeholder="总检结论内容"
                  ></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="addOverallResult"
                    >添加</el-button
                  >
                  <el-button type="warning" @click="resetOverallResult"
                    >清空</el-button
                  >
                </el-form-item>
              </el-form>
            </div>
          </el-card>

          <!-- 总检结论更新用对话框 -->
          <el-dialog v-model="dialogVisible" title="总检结论项更新" width="60%">
            <span>
              <el-form :model="updateOverallResultForm" label-width="120px">
                <el-form-item label="总检结论标题">
                  <el-input v-model="updateOverallResultForm.title"></el-input>
                </el-form-item>
                <el-form-item label="总检结论内容">
                  <el-input v-model="updateOverallResultForm.content" type="textarea" :rows="3"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="updateOverallResult">更新</el-button>
                  <el-button @click="dialogVisible = false">取消</el-button>
                </el-form-item>
              </el-form>
            </span>
          </el-dialog>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import { reactive, toRefs } from "vue";
import { useRouter, useRoute } from "vue-router";
import { getSessionStorage } from "../common.js";
import axios from "axios";
axios.defaults.baseURL = "http://localhost:8088/tijiancms/";

export default {
  setup() {
    const router = useRouter();
    const route = useRoute();

    const state = reactive({
      orderId: route.query.orderId,
      doctor: getSessionStorage("doctor"),
      overallResultArr: [],
      overallResultForm: {
        title: "",
        content: "",
      },
      updateOverallResultForm: {
        orId: "",
        title: "",
        content: "",
      },
      orders: {
        users: {},
        setmeal: {},
      },
      ciReportArr: [],
      dialogVisible: false,
    });

    //初始化查询-体检预约信息
    getOrdersById();
    //初始化查询-体检报告检查项信息
    listCiReport();
    //初始化查询-总检结论项信息
    listOverallResultByOrderId();

    function getOrdersById() {
      axios
        .post("orders/getOrdersById", {
          orderId: state.orderId,
        })
        .then((response) => {
          state.orders = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    }

    function listCiReport() {
      axios
        .post("ciReport/listCiReport", {
          orderId: state.orderId,
        })
        .then((response) => {
          state.ciReportArr = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    }

    function listOverallResultByOrderId() {
      axios
        .post("overallResult/listOverallResultByOrderId", {
          orderId: state.orderId,
        })
        .then((response) => {
          state.overallResultArr = response.data;
          for (let i = 0; i < state.overallResultArr.length; i++) {
            state.overallResultArr[i].code = i + 1;
          }
        })
        .catch((error) => {
          console.error(error);
        });
    }

    function toOrdersList() {
      router.push("/ordersList");
    }

    function cidrCheckByBlur(ciIndex, cidrIndex) {
      //获取当前选中的检查项明细
      let cidr = state.ciReportArr[ciIndex].cidrList[cidrIndex];
      //判断type属性（1：数值范围验证型；2：数值相等验证型；）
      if (cidr.type == 1) {
        if (
          !(cidr.value == null || cidr.value == "") &&
          (cidr.value < cidr.minrange || cidr.value > cidr.maxrange)
        ) {
          cidr.isError = 1;
        } else {
          cidr.isError = 0;
        }
      } else if (cidr.type == 2) {
        if (
          !(cidr.value == null || cidr.value == "") &&
          cidr.value != cidr.normalValue
        ) {
          cidr.isError = 1;
        } else {
          cidr.isError = 0;
        }
      }
    }

    function updateCiDetailedReport(ciIndex) {
      //表单验证（1：非空；2：当type==1时验证是否为数字）
      let cidrArr = state.ciReportArr[ciIndex].cidrList;
      for (let i = 0; i < cidrArr.length; i++) {
        if (cidrArr[i].value == "") {
          alert(cidrArr[i].name + "不能为空！");
          return;
        }
        if (
          cidrArr[i].type == 1 &&
          parseFloat(cidrArr[i].value).toString() == "NaN"
        ) {
          alert(cidrArr[i].name + "必须为数字！");
          return;
        }
      }

      //封装提交参数（压缩提交参数）
      let arr = [];
      for (let i = 0; i < cidrArr.length; i++) {
        arr.push({
          cidrId: cidrArr[i].cidrId,
          value: cidrArr[i].value,
          isError: cidrArr[i].isError,
        });
      }

      axios
        .post("ciDetailedReport/updateCiDetailedReport", arr)
        .then((response) => {
          if (response.data > 0) {
            alert("保存成功！");
            listCiReport();
          } else {
            alert("保存失败！");
          }
        })
        .catch((error) => {
          console.error(error);
        });
    }

    function addOverallResult() {
      if (state.overallResultForm.title == "") {
        alert("总检结论项标题不能为空！");
        return;
      }

      state.overallResultForm.orderId = state.orderId;
      axios
        .post("overallResult/saveOverallResult", state.overallResultForm)
        .then((response) => {
          if (response.data > 0) {
            listOverallResultByOrderId();
          } else {
            alert("总检结论项添加失败！");
          }
        })
        .catch((error) => {
          console.error(error);
        });
    }

    function resetOverallResult() {
      state.overallResultForm = {
        title: "",
        content: "",
      };
    }

    function removeOverallResult(row) {
      if (!confirm("确认删除此数据吗？")) {
        return;
      }

      axios
        .post("overallResult/removeOverallResult", {
          orId: row.orId,
        })
        .then((response) => {
          if (response.data > 0) {
            listOverallResultByOrderId();
          } else {
            alert("总检结论项删除失败！");
          }
        })
        .catch((error) => {
          console.error(error);
        });
    }

    function toUpdateOverallResult(row) {
      state.dialogVisible = true;
      //这里不能直接赋值为row，必须使用深拷贝。否则更新数据与原数据是绑定的
      state.updateOverallResultForm = JSON.parse(JSON.stringify(row));
    }

    function updateOverallResult() {
      axios
        .post("overallResult/updateOverallResult", state.updateOverallResultForm)
        .then((response) => {
          if (response.data > 0) {
            listOverallResultByOrderId();
          } else {
            alert("总检结论项更新失败！");
          }
          state.dialogVisible = false;
        })
        .catch((error) => {
          console.error(error);
        });
    }

    function updateOrdersState(){
      if(!confirm('总检结论报告归档前，请务必确认是否所有检查项数据都正确？')){
        return;
      }

      axios
        .post("orders/updateOrdersState", {
          orderId: state.orderId,
          state: 2
        })
        .then((response) => {
          if (response.data > 0) {
            router.push('/ordersList');
          } else {
            alert("总检结论报告归档失败！");
          }
        })
        .catch((error) => {
          console.error(error);
        });
    }

    return {
      ...toRefs(state),
      toOrdersList,
      cidrCheckByBlur,
      updateCiDetailedReport,
      addOverallResult,
      resetOverallResult,
      removeOverallResult,
      toUpdateOverallResult,
      updateOverallResult,
      updateOrdersState
    };
  },
};
</script>

<style scoped>
.el-header {
  background-color: #b3c0d1;
  color: var(--el-text-color-primary);
  text-align: center;
  line-height: 60px;

  display: flex;
  align-items: center;
  justify-content: space-between;
  color: #1c51a3;
}

.el-header h1 {
  font-size: 22px;
  font-weight: 700;
}
.el-header p {
  font-size: 16px;
}

.el-aside {
  background-color: #d3dce6;
  box-sizing: border-box;
  padding: 20px;
}

.el-main {
  background-color: #e9eef3;
  padding: 0;
}

.el-main .main-box {
  width: 100%;
  height: 680px;
  overflow: auto;
  background-color: #fff;
  box-sizing: border-box;
  padding: 20px;
}

/*********** 描述列表 ***********/

.el-descriptions {
  margin-top: 20px;
}
.cell-item {
  display: flex;
  align-items: center;
}
.margin-top {
  margin-top: 20px;
}

/*********** 总检结论 ***********/
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.box-card {
  width: 100%;
}
</style>
```













































