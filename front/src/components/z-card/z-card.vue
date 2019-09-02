<template>
  <div :style="card">
    <template v-if="$slots.header">
      <div :style="cardTitle">
        <slot name="header"></slot>
        <template v-if="$slots.extra">
          <div style="float: right;">
            <slot name="extra"></slot>
          </div>
        </template>
      </div>
    </template>
    <template v-if="$slots.body">
      <div :style="cardBody">
        <slot name="body"></slot>
      </div>
    </template>
    <template v-if="$slots.footer">
      <div :style="cardFooter">
        <slot name="footer"></slot>
      </div>
    </template>
  </div>
</template>

<script>
  export default {
    name: "z-card",
    props: {
      borderColor: {
        default() {
          return 'rgba(8, 8, 8, 0.2))'
        }
      },
      height: {
        default() {
          return '200px'
        }
      },
      width: {
        default() {
          return '300px'
        }
      }
    },
    data() {
      return {
        card: {
          border: `1px solid ${this.borderColor}`,
          height: this.height,
          width: this.width,
          borderRadius: '4px',
          boxShadow: '0 2px 12px 0 rgba(0, 0, 0, 0.1)'
        },
        cardTitle: {
          borderBottom: `1px solid rgb(76, 72, 72)`,
          lineHeight: '24px',
          paddingLeft: '10px'
        },
        cardBody: {
          padding: '10px'
        },
        cardFooter: {
          borderTop: `1px solid rgb(76, 72, 72)`,
          lineHeight: '24px',
          paddingLeft: '10px'
        }
      }
    }
  }
</script>

<style scoped>
</style>
