const columns = [
  {
    type: 'selection',
    width: 60,
    align: 'center'
  },
  {
    title: '权限',
    slot: 'permission',
    tooltip: true,
    align: 'center',
    type: 'MODEL',
    minWidth: 100,
    modelType: 'SUPER',
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