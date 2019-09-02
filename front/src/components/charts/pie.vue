<template>
  <div ref="dom" class="charts chart-pie"></div>
</template>

<script>
import echarts from 'echarts'
import tdTheme from './theme.json'
import { on, off } from '@/libs/tools'
echarts.registerTheme('tdTheme', tdTheme)
export default {
  name: 'ChartPie',
  props: {
    value: Array,
    text: String,
    subtext: String
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
      let legend = this.value.map(_ => _.name)
      this.dom = echarts.init(this.$refs.dom, 'tdTheme');
      if(this.dom != null){
        this.dom.clear()
      }

      const option = {
        backgroundColor: '#fff',
        tooltip : {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        title: {
          text: this.text,
          subtext: this.subtext,
          x: 'center'
        },
        legend: {
          orient: 'vertical',
          left: 'left',
          data: legend
        },
        series: [
          {
            name:'',
            type:'pie',
            radius : '80%',
            center: ['50%', '50%'],
            data: this.value
            // ,
            // label: { //饼图图形的文本标签
            //   normal: {  //下同，normal指在普通情况下样式，而非高亮时样式
            //     textStyle: {
            //       color: 'rgba(255, 255, 255, 0.3)'
            //     }
            //   }
            // }
            // ,
            // labelLine: {  //引导线样式
            //   normal: {
            //     lineStyle: {
            //       color: 'rgba(255, 255, 255, 0.3)'
            //     },
            //     smooth: 0.5, //0-1，越大越平滑弯曲
            //     length: 10,  //从块到文字的第一段长
            //     length2: 20  //拐弯到文字的段长
            //   }
            // }
          }
        ]
      };

      this.dom = echarts.init(this.$refs.dom, 'tdTheme')
      this.dom.setOption(option)
      on(window, 'resize', this.resize)
    }
  },
  mounted () {
    if(this.value.length > 0) this.drawEcharts();
    // this.$nextTick(() => {
    //   let legend = this.value.map(_ => _.name)
    //   let option = {
    //     title: {
    //       text: this.text,
    //       subtext: this.subtext,
    //       x: 'center'
    //     },
    //     tooltip: {
    //       trigger: 'item',
    //       formatter: '{a} <br/>{b} : {c} ({d}%)'
    //     },
    //     legend: {
    //       orient: 'vertical',
    //       left: 'left',
    //       data: legend
    //     },
    //     series: [
    //       {
    //         type: 'pie',
    //         radius: '55%',
    //         center: ['50%', '60%'],
    //         data: this.value,
    //         itemStyle: {
    //           emphasis: {
    //             shadowBlur: 10,
    //             shadowOffsetX: 0,
    //             shadowColor: 'rgba(0, 0, 0, 0.5)'
    //           }
    //         }
    //       }
    //     ]
    //   }
    //   this.dom = echarts.init(this.$refs.dom, 'tdTheme')
    //   this.dom.setOption(option)
    //   on(window, 'resize', this.resize)
    // })
  },
  beforeDestroy () {
    off(window, 'resize', this.resize)
  },
  watch: {
    'value'(freshData){
      if(freshData != null && freshData.length > 0){
        this.drawEcharts();
      }
    }
  }
}
</script>
