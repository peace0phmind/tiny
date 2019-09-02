<template>
  <div>
    <s-search ref="search" v-show="searchable" :search-items="meta._searchItems" :enums="meta._enums" :props="meta.props"
              slot="search"
              @change-search="changeSearch" v-permission:read.resource="meta.restfulResourcePath">
    </s-search>

    <s-operation-bar v-show="!readonly" :meta="Object.assign(meta)">

       <Button type="primary" style="margin-right: 5px;" @click="handleAdd" v-permission:create.resource="meta.restfulResourcePath"><i
        class="el-icon-plus"></i>新增</Button>

      <Button style="margin-right: 5px;" @click="handleRemoveAll" v-permission:delete.resource="meta.restfulResourcePath"><i class="el-icon-delete-solid"></i>批量删除</Button>

      <Button style="margin-right: 5px;" @click="frontExport" v-permission:export.resource="meta.restfulResourcePath"><i class="el-icon-share"></i>导出</Button>

    </s-operation-bar>

    <e-table ref="table" :columns="meta._columns" :data="data" :enums="meta._enums" :meta="Object.assign(meta)" :resource-path="meta.restfulResourcePath"
             @on-sort-change="onSortChange" @on-selection-change="onselectionchange" @on-select="onSelect" v-permission:read.resource="meta.restfulResourcePath">

      <!--      <Avatar style="color: #f56a00;background-color: #fde3cf" slot="name" slot-scope="{row}">{{ row.name }}</Avatar>-->

      <template slot="_action" slot-scope="{row, index}">

        <div v-show="!readonly">

          <el-tooltip effect="dark" content="修改" placement="left">
            <el-link icon="el-icon-edit-outline" :underline="false" type="primary" @click="handleEdit(row.id, index)"
                     style="margin-right: 10px" v-permission:update.resource="meta.restfulResourcePath"></el-link>
          </el-tooltip>
          <el-tooltip effect="dark" content="删除" placement="left">
            <el-link icon="el-icon-delete" :underline="false" type="danger" @click="handleRemove(row.id, index)"
                     style="margin-right: 10px" v-permission:delete.resource="meta.restfulResourcePath"></el-link>
          </el-tooltip>

        </div>

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
                :resource-path="meta.restfulResourcePath" :enums="meta._enums" :props="meta.props" @on-cancel="cancel"
                @on-save="submit">
      </zyx-form>

    </Modal>
  </div>
</template>

<script>
  import * as meta from './meta'
  import table from '@/package/mixins/tableMixin'

  export default {
    name: meta.name,
    data () {
      return {
        meta
      }
    },
    mixins: [table]
  }
</script>

<style scoped>
</style>
