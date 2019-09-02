/**
 * 基础扩展都放在此文件中做，此文件不会被覆盖
 * setting.field = {somekey...: someValue...}
 */
import searchLayout from './searchLayout'
import columnLayout from './columnLayout'
import formLayout from './formLayout'

import {
  mixinModel,
  mixinObjectArray,
  getFormItemsFromModel,
  getSearchItemsFromModel,
  getColumnsFromModel
} from '@/package/lib/tools.js'
import model from './model'


const columnsSetting = {}

const formItemsSetting = {}

const searchItemsSetting = {}

// 将 columns 中的 _action 属性的宽度调整为 220
columnsSetting._action = {
  width: 130
}

// 将 formItems 中的 权限，菜单，用户 设置为不显示
formItemsSetting.permissions = {
  notShow: true
}
formItemsSetting.menus = {
  notShow: true
}
formItemsSetting.users = {
  notShow: true
}

const modelSetting = {}

mixinModel(model, modelSetting)

export {model}

export const columns = mixinObjectArray(getColumnsFromModel(model, columnLayout), columnsSetting, 'slot')

export const formItems = mixinObjectArray(getFormItemsFromModel(model, formLayout), formItemsSetting, 'prop')

export const searchItems = mixinObjectArray(getSearchItemsFromModel(model, searchLayout), searchItemsSetting, 'key')
