const searchLayout = {
  alwaysShow : [ {
    prop : "username",
    label : "登录名",
    operation : "contains"
  }, {
    prop : "department",
    label : "部门",
    operation : "in"
  } ],
  extra : [ {
    prop : "name",
    label : "姓名",
    operation : "contains"
  }, {
    prop : "phone",
    label : "电话",
    operation : "contains"
  }, {
    prop : "email",
    label : "邮箱",
    operation : "contains"
  }, {
    prop : "password",
    label : "密码",
    operation : "contains",
    hide : true
  }, {
    prop : "newPassword",
    label : "确认密码",
    operation : "contains",
    hide : true
  }, {
    prop : "birthday",
    label : "生日",
    operation : "range"
  }, {
    prop : "enabled",
    label : "可使用",
    operation : "equals"
  }, {
    prop : "sex",
    label : "性别",
    operation : "in"
  } ]
}

export default searchLayout
