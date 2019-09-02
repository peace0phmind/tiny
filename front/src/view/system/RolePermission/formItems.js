import permission_columns from '_v/system/Permission/columns'
import * as permission_enums from '_v/system/Permission/enums'
import permission_formItems from '_v/system/Permission/formItems'
import permission_searchItems from '_v/system/Permission/searchItems'

const formItems = [
  {
    label: '权限',
    type: 'MODEL',
    modelType: 'SUPER',
    key: 'permission.id',
    prop: 'permission',
    modelName: 'Permission',
    referenceMode: 'manyToOne',
    resourcePath: 'sys-permissions',
    componentPath: 'system/Permission/index.vue',
    componentName: 'c-sys-permissions',
    bidirectional: false,
    enums: permission_enums,
    formItems: permission_formItems,
    searchItems: permission_searchItems,
    arr: [],
    columns: permission_columns
  }
]

export default formItems
