<template>
  <Layout>
    <circle-menu type="right" :number="3" circle class="func-menu">
      <Icon type="md-settings" style="font-size: 26px;color: white;" slot="item_btn"/>
      <Tooltip content="设置搜索条件" slot="item_1">
        <Icon type="md-search" style="font-size: 26px;color: white;"/>
      </Tooltip>
      <Tooltip content="设置表格" slot="item_2">
        <Icon type="ios-grid" style="font-size: 26px;color: white;"/>
      </Tooltip>
      <Tooltip content="提交建议" slot="item_3">
        <Icon type="ios-chatboxes" style="font-size: 26px;color: white;" @click="openAdvise"/>
      </Tooltip>
    </circle-menu>

    <Modal v-model="adviseShow" width="360">
      <p slot="header" style="color:#f60;text-align:center">
        <Icon type="ios-information-circle"></Icon>
        <span>提交建议</span>
      </p>
      <div style="text-align:center">
        <Input v-model="advise" type="textarea" :rows="4" placeholder="请填写关于当前界面的建议"/>
      </div>
      <div slot="footer">
        <Button type="error" size="large" long :loading="modal_loading" @click="submit">确定</Button>
      </div>
    </Modal>
  </Layout>
</template>

<script>
  export default {
    name: "func-menu",
    data() {
      return {
        adviseShow: false,
        modal_loading: false,
        advise: ''
      }
    },
    methods: {
      openAdvise() {
        this.adviseShow = true
      },
      submit() {
        this.adviseShow = false
        console.log('advise: ', this.advise, this.$route, this.$router)
      }
    }
  }
</script>

<style scoped>

</style>
