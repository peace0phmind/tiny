<template>
  <div>

    <z-search-condition :search-items="searchItems" :enums="enums" @search="search"
                        v-if="searchable"></z-search-condition>

    <Row style="padding-bottom: 10px;" v-if="addable || editable || removable">
      <Button type="primary" @click="add" v-if="addable">新增</Button>
      <Button type="primary" @click="edit" style="margin-left: 10px;" v-if="editable">修改</Button>
      <Button type="error" @click="remove" style="margin: 0 10px;" v-if="removable">批量删除</Button>
      <slot name="customAction"></slot>
    </Row>

    <Row v-show="selection.length > 0">
      <Alert show-icon>{{ selection.length | formatSelectTip}}</Alert>
    </Row>

    <Table border :columns="columns" :data="data" @on-selection-change="onSelectionChange" :height="tableHeight">

      <template v-for="(item, index) in columns" slot-scope="{ row }" :slot="item.slot">

        <template v-if="item.decorator && item.decorator.type == 'link' && item.decorator.type !== 'BOOLEAN'">
          <a :href="item.decorator.linkUrl" target="_blank">
            <template v-if="item.type == 'MODEL'">
              <template v-if="item.modelType == 'ENUM'">
                <span>{{ enums[item.enumName][row[item.slot]] && enums[item.enumName][row[item.slot]].name}}</span>
              </template>

              <template v-else>
                <span>{{ row[item.slot] && row[item.slot]._instanceName}}</span>
              </template>
            </template>

            <template v-else-if="item.type == 'DATETIME'">
              <template v-if="item.dateType == 'DEFAULT'">
                <span>{{ row[item.slot] | date_format }}</span>
              </template>

              <template v-if="item.dateType == 'DATE_ONLY'">
                <span>{{ row[item.slot] | date_format('YYYY-MM-DD') }}</span>
              </template>

              <template v-if="item.dateType == 'TIME_ONLY'">
                <span>{{ row[item.slot]}}</span>
              </template>
            </template>

            <template v-else>
              <span>{{ row[item.slot] }}</span>
            </template>
          </a>
        </template>

        <template v-else-if="item.decorator && item.decorator.type == 'image'">
          <Avatar :src="row[item.slot]"/>
        </template>

        <template v-else>
          <template v-if="item.type == 'MODEL'">
            <template v-if="item.modelType == 'ENUM'">
              <span>{{ enums[item.enumName][row[item.slot]] && enums[item.enumName][row[item.slot]].name}}</span>
            </template>

            <template v-else>
              <span>{{ row[item.slot] && row[item.slot]._instanceName }}</span>
            </template>
          </template>

          <template v-else-if="item.type == 'DATETIME'">
            <template v-if="item.dateType == 'DEFAULT'">
              <span>{{ row[item.slot] | date_format }}</span>
            </template>

            <template v-if="item.dateType == 'DATE_ONLY'">
              <span>{{ row[item.slot] | date_format('YYYY-MM-DD') }}</span>
            </template>

            <template v-if="item.dateType == 'TIME_ONLY'">
              <span>{{ row[item.slot] }}</span>
            </template>
          </template>

          <template v-else-if="item.type == 'BOOLEAN'">
            <i-switch :value="row[item.slot]" disabled/>
          </template>

          <template v-else>
            <span>{{ row[item.slot] }}</span>
          </template>
        </template>


      </template>

    </Table>
    <template v-if="pageable">
      <Row style="margin-top: 10px;">
        <div style="float: right;">
          <Page :total="total" :current="page" show-sizer show-elevator show-total @on-change="onChange"
                :page-size-opts="pageSizeOptions" :pageSize="size" @on-page-size-change="onPageSizeChange"/>
        </div>
      </Row>
    </template>


    <z-drawer-form ref="drawerForm" :enums="enums" :form-items="formItems" :form-layout="formLayout"
                   :resource-path="restfulResourcePath" @on-form-success="submitSuccess"
                   v-if="formMode === 'Drawer'"></z-drawer-form>

    <z-modal-form ref="modalForm" :enums="enums" :form-items="formItems" :form-layout="formLayout"
                  :resource-path="restfulResourcePath" @on-form-success="submitSuccess"
                  v-if="formMode === 'Modal'"></z-modal-form>
  </div>
</template>

<script>
  import Rest from '../../libs/proton/Rest'
  import {filterObj} from '../../libs/util'
  import ZSearchCondition from "../z-search-condition/z-search-condition"

  export default {
    name: 'z-table',
    components: {ZSearchCondition},
    provide() {
      return {
        FormRoot: this
      }
    },
    props: {
      /* 列的配置描述 */
      columns: {
        default() {
          return []
        }
      },
      context: {
        type: Object
      },
      /* 表单数据 */
      formItems: {
        default() {
          return []
        }
      },
      formLayout: {
        default() {
          return []
        }
      },
      tableHeight: {
        default() {
          return undefined
        }
      },
      /* 本地数据 */
      localData: {
        default() {
          return []
        }
      },
      /* 是否开启 rest模式，需配合 rest-resource-path使用 */
      rest: {
        default() {
          return false
        }
      },
      /* restful 接口地址，当rest===true时生效 */
      restfulResourcePath: {
        default() {
          return undefined
        }
      },
      /* data = rest.resp.content */
      dataProp: {
        default() {
          return 'content'
        }
      },
      /* total = rest.resp.totalElements */
      totalProp: {
        default() {
          return 'totalElements'
        }
      },
      /* 主键映射字段 */
      idProp: {
        default() {
          return 'id'
        }
      },
      /* 枚举列的描述，用于自动将枚举值映射为对应i18n的名称 */
      enums: {
        default() {
          return {}
        }
      },
      /* 是否可添加 */
      addable: {
        default() {
          return true
        }
      },
      /* 是否可编辑 */
      editable: {
        default() {
          return true
        }
      },
      /* 是否可删除 */
      removable: {
        default() {
          return true
        }
      },
      /* 表单模式，Modal弹窗，Drawer抽屉，Breadcrumb面包屑 */
      formMode: {
        default() {
          return 'Drawer'
        }
      },
      routePath: {
        default() {
          return undefined
        }
      },
      /* 是否可搜索，为后台搜索，当rest===true时生效 */
      searchable: {
        default() {
          return false
        }
      },
      /* 查询参数的配置 */
      searchItems: {
        default() {
          return []
        }
      },
      /* 是否分页，为后台分页，当rest===true时生效 */
      pageable: {
        default() {
          return false
        }
      },
      pageSize: {
        default() {
          return 10
        }
      },
      pageSizeOpts: {
        default() {
          return [10, 20, 30, 40]
        }
      },
      restSubmit: {
        default() {
          return true
        }
      },
      hasExtraParam: {
        default() {
          return false
        }
      }
    },
    data() {
      return {
        total: 0,
        data: [],
        page: 1,
        size: 10,
        pageSizeOptions: [],
        queryParam: {},
        selection: [],
        currentContext: this.context,
        visible: {},
        extraParam: {}
      }
    },
    computed: {
      sortList: function () {
        let sortList = []
        this.columns.forEach(column => {
          column.sort && sortList.push(`${column.slot},${column.sort}`)
        })
        return sortList
      }
    },
    created() {
      if (!this.context) {
        this.currentContext = this.$parent
      } else {
      }
      this.http = new Rest(this.restfulResourcePath)
      this.pageSize && (this.size = this.pageSize)
      this.pageSizeOpts && (this.pageSizeOptions = this.pageSizeOpts)
      /* 是否是rest模式 */
      this.rest && this.loadDataFromRest()
      /* 如果设置了localdata */
      || (this.localData && (this.data = this.localData))
      this.elasticHeight()
    },
    methods: {
      /* 从restful地址取数据 */
      loadDataFromRest(options = {}) {
        let {page = 1, size = this.size} = options
        page && (this.page = page)
        size && (this.size = size)
        const _params = this.assembleQueryParams()
        this.http.GET({params: _params}).then((res) => {
          this.data = res[this.dataProp]
          this.total = res[this.totalProp]
        })
      },
      /* 拼装查询参数 */
      assembleQueryParams() {
        let param = Object.assign({}, this.queryParam)
        if (this.hasExtraParam) {
          param = Object.assign(param, this.extraParam)
        }
        param.page = this.page - 1
        param.size = this.pageable && this.size || 10000
        this.sortList.forEach(sortItem => param['sort'] = sortItem)
        param['sort'] = 'id,desc'
        return filterObj(param)
      },
      search(param) {
        this.queryParam = param
        this.loadDataFromRest()
      },
      setExtraParam(param) {
        this.extraParam = param
      },
      addData(_data) {
        this.data.push(_data)
      },
      setData(_data) {
        if (_data) {
          _data = _data.filter(d => d && d != null)
          this.data = _data
        }
      },
      elasticHeight() {

      },
      submitSuccess(res) {
        if (this.restSubmit) {
          this.queryParam = {}
          this.selection = []
          this.loadDataFromRest({page: this.page, size: this.size})
        } else {
          this.$emit('on-result', res)
        }
      },
      /* 页码改变的回调，返回改变后的页码 */
      onChange(page) {
        this.loadDataFromRest({page: page})
      },
      /* 切换每页条数时的回调，返回切换后的每页条数 */
      onPageSizeChange(size) {
        this.loadDataFromRest({page: this.page, size: size})
      },
      onSelectionChange(selection) {
        this.selection = selection
        this.$emit('on-selection-change', selection)
      },
      getSelection() {
        return this.selection
      },
      clearSelection() {
        this.selection = []
        this.$forceUpdate()
      },
      add() {
        switch (this.formMode) {
          case 'Modal':
            this.$set(this.visible, 'modalShow', true)
            console.log(this.visible)
            this.$refs.modalForm.open({restSubmit: this.restSubmit})
            break
          case 'Drawer':
            this.$set(this.visible, 'drawerShow', true)
            console.log(this.visible)
            this.$refs.drawerForm.open({restSubmit: this.restSubmit})
            break
          case 'Breadcrumb':
            this.routePath && this.$router.push(this.routePath)
            break
        }
      },
      edit() {
        if (this.selection.length > 0) {
          if (this.selection.length == 1) {
            let id = this.selection[0][this.idProp]
            switch (this.formMode) {
              case 'Modal':
                this.$set(this.visible, 'modalShow', true)
                this.$refs.modalForm.open({id: id, restSubmit: this.restSubmit})
                break
              case 'Drawer':
                this.$set(this.visible, 'drawerShow', true)
                this.$refs.drawerForm.open({id: id, restSubmit: this.restSubmit})
                break
              case 'Breadcrumb':
                this.routePath && this.$router.push(`${this.routePath}/${id}`)
                break
            }
          } else {
            this.$Message.warning('只支持选择一行数据!')
          }
        } else {
          this.$Message.warning('请选择一行数据!')
        }
      },
      remove() {
        if (this.selection.length > 0) {
          if (this.restSubmit) {
            this.$Modal.confirm({
              title: '警告',
              content: `已选择 ${this.selection.length} 项，确定删除吗？`,
              onOk: () => {
                let ids = this.selection.map(s => s[this.idProp])
                this.http.DELETE({uri: ids}).then(() => {
                  this.$Message.success('删除成功')
                  this.loadDataFromRest(this.current, this.pageSize)
                })
              }
            })
          } else {
            for (let i = 0; i < this.data.length; i++) {
              let _data = this.data[i]
              for (let select of this.selection) {
                if (_data.id === select.id) {
                  this.data.splice(i, 1)
                }
              }
            }
            this.$emit('on-remove', this.selection)
          }
        } else {
          this.$Message.warning('请选择一行数据!')
        }
      }
    },
    filters: {
      formatSelectTip(rows) {
        return `已选择 ${rows} 项`
      }
    }
  }
</script>

<style scoped>

</style>
