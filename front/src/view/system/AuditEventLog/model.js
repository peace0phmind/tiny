const model = {
  username: {
    name: '用户名称',
    type: 'STRING',
    required: false,
    readonly: false,
    unique: false,
  },
  loginTime: {
    name: '登录时间',
    type: 'DATETIME',
    required: false,
    readonly: false,
    unique: false,
    dateType: 'DEFAULT'
  },
  success: {
    name: '是否成功',
    type: 'BOOLEAN',
    required: false,
    readonly: false,
    unique: false,
    defaultValue: false,
  },
  ip: {
    name: 'ip地址',
    type: 'STRING',
    required: false,
    readonly: false,
    unique: false,
  }
}

export default model
