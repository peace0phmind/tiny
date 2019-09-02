export default {
  name: 'FormSlot',
  functional: true,
  inject: ['Root'],
  props: {
    model: {},
    item: {}
  },
  render: (h, context) => {
    return h('div', context.injections.Root.$scopedSlots[context.props.item.prop]({
      model: context.props.model,
      item: context.props.item
    }))
  }
}
