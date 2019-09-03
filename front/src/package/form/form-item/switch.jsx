import _ from "lodash";

export default {
  name: 'zyx-switch',

  inject: ['zyxForm'],

  props: {
    formModel: {},
    disabled: {},
    readonly: {},
    view: {},
    item: {}
  },

  computed: {
    upperFirstItemProp() {
      return _.upperFirst(this.item.prop)
    }
  },

  render() {

    const {item: {prop, trueValue, falseValue, readonly: _readonly}} = this
    if (this.readonly) {
      return <span>{this.formModel[prop] && '是' || '否'}</span>
    } else {
      const switchSlot = {
        open: () => (<span>{trueValue}</span>),
        close: () => (<span>{falseValue}</span>)
      }

      if (trueValue && falseValue) {
        return (
          <i-switch v-model={this.formModel[prop]}
                    disabled={this.disabled[prop] || _readonly}
                    vOn:on-change={(val) => this.onChange(val)}
                    scopedSlots={switchSlot} size={'large'}
          >
          </i-switch>
        )
      } else {
        return (
          <i-switch v-model={this.formModel[prop]}
                    disabled={this.disabled[prop]}
                    vOn:on-change={(val) => this.onChange(val)}
          >
          </i-switch>
        )
      }
    }
  },

  methods: {
    onChange(val) {
      const call = this.zyxForm[`on${this.upperFirstItemProp}Change`]
      call && call({val})
    }
  }
}
