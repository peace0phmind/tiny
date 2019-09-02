<style lang="less">
  @import './login.less';
</style>

<template>
  <div class="login">
    <div class="login-con">
      <Card icon="log-in" title="欢迎登录" :bordered="false">
        <div class="form-con">
          <login-form @on-success-valid="handleSubmit"></login-form>
          <p class="login-tip">Copyright@南京知亦行信息技术有限公司</p>
        </div>
      </Card>
    </div>
  </div>
</template>

<script>
  import LoginForm from '_c/login-form'
  import {mapActions} from 'vuex'

  export default {
    components: {
      LoginForm
    },
    methods: {
      ...mapActions([
        'getUserInfo',
        'handleLogin',
      ]),
      handleSubmit({username, password}) {
        this.handleLogin({username, password}).then(res => {
          localStorage.setItem('id_token', res)
          this.getUserInfo().then(() => {
            this.$router.push({
              name: this.$config.homeName
            })
          })
        })
      }
    }
  }
</script>

<style>

</style>
