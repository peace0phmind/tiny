/**
 * 基础扩展都放在此文件中做，此文件不会被覆盖
 * setting.field = {somekey...: someValue...}
 */
import * as viewMode from '@/package/_util/viewMode.js'
import searchLayout from './searchLayout'
import columnLayout from './columnLayout'
import formLayout from './formLayout'
import model from './model'
import {
  mixinModel,
  mixinObjectArray,
  getFormItemsFromModel,
  getSearchItemsFromModel,
  getColumnsFromModel
} from '@/package/lib/tools.js'

const columnsSetting = {
  password: {
    hide: true
  },
  newPassword: {
    hide: true
  }
}

const formItemsSetting = {
  enabled: {
    trueValue: '可用',
    falseValue: '不可'
  }
}

const searchItemsSetting = {}

const modelSetting = {
  password: {
    name: '密码',
    type: 'STRING',
    minLength: 6,
    required: true,
    readonly: false,
  },
  newPassword: {
    name: '确认密码',
    type: 'STRING',
    equalsTo: 'password',
    required: true,
    readonly: false,
  },
  sex: {
    multiple: false
  },
  roles: {
    isExist: true
  }
}

mixinModel(model, modelSetting)

export {model}

export const columns = mixinObjectArray(getColumnsFromModel(model, columnLayout), columnsSetting, 'slot')

export const formItems = mixinObjectArray(getFormItemsFromModel(model, formLayout), formItemsSetting, 'prop')

export const searchItems = mixinObjectArray(getSearchItemsFromModel(model, searchLayout), searchItemsSetting, 'key')
