import _ from "lodash";

export default {
  name: 'zyx-input-number',

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

    const {item: {prop, max, min, precision, placeholder, readonly: _readonly}} = this
    if (this.readonly) {
      return <span>{this.formModel[prop] || '-'}</span>
    } else return (
      <InputNumber max={max}
                   min={min}
                   step={precision && 0.1 || 1}
                   precision={precision}
                   v-model={this.formModel[prop]}
                   disabled={this.disabled[prop] || _readonly}
                   placeholder={placeholder}
                   vOn:on-change={(val) => this.onChange(val)}
                   vOn:on-focus={(event) => this.onFocus()}
                   vOn:on-blur={(event) => this.onBlur()}
                   {...{style: {width: '100%'}}}
      ></InputNumber>
    )
  },

  methods: {
    onChange(val) {
      const call = this.zyxForm[`on${this.upperFirstItemProp}Change`]
      call && call({val})
    },
    onFocus() {
      const call = this.zyxForm[`on${this.upperFirstItemProp}Focus`]
      call && call()
    },
    onBlur() {
      const call = this.zyxForm[`on${this.upperFirstItemProp}Blur`]
      call && call()
    }
  }
}
