export default {
  name: 'FormSlot',
  functional: true,
  inject: ['FormRoot'],
  props: {
    formModel: {},
    item: {},
    disabled: {}
  },
  render: (h, context) => {
    return h('div', context.injections.FormRoot.$scopedSlots[context.props.item.prop]({
      formModel: context.props.formModel,
      item: context.props.item,
      disabled: context.props.disabled
    }))
  }
}
