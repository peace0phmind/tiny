<template>
  <div v-if="showH2DbConsole" class="full-screen-btn-con">
    <Tooltip content="h2数据库控制台" placement="bottom">
      <a target="_blank" :href="h2Console" style="color: #515a81;margin-right: 10px">
        <Icon type="ios-locate" :size="23"></Icon>
      </a>
    </Tooltip>
  </div>
</template>

<script>
  import {BASE_URL} from '../../../../config/proton'

  export default {
    name: 'Fullscreen',
    computed: {
      showH2DbConsole () {
        return process.env.NODE_ENV === 'development'
      },
      h2Console () {
        return `${BASE_URL}/h2-console`
      }
    },
  }
</script>

<style lang="less">
  .full-screen-btn-con .ivu-tooltip-rel {
    height: 64px;
    line-height: 56px;

    i {
      cursor: pointer;
    }
  }
</style>
