import {columns, formItems, searchItems, model} from './settings'
import formLayout from './formLayout'
import * as enums from './enums'

/* 默认的每页记录数 */
export const pageSize = 10

/* 分页切换配置 必须包含 pageSize */
export const pageSizeOpts = [10, 20, 50, 100]

/* 表单形式 Drawer抽屉 Modal弹窗 */
// export const formMode = 'Drawer'
export const formMode = 'Modal'

export const modalWidth = 760

export const modalFullscreen = false

export const submitText = '提交'

/* 用到的枚举 */
export const _enums = enums

/* 远程接口地址 */
export const restfulResourcePath = 'tny-tree-tests'

export const name = 'c-tny-tree-tests'

/* 接口返回值的配置映射 */
export const props = {
  label: '_instanceName',
  children: '_children',
  isLeaf: '_leaf',
  id: 'id',
  list: 'content'
}

export const orgTreeProps = {
  id: 'id',
  label: 'name',
  children: '_children'
}

export const orgTreeSetting = {
  color: '#3d88fe',
  fontSize: '12px',
  defaultExpandLevel: 5,
  levelOptions: [5, 10, 20, 30]
}

/* 表单元素配置 */
export const _formItems = formItems

/* 表单布局配置 */
export const _formLayout = formLayout

/* 搜索条件元素配置 */
export const _searchItems = searchItems

/* 复选框显示 */
export const showCheckbox = true

/* 高亮当前行 */
export const highlightCurrent = true

/* 父子节点选择时候不联动 */
export const checkStrictly = false

/* 点击节点时是否展开节点 */
export const expandOnClickNode = false

/* 是否可拖拽 */
export const draggable = true

/* 默认展开层级数量 */
export const defaultExpandLevel = 2

export {model}