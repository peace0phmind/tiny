<template>
  <div>
    <Alert show-icon>
      这里会列出所有模型可分配的权限列表, 请自行修改
    </Alert>
    <Row style="margin-bottom: 10px">
      <Button size="large" @click="handleAdd"><i class="el-icon-plus"></i>新增</Button>
    </Row>
    <Table border max-height="600" :columns="columns" :data="data" v-permission:read.resource="'sys-permissions'">

      <template slot="allPermission" slot-scope="{row, index}">
        <template v-for="type of row.allPermission">
          <template v-if="type === 'CREATE'">
            <Tag color="magenta" style="margin-right: 10px;"><i class="el-icon-plus"></i>创建</Tag>
          </template>
          <template v-else-if="type === 'READ'">
            <Tag color="green" style="margin-right: 10px;"><i class="el-icon-info"></i>读取</Tag>
          </template>
          <template v-else-if="type === 'UPDATE'">
            <Tag color="gold" style="margin-right: 10px;"><i class="el-icon-edit-outline"></i>更新</Tag>
          </template>
          <template v-else-if="type === 'DELETE'">
            <Tag color="red" style="margin-right: 10px;"><i class="el-icon-delete-solid"></i>删除</Tag>
          </template>
          <template v-else-if="type === 'MOVE'">
            <Tag color="orange" style="margin-right: 10px;"><i class="el-icon-d-caret"></i>移动</Tag>
          </template>
          <template v-else-if="type === 'EXPORT'">
            <Tag color="cyan" style="margin-right: 10px;"><i class="el-icon-share"></i>导出</Tag>
          </template>
          <template v-else-if="type === 'IMPORT'">
            <Tag color="blue" style="margin-right: 10px;"><i class="el-icon-upload"></i>导入</Tag>
          </template>
          <template v-else-if="type === 'BPMSTART'">
            <Tag color="geekblue" style="margin-right: 10px;"><i class="el-icon-set-up"></i>开始流程</Tag>
          </template>
          <template v-else-if="type === 'BPMCANCEL'">
            <Tag color="volcano" style="margin-right: 10px;"><i class="el-icon-close"></i>取消流程</Tag>
          </template>
          <template v-else-if="type === 'BPMCLAIM'">
            <Tag color="purple" style="margin-right: 10px;"><i class="el-icon-check"></i>签收任务</Tag>
          </template>
          <template v-else-if="type === 'BPMCOMPLETE'">
            <Tag color="purple" style="margin-right: 10px;"><i class="el-icon-success"></i>完成任务</Tag>
          </template>
          <template v-else>
            <Tag style="margin-right: 10px;"><i class="el-icon-question"></i>{{type}}</Tag>
          </template>
        </template>
      </template>

      <template slot-scope="{ row, index }" slot="action">
        <el-link icon="el-icon-edit-outline" :underline="false" type="primary" @click="handleEdit(row)"
                 v-permission:update.resource="'sys-permissions'"></el-link>
      </template>

    </Table>

    <Modal v-model="modalShow" width="700" ok-text="确定" cancel-text="取消" @on-ok="updateAllPermission">
      <p slot="header">
        <Icon type="ios-information-circle"></Icon>
        <span></span>
      </p>
      <div style="padding: 0px 5px">
        <Form v-model="modelPermission" label-position="right" :label-width="100">
          <FormItem label="模型名称">
            <Input v-model="modelPermission.modelName" :disabled="currentOperation === 'update'"></Input>
          </FormItem>
          <FormItem label="模型uri">
            <Input v-model="modelPermission.modelUri" :disabled="currentOperation === 'update'"></Input>
          </FormItem>
          <FormItem label="模型中文名">
            <Input v-model="modelPermission.modelCnName" :disabled="currentOperation === 'update'"></Input>
          </FormItem>
          <FormItem label="可分配权限">
            <Select multiple v-model="modelPermission.newModelOperations">
              <Option value="CREATE">创建</Option>
              <Option value="UPDATE">修改</Option>
              <Option value="DELETE">删除</Option>
              <Option value="READ">读取</Option>
              <Option value="EXPORT">导出</Option>
              <Option value="IMPORT">导入</Option>
              <Option value="MOVE">移动</Option>
              <Option value="BPMSTART">开始流程</Option>
              <Option value="BPMCANCEL">结束流程</Option>
              <Option value="BPMCLAIM">签收任务</Option>
              <Option value="BPMCOMPLETE">完成任务</Option>
            </Select>
          </FormItem>
        </Form>
      </div>
    </Modal>

  </div>
</template>

<script>

    import Rest from '../../../package/lib/Rest'

    export default {
        name: 'c-sys-permissions',
        data() {
            return {
                modelPermission: {
                    modelName: '',
                    modelCnName: '',
                    modelUri: '',
                    newModelOperations: []
                },
                modalShow: false,
                currentTitle: '',
                currentOperation: 'update',
                id: undefined,
                columns: [
                    {
                        title: '模型名称',
                        key: 'modelCnName',
                        width: 150
                    },
                    {
                        title: '模型uri',
                        key: 'modelUri',
                        width: 200,
                    },
                    {
                        title: '可分配权限',
                        slot: 'allPermission',
                        minWidth: 500,
                    },
                    {
                        title: '操作',
                        slot: 'action',
                        width: 150,
                        align: 'center'
                    }
                ],
                data: []
            }
        },
        created() {
            this.restTemplate = new Rest('sys-permissions', this.$apiURL)
            this.load()
        },
        methods: {
            handleEdit(row) {
                this.id = row.id
                this.currentTitle = `修改(${this.modelPermission.modelCnName}页面)可分配的权限`
                this.currentOperation = 'update'
                this.modelPermission = {
                    modelName: row.modelName,
                    modelCnName: row.modelCnName,
                    modelUri: row.modelUri,
                    newModelOperations: row.allPermission
                }
                this._watcher.update()
                this.modalShow = true
            },
            handleAdd() {
                this.id = undefined
                this.currentOperation = 'create'
                this.currentTitle = `新增`
                this.modalShow = true
            },
            updateAllPermission() {
                this.restTemplate.POST({
                    data: {
                        modelName: this.modelPermission.modelName,
                        modelCnName: this.modelPermission.modelCnName,
                        modelUri: this.modelPermission.modelUri,
                        types: this.modelPermission.newModelOperations,
                        _type: 'OP'
                    }
                }).then(res => {
                        this.$Message.success('操作成功')
                        this.modalShow = false
                        this.load()
                    },
                    error => {
                        this.$Message.error('操作失败')
                    })
            },
            load() {
                const findAllOperationTypesGroupByModelName = (data) => {

                    const modelGroup = {}

                    data.forEach((d) => {
                        const {modelName, modelCnName, modelUri, type, id} = d
                        modelGroup[modelName] = modelGroup[modelName] || {}
                        modelGroup[modelName][type] = d
                        modelGroup[modelName].modelCnName = modelCnName
                        modelGroup[modelName].modelUri = modelUri
                        modelGroup[modelName].id = id
                        modelGroup[modelName].allPermission = modelGroup[modelName].allPermission || []
                        modelGroup[modelName].allPermission.push(type)
                    })

                    return modelGroup
                }

                this.restTemplate.GET({params: {page: 0, size: 2000}}).then(res => {
                    const allOperationPermission = res.content.filter(op => op.operationPermDTO)


                    const group = findAllOperationTypesGroupByModelName(allOperationPermission)

                    this.data = Object.entries(group).map(([modelName, {allPermission, modelCnName, modelUri, id, ...meta}]) => {
                        return {
                            modelName,
                            modelCnName,
                            modelUri,
                            allPermission,
                            id,
                            meta
                        }
                    })
                })
            }
        }
    }
</script>

<style scoped>
</style>
