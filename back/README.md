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

执行mvn clean compile liquibase:diff时，可以分为三步理解：

>1.clean，清除target目录下的所有文件并删除target目录。

>2.compile，拷贝resources目录下的资源文件、配置文件等到classpath目录下，并且编译所有的java文件到classpath目录。

>3.liquibase:diff, liquibase会以上述配置文件中的数据库配置为基础，和referenceUrl指定的${liquibase.packages}包路径下的所有entity的class文件进行比较，将增量的数据库脚本生成到diffChangeLogFile中指定的文件中。
由于该处指定的是${maven.build.timestamp}_changelog.xml，所以对应的增量文件会按照时间戳加上_changelog.xml方式生成到工程resources目录下的config/liquibase/changelog目录中。由于这里第一步已经清除了url配置中的
target/h2db/db下的数据库文件，所以该步骤相当于是用空数据库与全量的entity的class文件进行比较，所以这里生成的是全量的数据库脚本。

>补充说明：数据库配置与driver，url，defaultSchemaName，username，password这5个变量相关，如果需要对mysql进行脚本生成，需要修改对应的配置参数。


通过liquibase创建升级脚本
------
- mvn clean
- mvn          # 启动成功，系统加载完成
- 通过control - c终止进程
- mvn liquibase:diff


>1. clean，清除target目录下的所有文件并删除target目录。此处是为了生成相对于现有脚本与现有entity做对比所做的对环境的清理工作（详见下面描述）。
>2. mvn, 当target目录被清空，对应的数据库文件被删除的清空下，运行mvn，liquibase插件会自动从changeLogFile所指定的文件对数据库文件进行创建，其中会解析master.xml文件，加载initial_schema.xml文件，该文件会将数据库的表结构
以及索引、外键、其他约束加载到数据库，另外还会加载initial_data.xml文件，将config/liquibase/data目录下的若干csv文件作为初始化数据加载到数据库中。当liquibase完成master.xml加载后，对应的在target/h2db/db/目录下生成的数据库
文件已经加载完成。
>3. 通过终止进程退出步骤2中的mvn命令
>4. 使用liquibase:diff命令的详细原理参见全量脚本中的步骤3，此处由于是一个使用相对于master.xml文件状态的数据库以及entity的class进行比较的脚本，所以这里生成的是升级脚本。


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
