const columnLayout = [ {
  slot : "selection",
  title : "selection",
  align : "center",
  type : "selection"
}, {
  slot : "name",
  title : "姓名",
  align : "center",
  minWidth : 100,
  type : "STRING",
  tooltip : true,
  sortable : true,
  flatAttr : [ ]
}, {
  slot : "username",
  title : "登录名",
  align : "center",
  minWidth : 100,
  type : "STRING",
  tooltip : true,
  flatAttr : [ ]
}, {
  slot : "phone",
  title : "电话",
  align : "center",
  minWidth : 100,
  type : "STRING",
  tooltip : true,
  flatAttr : [ ]
}, {
  slot : "email",
  title : "邮箱",
  align : "center",
  minWidth : 100,
  type : "STRING",
  tooltip : true,
  flatAttr : [ ]
}, {
  slot : "birthday",
  title : "生日",
  align : "center",
  minWidth : 100,
  type : "DATETIME",
  tooltip : true
}, {
  slot : "enabled",
  title : "可用",
  align : "center",
  minWidth : 100,
  type : "BOOLEAN",
  tooltip : true
}, {
  slot : "department",
  title : "部门",
  align : "center",
  minWidth : 100,
  type : "MODEL",
  tooltip : true,
  flatAttr : [ ]
}, {
  slot : "sex",
  title : "性别",
  align : "center",
  minWidth : 100,
  type : "MODEL",
  tooltip : true
}, {
  slot : "password",
  title : "密码",
  align : "center",
  hide : true,
  minWidth : 100,
  type : "STRING",
  tooltip : true,
  flatAttr : [ ]
}, {
  slot : "newPassword",
  title : "确认密码",
  align : "center",
  hide : true,
  minWidth : 100,
  type : "STRING",
  tooltip : true,
  flatAttr : [ ]
}, {
  slot : "_action",
  title : "操作",
  align : "center",
  fixed : "right",
  minWidth : 150
} ]

export default columnLayout
