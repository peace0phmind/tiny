const searchItems = [
  {
    label: '模型名称',
    type: 'STRING',
    key: 'modelName',
    operation: 'contains'
  },
  {
    label: '操作类型',
    type: 'MODEL',
    modelType: 'ENUM',
    key: 'type',
    operation: 'in'
  }
]

export default searchItems
