后端文档
----

* http curd请求

* 几种常用Filter的介绍()

* resource层

* mapper层 (http://mapstruct.org)
    - 基本的mapstruct使用
    - 系统默认生成的方法介绍：toDtoOnlyId, toDto, toDtoWithModel, toDtoWithModelAndCollection, toDomainOnlyId, toDomain, updateDomain   

* service层

* jpa层(spring common data)
    - spring common data
    - jpql
    - native sql

* DTO
    - 包装器Optional的作用和使用

* Domain
    - 如何部分更新
    - 1对多，多对一，多对多的几种场景 

* 安全(安全模块是如何工作的)

* 软删除是如何工作的

* 树是如何工作的

* 定制逻辑的几个约定
    - 第一步删除field, method方法上的@Generated(IRW.CODE_GENERATOR)
    - resource层用于参数校验
    - 逻辑尽可能在service层中完成
    - 逻辑尽可能用domain而不是dto编写
    - dto尽可能只用于数据传输，不用于逻辑运算
