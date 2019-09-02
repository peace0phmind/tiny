import store from '@/store'
import _ from 'lodash'

export default {
  inserted(el, binding, vnode) {
    let {arg: type, value: modelUri} = binding
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

      if (!hasPermission) {
        el.parentNode && el.parentNode.removeChild(el)
      }
    } else {
      throw new Error(`need permission! Like v-permission:sys-roles.create`)
    }
  }
}
