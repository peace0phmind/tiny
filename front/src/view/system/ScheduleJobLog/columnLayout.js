const columnLayout = [ {
  slot : "selection",
  title : "selection",
  align : "center",
  hide : true,
  type : "selection"
}, {
  slot : "job",
  title : "定时任务",
  align : "center",
  minWidth : 100,
  type : "MODEL",
  tooltip : true
}, {
  slot : "success",
  title : "成功",
  align : "center",
  minWidth : 100,
  type : "BOOLEAN",
  tooltip : true
}, {
  slot : "result",
  title : "结果",
  align : "center",
  minWidth : 100,
  type : "STRING",
  tooltip : true
}, {
  slot : "times",
  title : "耗时(单位：毫秒)",
  align : "center",
  minWidth : 100,
  type : "DECIMAL",
  tooltip : true
}, {
  slot : "startTime",
  title : "开始时间",
  align : "center",
  minWidth : 100,
  type : "DATETIME",
  tooltip : true
}, {
  slot : "_action",
  title : "操作",
  align : "center",
  fixed : "right",
  hide : true,
  minWidth : 150
} ]

export default columnLayout
