package foo.lesson.summary;

import java.util.regex.Pattern;

/**
 * <b>数据库相关</b><p>
 * 
 * 1.主流数据库有哪些?<br>
 * 2.你所了解的数据库优化措施?<br>
 * 3.Sql语句注意点?<br>
 * 4.什么是JDBC操作?写出一个简单的JDBC连接数据库的小程序?<br>
 * 5.什么是数据库连接池?如何使用数据库连接池?<br>
 * 6.什么是数据库缓存?<br>
 * 7.简单谈谈你对事务的理解?<br>
 * 8.批处理Batch,函数过程Process?<br>
 * 9.什么是ORM框架，为什么要有ORM框架?谈谈主流的ORM框架?<br>
 * 10.什么是索引？如何做索引优化?
 * 11.mysql的引擎和特性?
 * 12.你们会怎么做数据库优化?
 * 13.多表连接JOIN的用法？LEFT JOIN的用法?
 * 14.什么是一对多映射?什么是多对一映射?什么是多对多映射?
 * 15.数据库设计需要考虑哪些问题？
 * 
 * <b>Hibernate相关</b><p>
 * 0.为什么你要使用Hibernate?他有什么优点?<br>
 * 
 * 1.Hibernate的三种状态?他们之间是如何进行转化的?
 * 2.一级缓存(Session缓存)?二级缓存(SessionFactory全局缓存)?查询缓存？
 * 3.Session的加载实体对象的过程?(实体的 一级缓存-》二级缓存  黑名单功能)
 * 4.Dialect方言?利用方言帮你生成不同数据库的SQL语句(不同数据库的SQL有细微的差别，分页)
 * 5.使用Hibernate要注意哪些地方?
 * 
 
 * <b>MyBatis相关</b><p>
 * 1.为什么你要使用MyBatis?他有什么优点?<br>
 * 2.解释一下MyBatis中命名空间（namespace）的作用
 * 3.MyBatis中使用#和$书写占位符有什么区别？
 * 
 * 
 * <<SQL必学必会>> SQL语法一定要不同的学，数据库框架会用就好
 * 1.JOIN的用法
 * 2.Group by
 * 3.Order By
 * 4.Having  分组之后过滤数据 和 Where 分组之前过滤数据
 * 
 * Select 民族
 * FROM 中国
 * Group By 民族
 * Having sum(人口) > 1亿;
 * 
 * @author wyy
 * 2016年11月23日
 *
 */
public class LessonDataBase {
	
	
	/**
	 * 1.主流数据库有哪些?<br>
	 */
	//关系型数据库 RDBS
	//优点：保证数据的安全性和一致性   （ACID原则）
	       //可以实现复杂的搜索
	//缺点：行列表 ，他的文件结构Scheme比较复杂
	//1.*Mysql(开源)        适合企业：互联网和初创公司
	//优点：开源(免费，社区活跃[出现问题，在网上搜索解决问题的概率更高])，开源软件可以做深度定制
	//缺点：1.不够稳定，效率跟你使用者的能力是挂钩的 2.一般性能比不上商业服务器
	//2.*SqlServer(微软)    适合企业：微软全家桶
	//优点：跟微软自身那一整套服务整合，特别灵活方便 IIS（微软的web服务器） .net/c#
	//缺点：
	//3.*Oracle(商业服务器)  适合企业：国企
	//优点：各个方面都很强
	//缺点：太贵（按CPU数量来收license授权费）
	
	//非关系型数据库NOSQL
	//本质上就是带索引的文件-(做查询)
	//最大优点：文件结构比较简单
	//1.性能高
	//2.容易扩展
	//*Mongodb：主流的非关系型数据库，文档存储 更叫高效
	//*Redis:一般作为缓存数据库
	
	//怎么构建你的网站的数据库?
	//1.对一些重要的敏感的数据(用户信息  用户账户信息) 要选用 关系型数据库 RDBS（数据的安全性太过重要）
	//2.日记,电商的推荐系统(记录你的消费记录和网站浏览足迹) - 非关系型数据库NOSQL（对性能要求更高）
	
	//大数据简单历程：
	//B搜索A电商T社交
	//社交始于约炮 兴于炫耀 衰于广告 亡于微商
	//招募最好的学生（成长自己打击对手）
	
	//Google(谷爹)：hfsGoogle文件系统,BigTable大表格,
	//lucene：jAVA开源搜索引擎的作者看了Google的论文  2005
	//开发了Hadoop大数据库
	
	/**
	 * WEB服务器
	 */
	//*TOMCAT(开源WEB服务器)
	//优点：1.免费 2.社区活跃，出现问题如果在网上解决。 3.深度定制
	//*IIS(微软WEB服务器)
	//优点：
	//*WebLogic/WebShpehre(商业WEB服务器)
	//优点：1.重型 功能强大  
	//缺点：贵，重型
	/**
	 * HTTP服务器（只处理静态内容，效率比WEB服务器高很多）
	 */
	//*Nginx：
	//反向代理(正向代理-浏览器[前端]) ：代理的是你的服务端
	//反向代理就是为了实现负载均衡
	//*ApacheHTTP：
	
	//处理静态网页和静态内容
	//动静分离
	//所有请求都过HTTP服务器->  再把动态内容转发给WEB服务器
	
    /**
     * 2.你所了解的数据库优化措施?
     */
	//按以下顺序进行数据库优化：
	//0.数据库schema设计
	//1-1.sql语句的优化  （两种批量insert的效率)，批处理和函数过程
	//1-2.索引的优化
	//2.数据库连接池
	//3.缓存Ehcache
	//4.怎么更高效的使用事务
	//5.数据库安全
	
	//提高部分（不要求掌握）：
	//6.主从复制,读写分离。 qihu360的atlas 相当于mysql-proxy
	//7.mysql分区表（sql语句针对分区表做优化）
	//8.垂直拆分，(大的模块分解为小的模块)【分布式系统】
	//8.水平拆分，对数据库做sharding，选择一个合理的sharding key。
	/**
	 * 3.Sql语句注意点?<br>
	 */
	
	/**
	 * 4.什么是JDBC操作?写出一个简单的JDBC连接数据库的小程序?<br>
	 */
	
	/**
	 * 5.数据库连接池?<br>
	 */
	
	/**
	 * 6.数据库缓存?<br>
	 */
	
	/**
	 * 7.简单谈谈你对事务的理解?<br>
	 */
	
	/**
	 * 10.什么是索引？如何做索引优化?
	 */
	//1.排序 2.二分查找法(Btree 快速更新和删除)
	
	//Mysql数据库
	//1.一条数据查询只能使用一个索引(但索引可以是多个字段合并的复合索引)
	
	//#Hash索引 key-value索引
	
	/**
	 * 12.你们会怎么做数据库优化?
	 */
	//#数据库驱动
	//1.建表： 
	//#主键(unique保证唯一性)，主键天然就是索引
	//#索引优化，组合索引（）
	//#主键设置有两种方式:1.自增长  2.UUID（32位的字符串）
	//自增长效率更高，
	//UUID人工控制保证ID唯一，UUID
    //2.sql优化
	//批处理的例子， PreparedStatment和Statement的区别
	//3.数据库连接池 缓存
	
	//4.读写分离，主从分库
	//5.垂直拆分（把大的业务拆分成小的业务）
	//6.水平拆分（sharding）
	/**
	 * 13.多表连接JOIN ,LEFT JOIN
	 */
	//什么时候使用JOIN，一对多，多对一
	//1.讲一个你用的多对一场景()
	
	//2.会有3个表 ： A表 B表 a_b表：存A表和B表的主键（命名规则：a_b表代表A中包含多个B）
	//SQL语句：
	/**
	 * 什么是一对多映射?什么是多对一映射?什么是多对多映射?
	 */
	//什么是一对多映射?举出一个例子？@OneToMany
	//角色ROLE有多个权限 一对多
	//多对一映射?                @ManyToOne
	//在权限表中取角色(多个权限对应一个角色)
	//多对多映射？                                     @ManyToMany
	//多对多拆分成双向的多对一映射(角色和用户)
	//用多表连接JOIN的方式
	
	/**
	 * 5.使用Hibernate要注意哪些地方?
	 */
	//1.Hibernate一般会在数据库开发阶段把生成SQL打印出来用于测试和修改,发布的时候把他给去掉
	//2.默认是强制要求开启事务的
	//3.注意一下你的Hiernate的版本。
	//4.Hibernate的事务配置传入的是sessionFactory
	
	/**
	 * 1.为什么你要使用MyBatis?他有什么优点?<br>
	 */
	//相比Hibernate等其他框架
	//轻度的封装,可以订制功能，需要自己编写SQL语句，性能更好。
	/**
	 * 2.解释一下MyBatis中命名空间（namespace）的作用
	 */
	//避免重名(把一些通用方法给抽取出来)
	/**
	 * 3.MyBatis中使用#和$书写占位符有什么区别？
	 */
	//#占位符 
	//$相当于字符串拼接容易引起SQL注入问题
}
