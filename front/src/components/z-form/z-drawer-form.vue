<template>
  <div>
    <Drawer :title="title" v-model="show" :width="width" :mask-closable="false" :styles="styles" :mask="true"
            id="drawer">

      <Row type="flex" justify="center" :style="{height: formHeight, overflowY: 'scroll'}">
        <Col :span="20">
          <z-form ref="zForm" :enums="enums" :resource-path="resourcePath" :form-items="formItems"
                  :form-layout="formLayout"
                  @on-submit-success="success" :style="{width: `${formWidth}px`, margin: '0 auto'}"></z-form>
        </Col>
      </Row>

      <div class="drawer-footer" id="footer">
        <Button style="margin-right: 8px" @click="cancel">取消</Button>
        <Button type="primary" :loading="loading" @click="submit">
          <span v-if="!loading">提交</span>
          <span v-else>提交中...</span>
        </Button>
      </div>
    </Drawer>
  </div>
</template>

<script>
  export default {
    name: "z-drawer-form",
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
      formLayout: {},
      theme: {
        default() {
          return 'light' //light, dark
        }
      },
    },
    data() {
      return {
        width: 690,
        formWidth: 490,
        show: false,
        id: undefined,
        loading: false,
        styles: {
          height: 'calc(100% - 55px)',
          paddingBottom: '53px',
          position: 'static'
        },
        ghost: false,
        formHeight: '90%',
        drawerHeight: 0,
        restSubmit: true
      }
    },
    created() {

    },
    mounted() {

      let documentWidth = document.documentElement.clientWidth
      this.width = documentWidth * 0.5
      if (this.width < 790) this.width = 790
      this.formWidth = this.width * 0.8
      // this.elasticHeight()
      if (this.theme === 'dark') {
        this.$set(this.styles, 'background-color', '#2b2c2b')
        document.getElementsByClassName('ivu-drawer-header-inner')[0].style = 'color: #6b6767;'
        document.getElementsByClassName('ivu-drawer-header')[0].style = 'background-color: #484747;border-bottom: 1px solid #121213;color: white;'
        document.getElementsByClassName('drawer-footer')[0].style = 'background-color: #484747;border-top: 1px solid #121213;'
        Array.prototype.forEach.call(document.getElementsByClassName('ivu-select-selection'), function (element) {
          element.style = 'background-color: transparent;'
        })
        Array.prototype.forEach.call(document.getElementsByClassName('ivu-input'), function (element) {
          element.style = 'background-color: transparent;color: #c5d4ff;'
        })
        Array.prototype.forEach.call(document.getElementsByClassName('ivu-select-dropdown'), function (element) {
          element.style = 'background-color: transparent;border: 1px solid;display: none;'
        })
        this.ghost = true
      }
    },
    methods: {
      open(options) {
        let {id, restSubmit} = options
        this.restSubmit = restSubmit
        this.show = true
        this.loading = false
        this.$refs.zForm.init(id)
        this.$refs.zForm.resetField()
        // this.elasticHeight()
      },
      set(key, value) {
        this.$refs.zForm.set(key, value)
      },
      elasticHeight() {
        setTimeout(() => {
          let drawerHeight = this.drawerHeight || document.getElementsByClassName('ivu-drawer-right')[0].clientHeight
          let footerHeight = document.getElementById('footer').clientHeight
          let height = drawerHeight - footerHeight - 60
          this.formHeight = `${height}px`
          console.log(drawerHeight, footerHeight, this.formHeight)
        }, 100)
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
            }
          })
        } else {
          this.show = false
        }
      }
    }
  }
</script>

<style scoped>
  .drawer-footer {
    width: 100%;
    position: absolute;
    bottom: 0;
    left: 0;
    border-top: 1px solid #e8e8e8;
    padding: 10px 16px;
    text-align: right;
    background: #fff;
  }
  .footer {
    width: 100%;
    border-top: 1px solid #e8e8e8;
    padding: 10px 16px;
    text-align: right;
    background: #fff;
  }

</style>
