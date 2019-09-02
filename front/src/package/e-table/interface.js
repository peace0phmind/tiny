import * as Types from '../_util/javaTypes.js'
import * as RenderTypes from '../_util/renderType'
import * as utils from '../_util/util.js'
import _ from 'lodash'

export const decorate = ({row, column, enums, pool}) => {
  let {renderTo, value} = _format({row, column, enums})
  let {template, type, modelType, componentName} = column
  if (value) {

    if (template) {
      if (Types.isEnum(type, modelType)) {
        value = _.replace(template, '${v}', value)
      } else if (Types.isCommonOrSuperModel(type, modelType)) {
        template = _.replace(template, '${v}', value._instanceName)
        const {meta: {_formItems: destFormItems}} = pool[componentName]
        if (destFormItems) {
          destFormItems.forEach(destFormItem => {
            const {prop} = destFormItem
            template = _.replace(template, `\${v.${prop}}`, value[prop] && value[prop] || '-')
          })
        }
        value = template
      } else {
        value = _.replace(template, '${v}', value)
      }
    } else {
      Types.isCommonOrSuperModel(type, modelType) && (value = value && value._instanceName)
    }

    if (renderTo === RenderTypes.SWITCH) {
      value = value === '是' && true || false
    }

    if (Types.isEnum(type, modelType) && value instanceof Array) {
      value = value.join(',')
    }
  }
  return value
}

function _format({row, column, enums}) {
  let {slot, type, decorator, dateType, modelType, enumName, renderTo} = column
  let _v = row[slot]
  if (type === Types.DateTime) return {decorator, value: _formatDate(dateType, _v), renderTo}

  else if (type === Types.Model) return {decorator, value: _formatModel(modelType, _v, enums, enumName), renderTo}

  else if (type === Types.Boolean) return {decorator, value: _formatBoolean(_v), renderTo}

  return {decorator, value: _v, renderTo}
}

function _formatDate(dateType, value) {
  if (dateType === Types.DateType.Default) return utils.dateFormat(value)

  else if (dateType === Types.DateType.DateOnly) return utils.dateFormat(value, 'YYYY-MM-DD')
}

function _formatModel(modelType, value, enums, enumName) {
  if (modelType === Types.ModelType.Enum) {

    if (value) {
      if (value instanceof Array) return value.map(v => enums[enumName][v] && enums[enumName][v].name || '')
      else return enums[enumName][value] && enums[enumName][value].name || ''
    }

    return ''
  } else if (modelType === Types.ModelType.Common || modelType === Types.ModelType.Super) return value
}

function _formatBoolean(value) {
  return value ? '是' : ''
}
