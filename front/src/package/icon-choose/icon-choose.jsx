import iconPane from './icon-pane'

export default {
  name: 'icon-choose',

  model: {
    prop: 'val',
    event: 'change'
  },

  props: {
    val: {
      type: String
    },
    disabled: {
      type: Boolean
    }
  },

  data() {
    return {
      value: this.val,
      selectIconShow: false
    }
  },

  comments: {
    iconPane
  },

  methods: {
    chooseIcon() {
      this.selectIconShow = true
    },
    selectIcon(icon) {
      this.value = icon
      this.selectIconShow = false
      this.$emit('change', icon)
      this.$emit('on-change', icon)
    }
  },

  render() {

    return (
      <div>
        <Input value={this.val} disabled={this.disabled} placeholder={'点击选择图标'} vOn:on-focus={this.chooseIcon} icon={this.val}></Input>
        <Modal footer-hide={true} title={'选择图标'} v-model={this.selectIconShow} width={800} {...{style: {top: '30px'}}} >
          <icon-pane vOn:on-select={this.selectIcon}></icon-pane>
        </Modal>
      </div>
    )
  }
}
