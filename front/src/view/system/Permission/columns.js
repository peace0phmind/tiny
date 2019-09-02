const columns = [
  {
    type: 'selection',
    width: 60,
    align: 'center'
  },
  {
    title: '模型名称',
    slot: 'modelName',
    tooltip: true,
    align: 'center',
    type: 'STRING',
    minWidth: 100,
  },
  {
    title: '操作',
    slot: '_action',
    width: 150,
    align: 'center',
    fixed: 'right'
  }
]

export default columns