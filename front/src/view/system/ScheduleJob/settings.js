/**
 * 基础扩展都放在此文件中做，此文件不会被覆盖
 * setting.field = {someKey...: someValue...}
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

const columnsSetting = {
  active:{
    customRender: true
  }
}

const formItemsSetting = {
  beanName:{
    valid: {
      required: { // 校验必选
        value: true,
        message: '' // 校验提示，可省略此字段
      },
      validator(rule,value,callback) {
        if (value.length > 180) {
          return callback(new Error('beanName长度不大于180位'))
        } else {
          callback()
        }
      }
    }
  },
  methodName:{
    valid: {
      required: { // 校验必选
        value: true,
        message: '' // 校验提示，可省略此字段
      },
      validator(rule,value,callback) {
        if (value.length > 100) {
          return callback(new Error('方法名称长度不大于180位'))
        } else {
          callback()
        }
      }
    }
  },
  cronExpression:{
    customRender: true,
    valid: {
      required: { // 校验必选
        value: true,
        message: '' // 校验提示，可省略此字段
      },
      validator(rule,value,callback) {
        if (value.length > 100) {
          return callback(new Error('cron表达式长度不大于180位'))
        } else {
          callback()
        }
      }
    }
  },
  active: {
    defaultValue: true,
  },
  singleCase:{
    defaultValue: true,
  },
  logs:{
    notShow: true,
  },
}

const searchItemsSetting = {}

const modelSetting = {}

mixinModel(model, modelSetting)

export {model}

export const columns = mixinObjectArray(getColumnsFromModel(model, columnLayout), columnsSetting, 'slot')

export const formItems = mixinObjectArray(getFormItemsFromModel(model, formLayout), formItemsSetting, 'prop')

export const searchItems = mixinObjectArray(getSearchItemsFromModel(model, searchLayout), searchItemsSetting, 'key')
