const model = {
  job: {
    name: '定时任务',
    type: 'MODEL',
    required: false,
    readonly: false,
    unique: false,
    modelType: 'COMMON',
    modelName: 'ScheduleJob',
    referenceMode: 'manyToOne',
    resourcePath: 'sys-schedule-jobs',
    tree: false,
    primaryKey: 'id',
    primaryKeyType: 'UUID',
    componentRelativePath: 'system/ScheduleJob',
    componentName: 'c-sys-schedule-jobs',
    bidirectional: true,
    arr: [],
  },
  success: {
    name: '成功',
    type: 'BOOLEAN',
    required: false,
    readonly: false,
    unique: false,
    defaultValue: false,
  },
  result: {
    name: '结果',
    type: 'STRING',
    required: false,
    readonly: false,
    unique: false,
  },
  times: {
    name: '耗时(单位：毫秒)',
    type: 'DECIMAL',
    required: false,
    readonly: false,
    unique: false,
  },
  startTime: {
    name: '开始时间',
    type: 'DATETIME',
    required: false,
    readonly: false,
    unique: false,
    dateType: 'DEFAULT'
  }
}

export default model
