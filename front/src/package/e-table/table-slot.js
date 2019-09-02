export default {
    name: 'ETableSlot',
    functional: true,
    inject: ['eTableRoot'],
    props: {
        row: Object,
        index: Number,
        column: {
            type: Object,
            default: null
        }
    },
    render: (h, ctx) => {
        return h('div', ctx.injections.eTableRoot.$scopedSlots[ctx.props.column.slot]({
            row: ctx.props.row,
            column: ctx.props.column,
            index: ctx.props.index
        }));
    }
};
