<template>
  <div>
    <s-search ref="search" v-show="searchable" :search-items="meta._searchItems" :enums="meta._enums"
              :props="meta.props"
              slot="search"
              @change-search="changeSearch" v-permission:read.resource="meta.restfulResourcePath">
    </s-search>

    <s-operation-bar v-show="!readonly" :meta="Object.assign(meta)">
    </s-operation-bar>

    <e-table ref="table" :columns="meta._columns" :data="data" :enums="meta._enums" :meta="Object.assign(meta)" :resource-path="meta.restfulResourcePath"
             @on-sort-change="onSortChange" @on-selection-change="onselectionchange" @on-select="onSelect"
             v-permission:read.resource="meta.restfulResourcePath">

      <!--      <Avatar style="color: #f56a00;background-color: #fde3cf" slot="name" slot-scope="{row}">{{ row.name }}</Avatar>-->

      <span slot="success" slot-scope="{row}">{{ row.success && '是' || '否' }}</span>
      <template slot="_action" slot-scope="{row, index}">

      </template>

    </e-table>

    <Row style="margin-top: 10px;" v-permission:read.resource="meta.restfulResourcePath">
      <div style="float: right;">
        <Page :total="total" :current="page" show-sizer show-elevator show-total @on-change="onPageChange"
              :page-size-opts="meta.pageSizeOpts" :page-size="size"
              @on-page-size-change="onPageSizeChange"/>
      </div>
    </Row>

    <slot name="referenceTables"></slot>

    <Modal :title="title" v-model="modalShow" :fullscreen="modalFullscreen"
           :footer-hide="true" :width="modalWidth"
           :mask-closable="false" :ok-text="meta.submitText" :loading="submitLoading">


      <zyx-form v-if="modalShow" ref="form" :form-items="meta._formItems" :form-layout="meta._formLayout"
                :resource-path="meta.restfulResourcePath"
                :enums="meta._enums" :props="meta.props" @on-cancel="cancel" @on-save="submit">
      </zyx-form>

    </Modal>

  </div>
</template>

<script>
    import * as meta from './meta'
    import table from '@/package/mixins/tableMixin.jsx'

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
        data() {
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
                extraSubmitParams: {},
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
