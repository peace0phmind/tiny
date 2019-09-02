<template>
  <Row type="flex" justify="center">
    <Col>
      <z-form ref="form" :enums="enums" :resource-path="resourcePath" :form-items="formItems" :id="id"
              @on-submit-success="success"></z-form>

      <i-divider orientation="right">操作</i-divider>
      <div style="float: right">
        <Button style="margin-right: 8px" @click="show = false">取消</Button>
        <Button type="primary" :loading="loading" @click="submit">
          <span v-if="!loading">提交</span>
          <span v-else>提交中...</span>
        </Button>
      </div>
    </Col>
  </Row>
</template>

<script>
  import ZForm from './z-form'

  export default {
    name: "z-breadcrumb-form",
    components: {ZForm},
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
      routePath: {
        required: true
      }
    },
    data() {
      return {
        id: undefined,
        loading: false,
        styles: {
          height: 'calc(100% - 55px)',
          overflow: 'auto',
          paddingBottom: '53px',
          position: 'static'
        },
      }
    },
    created() {
      this.id = this.$route.params && this.$route.params.id
    },
    methods: {
      submit() {
        this.loading = true
        this.$refs.form.submit()
      },
      success() {
        this.loading = false
        this.$Message.success('操作成功')
        this.$router.push(this.routePath)
      }
    }
  }
</script>

<style scoped>

</style>
