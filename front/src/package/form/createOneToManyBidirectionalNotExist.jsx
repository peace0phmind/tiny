import {getColumnsFromModel} from "../lib/tools";
import * as modes from "../_util/_mode";

export default {

  methods: {
    createOneToManyBidirectionalNotExist({item}) {
      let {meta: {_columns: columns, _enums: enums, _formItems: formItems, _formLayout: formLayout, model, restfulResourcePath}} = this.$pool[item.componentName]
      const {prop, resourcePath, label, primaryKey} = item

      if (!columns && !model) {
        throw new Error('columns or model not exist')
      } else {
        const actionSlot = {
          _action: ({row, column, $index}) => {
            return (
              <div>
                <el-link icon={'el-icon-edit'} underline={false} type={'primary'}
                         onClick={() => this.handleEditOneToManyNotExist(row, prop, formLayout)} {...{style: {marginRight: '10px'}}}></el-link>
                <el-link icon={'el-icon-delete'} underline={false} type={'danger'}
                         onClick={() => this.handleRemoveOneToManyNotExist(row, item)}></el-link>
              </div>
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

        const form = (
          <Modal title={`${label}`} v-model={this[`${prop}ModalShow`]} footer-hide={true}
                 width={this[`${prop}ModalWidth`]} fullscreen={this[`${prop}Fullscreen`]} mask-closable={false}>
            <zyx-form ref={`${prop}Form`} form-items={formItems} form-layout={formLayout}
                      resource-path={restfulResourcePath} enums={enums}
                      vOn:on-save={(e) => this.saveOneToManyNotExist(item)}
                      vOn:on-cancel={(e) => {
                        this[`${prop}ModalShow`] = false
                        this._watcher.update()
                      }}>
            </zyx-form>
          </Modal>
        )

        return (
          <div>
            <div {...{style: {marginBottom: '10px'}}}>
              <Button type="primary" disabled={this.readonly}
                      onClick={() => this.handleAddOneToManyNotExist(prop, formLayout)}
                      v-permission:create_resource={resourcePath}><i
                className={'el-icon-plus'}></i>新增
              </Button>
            </div>
            {table}
            {form}
          </div>
        )
      }
    },

    handleEditOneToManyNotExist(row, prop, formLayout) {
      this[`${prop}Mode`] = modes.UPDATE
      this[`${prop}ModalShow`] = true
      const maxSizeOfRow = Math.max(...(formLayout.map(row => row.length)))
      if (maxSizeOfRow) {
        this[`${prop}ModalWidth`] = maxSizeOfRow * 370
        if (this.modalWidth >= document.documentElement.clientWidth) this[`${prop}Fullscreen`] = true
      } else {
        this[`${prop}ModalWidth`] = 760
      }
      this.$refs[`${prop}Form`].init()
      Object.entries(row).forEach(([key, value]) => {
        this.$refs[`${prop}Form`].setValue(key, value)
      })
      const index = this[`${prop}Data`].findIndex(data => _.isEqual(row, data))
      this[`${prop}Index`] = index
      this._watcher.update()
    },

    handleAddOneToManyNotExist(prop, formLayout) {
      this[`${prop}Mode`] = modes.CREATE
      this[`${prop}ModalShow`] = true
      const maxSizeOfRow = Math.max(...(formLayout.map(row => row.length)))
      if (maxSizeOfRow) {
        this[`${prop}ModalWidth`] = maxSizeOfRow * 370
        if (this.modalWidth >= document.documentElement.clientWidth) this[`${prop}Fullscreen`] = true
      } else {
        this[`${prop}ModalWidth`] = 760
      }
      this.$refs[`${prop}Form`].init()
      this[`${prop}Index`] = undefined
      this._watcher.update()
    },

    saveOneToManyNotExist(item) {
      let data = this.$refs[`${item.prop}Form`].getFormDataIfValid()
      if (data) {
        this.formModel[item.prop] = this.formModel[item.prop] || []
        this[`${item.prop}Data`] = this[`${item.prop}Data`] || []
        data = {...data, _mode: this[`${item.prop}Mode`]}

        const index = this[`${item.prop}Index`]
        if (index) {
          this[`${item.prop}Data`].splice(index, 1, data)
          this.formModel[item.prop].splice(index, 1, data)
        } else {
          this[`${item.prop}Data`].push(data)
          this.formModel[item.prop].push(data)
        }

        const arr = JSON.parse(JSON.stringify(this.formModel[item.prop]))
        this.$delete(this.formModel, item.prop)
        this.$set(this.formModel, item.prop, arr)

        this[`${item.prop}ModalShow`] = false
        this._watcher.update()
      } else {
        this.$Message.error('出现错误')
      }
    },

    handleRemoveOneToManyNotExist(row, item) {
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
