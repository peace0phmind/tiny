<template>
  <div>
    <Modal :title="title" v-model="show" :width="width" :mask-closable="false" ok-text="提交" :loading="loading"
           @on-ok="submit" @on-cancel="cancel">

      <z-form ref="zForm" :enums="enums" :resource-path="resourcePath" :form-items="formItems" :form-layout="formLayout"
              :id="id"
              @on-submit-success="success"></z-form>

    </Modal>
  </div>
</template>

<script>
  export default {
    name: "z-modal-form",
    props: {
      title: {
        default() {
          return '编辑'
        }
      },
      enums: {
        default() {
          return {}
        }
      },
      resourcePath: {
        required: true
      },
      formItems: {
        default() {
          return {}
        }
      },
      formLayout: {}
    },
    data() {
      return {
        width: 720,
        show: false,
        id: undefined,
        loading: false,
        styles: {
          height: 'calc(100% - 55px)',
          overflow: 'auto',
          paddingBottom: '53px',
          position: 'static'
        },
        restSubmit: true
      }
    },
    mounted() {
      let documentWidth = document.documentElement.clientWidth
      this.width = documentWidth * 0.5
      if (this.width < 790) this.width = 790
    },
    methods: {
      open(options) {
        let {id, restSubmit} = options
        this.restSubmit = restSubmit
        this.show = true
        this.loading = false
        this.$refs.zForm.init(id)
        this.$refs.zForm.resetField()
      },
      submit() {
        this.loading = true
        this.$refs.zForm.submit({rest: this.restSubmit})
      },
      success(res) {
        this.loading = false
        this.$Message.success('操作成功')
        this.show = false
        this.$emit('on-form-success', res)
      },
      cancel() {
        if (this.$refs.zForm.hasChanged()) {
          this.$Modal.confirm({
            title: '警告',
            content: `确定取消编辑吗？`,
            onOk: () => {
              this.show = false
            },
            onCancel: () => {
              this.show = true
            }
          })
        }
      }
    }
  }
</script>

<style scoped>

</style>
