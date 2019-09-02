<template>
  <div ref="dom_bar" class="charts chart-bar"></div>
</template>

<script>
import echarts from 'echarts'
import tdTheme from './theme.json'
import { on, off } from '@/libs/tools'
echarts.registerTheme('tdTheme', tdTheme)
export default {
  name: 'ChartBar',
  props: {
    text: String,
    value: {
      type: Object,
      default: {}
    }
  },
  data () {
    return {
      dom: null
    }
  },
  methods: {
    resize () {
      this.dom.resize()
    },
    drawEcharts() {
      let visiteVolume = echarts.init(this.$refs.dom_bar, 'tdTheme');
      if(visiteVolume != null){
        visiteVolume.clear();
      }
      const option = {
        tooltip : {
          trigger: 'axis',
          axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        legend: {
          // data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎','百度','谷歌','必应','其他']
          align: 'left',
          left: 10
        },
        title: {
          text: this.text,
          x: 'center'
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis : [
          {
            type : 'category',
            data : this.value.xData
          }
        ],
        yAxis : [
          {
            type : 'value'
          }
        ],
        series : this.value.sData
      };
      this.dom = echarts.init(this.$refs.dom_bar, 'tdTheme')
      this.dom.setOption(option)
      on(window, 'resize', this.resize)
    }
  },
  mounted () {
    // if(this.value.length > 0) this.drawEcharts();
    // this.drawEcharts();
    // this.$nextTick(() => {
    //   let xAxisData = Object.keys(this.value)
    //   let seriesData = Object.values(this.value)
    //   let option = {
    //     title: {
    //       text: this.text,
    //       subtext: this.subtext,
    //       x: 'center'
    //     },
    //     xAxis: {
    //       type: 'category',
    //       data: xAxisData
    //     },
    //     yAxis: {
    //       type: 'value'
    //     },
    //     series: [{
    //       data: seriesData,
    //       type: 'bar'
    //     }]
    //   }
    //   this.dom = echarts.init(this.$refs.dom, 'tdTheme')
    //   this.dom.setOption(option)
    //   on(window, 'resize', this.resize)
    // })
  },
  beforeDestroy () {
    off(window, 'resize', this.resize)
  },
  // watch: {
  //   'value'(freshData){
  //       this.drawEcharts();
  //
  //   }
  // }
}
</script>
