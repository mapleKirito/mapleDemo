## 相关参考 码匠社区

## 资料
[Spring 文档](https://spring.io/guides)
[Spring Web](https://spring.io/guides/gs/serving-web-content/)
[es社区](https://elasticsearch.cn/explore)
[Github deploy key](https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-keys)
[Bootstrap](https://v3.bootcss.com/getting-started/)
[Github OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)
[OKHttp](https://square.github.io/okhttp/)
[mybatis](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/index.html)

##工具
[Git](https://git-scm.com/download)
[Visual-paradigm](https://www.visual-paradigm.com)
[Maven仓库](https://mvnrepository.com/)

##控制台命令
flyway
~~~
mvn flyway:migrate
~~~

##脚本
~~~sql
create table springBootDemo_user
(
	id int auto_increment
		primary key,
	account_id varchar(100) null,
	name varchar(50) null,
	token char(36) null,
	gmt_create bigint null,
	gmt_modified bigint null
);
~~~