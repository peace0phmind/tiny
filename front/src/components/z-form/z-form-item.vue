<template>
  <div style="display: inline">
    <!-- I18n 前期特殊处理，后期优化 -> //TODO -->
    <template v-if="item.modelName && item.modelName === 'I18n'">
      <FormItem :label="item.label | i18n('zh_CN')" :prop="item.prop | i18n('zh_CN')">
        <Input v-model="i18n[`${item.prop}zh`]"
               :placeholder="item.label | placeFilter(item.type) | i18n('zh_CN')"
               :disabled="disabled[item.key]"></Input>
      </FormItem>

      <FormItem :label="item.label | i18n('en')" :prop="item.prop | i18n('en')">
        <Input v-model="i18n[`${item.prop}en`]" :placeholder="item.label | placeFilter(item.type) | i18n('en')"
               :disabled="disabled[item.key]"></Input>
      </FormItem>
    </template>

    <!-- 自定义渲染 -->
    <template v-else-if="item.customRender">
      <FormItem :label="item.label" :prop="item.prop">
        <form-slot :model="model" :item="item"></form-slot>
      </FormItem>
    </template>

    <template v-else>
      <FormItem :label="item.label" :prop="item.prop">

        <!-- String -->
        <template v-if="item.type == 'STRING'">
          <template v-if="item.upload">
            <a-upload :name="item.upload.name" :multiple="item.upload.multiple" :action="item.upload.action"
                      @change="handleUploadChange($event, model, item)">
              <a-button>
                <a-icon type="upload"/>
                上传文件
              </a-button>
            </a-upload>
          </template>

          <template v-if="item.textArea">
            <Input v-model="model[`${item.key}`]" :placeholder="item.label | placeFilter(item.type)"
                   :disabled="disabled[item.key]" type="textarea"
                   :autosize="{minRows: item.textArea.minRows, maxRows: item.textArea.maxRows}"></Input>
          </template>

          <template v-else>
            <Input v-model="model[`${item.key}`]" :placeholder="item.label | placeFilter(item.type)"
                   :disabled="disabled[item.key]"></Input>
          </template>
        </template>

        <!-- Decimal -->
        <template v-if="item.type == 'DECIMAL'">
          <InputNumber v-model="model[`${item.key}`]" :placeholder="item.label | placeFilter(item.type)"
                       :disabled="disabled[item.key]" style="width: 100%"></InputNumber>
        </template>

        <!-- Date or Datetime or Time -->
        <template v-if="item.type == 'DATETIME'">

          <!-- DEFAULT -> Datetime -->
          <template v-if="item.dateType == 'DEFAULT'">
            <DatePicker type="datetime" :options="datetimeOptions" v-model="model[`${item.key}`]"
                        :placeholder="item.label | placeFilter(item.type)" style="width:100%"
                        :disabled="disabled[item.key]"></DatePicker>
          </template>
          <!-- DATE_ONLY -> Date -->
          <template v-if="item.dateType == 'DATE_ONLY'">
            <DatePicker type="date" :options="dateOptions" v-model="model[`${item.key}`]"
                        :placeholder="item.label | placeFilter(item.type)" style="width:100%"
                        :disabled="disabled[item.key]"></DatePicker>
          </template>
          <!-- TIME_ONLY -> Time -->
          <template v-if="item.dateType == 'TIME_ONLY'">
            <TimePicker type="time" v-model="model[`${item.key}`]"
                        :placeholder="item.label | placeFilter(item.type)" style="width:100%"
                        :disabled="disabled[item.key]"></TimePicker>
          </template>

        </template>

        <!-- Boolean -->
        <template v-if="item.type == 'BOOLEAN'">
          <i-switch v-model="model[`${item.key}`]" size="small" :disabled="disabled[item.key]"/>
        </template>

        <!-- Model -->
        <template v-if="item.type == 'MODEL'">

          <!-- Enum -->
          <template v-if="item.modelType == 'ENUM'">
            <Select v-model="model[`${item.key}`]" :placeholder="item.label | placeFilter(item.type)" clearable
                    filterable :disabled="disabled[item.key]">
              <Option v-for="(v, k) in enums[`${item.enumName}`]" :value="k">
                {{ v.name }}
              </Option>
            </Select>
          </template>

          <!-- Common or Super -->
          <template v-if="item.modelType == 'COMMON' || item.modelType == 'SUPER'">

            <template v-if="item.modelName !== 'I18n'">

              <!-- 树形节点特殊处理 -->
              <template v-if="item.tree">
                <Input v-model="model[`${item.key}`]" :placeholder="item.label | placeFilter(item.type)" clearable
                       readonly style="width: 100%"/>
              </template>

              <template v-else>
                <div class="selectModel" :itemProp="item.prop" style="width: 100%">
                  <Select v-model="model[`${item.key}`]" :placeholder="item.label | placeFilter(item.type)" clearable
                          :disabled="disabled[item.key]" :style="{width: `${selectModelWidth[item.prop]}px`}"
                          filterable @on-open-change="onOpenChange(item, $event)">
                    <template v-for="ite in item.arr">
                      <template v-if="ite != undefined && ite.hasOwnProperty('id') == true">
                        <Option :value="ite.id">{{ ite._instanceName }}</Option>
                      </template>
                    </template>
                  </Select>
                  <Button style="float: right" @click="openSelectModel(item.key)">···
                  </Button>
                </div>
              </template>
            </template>

          </template>

        </template>

      </FormItem>
    </template>
  </div>
</template>

<script>
  import FormSlot from './form-slot'
  import Rest from "../../libs/proton/Rest";

  export default {
    name: 'z-form-item',
    props: {
      item: {},
      model: {},
      rules: {},
      i18n: {},
      disabled: {},
      drawerShow: {},
      selectModelRender: {},
      enums: {},
      span: {},
      isTreeForm: {},
      optionDataProp: {
        default: 'content'
      }
    },
    components: {FormSlot},
    data() {
      return {
        selectModelWidth: {},
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
      this.initI18n()

      this.reset()

      this.initDefaultValue()

      this.initRules()

      this.initModelOptions()
    },
    methods: {
      initI18n() {
        let item = this.item
        if (item.modelName && item.modelName === 'I18n') {
          this.$set(this.i18n, `${item.prop}zh`, '')
          this.$set(this.i18n, `${item.prop}en`, '')
        }
      },
      reset() {
        this.$delete(this.model, this.item.key)
      },
      /* init the form item default value */
      initDefaultValue() {
        let item = this.item
        this.model[item.key] = item.defaultValue
      },
      /* init the form rules */
      initRules() {
        let item = this.item
        if (item.required) {
          this.rules[item.key] = [{
            required: item.required,
            message: item.message && item.message || '不能为空',
            trigger: 'blur'
          }]
        }
      },
      /* init model select options */
      initModelOptions() {
        let item = this.item
        item.type == 'MODEL' && (item.modelType == 'COMMON' || item.modelType == 'SUPER') && this.pullOptions(item)
      },
      /* init select model position */
      reRender() {
        let selectModelArr = document.getElementsByClassName('selectModel')
        if (selectModelArr && selectModelArr.length > 0) {
          let vm = this
          let _eleArr = []
          Array.prototype.forEach.call(selectModelArr, function (item) {
            let prop = item.getAttribute('itemProp')
            if (!_eleArr.includes(prop)) {
              /* div width */
              let width = item.clientWidth
              if (width !== 0) {
                /* btn width */
                let btnWidth = 42.69
                let selectWidth
                if (vm.isTreeForm) {
                  selectWidth = width - btnWidth - 10
                } else {
                  selectWidth = width - btnWidth - 10
                }
                vm.$set(vm.selectModelWidth, prop, selectWidth)
                console.log(width, btnWidth, selectWidth)
                _eleArr.push(prop)
              }
            }
          })
        }
      },
      /* pull select options with resourcePath, put into specified location */
      pullOptions(item) {
        let _rest = new Rest(item.resourcePath)
        _rest.GET({
          params: {
            page: 0,
            size: 1000
          }
        }).then((res) => {
          if (res) {
            item.arr = res[this.optionDataProp]
            this.$forceUpdate()
          }
        })
      },
      onOpenChange(item, status) {
        if (status === true) {
          if (item.call && typeof item.call === 'function') {

          }
          this.pullOptions(item)
        }
      },
      openSelectModel(key) {
        this.$set(this.selectModelRender, key, true)
        this.$set(this.drawerShow, key, true)
        // this.$parent.$parent.$parent.$parent.clearSelection()
      },
      handleUploadChange({file}, model, item) {
        if (file.status === 'done') {
          model[item.key] = file.response[item.upload.responseField]
        }
      },
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
