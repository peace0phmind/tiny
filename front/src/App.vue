<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
  export default {
    name: 'App',
    provide() {
      return {
        reload: this.reload
      }
    },
    data() {
      return {
        isRouterAlive: true
      }
    },
    created() {
      this.$store.dispatch('initMenus').then(res => {})
    },
    methods: {
      reload() {
        this.isRouterAlive = false
        this.$nextTick(function () {
          this.isRouterAlive = true
        })
      }
    }
  }
</script>

<style lang="less">
  .size {
    width: 100%;
    height: 100%;
  }

  html, body {
    .size;
    overflow: hidden;
    margin: 0;
    padding: 0;
  }

  #app {
    .size;
  }

</style>
