const columnLayout = [ {
  slot : "selection",
  title : "selection",
  align : "center",
  type : "selection"
}, {
  slot : "name",
  title : "名称",
  align : "center",
  minWidth : 100,
  type : "STRING",
  tooltip : true
}, {
  slot : "templateType",
  title : "模板类型",
  align : "center",
  minWidth : 100,
  type : "MODEL",
  tooltip : true
}, {
  slot : "intType",
  title : "int类型",
  align : "center",
  minWidth : 100,
  type : "DECIMAL",
  tooltip : true
}, {
  slot : "decimalType",
  title : "decimal类型",
  align : "center",
  minWidth : 100,
  type : "DECIMAL",
  tooltip : true
}, {
  slot : "timeType",
  title : "时间类型",
  align : "center",
  minWidth : 100,
  type : "DATETIME",
  tooltip : true
}, {
  slot : "dateType",
  title : "日期类型",
  align : "center",
  minWidth : 100,
  type : "DATETIME",
  tooltip : true
}, {
  slot : "dateTimeType",
  title : "日期时间类型",
  align : "center",
  minWidth : 181,
  type : "DATETIME",
  tooltip : true
}, {
  slot : "booleanType",
  title : "开关类型",
  align : "center",
  minWidth : 100,
  type : "BOOLEAN",
  tooltip : true
}, {
  slot : "status",
  title : "状态",
  align : "center",
  minWidth : 100,
  type : "MODEL",
  tooltip : true
}, {
  slot : "multiEnums",
  title : "多值枚举",
  align : "center",
  minWidth : 100,
  type : "MODEL",
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
  slot : "deletedDate",
  title : "删除时间",
  align : "center",
  minWidth : 100,
  type : "DATETIME",
  tooltip : true
}, {
  slot : "_action",
  title : "操作",
  align : "center",
  fixed : "right",
  minWidth : 150
} ]

export default columnLayout
