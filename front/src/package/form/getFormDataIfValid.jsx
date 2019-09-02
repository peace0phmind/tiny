import * as Types from '../_util/javaTypes'
import * as utils from '../_util/util'
import * as FormItemRenderTypes from '../_util/formItemRenderType.js'

export default {
  methods: {
    getFormDataIfValid() {
      let _data
      // this.$refs.form.validate(valid => {
      //   if (valid) {
      const model = JSON.parse(JSON.stringify(this.formModel))

      this.normalFormItems.forEach(item => {
        const val = model[item.prop]

        const {prop} = item

        if (val) {

          if (Types.isDateTime(item.type, item.dateType))
            model[item.prop] = utils.dateFormat(val, 'YYYY-MM-DDTHH:mm:ss')
          if (Types.isDateOnly(item.type, item.dateType))
            model[item.prop] = utils.dateFormat(val, 'YYYY-MM-DD')

          if (Types.isCommonOrSuperModel(item.type, item.modelType)) {
            const {primaryKey, tree, renderTo} = item
            if (tree) model[`${prop}.${primaryKey}`] = val[val.length - 1]
            else model[`${prop}.${primaryKey}`] = val
          }
        }
      })

      if (!this.id) {
        // convert *.id to *: {id: x}
        _data = utils.convertFieldIdToJson(model)
      } else {

        Object.keys(this.changedModel).forEach(k => {
          const formItem = this.normalFormItems.find(item => item.prop === k)
          if (formItem) {
            const {type, modelType, primaryKey} = formItem
            if (Types.isCommonOrSuperModel(type, modelType)) {
              this.changedModel[`${k}.${primaryKey}`] = model[`${k}.${primaryKey}`]
            } else {
              this.changedModel[k] = model[k]
            }
            if (this.changedModel[k] === undefined) this.changedModel[k] = null
          }
        })
        this.changedModel.id = this.id
        // convert *.id to *: {id: x}
        _data = utils.convertFieldIdToJson(this.changedModel)
      }

      // }
      // })
      return _data
    }
  }
}
