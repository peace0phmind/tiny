const columnLayout = [ {
  slot : "selection",
  title : "selection",
  align : "center",
  hide : false,
  type : "selection",
  tooltip : true
}, {
  slot : "beanName",
  title : "Bean名称",
  align : "center",
  minWidth : 150,
  type : "STRING",
  tooltip : true
}, {
  slot : "methodName",
  title : "方法名",
  align : "center",
  minWidth : 100,
  type : "STRING",
  tooltip : true
}, {
  slot : "params",
  title : "参数",
  align : "center",
  minWidth : 100,
  type : "STRING",
  tooltip : true
}, {
  slot : "cronExpression",
  title : "cron表达式",
  align : "center",
  minWidth : 100,
  type : "STRING",
  tooltip : true
}, {
  slot : "active",
  title : "激活",
  align : "center",
  minWidth : 80,
  type : "BOOLEAN",
  tooltip : true
}, {
  slot : "singleCase",
  title : "单实例",
  align : "center",
  minWidth : 80,
  type : "BOOLEAN",
  tooltip : true
}, {
  slot : "remarks",
  title : "备注",
  align : "center",
  minWidth : 100,
  type : "STRING",
  tooltip : true
}, {
  slot : "createdDate",
  title : "创建时间",
  align : "center",
  minWidth : 100,
  type : "DATETIME",
  tooltip : true
}, {
  slot : "modifiedDate",
  title : "修改时间",
  align : "center",
  minWidth : 100,
  type : "DATETIME",
  tooltip : true
}, {
  slot : "creator",
  title : "创建人",
  align : "center",
  minWidth : 100,
  type : "MODEL",
  tooltip : true
}, {
  slot : "modifier",
  title : "修改人",
  align : "center",
  minWidth : 100,
  type : "MODEL",
  tooltip : true
}, {
  slot : "_action",
  title : "操作",
  align : "center",
  fixed : "right",
  hide : false,
  minWidth : "200",
  tooltip : true
} ]

export default columnLayout
