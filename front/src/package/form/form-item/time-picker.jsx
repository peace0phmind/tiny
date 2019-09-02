import _ from 'lodash'

export default {
  name: 'zyx-time-picker',

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

    const {item: {prop, placeholder}} = this
    if (this.readonly) {
      return <span>{this.formModel[prop] || '-'}</span>
    } else return (
      <TimePicker v-model={this.formModel[prop]}
                  placeholder={placeholder}
                  disabled={this.disabled[prop]}
                  clearable
                  transfer
                  type={'time'}
                  {...{style: {width: '100%'}}}
                  vOn:on-change={(val) => this.onChange(val)}
      ></TimePicker>
    )
  },

  methods: {
    onChange(val) {
      const call = this.zyxForm[`on${this.upperFirstItemProp}Change`]
      call && call({val})
    }
  }
}
