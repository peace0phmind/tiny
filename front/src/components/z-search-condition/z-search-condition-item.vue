<template>
  <FormItem :label="item.label">

    <!-- 如果是String类型 -->
    <template v-if="item.type == 'STRING'">
      <Input v-model="queryParams[`${item.key}`]" :placeholder="item.label" style="width: 280px"></Input>
    </template>

    <!--如果是Decimal类型 -->
    <!--            <template v-if="item.type == 'DECIMAL'">-->
    <!--              <InputNumber v-model="queryParams[`${item.key}`]" :precision="item.precision"-->
    <!--                           :placeholder="item.label"></InputNumber>-->
    <!--              - -->
    <!--              <InputNumber v-model="queryParams[`${item.key}`]" :precision="item.precision"-->
    <!--                           :placeholder="item.label"></InputNumber>-->
    <!--            </template>-->

    <!-- 如果是Datetime类型 -->


    <template v-if="item.type == 'DATETIME'">

      <!-- DEFAULT -> Datetime -->
      <template v-if="item.dateType == 'DEFAULT'">
        <DatePicker v-model="queryParams[`${item.key}`]" type="datetimerange"
                    :placeholder="item.label" style="width: 280px"></DatePicker>
      </template>

      <!-- DATE_ONLY -> Date -->
      <template v-else-if="item.dateType == 'DATE_ONLY'">
        <DatePicker v-model="queryParams[`${item.key}`]" type="daterange"
                    :placeholder="item.label" style="width: 280px"></DatePicker>
      </template>


      <!-- TIME_ONLY -> Time -->
      <template v-else-if="item.dateType == 'TIME_ONLY'">
        <TimePicker v-model="queryParams[`${item.key}`]" type="timerange"
                    :placeholder="item.label" style="width: 280px"></TimePicker>
      </template>

    </template>


    <!-- 如果是BOOLEAN类型 -->
    <template v-if="item.type == 'BOOLEAN'">
      <Select v-model="queryParams[`${item.key}`]" :placeholder="item.label" clearable filterable
              style="width: 280px">
        <Option value="true">是</Option>
        <Option value="false">否</Option>
      </Select>
    </template>

    <!-- 如果是MODEL类型 -->
    <template v-if="item.type == 'MODEL'">

      <!-- 枚举处理 -->
      <template v-if="item.modelType == 'ENUM'">
        <Select v-model="queryParams[`${item.key}`]" :placeholder="item.label" clearable
                filterable style="width: 280px">
          <Option v-for="(v, k) in enums[`${item.enumName}`]" :value="k">{{ v.name }}</Option>
        </Select>
      </template>

      <!-- 对象类型 -->
      <template v-if="item.modelType == 'COMMON' || item.modelType == 'SUPER'">
        <Select v-model="queryParams[`${item.key}`]" :placeholder="item.label" clearable
                filterable style="width: 280px"
                @on-open-change="onOpenChange($event, item)">
          <template v-for="ite in item.arr">
            <template v-if="ite != undefined && ite.hasOwnProperty('id') == true">
              <Option :value="ite.id">{{ ite._instanceName }}</Option>
            </template>
          </template>
        </Select>
      </template>

    </template>
  </FormItem>
</template>

<script>
  import Rest from '../../libs/proton/Rest'

  export default {
    name: 'z-search-condition-item',
    props: {
      item: {},
      queryParams: {},
      enums: {},
      optionDataProp: {
        default() {
          return 'content'
        }
      }
    },
    data() {
      return {
        dateOptions: {
          shortcuts: [
            {
              text: '近一周',
              value() {
                const end = new Date();
                const start = new Date();
                start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                return [start, end];
              }
            },
            {
              text: '近一个月',
              value() {
                const end = new Date();
                const start = new Date();
                start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                return [start, end];
              }
            },
            {
              text: '近三个月',
              value() {
                const end = new Date();
                const start = new Date();
                start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                return [start, end];
              }
            }
          ]
        }
      }
    },
    created() {
      this.item.type == 'MODEL' && (this.item.modelType == 'COMMON' || this.item.modelType == 'SUPER') && this.pullOptions(this.item)
    },
    methods: {
      pullOptions(item) {
        let {resourcePath} = item
        let _rest = new Rest(resourcePath)
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
      onOpenChange(status, item) {
        if (status === true) {
          this.pullOptions(item)
        }
      }
    }
  }
</script>

<style scoped>

</style>
