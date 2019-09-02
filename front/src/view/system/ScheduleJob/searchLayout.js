const searchLayout = {
  alwaysShow : [ ],
  extra : [ {
    prop : "beanName",
    label : "Bean名称",
    operation : "contains"
  }, {
    prop : "methodName",
    label : "方法名",
    operation : "contains"
  }, {
    prop : "params",
    label : "参数",
    operation : "contains",
    hide : true
  }, {
    prop : "cronExpression",
    label : "cron表达式",
    operation : "contains",
    hide : true
  }, {
    prop : "active",
    label : "激活",
    operation : "equals",
    hide : true
  }, {
    prop : "singleCase",
    label : "单实例",
    operation : "equals",
    hide : true
  }, {
    prop : "remarks",
    label : "备注",
    operation : "contains",
    hide : true
  }, {
    prop : "createdDate",
    label : "创建时间",
    operation : "range",
    hide : true
  }, {
    prop : "modifiedDate",
    label : "修改时间",
    operation : "range",
    hide : true
  }, {
    prop : "creator",
    label : "创建人",
    operation : "in",
    hide : true
  }, {
    prop : "modifier",
    label : "修改人",
    operation : "in",
    hide : true
  } ]
}

export default searchLayout
