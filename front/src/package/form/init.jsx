import * as ReferenceModes from '../_util/referenceMode'
import * as Types from '../_util/javaTypes'
import * as FormItemRenderTypes from '../_util/formItemRenderType.js'
import moment from 'moment'
import Rest from '../lib/Rest'

export default {
  methods: {
    init(id) {

      this.reset()

      if (id) {
        this.id = id
        this.pluginData()
      } else {
        this.id = undefined
      }

      this.initTabFormItems(id)

    },

    initTabFormItems(id) {
      this.activeTab = this.tabFormItems && this.tabFormItems.length && this.tabFormItems[0].prop || undefined
      this.activeTab && this.pluginTabData(id)
    },

    reset() {
      Object.keys(this.formModel).forEach(prop => {
        this.$delete(this.formModel, prop)
      })

      !this.id && this.pluginDefaultValue()

      this.pluginRules()

      this.loadingSubmit = false

      this.disabledSubmit = true

      this.previousFormModel = {}

      this.changedModel = {}
    },
    pluginDefaultValue() {
      this.normalFormItems.filter(item => item.defaultValue !== undefined).forEach(item => {
        const {prop, defaultValue, resourcePath, tree} = item
        if (tree) {
          this.$refs[`${prop}_form_item`].$refs[`${prop}_item`].init()
          const _rest = new Rest(resourcePath, this.$apiURL)
          _rest.GET({uri: `${defaultValue}/_parent`}).then(tree => {
            const arr = []
            const flatIds = (tree, arr) => {
              arr.push(tree[primaryKey])
              if (tree._children && tree._children.length) {
                flatIds(tree._children[0], arr)
              }
            }
            flatIds(tree, arr)
            this.$set(this.formModel, prop, arr)
          })

        } else {
          this.$set(this.formModel, prop, defaultValue)
        }
      })
    },

    pluginTabData(id) {
      const {activeTab} = this
      const formItem = this.tabFormItems.find(item => item.prop === activeTab)
      const {referenceMode, bidirectional} = formItem

      if (referenceMode === ReferenceModes.oneToMany) {
        if (bidirectional !== false) {
          this.loadAllToKnowFrom(formItem)
        }
      } else {
        this.loadAllToKnowFrom(formItem)
      }
    },

    pluginData() {
      const {id, formProps: {list}} = this

      this.restTemplate.GET({uri: id}).then(async (res) => {
        if (res) {
          const model = list && res[list] || res

          for (let item of this.normalFormItems) {
            const {type, dateType, modelType, referenceMode, tree, prop, primaryKey, renderTo, resourcePath} = item

            const val = model[prop]

            if (val) {
              Types.isDateTime(type, dateType) || Types.isDateOnly(type, dateType) && (model[prop] = moment(val).format('YYYY-MM-DD HH:mm:ss'))

              if (Types.isCommonOrSuperModel(type, modelType)) {
                if (tree) {
                  this.$refs[`${prop}_form_item`].$refs[`${prop}_item`].init()
                  const _rest = new Rest(resourcePath, this.$apiURL)
                  await _rest.GET({uri: `${val[primaryKey]}/_parent`}).then(tree => {
                    const arr = []
                    const flatIds = (tree, arr) => {
                      arr.push(tree[primaryKey])
                      if (tree._children && tree._children.length) {
                        flatIds(tree._children[0], arr)
                      }
                    }
                    flatIds(tree, arr)
                    model[prop] = arr
                  })
                } else {
                  this.$refs[`${prop}_form_item`].$refs[`${prop}_item`].pluginData(val[primaryKey])
                  model[prop] = val[primaryKey]
                }
              }

            }

          }

          const idx = this.normalFormItems.findIndex(item => item.prop === '_parent')
          if (idx >= 0) model._parent = (model._parent === undefined || model._parent === null) && [] || model._parent

          this.tabFormItems.forEach(item => {
            delete model[item.prop]
          })

          this.formModel = JSON.parse(JSON.stringify(model))
          this.previousFormModel = JSON.parse(JSON.stringify(model))
        }
      }, (error) => {
        this.$Message.error('获取数据失败!')
      })
    }
  }
}
