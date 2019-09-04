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

const modelSetting = {
  name: {
    unique: true
  },
  templateType: {
    required: true
  }
}

mixinModel(model, modelSetting)

export {model}

export const columns = mixinObjectArray(getColumnsFromModel(model, columnLayout), columnsSetting, 'slot')

export const formItems = mixinObjectArray(getFormItemsFromModel(model, formLayout), formItemsSetting, 'prop')

export const searchItems = mixinObjectArray(getSearchItemsFromModel(model, searchLayout), searchItemsSetting, 'key')
