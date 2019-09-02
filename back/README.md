启动应用
--------
mvn



数据库的初始化, prod的时候使用，dev的时候直接使用本地h2数据库
-----------
使用下面脚本创建和初始化数据库，并给相关账号数据库访问权限：

mysql -uroot -p

mysql>

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
