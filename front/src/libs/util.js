import Cookies from 'js-cookie'
// cookie保存的天数
import config from '@/config'
import {forEach, hasOneOf, objEqual} from '@/libs/tools'
import lazyLoading from './lazyLoading.js'
import * as Types from '../components/_util/javaTypes'

const {title, cookieExpires, useI18n} = config

export const TOKEN_KEY = 'token'

export const setToken = (token) => {
  Cookies.set(TOKEN_KEY, token, {expires: cookieExpires || 1})
}

export const getToken = () => {
  const token = Cookies.get(TOKEN_KEY)
  if (token) {
    return token
  } else {
    return false
  }
}

export const hasChild = (item) => {
  return item.children && item.children.length !== 0
}

const showThisMenuEle = (item, access) => {
  if (item.meta && item.meta.access && item.meta.access.length) {
    const accessList = item.meta.access.map(role => role.innerName)
    if (hasOneOf(accessList, access)) {
      return true
    } else {
      return false
    }
  } else {
    return true
  }
}
/**
 * @param {Array} list 通过路由列表得到菜单列表
 * @returns {Array}
 */
export const getMenuByRouter = (list, access, roles) => {

  const superAdmin = roles && roles.find(role => role.type === 'SUPER')
  if (superAdmin) return list

  let res = []
  forEach(list, item => {
    if (!item.meta || (item.meta && !item.meta.hideInMenu)) {
      let obj = {
        icon: (item.meta && item.meta.icon) || '',
        name: item.name,
        meta: item.meta
      }
      if ((hasChild(item) || (item.meta && item.meta.showAlways)) && showThisMenuEle(item, access)) {
        obj.children = getMenuByRouter(item.children, access)
      }
      if (item.meta && item.meta.href) obj.href = item.meta.href
      if (showThisMenuEle(item, access)) res.push(obj)
    }
  })
  return res
}

/**
 * @param {Array} routeMetched 当前路由metched
 * @returns {Array}
 */
export const getBreadCrumbList = (route, homeRoute) => {
  let homeItem = {...homeRoute, icon: homeRoute.meta.icon}
  let routeMetched = route.matched
  if (routeMetched.some(item => item.name === homeRoute.name)) return [homeItem]
  let res = routeMetched.filter(item => {
    return item.meta === undefined || !item.meta.hideInBread
  }).map(item => {
    let meta = {...item.meta}
    if (meta.title && typeof meta.title === 'function') {
      meta.__titleIsFunction__ = true
      meta.title = meta.title(route)
    }
    let obj = {
      icon: (item.meta && item.meta.icon) || '',
      name: item.name,
      meta: meta
    }
    return obj
  })
  res = res.filter(item => {
    return !item.meta.hideInMenu
  })
  return [{...homeItem, to: homeRoute.path}, ...res]
}

export const getRouteTitleHandled = (route) => {
  let router = {...route}
  let meta = {...route.meta}
  let title = ''
  if (meta.title) {
    if (typeof meta.title === 'function') {
      meta.__titleIsFunction__ = true
      title = meta.title(router)
    } else {
      title = meta.title
    }
  }
  meta.title = title
  router.meta = meta
  return router
}

export const showTitle = (item, vm) => {
  let {title, __titleIsFunction__} = item.meta
  // if (!title) return
  // if (useI18n) {
  //   if (title.includes('{{') && title.includes('}}') && useI18n) {
  //     title = title.replace(/({{[\s\S]+?}})/, (m, str) => str.replace(/{{([\s\S]*)}}/, (m, _) => vm.$t(_.trim())))
  //   } else if (__titleIsFunction__) {
  //     title = item.meta.title
  //   } else {
  //     title = vm.$t(item.name)
  //   }
  // } else {
  //   title = (item.meta && item.meta.title) || item.name
  // }
  return title
}

/**
 * @description 本地存储和获取标签导航列表
 */
export const setTagNavListInLocalstorage = list => {
  localStorage.tagNaveList = JSON.stringify(list)
}
/**
 * @returns {Array} 其中的每个元素只包含路由原信息中的name, path, meta三项
 */
export const getTagNavListFromLocalstorage = () => {
  const list = localStorage.tagNaveList
  return list ? JSON.parse(list) : []
}

/**
 * @param {Array} routers 路由列表数组
 * @description 用于找到路由列表中name为home的对象
 */
export const getHomeRoute = (routers, homeName = 'home') => {
  let i = -1
  let len = routers.length
  let homeRoute = {}
  while (++i < len) {
    let item = routers[i]
    if (item.children && item.children.length) {
      let res = getHomeRoute(item.children, homeName)
      if (res.name) return res
    } else {
      if (item.name === homeName) homeRoute = item
    }
  }
  return homeRoute
}

/**
 * @param {*} list 现有标签导航列表
 * @param {*} newRoute 新添加的路由原信息对象
 * @description 如果该newRoute已经存在则不再添加
 */
export const getNewTagList = (list, newRoute) => {
  const {name, path, meta} = newRoute
  let newList = [...list]
  if (newList.findIndex(item => item.name === name) >= 0) {
    return newList
  } else {
    newList.push({name, path, meta})
  }
  return newList
}

/**
 * @param {*} access 用户权限数组，如 ['super_admin', 'admin']
 * @param {*} route 路由列表
 */
const hasAccess = (access, roles, route) => {
  const superAdmin = roles.find(role => role.superAdmin === true)
  if (superAdmin) return true

  if (route.meta && route.meta.access) {
    return hasOneOf(access, route.meta.access)
  } else {
    return true
  }
}

/**
 * 权鉴
 * @param {*} name 即将跳转的路由name
 * @param {*} access 用户权限数组
 * @param {*} routes 路由列表
 * @description 用户是否可跳转到该页
 */
export const canTurnTo = (name, access, roles, routes, store) => {
  const superAdmin = roles.find(role => role.type === 'SUPER')
  if (superAdmin) return true

  const routePermissionJudge = (list) => {
    return list.some(item => {
      if (item.children && item.children.length) {
        return routePermissionJudge(item.children)
      } else if (item.name === name) {
        return hasAccess(access, roles, item)
      }
    })
  }

  const localTurnTo = routePermissionJudge(routes)

  if (localTurnTo) {
    return true
  } else {

    const hasRole = (roles, access) => {
      for (let role of roles) {
        if (access === undefined || access.length === 0 || access.findIndex(acc => acc === role.innerName) >= 0) {
          return true
        }
      }
      return false
    }

    const menuPermissionJudge = (list) => {
      return list.some(item => {
        if (item.children && item.children.length) {
          return menuPermissionJudge(item.children)
        } else if (item.name === name) {
          return hasRole(roles, item.access)
        }
      })
    }
    return menuPermissionJudge(store.getters.menuList)
  }

}

/**
 * @param {String} url
 * @description 从URL中解析参数
 */
export const getParams = url => {
  const keyValueArr = url.split('?')[1].split('&')
  let paramObj = {}
  keyValueArr.forEach(item => {
    const keyValue = item.split('=')
    paramObj[keyValue[0]] = keyValue[1]
  })
  return paramObj
}

/**
 * @param {Array} list 标签列表
 * @param {String} name 当前关闭的标签的name
 */
export const getNextRoute = (list, route) => {
  let res = {}
  if (list.length === 2) {
    res = getHomeRoute(list)
  } else {
    const index = list.findIndex(item => routeEqual(item, route))
    if (index === list.length - 1) {
      res = list[list.length - 2]
    } else {
      res = list[index + 1]
    }
  }
  return res
}

/**
 * @param {Number} times 回调函数需要执行的次数
 * @param {Function} callback 回调函数
 */
export const doCustomTimes = (times, callback) => {
  let i = -1
  while (++i < times) {
    callback(i)
  }
}

/**
 * @param {Object} file 从上传组件得到的文件对象
 * @returns {Promise} resolve参数是解析后的二维数组
 * @description 从Csv文件中解析出表格，解析成二维数组
 */
export const getArrayFromFile = (file) => {
  let nameSplit = file.name.split('.')
  let format = nameSplit[nameSplit.length - 1]
  return new Promise((resolve, reject) => {
    let reader = new FileReader()
    reader.readAsText(file) // 以文本格式读取
    let arr = []
    reader.onload = function (evt) {
      let data = evt.target.result // 读到的数据
      let pasteData = data.trim()
      arr = pasteData.split((/[\n\u0085\u2028\u2029]|\r\n?/g)).map(row => {
        return row.split('\t')
      }).map(item => {
        return item[0].split(',')
      })
      if (format === 'csv') {
        resolve(arr)
      } else {
        reject(new Error('[Format Error]:你上传的不是Csv文件'))
      }
    }
  })
}

/**
 * @param {Array} array 表格数据二维数组
 * @returns {Object} { columns, tableData }
 * @description 从二维数组中获取表头和表格数据，将第一行作为表头，用于在iView的表格中展示数据
 */
export const getTableDataFromArray = (array) => {
  let columns = []
  let tableData = []
  if (array.length > 1) {
    let titles = array.shift()
    columns = titles.map(item => {
      return {
        title: item,
        key: item
      }
    })
    tableData = array.map(item => {
      let res = {}
      item.forEach((col, i) => {
        res[titles[i]] = col
      })
      return res
    })
  }
  return {
    columns,
    tableData
  }
}

export const findNodeUpper = (ele, tag) => {
  if (ele.parentNode) {
    if (ele.parentNode.tagName === tag.toUpperCase()) {
      return ele.parentNode
    } else {
      return findNodeUpper(ele.parentNode, tag)
    }
  }
}

export const findNodeUpperByClasses = (ele, classes) => {
  let parentNode = ele.parentNode
  if (parentNode) {
    let classList = parentNode.classList
    if (classList && classes.every(className => classList.contains(className))) {
      return parentNode
    } else {
      return findNodeUpperByClasses(parentNode, classes)
    }
  }
}

export const findNodeDownward = (ele, tag) => {
  const tagName = tag.toUpperCase()
  if (ele.childNodes.length) {
    let i = -1
    let len = ele.childNodes.length
    while (++i < len) {
      let child = ele.childNodes[i]
      if (child.tagName === tagName) {
        return child
      } else {
        return findNodeDownward(child, tag)
      }
    }
  }
}

export const showByAccess = (access, canViewAccess) => {
  return hasOneOf(canViewAccess, access)
}

/**
 * @description 根据name/params/query判断两个路由对象是否相等
 * @param {*} route1 路由对象
 * @param {*} route2 路由对象
 */
export const routeEqual = (route1, route2) => {
  const params1 = route1.params || {}
  const params2 = route2.params || {}
  const query1 = route1.query || {}
  const query2 = route2.query || {}
  return (route1.name === route2.name) && objEqual(params1, params2) && objEqual(query1, query2)
}

/**
 * 判断打开的标签列表里是否已存在这个新添加的路由对象
 */
export const routeHasExist = (tagNavList, routeItem) => {
  let len = tagNavList.length
  let res = false
  doCustomTimes(len, (index) => {
    if (routeEqual(tagNavList[index], routeItem)) res = true
  })
  return res
}

export const localSave = (key, value) => {
  localStorage.setItem(key, value)
}

export const localRead = (key) => {
  return localStorage.getItem(key) || ''
}

// scrollTop animation
export const scrollTop = (el, from = 0, to, duration = 500, endCallback) => {
  if (!window.requestAnimationFrame) {
    window.requestAnimationFrame = (
      window.webkitRequestAnimationFrame ||
      window.mozRequestAnimationFrame ||
      window.msRequestAnimationFrame ||
      function (callback) {
        return window.setTimeout(callback, 1000 / 60)
      }
    )
  }
  const difference = Math.abs(from - to)
  const step = Math.ceil(difference / duration * 50)

  const scroll = (start, end, step) => {
    if (start === end) {
      endCallback && endCallback()
      return
    }

    let d = (start + step > end) ? end : start + step
    if (start > end) {
      d = (start - step < end) ? end : start - step
    }

    if (el === window) {
      window.scrollTo(d, d)
    } else {
      el.scrollTop = d
    }
    window.requestAnimationFrame(() => scroll(d, end, step))
  }
  scroll(from, to, step)
}

/**
 * @description 根据当前跳转的路由设置显示在浏览器标签的title
 * @param {Object} routeItem 路由对象
 * @param {Object} vm Vue实例
 */
export const setTitle = (routeItem, vm) => {
  const handledRoute = getRouteTitleHandled(routeItem)
  const pageTitle = showTitle(handledRoute, vm)
  const resTitle = pageTitle ? `${title} - ${pageTitle}` : title
  window.document.title = resTitle
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
  for (var key in obj) {
    if (obj.hasOwnProperty(key) &&
      (obj[key] == null || obj[key] === undefined || obj[key] === '')) {
      delete obj[key]
    }
  }
  return obj
}

/**
 * @description 取出修改过的值
 * @param newObject
 * @param oldObject
 */
export const getModifiedValueInObjects = (newObject, oldObject) => {
  let modified = {}
  Object.keys(oldObject).forEach(key => {
    if (typeof oldObject[key] === 'Object') {
      if (oldObject[key] instanceof Array) { //数组
        if (oldObject[key].length !== newObject[key].length) {
          modified[key] = newObject[key]
        } else {
          newObject[key].forEach(item => {
            if (!oldObject.includes(item)) {
              modified[key] = newObject[key]
            }
          })
        }
      } else { //对象
        modified[key] = getModifiedValueInObjects(newObject[key], oldObject[key])
      }
    } else { //基本类型
      oldObject[key] !== newObject[key] && (modified[key] == newObject[key])
    }
  })
  return modified
}

/**
 * 格式化后台返回的菜单列表
 * @param menuList
 */
export const formatMenuData = (menuList) => {
  const routers = []
  initRouterNode(routers, menuList)
  return routers
}

// 生成路由节点
const initRouterNode = function (routers, data) {

  for (let item of data) {
    let menu = Object.assign({}, item)

    let relativePath = menu.component

    const searchParams = {}

    if (relativePath.includes('?') && relativePath.includes('=')) {
      const pathAndSearch = relativePath.split('?')

      const [path, search] = pathAndSearch

      search.split('&').forEach(s => {
        const [param, value] = s.split('=')
        searchParams[param] = value
      })

      menu.component = lazyLoading(path, menu._leaf, menu._parent)
    } else {
      menu.component = lazyLoading(menu.component, menu._leaf, menu._parent)
    }

    if (item._children && item._children.length > 0) {
      menu.children = []
      initRouterNode(menu.children, item._children)
    }

    let meta = {}
    // 给页面添加权限、标题、第三方网页链接
    meta.access = menu.roles ? menu.roles : undefined
    if (!meta.access) delete meta.access
    meta.title = menu.title ? menu.title : ''
    meta.hideInBread = menu.hideInBread ? menu.hideInBread : false
    meta.hideInMenu = menu.hideInMenu ? menu.hideInMenu : false
    meta.notCache = menu.notCache ? menu.notCache : true
    meta.icon = menu.icon
    meta.searchParams = searchParams
    menu.meta = meta

    if (!menu._leaf && menu._children.length > 0) menu.meta.showAlways = true

    if (menu._leaf && menu._children.length === 0 && !menu._parent) {
      const realMenu = {
        path: menu.path,
        name: `${menu.name}_p`,
        meta: {
          hideInBread: true
        },
        component: lazyLoading('_c/main'),
        children: [
          {
            path: '',
            name: menu.name,
            meta: {
              icon: menu.icon,
              title: menu.title
            },
            component: menu.component
          }
        ]
      }

      menu = realMenu
    }

    routers.push(menu)
  }

  sessionStorage.setItem('routers', JSON.stringify(routers))
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

export const parseModel = function (model) {

}

