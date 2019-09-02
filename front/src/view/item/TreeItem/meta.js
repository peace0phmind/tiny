import {columns, formItems, searchItems, model} from './settings'
import formLayout from './formLayout'
import * as enums from './enums'

/* 默认的每页记录数 */
export const pageSize = 10

/* 分页切换配置 必须包含 pageSize */
export const pageSizeOpts = [10, 20, 50, 100]

/* 用到的枚举 */
export const _enums = enums

/* 远程接口地址 */
export const restfulResourcePath = 'tny-tree-items'
export const name = 'c-tny-tree-items'

/* 接口返回值的配置映射 */
export const props = {
  list: 'content',
  total: 'totalElements',
  id: 'id'
}

/* 列配置 */
export const _columns = columns

/* 表单元素配置 */
export const _formItems = formItems

/* 表单布局配置 */
export const _formLayout = formLayout

/* 搜索条件元素配置 */
export const _searchItems = searchItems
export {model}