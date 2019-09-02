<template>
  <div v-if="show" style="position: absolute;top: 40%;right: 0px;z-index: 100;">
    <Tooltip content="全局设置" placement="bottom">
      <div class="setting">
        <a href="javascript:void(0)" :style="{color: '#FFFFFF'}" @click="settingsModalShow = true">
          <Icon type="ios-settings-outline" :size="30"></Icon>
        </a>
      </div>
    </Tooltip>

    <Modal v-model="settingsModalShow" width="800" ok-text="确定" cancel-text="取消" @on-ok="saveSettings">
      <p slot="header" :style="{color: '#68a5ff', textAlign: 'center'}">
        <Icon type="ios-information-circle"></Icon>
        <span>全局设置</span>
      </p>
      <div>
        <Form :model="settings" label-position="right" :label-width="125">
          <FormItem label="大标题">
            <Input id="maxTitle" type="textarea" v-model="settings.maxTitle" @on-change="changeMaxTitle"></Input>
          </FormItem>
          <FormItem label="小标题">
            <Input id="minTitle" type="textarea" v-model="settings.minTitle" @on-change="changeMinTitle"></Input>
          </FormItem>
          <Row :gutter="10">
            <Col :span="12">
              <FormItem label="启用 TAG">
                <i-switch v-model="settings.tagNavEnable" true-value="启用" false-value="不启用"></i-switch>
              </FormItem>
            </Col>
            <Col :span="12">
              <FormItem label="左侧栏背景色">
                <ColorPicker v-model="settings.layoutSiderBackgroundColor" recommend alpha/>
              </FormItem>
            </Col>
          </Row>
          <Row :gutter="10">
            <Col span="12">
              <FormItem label="主要配色">
                <ColorPicker v-model="settings.primaryColor" recommend alpha/>
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem label="链接色">
                <ColorPicker v-model="settings.linkColor" recommend alpha/>
              </FormItem>
            </Col>
          </Row>
          <Row :gutter="10">
            <Col span="12">
              <FormItem label="树节点高亮背景色">
                <ColorPicker v-model="settings.treeHighlightBackgroundColor" recommend alpha/>
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem label="树节点高亮文字色">
                <ColorPicker v-model="settings.treeHighlightTextColor" recommend alpha/>
              </FormItem>
            </Col>
          </Row>
          <Row :gutter="10">
            <Col span="8">
              <FormItem label="菜单背景色">
                <ColorPicker v-model="settings.menuBackgroundColor" recommend alpha/>
              </FormItem>
            </Col>
            <Col span="8">
              <FormItem label="激活菜单的背景色">
                <ColorPicker v-model="settings.activeMenuBackgroundColor" recommend alpha/>
              </FormItem>
            </Col>
            <Col span="8">
              <FormItem label="菜单文字色">
                <ColorPicker v-model="settings.menuTextColor" recommend alpha/>
              </FormItem>
            </Col>
          </Row>
        </Form>
      </div>
    </Modal>
  </div>
</template>

<script>
    import Vue from 'vue'
    import Rest from '../../../../package/lib/Rest.js'

    export default {
        name: 'Settings',
        inject: ['main'],
        computed: {
            show() {
                return process.env.NODE_ENV === 'development'
            },
        },
        data() {
            return {
                settingsModalShow: false,
                settings: {
                    layoutSiderBackgroundColor: '',
                    primaryColor: '',
                    linkColor: '',
                    treeHighlightBackgroundColor: '',
                    treeHighlightTextColor: '',
                    menuBackgroundColor: '',
                    activeMenuBackgroundColor: '',
                    menuTextColor: '',
                }
            }
        },
        created() {

            const _rest = new Rest('dev', this.$apiURL)

            _rest.GET({
                uri: 'parse/css', params: {
                    fileRelativePath: 'config/variables.less'
                }
            }).then(res => {
                this.settings.layoutSiderBackgroundColor = res['@layout-sider-background']
                this.settings.primaryColor = res['@primary-color']
                this.settings.linkColor = res['@link-color']
                this.settings.treeHighlightBackgroundColor = res['@tree-highlight-background-color']
                this.settings.treeHighlightTextColor = res['@tree-highlight-text-color']
                this.settings.menuBackgroundColor = res['@menu-dark-title']
                this.settings.activeMenuBackgroundColor = res['@menu-dark-active-bg']
                this.settings.menuTextColor = res['@menu-dark-subsidiary-color']
                this._watcher.update()
            })

            this.settings.maxTitle = this.$maxTitle
            this.settings.minTitle = this.$minTitle
            this.settings.tagNavEnable = this.$tagNavEnable
        },
        mounted() {
            const arr = Array.from(document.getElementsByClassName('ivu-input'))
            arr.filter(el => ['maxTitle', 'minTitle'].includes(el.parentElement.id)).forEach(el => {
                el.style.color = '#3f9eff'
                el.style.backgroundColor = '#22262b'
            })
        },
        methods: {
            saveSettings() {
                const _rest = new Rest('dev', this.$apiURL)
                const data = {}
                data[`config/maxTitle.js`] = `export default ${this.settings.maxTitle}`
                data[`config/minTitle.js`] = `export default ${this.settings.minTitle}`
                data[`config/tagNavEnable.js`] = `export default ${this.settings.tagNavEnable}`
                data[`config/variables.less`] = `@primary-color: ${this.settings.primaryColor};
@link-color: ${this.settings.linkColor};
@tree-highlight-background-color: ${this.settings.treeHighlightBackgroundColor};
@tree-highlight-text-color: ${this.settings.treeHighlightTextColor};
@menu-dark-title: ${this.settings.menuBackgroundColor};
@menu-dark-active-bg: ${this.settings.activeMenuBackgroundColor};
@menu-dark-subsidiary-color: ${this.settings.menuTextColor};
@layout-sider-background: ${this.settings.layoutSiderBackgroundColor};`
                data[`config/element-variables.scss`] = `$--color-primary: ${this.settings.primaryColor};
$--font-path: '~element-ui/lib/theme-chalk/fonts';
@import "../../node_modules/element-ui/packages/theme-chalk/src/index";`
                _rest.POST({
                    uri: 'write/file',
                    data
                }).then(res => {
                    this.$Message.success('设置成功!正在编译')
                }, error => this.$Message.error('设置失败!'))
            },
            changeMaxTitle(event) {
                Vue.prototype.$maxTitle = event.target.value
                this.main._watcher.update()
            },
            changeMinTitle(minTitle) {
                Vue.prototype.$minTitle = event.target.value
                this.main._watcher.update()
            }
        }
    }
</script>

<style lang="less" scoped>
  @import "../../../../config/variables.less";

  .setting {
    background: @primary-color;
    width: 40px;
    height: 40px;
    padding: 5px 6px;
    box-shadow: rgba(0, 0, 0, 0.2) 0px 12px 12px 0px;
    border-radius: 4px 0px 0px 4px;
  }
</style>
