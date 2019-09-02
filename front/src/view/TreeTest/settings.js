import searchLayout from './searchLayout'
import formLayout from './formLayout'
import {mixinModel, mixinObjectArray, getFormItemsFromModel, getSearchItemsFromModel} from '@/package/lib/tools.js'
import model from './model'

const formItemsSetting = {}

const searchItemsSetting = {}

const modelSetting = {}

mixinModel(model, modelSetting)

export {model}

export const formItems = mixinObjectArray(getFormItemsFromModel(model, formLayout), formItemsSetting, 'prop')

export const searchItems = mixinObjectArray(getSearchItemsFromModel(model, searchLayout), searchItemsSetting, 'key')
