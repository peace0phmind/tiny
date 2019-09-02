const model = {
  name: {
    name: '名称',
    type: 'STRING',
    required: false,
    readonly: false,
    unique: false,
  },
  value: {
    name: '值',
    type: 'STRING',
    required: true,
    readonly: false,
    unique: false,
  },
  test: {
    name: 'test',
    type: 'MODEL',
    required: false,
    readonly: false,
    unique: false,
    modelType: 'COMMON',
    modelName: 'Test',
    referenceMode: 'manyToOne',
    resourcePath: 'tny-tests',
    tree: false,
    primaryKey: 'id',
    primaryKeyType: 'Long',
    componentRelativePath: 'Test',
    componentName: 'c-tny-tests',
    bidirectional: true,
    arr: [],
  }
}

export default model
