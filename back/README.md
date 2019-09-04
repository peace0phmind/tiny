启动应用
--------
```
mvn
```


数据库的初始化, prod的时候使用，dev的时候直接使用本地h2数据库
-----------
使用下面脚本创建和初始化数据库，并给相关账号数据库访问权限：
```
mysql -uroot -p
```
```
mysql>
```

```
create database proto default character set utf8;
grant all privileges on proto.* to 'proto'@'%' identified by 'proto';
```

在mysql8下创建数据库schema
------------------
```
CREATE DATABASE proto DEFAULT CHARACTER SET utf8mb4;
CREATE USER 'proto'@'%' IDENTIFIED BY 'proto';
GRANT ALL PRIVILEGES ON proto.* TO 'proto'@'%' WITH GRANT OPTION;
```


通过liquibase创建初始化脚本
------
mvn clean compile liquibase:diff

>liquibase插件主要配置：
```
<configuration>
    <!-- liquibase下的master.xml负责管理全量脚本的生成 -->
    <changeLogFile>${project.basedir}/src/main/resources/config/liquibase/master.xml</changeLogFile>
    
    <!-- 配置增量脚本生成的位置，及命名 -->
    <diffChangeLogFile>
        ${project.basedir}/src/main/resources/config/liquibase/changelog/${maven.build.timestamp}_changelog.xml
    </diffChangeLogFile>
    
    <!-- 数据库相关配置 -->
    <driver>org.h2.Driver</driver>
    <url>jdbc:h2:file:${project.build.directory}/h2db/db/${project.name}</url>
    <defaultSchemaName/>
    <username>${project.name}</username>
    <password/>
    <!-- 指定liquibase参照的数据 -->
    <referenceUrl>
        hibernate:spring:${liquibase.packages}?dialect=org.hibernate.dialect.H2Dialect&amp;hibernate.physical_naming_strategy=org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy&amp;hibernate.implicit_naming_strategy=org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy
    </referenceUrl>
    <verbose>true</verbose>
    <logging>debug</logging>
    <contexts>!test</contexts>
</configuration>
```

当文件有修改后，执行mvn clean compile liquibase:diff时，可以分为三步理解：

>1.首先执行mvn clean，清除liquibase配置中url指定${project.build.directory}路径下的target文件。

>2.接着执行mvn，对referenceUrl指定的${liquibase.packages}路径下的工程进行编译，liquibase按照url指定${project.build.directory}下编译出来的target文件生成全量脚本，
全量脚本按changeLogFile指定的master.xml的管理，在指定位置生成。

>3.最后执行mvn liquibase:diff，此时liquibase对比数据库配置的url对应的数据库和根据${project.build.directory}下编译出来的target文件之间的差别，并生成增量脚本到diffChangeLogFile指定目录下，
为避免产生冲突，脚本采用当前时间戳为前缀，按照${maven.build.timestamp}_changelog.xml方式命名。



通过liquibase创建升级脚本
------
- mvn clean
- mvn          # 启动成功，系统加载完成
- mvn liquibase:diff

从h2中导出数据保存到文件
------
call CSVWRITE('./src/main/resources/config/liquibase/data/sys_user.csv', 'SELECT * FROM SYS_USER', 'charset=UTF-8 null=NULL')
call CSVWRITE('./src/main/resources/config/liquibase/data/role.csv', 'SELECT * FROM SYS_ROLE', 'charset=UTF-8 null=NULL')
call CSVWRITE('./src/main/resources/config/liquibase/data/user_role.csv', 'SELECT * FROM SYS_USER_ROLE', 'charset=UTF-8 null=NULL')

call CSVWRITE('./src/main/resources/config/liquibase/data/sys_menu.csv', 'SELECT * FROM SYS_MENU', 'charset=UTF-8 null=NULL');
call CSVWRITE('./src/main/resources/config/liquibase/data/sys_menu__tp.csv', 'SELECT * FROM SYS_MENU__TP', 'charset=UTF-8 null=NULL');

call CSVWRITE('./src/main/resources/config/liquibase/data/sys_department.csv', 'SELECT * FROM SYS_DEPARTMENT', 'charset=UTF-8 null=NULL')
call CSVWRITE('./src/main/resources/config/liquibase/data/sys_department__tp.csv', 'SELECT * FROM SYS_DEPARTMENT__TP', 'charset=UTF-8 null=NULL')



打包并忽略测试编译和运行
------
mvn -Dmaven.test.skip=true -Pprod clean package
