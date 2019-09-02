import {filterObj} from '../lib/tools.js'
import * as Types from '../_util/javaTypes.js'
import * as utils from '../_util/util.js'
import * as Operations from '../_util/operations.js'
import * as Conditions from '../_util/conditions.js'
import searchItem from './search-item'
import _ from 'lodash'

export default {
  name: 's-search',

  props: {
    searchItems: {
      type: Array,
      default() {
        return []
      }
    },
    enums: {},
    props: Object,
    labelWidth: {
      type: Number,
      default: 80
    }
  },

  data() {
    return {
      queryParams: {},
      dropDownContent: '展开',
      dropDownIcon: 'ios-arrow-down',
      drop: false,
      searchLoading: false,
      resetLoading: false,
      lastSearchParams: {}
    }
  },

  components: {searchItem},

  provide() {
    return {
      SearchRoot: this
    }
  },

  computed: {
    searchItemArray() {
      return this.searchItems.filter(item => !item.hide)
    },
    hasDefaultSearchItem() {
      const item = this.searchItemArray.find(item => item.defaultShow === true)
      if (item) return true
    },
    labelPosition() {
      return 'left'
    },
    class() {
      return 'search-form'
    }
  },

  watch: {
    queryParams: {
      deep: true,
      handler(newValue) {
        console.log(newValue)
      }
    }
  },

  methods: {
    dropDown() {
      let {drop} = this
      if (drop) {
        this.dropDownIcon = 'ios-arrow-down'
        this.dropDownContent = '展开'
      } else {
        this.dropDownIcon = 'ios-arrow-up'
        this.dropDownContent = '收起'
      }
      this.drop = !drop
      this.$emit('on-drop')
    },
    setValue(key, value) {
      this.$set(this.queryParams, key, value)
    },
    reset() {
      this.$set(this, 'queryParams', {})
    },
    handleSearch() {
      let parameter = {}
      const keys = Object.keys(this.queryParams)
      keys.forEach((key) => {

        const item = this.searchItemArray.find(_item => _item.key === key)
        const {operation, type, tree, modelType, dateType} = item
        let _value = this.queryParams[key]

        if (_value) {
          if (operation === Operations.range) {
            if (type === Types.DateTime) {
              const [start, end] = _value
              const dateTimeFormat = 'YYYY-MM-DDTHH:mm:ss'
              const dateFormat = 'YYYY-MM-DD'
              const _start = `${key}.${Conditions.greaterOrEqualThan}`
              const _end = `${key}.${Conditions.lessThan}`

              if (dateType === Types.DateType.Default) {
                parameter[_start] = utils.dateFormat(start, dateTimeFormat)
                parameter[_end] = utils.dateFormat(end, dateTimeFormat)
              } else if (dateType === Types.DateType.DateOnly) {
                parameter[_start] = utils.dateFormat(start, dateFormat)
                parameter[_end] = utils.dateFormat(end, dateFormat)
              } else {
                parameter[_start] = start
                parameter[_end] = end
              }
            } else if (type === Types.Decimal) {
              const [min, max] = _value
              console.log('min, max', min, max, _value)
              const _min = `${key}.${Conditions.greaterOrEqualThan}`
              const _max = `${key}.${Conditions.lessThan}`
              min !== undefined && (parameter[_min] = min)
              max !== undefined && (parameter[_max] = max)
            }
          } else {
            parameter[`${key}.${operation}`] = _value

            if (type === Types.Model && (modelType === Types.ModelType.Common || modelType === Types.ModelType.Super) && tree) {
              parameter[`${key}.${operation}`] = _value && _value.length && !_value.includes(undefined) && [_value[_value.length - 1]] || undefined
            }
          }
        }
      })
      parameter = filterObj(parameter)
      this.$emit('change-search', parameter)
      this.lastSearchParams = Object.assign({}, this.queryParams)
    },
    handleReset() {
      this.$refs.searchForm.resetFields()
      Object.keys(this.queryParams).forEach(key => {
        this.$delete(this.queryParams, key)
      })
      this.$emit('change-search', {})
    },
    createBtnGroups() {
      let {searchItemArray, dropDownIcon, dropDownContent} = this
      return (
        <Form-item {...{style: {marginLeft: '-80px'}}}>
          <Button onClick={this.handleSearch} loading={this.searchLoading} type="primary"
                  icon="ios-search">搜索</Button>
          <Button onClick={this.handleReset}
                  loading={this.resetLoading} {...{style: {marginLeft: '10px'}}}>重置</Button>
          <a {...{style: {fontSize: '13px', marginLeft: '10px'}}} onClick={this.dropDown}
             v-show={searchItemArray.length > 2}>
            {dropDownContent}
            <Icon type={dropDownIcon}></Icon>
          </a>
        </Form-item>
      )
    },
    createItems() {
      let defaultShowItems
      let otherItems
      let items

      let {hasDefaultSearchItem, searchItemArray, enums, queryParams, props, dropDownIcon, dropDownContent, drop} = this

      const createItem = (item) => {
        return (<search-item item={item} enums={enums} queryParams={queryParams} props={props}></search-item>)
      }

      if (hasDefaultSearchItem) {
        defaultShowItems = searchItemArray.filter(item => item.defaultShow).map(item => {
          return createItem(item)
        })

        otherItems = searchItemArray.filter(item => !item.defaultShow).map(item => {
          return createItem(item)
        })

        items = drop && (
          <span>
          {defaultShowItems}
            {otherItems}
        </span>
        ) || (
          <span>
          {defaultShowItems}
        </span>
        )
      } else {
        if (searchItemArray.length <= 2) {
          items = searchItemArray.map(item => {
            return createItem(item)
          })
        } else {
          defaultShowItems = searchItemArray.map((item, index) => {
            if (index < 2) {
              return createItem(item)
            }
          })

          otherItems = searchItemArray.map((item, index) => {
            if (index >= 2) {
              return createItem(item)
            }
          })

          items = drop && (
            <span>
          {defaultShowItems}
              {otherItems}
        </span>
          ) || (
            <span>
          {defaultShowItems}
        </span>
          )
        }
      }
      return items
    }
  },

  render() {

    const items = this.createItems()
    const btnGroups = this.createBtnGroups()

    return (<div>
      <Row nativeOn-keydown={arg => arg.keyCode === 13 && this.handleSearch()}>
        <Form ref="searchForm" attrs={{model: this.queryParams}} inline label-width={this.labelWidth}
              label-position={this.labelPosition} class={this.class}>
          {items}
          {btnGroups}
        </Form>
      </Row>
    </div>)
  }
}
