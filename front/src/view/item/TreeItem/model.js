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
  tests: {
    name: 'test',
    type: 'MODEL',
    required: false,
    readonly: false,
    unique: false,
    collection: true,
    modelType: 'TreeTest',
    referenceMode: 'manyToMany',
    bidirectional: true,
    referModelAttribute: 'treeTestItems',
    resourcePath: 'tny-tree-tests',
    tree: true,
    primaryKey: 'id',
    primaryKeyType: 'Long',
    componentRelativePath: 'TreeTest',
    componentName: 'c-tny-tree-tests',
  }
}

export default model
