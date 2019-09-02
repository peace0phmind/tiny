import IconChoose from './icon-choose.jsx'
import IconPane from './icon-pane'

IconChoose.install = function (Vue) {
  Vue.component(IconChoose.name, IconChoose)
}

IconPane.install = function (Vue) {
  Vue.component(IconPane.name, IconPane)
}

export {
  IconChoose,
  IconPane
}
