const model = {
  name: {
    name: '名称',
    type: 'STRING',
    required: false,
    readonly: false,
    unique: false,
  },
  templateType: {
    name: '模板类型',
    type: 'MODEL',
    required: false,
    readonly: false,
    unique: false,
    modelType: 'COMMON',
    modelName: 'TestType',
    referenceMode: 'manyToOne',
    resourcePath: 'tny-test-types',
    tree: false,
    primaryKey: 'id',
    primaryKeyType: 'Long',
    componentRelativePath: 'TestType',
    componentName: 'c-tny-test-types',
    bidirectional: false,
    arr: [],
  },
  intType: {
    name: 'int类型',
    type: 'DECIMAL',
    required: false,
    readonly: false,
    unique: false,
  },
  decimalType: {
    name: 'decimal类型',
    type: 'DECIMAL',
    required: false,
    readonly: false,
    unique: false,
  },
  timeType: {
    name: '时间类型',
    type: 'DATETIME',
    required: false,
    readonly: false,
    unique: false,
    dateType: 'TIME_ONLY'
  },
  dateType: {
    name: '日期类型',
    type: 'DATETIME',
    required: false,
    readonly: false,
    unique: false,
    dateType: 'DATE_ONLY'
  },
  dateTimeType: {
    name: '日期时间类型',
    type: 'DATETIME',
    required: false,
    readonly: false,
    unique: false,
    dateType: 'DEFAULT'
  },
  booleanType: {
    name: '开关类型',
    type: 'BOOLEAN',
    required: false,
    readonly: false,
    unique: false,
    defaultValue: false,
  },
  status: {
    name: '状态',
    type: 'MODEL',
    required: false,
    readonly: false,
    unique: false,
    modelType: 'ENUM',
    enumName: 'Status',
  },
  multiEnums: {
    name: '多值枚举',
    type: 'MODEL',
    required: false,
    readonly: false,
    unique: false,
    collection: true,
    modelType: 'MultiEnum',
    referenceMode: 'oneToMany',
    enumName: 'MultiEnum',
  },
  items: {
    name: '测试items',
    type: 'MODEL',
    required: false,
    readonly: false,
    unique: false,
    collection: true,
    modelType: 'Item',
    referenceMode: 'oneToMany',
    bidirectional: true,
    referModelAttribute: 'test',
    resourcePath: 'tny-items',
    tree: false,
    primaryKey: 'id',
    primaryKeyType: 'UUID',
    componentRelativePath: 'item/Item',
    componentName: 'c-tny-items',
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
  },
  deletedDate: {
    name: '删除时间',
    type: 'DATETIME',
    required: false,
    readonly: true,
    unique: false,
    dateType: 'DEFAULT'
  }
}

export default model
