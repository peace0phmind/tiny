import Rest from '../lib/Rest'
import * as Types from '../_util/javaTypes.js'
import FormSlot from './form-slot.js'
import {copyObjectRenameAttrInArrRecursive} from '../lib/tools.js'

export default {
  name: 's-form-item',
  props: {
    item: {},
    formModel: {},
    disabled: {},
    enums: {},
    formProps: {},
    hidden: {}
  },
  components: {FormSlot},
  data() {
    return {
      selectModelWidth: {},
      modelSelection: []
    }
  },
  render() {
    const {item, enums} = this
    const {customRender, prop, label, type, key, hidden} = item
    const placeholder = type === Types.DateTime || type === Types.Model ? `请选择${label}` : `请输入${label}`
    const vm = this
    if (customRender) {
      return (
        <form-slot formModel={this.formModel} item={item} disabled={this.disabled}></form-slot>
      )
    } else {
      let _item

      if (type === Types.String) {
        const {upload, textarea} = item
        if (upload) { // upload component
          const {name, multiple, action, type = 'drag', accept, format, maxSize, defaultFileList} = upload
          _item = (<Upload multiple={multiple} name={name} type={type} action={action} accept={accept}
                           format={format}
                           max-size={maxSize} default-file-list={defaultFileList}
                           vOn:on-success={this.uploadSuccess}>
            <div style="padding: 20px 0">
              <Icon type="ios-cloud-upload" size="52" style="color: #3399ff"></Icon>
              <p>点击或拖拽文件到这里上传</p>
            </div>
          </Upload>)
        } else if (textarea) { //textarea
          const type = 'textarea'
          const {minRows, maxRows} = textarea
          _item = (
            <Input v-model={this.formModel[prop]} placeholder={placeholder}
                   disabled={this.disabled[prop]} type={type}
                   {...{autosize: {minRows, maxRows}}} clearable
            ></Input>
          )
        } else {
          _item = (
            <Input v-model={this.formModel[prop]} placeholder={placeholder}
                   disabled={this.disabled[prop]} clearable></Input>
          )
        }
      } else if (type === Types.Decimal) {
        const {precision} = item
        let step = 1
        if (precision) step = 0.1
        _item = (
          <Input v-model={this.formModel[prop]} placeholder={placeholder}
                 disabled={this.disabled[prop]} clearable type={'number'}></Input>
        )
      } else if (type === Types.Boolean) {
        _item = (
          <i-switch v-model={this.formModel[prop]} size={'small'} disabled={this.disabled[prop]}/>
        )
      } else if (type === Types.DateTime) {
        const {dateType} = item
        if (dateType === Types.DateType.Default) {
          _item = (
            <DatePicker v-model={this.formModel[prop]} placeholder={placeholder}
                        disabled={this.disabled[prop]} clearable
                        type={'datetime'} {...{style: {width: '100%'}}}></DatePicker>
          )
        } else if (dateType === Types.DateType.DateOnly) {
          _item = (
            <DatePicker v-model={this.formModel[prop]} placeholder={placeholder}
                        disabled={this.disabled[prop]} clearable
                        type={'date'} {...{style: {width: '100%'}}}></DatePicker>
          )
        } else {
          _item = (
            <TimePicker v-model={this.formModel[prop]} placeholder={placeholder}
                        disabled={this.disabled[prop]} clearable
                        type={'time'} {...{style: {width: '100%'}}}></TimePicker>
          )
        }
      } else if (type === Types.Model) {
        const {modelType, multiple} = item
        if (modelType === Types.ModelType.Enum) {
          const {enumName} = item
          _item = (
            <Select v-model={this.formModel[prop]} multiple={multiple} placeholder={placeholder} disabled={this.disabled[prop]}
                    clearable
                    filterable vOn:on-clear={(e) => vm.clearSelect(item)}>
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
        } else if (modelType === Types.ModelType.Common || modelType === Types.ModelType.Super) {
          const vm = this
          const {arr, tree} = item
          if (tree) {
            _item = (
              <Cascader v-model={this.formModel[prop]} data={arr} filterable change-on-select clearable
                        vOn:on-visible-change={(value) => vm.onOptionChange(item.prop, value)}
                        selectModel={true}
                        vOn:on-select-model={(e) => vm.openSelectModel(item)}
                        vOn:on-change={(value) => vm.onOptionChange(item.prop, value && value.length && value[value.length - 1] || null)}></Cascader>
            )
          } else {
            _item = (
              <Select v-model={this.formModel[prop]} placeholder={placeholder}
                      disabled={this.disabled[prop]} clearable
                      filterable vOn:on-clear={(e) => vm.clearSelect(item)}
                      vOn:on-change={(value) => vm.onOptionChange(item.prop, value)}
                      selectModel={true} vOn:on-select-model={(e) => vm.openSelectModel(item)}
                      vOn:on-open-change={(status) => vm.onOpenChange(item, status)} {...{style: {width: this.width}}}>
                {
                  arr.map(v => {
                    if (v && v.id) {
                      return (
                        <Option value={v.id}>
                          {v._instanceName}
                        </Option>
                      )
                    }
                  })
                }
              </Select>
            )
          }
        }
      }

      return (
        <FormItem label={label} prop={prop} hidden={this.hidden[prop]}>
          {_item}
        </FormItem>
      )
    }
  },
  created() {
    this.initModelOptions()
  },
  methods: {
    uploadSuccess(response, file, fileList) {

    },
    /* init model select options */
    initModelOptions() {
      let item = this.item
      item.type === Types.Model && (item.modelType === Types.ModelType.Common || item.modelType === Types.ModelType.Super) && this.pullOptions(item)
    },
    /* pull select options with resourcePath, put into specified location */
    pullOptions(item) {
      const _rest = new Rest(item.resourcePath, this.$apiURL)
      const {tree} = item
      if (tree) {
        const vm = this
        _rest.GET({
          uri: `tree`,
          params: {level: 10}
        }).then((res) => {
          item.arr = copyObjectRenameAttrInArrRecursive({
            src: res,
            correspondence: {_instanceName: 'label', id: 'value', _children: 'children'}
          })
          vm.$forceUpdate()
        })
      } else {
        const vm = this
        _rest.GET({
          params: {
            page: 0,
            size: 1000
          }
        }).then((res) => {
          if (res) {
            item.arr = res[vm.formProps.list]
            vm.$forceUpdate()
          }
        })
      }
    },
    onOpenChange(item, status) {
      if (status === true) {
        this.pullOptions(item)
      }
    },
    onOptionChange(prop, value) {
      this.$emit('on-option-change', {prop, value})
    },
    clearSelect(item) {
      // ignore
    },
    openSelectModel(item) {
      const vm = this
      const width = document.documentElement.clientWidth * 0.8
      this.$Modal.confirm({
        title: item.label,
        closable: false,
        fullscreen: true,
        width: width,
        render: (h) => {
          return h(item.componentName, {
            ref: item.prop,
            props: {
              showSearch: true
            },
            on: {
              'on-check': (selection) => {
                vm.modelSelection = selection
              }
            }
          })
        },
        onOk: () => {
          this.initModelOptions()
          if (item.tree) {
            const id = this.modelSelection[0][this.formProps.id]
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
                this.$set(this.formModel, item.prop, arr)
              })
            }
          } else {
            this.$set(this.formModel, item.prop, this.modelSelection[0][this.formProps.id])
          }
          this.modelSelection = []
        },
        onCancel: () => {
          this.modelSelection = []
        }
      })
    },
  }
}
