export const DateTime = 'DATETIME'

export const String = 'STRING'

export const Decimal = 'DECIMAL'

export const Model = 'MODEL'

export const Boolean = 'BOOLEAN'

export const Collection = 'COLLECTION'

const DateType = {
  Default: 'DEFAULT',
  DateOnly: 'DATE_ONLY',
  TimeOnly: 'TIME_ONLY'
}

const ModelType = {
  Enum: 'ENUM',
  Common: 'COMMON',
  Super: 'SUPER'
}

export {DateType, ModelType}

export const isCommonOrSuperModel = (type, modelType) => {
  return type === Model && [ModelType.Common, ModelType.Super].includes(modelType)
}

export const isEnum = (type, modelType) => {
  return type === Model && modelType === ModelType.Enum
}

export const isModel = (type) => {
  return type === Model
}

export const isDateOnly = (type, dateType) => {
  return type === DateTime && dateType === DateType.DateOnly
}

export const isDateTime = (type, dateType) => {
  return type === DateTime && dateType === DateType.Default
}

export const isTimeOnly = (type, dateType) => {
  return type === DateTime && dateType === DateType.TimeOnly
}

export const isDateType = (type) => {
  return type === DateTime
}

export const isCollection = (type) => {
  return type === Collection
}

export const isString = (type) => {
  return type === String
}

export const isDecimal = (type) => {
  return type === Decimal
}

export const isBoolean = (type) => {
  return type === Boolean
}
