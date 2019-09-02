import ETable from './e-table'
import SForm from './s-form'
import ZyxForm from './form/index.js'
import SOperationBar from './s-operation-bar'
import SSearch from './s-search'
import STable from './s-table'
import {IconChoose, IconPane} from './icon-choose'
import {mixinObjectArray, getFormItemsFromModel, getColumnsFromModel, getSearchItemsFromModel,} from './lib/tools.js'

const protonUi = [
  ETable,
  SForm,
  ZyxForm,
  SOperationBar,
  SSearch,
  STable,
  IconChoose,
  IconPane
]

const install = function (Vue, options = {}) {

  const {apiBaseUrl = 'api', componentsPool = {}, layoutEditable = true} = options

  Vue.prototype.$apiURL = apiBaseUrl

  Vue.prototype.$pool = componentsPool

  Vue.prototype.$layoutEditable = layoutEditable

  Vue.mixinObjectArray = mixinObjectArray

  Vue.getFormItemsFromModel = getFormItemsFromModel

  Vue.getColumnsFromModel = getColumnsFromModel

  Vue.getSearchItemsFromModel = getSearchItemsFromModel

  protonUi.forEach(component => {
    Vue.component(component.name, component)
  })
}

if (typeof window !== 'undefined' && window.Vue) {
  install(window.Vue)
}

export default {
  install,
  ETable,
  SForm,
  ZyxForm,
  SOperationBar,
  SSearch,
  STable,
  IconChoose,
  IconPane
}
