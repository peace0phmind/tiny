import zyxFormItem from './form-item.jsx'
import _ from 'lodash'

export default {

  data() {
    return {
      formItemRefSuffix: '_form_item',
      treeModelPool: {}
    }
  },

  components: {zyxFormItem},

  methods: {
    findFormItemByProp(prop) {
      return this.normalFormItems.find(item => item.prop === prop)
    },

    createItem(formItem) {
      const ref = `${formItem.prop}${this.formItemRefSuffix}`
      return (
        <zyx-form-item ref={ref} item={formItem}
                       form-model={this.formModel}
                       disabled={this.disabled}
                       hidden={this.hidden}
                       readonly={this.readonly}
                       enums={this.enums}
                       tree-model-pool={this.treeModelPool}
                       form-props={this.formProps}
        ></zyx-form-item>
      )
    },

    createNormalFormItemNode() {
      return this.formLayout.map(row => {
        const span = 24 / row.length

        const currentRow = {}
        for (let item of row) {
          if (item.prop) currentRow[item.prop] = (currentRow[item.prop] || 0) + 1
        }

        return (
          <Row gutter={32}>
            {
              Object.entries(currentRow).map(([prop, count]) => {
                const formItemNode = this.findFormItemByProp(prop)
                if (formItemNode) {
                  const formItem = this.createItem(formItemNode)
                  return (
                    <Col span={span * count}>
                      {formItem}
                    </Col>
                  )
                }
              })
            }
          </Row>
        )
      })
    }
  }
}
