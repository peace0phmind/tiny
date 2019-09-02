import Rest from '../lib/Rest'
import * as Types from '../_util/javaTypes.js'
import SearchSlot from './search-slot.js'
import {copyObjectRenameAttrInArrRecursive} from '../lib/tools.js'

export default {
  name: 'search-item',

  props: {
    item: Object,
    queryParams: Object,
    enums: {},
    props: Object
  },

  data() {
    return {
      modelSelection: []
    }
  },

  inject: ['SearchRoot'],

  components: {SearchSlot},

  created() {
    const {item} = this
    item.type === Types.Model && (item.modelType === Types.ModelType.Common || item.modelType === Types.ModelType.Super) && this.pullOptions(item)
  },

  methods: {
    pullOptions(item) {
      let {resourcePath} = item
      let _rest = new Rest(resourcePath, this.$apiURL)

      const {tree} = item
      if (tree) {
        _rest.GET({
          uri: `tree`,
          params: {level: 10}
        }).then((res) => {
          item.arr = copyObjectRenameAttrInArrRecursive({
            src: res,
            correspondence: {_instanceName: 'label', id: 'value', _children: 'children'}
          })
          this.$forceUpdate()
        })
      } else {

        _rest.GET({
          params: {
            page: 0,
            size: 10
          }
        }).then((res) => {
          if (res) {
            item.arr = res.content
            this.$forceUpdate()
          }
        })
      }
    },
    onOpenChange(status, item) {
      if (status === true) {
        this.pullOptions(item)
      }
    },
    onEnumChange(v, key) {
      // if (!v || v.length === 0) {
      //     delete this.queryParams[key]
      // }
    },
    clearSelect() {
      // this.$set(this.formModel, this.item.key, null)
    },
    openSelectModel(item) {
      const vm = this
      const width = item.tree && 400 || document.documentElement.clientWidth * 0.8
      this.$Modal.confirm({
          title: item.label,
          closable: true,
          fullscreen: true,
          width: width,
          render: (h) => {
            return h(item.componentName, {
              ref: item.prop,
              props: {
                searchable: !item.tree && true || false,
                readonly: true,
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
            this.pullOptions(vm.item)
            if (item.tree) {
              const id = this.modelSelection[0].id

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
                  this.$set(this.queryParams, item.key, arr)
                })
              }
            } else {
              this.$set(this.queryParams, item.key, this.modelSelection[0].id)
            }
            this.modelSelection = []
          },
          onCancel: () => {
            this.modelSelection = []
          }
        }
      )
    },
    createBody() {
      let body

      let {item} = this

      const vm = this

      let {label, type, key, dateType, modelType, enumName, arr, tree, multiple} = item

      if (multiple === undefined) multiple = true

      if (type === Types.String) {
        body = (
          <Input placeholder={label} clearable
                 v-model={this.queryParams[key]} {...{style: {width: this.width}}}></Input>
        )
      } else if (type === Types.Decimal) {
        body = (
          <InputNumberRang clearable v-model={this.queryParams[key]} {...{style: {width: this.width}}}></InputNumberRang>
        )
      } else if (type === Types.Boolean) {
        body = (
          <Select v-model={this.queryParams[key]} placeholder={label} clearable
                  filterable {...{style: {width: this.width}}}>
            <Option value="true">是</Option>
            <Option value="false">否</Option>
          </Select>
        )
      } else if (type === Types.DateTime) {

        if (dateType === Types.DateType.Default) {
          body = (
            <DatePicker v-model={this.queryParams[key]} type="datetimerange"
                        placeholder={label} {...{style: {width: this.width}}}></DatePicker>
          )
        } else if (dateType === Types.DateType.DateOnly) {
          body = (
            <DatePicker v-model={this.queryParams[key]} type="daterange"
                        placeholder={label} {...{style: {width: this.width}}}></DatePicker>
          )
        } else {
          body = (
            <TimePicker v-model={this.queryParams[key]} type="timerange"
                        placeholder={label} {...{style: {width: this.width}}}></TimePicker>
          )
        }

      } else if (type === Types.Model) {

        if (modelType === Types.ModelType.Enum) {
          body = (
            <Select v-model={this.queryParams[key]} placeholder={label} clearable
                    filterable {...{style: {width: this.width}}} multiple={multiple}
                    vOn:on-change={(v) => vm.onEnumChange(v, key)}>
              {
                Object.entries(this.enums[enumName]).map(([value, _enum]) => {
                  return (
                    <Option value={value}>{_enum.name}</Option>
                  )
                })
              }
            </Select>
          )
        } else {
          if (tree) {
            body = (
              <Cascader v-model={this.queryParams[key]} data={arr} filterable change-on-select
                        clearable {...{style: {width: this.width}}} placeholder={label}
                        selectModel={true} vOn:on-select-model={(e) => vm.openSelectModel(item)}
                        vOn:on-visible-change={(value) => vm.onOpenChange(status, item)}></Cascader>
            )
          } else {
            body = (
              <Select v-model={this.queryParams[key]} placeholder={label} clearable
                      filterable {...{style: {width: this.width}}}
                      vOn:on-open-change={(status) => vm.onOpenChange(status, item)}
                      selectModel={true} vOn:on-select-model={(e) => vm.openSelectModel(item)}
                      vOn:on-clear={(e) => vm.clearSelect()}>
                {
                  arr.map(option => {
                    if (option && option.id) {
                      return (
                        <Option value={option.id}>{option._instanceName}</Option>
                      )
                    }
                  })
                }
              </Select>
            )
          }
        }
      }
      return body
    },
  },

  computed: {
    width() {
      return '280px'
    }
  },

  render() {
    const body = this.createBody()

    const {label, key} = this.item

    return (<FormItem label={label}>{
      this.SearchRoot.$scopedSlots[key] && this.SearchRoot.$scopedSlots[key]({
        queryParams: this.queryParams,
        width: this.width
      })
      || body
    }</FormItem>)
  }
}
