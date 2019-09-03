import _ from 'lodash'
import * as Types from "../../_util/javaTypes";

export default {
  name: 'zyx-radio',

  inject: ['zyxForm'],

  props: {
    formModel: {},
    disabled: {},
    readonly: {},
    item: {},
    enums: {}
  },

  computed: {
    upperFirstItemProp() {
      return _.upperFirst(this.item.prop)
    },
    isEnum() {
      return Types.isEnum(this.item.type, this.item.modelType)
    }
  },

  render() {

    const {item: {prop, enumName, readonly: _readonly}, enums} = this

    if (this.isEnum) {
      if (this.readonly) {
        return <span>{enums[enumName][this.formModel[prop]].name || '-'}</span>
      } else return (
        <RadioGroup v-model={this.formModel[prop]} disabled={this.disabled[prop] || _readonly} vOn:on-change={(val) => this.onChange}>
          {
            Object.keys(enums[enumName]).map(k => {
              return (
                <Radio label={k}>{enums[enumName][k].name}</Radio>
              )
            })
          }
        </RadioGroup>
      )
    }
  },

  methods: {
    onChange(val) {
      const call = this.zyxForm[`on${this.upperFirstItemProp}Change`]
      call && call({val})
    }
  }
}
