import _ from 'lodash'
import * as Types from '../../_util/javaTypes.js'
import Rest from '../../lib/Rest'
import {copyObjectRenameAttrInArrRecursive} from '../../lib/tools.js'

export default {
  name: 'zyx-select',

  inject: ['zyxForm'],

  props: {
    formModel: {},
    disabled: {},
    readonly: {},
    view: {},
    item: {},
    enums: {},
    formProps: {},
    treeModelPool: {}
  },

  data() {
    return {
      modelOptions: {},
      modelSelection: [],
      treePool: this.treeModelPool,
      treeData: [],
      treeNodeModalShow: false,
      treeMap: {},
      modelNames: {}
    }
  },

  computed: {
    upperFirstItemProp() {
      return _.upperFirst(this.item.prop)
    },
    isEnum() {
      return Types.isEnum(this.item.type, this.item.modelType)
    },
    isCommonOrSuperModel() {
      return Types.isCommonOrSuperModel(this.item.type, this.item.modelType)
    },
    isTree() {
      return this.item.tree
    },
    isMultiple() {
      return this.item.collection
    }
  },

  render() {

    const {item: {prop, enumName, label, primarykey, placeholder, notFoundText, prefixIcon, readonly: _readonly}, enums} = this

    const attrs = {}
    prefixIcon && (attrs.prefixIcon = prefixIcon)

    if (this.isEnum) {
      if (this.readonly) {
        const val = this.formModel[prop]
        if (this.isMultiple) {
          if (val && val.length) {
            return (
              <span>
                {val.map(v => {
                  return (<span>{`${v && this.enums[enumName][v].name || ''}、`}</span>)
                })}
              </span>
            )
          } else {
            return (<span>-</span>)
          }
        } else {
          return <span>{val && this.enums[enumName][val].name || '-'}</span>
        }
      } else return (
        <Select v-model={this.formModel[prop]}
                multiple={this.isMultiple}
                placeholder={placeholder}
                disabled={this.disabled[prop] || _readonly}
                not-found-text={notFoundText && notFoundText || '当前无数据'}
                transfer
                clearable
                filterable
                attrs={attrs}
                vOn:on-clear={(event) => this.onClear()}
                vOn:on-change={(val) => this.onChange(val)}
                vOn:on-open-change={(status) => this.onOpenChange(status)}
        >
          {
            Object.keys(enums[enumName]).map(k => {
              return (
                <Option value={k}>
                  {enums[enumName][k].name}
                </Option>
              )
            })
          }
        </Select>
      )
    } else {
      if (!this.isTree) {
        if (this.readonly) {
          const val = this.formModel[prop]
          if (val && val !== null) {
            this.initInstanceName(val)
            return <span>{val && this.modelNames[val] || '-'}</span>
          } else {
            return <span>{'-'}</span>
          }
        } else return (<Select v-model={this.formModel[prop]}
                               placeholder={placeholder}
                               disabled={this.disabled[prop]}
                               clearable
                               vOn:on-clear={(e) => this.onClear()}
                               vOn:on-change={(value) => this.onChange(value)}
                               selectModel={true}
                               vOn:on-select-model={(e) => this.onSelectModel()}
                               vOn:on-open-change={(status) => this.onOpenChange(status)}
        >
          {
            this.modelOptions[prop] && this.modelOptions[prop].map(v => {
              const id = v.id
              if (v && id) {
                return (<Option value={id}>{v._instanceName}</Option>)
              }
            })
          }
        </Select>)
      }
    }
  },

  created() {

  },

  methods: {
    onChange(val) {
      const call = this.zyxForm[`on${this.upperFirstItemProp}Change`]
      call && call({val})
    },
    onClear() {
      const call = this.zyxForm[`on${this.upperFirstItemProp}Clear`]
      call && call()
    },
    onOpenChange(status) {
      if (this.isCommonOrSuperModel && status) {
        this.init()
      }

      const call = this.zyxForm[`on${this.upperFirstItemProp}OpenChange`]
      call && call({status})
    },
    init() {
      const _rest = new Rest(this.item.resourcePath, this.$apiURL)
      _rest.GET({params: {page: 0, size: 1000}}).then((res) => {
        if (res) {
          this.modelOptions[this.item.prop] = res.content
          this._watcher.update()
          if (this.modelOptions[this.item.prop].findIndex(option => option[this.item.primaryKey] === this.formModel[this.item.prop]) < 0) {
            this.pluginData(this.formModel[this.item.prop])
          }
        }
      })
    },
    pluginData(val) {
      const _rest = new Rest(this.item.resourcePath, this.$apiURL)
      _rest.GET({uri: val}).then((res) => {
        if (res) {
          this.modelOptions[this.item.prop] = this.modelOptions[this.item.prop] || []
          this.modelOptions[this.item.prop].push(res)
          this.$forceUpdate()
        }
      })
    },
    initInstanceName(id) {
      const _rest = new Rest(this.item.resourcePath, this.$apiURL)
      _rest.GET({uri: id}).then((res) => {
        if (res) {
          this.$set(this.modelNames, id, res._instanceName)
        }
      })
    },
    onSelectModel() {
      const vm = this
      const width = document.documentElement.clientWidth * 0.8
      this.$Modal.confirm({
        title: this.item.label,
        closable: true,
        width: width,
        render: (h) => {
          return h(this.item.componentName, {
            props: {
              enterFromRoute: false,
              multipleSelect: false
            },
            on: {
              'on-check': (selection) => {
                vm.modelSelection = selection
              }
            }
          })
        },
        onOk: () => {
          // vm.modelOptions[vm.item.prop] = JSON.parse(JSON.stringify(vm.modelSelection))
          vm.modelOptions[vm.item.prop] = vm.modelOptions[vm.item.prop] || []
          for (let sel of vm.modelSelection) {
            vm.modelOptions[vm.item.prop].push(sel)
          }
          vm.$set(vm.formModel, vm.item.prop, vm.modelSelection[0][vm.formProps.id])
          vm.$forceUpdate()
          vm.modelSelection = []
        },
        onCancel: () => {
          vm.modelSelection = []
        }
      })
    }
  }
}
