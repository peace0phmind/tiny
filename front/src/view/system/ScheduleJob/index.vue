<template>
  <div>
    <s-search ref="search" v-show="searchable" :search-items="meta._searchItems" :enums="meta._enums"
              :props="meta.props"
              slot="search"
              @change-search="changeSearch">
    </s-search>

    <s-operation-bar v-show="!readonly" :meta="Object.assign(meta)">


      <Button type="primary" style="margin-right: 5px;" @click="handleAdd"
              v-permission:create.resource="meta.restfulResourcePath"><i
        class="el-icon-plus"></i>新增
      </Button>

      <Button style="margin-right: 5px;" @click="handleRemoveAll"
              v-permission:delete.resource="meta.restfulResourcePath"><i class="el-icon-delete-solid"></i>批量删除
      </Button>

    </s-operation-bar>

    <e-table ref="table" :columns="meta._columns" :data="data" :enums="meta._enums" :meta="Object.assign(meta)" :resource-path="meta.restfulResourcePath"
             @on-sort-change="onSortChange" @on-selection-change="onselectionchange" @on-select="onSelect">

      <template slot="active" slot-scope="{row, index}">
        <Tag color="#3d88fe" v-if="row.active">已激活</Tag>
        <Tag color="#ed4014" v-if="!row.active">已停止</Tag>
      </template>

      <template slot="_action" slot-scope="{row, index}">
        <div v-show="!readonly">
          <Button type="primary" size="small" style="margin-right: 5px;" @click="handleEdit(row.id, index)">修改</Button>

          <Button type="info" size="small" style="margin-right: 5px;" @click="showLogModal(row.id)">日志</Button>

          <Button type="warning" size="small" style="margin-right: 5px;" @click="pauseOrResume(row)">{{row.active?
            '停止':'激活'}}
          </Button>

          <Button type="error" size="small" @click="handleRemove(row.id, index)">删除</Button>

          <el-tooltip effect="dark" content="修改" placement="left">
            <el-link icon="el-icon-edit-outline" :underline="false" type="primary" @click="handleEdit(row.id, index)"
                     style="margin-right: 10px" v-permission:update.resource="meta.restfulResourcePath"></el-link>
          </el-tooltip>

          <el-tooltip effect="dark" content="日志" placement="left">
            <el-link icon="el-icon-document" :underline="false" @click="showLogModal(row.id)"
                     style="margin-right: 10px" v-permission:read.resource="meta.restfulResourcePath"></el-link>
          </el-tooltip>

          <el-tooltip v-if="row.active" effect="dark" content="停止" placement="left">
            <el-link icon="el-icon-circle-close" :underline="false" @click="showLogModal(row.id)"
                     style="margin-right: 10px" v-permission:update.resource="meta.restfulResourcePath"></el-link>
          </el-tooltip>

          <el-tooltip v-if="!row.active" effect="dark" content="激活" placement="left">
            <el-link icon="el-icon-circle-check" :underline="false" @click="showLogModal(row.id)"
                     style="margin-right: 10px" v-permission:update.resource="meta.restfulResourcePath"></el-link>
          </el-tooltip>

          <el-tooltip effect="dark" content="删除" placement="left">
            <el-link icon="el-icon-delete" :underline="false" type="danger" @click="handleRemove(row.id, index)"
                     style="margin-right: 10px" v-permission:delete.resource="meta.restfulResourcePath"></el-link>
          </el-tooltip>
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

    <slot name="referenceTables"></slot>

    <Modal :title="title" v-model="modalShow" :fullscreen="modalFullscreen"
           :footer-hide="true" :width="modalWidth"
           :mask-closable="false" :ok-text="meta.submitText" :loading="submitLoading">

      <zyx-form v-if="modalShow" ref="form" :form-items="meta._formItems" :form-layout="meta._formLayout"
              :resource-path="meta.restfulResourcePath"
              :enums="meta._enums" :props="meta.props" @on-cancel="cancel" @on-save="submit">
        <FormItem slot="cronExpression" slot-scope="{formModel, item}" label="cron表达式" prop="cronExpression">
          <Input v-model="formModel.cronExpression" style="width:100%" type="text" clearable
                 @click.native="cronPopover=true">
            <Icon type="ios-clock-outline" slot="prefix" @click="cronPopover=true" style="cursor: pointer;"/>
          </Input>
          <el-popover v-model="cronPopover">
            <cron @change="changeCron" @close="cronPopover=false" i18n="cn" style="width: 623px;"></cron>
          </el-popover>
        </FormItem>
      </zyx-form>
    </Modal>

  </div>
</template>

<script>
    import * as meta from './meta'
    import table from '@/package/mixins/tableMixin.jsx'
    import Rest from '@/libs/proton/Rest'
    import {cron} from 'vue-cron'

    export default {
        template: '<cron/>',
        components: {cron},
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
                cronPopover: false,
                jobId: null
            }
        },
        methods: {
            pauseOrResume(job) {
                let flag = job.active ? "停止" : "激活";
                let active = job.active ? false : true;
                this.$Modal.confirm({
                    title: '警告',
                    content: "确定" + flag + "【" + job.beanName + " - " + job.methodName + "】任务吗？",
                    onOk: () => {
                        new Rest('sys-schedule-jobs-pauseOrResume').PUT({
                            data: {
                                id: job.id,
                                beanName: job.beanName,
                                methodName: job.methodName,
                                params: job.params,
                                cronExpression: job.cronExpression,
                                singleCase: job.singleCase,
                                remarks: job.remarks,
                                active: job.active
                            }
                        }).then((res) => {
                            job.active = active
                            this.$Message.success('操作成功');
                        })
                    }
                })
            },
            changeCron(val) {
                this.$refs.form.setValue('cronExpression', val)
            },
            showLogModal(id) {
                this.$Modal.info({
                    title: '定时任务日志',
                    closable: true,
                    width: 1200,
                    render: (h) => {
                        return h('c-sys-schedule-job-logs', {
                            props: {
                                jobId: id,
                                showSearch: false,
                                selectingMode: true
                            }
                        })
                    }
                })
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
<style>
  /* 全局样式 */
  #changeContab .bottom .value {
    float: left;
    padding-left: 10px;
  }

  .el-button--primary {
    width: 50px;
    height: 30px;
    float: right;
    padding: 0px;
    margin-left: 10px;
  }
</style>
