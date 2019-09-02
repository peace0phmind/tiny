<template>
  <div>
    <Row v-if="selectable">
      <Alert show-icon>{{ selection.length | formatSelectTip}}</Alert>
    </Row>

    <Table border :columns="columns" :data="data" @on-selection-change="onSelectionChange">

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

  </div>
</template>

<script>
  import Rest from '../../libs/proton/Rest'
  import {filterObj} from '../../libs/util'

  export default {
    name: 'z-single-table',
    props: {
      /* 列的配置描述 */
      columns: {
        default() {
          return []
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
      routePath: {
        default() {
          return undefined
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
      }
    },
    data() {
      return {
        total: 0,
        data: [],
        page: 1,
        size: 10,
        pageSizeOptions: [],
        selection: [],
        selectable: false
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
      this.http = new Rest(this.restfulResourcePath)
      this.pageSize && (this.size = this.pageSize)
      this.pageSizeOpts && (this.pageSizeOptions = this.pageSizeOpts)
      /* 是否是rest模式 */
      this.rest && this.loadDataFromRest()
      /* 如果设置了localdata */
      || (this.localData && (this.data = this.localData))

      /* 判断是否可选择 */
      let selection_column = this.columns.find(column => column.type === 'selection')
      selection_column && (this.selectable = true)
    },
    methods: {
      /* 从restful地址取数据 */
      loadDataFromRest(options = {}) {
        let {page = 1, size = this.size, queryParam = {}} = options
        page && (this.page = page)
        size && (this.size = size)
        const _params = this.assembleQueryParams(queryParam)
        this.http.GET({params: _params}).then((res) => {
          this.data = res[this.dataProp]
          this.total = res[this.totalProp]
        })
      },
      /* 拼装查询参数 */
      assembleQueryParams(queryParam) {
        const param = Object.assign({}, queryParam)
        param.page = this.page - 1
        param.size = this.pageable && this.size || 10000
        this.sortList.forEach(sortItem => param['sort'] = sortItem)
        param['sort'] = 'id,desc'
        return filterObj(param)
      },
      setData(data) {
        this.data = data
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
