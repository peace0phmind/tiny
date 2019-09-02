import Rest from '../lib/Rest'
import Sortable from 'sortablejs'
import * as Types from '../_util/javaTypes.js'
import * as RenderTypes from '../_util/renderType'
import './index.less'

export default {
  name: 's-operation-bar',

  props: {
    meta: {}
  },

  data() {
    return {
      allColumns: this.meta._columns,
      metaData: [],
      formLayoutArray: this.meta._formLayout,
      formItems: this.meta._formItems,
      formLayoutMeta: [],
      formLayoutItems: [],
      tags: [],
      searchItemArray: this.meta._searchItems,
      searchMetaData: [],
      columnSettingShow: false,
      formSettingShow: false,
      searchSettingShow: false,
    }
  },

  created() {
    this.restTemplate = new Rest('dev', this.$apiURL)

    if (this.allColumns) {
      this.initMetaData()
    }
    this.initFormLayoutMeta()
    this.initFormLayoutItems()
    this.initSearchMetaData()
  },

  methods: {
    initMetaData() {
      this.metaData = this.allColumns.map(column => {
        const {slot, title, align, fixed, hide, minWidth, width, type, tooltip, sortable, defaultSort, renderTo, summary, flatAttr, template, dotStatus} = column
        return {
          slot,
          title,
          align,
          fixed,
          hide,
          minWidth: minWidth || width,
          type,
          tooltip,
          sortable,
          defaultSort,
          flatAttr,
          renderTo,
          summary,
          template,
          dotStatus
        }
      })
    },
    initFormLayoutMeta() {
      this.formLayoutMeta = this.formLayoutArray.map(layout => {
        return layout.map(item => {
          if (item && item.prop) {
            const {classes, dom, label} = this.getFormItemPageMeta(item.prop)
            return {text: `${label}[${dom}]` || '占位符', prop: item.prop, classes}
          } else {
            return {text: '占位符', prop: '占位符', classes: 'empty-col'}
          }
        })
      })
    },
    initFormLayoutItems() {
      this.formLayoutItems = this.formItems.filter(item => item.type !== Types.Collection).map(item => {
        const {classes, dom, label} = this.getFormItemPageMeta(item.prop)

        return {text: `${label}[${dom}]` || '占位符', prop: item.prop, classes}
      })
      this.formLayoutItems.push({text: '占位符', prop: '占位符', classes: 'empty-col'})
    },
    initSearchMetaData() {
      this.searchMetaData = this.searchItemArray.map(item => {
        const {prop, label, defaultShow, operation, hide} = item
        return {prop, label, defaultShow, operation, hide}
      })
    },
    saveColumnSetting() {
      this.restTemplate.PUT({
        uri: 'layout/column',
        data: this.metaData,
        params: {relativePath: this.relativePath}
      }).then(res => {
        this.$Message.success('操作成功, 正在编译文件')
      })
    },
    saveFormSetting() {
      this.restTemplate.PUT({
        uri: 'view/form',
        data: this.formLayoutMeta,
        params: {relativePath: this.relativePath}
      }).then(res => {
        this.$Message.success('操作成功, 正在编译文件')
      })
    },
    saveSearchSetting() {
      this.restTemplate.PUT({
        uri: 'view/search',
        data: this.searchMetaData,
        params: {relativePath: this.relativePath}
      }).then(res => {
        this.$Message.success('操作成功, 正在编译文件')
      })
    },
    addFromRow() {
      this.formLayoutArray.push([])
      this.formLayoutMeta.push([])
      this.tags.push('')
    },
    removeFormRow(index) {
      this.formLayoutArray.splice(index, 1)
      this.formLayoutMeta.splice(index, 1)
    },
    moveUpFormItem(index) {
      if (index > 0) {
        const target = this.formLayoutArray.splice(index, 1)[0]
        this.formLayoutArray.splice(index - 1, 0, target)
        const targetMeta = this.formLayoutMeta.splice(index, 1)[0]
        this.formLayoutMeta.splice(index - 1, 0, targetMeta)
      } else {
        this.$Message.warning('已经处于第一个位置，无法上移')
      }
    },
    moveDownFormItem(index) {
      if (index < this.formLayoutArray.length - 1) {
        const target = this.formLayoutArray.splice(index, 1)[0]
        this.formLayoutArray.splice(index + 1, 0, target)
        const targetMeta = this.formLayoutMeta.splice(index, 1)[0]
        this.formLayoutMeta.splice(index + 1, 0, targetMeta)
      } else {
        this.$Message.warning('已经处于最后一个位置，无法下移')
      }
    },
    moveUp(arr, value, key) {
      const index = arr.findIndex(item => item[key] === value)
      if (index > 0) {
        const target = arr.splice(index, 1)[0]
        arr.splice(index - 1, 0, target)
      } else {
        this.$Message.warning('已经处于第一个位置，无法上移')
      }
    },
    moveDown(arr, value, key) {
      const index = arr.findIndex(item => item[key] === value)
      if (index < arr.length - 1) {
        const target = arr.splice(index, 1)[0]
        arr.splice(index + 1, 0, target)
      } else {
        this.$Message.warning('已经处于最后一个位置，无法下移')
      }
    },
    clickFromItem(prop, index) {
      if (prop === '占位符') {
        this.formLayoutMeta[index].push({
          text: '占位符',
          prop: '占位符',
          classes: 'empty-col'
        })
      } else {
        const {classes, dom, label} = this.getFormItemPageMeta(prop)
        this.formLayoutMeta[index].push({
          text: `${label}[${dom}]`,
          prop,
          classes
        })
      }
    },
    getFormItemBySlot(slot) {
      if (!slot) return undefined
      return this.formItems.find(formItem => formItem.prop === slot)
    },
    getFormItemPageMeta(prop) {
      const formItem = this.formItems.find(item => item.prop === prop)
      if (formItem) {
        const {type, tree} = formItem
        let classes = 'input'
        let dom = '文本框'
        if (Types.isString(type)) {
          classes = 'input'
          dom = '文本框'
        } else if (Types.isDecimal(type)) {
          classes = 'input-number'
          dom = '数值框'
        } else if (Types.isBoolean(type)) {
          classes = 'switch'
          dom = '开关'
        } else if (Types.isDateType(type)) {
          classes = 'datetime'
          dom = '日期框'
        } else if (Types.isModel(type)) {
          if (tree) {
            classes = 'cascader'
            dom = '级联框'
          } else {
            classes = 'select'
            dom = '下拉框'
          }
        }
        return {classes, dom, label: formItem.label}
      } else {
        return {classes: '', dom: '', label: '占位符'}
      }
    }
  },

  mounted() {
    const formSetting = document.querySelector('#metaForm')
    Sortable.create(formSetting, {
      onEnd({newIndex, oldIndex}) {
        const targetRow = self.formLayoutArray.splice(oldIndex, 1)[0]
        self.formLayoutArray.splice(newIndex, 0, [...targetRow])
        const targetMeta = self.formLayoutMeta.splice(oldIndex, 1)[0]
        self.formLayoutMeta.splice(newIndex, 0, [...targetMeta])
      }
    })
  },

  computed: {
    relativePath() {
      const filePath = this.$pool[this.meta.name].__file.__file
      const [src, view, ...relativePath] = filePath.split('/').slice(0, -1)
      return relativePath.join('/')
    },
  },

  render() {
    const btns = this.$slots.default
    const vm = this

    const titleSlot = {
      default: (scope) => {
        return (
          <Input v-model={scope.row.title} disabled={scope.row.slot === 'selection'}/>
        )
      }
    }

    const alignSlot = {
      default: (scope) => {
        return (
          <Select v-model={scope.row.align} size={'small'} {...{style: {width: '100%'}}} clearable filterable transfer>
            <Option value={'center'}>居中</Option>
            <Option value={'left'}>居左</Option>
            <Option value={'right'}>居右</Option>
          </Select>
        )
      }
    }

    const fixedSlot = {
      default: (scope) => {
        return (
          <Select v-model={scope.row.fixed} size={'small'} {...{style: {width: '100%'}}} clearable filterable transfer>
            <Option value={'left'}>固定左侧</Option>
            <Option value={'right'}>固定右侧</Option>
          </Select>
        )
      }
    }

    const hideSlot = {
      default: (scope) => {
        return (
          <i-switch v-model={scope.row.hide} size={'small'}/>
        )
      }
    }

    const sortSlot = {
      default: (scope) => {
        if (scope.row.slot !== 'selection') {
          return (
            <i-switch v-model={scope.row.sortable} size={'small'}/>
          )
        } else return (<span></span>)
      }
    }

    const defaultSortSlot = {
      default: (scope) => {
        if (scope.row.slot !== 'selection') {
          return (
            <i-switch v-model={scope.row.defaultSort} size={'small'}/>
          )
        } else return (<span></span>)
      }
    }

    const renderSlot = {
      default: (scope) => {
        if (scope.row.slot !== 'selection') {
          const formItem = vm.getFormItemBySlot(scope.row.slot)
          if (formItem) {
            const {type, modelType} = formItem
            return (
              <Select v-model={scope.row.renderTo} size={'small'} {...{style: {width: '100%'}}} clearable filterable
                      transfer>
                <Option value={RenderTypes.AVATAR_NEW_WINDOW}>头像[新窗口]</Option>
                <Option value={RenderTypes.IMAGE_MODAL}>图片[弹出层]</Option>
                <Option value={RenderTypes.LINK_NEW_WINDOW}>链接[新窗口]</Option>
                <Option value={RenderTypes.LINK_MODAL}>链接[弹出层]</Option>
                {Types.isCommonOrSuperModel(type, modelType) &&
                <Option value={RenderTypes.MODEL_DETAIL}>对象详情[弹出层]</Option>}
                {Types.isBoolean(type) && <Option value={RenderTypes.SWITCH}>开关</Option>}
                <Option value={RenderTypes.TAG}>标签</Option>
                <Option value={RenderTypes.BADGE} disabled>徽标</Option>
              </Select>
            )
          } else return (<span></span>)
        } else return (<span></span>)
      }
    }

    const templateSLot = {
      default: (scope) => {
        const column = vm.allColumns.find(column => column.slot === scope.row.slot)
        const {type} = column
        if ([Types.Boolean, Types.DateTime].includes(type) || scope.row.type === 'selection' || scope.row.slot === '_action') {
          return (<span></span>)
        } else {
          return (
            <Input v-model={scope.row.template} placeholder={'字符串模板，遵循es6'}
                   size={'small'} {...{style: {width: '100%'}}} clearable/>
          )
        }
      }
    }

    const dotSlot = {
      default: (scope) => {
        const renderTo = scope.row.renderTo
        if (renderTo === RenderTypes.BADGE) {
          const column = vm.allColumns.find(column => column.slot === scope.row.slot)
          if (column) {
            const {type, modelType, dotStatus, enumName} = column
            if (Types.isEnum(type, modelType)) {
              if (dotStatus) {
                scope.row.dotStatus = dotStatus
              } else {
                scope.row.dotStatus = {}
              }
              const enumObj = vm.meta._enums[enumName]
              const enumStatusSetting = {}
              const enumData = []
              Object.entries(enumObj).forEach(([enumValue, enumNameObj]) => {
                enumData.push({
                  enumValue,
                  enumName: enumNameObj.name,
                  status: scope.row.dotStatus[enumValue]
                })
              })
              const statusSlot = {
                default: (statusScope) => {
                  return (
                    <Select placeholder={'预设徽标点颜色'} v-model={scope.row.dotStatus[statusScope.row.enumValue]}>
                      <Option value={'success'}><Badge status="success"/> 成功</Option>
                      <Option value={'error'}><Badge status="error"/> 失败</Option>
                      <Option value={'default'}><Badge status="default"/> 默认</Option>
                      <Option value={'processing'}><Badge status="processing"/> 处理中</Option>
                      <Option value={'warning'}><Badge status="warning"/> 警告</Option>
                    </Select>
                  )
                }
              }

              enumStatusSetting.content = () => {
                return (
                  <el-table border data={enumData} empty-text={'暂无数据'} size={'mini'} max-height={400}>
                    <el-table-column prop={'enumValue'} label={'枚举值'} minWidth={120}></el-table-column>
                    <el-table-column prop={'enumName'} label={'枚举名称'} minWidth={120}></el-table-column>
                    <el-table-column prop={'status'} label={'状态'} minWidth={120}
                                     scopedSlots={statusSlot}></el-table-column>
                  </el-table>
                )
              }

              return (
                <Poptip placement={'left-end'} scopedSlots={enumStatusSetting}>
                  <el-link type={'primary'}>配置徽标</el-link>
                </Poptip>
              )
            } else {
              return (
                <Select placeholder={'预设徽标点颜色'} v-model={scope.row.dotStatus}>
                  <Option value={'success'}><Badge status="success"/> 成功</Option>
                  <Option value={'error'}><Badge status="error"/> 失败</Option>
                  <Option value={'default'}><Badge status="default"/> 默认</Option>
                  <Option value={'processing'}><Badge status="processing"/> 处理中</Option>
                  <Option value={'warning'}><Badge status="warning"/> 警告</Option>
                </Select>
              )
            }
          } else {
            return (<span></span>)
          }
        } else {
          return (<span></span>)
        }
      }
    }

    const tagColorSlot = {
      default: (scope) => {
        const renderTo = scope.row.renderTo
        if (renderTo === RenderTypes.TAG) {
          const column = vm.allColumns.find(column => column.slot === scope.row.slot)
          if (column) {
            const {type, modelType, tagColor, enumName} = column
            if (Types.isEnum(type, modelType)) {
              if (tagColor) {
                scope.row.tagColor = tagColor
              } else {
                scope.row.tagColor = {}
              }
              const enumObj = vm.meta._enums[enumName]
              const enumColorSetting = {}
              const enumData = []
              Object.entries(enumObj).forEach(([enumValue, enumNameObj]) => {
                enumData.push({
                  enumValue,
                  enumName: enumNameObj.name,
                  color: scope.row.tagColor[enumValue]
                })
              })
              const colorSlot = {
                default: (colorScope) => {
                  return (
                    <input type={'color'} value={scope.row.tagColor[colorScope.row.enumValue]} onChange={(e) => {
                      scope.row.tagColor[colorScope.row.enumValue] = e.target.value
                    }}/>
                  )
                }
              }

              enumColorSetting.content = () => {
                return (
                  <el-table border data={enumData} empty-text={'暂无数据'} size={'mini'} max-height={400}>
                    <el-table-column prop={'enumValue'} label={'枚举值'} minWidth={120}></el-table-column>
                    <el-table-column prop={'enumName'} label={'枚举名称'} minWidth={120}></el-table-column>
                    <el-table-column prop={'color'} label={'颜色'} minWidth={60}
                                     scopedSlots={colorSlot}></el-table-column>
                  </el-table>
                )

              }

              return (
                <Poptip placement={'left-end'} scopedSlots={enumColorSetting}>
                  <el-link type={'primary'}>配置颜色</el-link>
                </Poptip>
              )
            } else {
              return (
                <input type={'color'} value={scope.row.tagColor} onChange={(e) => {
                  scope.row.tagColor = e.target.value
                }}/>
              )
            }
          } else {
            return (<span></span>)
          }
        } else {
          return (<span></span>)
        }
      }
    }

    const minWidthSlot = {
      default: (scope) => {
        return (
          <Input type={'number'} v-model={scope.row.minWidth}/>
        )
      }
    }

    const summarySlot = {
      default: (scope) => {
        if (scope.row.slot !== 'selection' && scope.row.slot !== '_action') {
          return (
            <i-switch v-model={scope.row.summary} size={'small'}/>
          )
        } else return (<span></span>)
      }
    }

    const flatAttrSlot = {
      default: (scope) => {
        if (scope.row.slot !== 'selection' && scope.row.slot !== '_action') {
          const formItem = vm.getFormItemBySlot(scope.row.slot)
          if (formItem) {
            const {type, modelType} = formItem
            if (Types.isCommonOrSuperModel(type, modelType)) {
              const {componentName} = formItem
              const {meta} = vm.$pool[componentName]
              const formItems = meta._formItems.filter(item => !Types.isCollection(item.type) || !item.collection)
              return (
                <el-select v-model={scope.row.flatAttr} multiple allow-create placeholder={'不拉平属性'} size={'mini'}
                           no-data-text={'属性为空'}
                           {...{style: {width: '100%'}}}>
                  {formItems.map(item => {
                    return (
                      <el-option value={item.prop} label={item.label}></el-option>
                    )
                  })}
                </el-select>
              )
            } else if (Types.isString(type)){
              return (
                <el-select v-model={scope.row.flatAttr} multiple allow-create filterable default-first-option placeholder={'当值为json生效'} size={'mini'}
                           no-data-text={'属性为空'}
                           {...{style: {width: '100%'}}}>
                </el-select>
              )
            } else return (<span></span>)
          } else return (<span></span>)
        } else return (<span></span>)
      }
    }

    const columnActionSlot = {
      default: (scope) => {
        if (scope.row.slot !== '_action') {
          return (
            <div>
              <el-link onClick={(e) => this.moveUp(this.metaData, scope.row.slot, 'slot')} {...{
                style: {
                  fontSize: '20px',
                  cursor: 'pointer'
                }
              }}>
                <Icon type="ios-arrow-dropup"/>
              </el-link>
              <el-link onClick={(e) => this.moveDown(this.metaData, scope.row.slot, 'slot')} {...{
                style: {
                  fontSize: '20px',
                  marginLeft: '5px',
                  cursor: 'pointer'
                }
              }}>
                <Icon type="ios-arrow-dropdown"/>
              </el-link>
            </div>
          )
        } else {
          return (<span></span>)
        }
      }
    }

    const columnSetting = {}

    columnSetting.default = () => {
      return (
        <div {...{
          style: {
            maxHeight: '530px',
            overflow: 'auto'
          }
        }}>
          <el-table row-key={'slot'} id={'metaTable'} border data={this.metaData} ref={'table'}
                    empty-text={'暂无数据'} size={'mini'} max-height={500}>
            <el-table-column prop={'slot'} label={'字段名称'} minWidth={140}></el-table-column>
            <el-table-column prop={'title'} label={'标题'} minWidth={120}
                             scopedSlots={titleSlot}></el-table-column>

            <el-table-column prop={'align'} label={'对齐'} minWidth={80}
                             scopedSlots={alignSlot}></el-table-column>

            <el-table-column prop={'fixed'} label={'固定'} minWidth={100}
                             scopedSlots={fixedSlot}></el-table-column>

            <el-table-column prop={'hide'} label={'隐藏'} minWidth={50}
                             scopedSlots={hideSlot}></el-table-column>

            <el-table-column prop={'sortable'} label={'可排序'} minWidth={50}
                             scopedSlots={sortSlot}></el-table-column>

            <el-table-column prop={'defaultSort'} label={'默认排序'} minWidth={50}
                             scopedSlots={defaultSortSlot}></el-table-column>

            <el-table-column prop={'summary'} label={'可合计'} minWidth={50}
                             scopedSlots={summarySlot}></el-table-column>

            <el-table-column prop={'flatAttr'} label={'拉平字段'} minWidth={260}
                             scopedSlots={flatAttrSlot}></el-table-column>

            <el-table-column prop={'renderTo'} label={'渲染为'} minWidth={100}
                             scopedSlots={renderSlot}></el-table-column>

            <el-table-column prop={'template'} label={'模板'} minWidth={250}
                             scopedSlots={templateSLot}></el-table-column>

            <el-table-column prop={'tagColor'} label={'标签颜色'} minWidth={100}
                             scopedSlots={tagColorSlot}></el-table-column>

            <el-table-column prop={'dotStatus'} label={'徽标状态'} minWidth={100}
                             scopedSlots={dotSlot}></el-table-column>

            <el-table-column prop={'minWidth'} label={'最小宽度'} minWidth={75}
                             scopedSlots={minWidthSlot}></el-table-column>

            <el-table-column prop={'_action'} label={'操作'} fixed={'right'} minWidth={80}
                             scopedSlots={columnActionSlot}></el-table-column>
          </el-table>
        </div>
      )
    }

    const formItemDropdownSlot = {}
    formItemDropdownSlot.list = () => {
      return (
        <DropdownMenu {...{style: {maxHeight: '300px', overflowY: 'auto'}}}>
          {
            this.formLayoutItems.map(item => {
              return (
                <DropdownItem name={item.prop}>{item.text}({item.prop})</DropdownItem>
              )
            })
          }
        </DropdownMenu>
      )
    }

    const autoCompleteFormItems = () => {

    }

    const formItemSetting = {}
    formItemSetting.default = () => {
      return (
        <div id={'metaForm'} {...{
          style: {
            maxHeight: '530px',
            overflow: 'auto'
          }
        }}>
          {
            this.formLayoutArray.map((layout, index) => {
              return (
                <div>
                  <vue-tags-input v-model={this.tags[index]} tags={this.formLayoutMeta[index]}
                                  avoid-adding-duplicates={false} placeholder={'输入属性'}
                                  autocomplete-items={
                                    this.formLayoutItems.filter(layoutItems => layoutItems.text.includes(this.tags[index]))
                                  }
                                  vOn:tags-changed={(newTags) => this.formLayoutMeta[index] = newTags} {...{
                    style: {
                      width: '705px',
                      display: 'inline-block'
                    }
                  }}/>
                  <Dropdown {...{
                    style: {
                      marginLeft: '5px', marginBottom: '6px'
                    }
                  }} scopedSlots={formItemDropdownSlot}
                            vOn:on-click={(name) => this.clickFromItem(name, index)}>
                    <a href={'javascript:void(0)'}>
                      选择属性
                      <Icon type={'ios-arrow-down'}></Icon>
                    </a>
                  </Dropdown>
                  <Button {...{
                    style: {
                      marginLeft: '5px', marginBottom: '6px'
                    }
                  }} type={'error'} icon={'ios-trash-outline'}
                          onClick={(e) => this.removeFormRow(index)}></Button>
                  <el-link onClick={(e) => this.moveUpFormItem(index)} {...{
                    style: {
                      fontSize: '20px',
                      cursor: 'pointer',
                      marginLeft: '5px',
                      marginBottom: '6px'
                    }
                  }}>
                    <Icon type="ios-arrow-dropup"/>
                  </el-link>
                  <el-link onClick={(e) => this.moveDownFormItem(index)} {...{
                    style: {
                      fontSize: '20px',
                      marginLeft: '5px',
                      cursor: 'pointer',
                      marginBottom: '6px'
                    }
                  }}>
                    <Icon type="ios-arrow-dropdown"/>
                  </el-link>
                </div>
              )
            })
          }
        </div>
      )
    }

    formItemSetting.footer = () => {
      return (
        <div {...{
          style: {
            height: '40px',
            padding: '5px'
          }
        }}>
          <Button {...{
            style: {
              float: 'left'
            }
          }} type={'primary'} icon={'ios-add'} onClick={this.addFromRow}>新增一行</Button>
          <Button {...{
            style: {
              float: 'right'
            }
          }} type={'primary'} onClick={this.saveFormSetting}>保存</Button>
        </div>
      )
    }

    const labelSlot = {
      default: (scope) => {
        return (
          <Input v-model={scope.row.label}/>
        )
      }
    }

    const defaultShowSlot = {
      default: (scope) => {
        return (
          <i-switch v-model={scope.row.defaultShow} size={'small'}/>
        )
      }
    }

    const operationSlot = {
      default: (scope) => {
        const formItem = vm.getFormItemBySlot(scope.row.prop)
        if (formItem) {
          const {type} = formItem
          return (
            <Select v-model={scope.row.operation} size={'small'} {...{style: {width: '100%'}}} clearable transfer>
              {[Types.String].includes(type) && <Option value={'contains'}>包含</Option>}
              {[Types.String, Types.Boolean, Types.Model].includes(type) && <Option value={'equals'}>等于</Option>}
              {[Types.Model].includes(type) && <Option value={'in'}>在...之内</Option>}
              {[Types.Decimal, Types.DateTime].includes(type) && <Option value={'range'}>区间</Option>}
            </Select>
          )
        } else return ({})

      }
    }

    const searchHideSlot = {
      default: (scope) => {
        return (
          <i-switch v-model={scope.row.hide} size={'small'}/>
        )
      }
    }

    const searchActionSlot = {
      default: (scope) => {
        return (
          <div>
            <Button onClick={(e) => this.moveUp(this.searchMetaData, scope.row.key, 'key')}>上移</Button>
            <Button onClick={(e) => this.moveDown(this.searchMetaData, scope.row.key, 'key')}>下移</Button>
          </div>
        )
      }
    }

    const searchItemsSetting = {}
    searchItemsSetting.default = () => {
      return (
        <div {...{
          style: {
            maxHeight: '530px',
            overflow: 'auto'
          }
        }}>
          <el-table row-key={'prop'} border data={this.searchMetaData} ref={'searchItemSettingTable'}
                    empty-text={'暂无数据'} size={'mini'} max-height={500}>
            <el-table-column prop={'prop'} label={'字段名称'} minWidth={150}></el-table-column>
            <el-table-column prop={'label'} label={'标题'} minWidth={150}
                             scopedSlots={labelSlot}></el-table-column>
            <el-table-column prop={'defaultShow'} label={'默认显示'} minWidth={150}
                             scopedSlots={defaultShowSlot}></el-table-column>
            <el-table-column prop={'operation'} label={'操作条件'} minWidth={150}
                             scopedSlots={operationSlot}></el-table-column>
            <el-table-column prop={'hide'} label={'总是隐藏'} minWidth={100}
                             scopedSlots={searchHideSlot}></el-table-column>
            {/*<el-table-column prop={'_action'} label={'操作'} minWidth={150}*/}
            {/*                 scopedSlots={searchActionSlot}></el-table-column>*/}
          </el-table>
        </div>
      )
    }

    const width = document.documentElement.clientWidth * 0.8

    const columnModal = (
      <Modal v-model={this.columnSettingShow} title={'列配置'} width={width} vOn:on-ok={this.saveColumnSetting}
             ok-text={'保存'} scopedSlots={columnSetting}>
      </Modal>
    )

    const formModal = (
      <Modal v-model={this.formSettingShow} title={'表单布局配置'} width={906} scopedSlots={formItemSetting}>
      </Modal>
    )
    const searchModal = (
      <Modal v-model={this.searchSettingShow} title={'搜索条件配置'} width={width} vOn:on-ok={this.saveSearchSetting}
             ok-text={'保存'} scopedSlots={searchItemsSetting}>
      </Modal>
    )

    return (
      <Row {...{style: {paddingBottom: '10px'}}}>
        {btns}
        {
          this.$layoutEditable && (
            <div {...{
              style: {
                display: 'inline'
              }
            }}>
              {
                this.allColumns && (
                  <Button {...{
                    style: {
                      float: 'right'
                    }
                  }} icon={'ios-apps-outline'} type={'primary'} size={'small'}
                          onClick={(e) => this.columnSettingShow = true}></Button>
                )
              }
              <Button {...{
                style: {
                  marginRight: '5px', float: 'right'
                }
              }} icon={'ios-browsers-outline'} type={'primary'} size={'small'}
                      onClick={(e) => this.formSettingShow = true}></Button>
              <Button {...{
                style: {
                  marginRight: '5px', float: 'right'
                }
              }} icon={'ios-search'} type={'primary'} size={'small'}
                      onClick={(e) => this.searchSettingShow = true}></Button>
              {columnModal}
              {formModal}
              {searchModal}
            </div>
          )
        }
      </Row>
    )
  }
}
