import _ from "lodash";

export default {
  name: 'zyx-slider',

  inject: ['zyxForm'],

  props: {
    formModel: {},
    disabled: {},
    view: {},
    readonly: {},
    item: {}
  },

  computed: {
    upperFirstItemProp() {
      return _.upperFirst(this.item.prop)
    }
  },

  render() {

    let {item: {prop, max, min, precision, showInput, readonly: _readonly}} = this

    if (showInput === undefined) showInput = true
    if (this.readonly) {
      return <span>{this.formModel[prop] || '-'}</span>
    } else return (
      <Slider v-model={this.formModel[prop]}
              max={max}
              min={min}
              step={precision && 0.1 || 1}
              disabled={this.disabled[prop] || _readonly}
              show-input={showInput}
              vOn:on-change={(val) => this.onChange(val)}
              vOn:on-input={(val) => this.onChanging(val)}
      ></Slider>
    )
  },

  methods: {
    onChange(val) {
      const call = this.zyxForm[`on${this.upperFirstItemProp}Change`]
      call && call({val})
    },
    onChanging() {
      const call = this.zyxForm[`on${this.upperFirstItemProp}Changing`]
      call && call()
    }
  }
}
