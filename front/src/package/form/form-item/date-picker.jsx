import _ from 'lodash'

export default {
  name: 'zyx-date-picker',

  inject: ['zyxForm'],

  props: {
    formModel: {},
    disabled: {},
    readonly: {},
    view: {},
    item: {},
    type: {}
  },

  computed: {
    upperFirstItemProp() {
      return _.upperFirst(this.item.prop)
    }
  },

  render() {

    const {item: {prop, placeholder}, type} = this
    if (this.readonly) {
      return <span>{this.formModel[prop] || '-'}</span>
    } else return (
      <DatePicker v-model={this.formModel[prop]}
                  placeholder={placeholder}
                  disabled={this.disabled[prop]}
                  clearable
                  transfer
                  type={type}
                  {...{style: {width: '100%'}}}
                  vOn:on-change={(val, type) => this.onChange(val, type)}
      ></DatePicker>
    )
  },

  methods: {
    onChange(val, type) {
      const call = this.zyxForm[`on${this.upperFirstItemProp}Change`]
      call && call({val, type})
    }
  }
}
