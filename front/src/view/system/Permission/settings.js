/**
 * 基础扩展都放在此文件中做，此文件不会被覆盖
 * setting.field = {somekey...: someValue...}
 */
import searchLayout from './searchLayout'
import columnLayout from './columnLayout'
import {mixinObjectArray, getFormItemsFromModel, getSearchItemsFromModel, getColumnsFromModel} from 'zyx-proton-ui/package/lib/tools.js'
import model from './model'

const columnsSetting = {}

const formItemsSetting = {}

const searchItemsSetting = {}

export const columns = mixinObjectArray(getColumnsFromModel(model, columnLayout), columnsSetting, 'slot')

export const formItems = mixinObjectArray(getFormItemsFromModel(model), formItemsSetting, 'prop')

export const searchItems = mixinObjectArray(getSearchItemsFromModel(model, searchLayout), searchItemsSetting, 'key')
