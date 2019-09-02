const model = {
  name: {
    name: '名称',
    type: 'STRING',
    required: false,
    readonly: false,
    unique: false,
  },
  testType: {
    name: '测试类型',
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
  treeTestItems: {
    name: 'tree测试item',
    type: 'MODEL',
    required: false,
    readonly: false,
    unique: false,
    collection: true,
    modelType: 'TreeItem',
    referenceMode: 'manyToMany',
    bidirectional: true,
    referModelAttribute: 'tests',
    resourcePath: 'tny-tree-items',
    tree: false,
    primaryKey: 'id',
    primaryKeyType: 'UUID',
    componentRelativePath: 'item/TreeItem',
    componentName: 'c-tny-tree-items',
  },
  _parent: {
    name: '父节点',
    type: 'MODEL',
    required: false,
    readonly: false,
    unique: false,
    modelType: 'COMMON',
    modelName: 'TreeTest',
    referenceMode: 'manyToOne',
    resourcePath: 'tny-tree-tests',
    tree: true,
    primaryKey: 'id',
    primaryKeyType: 'Long',
    componentRelativePath: 'TreeTest',
    componentName: 'c-tny-tree-tests',
    bidirectional: false,
    arr: [],
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