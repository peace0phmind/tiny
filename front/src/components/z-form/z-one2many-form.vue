<template>
  <div>
    <!-- declare a from -->
    <Form ref="zForm" :model="model" :rules="rules" :label-width="labelWidth">
      <!-- parse fromItems -->
      <template v-for="(item, index) in formItems" v-if="item.type !== 'COLLECTION'">
        <FormItem :label="item.label" :prop="item.prop">

          <!-- I18n 前期特殊处理，后期优化 -> //TODO -->
          <template v-if="item.modelName && item.modelName === 'I18n'">
            <FormItem :label="item.label | i18n('zh_CN')" :prop="item.prop | i18n('zh_CN')">
              <Input v-model="i18n[`${item.prop}zh`]"
                     :placeholder="item.label | placeFilter(item.type) | i18n('zh_CN')"
                     :disabled="item.disabled"></Input>
            </FormItem>

            <FormItem :label="item.label | i18n('en')" :prop="item.prop | i18n('en')">
              <Input v-model="i18n[`${item.prop}en`]" :placeholder="item.label | placeFilter(item.type) | i18n('en')"
                     :disabled="item.disabled"></Input>
            </FormItem>
          </template>

          <!-- 自定义渲染 -->
          <template v-else-if="item.customRender">
            <FormItem :label="item.label" :prop="item.prop">
              <form-slot :model="model" :item="item"></form-slot>
            </FormItem>
          </template>

          <template v-else>

            <!-- String -->
            <template v-if="item.type == 'STRING'">
              <Input v-model="model[`${item.key}`]" :placeholder="item.label | placeFilter(item.type)"
                     :disabled="item.disabled"></Input>
            </template>

            <!-- Decimal -->
            <template v-if="item.type == 'DECIMAL'">
              <Input v-model="model[`${item.key}`]" :placeholder="item.label | placeFilter(item.type)"
                     :disabled="item.disabled"></Input>
            </template>

            <!-- Date or Datetime or Time -->
            <template v-if="item.type == 'DATETIME'">

              <!-- DEFAULT -> Datetime -->
              <template v-if="item.dateType == 'DEFAULT'">
                <DatePicker type="datetime" :options="datetimeOptions" v-model="model[`${item.key}`]"
                            :placeholder="item.label | placeFilter(item.type)" style="width:100%"
                            :disabled="item.disabled"></DatePicker>
              </template>
              <!-- DATE_ONLY -> Date -->
              <template v-if="item.dateType == 'DATE_ONLY'">
                <DatePicker type="date" :options="dateOptions" v-model="model[`${item.key}`]"
                            :placeholder="item.label | placeFilter(item.type)" style="width:100%"
                            :disabled="item.disabled"></DatePicker>
              </template>
              <!-- TIME_ONLY -> Time -->
              <template v-if="item.dateType == 'TIME_ONLY'">
                <TimePicker type="time" v-model="model[`${item.key}`]"
                            :placeholder="item.label | placeFilter(item.type)" style="width:100%"
                            :disabled="item.disabled"></TimePicker>
              </template>

            </template>

            <!-- Boolean -->
            <template v-if="item.type == 'BOOLEAN'">
              <i-switch v-model="model[`${item.key}`]" size="small" :disabled="item.disabled"/>
            </template>

            <!-- Model -->
            <template v-if="item.type == 'MODEL'">

              <!-- Enum -->
              <template v-if="item.modelType == 'ENUM'">
                <Select v-model="model[`${item.key}`]" :placeholder="item.label | placeFilter(item.type)" clearable
                        filterable :disabled="item.disabled">
                  <Option v-for="(v, k) in enums[`${item.enumName}`]" :value="k">
                    {{ v.name }}
                  </Option>
                </Select>
              </template>

              <!-- Common or Super -->
              <template v-if="item.modelType == 'COMMON' || item.modelType == 'SUPER'">
                <Select v-model="model[`${item.key}`]" :placeholder="item.label | placeFilter(item.type)" clearable
                        :disabled="item.disabled"
                        filterable @on-open-change="onOpenChange(item.resourcePath, index, $event)">
                  <template v-for="ite in item.arr">
                    <template v-if="ite != undefined && ite.hasOwnProperty('id') == true">
                      <Option :value="ite.id">{{ ite._instanceName }}</Option>
                    </template>
                  </template>
                </Select>
              </template>

            </template>
          </template>
        </FormItem>
      </template>
    </Form>

    <Tabs>

      <template v-for="(item, index) in formItems" v-if="item.type == 'COLLECTION'">
        <TabPane :label="item.label" :name="item.key">
          <Row>
            <Button @click="openMany(item.key)">新增</Button>
            <Button style="margin-left: 10px;" @click="removeSelection(item.key)">删除</Button>
          </Row>
          <z-single-table :ref="item.key" :columns="item.columns" :enums="item.enums" :rest="false"
                          :local-data="model[item.key]"
                          @on-selection-change="onRmSelectionChange(item.key, $event)"></z-single-table>
        </TabPane>
      </template>

    </Tabs>

    <template v-for="(item, index) in formItems" v-if="item.type == 'COLLECTION'">
      <Drawer :title="item.label" :closable="false" v-model="drawerShow[item.key]" width="490" placement="left"
              style="z-index: 10000;">
        <z-single-table :columns="item.columns" :enums="item.enums" :rest="true"
                        :restful-resource-path="item.resourcePath"
                        @on-selection-change="onSelectionChange(item.key, $event)"></z-single-table>
        <div class="drawer-footer">
          <Button style="margin-right: 8px" @click="cancelMany(item.key)">取消</Button>
          <Button type="primary" @click="selectMany(item.key)">确定</Button>
        </div>
      </Drawer>
    </template>

    <!-- 加载遮罩，防止重复点击提交 -->
    <Spin fix v-if="spin">提交中...</Spin>
  </div>
</template>

<script>

  import Rest from '../../libs/proton/Rest'
  import ZSingleTable from '../z-table/z-single-table'
  import FormSlot from './form-slot'

  export default {
    name: "z-one2many-form",
    components: {FormSlot, ZSingleTable},
    props: {
      formItems: {
        default() {
          return []
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
        rules: {},
        i18n: {},
        spin: false,
        id: undefined,
        rmSelection: {},
        selection: {},
        drawerShow: {},
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
        }
      }
    },
    created() {
      this.http = new Rest(this.resourcePath)
    },
    methods: {
      init(id) {
        this.initI18n()

        this.reset()

        this.initDefaultValue()

        this.initRules()
        this.id = undefined
        /* GET data if id is not empty */
        id && (this.id = id)
        id && this.get(this.id)

        this.initModelOptions()
        this.formItems.forEach(item => {
          if (item.type == 'COLLECTION') {
            this.$refs[item.key][0].setData([])
          }
        })
        console.log('form init successfully! ')
      },
      /* load data */
      get(id) {
        this.http.GET({uri: id}).then((res) => {
          if (res) {
            this.model = this.dataProp && res[this.dataProp] || res
            Object.keys(this.model).forEach(k => {
              /* one 2 one */
              if (this.model[k] instanceof Object && this.model[k].hasOwnProperty('id')) {
                let v = this.model[k]['id']
                this.model[`${k}.id`] = v
              }
            })
            this.formItems.forEach(item => {
              if (item.type == 'COLLECTION') {
                this.$refs[item.key][0].setData(this.model[item.key])
              }
            })
          }
        })
      },
      initI18n() {
        this.formItems.forEach((item) => {
          if (item.modelName && item.modelName === 'I18n') {
            this.$set(this.i18n, `${item.prop}zh`, '')
            this.$set(this.i18n, `${item.prop}en`, '')
          }
        })
      },
      /* init the form item default value */
      initDefaultValue() {
        this.formItems.forEach((item) => {
          this.model[item.key] = item.defaultValue
        })
      },
      /* init the form rules */
      initRules() {
        this.formItems.forEach((item) => {
          if (item.required) {
            this.rules[item.key] = [{
              required: item.required,
              message: item.message && item.message || '不能为空',
              trigger: 'blur'
            }]
          }
        })
      },
      /* init model select options */
      initModelOptions() {
        this.formItems.forEach((item, index) => {
          item.type == 'MODEL' && (item.modelType == 'COMMON' || item.modelType == 'SUPER') && this.pullOptions(item.resourcePath, index)
        })
      },
      onOpenChange(resourcePath, index, status) {
        if (status === true) this.pullOptions(resourcePath, index)
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
        this.$refs[key][0].setData(this.model[key])
      },
      openMany(key) {
        this.$set(this.drawerShow, key, true)
      },
      cancelMany(key) {
        this.$set(this.drawerShow, key, false)
      },
      /* pull select options with resourcePath, put into specified location */
      pullOptions(resourcePath, index) {
        let _rest = new Rest(resourcePath)
        _rest.GET({
          params: {
            page: 0,
            size: 1000
          }
        }).then((res) => {
          if (res) {
            this['formItems'][index].arr = res[this.optionDataProp]
            this.$forceUpdate()
          }
        })
      },
      /* reset form fields */
      reset() {
        this.$refs.zForm.resetFields()
        Object.keys(this.model).forEach(key => delete this.model[key])
        this.model = JSON.parse(JSON.stringify({}))
      },
      /* get valided form data */
      getFormDataIfValid() {
        this.spin = true
        let _data
        this.$refs.zForm.validate(valid => {
          if (valid) {
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
          }
        });
        return _data
      },
      /* callback after custom successful submission */
      customSubmitSuccess() {
        this.spin = false
        /* notify the parent component of the successful commit eventv */
        this.$emit('on-submit-success')
      },
      /* submit form */
      submit() {
        let _data = this.getFormDataIfValid()
        if (_data) {
          let method = 'POST'
          this.id && (method = 'PUT')
          this.http[method]({data: _data}).then(() => {
            this.spin = false
            /* notify the parent component of the successful commit eventv */
            this.$emit('on-submit-success')
          })
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
            item['disabled'] = true
          })
        } else {
          this.formItems.forEach(formItem => {
            formItem['disabled'] = true
          })
        }
      },
      enableFormItems(...items) {
        if (items.length > 0) {
          items.forEach(itemName => {
            let item = this.formItems.find(item => item.prop == itemName)
            delete item['disabled']
          })
        } else {
          this.formItems.forEach(formItem => {
            delete formItem['disabled']
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
