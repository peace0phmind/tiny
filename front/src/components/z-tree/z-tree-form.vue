<template>
  <div id="zTreeForm">
    <z-search-condition id="zSearchCondition" :search-items="searchItems" :enums="enums"
                        @search="search" @on-drop="elasticHeight"></z-search-condition>
    <div v-if="currentView == 'tree'">
      <Row style="margin-bottom: 10px;" id="zOptions">
        <ButtonGroup>
          <Button type="primary" @click="() => insertRoot()">新增根节点</Button>
          <Button @click="() => append(this.$refs.zTree.currentNode.data)">新增子节点</Button>
          <Button @click="() => insertBefore(this.$refs.zTree.currentNode.data)">节点前新增</Button>
          <Button @click="() => insertAfter(this.$refs.zTree.currentNode.data)">节点后新增</Button>
          <!--        <Button @click="() => remove(this.$refs.zTree.currentNode)">删除</Button>-->
          <Button type="error" @click="() => removeBatch()">删除</Button>
          <Button type="warning" @click="() => showOrgTree(this.$refs.zTree.currentNode.data)">图谱</Button>
        </ButtonGroup>
      </Row>
      <Row id="alertInfo">
        <Alert show-icon>
          {{ selectingNode | formatSelectTip}}
          <div style="float: right;">
            {{ editTip | formatEditTip }}
          </div>
        </Alert>
      </Row>
      <Row :gutter="20" style="height: 100%;">
        <Col :span="5">
          <Card :shadow="true" :style="{height: treeHeight, overflowY: 'scroll'}">
            <z-tree ref="zTree" :resource-path="resourcePath" :draggable="draggable" :default-level="defaultExpandLevel"
                    :show-checkbox="showCheckbox" :check-strictly="checkStrictly"
                    :show-search="false" @node-click="nodeClick" @node-drop="nodeDrop">

              <span class="custom-tree-node" slot-scope="{node, data}">
                <span :style="data._highlight == true && {color: 'red'} || {}">{{ node.label }}</span>
              </span>
            </z-tree>
          </Card>
        </Col>
        <Col :span="19">
          <Card :shadow="true" :style="{height: formCardHeight}">
            <div :style="{overflowY: 'scroll', height: formHeight, padding: '0px 8%'}">
              <z-form ref="zForm" :form-items="formItems" :form-layout="formLayout" :resource-path="resourcePath"
                      :enums="enums"
                      :label-width="100"
                      @on-submit-success="onSuccess" :is-tree-form="true"></z-form>
            </div>
            <div class="drawer-footer">
              <Button type="primary" style="margin-right: 8px;" :disabled="disabledCancel" @click="cancel">
                取消
              </Button>
              <Button type="primary" :loading="loading" :disabled="disabled" @click="submit">
                <span v-if="!loading">提交</span>
                <span v-else>提交中...</span>
              </Button>
            </div>
          </Card>
        </Col>
      </Row>
    </div>
    <Modal v-model="orgTreeModal" width="800" :footer-hide="true">
      <p slot="header" style="color:#f60;text-align:center">
        <Icon type="ios-information-circle"></Icon>
        <span>图谱</span>
      </p>
      <div style="text-align:center">
        <div>
          <z-org-tree id="orgTree" ref="orgTree"
                      :menu-list="menuList"
                      :zoom-handled="zoomHandled"
                      @on-menu-click="handleMenuClick">
          </z-org-tree>
        </div>
        <div style="margin-bottom: 16px;">
          <Button style="float: right;" icon="ios-download-outline" type="text" @click="saveImage">保存为图片</Button>
        </div>
      </div>
    </Modal>
  </div>
</template>

<script>
  import Rest from '@/libs/proton/Rest'
  import ZSearchCondition from "../z-search-condition/z-search-condition"
  import ZoomController from './org-tree/zoom-controller'
  import html2canvas from 'html2canvas'

  export default {
    name: "z-tree-form",
    provide() {
      return {
        FormRoot: this
      }
    },
    props: {
      searchItems: {},
      formItems: {},
      formLayout: {},
      enums: {},
      resourcePath: {},
      showCheckbox: {},
      checkStrictly: {},
      draggable: {},
      context: {
        type: Object
      },
      defaultExpandLevel: {
        default() {
          return 2
        }
      },
      defaultView: {
        default() {
          return 'tree'
        }
      },
      menuList: {
        default() {
          return [
            {
              key: 'edit',
              label: '编辑'
            },
            {
              key: 'add-child',
              label: '新增子部门'
            },
            {
              key: 'delete',
              label: '删除部门'
            }
          ]
        }
      }
    },
    components: {
      ZoomController,
      ZSearchCondition,
    },
    data() {
      return {
        defaultValue: {},
        selectedNode: {},
        disabled: true,
        disabledCancel: true,
        loading: false,
        selectingNode: undefined,
        editTip: '',
        operation: 'NORMAL',
        currentView: 'tree',
        parameter: {},
        orgTreeModal: false,
        zoom: 100,
        treeHeight: '420px',
        formHeight: '340px',
        formCardHeight: '400px',
        mainContentHeight: undefined,
        screenHeight: document.documentElement.clientHeight,
        currentContext: this.context
      }
    },
    computed: {
      zoomHandled() {
        return this.zoom / 100
      }
    },
    created() {
      if (!this.context) this.currentContext = this.$parent
      this.currentView = this.defaultView && this.defaultView
      this.rest = new Rest(this.resourcePath)
      this.search()
    },
    mounted() {
      this.$refs.zForm.init()
      this.$refs.zForm.disableFormItems()
      this.elasticHeight()
    },
    watch: {
      'screenHeight': function () {
        this.elasticHeight()
      }
    },
    methods: {
      /* search */
      search(parameter) {
        /* perform a search when the search parameter is not empty */
        if (parameter && Object.keys(parameter).length > 0) {
          this.rest.GET({
            uri: 'tree/search',
            params: {...parameter}
          }).then((res) => {
            this.$refs.zTree.setTreeData(res, true)
          })
          this.parameter = JSON.parse(JSON.stringify(parameter))
        } else { /* otherwise, reinitialize data */
          this.rest.GET({
            uri: `tree`,
            params: {level: this.defaultExpandLevel + 1}
          }).then((res) => {
            this.$refs.zTree.setTreeData(res)
          })
          this.parameter = Object.assign({}, {})
        }
      },
      elasticHeight() {
        setTimeout(() => {
          let mainContentHeight = this.mainContentHeight || document.getElementById('mainContent').clientHeight
          let zSearchConditionHeight = document.getElementById('zSearchCondition').clientHeight
          let zOptionsHeight = document.getElementById('zOptions').clientHeight
          let alertInfoHeight = document.getElementById('alertInfo').clientHeight
          let height = mainContentHeight - zSearchConditionHeight - zOptionsHeight - alertInfoHeight - 60
          this.treeHeight = `${height}px`
          this.formCardHeight = `${height - 20}px`
          this.formHeight = `${height - 80}px`
        }, 100)
      },
      /* change view */
      changeView(viewName) {
        this.currentView = viewName
      },
      /* change status */
      changeStatus(parameter = {}) {
        let {
          editTip = this.editTip,
          selectingNode = this.selectingNode,
          disabledSubmit = this.disabled,
          disabledCancel = this.disabledCancel,
          loadingSubmit = this.loading,
          operation = this.operation
        } = parameter
        this.editTip = editTip
        this.selectingNode = selectingNode
        this.disabled = disabledSubmit
        this.disabledCancel = disabledCancel
        this.loading = loadingSubmit
        this.operation = operation

      },
      /* show org tree by selected id */
      showOrgTree(data) {
        this.rest.GET({
          uri: `${data.id}/tree`,
          params: {level: 5, includeNode: true}
        }).then((res) => {
          this.$refs.orgTree.setTreeData(res[0])
        })
        this.orgTreeModal = true
      },
      /* save org tree to image */
      saveImage() {
        html2canvas(document.getElementById("orgTree")).then((canvas) => {
          const imgUrl = canvas.toDataURL("image/jpeg")
          downloadFile('图谱.jpg', imgUrl)
        })
      },
      /* handle click the `insert root` button */
      insertRoot() {
        this.changeStatus({
          editTip: `插入根节点`,
          disabledCancel: false,
          disabledSubmit: false,
          operation: `INSERT_ROOT`
        })
        this.$refs.zForm.init()
        this.$refs.zForm.resetField()
        this.$refs.zForm.enableFormItems()
        this.$refs.zForm.disableFormItems('_parent')
      },
      /* append node */
      append(data) {
        this.changeStatus({
          editTip: `向 ${data._instanceName} 节点下插入子节点`,
          disabledSubmit: false,
          disabledCancel: false,
          operation: `APPEND`
        })
        this.$refs.zForm.init()
        this.$refs.zForm.resetField()
        this.$refs.zForm.enableFormItems()
        this.$refs.zForm.set('_parent.id', data.id)
        this.$refs.zForm.disableFormItems('_parent')
      },
      /* insert node before current node */
      insertBefore(data) {
        if (this.selectingNode) {
          this.changeStatus({
            editTip: `在 ${data._instanceName} 节点前新增节点`,
            disabledCancel: false,
            disabledSubmit: false,
            operation: `INSERT_BEFORE`
          })
          this.$refs.zForm.init()
          this.$refs.zForm.resetField()
          this.$refs.zForm.enableFormItems()
          this.$refs.zForm.disableFormItems('_parent')
        } else {
          this.$Message.warning('未选择任何节点')
        }
      },
      /* insert node after current node*/
      insertAfter(data) {
        if (this.selectingNode) {
          this.changeStatus({
            editTip: `在 ${data._instanceName} 节点后新增节点`,
            disabledCancel: false,
            disabledSubmit: false,
            operation: `INSERT_AFTER`
          })
          this.$refs.zForm.init()
          this.$refs.zForm.resetField()
          this.$refs.zForm.enableFormItems()
          this.$refs.zForm.disableFormItems('_parent')
        } else {
          this.$Message.warning('未选择任何节点')
        }
      },
      /* remove node */
      remove(node, data) {
        if (node) {
          this.$Modal.confirm({
            title: '警告',
            content: `确定删除吗？`,
            onOk: () => {
              this.rest.DELETE({uri: node.key}).then(() => {
                this.$Message.success('删除成功')
                /* remove node, don't refresh data */
                this.$refs.zTree.remove(node)
                this.$refs.zForm.init()
                this.$refs.zForm.resetField()
                this.$refs.zForm.disableFormItems()
                this.selectingNode = undefined
              })
            }
          })
        } else {
          this.$Message.warning('未选中节点!')
        }
      },
      removeBatch() {
        let nodes = this.$refs.zTree.getCheckedNodes()
        if (nodes.length > 0) {
          let ids = nodes.map(data => data.id)
          this.$Modal.confirm({
            title: '警告',
            content: `已选择 ${ids.length} 项，确定删除吗？`,
            onOk: () => {
              this.rest.DELETE({uri: ids}).then(() => {
                this.$Message.success('删除成功')
                nodes.forEach(data => {
                  this.$refs.zTree.remove(data)
                })
                this.selectingNode = undefined
                this.editTip = ``
                this.$refs.zForm.init()
                this.$refs.zForm.resetField()
                this.$refs.zForm.disableFormItems()
                this.$refs.zTree.setCheckedKeys([])
              })
            }
          })
        } else {
          this.$Message.warning('未勾选任何数据!')
        }
      },
      /* handle click node event */
      nodeClick(status, data, node) {
        console.log(this.$refs.zTree.getTreeData())
        /* select */
        if (status === true) {
          this.$refs.zForm.init(data.id)
          this.changeStatus({
            disabledSubmit: false,
            disabledCancel: false,
            selectingNode: node,
            editTip: `编辑 ${data._instanceName} 中`,
            operation: `EDIT`
          })
          this.$refs.zForm.enableFormItems()
          this.$refs.zForm.disableFormItems('_parent')
        } else {/* cancel selection */
          this.changeStatus({
            disabledSubmit: true,
            disabledCancel: true,
            selectingNode: undefined,
            editTip: ``,
          })
          this.selectingNode = undefined
          this.$refs.zForm.init()
          this.$refs.zForm.resetField()
          this.$refs.zForm.disableFormItems()
        }
      },
      /* handle node drop */
      nodeDrop(node, toNode, dropType, draggingParent, event) {
        let drag = dropType
        drag == 'inner' && (drag = 'under')
        let uri = `tree/${node.data.id}/${drag}/${toNode.data.id}`
        this.rest.PUT({uri: uri}).then((res) => {
          this.$Message.success('操作成功')
          toNode.data._leaf = false
          toNode.expand()

          if (draggingParent) {
            const childNodes = draggingParent.childNodes;
            draggingParent.data._leaf = !childNodes || childNodes.length === 0;
          }
        })
      },
      /* callback after submit successfully from sub component */
      onSuccess(res) {
        this.$Message.success('操作成功')
        this.changeStatus({
          loadingSubmit: false,
          disabledSubmit: true,
          disabledCancel: true,
          selectingNode: undefined,
          editTip: ``
        })
        // this.$refs.zTree.loadData({level: this.defaultExpandLevel})
        this.$refs.zForm.init()
        this.$refs.zForm.resetField()
        this.$refs.zForm.disableFormItems()
      },
      /* execute submit */
      submit() {
        this.loading = true
        /* submit by self */
        /* get form data */
        let formData = this.$refs.zForm.getFormDataIfValid()
        if (formData) {
          if (this.operation == 'APPEND') {
            this.rest.POST({data: formData}).then((res) => {
              /* notify sub component after submit successfully */
              this.$refs.zForm.customSubmitSuccess()
              this.$refs.zTree.append(res, this.selectingNode)
              this.selectingNode.expand()
              this.selectingNode.data._leaf = false
            })
          } else if (this.operation === 'EDIT') {
            this.rest.PUT({data: formData}).then((res) => {
              /* notify sub component after submit successfully */
              this.$refs.zForm.customSubmitSuccess()
              this.selectingNode.data = JSON.parse(JSON.stringify(res))
            })
          } else if (this.operation === 'INSERT_ROOT') {/* `INSERT_ROOT` */
            this.rest.POST({data: formData}).then((res) => {
              /* notify sub component after submit successfully */
              this.$refs.zForm.customSubmitSuccess()
              this.$refs.zTree.append(res)
            })
          } else if (this.operation === 'INSERT_BEFORE') {/* `INSERT_BEFORE` */
            let uri = `tree/before/${this.selectingNode.data.id}`
            this.rest.POST({uri: uri, data: formData}).then((res) => {
              /* notify sub component after submit successfully */
              this.$refs.zForm.customSubmitSuccess()
              this.$refs.zTree.insertBefore(res, this.selectingNode)
            })
          } else if (this.operation === 'INSERT_AFTER') {/* `INSERT_AFTER` */
            let uri = `tree/after/${this.selectingNode.data.id}`
            this.rest.POST({uri: uri, data: formData}).then((res) => {
              /* notify sub component after submit successfully */
              this.$refs.zForm.customSubmitSuccess()
              console.log(this.selectingNode, res)
              this.$refs.zTree.insertAfter(res, this.selectingNode)
            })
          }
        }
      },
      cancel() {
        this.$refs.zForm.init()
        this.$refs.zForm.resetField()
        this.changeStatus({
          editTip: ``,
          disabledSubmit: true,
          disabledCancel: true,
          selectingNode: undefined
        })
        this.$refs.zForm.disableFormItems()
      },

      /* z-org-tree */
      handleMenuClick({data, key}) {
        console.log(data, key)
      },
    },
    filters: {
      formatSelectTip(selectingNode) {
        return selectingNode && `当前选择: ${selectingNode.data._instanceName} ` || `未选择节点`
      },
      formatEditTip(editTip) {
        return editTip && `编辑提示: ${editTip}` || ``
      }
    }
  }

  function downloadFile(fileName, content) {
    let aLink = document.createElement('a');
    let blob = base64ToBlob(content); //new Blob([content]);

    let evt = document.createEvent("HTMLEvents");
    evt.initEvent("click", true, true);//initEvent 不加后两个参数在FF下会报错  事件类型，是否冒泡，是否阻止浏览器的默认行为
    aLink.download = fileName;
    aLink.href = URL.createObjectURL(blob);

    // aLink.dispatchEvent(evt);
    aLink.click()
  }

  function base64ToBlob(code) {
    let parts = code.split(';base64,');
    let contentType = parts[0].split(':')[1];
    let raw = window.atob(parts[1]);
    let rawLength = raw.length;

    let uInt8Array = new Uint8Array(rawLength);

    for (let i = 0; i < rawLength; ++i) {
      uInt8Array[i] = raw.charCodeAt(i);
    }
    return new Blob([uInt8Array], {type: contentType});
  }
</script>

<style lang="less" scoped>
  .drawer-footer {
    width: 100%;
    position: absolute;
    bottom: -20px;
    left: 0;
    border-top: 1px solid #e8e8e8;
    padding: 10px 16px;
    text-align: right;
    background: #fff;
  }

  .custom-tree-node {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 14px;
    padding-right: 8px;
  }


</style>
