<template>
  <div>
    <s-search ref="search" v-show="searchable" :search-items="meta._searchItems" :enums="meta._enums"
              :props="meta.props"
              slot="search"
              @change-search="changeSearch">
    </s-search>

    <s-operation-bar :meta="Object.assign(meta)" v-if="!readonly">
    </s-operation-bar>

    <e-table ref="table" :columns="meta._columns" :data="data" :enums="meta._enums" :meta="Object.assign(meta)" :resource-path="meta.restfulResourcePath"
             @on-sort-change="onSortChange" @on-selection-change="onselectionchange" @on-select="onSelect">

      <template slot="job" slot-scope="{row, index}">
        <p>{{row.job}}</p>
      </template>

      <template slot="_action" slot-scope="{row, index}">
      </template>

    </e-table>

    <Row style="margin-top: 10px;">
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
            jobId: {},
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
        methods: {
            beforeLoad() {
                this.extraParams['jobId.equals'] = this.jobId
            }
        },
        mixins: [table]
    }
</script>

<style scoped>
  .drawer-footer {
    width: 100%;
    position: absolute;
    bottom: 0;
    left: 0;
    border-top: 1px solid #e8e8e8;
    padding: 10px 16px;
    text-align: right;
    background: #fff;
  }
</style>
