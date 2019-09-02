// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import VueBus from 'vue-bus'
import App from './App'
import router from './router'
import store from './store'
import iView from 'dxs-iview'
import i18n from '@/locale'
import config from '@/config'
import importDirective from '@/directive'
import {directive as clickOutside} from 'v-click-outside-x'
import installPlugin from '@/plugin'
import '@/assets/icons/iconfont.css'
import TreeTable from 'tree-table-vue'
import VOrgTree from 'v-org-tree'
import 'v-org-tree/dist/v-org-tree.css'
import ElementUI from 'element-ui'
import './config/element-variables.scss'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
import CircleMenu from 'vue-circle-menu'
import splitPane from 'vue-splitpane'
import moment from 'moment'
import 'moment/locale/zh-cn'
import Rest from './libs/proton/Rest'
import {ZForm, ZDrawerForm, ZModalForm, ZFormItem} from '_c/z-form'
import {ZTable, ZSingleTable} from '_c/z-table'
import {ZTree, ZTreeForm, ZOrgTree} from '_c/z-tree'
// import STable from '_c/s-table/s-table'
// import ETable from '_c/e-table/table'
import VueTagsInput from '@johmun/vue-tags-input'
// import SSearch from '_c/s-search/s-search'
// import SForm from '_c/s-form/s-form'
// import SOperationBar from '_c/s-operation-bar/s-operation-bar'
import pool from './component'
// import zyxProtonUi from 'zyx-proton-ui'
import zyxProtonUi from './package'
import {API_URL} from './config/proton'
import './index.less'
import './config/config.js'
import VueCron from 'vue-cron'

moment.locale('zh-cn')

const i18nResource = new Rest('i18ns')

// 实际打包时应该不引入mock
/* eslint-disable */
if (process.env.NODE_ENV !== 'production') require('@/mock')

Vue.use(ElementUI)
Vue.use(Antd)
Vue.use(iView, {
  i18n: (key, value) => i18n.t(key, value)
})
Vue.use(VueCron)
Vue.use(TreeTable)
Vue.use(VOrgTree)
Vue.use(VueBus)
Vue.use(zyxProtonUi, {
  apiBaseUrl: API_URL,
  componentsPool: pool,
  layoutEditable: process.env.NODE_ENV === 'development'
})

/**
 * @description 注册admin内置插件
 */
installPlugin(Vue)
/**
 * @description 生产环境关掉提示
 */
Vue.config.productionTip = false
/**
 * @description 全局注册应用配置
 */
Vue.prototype.$config = config

/**
 * 注册指令
 */
importDirective(Vue)
Vue.directive('clickOutside', clickOutside)

Vue.component('split-pane', splitPane)

Vue.component('CircleMenu', CircleMenu)

Vue.component('VueTagsInput', VueTagsInput)

Vue.component('ZTable', ZTable)

Vue.component('ZSingleTable', ZSingleTable)

Vue.component('ZForm', ZForm)

Vue.component('ZFormItem', ZFormItem)

Vue.component('ZModalForm', ZModalForm)

Vue.component('ZDrawerForm', ZDrawerForm)

Vue.component('ZTree', ZTree)

Vue.component('ZTreeForm', ZTreeForm)

Vue.component('ZOrgTree', ZOrgTree)

// Vue.component('STable', STable)

// Vue.component('ETable', ETable)

// Vue.component('SSearch', SSearch)

// Vue.component('SForm', SForm)

// Vue.component('SOperationBar', SOperationBar)

Vue.filter('date_format', function (date, pattern = 'YYYY-MM-DD HH:mm:ss') {
  return date && moment(date).format(pattern)
})

Vue.filter('i18n', (text, i18n) => `${text}(${i18n})`)

Vue.filter('i18nName', async (id, i18n) => {
  let _name = '/'
  await i18nResource.GET({uri: id}).then(res => {
    _name = res[i18n]
    return _name
  })
})

Vue.prototype.moment = moment
Vue.prototype.dateFormat = function (date, pattern = 'YYYY-MM-DD HH:mm:ss') {
  return date && moment(date).format(pattern)
}
Date.prototype.toJSON = function () {
  return moment(this).format('YYYY-MM-DD HH:mm:ss')
}

console.warn = function () {
}

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  i18n,
  store,
  render: h => h(App)
})
