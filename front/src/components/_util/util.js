import moment from 'moment'
import 'moment/locale/zh-cn'

moment.locale('zh-cn')

export const dateFormat = (date, pattern = 'YYYY-MM-DD HH:mm:ss') => date && moment(date).format(pattern)

export const convertFieldIdToJson = (data) => {
  Object.keys(data).forEach(k => {
    if (k.indexOf('.') > 0) {
      let v = data[k]
      delete data[k]
      data[k.split('.')[0]] = {}
      data[k.split('.')[0]][k.split('.')[1]] = v
    }
  })
  return data
}

export const removeFromArr = (arr, condition) => {

  const _arr = []

  for (let i = 0; i < arr.length; i++) {
    const item = arr[i]
    Object.keys(condition).forEach(key => {
      if (item[key] !== condition[key]) {
        _arr.push(item)
      }
    })
  }

  return _arr
}
