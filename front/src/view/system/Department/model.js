const model = {
  name: {
    name: '名称',
    type: 'STRING',
    required: false,
    readonly: false,
    unique: false,
  },
  code: {
    name: '代码',
    type: 'STRING',
    required: false,
    readonly: false,
    unique: false,
  },
  description: {
    name: '描述',
    type: 'STRING',
    required: false,
    readonly: false,
    unique: false,
  },
  _parent: {
    name: '父节点',
    type: 'MODEL',
    required: false,
    readonly: false,
    unique: false,
    modelType: 'COMMON',
    modelName: 'Department',
    referenceMode: 'manyToOne',
    resourcePath: 'sys-departments',
    tree: true,
    primaryKey: 'id',
    primaryKeyType: 'UUID',
    componentRelativePath: 'system/Department',
    componentName: 'c-sys-departments',
    bidirectional: false,
    arr: [],
  }
}

export default model
