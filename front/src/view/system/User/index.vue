<template>
  <div>
    <s-search ref="search" v-show="searchable" :search-items="meta._searchItems" :enums="meta._enums"
              :props="meta.props"
              slot="search"
              @change-search="changeSearch" v-permission:read.resource="meta.restfulResourcePath">
<!--      <Input slot="username" slot-scope="{queryParams, width}" v-model="queryParams.username" :style="{width: width}"-->
<!--             placeholder="输入一点啥"></Input>-->
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

    <e-table ref="table" :columns="meta._columns" :data="data" :enums="meta._enums" :meta="Object.assign(meta)"
             :resource-path="meta.restfulResourcePath"
             @on-sort-change="onSortChange" @on-selection-change="onselectionchange"
             @on-select="onSelect"
             v-permission:read.resource="meta.restfulResourcePath">

      <span slot="enabled" slot-scope="{row, index}">{{ row.enabled && '是' || '否' }}</span>
      <template slot="_action" slot-scope="{row, index}">
        <!-- 操作按钮区域 -->
        <div v-show="!readonly">
          <el-tooltip effect="dark" content="修改" placement="left">
            <el-link icon="el-icon-edit-outline" :underline="false" type="primary" @click="handleEdit(row.id, index)"
                     style="margin-right: 10px" v-permission:update.resource="meta.restfulResourcePath"></el-link>
          </el-tooltip>
          <el-tooltip v-if="userId !== row.id" effect="dark" content="重置密码" placement="left">
            <el-link icon="el-icon-refresh-left" :underline="false" type="primary" @click="resetPasswords(row)"
                     style="margin-right: 10px" v-permission:update.resource="meta.restfulResourcePath"></el-link>
          </el-tooltip>
          <el-tooltip v-if="row.enabled && userId !== row.id" effect="dark" content="锁定" placement="left">
            <el-link icon="el-icon-lock" :underline="false"
                     @click="changeEnabled(row.id, false,'锁定')"
                     style="margin-right: 10px" v-permission:update.resource="meta.restfulResourcePath"></el-link>
          </el-tooltip>
          <el-tooltip v-if="!row.enabled && userId !== row.id" effect="dark" content="解锁" placement="left">
            <el-link icon="el-icon-unlock" :underline="false"
                     @click="changeEnabled(row.id, true,'解锁')"
                     style="margin-right: 10px" v-permission:update.resource="meta.restfulResourcePath"></el-link>
          </el-tooltip>
          <el-tooltip v-if="userId !== row.id" effect="dark" content="删除" placement="left">
            <el-link icon="el-icon-delete" :underline="false" type="danger" @click="handleRemove(row.id, index)"
                     style="margin-right: 10px" v-permission:delete.resource="meta.restfulResourcePath"></el-link>
          </el-tooltip>
        </div>
      </template>

    </e-table>

    <Row style="margin-top: 5px;" v-permission:read.resource="meta.restfulResourcePath">
      <div style="float: right;">
        <Page :total="total" :current="page" show-sizer show-elevator show-total @on-change="onPageChange"
              :page-size-opts="pageSizeOpts" :page-size="size"
              @on-page-size-change="onPageSizeChange"/>
      </div>
    </Row>

    <slot name="referenceTables"></slot>

    <Modal :title="title" v-model="modalShow" :fullscreen="modalFullscreen"
           :footer-hide="true" :width="modalWidth"
           :mask-closable="false" :ok-text="meta.submitText" :loading="submitLoading">

      <zyx-form v-if="modalShow" ref="form" :form-items="meta._formItems" :form-layout="meta._formLayout"
                :resource-path="meta.restfulResourcePath" :enums="meta._enums" :props="meta.props" @on-cancel="cancel"
                @on-save="submit" @on-name-change="onNameChange">
        <!--        <Input slot="username" slot-scope="{formModel}" v-model="formModel.username" icon="ios-eye"></Input>-->
      </zyx-form>

    </Modal>

    <Modal v-model="modal" width="400" ok-text="确定" cancel-text="取消" @on-ok="resetPasswordsSubmit">
      <p slot="header" style="color:#56a3ff;text-align:left">
        <Icon type="ios-information-circle"></Icon>
        <span>{{passwordTitle}}</span>
      </p>
      <div>
        <Form :model="passwordModel" :rules="passwordRules" ref="passwordForm">
          <FormItem prop="newPassword" label="新密码">
            <Input v-model="passwordModel.newPassword" ref="newPassword" placeholder="请输入新密码"
                   :type="passwordCanSee && 'text' || 'password'" icon="ios-eye"
                   @on-click="passwordCanSee = !passwordCanSee"/>
          </FormItem>
        </Form>
      </div>
    </Modal>
  </div>
</template>

<script>
    import * as meta from './meta'
    import table from '@/package/mixins/tableMixin'
    import Rest from '@/libs/proton/Rest'

    export default {
        name: meta.name,
        data() {
            return {
                meta,
                modal: false,
                newPassword: null,
                passwordCanSee: false,
                passwordModel: {
                    newPassword: undefined
                },
                passwordRules: {
                    newPassword: [
                        {min: 6, message: '长度最小为6位'}
                    ]
                },
                passwordTitle: '重置密码',
                id: '',
                modal_loading: false,
                mode: '',
                passwordFlag: true
            }
        },
        computed: {
            userId() {
                return this.$store.state.user.userId
            }
        },
        methods: {
            onNameChange(data) {
            },
            resetPasswords(obj) {
                this.passwordTitle = `重置【${obj.name}】密码`
                this.id = obj.id
                this.modal = true
            },
            resetPasswordsSubmit() {
                this.$refs.passwordForm.validate((valid) => {
                    if (valid) {
                        new Rest('sys-users').PUT({
                            uri: `${this.id}/password`,
                            params: {newPassword: this.passwordModel.newPassword}
                        }).then((res) => {
                            this.modal_loading = false
                            this.modal = false
                            this.newPassword = null
                            this.$Message.success('操作成功')
                        })
                    }
                })

            },
            afterEdit() {
                this.$refs.form.hideFormItems(true, 'password', 'newPassword')
            },
            afterAdd() {
                this.$refs.form.hideFormItems(false, 'password', 'newPassword')
            },
            beforeSubmit() {
                this.extraSubmitParams.newPassword = this.$refs.form.formModel.newPassword
            },
            changeEnabled(id, flag, title) {
                this.$Modal.confirm({
                    title: '警告',
                    content: `确定${title}吗？`,
                    onOk: () => {
                        this.modal_loading = true
                        new Rest('sys-users').PUT({
                            data: {
                                enabled: flag,
                                id: id
                            }
                        }).then((res) => {
                            this.modal_loading = false
                            this.modal = false
                            this.$Message.success('操作成功')
                            this.load()
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

  .footer {
    width: 100%;
    border-top: 1px solid #e8e8e8;
    padding: 10px 16px;
    text-align: right;
    background: #fff;
  }
</style>
