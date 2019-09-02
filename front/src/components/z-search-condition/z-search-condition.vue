<template>
  <div>
    <Row @keydown.enter.native="handleSearch">
      <Form ref="searchForm" :model="queryParams" inline :label-width="90" label-position="left" class="search-form">

        <template v-if="hasDefaultSearchItem">

          <template v-for="(item, index) in searchItemArray">
            <template v-if="item.defaultShow">
              <z-search-condition-item :item="item" :enums="enums" :option-data-prop="optionDataProp"
                                       :query-params="queryParams"></z-search-condition-item>
            </template>
          </template>

          <template v-for="(item, index) in searchItemArray">
            <template v-if="!item.defaultShow">
              <z-search-condition-item :item="item" :enums="enums" :option-data-prop="optionDataProp"
                                       :query-params="queryParams" v-show="drop"></z-search-condition-item>
            </template>
          </template>

        </template>

        <template v-else>
          <template v-if="searchItemArray.length > 2">
            <template v-for="(item, index) in searchItemArray">
              <template v-if="index < 2">
                <z-search-condition-item :item="item" :enums="enums" :option-data-prop="optionDataProp"
                                         :query-params="queryParams"></z-search-condition-item>
              </template>
            </template>

            <template v-for="(item, index) in searchItemArray">
              <template v-if="index >= 2">
                <z-search-condition-item :item="item" :enums="enums" :option-data-prop="optionDataProp"
                                         :query-params="queryParams" v-show="drop"></z-search-condition-item>
              </template>
            </template>

          </template>

          <template v-else>
            <template v-for="(item, index) in searchItemArray">
              <z-search-condition-item :item="item" :enums="enums" :option-data-prop="optionDataProp"
                                       :query-params="queryParams"></z-search-condition-item>
            </template>
          </template>

        </template>


        <Form-item style="margin-left:-91px;" class="br">
          <Button @click="handleSearch" type="primary" icon="ios-search">搜索</Button>
          <Button @click="handleReset" style="margin-left: 10px;">重置</Button>
          <a style="font-size: 13px;margin-left: 10px" @click="dropDown" v-if="searchItemArray.length > 2">
            {{dropDownContent}}
            <Icon :type="dropDownIcon"></Icon>
          </a>
        </Form-item>
      </Form>
    </Row>
  </div>
</template>

<script>

  import {filterObj} from '../../libs/util'

  import ZSearchConditionItem from './z-search-condition-item'

  export default {
    name: 'z-search-condition',
    components: {ZSearchConditionItem},
    props: {
      searchItems: {
        type: Array,
        default() {
          return []
        }
      },
      enums: {
        default() {
          return {}
        }
      },
      optionDataProp: {}
    },
    data() {
      return {
        searchItemArray: [],
        dropDownContent: '展开',
        dropDownIcon: 'ios-arrow-down',
        queryParams: {},
        drop: false,
        hasDefaultSearchItem: false
      }
    },
    created() {
      this.searchItemArray = this.searchItems.filter(item => item.type !== 'DECIMAL')
      let hasDefaultSearchItem = this.searchItemArray.find(item => item.defaultShow === true)
      hasDefaultSearchItem && (this.hasDefaultSearchItem = true)
    },
    methods: {
      dropDown() {
        if (this.drop) {
          this.dropDownIcon = 'ios-arrow-down'
          this.dropDownContent = '展开'
        } else {
          this.dropDownIcon = 'ios-arrow-up'
          this.dropDownContent = '收起'
        }
        this.drop = !this.drop
        this.$emit('on-drop')
      },
      handleSearch() {
        let parameter = {}
        let keys = Object.keys(this.queryParams)
        console.log('keys', keys)
        if (keys && keys.length > 0) {
          keys.forEach((key) => {

            let item
            this.searchItemArray.forEach(d => d.key == key && (item = d))
            if (item.operation === 'range') {
              if (item.type === 'DATETIME') {
                if (item.dateType === 'DEFAULT') {
                  parameter[`${key}.greaterOrEqualThan`] = this.dateFormat(this.queryParams[key][0], 'YYYY-MM-DDTHH:mm:ss')
                  parameter[`${key}.lessThan`] = this.dateFormat(this.queryParams[key][1], 'YYYY-MM-DDTHH:mm:ss')
                } else if (item.dateType === 'DATE_ONLY') {
                  parameter[`${key}.greaterOrEqualThan`] = this.dateFormat(this.queryParams[key][0], 'YYYY-MM-DD')
                  parameter[`${key}.lessThan`] = this.dateFormat(this.queryParams[key][1], 'YYYY-MM-DD')
                } else if (item.dateType === 'TIME_ONLY') {
                  parameter[`${key}.greaterOrEqualThan`] = this.queryParams[key][0]
                  parameter[`${key}.lessThan`] = this.queryParams[key][1]
                }
              }
            } else {
              parameter[`${key}.${item.operation}`] = this.queryParams[key]
            }
          })
          this.$emit('search', filterObj(parameter))
        }
      },
      handleReset() {
        // this.$refs['searchForm'].resetFields()
        Object.keys(this.queryParams).forEach(key => this.$delete(this.queryParams, key))
        this.$emit('search', {})
      },
    }
  }
</script>

<style scoped>
  .ivu-form-item {
    margin-bottom: 10px;
    vertical-align: top;
    zoom: 1;
  }

  .drop-down {
    font-size: 13px;
    margin-left: 5px;
  }
</style>

