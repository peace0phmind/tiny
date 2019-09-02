import * as Types from '../_util/javaTypes'
import _ from 'lodash'
import store from '@/store'

export const forEach = (arr, fn) => {
  if (!arr.length || !fn) return
  let i = -1
  let len = arr.length
  while (++i < len) {
    let item = arr[i]
    fn(item, i, arr)
  }
}

/**
 * @param {Array} arr1
 * @param {Array} arr2
 * @description 得到两个数组的交集, 两个数组的元素为数值或字符串
 */
export const getIntersection = (arr1, arr2) => {
  let len = Math.min(arr1.length, arr2.length)
  let i = -1
  let res = []
  while (++i < len) {
    const item = arr2[i]
    if (arr1.indexOf(item) > -1) res.push(item)
  }
  return res
}

/**
 * @param {Array} arr1
 * @param {Array} arr2
 * @description 得到两个数组的并集, 两个数组的元素为数值或字符串
 */
export const getUnion = (arr1, arr2) => {
  return Array.from(new Set([...arr1, ...arr2]))
}

/**
 * @param {Array} target 目标数组
 * @param {Array} arr 需要查询的数组
 * @description 判断要查询的数组是否至少有一个元素包含在目标数组中
 */
export const hasOneOf = (targetarr, arr) => {
  return targetarr.some(_ => arr.indexOf(_) > -1)
}

/**
 * @param {String|Number} value 要验证的字符串或数值
 * @param {*} validList 用来验证的列表
 */
export function oneOf(value, validList) {
  for (let i = 0; i < validList.length; i++) {
    if (value === validList[i]) {
      return true
    }
  }
  return false
}

/**
 * @param {Number} timeStamp 判断时间戳格式是否是毫秒
 * @returns {Boolean}
 */
const isMillisecond = timeStamp => {
  const timeStr = String(timeStamp)
  return timeStr.length > 10
}

/**
 * @param {Number} timeStamp 传入的时间戳
 * @param {Number} currentTime 当前时间时间戳
 * @returns {Boolean} 传入的时间戳是否早于当前时间戳
 */
const isEarly = (timeStamp, currentTime) => {
  return timeStamp < currentTime
}

/**
 * @param {Number} num 数值
 * @returns {String} 处理后的字符串
 * @description 如果传入的数值小于10，即位数只有1位，则在前面补充0
 */
const getHandledValue = num => {
  return num < 10 ? '0' + num : num
}

/**
 * @param {Number} timeStamp 传入的时间戳
 * @param {Number} startType 要返回的时间字符串的格式类型，传入'year'则返回年开头的完整时间
 */
const getDate = (timeStamp, startType) => {
  const d = new Date(timeStamp * 1000)
  const year = d.getFullYear()
  const month = getHandledValue(d.getMonth() + 1)
  const date = getHandledValue(d.getDate())
  const hours = getHandledValue(d.getHours())
  const minutes = getHandledValue(d.getMinutes())
  const second = getHandledValue(d.getSeconds())
  let resStr = ''
  if (startType === 'year') resStr = year + '-' + month + '-' + date + ' ' + hours + ':' + minutes + ':' + second
  else resStr = month + '-' + date + ' ' + hours + ':' + minutes
  return resStr
}

/**
 * @param {String|Number} timeStamp 时间戳
 * @returns {String} 相对时间字符串
 */
export const getRelativeTime = timeStamp => {
  // 判断当前传入的时间戳是秒格式还是毫秒
  const IS_MILLISECOND = isMillisecond(timeStamp)
  // 如果是毫秒格式则转为秒格式
  if (IS_MILLISECOND) Math.floor(timeStamp /= 1000)
  // 传入的时间戳可以是数值或字符串类型，这里统一转为数值类型
  timeStamp = Number(timeStamp)
  // 获取当前时间时间戳
  const currentTime = Math.floor(Date.parse(new Date()) / 1000)
  // 判断传入时间戳是否早于当前时间戳
  const IS_EARLY = isEarly(timeStamp, currentTime)
  // 获取两个时间戳差值
  let diff = currentTime - timeStamp
  // 如果IS_EARLY为false则差值取反
  if (!IS_EARLY) diff = -diff
  let resStr = ''
  const dirStr = IS_EARLY ? '前' : '后'
  // 少于等于59秒
  if (diff <= 59) resStr = diff + '秒' + dirStr
  // 多于59秒，少于等于59分钟59秒
  else if (diff > 59 && diff <= 3599) resStr = Math.floor(diff / 60) + '分钟' + dirStr
  // 多于59分钟59秒，少于等于23小时59分钟59秒
  else if (diff > 3599 && diff <= 86399) resStr = Math.floor(diff / 3600) + '小时' + dirStr
  // 多于23小时59分钟59秒，少于等于29天59分钟59秒
  else if (diff > 86399 && diff <= 2623859) resStr = Math.floor(diff / 86400) + '天' + dirStr
  // 多于29天59分钟59秒，少于364天23小时59分钟59秒，且传入的时间戳早于当前
  else if (diff > 2623859 && diff <= 31567859 && IS_EARLY) resStr = getDate(timeStamp)
  else resStr = getDate(timeStamp, 'year')
  return resStr
}

/**
 * @returns {String} 当前浏览器名称
 */
export const getExplorer = () => {
  const ua = window.navigator.userAgent
  const isExplorer = (exp) => {
    return ua.indexOf(exp) > -1
  }
  if (isExplorer('MSIE')) return 'IE'
  else if (isExplorer('Firefox')) return 'Firefox'
  else if (isExplorer('Chrome')) return 'Chrome'
  else if (isExplorer('Opera')) return 'Opera'
  else if (isExplorer('Safari')) return 'Safari'
}

/**
 * @description 绑定事件 on(element, event, handler)
 */
export const on = (function () {
  if (document.addEventListener) {
    return function (element, event, handler) {
      if (element && event && handler) {
        element.addEventListener(event, handler, false)
      }
    }
  } else {
    return function (element, event, handler) {
      if (element && event && handler) {
        element.attachEvent('on' + event, handler)
      }
    }
  }
})()

/**
 * @description 解绑事件 off(element, event, handler)
 */
export const off = (function () {
  if (document.removeEventListener) {
    return function (element, event, handler) {
      if (element && event) {
        element.removeEventListener(event, handler, false)
      }
    }
  } else {
    return function (element, event, handler) {
      if (element && event) {
        element.detachEvent('on' + event, handler)
      }
    }
  }
})()

/**
 * 判断一个对象是否存在key，如果传入第二个参数key，则是判断这个obj对象是否存在key这个属性
 * 如果没有传入key这个参数，则判断obj对象是否有键值对
 */
export const hasKey = (obj, key) => {
  if (key) return key in obj
  else {
    let keysArr = Object.keys(obj)
    return keysArr.length
  }
}

/**
 * @param {*} obj1 对象
 * @param {*} obj2 对象
 * @description 判断两个对象是否相等，这两个对象的值只能是数字或字符串或普通數組
 */
export const objEqual = (obj1, obj2) => {
  const keysArr1 = Object.keys(obj1)
  const keysArr2 = Object.keys(obj2)
  if (keysArr1.length !== keysArr2.length) return false
  else if (keysArr1.length === 0 && keysArr2.length === 0) return true
  else return !keysArr1.some(key => {
      if (typeof obj1[key] === 'Array') {
        if (typeof obj2[key] === 'Array') {
          return true
        } else {
          return true
        }
      } else {
        if (typeof obj2[key] === 'Array') return true
        else return obj1[key] != obj2[key]
      }
    })
}

/**
 * 找出两个对象之间的变动
 *
 * @param newObj
 * @param oldObj
 */
export const findObjChange = (newObj, oldObj) => {
  let change = undefined

  const newObjKeys = Object.keys(newObj)
  const oldObjKeys = Object.keys(oldObj)

  if (newObjKeys.length !== 0 || oldObjKeys.length !== 0) { //当两个对象的length有其一不为0时
    change = {}

    // 判断 newKey 在 oldObj中存不存在，存在则比对值，不存在直接添加到 change 中
    Object.entries(newObj).forEach(([newKey, newValue]) => {
      if (!oldObj.hasOwnProperty(newKey)) {
        change[newKey] = newValue
      } else {
        !_.isEqual(newValue, oldObj[newKey]) && (change[newKey] = newValue)
      }
    })

    // 判断 oldKey 在 newObj 中存不存在，不存在直接添加到 change 中
    Object.entries(oldObj).forEach(([oldKey, oldValue]) => {
      if (!newObj.hasOwnProperty(oldKey)) change[oldKey] = null
    })
  }

  return change
}

/**
 * 获取当前页面标题
 * @param vm
 * @returns {*}
 */
export const getCurrentPageTitle = (vm) => {
  return vm.$route.meta.title
}

/**
 * recursive to copy object & rename attr, return new object
 * @param parameter
 * @param [parameter.src = {}] src object
 * @param [parameter.correspondence = {a: b, c: d}] correspondence of every attr
 * TODO recursive -> foreach
 */
export function copyObjectRenameAttrRecursive(parameter = {}) {
  let {src = null, correspondence = {}, ignore = true} = parameter
  /* src object is null */
  if (src == null || src === undefined) return {}

  /* copy */
  let to = {}
  Object.entries(src).forEach(([key, value]) => {
    if (value instanceof Array) {
      if (correspondence[key]) { // if has correspondence
        let arr = to[correspondence[key]] = []
        value.forEach(item => {
          arr.push(copyObjectRenameAttrRecursive({src: item, correspondence, ignore}))
        })
      } else {
        to[key] = value
      }
    } else {
      correspondence[key] && (to[correspondence[key]] = value) || (!ignore && (to[key] = value))
    }
  })
  return to
}

/**
 * recursive to copy arr[object] & rename object attr, return new arr[object]
 * @param parameter
 * @param [parameter.src = {}] src object
 * @param [parameter.correspondence = {a: b, c: d}] correspondence of every attr
 */
export function copyObjectRenameAttrInArrRecursive(parameter = {}) {
  let {src = [], correspondence = {}, ignore = true} = parameter
  /* src object is null */
  if (src == null || src === undefined || src.length === 0) return []

  /* copy */
  let to = []
  src.forEach(item => {
    to.push(copyObjectRenameAttrRecursive({src: item, correspondence, ignore}))
  })
  return to
}

/**
 * 过滤对象中为空的属性
 * @param obj
 * @returns {*}
 */
export const filterObj = (obj) => {
  if (!(typeof obj === 'object')) {
    return
  }
  for (let key in obj) {
    if (obj.hasOwnProperty(key) &&
      (obj[key] == null || obj[key] === undefined || obj[key] === '' || (obj[key] instanceof Array && !obj[key].length))) {
      delete obj[key]
    }
  }
  return obj
}

// mixin array
export const mixinObjectArray = function (src, extra, fieldName) {
  src.forEach(item => {
    Object.keys(extra).forEach(key => {
      const values = extra[key]
      if (item[fieldName] === key) {
        Object.keys(values).forEach(subKey => {
          item[subKey] = values[subKey]
        })
      }
    })
  })
  return src
}


// get form items from model
export const getFormItemsFromModel = function (model, formLayout) {
  const formItems = Object.entries(model).map(([key, value]) => {
    let item = {prop: key}
    item = Object.assign({}, item, value)
    const layout = formLayout.flat(1).find(layout => layout.prop === key)
    if (layout) item = Object.assign({}, item, layout)
    !item.label && (item.label = item.name)
    !item.placeholder && (item.placeholder = item.name)
    delete item.name
    if (item.referenceMode === 'manyToOne') {
      if (item.primaryKey) {
        item.key = `${key}.${item.primaryKey}`
      } else {
        item.key = `${key}.id`
      }
      item.arr = []
    } else {
      item.key = key
    }
    return item
  })

  return formItems
}
// get columns from model
export const getColumnsFromModel = function (model, columnLayout) {
  const columns = Object.entries(model).filter(([key, value]) => !value.collection || (value.collection && Types.isEnum(value.type, value.modelType))).map(([key, value]) => {
    let item = {prop: key}
    item = Object.assign({}, item, value)
    item.title = item.name
    delete item.name
    item.slot = key
    item.tooltip = true
    item.align = 'center'
    item.minWidth = 100
    return item
  })

  const columnMeta = []

  // mixin attr
  columnLayout.forEach(col => {

    if (!col.slot) return

    if (col.type !== 'selection' && col.slot !== '_action') {

      const {title, tooltip, align, minWidth, fixed, hide, sortable, defaultSort, flatAttr, renderTo, summary, template, tagColor, dotStatus} = col
      const column = columns.find(column => column.slot === col.slot)

      const meta = Object.assign({}, column)

      title && (meta.title = col.title)
      tooltip && (meta.tooltip = col.tooltip)
      align && (meta.align = col.align)
      minWidth && (meta.minWidth = col.minWidth)
      fixed && (meta.fixed = col.fixed)
      hide && (meta.hide = col.hide)
      sortable && (meta.sortable = col.sortable)
      defaultSort && (meta.defaultSort = col.defaultSort)
      flatAttr && (meta.flatAttr = col.flatAttr)
      renderTo && (meta.renderTo = col.renderTo)
      summary && (meta.summary = col.summary)
      template && (meta.template = col.template)
      tagColor && (meta.tagColor = col.tagColor)
      dotStatus && (meta.dotStatus = col.dotStatus)
      columnMeta.push(meta)
    }
  })

  // mixin item not exist in column layout
  columns.forEach(column => {
    const {slot} = column
    const col = columnLayout.find(col => col.slot === slot)
    if (!col) {
      columnMeta.push(Object.assign(column))
    }
  })

  columnLayout.forEach(col => {
    const {type, slot, minWidth, width} = col
    if (type === 'selection') {
      columnMeta.splice(0, 0, col)
    } else if (slot === '_action') {
      if (minWidth) {
        col.width = minWidth
        delete col.minWidth
      }
      if (!width) {
        col.width = 150
      }
      columnMeta.push(col)
    }
  })

  const selection = columnMeta.find(col => col.type === 'selection')
  if (!selection) {
    columnMeta.splice(0, 0, {
      slot: 'selection',
      title: 'selection',
      type: 'selection',
      align: 'center',
    })
  }

  const action = columnMeta.find(col => col.slot === '_action')
  if (!action) {
    columnMeta.push({
      slot: '_action',
      title: '操作',
      align: 'center',
      fixed: 'right',
      width: 150
    })
  }

  return columnMeta
}

// get search items from model
export const getSearchItemsFromModel = function (model, searchLayout) {
  const searchItems = Object.entries(model).filter(([key, value]) => !value.collection || (value.collection && Types.isEnum(value.type, value.modelType))).map(([key, value]) => {
    let item = {prop: key}
    item = Object.assign({}, item, value)
    item.label = item.name
    delete item.name
    // if (item.primaryKey) {
    //   item.key = `${key}${item.primaryKey.toLowerCase().replace(/( |^)[a-z]/g, (L) => L.toUpperCase())}`
    //   item.arr = []
    // } else {
    item.key = key
    // }
    const {type} = item
    let operation = 'equals'
    switch (type) {
      case Types.String:
        operation = 'contains'
        break
      case Types.Boolean:
        operation = 'equals'
        break
      case Types.Decimal:
      case Types.DateTime:
        operation = 'range'
        break
      case Types.Model:
        operation = 'in'
        break
    }
    item.operation = operation
    return item
  })

  const {alwaysShow, extra} = searchLayout
  alwaysShow.forEach(se => {
    const {prop, operation, label, hide} = se
    const item = searchItems.find(ite => ite.prop === prop)
    if (item) {
      item.defaultShow = true
      operation && (item.operation = operation)
      label && (item.label = label)
      hide && (item.hide = hide)
    }
  })

  extra.forEach(se => {
    const {prop, operation, label, hide} = se
    const item = searchItems.find(ite => ite.prop === prop)
    if (item) {
      operation && (item.operation = operation)
      label && (item.label = label)
      hide && (item.hide = hide)
    }
  })

  return searchItems
}

export const mixinModel = (model, modelSetting) => {
  Object.entries(modelSetting).forEach(([key, option]) => {
    const name = Object.keys(model).find((name) => name === key)
    if (name) {
      Object.entries(option).forEach(([k, v]) => model[name][k] = v)
    } else {
      model[key] = option
    }
  })

  Object.keys(model).forEach(key => {
    const {collection} = model[key]
    if (collection) model[key].viewMode = 'form_table'
  })
}

export const hasPermission = (modelUri, type) => {
  type = _.toUpper(type)
  const roles = store.state.user.roles
  if (modelUri && type) {
    const hasPermission = roles.some(role => {
      if (role.type === 'SUPER') return true

      else {
        return !role.permissions && true || !(role.permissions.some(permission =>
          permission.allow === false && permission.permission.type === type && permission.permission.modelUri === modelUri))
      }
    })

    return hasPermission
  } else {
    console.error(`need permission! Like v-permission.resource="'sys-roles'"`, modelUri, type)
    return true
  }
}

