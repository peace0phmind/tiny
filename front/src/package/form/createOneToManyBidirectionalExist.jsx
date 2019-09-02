import {getColumnsFromModel} from '../lib/tools'
import * as modes from '../_util/_mode'

export default {

  methods: {
    createOneToManyBidirectionalExist({item}) {
      let {meta: {_columns: columns, _enums: enums, model}} = this.$pool[item.componentName]
      const {prop, resourcePath} = item

      if (!columns && !model) {
        throw new Error('columns or model not exist')
      } else {
        const actionSlot = {
          _action: ({row}) => {
            return (
              <el-link icon={'el-icon-delete'} underline={false} type={'danger'}
                       onClick={() => this.handleRemoveOneToManyExist(row, item)}></el-link>
            )
          }
        }

        if (!columns || !columns.length) {
          columns = getColumnsFromModel(model, [])
        }

        const table = (
          <e-table ref={prop} columns={columns} data={this[`${prop}Data`]} enums={enums} max-height={200}
                   scopedSlots={actionSlot}>
          </e-table>
        )

        return (
          <div>
            <div {...{style: {marginBottom: '10px'}}}>
              <Button type="primary" disabled={this.readonly}
                      onClick={() => this.handleAddOneToManyExist(item)} v-permission:create_resource={resourcePath}><i
                className={'el-icon-plus'}></i>新增
              </Button>
            </div>
            {table}
          </div>
        )
      }
    },
    handleAddOneToManyExist(item) {
      const vm = this
      const width = document.documentElement.clientWidth * 0.8
      this.$Modal.confirm({
        title: item.label,
        closable: false,
        width: width,
        render: (h) => {
          return h(item.componentName, {
            ref: `${item.prop}Source`,
            props: {
              enterFromRoute: false,
              filters: item.filter
            },
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
          this.formModel[item.prop] = this.formModel[item.prop] || []
          this[`${item.prop}Data`] = this[`${item.prop}Data`] || []
          this.modelSelection[item.prop].forEach(sel => {
            sel = {...sel, _mode: modes.CREATE}
            if (!this.formModel[item.prop].find(m => m[item.primaryKey] === sel[item.primaryKey])) {
              this.formModel[item.prop].push(sel)
              const arr = JSON.parse(JSON.stringify(this.formModel[item.prop]))
              this.$delete(this.formModel, item.prop)
              this.$set(this.formModel, item.prop, arr)
            }
            !this[`${item.prop}Data`].find(m => m[item.primaryKey] === sel[item.primaryKey]) && this[`${item.prop}Data`].push(sel)
          })
          this.$forceUpdate()
          this.modelSelection[item.prop] = []
        },
        onCancel: () => {
          this.modelSelection[item.prop] = []
        }
      })
    },

    handleRemoveOneToManyExist(row, item) {
      const index = this[`${item.prop}Data`].findIndex(data => _.isEqual(row, data))
      const deletedRow = this[`${item.prop}Data`].splice(index, 1)[0]
      const {_mode} = deletedRow
      if (_mode !== modes.CREATE) {
        if (this.formModel[item.prop]) {
          const idx = this.formModel[item.prop].findIndex(m => m[item.primaryKey] === deletedRow[item.primaryKey])
          if (idx >= 0) this.formModel[item.prop][idx]._mode = modes.DELETE
          else this.formModel[item.prop].push({...deletedRow, _mode: modes.DELETE})
        } else {
          this.$set(this.formModel, item.prop, [])
          this.formModel[item.prop].push({...deletedRow, _mode: modes.DELETE})
        }
      }
    }
  }
}
