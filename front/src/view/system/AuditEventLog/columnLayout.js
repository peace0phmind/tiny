const columnLayout = [ {
  slot : "selection",
  title : "selection",
  align : "center",
  type : "selection"
}, {
  slot : "username",
  title : "用户名称",
  align : "center",
  minWidth : 100,
  type : "STRING",
  tooltip : true,
  flatAttr : [ ]
}, {
  slot : "loginTime",
  title : "登录时间",
  align : "center",
  minWidth : 100,
  type : "DATETIME",
  tooltip : true,
  defaultSort : true
}, {
  slot : "success",
  title : "是否成功",
  align : "center",
  minWidth : 100,
  type : "BOOLEAN",
  tooltip : true
}, {
  slot : "ip",
  title : "ip地址",
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
  minWidth : 130
} ]

export default columnLayout
