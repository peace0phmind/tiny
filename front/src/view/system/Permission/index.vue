<template>
  <div>
    <s-search ref="search" v-show="showSearch" :search-items="meta._searchItems" :enums="meta._enums" :props="meta.props"
              slot="search"
              @change-search="changeSearch">
    </s-search>

    <s-operation-bar v-show="!selectingMode" :meta="Object.assign(meta)">

      <Button type="primary" style="margin-right: 5px;" @click="handleAdd">新增</Button>

      <Button style="margin-right: 5px;" @click="handleRemoveAll">批量删除</Button>
      <Button style="margin-right: 5px;" @click="frontExport()">导出</Button>

    </s-operation-bar>

    <e-table ref="table" :columns="meta._columns" :data="data" :enums="meta._enums" :meta="Object.assign(meta)"
             @on-sort-change="onSortChange" @on-selection-change="onselectionchange">

      <!--      <Avatar style="color: #f56a00;background-color: #fde3cf" slot="name" slot-scope="{row}">{{ row.name }}</Avatar>-->

      <template slot="_action" slot-scope="{row, index}">
        <div v-show="!selectingMode">
          <Button type="primary" size="small" style="margin-right: 5px;" @click="handleEdit(row.id, index)">修改</Button>

          <Button type="error" size="small" @click="handleRemove(row.id, index)">删除</Button>
        </div>
      </template>

    </e-table>

    <Row style="margin-top: 10px;">
      <div style="float: right;">
        <Page :total="total" :current="page" show-sizer show-elevator show-total @on-change="onPageChange"
              :page-size-opts="meta.pageSizeOpts" :page-size="size"
              @on-page-size-change="onPageSizeChange"/>
      </div>
    </Row>

    <Modal v-if="meta.formMode === modes.modal" :title="title" v-model="modalShow" :fullscreen="meta.modalFullscreen" :footer-hide="true"
           :width="meta.modalWidth"
           :mask-closable="false" :ok-text="meta.submitText" :loading="submitLoading">

      <s-form v-if="modalShow" ref="form" :form-items="meta._formItems" :form-layout="meta._formLayout"
              :resource-path="meta.restfulResourcePath"
              :enums="meta._enums" :props="meta.props">
      <template slot="operation">
          <Button style="margin-right: 8px" @click="cancel">取消</Button>
          <Button type="primary" :loading="submitLoading" @click="submit">
            <span v-if="!submitLoading">{{ meta.submitText }}</span>
            <span v-else>提交中...</span>
          </Button>
        </template>

      </s-form>

    </Modal>

    <Drawer v-if="meta.formMode === modes.drawer" :title="title" v-model="modalShow" :width="meta.modalWidth"
            :mask-closable="false" :styles="styles"
            :mask="true">

      <Row v-if="modalShow" type="flex" justify="center" :style="{height: '90%', overflowY: 'scroll'}">
        <Col :span="20">
          <s-form ref="form" :form-items="meta._formItems" :form-layout="meta._formLayout"
                  :resource-path="meta.restfulResourcePath"
                  :enums="meta._enums" :props="meta.props">
          <template slot="operation">
          <Button style="margin-right: 8px" @click="cancel">取消</Button>
          <Button type="primary" :loading="submitLoading" @click="submit">
            <span v-if="!submitLoading">{{ meta.submitText }}</span>
            <span v-else>提交中...</span>
          </Button>
        </template>

      </s-form>
        </Col>
      </Row>

      <div v-if="modalShow" class="drawer-footer" id="footer">
        <Button style="margin-right: 8px" @click="cancel">取消</Button>
        <Button type="primary" :loading="submitLoading" @click="submit">
          <span v-if="!submitLoading">{{ meta.submitText }}</span>
          <span v-else>提交中...</span>
        </Button>
      </div>
    </Drawer>

  </div>
</template>

<script>
  import * as meta from './meta'
  import table from 'zyx-proton-ui/package/mixins/table'

  export default {
    name: meta.name,
    props: {
      showSearch: {
        default: true
      },
      shouldLoad: {
        default: true
      }
    },
    data () {
      return {
        meta,
        data: [],
        total: 0,
        page: 1,
        size: meta.pageSize,
        queryParams: {},
        title: '',
        width: meta.modalWidth,
        modalShow: false,
        submitLoading: false,
        id: undefined,
        extraParams: {},
        localMode: false,
        styles: {
          height: 'calc(100% - 55px)',
          paddingBottom: '53px',
          position: 'static'
        },
      }
    },
    mixins: [table]
  }
</script>

<style scoped>
</style>
