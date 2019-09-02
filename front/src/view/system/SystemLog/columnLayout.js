const columnLayout = [ {
  slot : "selection",
  title : "selection",
  align : "center",
  type : "selection"
}, {
  slot : "username",
  title : "用户名",
  align : "center",
  minWidth : 100,
  type : "STRING",
  tooltip : true,
  flatAttr : [ ]
}, {
  slot : "requestTime",
  title : "请求时间",
  align : "center",
  minWidth : "110",
  type : "DATETIME",
  tooltip : true,
  sortable : true,
  defaultSort : true
}, {
  slot : "description",
  title : "描述",
  align : "center",
  minWidth : "150",
  type : "STRING",
  tooltip : true,
  flatAttr : [ ]
}, {
  slot : "ipAddress",
  title : "ip地址",
  align : "center",
  minWidth : 100,
  type : "STRING",
  tooltip : true,
  flatAttr : [ ]
}, {
  slot : "costTime",
  title : "花费时间（ms）",
  align : "center",
  minWidth : 100,
  type : "DECIMAL",
  tooltip : true,
  sortable : true
}, {
  slot : "className",
  title : "类名",
  align : "center",
  hide : true,
  minWidth : 100,
  type : "STRING",
  tooltip : true,
  flatAttr : [ ]
}, {
  slot : "methodName",
  title : "方法名",
  align : "center",
  minWidth : 100,
  type : "STRING",
  tooltip : true,
  flatAttr : [ ]
}, {
  slot : "params",
  title : "请求体",
  align : "center",
  minWidth : 100,
  type : "STRING",
  tooltip : true,
  flatAttr : [ ]
}, {
  slot : "_action",
  title : "操作",
  align : "center",
  fixed : "right",
  hide : true,
  minWidth : 150
} ]

export default columnLayout
