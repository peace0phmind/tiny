<template>
  <div>
    <s-search ref="search" v-show="searchable" :search-items="meta._searchItems" :enums="meta._enums"
              :props="meta.props"
              slot="search"
              @change-search="changeSearch" v-permission:create.resource="meta.restfulResourcePath">
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
             v-permission:read.resource="meta.restfulResourcePath" @on-select="onSelect">

      <template slot="_action" slot-scope="{row, index}">
        <div v-show="!readonly">

          <el-link icon="el-icon-edit-outline" :underline="false" type="primary" @click="handleEdit(row.id, index)"
                   style="margin-right: 10px" v-permission:update.resource="meta.restfulResourcePath"></el-link>

          <el-link icon="el-icon-delete" :underline="false" type="danger" @click="handleRemove(row.id, index)"
                   style="margin-right: 10px" v-permission:delete.resource="meta.restfulResourcePath"></el-link>

          <Dropdown transfer @on-click="clickPermission($event, row.id)" v-if="row.type !== 'SUPER'"
                    v-permission:update.resource="meta.restfulResourcePath">
            <a href="javascript:void(0)">
              权限
              <Icon type="ios-arrow-down"></Icon>
            </a>
            <DropdownMenu slot="list">
              <DropdownItem name="menu"><a>菜单权限</a></DropdownItem>
              <DropdownItem name="operation"><a>操作权限</a></DropdownItem>
            </DropdownMenu>
          </Dropdown>

        </div>
      </template>

    </e-table>

    <Row style="margin-top: 10px;" v-permission:read.resource="meta.restfulResourcePath">
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
                :resource-path="meta.restfulResourcePath" :enums="meta._enums" :props="meta.props"
                @on-cancel="cancel" @on-save="submit">

      </zyx-form>

    </Modal>

    <Modal v-model="menuPermissionModalShow" width="400" ok-text="确定" cancel-text="取消" @on-ok="updateMenuPermission">
      <p slot="header" style="color:#5689ff;text-align:center">
        <Icon type="ios-information-circle"></Icon>
        <span>菜单权限</span>
      </p>
      <div style="padding: 0px 5px">
        <Tree ref="menuTree" :data="menuData" show-checkbox multiple></Tree>
      </div>
    </Modal>

    <Modal v-model="operationPermissionModalShow" width="1000" ok-text="确定" cancel-text="取消"
           @on-ok="updateOperationPermission">
      <p slot="header" style="color:#5689ff;text-align:center">
        <Icon type="ios-information-circle"></Icon>
        <span>操作权限</span>
      </p>
      <div style="padding: 0px 5px">
        <el-table :data="operationPermissionData" max-height="375" style="width: 100%" empty-text="当前无数据"
                  size="small">
          <!--          <el-table-column prop="modelName" label="模型名称" min-width="120"></el-table-column>-->
          <el-table-column prop="modelCnName" label="模型名称" min-width="120"></el-table-column>
          <el-table-column prop="permission" label="权限" min-width="500">
            <template slot-scope="scope">
              <CheckboxGroup v-model="scope.row.permission" v-for="type of scope.row.allPermission">
                <Checkbox :label="type">
                  <template v-if="type === 'CREATE'">
                    <Tag color="magenta"><i class="el-icon-plus"></i>创建</Tag>
                  </template>
                  <template v-else-if="type === 'READ'">
                    <Tag color="green"><i class="el-icon-info"></i>读取</Tag>
                  </template>
                  <template v-else-if="type === 'UPDATE'">
                    <Tag color="gold"><i class="el-icon-edit-outline"></i>更新</Tag>
                  </template>
                  <template v-else-if="type === 'DELETE'">
                    <Tag color="red"><i class="el-icon-delete-solid"></i>删除</Tag>
                  </template>
                  <template v-else-if="type === 'MOVE'">
                    <Tag color="orange"><i class="el-icon-d-caret"></i>移动</Tag>
                  </template>
                  <template v-else-if="type === 'EXPORT'">
                    <Tag color="cyan"><i class="el-icon-share"></i>导出</Tag>
                  </template>
                  <template v-else-if="type === 'IMPORT'">
                    <Tag color="blue"><i class="el-icon-upload"></i>导入</Tag>
                  </template>
                  <template v-else-if="type === 'BPMSTART'">
                    <Tag color="geekblue"><i class="el-icon-set-up"></i>开始流程</Tag>
                  </template>
                  <template v-else-if="type === 'BPMCANCEL'">
                    <Tag color="volcano"><i class="el-icon-close"></i>取消流程</Tag>
                  </template>
                  <template v-else-if="type === 'BPMCLAIM'">
                    <Tag color="purple"><i class="el-icon-check"></i>签收任务</Tag>
                  </template>
                  <template v-else-if="type === 'BPMCOMPLETE'">
                    <Tag color="purple"><i class="el-icon-success"></i>完成任务</Tag>
                  </template>
                  <template v-else>
                    <Tag><i class="el-icon-question"></i>{{type}}</Tag>
                  </template>
                </Checkbox>
              </CheckboxGroup>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </Modal>

  </div>
</template>

<script>
    import * as meta from './meta'
    import table from '@/package/mixins/tableMixin.jsx'
    import Rest from 'zyx-proton-ui/package/lib/Rest'
    import {copyObjectRenameAttrInArrRecursive} from 'zyx-proton-ui/package/lib/tools'

    export default {
        name: meta.name,
        data() {
            return {
                meta,
                menuSelection: [],
                menuPermissionModalShow: false,
                menuSubmitLoading: false,
                operationPermissionModalShow: false,
                operationSubmitLoading: false,
                menuData: [],
                currentId: undefined,
                menuMap: {},
                operationPermissionData: [],
                preCheckedMenus: [],
                preAllocatedOperations: []
            }
        },
        mixins: [table],
        methods: {
            clickPermission(itemName, id) {
                if (itemName === 'menu') this.openMenuPermission(id)
                else if (itemName === 'operation') this.openOperationPermission(id)
            },

            openOperationPermission(id) {

                this.operationPermissionModalShow = true

                this.currentId = id

                const roleOperationRest = new Rest('sys-role-permissions', this.$apiURL)

                const operationRest = new Rest('sys-permissions', this.$apiURL)

                const findAllOperationTypesGroupByModelName = (data) => {

                    const modelGroup = {}

                    data.forEach((d) => {
                        const {modelName, modelCnName, type} = d
                        modelGroup[modelName] = modelGroup[modelName] || {}
                        modelGroup[modelName][type] = d
                        modelGroup[modelName].modelCnName = modelCnName
                        modelGroup[modelName].allPermission = modelGroup[modelName].allPermission || []
                        modelGroup[modelName].allPermission.push(type)
                    })

                    return modelGroup
                }

                operationRest.GET().then(res => {
                    const allOperationPermission = res.content.filter(op => op.operationPermDTO)

                    roleOperationRest.GET({params: {'roleId.equals': id}}).then(res => {

                        const group = findAllOperationTypesGroupByModelName(allOperationPermission)

                        const allocatedOperations = res.content

                        this.preAllocatedOperations = JSON.parse(JSON.stringify(allocatedOperations))

                        this.operationPermissionData = Object.entries(group).map(([modelName, {allPermission, modelCnName, ...meta}]) => {
                            const permission = JSON.parse(JSON.stringify(allPermission))

                            allocatedOperations.forEach(allocatedOperation => {
                                if (allocatedOperation.allow === false) {
                                    const index = permission.findIndex(p => p === allocatedOperation.permission.type && modelName === allocatedOperation.permission.modelName)
                                    if (index >= 0) permission.splice(index, 1)
                                }
                            })

                            return {
                                modelName,
                                modelCnName,
                                allPermission,
                                permission,
                                meta
                            }
                        })

                    })

                })

            },

            updateOperationPermission() {
                const rolePermissions = []

                this.operationSubmitLoading = true
                const allowcated = this.preAllocatedOperations
                this.operationPermissionData.forEach(({meta, allPermission, permission}) => {
                    const all = JSON.parse(JSON.stringify(allPermission))
                    const allow = allowcated
                    permission.forEach(p => all.splice(all.findIndex(item => item === p), 1))

                    all.forEach(p => {
                        const permission = {id: meta[p].id}
                        permission.type = p
                        permission._type = 'OP'

                        rolePermissions.push({
                            permission,
                            _type: 'OP',
                            allow: false
                        })
                    })

                })

                this.restTemplate.PUT({data: {id: this.currentId, permissions: rolePermissions}}).then(res => {
                    this.operationSubmitLoading = false
                    this.operationPermissionModalShow = false
                    this.$Message.success('分配成功')
                }, error => {
                    this.operationSubmitLoading = false
                    this.$Message.error('分配失败')
                })
            },

            openMenuPermission(id) {
                this.menuPermissionModalShow = true

                this.currentId = id

                const menuRest = new Rest('sys-menus', this.$apiURL)

                const checkMenu = (menuData, checkedMenus) => {
                    menuData.forEach(menu => {
                        if (checkedMenus.findIndex(id => id === menu.id) >= 0) {
                            menu.checked = true
                        }
                        if (menu.children) checkMenu(menu.children, checkedMenus)
                    })
                }

                menuRest.GET({uri: 'tree', params: {level: 10}}).then(res => {
                    const menuData = copyObjectRenameAttrInArrRecursive({
                        src: res,
                        correspondence: {_instanceName: 'title', id: 'id', _children: 'children'}
                    })

                    const initMenuMap = (menuData) => {
                        menuData.forEach(menu => {
                            this.menuMap[menu.title] = menu.id
                            if (menu.children) initMenuMap(menu.children)
                        })
                    }

                    initMenuMap(menuData)

                    menuRest.GET({params: {'roles.equals': [id]}}).then(res => {
                        const checkedMenus = res.content
                        this.preCheckedMenus = JSON.parse(JSON.stringify(checkedMenus))
                        checkedMenus && checkedMenus.length && checkMenu(menuData, checkedMenus.map(menu => menu.id))
                        this.menuData = menuData
                    })

                })

            },
            updateMenuPermission() {
                this.menuSubmitLoading = true
                const role = {}
                role.id = this.currentId
                role.menus = this.$refs.menuTree.getCheckedNodes().map(node => {
                    const menu = JSON.parse(JSON.stringify(node))
                    if (this.preCheckedMenus.findIndex(m => m.id === node.id) < 0) {
                        menu._mode = 'CREATE'
                    }
                    return menu
                })
                this.preCheckedMenus.forEach(p => {
                    if (role.menus.findIndex(m => m.id === p.id) < 0) {
                        role.menus.push({...p, _mode: 'DELETE'})
                    }
                })
                this.restTemplate.PUT({data: role}).then(res => {
                    this.$Message.success('配置成功')
                    this.menuSubmitLoading = false
                    this.menuPermissionModalShow = false
                }, error => {
                    this.$Message.error('配置失败')
                    this.menuSubmitLoading = false
                })
            }
        }
    }
</script>

<style scoped>
  .ivu-checkbox-group {
    font-size: 14px;
    display: inline-block;
  }
</style>
