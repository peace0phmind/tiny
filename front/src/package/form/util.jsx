import Rest from "../lib/Rest";
import * as Types from '../_util/javaTypes.js'

export default {
  methods: {
    /* disable form items */
    disableFormItems(status, ...items) {
      if (items.length) {
        items.forEach(itemName => {
          let item = this.normalFormItems.find(item => item.prop == itemName)
          this.$set(this.disabled, item.prop, status)
        })
      } else {
        this.normalFormItems.forEach(formItem => {
          this.$set(this.disabled, formItem.prop, status)
        })
      }
    },

    hideFormItems(status, ...items) {
      if (items.length) {
        items.forEach(itemName => {
          const idx = this.normalFormItems.findIndex(item => item.prop == itemName)
          if (idx >= 0) {
            status && this.normalFormItems.splice(idx, 1)
          } else {
            !status && this.normalFormItems.push(this.formItems.find(item => item.prop === items[idx]))
          }
        })
      }
    },

    setValue(k, v) {
      const formItem = this.normalFormItems.find(item => item.prop === k)
      if (formItem) {
        const {tree, resourcePath, renderTo, primaryKey, prop, type, modelType} = formItem
        if (tree) {
          this.$refs[`${prop}_form_item`].$refs[`${prop}_item`].init()
          const _rest = new Rest(resourcePath, this.$apiURL)
          _rest.GET({uri: `${v}/_parent`}).then(tree => {
            const arr = []
            const flatIds = (tree, arr) => {
              arr.push(tree[primaryKey])
              if (tree._children && tree._children.length) {
                flatIds(tree._children[0], arr)
              }
            }
            flatIds(tree, arr)
            this.$set(this.formModel, k, arr)
          })
        } else {
          if (Types.isCommonOrSuperModel(type, modelType)) {
            this.$refs[`${prop}_form_item`].$refs[`${prop}_item`].init()
          }
          this.$set(this.formModel, k, v)
        }
      } else {
        this.$set(this.formModel, k, v)
      }
      return this
    },

    addCollectionItem(k, v) {
      if (this.formModel[k] === undefined) this.$set(this.formModel, k, [])
      this.formModel[k].push(v)
      return this
    },
  }
}
