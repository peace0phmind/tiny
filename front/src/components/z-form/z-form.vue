<template>
  <div>
    <!-- declare a from -->
    <Form ref="zForm" :model="model" :rules="rules" :label-width="labelWidth">
      <!-- parse fromItems -->
      <template v-if="formLayout.length > 0">
        <template v-for="(row, index) in formLayout">
          <Row :gutter="32">
            <template v-for="(item, index) in row">

              <Col :span="24 / row.length">
                <template v-if="item && item.hasOwnProperty('prop') && getFormItem(item.prop)">
                  <z-form-item :ref="`${item.prop}_form_item`" :item="getFormItem(item.prop)" :model="model"
                               :rules="rules"
                               :i18n="i18n"
                               :disabled="disabled" :drawerShow="drawerShow" :selectModelRender="selectModelRender"
                               :enums="enums" :span="24 / row.length" :is-tree-form="isTreeForm"></z-form-item>
                </template>
              </Col>

            </template>
          </Row>
        </template>
      </template>

      <template v-else>
        <template v-for="(item, index) in formItems" v-if="item.type !== 'COLLECTION'">
          <z-form-item :item="item" :model="model" :rules="rules" :i18n="i18n"
                       :disabled="disabled" :drawerShow="drawerShow" :selectModelRender="selectModelRender"
                       :enums="enums"></z-form-item>
        </template>
      </template>
    </Form>

    <template v-if="hasMany">
      <el-tabs v-model="activeTab">

        <template v-for="(item, index) in formItems" v-if="item.type == 'COLLECTION'">
          <el-tab-pane :label="item.label" :name="item.prop">
            <!-- 双向 -->
            <template v-if="item.bidirectional">
              <!-- 一对多 -->
              <template v-if="item.referenceMode === 'oneToMany'">
                <!-- 如果是修改，用远程增删改 -->
                <template v-if="id">
                  <z-table :ref="item.key" :columns="item.columns" :enums="item.enums" :rest="true"
                           :restful-resource-path="item.resourcePath" :pageable="true"
                           :form-items="item.formItems" :search-items="item.searchItems" :page-size="item.pageSize"
                           :searchable="false" :page-size-opts="item.pageSizeOpts" form-mode="Modal"
                           @on-selection-change="onSelectionChange(item.key, $event)" :height="200"
                           :has-extra-param="true"></z-table>
                </template>

                <!-- 如果是新增，用内存型增删改 -->
                <template v-else>
                  <z-table :ref="item.key" :columns="item.columns" :enums="item.enums" :rest="true"
                           :restful-resource-path="item.resourcePath" :pageable="true"
                           :form-items="item.formItems" :search-items="item.searchItems" :page-size="item.pageSize"
                           :searchable="false" :page-size-opts="item.pageSizeOpts" form-mode="Modal"
                           @on-selection-change="onSelectionChange(item.key, $event)" :rest-submit="false"
                           @on-result="onTableResult($event, item)" @on-remove="onTableRemove($event, item)"
                           :height="200" :has-extra-param="true"></z-table>
                </template>

              </template>

              <!-- 多对多，用select方式的table -->
              <template v-else-if="item.referenceMode === 'manyToMany'">
                <Row>
                  <Button @click="openSelectModel(item.key)">新增</Button>
                  <Button style="margin-left: 10px;" @click="removeSelection(item.key)">删除</Button>
                </Row>
                <z-single-table :ref="item.key" :columns="item.columns" :enums="item.enums" :rest="false"
                                :local-data="model[item.key]"
                                @on-selection-change="onRmSelectionChange(item.key, $event)"></z-single-table>
              </template>

            </template>

            <!-- 单向 -->
            <template v-else>
              <!-- 一对多，全部用内存型增删改 -->
              <template v-if="item.referenceMode === 'oneToMany'">
                <z-table :ref="item.key" :columns="item.columns" :enums="item.enums" :rest="true"
                         :restful-resource-path="item.resourcePath" :pageable="true"
                         :form-items="item.formItems" :search-items="item.searchItems" :page-size="item.pageSize"
                         :searchable="false" :page-size-opts="item.pageSizeOpts" form-mode="Modal"
                         @on-selection-change="onSelectionChange(item.key, $event)" :rest-submit="false"
                         @on-result="onTableResult($event, item)" @on-remove="onTableRemove($event, item)"
                         :has-extra-param="true"></z-table>
              </template>

              <!-- 多对多 -->
              <template v-else-if="item.referenceMode === 'manyToMany'">
                <Row>
                  <Button @click="openSelectModel(item.key)">新增</Button>
                  <Button style="margin-left: 10px;" @click="removeSelection(item.key)">删除</Button>
                </Row>
                <z-single-table :ref="item.key" :columns="item.columns" :enums="item.enums" :rest="false"
                                :local-data="model[item.key]"
                                @on-selection-change="onRmSelectionChange(item.key, $event)"></z-single-table>
              </template>

            </template>
          </el-tab-pane>
        </template>

      </el-tabs>

    </template>

    <template v-if="hasMany">

      <template v-for="(item, index) in formItems"
                v-if="item.type == 'COLLECTION' && item.referenceMode == 'manyToMany'">
        <Modal :title="item.label" :closable="false" v-model="drawerShow[item.key]" :width="modelModalWidth"
               :mask-closable="false"
               @on-ok="selectMany(item.key)" @on-cancel="cancelSelectModel(item.key)" style="z-index: 10000;"
               v-if="selectModelRender[item.key]">
          <z-table :ref="`${item.prop}_select`" :columns="item.columns" :enums="item.enums" :rest="true"
                   :restful-resource-path="item.resourcePath" :pageable="true"
                   :form-items="item.formItems" :form-layout="item.formLayout" :search-items="item.searchItems"
                   :page-size="item.pageSize"
                   :searchable="true" :page-size-opts="item.pageSizeOpts" form-mode="Modal"
                   @on-selection-change="onSelectionChange(item.key, $event)"></z-table>
        </Modal>
      </template>

    </template>

    <template v-for="(item, index) in formItems"
              v-if="item.type == 'MODEL' && (item.modelType == 'COMMON' || item.modelType == 'SUPER')">
      <template v-if="item.referenceMode == 'manyToOne'">
        <Modal :title="item.label" :closable="false" v-model="drawerShow[item.key]" :width="modelModalWidth"
               :mask-closable="false"
               @on-ok="selectOne(item)" @on-cancel="cancelSelectModel(item.key)" style="z-index: 10000;"
               v-if="selectModelRender[item.key]">
          <z-table :ref="`${item.prop}_select`" :columns="item.columns" :enums="item.enums" :rest="true"
                   :restful-resource-path="item.resourcePath" :pageable="true"
                   :form-items="item.formItems" :form-layout="item.formLayout" :search-items="item.searchItems"
                   :page-size="item.pageSize"
                   :searchable="true" :page-size-opts="item.pageSizeOpts" form-mode="Modal"
                   @on-selection-change="onSelectionChange(item.key, $event)"></z-table>
        </Modal>
      </template>
    </template>

    <!-- 加载遮罩 -->
    <Spin fix v-if="spin">提交中...</Spin>
  </div>
</template>

<script>

  import Rest from '../../libs/proton/Rest'
  import FormSlot from './form-slot'
  import {getModifiedValueInObjects} from '../../libs/util'
  import moment from 'moment'


  export default {
    name: "z-form",
    components: {FormSlot},
    props: {
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
      isTreeForm: {
        default() {
          return false
        }
      },
      labelWidth: {
        default() {
          return 80
        }
      },
      resourcePath: {
        required: true
      },
      dataProp: {
        default() {
          return undefined
        }
      },
      optionDataProp: {
        default() {
          return 'content'
        }
      },
      enums: {
        default() {
          return {}
        }
      },
      numberOfColsInARow: {
        default() {
          return 1
        }
      }
    },
    data() {
      return {
        model: {},
        changed: false,
        rules: {},
        i18n: {},
        disabled: {},
        hasMany: false,
        rmSelection: {},
        selection: {},
        drawerShow: {},
        spin: false,
        modelModalWidth: 790,
        id: undefined,
        // context: this.$parent.$parent.$parent.currentContext,
        dateOptions: {
          shortcuts: [
            {
              text: '今天',
              value() {
                return new Date();
              }
            },
            {
              text: '昨天',
              value() {
                const date = new Date();
                date.setTime(date.getTime() - 3600 * 1000 * 24);
                return date;
              }
            },
            {
              text: '一周前',
              value() {
                const date = new Date();
                date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                return date;
              }
            }
          ]
        },
        datetimeOptions: {
          shortcuts: [
            {
              text: '此刻',
              value() {
                return new Date();
              }
            },
            {
              text: '昨天',
              value() {
                const date = new Date();
                date.setTime(date.getTime() - 3600 * 1000 * 24);
                date.setHours(0)
                date.setMinutes(0)
                date.setSeconds(0)
                return date;
              }
            },
            {
              text: '一周前此刻',
              value() {
                const date = new Date();
                date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                date.setHours(0)
                date.setMinutes(0)
                date.setSeconds(0)
                return date;
              }
            }
          ]
        },
        activeTab: undefined,
        selectModelRender: {}
      }
    },
    created() {
      this.http = new Rest(this.resourcePath)
      let formItem = this.formItems.find(item => item.type == 'COLLECTION')
      if (formItem) {
        this.$set(this, 'hasMany', true)
        this.$set(this, 'activeTab', formItem.prop)
      }
    },
    mounted() {
      this.reRenderSelectFormItem()
      let documentWidth = document.documentElement.clientWidth
      this.modelModalWidth = documentWidth * 0.5
      if (this.modelModalWidth < 790) this.modelModalWidth = 790
    },
    methods: {
      init(id) {
        this.reset()

        this.id = undefined

        /* GET data if id is not empty */
        id && (this.id = id)
        if (id) {
          this.get(this.id)
        } else {
          this.initTable()
        }
        setTimeout(() => {
          this.reRenderSelectFormItem()
        }, 100)

        //设置额外的查询参数
        this.formItems.filter(item => item.referenceMode === 'oneToMany')
          .forEach(item => {
            let param = {}
            param[`${item.referModelAttribute}.equals`] = id
            this.$refs[item.key][0].setExtraParam(param)
          })

        console.log('form init successfully! ')
      },
      /* load data */
      get(id) {
        this.http.GET({uri: id}).then((res) => {
          if (res) {
            this.model = Object.assign({}, this.dataProp && res[this.dataProp] || res)
            Object.keys(this.model).forEach(k => {
              if (this.model[k] instanceof Object && this.model[k].hasOwnProperty('id')) {
                let v = this.model[k]['id']
                this.model[`${k}.id`] = v
              }
            })
            this.formItems.forEach(item => {
              if (item.type == 'COLLECTION') {
                if (item.bidirectional) {
                  let _rest = new Rest(item.resourcePath)
                  let param = {}
                  param[`${item.referModelAttribute}.equals`] = id
                  _rest.GET({
                    params: {
                      page: 0,
                      pageSize: 8,
                      ...param
                    }
                  }).then(res => this.$refs[item.key][0].setData(res['content']))
                } else {
                  this.$refs[item.key][0].setData(this.model[item.key])
                }
              }

              if (item.type == 'DATETIME' && (item.dateType == 'DATE_ONLY' || item.dateType == 'DEFAULT')) {
                this.model[item.key] = moment(this.model[item.key]).format('YYYY-MM-DD HH:mm:ss')
              }
            })
          }
        })
      },
      initTable() {
        this.formItems.forEach(item => {
          if (item.type == 'COLLECTION') {
            if (item.bidirectional) {
              let _rest = new Rest(item.resourcePath)
              let param = {}
              param[`${item.referModelAttribute}.equals`] = -1
              _rest.GET({
                params: {
                  page: 0,
                  pageSize: 8,
                  ...param
                }
              }).then(res => this.$refs[item.key][0].setData(res['content']))
            } else {
              this.$refs[item.key][0].setData(this.model[item.key])
            }
          }
        })
      },
      hasChanged() {
        return this.changed
      },
      reRenderSelectFormItem() {
        let selectModelArr = this.formItems.filter(item => item.type === 'MODEL' && (item.modelType === 'COMMON' || item.modelType === 'SUPER'))
        if (selectModelArr.length > 0) {
          selectModelArr.forEach(item => {
            let child = this.$refs[`${item.prop}_form_item`]
            if (child) {
              child[0].reRender()
            }
          })
        }
      },
      onTableResult(res, item) {
        if (this.model[item.key] && this.model[item.key].length > 0) {
          this.model[item.key].push(res)
        } else {
          this.$set(this.model, item.key, [])
          this.model[item.key].push(res)
        }
        this.$refs[item.key][0].setData(this.model[item.key])
      },
      onTableRemove(selection, item) {
        let arr = this.model[item.key]
        for (let i = 0; i < arr.length; i++) {
          let _data = arr[i]
          for (let select of selection) {
            if (_data.id === select.id) {
              arr.splice(i, 1)
            }
          }
        }
      },
      getFormItem(prop) {
        return this.formItems.find(item => item.prop === prop)
      },
      onRmSelectionChange(key, selection) {
        this.$set(this.rmSelection, key, selection)
      },
      /* delete selection */
      removeSelection(key) {

        this.rmSelection[key].forEach(sel => {
          for (let i = this.model[key].length - 1; i >= 0; i--) {
            if (this.model[key][i].id === sel.id) {
              this.model[key].splice(i, 1);
            }
          }
        })
      },
      onSelectionChange(key, selection) {
        this.$set(this.selection, key, selection)
      },
      /* select many arr */
      selectMany(key) {
        if (this.model[key] && this.model[key].length > 0) {
          this.selection[key].forEach((sel) => {
            !this.model[key].find(m => m.id === sel.id) && this.model[key].push(sel)
          })
        } else {
          this.$set(this.model, key, [])
          this.selection[key].forEach((sel, index) => this.$set(this.model[key], index, sel))
        }
        this.$set(this.drawerShow, key, false)
        this.$set(this.selection, key, [])
        this.$refs[key][0].setData(this.model[key])
      },
      selectOne(item) {
        let key = item.key
        if (this.selection[key].length > 1) {
          this.$Message.error('仅支持选择一行!')
        } else {
          this.$refs[`${item.prop}_form_item`][0].initModelOptions()
          this.$set(this.model, key, this.selection[key][0].id)
          this.$set(this.selection, key, [])
          this.$set(this.drawerShow, key, false)
        }
      },
      clearSelection() {
        this.formItems
          .filter(item => item.referenceMode === 'manyToMany' || (item.type === 'MODEL' && (item.modelType === 'COMMON' || item.modelType === 'SUPER')))
          .forEach(item => {
            let refs = this.$refs[`${item.prop}_select`]
            if (refs && refs.length > 0) {
              refs[0].clearSelection()
            }
          })
      },
      openSelectModel(key) {
        this.$set(this.selectModelRender, key, true)
        this.$set(this.drawerShow, key, true)
        // this.clearSelection()
      },
      cancelSelectModel(key) {
        this.$set(this.drawerShow, key, false)
        // this.clearSelection()
        // this.$set(this.selectModelRender, key, false)
      },
      /* pull tree nodes */
      pullTreeNodes(item) {
        let _rest = new Rest(item.resourcePath)
        _rest.GET({
          uri: `tree`,
          params: {level: 1}
        }).then((res) => {
          console.log(this.$refs)
          // this.$refs[item.prop].setTreeData(res)
        })
      },
      /* select one tree node */
      nodeClick(status, data, node, key) {
        (status) && this.$set(this.model, key, node.label)
      },
      /* reset form fields */
      reset() {
        this.formItems.filter(item => item.type === 'COLLECTION').forEach(item => {
          if (item.referenceMode === 'manyToMany') {
            this.$refs[item.key][0].setData([])
          }
        })
      },
      resetField() {
        this.formItems.filter(item => item.type !== 'COLLECTION').forEach(item => {
          let formItem = this.$refs[`${item.prop}_form_item`]
          if (formItem && formItem.length > 0) formItem[0].reset()
        })
        // this.$delete(this.model, 'id')
        Object.keys(this.model).forEach(key => this.$delete(this.model, key))
      },
      /* get valided form data */
      getFormDataIfValid() {
        let _data
        this.$refs.zForm.validate(valid => {
          if (valid) {

            //处理时间，日期
            this.formItems.filter(item => item.type === 'DATETIME' && (item.dateType === 'DATE_ONLY' || item.dateType === 'DEFAULT'))
              .forEach(item => {
                if (this.model[item.key]) {
                  if (item.dateType === 'DEFAULT') {
                    this.model[item.key] = moment(this.model[item.key]).utc()
                  }else {
                    this.model[item.key] = moment(this.model[item.key]).format('YYYY-MM-DD')
                  }
                }
              })
            _data = JSON.stringify(this.model)
            _data = JSON.parse(_data)
            Object.keys(_data).forEach(k => {
              if (k.indexOf('.') > 0) {
                let v = _data[k]
                delete _data[k]
                _data[k.split('.')[0]] = {}
                _data[k.split('.')[0]][k.split('.')[1]] = v
              }
            })
            Object.entries(_data).forEach(([key, value]) => {
              if (typeof value === 'object' && value) {
                if (value.hasOwnProperty('id')) {
                  if (value.id == null || value.id === undefined) {
                    delete _data[key]
                  }
                } else {
                  !value instanceof Array && delete _data[key]
                }
              }
            })

            if (Object.keys(this.i18n).length > 0) {
              Object.keys(this.i18n).forEach(k => {
                let key = k.substring(0, k.length - 2)
                delete _data[key]
              })
            }

            if (Object.keys(this.i18n).length > 0) {
              Object.keys(this.i18n).forEach(k => {
                let key = k.substring(0, k.length - 2)
                let lang = k.substring(k.length - 2, k.length)
                let value = this.i18n[k]
                if (_data.hasOwnProperty(key)) {
                  if (lang === 'zh') {
                    _data[key][`${lang}_CN`] = value
                  } else {
                    _data[key][lang] = value
                  }
                } else {
                  _data[key] = {}
                  if (lang === 'zh') {
                    _data[key][`${lang}_CN`] = value
                  } else {
                    _data[key][lang] = value
                  }
                }
              })
            }
          }
        });
        return _data
      },
      /* callback after custom successful submission */
      customSubmitSuccess() {
        /* notify the parent component of the successful commit eventv */
        this.$emit('on-submit-success')
      },
      /* submit form */
      submit({rest = true}) {
        let _data = this.getFormDataIfValid()
        if (_data) {
          if (rest) {
            let method = 'POST'
            this.id && (method = 'PUT')
            this.http[method]({data: _data}).then((res) => {
              /* notify the parent component of the successful commit eventv */
              this.$emit('on-submit-success', res)
            })
          } else {
            this.$emit('on-submit-success', _data)
          }
        }
      },
      /* model setter */
      set(key, value) {
        this.model[key] = value
      },
      /* disable form items */
      disableFormItems(...items) {
        if (items.length > 0) {
          items.forEach(itemName => {
            let item = this.formItems.find(item => item.prop == itemName)
            this.$set(this.disabled, item.key, true)
          })
        } else {
          this.formItems.forEach(formItem => {
            this.$set(this.disabled, formItem.key, true)
          })
        }
      },
      enableFormItems(...items) {
        if (items.length > 0) {
          items.forEach(itemName => {
            let item = this.formItems.find(item => item.prop == itemName)
            this.$delete(this.disabled, item.key)
          })
        } else {
          this.formItems.forEach(formItem => {
            this.$delete(this.disabled, formItem.key)
          })
        }
      }
    },
    filters: {
      placeFilter(label, type) {
        if (type === 'MODEL' || type === 'DATETIME')
          return `请选择${label}`
        else
          return `请填写${label}`
      }
    }
  }
</script>

<style scoped>

</style>
