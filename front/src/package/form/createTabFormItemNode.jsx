import * as ReferenceModes from '../_util/referenceMode'
import createOneToMany from './createOneToMany.jsx'
import createManyToMany from './createManyToMany.jsx'

export default {

  mixins: [createOneToMany, createManyToMany],

  methods: {
    createTabContent(item) {

      const {prop, bidirectional, referenceMode, label, src} = item

      if (this.$scopedSlots[prop]) return this.$scopedSlots[prop]({label, bidirectional, referenceMode, src})

      else {
        if (referenceMode === ReferenceModes.oneToMany) {

          if (bidirectional) {
            return this.createOneToManyBidirectional({item})
          } else {
            return this.createOneToManyNotBidirectional({item})
          }

        } else if (referenceMode === ReferenceModes.manyToMany) {
          return this.createManyToMany({item})
        }
      }
    },

    clickTab() {
      if (this.$refs[this.activeTab]) {
        const formItem = this.tabFormItems.find(item => item.prop === this.activeTab)
        const {referenceMode, bidirectional} = formItem
        if (referenceMode === ReferenceModes.manyToMany) {
          this.loadAllToKnowFrom(formItem)
        } else {
          if (bidirectional !== false) {
            this.loadAllToKnowFrom(formItem)
          }
        }
      }
    },

    createTabFormItems() {
      const vm = this
      return (
        <el-tabs v-model={this.activeTab} vOn:tab-click={this.clickTab} type={undefined}>

          {this.$scopedSlots.frontTabs && this.$scopedSlots.frontTabs({formModel})}

          {
            this.tabFormItems.filter(item => !item.notShow).map(item => {
              const {prop, bidirectional, referenceMode, label} = item

              const {meta: {_columns: columns, _enums: enums}} = vm.$pool[item.componentName]

              return (
                <el-tab-pane label={label} name={prop}>
                  {this.createTabContent(item)}
                </el-tab-pane>
              )
            })
          }

          {this.$scopedSlots.behindTabs && this.$scopedSlots.behindTabs({formModel})}

        </el-tabs>
      )
    },
  }
}
