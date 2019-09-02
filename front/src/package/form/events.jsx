import _ from 'lodash'

export default {
  created() {
    this.normalFormItems.forEach(item => {
      const {prop, renderTo} = item
      const upperFirstProp = _.upperFirst(prop)

      this[`on${upperFirstProp}Change`] = (obj) => this.$emit(`on-${prop}-change`, obj)
      this[`on${upperFirstProp}Changing`] = (obj) => this.$emit(`on-${prop}-changing`, obj)
      this[`on${upperFirstProp}OpenChange`] = (obj) => this.$emit(`on-${prop}-open-change`, obj)
      this[`on${upperFirstProp}Enter`] = () => this.$emit(`on-${prop}-enter`)
      this[`on${upperFirstProp}Focus`] = () => this.$emit(`on-${prop}-change`)
      this[`on${upperFirstProp}Blur`] = () => this.$emit(`on-${prop}-change`)
      this[`on${upperFirstProp}Keyup`] = (obj) => this.$emit(`on-${prop}-change`, obj)
      this[`on${upperFirstProp}Keydown`] = (obj) => this.$emit(`on-${prop}-change`, obj)
      this[`on${upperFirstProp}Keypress`] = (obj) => this.$emit(`on-${prop}-change`, obj)
      this[`on${upperFirstProp}Clear`] = () => this.$emit(`on-${prop}-change`)
    })
  },

}
