import {decorate} from './interface.js'
import STableSlot from './table-slot'
import Rest from '../lib/Rest'
import * as Types from '../_util/javaTypes.js'
import * as RenderTypes from '../_util/renderType'
import './index.less'

export default {

  name: 'e-table',
  props: {
    columns: Array,

    data: Array,

    enums: {},

    rowClassName: {},

    meta: {},

    resourcePath: {},

    maxHeight: {default: undefined}
  },

  components: {STableSlot},

  data() {
    return {
      columnArray: this.columns,
      selection: [],
      allColumns: [],
      metaData: []
    }
  },

  provide() {
    return {
      eTableRoot: this
    }
  },

  created() {
    this.restTemplate = new Rest('dev', this.$apiURL)
    this.allColumns = JSON.parse(JSON.stringify(this.columnArray))
    this.initMetaData()
    this.columnArray = this.columnArray.filter(column => !column.hide)
  },
  computed: {
    relativePath() {
      const filePath = this.$pool[this.meta.name].__file.__file
      const [src, view, ...relativePath] = filePath.split('/').slice(0, -1)
      return relativePath.join('/')
    },
  },
  methods: {
    getSelection() {
      return this.selection
    },
    clearSelection() {
      this.$refs.table.clearSelection()
    },
    toggleRowSelection(row, selected) {
      this.$refs.table.toggleRowSelection(row, selected)
    },
    onSelectionChange(selection) {
      this.selection = selection
      this.$emit('on-selection-change', selection)
    },
    headDragend(newWidth, oldWidth, column, event) {
      const {property} = column
      const meta = this.metaData.find(meta => meta.slot === property)
      meta.minWidth = Math.floor(newWidth)
      this.saveColumnSetting()
    },
    initMetaData() {
      this.metaData = this.allColumns.map(column => {
        const {slot, title, align, fixed, hide, minWidth, width, type, tooltip, sortable, defaultSort, renderTo, summary, flatAttr, template, tagColor, dotStatus} = column
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
          tagColor,
          dotStatus
        }
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
    showModelDetail(column, model) {

      const {meta: {_formLayout: formLayout, _formItems: formItems, _enums: enums}} = this.$pool[column.componentName]
      const findLabel = (prop) => {
        return formItems.find(formItem => formItem.prop === prop).label
      }

      const buildColumn = (prop) => {
        const formItem = formItems.find(formItem => formItem.prop === prop)
        const column = Object.assign({}, formItem)
        column.slot = column.prop
        return column
      }

      this.$Modal.info({
        title: column.title,
        closable: false,
        width: 620,
        render: (h) => {
          return (
            <div>
              {
                formLayout.map(layout => {

                  const span = 24 / layout.length

                  const row = {}
                  for (let item of layout) {
                    if (item.prop) row[item.prop] = (row[item.prop] || 0) + 1
                  }

                  return (
                    <Row gutter={20} {...{style: {marginBottom: '7px', fontSize: '12px'}}}>
                      {
                        Object.entries(row).map(([prop, count]) => {
                          if (model[prop]) {
                            const row = {}
                            row[prop] = model[prop]
                            const _v = decorate({
                              row,
                              column: buildColumn(prop),
                              enums,
                              pool: this.$pool
                            })

                            return (
                              <Col span={span * count}>
                                <b>{findLabel(prop)}: </b>
                                <span type={'info'}>{_v && _v || '-'}</span>
                              </Col>
                            )
                          } else {
                            return (
                              <Col span={span * count}>
                                <b>{findLabel(prop)}: </b>
                                <span type={'info'}>{'-'}</span>
                              </Col>
                            )
                          }
                        })
                      }
                    </Row>
                  )
                })
              }
            </div>
          )
        }
      })
    },
    sortChange({column, prop, order}) {
      this.$emit('on-sort-change', {prop, order: order === 'descending' && 'desc' || 'asc'})
    },
    // render column
    decorator({column, value, model, enums, id}) {
      const {renderTo, flatAttr, tagColor, type, modelType, enumName, summary, dotStatus, slot} = column
      if (renderTo === RenderTypes.AVATAR_NEW_WINDOW) {
        return (<a href={value} target={'_blank'}><Avatar src={value}></Avatar></a>)
      } else if (renderTo === RenderTypes.IMAGE_MODAL) {
        const previewSrcList = [value]
        return (
          <el-image {...{style: {width: '40px', height: '40px'}}} src={value}
                    preview-src-list={previewSrcList}>
          </el-image>)

      } else if (renderTo === RenderTypes.LINK_NEW_WINDOW) {
        return (<a href={value} target={'_blank'}>{value}</a>)
      } else if (renderTo === RenderTypes.LINK_MODAL) {
        return (<a href={value}>{value}</a>)
      } else if (renderTo === RenderTypes.SWITCH) {
        return (<i-switch value={value} vOn:on-change={(val) => {
          const _rest = new Rest(this.resourcePath, this.$apiURL)
          const data = {id}
          data[slot] = val
          _rest.PUT({data}).then(res => {
            this.$Message.success('操作成功')
          }, error => this.$Message.error('操作失败'))
        }}></i-switch>)
      } else if (renderTo === RenderTypes.BADGE) {
        if (type === Types.Model && modelType === Types.ModelType.Enum) {
          let enumValue
          Object.entries(enums[enumName]).forEach(([enumV, enumOption]) => {
            if (enumOption.name === value) {
              enumValue = enumV
            }
          })
          const status = enumValue && dotStatus[enumValue] || 'success'
          return (
            <Badge status={status} text={value}/>
          )
        } else {
          return (<Badge status={dotStatus} text={value}/>)
        }
      } else if (renderTo === RenderTypes.TAG) {
        if (Types.isEnum(type, modelType)) {
          if (value.includes(',')) {
            const values = value.split(',')
            return (
              values.map(v => {
                let enumValue
                Object.entries(enums[enumName]).forEach(([enumV, enumOption]) => {
                  if (enumOption.name === v) {
                    enumValue = enumV
                  }
                })
                const color = enumValue && tagColor[enumValue] || 'primary'
                return (
                  <Tag color={color}>{v}</Tag>
                )
              })
            )
          } else {
            if (value.length) {
              let enumValue
              Object.entries(enums[enumName]).forEach(([enumV, enumOption]) => {
                if (enumOption.name === value) {
                  enumValue = enumV
                }
              })
              const color = enumValue && tagColor[enumValue] || 'primary'
              return (
                <Tag color={color}>{value}</Tag>
              )
            } else return (<span></span>)
          }
        } else {
          return (<Tag color={tagColor}>{value}</Tag>)
        }
      } else {
        return (
          <span domPropsInnerHTML={value}></span>
        )
      }
    },
    tableHeaderColor({row, column, rowIndex, columnIndex}) {
      if (rowIndex === 0) {
        return 'background-color: #f8f8f9'
      }
    },
  },
  render() {
    const _enums = this.enums

    const body = this.columnArray.map(column => {
      //
      const {title, type, modelType, slot, width, minWidth, align, tooltip, fixed, sortable, flatAttr, componentName} = column
      if (type === 'selection') {
        return (<el-table-column type={'selection'} prop={'_selection'} width={width}
                                 align={align}></el-table-column>)
      } else if (slot === '_action') {
        let _scopedSlot = {}
        _scopedSlot.default = (scope) => {
          return (<s-table-slot row={scope.row} index={undefined} column={column}></s-table-slot>)
        }
        return (
          <el-table-column label={title} prop={slot} align={align} width={width} fixed={fixed}
                           scopedSlots={_scopedSlot}>
          </el-table-column>)
      } else {
        let scopedSlot = {}
        scopedSlot.default = (scope) => {
          if (this.$scopedSlots[slot]) {
            return this.$scopedSlots[slot]({row: scope.row})
          } else {
            const value = decorate({row: scope.row, column, enums: _enums, pool: this.$pool})
            return this.decorator({column, value, model: scope.row[column.slot], enums: _enums, id: scope.row.id})
          }
        }

        if (flatAttr && flatAttr.length) {

          if (Types.isCommonOrSuperModel(type, modelType)) {
            const {_formItems: destFormItems, _enums: destEnums} = this.$pool[componentName].meta
            if (destFormItems) {
              const buildColumn = (prop) => {
                const formItem = destFormItems.find(formItem => formItem.prop === prop)
                const column = Object.assign({}, formItem)
                column.prop = `${slot}_${column.prop}`
                column.slot = column.prop
                return column
              }

              return (
                <el-table-column label={`${title}信息`} align={align} minWidth={minWidth} fixed={fixed}>
                  {
                    flatAttr.map((attr, index) => {
                      const destFormItem = destFormItems.find(formItem => formItem.prop === attr)
                      if (destFormItem) {
                        const {label, prop, type, modelType, dateType} = destFormItem

                        let destScopedSlot = {}
                        const col = `${slot}_${prop}`
                        destScopedSlot.default = (destScope) => {
                          destScope.row[col] = destScope.row[slot][prop]
                          const value = decorate({
                            row: destScope.row,
                            column: buildColumn(prop),
                            enums: destEnums,
                            pool: this.$pool
                          })
                          if (index === 0) {
                            return (<el-link type={'primary'}
                                             onClick={(e) => this.showModelDetail(column, destScope.row[slot])}>{value}</el-link>)
                          } else {
                            return (<span>{value}</span>)
                          }
                        }

                        return (
                          <el-table-column label={label} prop={col} align={align}
                                           sortable={sortable && 'custom' || undefined}
                                           scopedSlots={destScopedSlot}
                                           show-overflow-tooltip={tooltip}>
                          </el-table-column>
                        )
                      }
                    })
                  }
                </el-table-column>
              )

            } else {
              return (
                <el-table-column label={title} prop={slot} align={align} minWidth={minWidth}
                                 fixed={fixed}
                                 sortable={sortable && 'custom' || undefined}
                                 scopedSlots={scopedSlot} show-overflow-tooltip={tooltip}>
                </el-table-column>
              )
            }
          } else if (Types.isString(type)) {

            return (
              <el-table-column label={`${title}信息`} align={align} minWidth={minWidth} fixed={fixed}>
                {
                  flatAttr.map((attr) => {
                    try {
                      const [attrName, attrLabel] = attr.split(',')
                      const col = `${slot}_${attrName}`
                      const destScopedSlot = {}
                      destScopedSlot.default = (destScope) => {
                        const obj = JSON.parse(destScope.row[slot])
                        destScope.row[col] = obj[attrName] && obj[attrName] || '-'
                        return destScope.row[col]
                      }

                      return (
                        <el-table-column label={attrLabel} prop={col} align={align}
                                         sortable={sortable && 'custom' || undefined}
                                         scopedSlots={destScopedSlot}
                                         show-overflow-tooltip={tooltip}>
                        </el-table-column>
                      )
                    } catch (e) {
                      throw e
                    }
                  })
                }
              </el-table-column>
            )

          } else {
            return (
              <el-table-column label={title} prop={slot} align={align} minWidth={minWidth} fixed={fixed}
                               sortable={sortable && 'custom' || undefined}
                               scopedSlots={scopedSlot} show-overflow-tooltip={tooltip}>
              </el-table-column>
            )
          }

        } else {

          return (
            <el-table-column label={title} prop={slot} align={align} minWidth={minWidth} fixed={fixed}
                             sortable={sortable && 'custom' || undefined}
                             scopedSlots={scopedSlot} show-overflow-tooltip={tooltip}>
            </el-table-column>
          )
        }
      }
    })

    return (
      <div>
        <el-table border data={this.data} max-height={this.maxHeight} row-key={'id'}
                  vOn:selection-change={(selection) => this.onSelectionChange(selection)}
                  vOn:select={(selection, row) => this.$emit('on-select', {selection, row})}
                  ref={'table'} empty-text={'暂无数据'} size={'small'} header-cell-style={this.tableHeaderColor}
                  vOn:header-dragend={(newWidth, oldWidth, column, event) => {
                    this.headDragend(newWidth, oldWidth, column, event)
                  }}
                  vOn:sort-change={({column, prop, order}) => {
                    this.sortChange({column, prop, order})
                  }} row-class-name={this.rowClassName}>
          {body}
        </el-table>
      </div>
    )
  }
}
