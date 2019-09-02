<template>
  <div>
    <s-search ref="search" v-show="searchable" id="searchCondition" :search-items="meta._searchItems"
              :enums="meta._enums"
              :props="meta.props"
              slot="search"
              @change-search="changeSearch"
              @on-drop="onSearchDrop" v-permission:read.resource="meta.restfulResourcePath">
    </s-search>

    <s-operation-bar id="operationBar" v-show="!readonly" :meta="Object.assign(meta)">

      <Button style="margin-right: 5px;" @click="() => insertRoot()" v-permission:create.resource="meta.restfulResourcePath"><i class="el-icon-plus"></i>新增根节点
      </Button>

      <Button style="margin-right: 5px;" @click="() => append(this.$refs.tree.getCurrentNode())"
              v-permission:create.resource="meta.restfulResourcePath"><i
        class="el-icon-plus"></i>新增子节点
      </Button>

      <Button style="margin-right: 5px;" @click="() => insertBefore(this.$refs.tree.getCurrentNode())"
              v-permission:create.resource="meta.restfulResourcePath"><i
        class="el-icon-plus"></i>节点前新增
      </Button>

      <Button style="margin-right: 5px;" @click="() => insertAfter(this.$refs.tree.getCurrentNode())"
              v-permission:create.resource="meta.restfulResourcePath"><i
        class="el-icon-plus"></i>节点后新增
      </Button>

      <Button style="margin-right: 5px;" type="error" @click="() => removeBatch()"
              v-permission:delete.resource="meta.restfulResourcePath"><i class="el-icon-delete"></i>删除
      </Button>

      <Button v-if="!selecting" style="margin-right: 5px;" @click="() => showOrgTree(this.$refs.tree.getCurrentNode())"
              v-permission:read.resource="meta.restfulResourcePath">
        <i class="el-icon-document"></i>图谱
      </Button>

    </s-operation-bar>

    <Row :gutter="20" :style="{height: !readonly && '100%' || bodyElasticHeight}">
      <Col :span="readonly ? 24 : 5">
        <Card :shadow="true" :style="{height: treeHeight, overflowY: 'scroll'}">
          <el-tree ref="tree" :load="loadNode" :lazy="true" :props="meta.props" :show-checkbox="multipleSelect"
                   :node-key="meta.props.id" highlight-current
                   :check-strictly="readonly || selecting"
                   auto-expand-parent :default-expanded-keys="expandedKeys" :default-checked-keys="checkedKeys"
                   :expand-on-click-node="meta.expandOnClickNode" :default-expand-all="readonly ? true : expandAll"
                   :draggable="!readonly && meta.draggable"
                   :allow-drop="allowDrop" @node-click="nodeClick"
                   @node-drag-start="nodeDragStart"
                   @node-drop="nodeDrop" @check-change="checkChange">
            <template slot-scope="{node, data}">
              <div>
                <span :style="data._highlight && {color: 'red'} || {}">{{ node.label }}</span>
              </div>
            </template>
          </el-tree>
        </Card>
      </Col>
      <Col :span="19" v-show="!readonly">
        <Card :shadow="true" :style="{height: formCardHeight}">
          <div :style="{overflowY: 'scroll', height: formHeight, padding: '0px 8%'}">
            <zyx-form ref="editForm" :form-items="meta._formItems" :form-layout="meta._formLayout"
                      :resource-path="meta.restfulResourcePath"
                      :enums="meta._enums" :props="meta.props" @on-cancel="cancel" @on-save="submit">
            </zyx-form>
          </div>
        </Card>
      </Col>
    </Row>

    <Modal :title="title" v-model="modalShow" :fullscreen="meta.modalFullscreen" :footer-hide="true"
           :width="meta.modalWidth"
           :mask-closable="false" :ok-text="meta.submitText" :loading="loadingSubmit">

      <zyx-form v-if="modalShow" ref="addForm" :form-items="meta._formItems" :form-layout="meta._formLayout"
                :resource-path="meta.restfulResourcePath"
                :enums="meta._enums" :props="meta.props" @on-cancel="cancelAdd" @on-save="submitAdd">

      </zyx-form>
    </Modal>

  </div>
</template>

<script>

    import * as meta from './meta'
    import tree from '@/package/mixins/tree.jsx'

    export default {
        name: meta.name,
        data() {
            return {
                meta,
                draggingParent: undefined,
                expandedKeys: [],
                expandAll: false,
                treeHeight: '420px',
                formHeight: '340px',
                formCardHeight: '400px',
                mainContentHeight: undefined,
                screenHeight: document.documentElement.clientHeight,
                disabledCancel: true,
                disabledSubmit: true,
                loadingSubmit: false,
                parameter: {},
                defaultExpandLevel: meta.defaultExpandLevel,
                currentNode: undefined,
                orgTreeData: {},
                orgTreeModal: false,
                orgTreeModalWidth: 800,
                formRender: false,
                modalShow: false,
                currentOperation: undefined,
                title: '',
                checkedKeys: []
            }
        },
        mixins: [tree]
    }
</script>

<style scoped lang="less">
</style>
