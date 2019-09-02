import {decorate} from './interface.js'
import STableSlot from './table-slot'

export default {

  name: 's-table',
  props: {
    columns: Array,

    data: Array,

    enums: {},
  },

  components: {STableSlot},

  data() {
    return {
      columnArray: this.columns,
    }
  },

  provide() {
    return {
      sTableRoot: this
    }
  },

  created() {
    const columns = []

    this.columnArray.forEach(column => {
      if (!column.children) columns.push(column)
      else {
        const children = column.children
        if (children.length > 0) {
          const col = {
            title: child.title,
            slot: child.slot,
            tooltip: column.tooltip,
            align: column.align,
            type: column.type,
            minWidth: 100
          }
          if (child.modelType) col.modelType = child.modelType
          if (child.dateType) col.dateType = child.dateType
          columns.push(col)
        }
      }
    })

    this.columnArray = columns

    this.columnArray = this.columnArray.filter(column => !column.hide)
  },

  methods: {
    getSelection() {
      return this.$refs.table.getSelection()
    },
    onSelectionChange(selection) {
      this.$emit('on-selection-change', selection)
    },
    // render column
    decorator({column, value}) {
      const {decorator} = column
      if (decorator) {
        const {_avatar, _switch} = decorator
        if (_avatar) {
          return (<Avatar src={value}></Avatar>)
        } else if (_switch) {
          return (<i-switch value={value} disabled></i-switch>)
        }
      } else {
        return (
          <Tooltip content={value} placement={'top'}>
            <span domPropsInnerHTML={value}></span>
          </Tooltip>
        )
      }
    },
  },
  render() {
    const scopedSlots = {}
    const _enums = this.enums

    this.columnArray.map((column) => {
      const {slot, toolTip} = column
      if (slot) {
        if (slot !== '_action') {
          scopedSlots[slot] = ({row, index}) => {
            if (column.customRender) {
              return (<s-table-slot row={row} index={index} column={column}></s-table-slot>)
            } else {
              const value = decorate({row, column, enums: _enums})
              return this.decorator({column, value, toolTip})
            }
          }
        } else {
          scopedSlots[slot] = ({row, index}) => {
            return (<s-table-slot row={row} index={index} column={column}></s-table-slot>)
          }
        }
      }
    })

    return (
      <Table ref={'table'} border columns={this.columnArray} data={this.data}
             scopedSlots={scopedSlots} vOn:on-selection-change={this.onSelectionChange}>
      </Table>
    )
  }
}
