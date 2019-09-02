import _ from 'lodash'

export default {
  name: 'zyx-input',

  inject: ['zyxForm'],

  props: {
    formModel: {},
    disabled: {},
    readonly: {},
    view: {},
    type: {},
    item: {}
  },

  computed: {
    upperFirstItemProp() {
      return _.upperFirst(this.item.prop)
    }
  },

  render() {
    const {placeholder, prefixIcon, suffixIcon, rows, prop} = this.item

    const attrs = {}
    prefixIcon && (attrs.prefixIcon = prefixIcon)
    suffixIcon && (attrs.suffixIcon = suffixIcon)
    rows && (attrs.rows = rows)
    if (this.readonly) {
      return <span>{this.formModel[prop] || '-'}</span>
    } else return (
      <Input type={this.type}
             v-model={this.formModel[prop]}
             placeholder={placeholder}
             disabled={this.disabled[prop]}
             attrs={attrs}
             clearable
             vOn:on-enter={(event) => this.onEnter()}
             vOn:on-change={(event) => this.onChange(event)}
             vOn:on-focus={(event) => this.onFocus()}
             vOn:on-blur={(event) => this.onBlur()}
             vOn:on-keyup={(event) => this.onKeyup(event)}
             vOn:on-keydown={(event) => this.onKeydown(event)}
             vOn:on-keypress={(event) => this.onKeypress(event)}
             vOn:on-clear={(event) => this.onClear()}
      ></Input>
    )
  },
  methods: {
    onEnter() {
      const call = this.zyxForm[`on${this.upperFirstItemProp}Enter`]
      call && call()
    },
    onChange(event) {
      const call = this.zyxForm[`on${this.upperFirstItemProp}Change`]
      call && call({val: event.target.value})
    },
    onFocus() {
      const call = this.zyxForm[`on${this.upperFirstItemProp}Focus`]
      call && call()
    },
    onBlur() {
      const call = this.zyxForm[`on${this.upperFirstItemProp}Blur`]
      call && call()
    },
    onKeyup(event) {
      const call = this.zyxForm[`on${this.upperFirstItemProp}Keyup`]
      call && call({val: event.target.value})
    },
    onKeydown(event) {
      const call = this.zyxForm[`on${this.upperFirstItemProp}Keydown`]
      call && call({val: event.target.value})
    },
    onKeypress(event) {
      const call = this.zyxForm[`on${this.upperFirstItemProp}Keypress`]
      call && call({val: event.target.value})
    },
    onClear() {
      const call = this.zyxForm[`on${this.upperFirstItemProp}Clear`]
      call && call()
    },
  }
}
