export default {
  name: 'SearchSlot',
  functional: true,
  inject: ['SearchRoot'],
  props: {
    queryParams: {},
    item: {},
    width: {}
  },
  render: (h, context) => {
    return h('div', context.injections.SearchRoot.$scopedSlots[context.props.item.key]({
      queryParams: context.props.queryParams,
      item: context.props.item,
      width: context.props.width
    }))
  }
}
