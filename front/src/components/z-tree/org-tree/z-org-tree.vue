<template>
  <div
    ref="dragWrapper"
    class="org-tree-drag-wrapper"
    @mousedown="mousedownView"
    @contextmenu="handleDocumentContextmenu"
  >
    <div class="org-tree-wrapper">
      <v-org-tree
        :data="treeData"
        :node-render="nodeRender"
        :expand-all="true"
        @on-node-click="handleNodeClick"
        :props="props"
        collapsable
      ></v-org-tree>
    </div>
  </div>
</template>

<script>
  import {on, off} from '@/libs/tools'

  export default {
    name: 'z-org-tree',
    props: {
      zoomHandled: {
        type: Number,
        default: 1
      },
      data: {
        default() {
          return {}
        }
      },
      menuList: {
        default() {
          return []
        }
      },
      props: {
        default() {
          return {id: 'id', label: 'name', children: '_children'}
        }
      }
    },
    data() {
      return {
        currentContextMenuId: '',
        orgTreeOffsetLeft: 0,
        orgTreeOffsetTop: 0,
        initPageX: 0,
        initPageY: 0,
        oldMarginLeft: 0,
        oldMarginTop: 0,
        canMove: false,
        treeData: {}
      }
    },
    computed: {
      orgTreeStyle() {
        return {
          transform: `translate(-50%, -50%) scale(${this.zoomHandled}, ${
            this.zoomHandled
            })`,
          marginLeft: `${this.orgTreeOffsetLeft}px`,
          marginTop: `${this.orgTreeOffsetTop}px`
        }
      }
    },
    created() {
      this.treeData = this.data && this.data
    },
    methods: {
      handleNodeClick(e, data, expand) {
        expand()
      },
      setTreeData(data) {
        this.treeData = JSON.parse(JSON.stringify(data))
      },
      closeMenu() {
        this.currentContextMenuId = ''
      },
      getBgColor(data) {
        return this.currentContextMenuId === data.id
          ? data.isRoot
            ? '#0d7fe8'
            : '#5d6c7b'
          : ''
      },
      nodeRender (h, data) {
      return (
        <div
          class={[
            'custom-org-node',
            data.children && data.children.length ? 'has-children-label' : ''
          ]}
          on-mousedown={event => event.stopPropagation()}
          on-contextmenu={this.contextmenu.bind(this, data)}
        >
          {data._instanceName}
          <dropdown
            trigger="custom"
            class="context-menu"
            visible={this.currentContextMenuId === data.id}
            nativeOn-click={this.handleDropdownClick}
            on-on-click={this.handleContextMenuClick.bind(this, data)}
            style={{
              transform: `scale(${1 / this.zoomHandled}, ${1 /
                this.zoomHandled})`
            }}
            v-click-outside={this.closeMenu}
          >
            <dropdown-menu slot="list">
              {this.menuList.map(item => {
                return (
                  <dropdown-item name={item.key}>{item.label}</dropdown-item>
                )
              })}
            </dropdown-menu>
          </dropdown>
        </div>
      )
    },
      contextmenu(data, $event) {
        let event = $event || window.event
        event.preventDefault
          ? event.preventDefault()
          : (event.returnValue = false)
        this.currentContextMenuId = data.id
      },
      setDepartmentData(data) {
        data.isRoot = true
        this.departmentData = data
      },
      mousedownView(event) {
        this.canMove = true
        this.initPageX = event.pageX
        this.initPageY = event.pageY
        this.oldMarginLeft = this.orgTreeOffsetLeft
        this.oldMarginTop = this.orgTreeOffsetTop
        on(document, 'mousemove', this.mousemoveView)
        on(document, 'mouseup', this.mouseupView)
      },
      mousemoveView(event) {
        if (!this.canMove) return
        const {pageX, pageY} = event
        this.orgTreeOffsetLeft = this.oldMarginLeft + pageX - this.initPageX
        this.orgTreeOffsetTop = this.oldMarginTop + pageY - this.initPageY
      },
      mouseupView() {
        this.canMove = false
        off(document, 'mousemove', this.mousemoveView)
        off(document, 'mouseup', this.mouseupView)
      },
      handleDropdownClick(event) {
        event.stopPropagation()
      },
      handleDocumentContextmenu() {
        this.canMove = false
      },
      handleContextMenuClick(data, key) {
        this.$emit('on-menu-click', {data, key})
      }
    },
    mounted() {
      on(document, 'contextmenu', this.handleDocumentContextmenu)
    },
    beforeDestroy() {
      off(document, 'contextmenu', this.handleDocumentContextmenu)
    }
  }
</script>

<style>
</style>
