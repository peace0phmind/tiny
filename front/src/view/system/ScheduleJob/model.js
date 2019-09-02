const model = {
  beanName: {
    name: 'Bean名称',
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
    name: '参数',
    type: 'STRING',
    required: false,
    readonly: false,
    unique: false,
  },
  cronExpression: {
    name: 'cron表达式',
    type: 'STRING',
    required: false,
    readonly: false,
    unique: false,
  },
  active: {
    name: '激活',
    type: 'BOOLEAN',
    required: false,
    readonly: false,
    unique: false,
    defaultValue: false,
  },
  singleCase: {
    name: '单实例',
    type: 'BOOLEAN',
    required: false,
    readonly: false,
    unique: false,
    defaultValue: false,
  },
  remarks: {
    name: '备注',
    type: 'STRING',
    required: false,
    readonly: false,
    unique: false,
  },
  logs: {
    name: '日志',
    type: 'MODEL',
    required: false,
    readonly: false,
    unique: false,
    collection: true,
    modelType: 'ScheduleJobLog',
    referenceMode: 'oneToMany',
    bidirectional: true,
    referModelAttribute: 'job',
    resourcePath: 'sys-schedule-job-logs',
    tree: false,
    primaryKey: 'id',
    primaryKeyType: 'Long',
    componentRelativePath: 'system/ScheduleJobLog',
    componentName: 'c-sys-schedule-job-logs',
  },
  createdDate: {
    name: '创建时间',
    type: 'DATETIME',
    required: false,
    readonly: true,
    unique: false,
    dateType: 'DEFAULT'
  },
  modifiedDate: {
    name: '修改时间',
    type: 'DATETIME',
    required: false,
    readonly: true,
    unique: false,
    dateType: 'DEFAULT'
  },
  creator: {
    name: '创建人',
    type: 'MODEL',
    required: false,
    readonly: true,
    unique: false,
    modelType: 'COMMON',
    modelName: 'User',
    referenceMode: 'manyToOne',
    resourcePath: 'sys-users',
    tree: false,
    primaryKey: 'id',
    primaryKeyType: 'UUID',
    componentRelativePath: 'system/User',
    componentName: 'c-sys-users',
    bidirectional: false,
    arr: [],
  },
  modifier: {
    name: '修改人',
    type: 'MODEL',
    required: false,
    readonly: true,
    unique: false,
    modelType: 'COMMON',
    modelName: 'User',
    referenceMode: 'manyToOne',
    resourcePath: 'sys-users',
    tree: false,
    primaryKey: 'id',
    primaryKeyType: 'UUID',
    componentRelativePath: 'system/User',
    componentName: 'c-sys-users',
    bidirectional: false,
    arr: [],
  }
}

export default model
