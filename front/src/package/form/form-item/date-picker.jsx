import _ from 'lodash'
import moment from 'moment'

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

    const {item: {prop, placeholder, readonly: _readonly}, type} = this
    if (this.readonly) {
      const val = this.formModel[prop]

      return <span>{val && moment(val).format('YYYY-MM-DD HH:mm:ss') || '-'}</span>
    } else return (
      <DatePicker v-model={this.formModel[prop]}
                  placeholder={placeholder}
                  disabled={this.disabled[prop] || _readonly}
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
