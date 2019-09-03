import _ from 'lodash'
import * as Types from '../../_util/javaTypes.js'
import Rest from '../../lib/Rest'
import {copyObjectRenameAttrInArrRecursive} from '../../lib/tools.js'

export default {
  name: 'zyx-cascader',

  inject: ['zyxForm'],

  props: {
    formModel: {},
    disabled: {},
    readonly: {},
    view: {},
    item: {},
    formProps: {}
  },

  data() {
    return {
      modelSelection: [],
      cascaderData: []
    }
  },

  computed: {
    upperFirstItemProp() {
      return _.upperFirst(this.item.prop)
    },
    isCommonOrSuperModel() {
      return Types.isCommonOrSuperModel(this.item.type, this.item.modelType)
    },
    isTree() {
      return this.item.tree
    }
  },

  render() {

    const {item: {prop, label, placeholder, primarykey, readonly: _readonly}} = this

    return (
      <Cascader v-model={this.formModel[prop]} disabled={this.disabled[prop] || _readonly} data={this.cascaderData} filterable
                change-on-select
                clearable placeholder={placeholder} selectModel={true} vOn:on-select-model={(e) => this.onSelectModel()}
                vOn:on-visible-change={(value) => this.onOpenChange(value)}></Cascader>
    )

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
      let {resourcePath} = this.item
      let _rest = new Rest(resourcePath, this.$apiURL)

      _rest.GET({
        uri: `tree`,
        params: {level: 10}
      }).then((res) => {
        this.cascaderData = copyObjectRenameAttrInArrRecursive({
          src: res,
          correspondence: {_instanceName: 'label', id: 'value', _children: 'children'}
        })
        this.$forceUpdate()
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
              selecting: true,
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
          vm.init()
          const id = vm.modelSelection[0].id

          if (id) {
            const {resourcePath, primaryKey} = vm.item
            const _rest = new Rest(resourcePath, vm.$apiURL)
            _rest.GET({uri: `${id}/_parent`}).then(tree => {
              const arr = []
              const flatIds = (tree, arr) => {
                arr.push(tree[primaryKey])
                if (tree._children && tree._children.length) {
                  flatIds(tree._children[0], arr)
                }
              }
              flatIds(tree, arr)
              vm.$set(vm.formModel, vm.item.prop, arr)
            })
          }
          vm.modelSelection = []
        },
        onCancel: () => {
          vm.modelSelection = []
        }
      })
    }
  }
}
