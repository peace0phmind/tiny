<template>
  <Layout style="height: 100%" class="main">
    <Sider hide-trigger collapsible :width="220" :collapsed-width="64" v-model="collapsed" class="left-sider"
           :style="{overflow: 'hidden'}">
      <side-menu accordion ref="sideMenu" :active-name="$route.name" :collapsed="collapsed" @on-select="turnToPage"
                 :menu-list="menuList">
        <!-- 需要放在菜单上面的内容，如Logo，写在side-menu标签内部，如下 -->
        <div class="logo-con">
          <div v-show="!collapsed" key="max-logo" v-html="$maxTitle"></div>
          <div v-show="collapsed" key="min-logo" v-html="$minTitle"></div>
        </div>
      </side-menu>
    </Sider>
    <Layout>
      <Header class="header-con">
        <header-bar :collapsed="collapsed" @on-coll-change="handleCollapsedChange">
          <user :message-unread-count="unreadCount" :user-avatar="userAvatar" :user-name="userName"/>
          <fullscreen v-model="isFullscreen" style="margin-right: 10px;"/>
          <h2-db></h2-db>
        </header-bar>
      </Header>
      <Content class="main-content-con">
        <Layout class="main-layout-con">
          <div class="tag-nav-wrapper" v-if="$tagNavEnable">
            <tags-nav :value="$route" @input="handleClick" :list="tagNavList" @on-close="handleCloseTag"/>
          </div>
          <Content class="content-wrapper" id="mainContent">
            <router-view/>
            <ABackTop :height="100" :bottom="80" :right="50" container=".content-wrapper"></ABackTop>
          </Content>
        </Layout>
      </Content>
      <settings></settings>
    </Layout>
  </Layout>
</template>
<script>
    import SideMenu from './components/side-menu'
    import HeaderBar from './components/header-bar'
    import TagsNav from './components/tags-nav'
    import User from './components/user'
    import ABackTop from './components/a-back-top'
    import Fullscreen from './components/fullscreen'
    import H2Db from './components/h2-db'
    import Language from './components/language'
    import ErrorStore from './components/error-store'
    import {mapMutations, mapActions, mapGetters} from 'vuex'
    import {getNewTagList, routeEqual} from '@/libs/util'
    import routers from '@/router/routers'
    // import minLogo from '@/assets/images/logo-min.jpg'
    import minLogo from '@/assets/images/logo.png'
    import maxLogo from '@/assets/images/logo.jpg'
    import store from '@/store'
    import './main.less'
    import Settings from './components/settings/settings'

    export default {
        name: 'Main',
        components: {
            Settings,
            SideMenu,
            HeaderBar,
            Language,
            TagsNav,
            Fullscreen,
            H2Db,
            ErrorStore,
            User,
            ABackTop
        },
        data() {
            return {
                collapsed: false,
                minLogo,
                maxLogo,
                isFullscreen: false,
                visible: {}
            }
        },
        provide() {
            return {
                main: this
            }
        },
        computed: {
            ...mapGetters([
                'errorCount'
            ]),
            tagNavList() {
                return this.$store.state.app.tagNavList
            },
            tagRouter() {
                return this.$store.state.app.tagRouter
            },
            userAvatar() {
                return this.$store.state.user.avatarImgPath
            },
            userName() {
                return this.$store.state.user.userName
            },
            cacheList() {
                const list = ['ParentView', ...this.tagNavList.length ? this.tagNavList.filter(item => !(item.meta && item.meta.notCache)).map(item => item.name) : []]
                return list
            },
            menuList() {
                return this.$store.getters.menuList
            },
            local() {
                return this.$store.state.app.local
            },
            hasReadErrorPage() {
                return this.$store.state.app.hasReadErrorPage
            },
            unreadCount() {
                return this.$store.state.user.unreadCount
            }
        },
        methods: {
            ...mapMutations([
                'setBreadCrumb',
                'setTagNavList',
                'addTag',
                'setLocal',
                'setHomeRoute',
                'closeTag'
            ]),
            ...mapActions([
                'handleLogin',
                'getUnreadMessageCount'
            ]),

            turnToPage(route) {
                let {name, params, query} = {}
                if (typeof route === 'string') {
                    name = route
                } else {
                    name = route.name
                    params = route.params
                    query = route.query
                }
                if (name.indexOf('isTurnByHref_') > -1) {
                    window.open(name.split('_')[1])
                    return
                }
                console.log(this)
                this.$router.push({
                    name,
                    params,
                    query
                })
            },
            handleCollapsedChange(state) {
                this.collapsed = state
            },
            handleCloseTag(res, type, route) {
                if (type !== 'others') {
                    if (type === 'all') {
                        this.turnToPage(this.$config.homeName)
                    } else {
                        if (routeEqual(this.$route, route)) {
                            this.closeTag(route)
                        }
                    }
                }
                this.setTagNavList(res)
            },
            handleClick(item) {
                this.turnToPage(item)
            }
        },
        watch: {
            '$route'(newRoute) {
                const {name, query, params, meta} = newRoute
                this.addTag({
                    route: {name, query, params, meta},
                    type: 'push'
                })
                this.setBreadCrumb(newRoute)
                this.setTagNavList(getNewTagList(this.tagNavList, newRoute))
                this.$refs.sideMenu.updateOpenName(newRoute.name)
            }
        },
        created() {
            store.dispatch('initMenus').then(() => {
            })
        },
        mounted() {
            /**
             * @description 初始化设置面包屑导航和标签导航
             */
            this.setTagNavList()
            const allRoutes = []
            allRoutes.push(routers)
            allRoutes.push(this.menuList)
            this.setHomeRoute(routers)
            const {name, params, query, meta} = this.$route
            this.addTag({
                route: {name, params, query, meta}
            })
            this.setBreadCrumb(this.$route)
            // 设置初始语言
            this.setLocal(this.$i18n.locale)
            // 如果当前打开页面不在标签栏中，跳到homeName页
            if (!this.tagNavList.find(item => item.name === this.$route.name)) {
                this.$router.push({
                    name: this.$config.homeName
                })
            }
            // 获取未读消息条数
            // this.getUnreadMessageCount()
        }
    }
</script>
