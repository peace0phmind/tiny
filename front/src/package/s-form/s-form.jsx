import Rest from '../lib/Rest'
import * as Types from '../_util/javaTypes.js'
import * as utils from '../_util/util.js'
import * as ReferenceModes from '../_util/referenceMode.js'
import SFormItem from './form-item'
import './form.less'
import moment from 'moment'
import {findObjChange} from '../lib/tools.js'

export default {
  name: 's-form',
  components: {
    SFormItem,
  },
  props: {
    formItems: {
      type: Array,
      default() {
        return []
      }
    },
    formLayout: {
      type: Array,
      default() {
        return []
      }
    },
    labelWidth: {
      type: Number,
      default: 80
    },
    resourcePath: {
      required: true
    },
    props: {
      type: Object,
      default() {
        return {
          list: 'content',
          total: 'totalElements',
          id: 'id'
        }
      }
    },
    enums: {
      default() {
        return {}
      }
    },
  },
  provide() {
    return {
      FormRoot: this
    }
  },
  data() {
    return {
      formModel: {},
      formItemArray: this.formItems,
      rules: {},
      disabled: {},
      hidden: {},
      activeTab: undefined,
      hasCollection: false,
      oneToMany: false,
      modelSelection: {},
      disabledCancel: true,
      loadingSubmit: false,
      disabledSubmit: true,
      changed: {},
      id: undefined,
      lastFormModel: {}
    }
  },
  computed: {
    gutter() {
      return 32
    },
  },
  render(h) {
    const items = this.createItems()

    const vm = this

    const createOneToMany = function (item) {
      return h(item.componentName, {
        ref: item.prop,
        props: {
          showSearch: false,
          shouldLoad: false
        },
        on: {
          'on-item-change': (option) => {
            const {data, operationType, front} = option
            vm.formModel[item.prop] = data
            if (vm.$parent.$parent.onItemChange) {
              vm.$parent.$parent.onItemChange({prop: item.prop, data, operationType, front})
            }
          }
        }
      })
    }

    const createManyObjTabs = function () {
      let tabList

      const {formItemArray, formModel, props} = vm

      if (vm.hasCollection === true) {
        tabList = (
          <el-tabs v-model={vm.activeTab} vOn:tab-click={vm.tabClick}
                   vOn:on-arr-change={(arr) => vm.onArrChange(arr)}>
            {vm.$scopedSlots.frontTabs && vm.$scopedSlots.frontTabs({formModel})}
            {
              formItemArray.filter(item => item.type === Types.Collection && !item.notShow).map(item => {
                const {label, prop, bidirectional, referenceMode} = item

                const action_scopedSlots = {
                  _action: ({row, index}) => {
                    return (
                      <Button type="error" size="small"
                              onClick={() => vm.handleRemoveManyToMany(row, index, item)}>删除</Button>
                    )
                  }
                }

                const {meta: {_columns: columns, _enums: enums}} = vm.$pool[item.componentName]

                return (
                  <el-tab-pane label={label} name={prop}
                               vOn:on-arr-change={(arr) => vm.onArrChange(arr)}>
                    {
                      bidirectional ?
                        (
                          <div>
                            {
                              referenceMode === ReferenceModes.oneToMany ?
                                (
                                  <div>
                                    {
                                      vm.id ?
                                        (<div>
                                          {
                                            createOneToMany(item)
                                          }
                                        </div>) // 修改
                                        :
                                        (<div>
                                          {
                                            createOneToMany(item)
                                          }
                                        </div>) // 新增
                                    }
                                  </div>
                                ) // 一对多
                                :
                                (
                                  <div>
                                    <div>

                                      <Button type="primary"
                                              style="margin-right: 5px;"
                                              onClick={() => vm.handleAddManyToMany(item)}>新增
                                      </Button>

                                    </div>

                                    <s-table ref="table" columns={columns}
                                             data={vm.formModel[item.prop]}
                                             enums={enums}
                                             scopedSlots={action_scopedSlots}>

                                    </s-table>
                                  </div>
                                ) // 多对多
                            }
                          </div>
                        ) // 双向
                        :
                        ({}) // 单向 暂不处理
                    }
                  </el-tab-pane>
                )
              })
            }
            {vm.$scopedSlots.behindTabs && vm.$scopedSlots.behindTabs({formModel})}
          </el-tabs>
        )
      }

      return tabList
    }

    const tabs = createManyObjTabs()
    return (
      <div>
        <Form ref="zForm" attrs={{model: this.formModel, rules: this.rules}} label-width={this.labelWidth}
              vOn:on-option-change={(option) => this.onOptionChange(option)}
              vOn:on-arr-change={(arr) => vm.onArrChange(arr)}>
          {items}
        </Form>
        <div {...{
          style: {
            textAlign: 'right',
            borderTop: vm.hasCollection && '1px solid #e8e8e8' || '0px',
            borderBottom: !vm.hasCollection && '1px solid #e8e8e8' || '0px',
            padding: '10px 5px'
          }
        }}>
          {this.$scopedSlots.operation && this.$scopedSlots.operation({}) || (
            <div>
              <Button {...{style: {marginRight: '8px'}}} onClick={this.cancelEdit}>取消</Button>
              <Button type={'primary'} loading={this.loadingSubmit} disabled={this.disabledSubmit}
                      onClick={this.submitEdit}>
                {this.loadingSubmit && '提交中' || '提交'}
              </Button>
            </div>
          )}
        </div>
        <div {...{style: {marginTop: '10px'}}}>
          {tabs}
        </div>
      </div>
    )

  },
  created() {
    const {resourcePath} = this
    this.rest = new Rest(resourcePath, this.$apiURL)

    let formItem = this.formItemArray.find(item => item.type === 'COLLECTION')
    if (formItem) {
      this.hasCollection = true
      this.activeTab = formItem.prop
    }
  },
  watch: {
    formModel: {
      handler(newFormModel, oldFormModel) {
        if (this.id) { //修改
          if (Object.keys(this.lastFormModel).length > 0) {
            if (!_.isEqual(this.lastFormModel, newFormModel)) {
              const changed = findObjChange(newFormModel, this.lastFormModel)
              if (changed) {
                Object.entries(changed).forEach(([k, v]) => this.changed[k] = JSON.parse(JSON.stringify(v)))
                this.disabledSubmit = false
              }
            } else {
              this.disabledSubmit = true
            }
          }
        } else { //新增
          if (!_.isEqual({}, newFormModel)) {
            const changed = findObjChange(newFormModel, oldFormModel)
            if (changed) {
              Object.entries(changed).forEach(([k, v]) => this.changed[k] = v)
              this.disabledSubmit = false
            } else {
              this.disabledSubmit = true
            }
          }
        }
      },
      deep: true
    }
  },
  methods: {
    init(id) {
      this.reset()
      const oneToMany = this.formItemArray.find(item => item.prop === this.activeTab)
      if (id) {
        this.id = id
        this.pluginData()
        if (oneToMany && this.$refs[this.activeTab]) {
          this.$refs[this.activeTab].localMode = false
          const extraParams = {}
          const formItem = this.formItemArray.find(item => item.prop === this.activeTab)
          extraParams[`${formItem.referModelAttribute}.equals`] = id
          extraParams.id = -2
          this.$refs[this.activeTab].extraParams = extraParams
          this.$refs[this.activeTab].load()
        }
      } else {
        this.id = undefined
        if (oneToMany && this.$refs[this.activeTab]) {
          this.$refs[this.activeTab].localMode = true
          this.$refs[this.activeTab].extraParams = {id: -1}
        }
      }

    },

    onOptionChange(option) {
      this.$emit('on-option-change', option)
    },

    cancelEdit() {
      if (this.$parent.$parent.cancel) {
        this.$parent.$parent.cancel()
      }
    },

    submitEdit() {
      if (this.$parent.$parent.submit) {
        this.$parent.$parent.submit()
      } else {
        if (this.$parent.$parent.$parent.$parent.submit) {
          this.$parent.$parent.$parent.$parent.submit()
        }
      }
    },

    createItems() {
      const {formLayout, formItemArray, getFormItemByProp, formModel, disabled, enums, props, hidden} = this

      let items

      const createItem = (item) => {
        const ref = `${item.prop}_form_item`
        return (
          <s-form-item ref={ref} item={item} form-model={formModel} disabled={disabled} hidden={hidden}
                       enums={enums}
                       form-props={props}
                       vOn:on-option-change={(option) => this.onOptionChange(option)}></s-form-item>)
      }

      if (formLayout.length > 0) {
        items = formLayout.map(layout => {

          const span = 24 / layout.length

          const row = {}
          for (let item of layout) {
            if (item.prop) row[item.prop] = (row[item.prop] || 0) + 1
          }

          return (
            <Row gutter={this.gutter}>
              {
                Object.entries(row).map(([prop, count]) => {
                  if (getFormItemByProp(prop)) {
                    return (
                      <Col span={span * count}>
                        {createItem(getFormItemByProp(prop))}
                      </Col>
                    )
                  }
                })
              }
            </Row>
          )
        })
      } else {
        items = formItemArray.filter(item => item.type !== Types.Collection).map(item => {
          return (<div>{createItem(item)}</div>)
        })
      }
      return items
    },
    getFormItemByProp(prop) {
      const {formItemArray} = this
      const formItem = formItemArray.find(item => item.prop === prop)
      return formItem
    },
    getFormDataIfValid() {
      let _data
      this.$refs.zForm.validate(valid => {
        if (valid) {
          const model = JSON.parse(JSON.stringify(this.formModel))
          //handle datetime and date
          this.formItemArray.filter(item => item.type === Types.DateTime)
            .forEach(item => {
              if (model[item.prop]) {
                const {dateType} = item
                if (dateType === Types.DateType.Default) model[item.prop] = utils.dateFormat(model[item.prop], 'YYYY-MM-DDTHH:mm:ss') // format datatime
                if (dateType === Types.DateType.DateOnly) model[item.prop] = utils.dateFormat(model[item.prop], 'YYYY-MM-DD') // format date to string
              }
            })

          this.formItemArray.filter(item => item.type === Types.Model && (item.modelType === Types.ModelType.Common || item.modelType === Types.ModelType.Super))
            .forEach(item => {
              if (model[item.prop]) {
                const {primaryKey, prop} = item
                if (item.tree) {
                  const v = model[item.prop]
                  if (v) {
                    model[`${prop}.${primaryKey}`] = v[v.length - 1]
                  }
                } else {
                  model[`${prop}.${primaryKey}`] = model[item.prop]
                }
              }
            })
          // if (this.id) this.changed.id = this.id
          if (!this.id) {
            // deep clone
            // _data = JSON.stringify(this.formModel)
            // _data = JSON.parse(_data)
            // convert *.id to *: {id: x}
            _data = utils.convertFieldIdToJson(model)
          } else {
            Object.keys(this.changed).forEach(k => {
              const formItem = this.formItemArray.find(item => item.prop === k)
              const {type, modelType, primaryKey} = formItem
              if (type === Types.Model && (modelType === Types.ModelType.Common || modelType === Types.ModelType.Super)) {
                this.changed[`${k}.${primaryKey}`] = model[`${k}.${primaryKey}`]
              } else {
                this.changed[k] = model[k]
              }
              if (this.changed[k] === undefined) this.changed[k] = null
            })
            this.changed.id = this.id
            // deep clone
            // _data = JSON.stringify(this.changed)
            // _data = JSON.parse(_data)
            // convert *.id to *: {id: x}
            _data = utils.convertFieldIdToJson(this.changed)
          }

        }
      })
      return _data
    },
    reset() {
      this.changed = {}

      Object.keys(this.formModel).forEach(prop => {
        this.$delete(this.formModel, prop)
      })

      // this.pluginNumber()

      !this.id && this.pluginDefaultValue()

      this.pluginRules()

      this.disabledCancel = true

      this.disabledSubmit = true

      this.loadingSubmit = false

      this.lastFormModel = {}
    },
    pluginDefaultValue() {
      const {formItemArray} = this
      formItemArray.filter(item => item.defaultValue).forEach(item => {
        const {prop, defaultValue} = item
        this.$set(this.formModel, prop, defaultValue)
      })
    },
    pluginNumber() {
      const {formItemArray} = this
      formItemArray.filter(item => item.type === Types.Decimal).forEach(item => {
        const {prop} = item
        this.$set(this.formModel, prop, 0)
      })
    },
    pluginRules() {
      this.formItemArray.forEach(item => {
        if (item.valid) {
          const {prop, valid} = item
          if (valid) {
            const {required, type, validator} = valid
            this.$set(this.rules, prop, [])
            if (required) {
              this.rules[prop].push({
                required: required.value,
                message: required.message && required.message || `请填写${item.label}`
              })
            }

            if (type) {
              const {value, min, max, len, pattern, message} = type
              const rule = {}
              if (value) {
                rule.type = value
                if (min) rule.min = min
                if (max) rule.max = max
                if (len) rule.len = len
                if (pattern) rule.pattern = pattern
                rule.message = message && message || `${item.label}格式不正确`

              }
              this.rules[prop].push(rule)
            }

            if (validator) {
              this.rules[prop].push({
                validator: validator,
                formModel: this.formModel
              })
            }
          }
        }
      })
    },
    pluginData() {
      const {id, props} = this
      const {list} = props
      this.rest.GET({uri: id}).then((res) => {
        if (res) {
          this.formModel = list && res[list] || res
          this.formItemArray.forEach(item => {
            const {type, dateType, modelType, referenceMode, tree, prop, primaryKey} = item

            if (type === Types.DateTime && (dateType === Types.DateType.DateOnly || dateType == Types.DateType.Default)) {
              if (this.formModel[prop]) this.formModel[prop] = moment(this.formModel[prop]).format('YYYY-MM-DD HH:mm:ss')
            }

            if (type === Types.Collection && referenceMode === ReferenceModes.manyToMany) {
              this.modelSelection[prop] = this.formModel[prop]
            }

            if (type === Types.Model && (modelType === Types.ModelType.Common || modelType === Types.ModelType.Super)) {
              if (this.formModel[prop]) {
                if (tree) {
                  this.formModel[prop] = [this.formModel[prop][primaryKey]]
                  if (this.formModel[prop]) {
                    const formItem = this.formItemArray.find(formItem => formItem.prop === prop)
                    if (formItem) {
                      const {resourcePath} = formItem
                      const _rest = new Rest(resourcePath, this.$apiURL)
                      _rest.GET({uri: `${this.formModel[prop]}/_parent`}).then(tree => {
                        const arr = []
                        const flatIds = (tree, arr) => {
                          arr.push(tree[primaryKey])
                          if (tree._children && tree._children.length) {
                            flatIds(tree._children[0], arr)
                          }
                        }
                        flatIds(tree, arr)
                        this.formModel[prop] = arr
                      })
                    }
                  }
                } else {
                  this.formModel[prop] = this.formModel[prop][primaryKey]
                }
              }
            }
          })
          _.assignIn(this.lastFormModel, this.formModel)
        }
      })
    },

    /* disable form items */
    disableFormItems(...items) {
      if (items.length) {
        items.forEach(itemName => {
          let item = this.formItemArray.find(item => item.prop == itemName)
          this.$set(this.disabled, item.prop, true)
        })
      } else {
        this.formItemArray.forEach(formItem => {
          this.$set(this.disabled, formItem.prop, true)
        })
      }
    },

    /* hideOrShow form items */
    hideOrShowFormItems(leaf, ...items) {
      if (leaf) {
        if (items.length) {
          items.forEach(itemName => {
            let item = this.formItemArray.find(item => item.prop == itemName)
            this.$set(this.hidden, item.prop, false)
          })
        }
      } else {
        if (items.length > 0) {
          items.forEach(itemName => {
            let item = this.formItemArray.find(item => item.prop == itemName)
            this.$set(this.hidden, item.prop, true)
          })
        }
      }
    },

    setValue(k, v) {
      this.$set(this.formModel, k, v)
    },

    setArray(k, v) {
      if (this.formModel[k] === undefined) {
        this.$set(this.formModel, k, [])
      }
      this.formModel[k].push(v)
    },

    tabClick(tab) {
      const oneToMany = this.formItemArray.find(item => item.type === Types.Collection && item.referenceMode === ReferenceModes.oneToMany && item.prop === tab.name)
      if (oneToMany && this.$refs[this.activeTab]) {
        if (this.id) {
          this.$refs[this.activeTab].localMode = false
          const extraParams = {}
          const formItem = this.formItemArray.find(item => item.prop === this.activeTab)
          extraParams[`${formItem.referModelAttribute}.equals`] = this.id
          this.$refs[this.activeTab].extraParams = extraParams
          this.$refs[this.activeTab].load()
        } else {
          this.$refs[this.activeTab].localMode = true
          this.$refs[this.activeTab].extraParams = {}
        }
      }
    },

    handleAddManyToMany(item) {
      const vm = this
      const width = document.documentElement.clientWidth * 0.8
      this.$Modal.confirm({
        title: item.label,
        closable: false,
        width: width,
        render: (h) => {
          return h(item.componentName, {
            ref: item.prop,
            on: {
              'on-check': (selection) => {
                const arr = vm.modelSelection[item.prop]
                if (arr && arr.length >= 0) {
                  selection.forEach((sel) => {
                    !vm.modelSelection[item.prop].find(m => m.id === sel.id) && vm.modelSelection[item.prop].push(sel)
                  })
                } else {
                  vm.modelSelection[item.prop] = []
                  selection.forEach((sel) => {
                    vm.modelSelection[item.prop].push(sel)
                  })
                }
              }
            }
          })
        },
        onOk: () => {
          this.$set(this.formModel, item.prop, this.modelSelection[item.prop])
          this.modelSelection[item.prop] = []
        },
        onCancel: () => {
          this.modelSelection[item.prop] = []
        }
      })
    },

    handleRemoveManyToMany(id, index, item) {
      this.formModel[item.prop].splice(index, 1)
    }
  }
}
