const model = {
  username: {
    name: '用户名',
    type: 'STRING',
    required: false,
    readonly: false,
    unique: false,
  },
  requestTime: {
    name: '请求时间',
    type: 'DATETIME',
    required: false,
    readonly: false,
    unique: false,
    dateType: 'DEFAULT'
  },
  description: {
    name: '描述',
    type: 'STRING',
    required: false,
    readonly: false,
    unique: false,
  },
  ipAddress: {
    name: 'ip地址',
    type: 'STRING',
    required: false,
    readonly: false,
    unique: false,
  },
  costTime: {
    name: '花费时间',
    type: 'DECIMAL',
    required: false,
    readonly: false,
    unique: false,
  },
  className: {
    name: '类名',
    type: 'STRING',
    required: false,
    readonly: false,
    unique: false,
  },
  methodName: {
    name: '方法名',
    type: 'STRING',
    required: false,
    readonly: false,
    unique: false,
  },
  params: {
    name: '请求体',
    type: 'STRING',
    required: false,
    readonly: false,
    unique: false,
  }
}

export default model
