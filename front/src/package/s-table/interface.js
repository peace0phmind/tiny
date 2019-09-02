import * as Types from '../_util/javaTypes.js'
import * as utils from '../_util/util.js'

export const decorate = ({row, column, enums}) => {
  let {decorator, value} = _format({row, column, enums})

  if (value && decorator) {
    const {_avatar, _switch} = decorator
    if (_avatar) {
      value = value.startsWith('http') && value || `${_avatar.image.prefix}/${value}`
    } else if (_switch) {
      value = value === '是' && true || false
    }
  }

  return value
}

function _format({row, column, enums}) {
  let {slot, type, decorator, dateType, modelType, enumName} = column
  let _v = row[slot]

  if (type === Types.DateTime) return {decorator, value: _formatDate(dateType, _v)}

  else if (type === Types.Model) return {decorator, value: _formatModel(modelType, _v, enums, enumName)}

  else if (type === Types.Boolean) return {decorator, value: _formatBoolean(_v)}

  return {decorator, value: _v}
}

function _formatDate(dateType, value) {
  if (dateType === Types.DateType.Default) return utils.dateFormat(value)

  else if (dateType === Types.DateType.DateOnly) return utils.dateFormat(value, 'YYYY-MM-DD')
}

function _formatModel(modelType, value, enums, enumName) {
  if (modelType === Types.ModelType.Enum) return enums[enumName][value] && enums[enumName][value].name || ''

  else if (modelType === Types.ModelType.Common || modelType === Types.ModelType.Super) return value ? value._instanceName : ''
}

function _formatBoolean(value) {
  return value ? '是' : ''
}
