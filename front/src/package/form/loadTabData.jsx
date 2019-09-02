import Rest from '../lib/Rest'

export default {
  methods: {
    changePage(page, item) {
      this.loadToKnowFrom(page, item)
    },
    loadToKnowFrom(page, item) {
      const {resourcePath, prop, referModelAttribute} = item
      const _rest = new Rest(resourcePath, this.$apiURL)

      const parentParam = {}
      if (this.id) {
        parentParam[`${referModelAttribute}.equals`] = this.id
      }

      _rest.GET({
        params: {
          page: page - 1,
          size: 10,
          ...parentParam
        }
      }).then(res => {
        this[`${prop}Data`] = res.content
        this[`${prop}Total`] = res.totalElements
      })
    },
    loadAllToKnowFrom(item) {
      const {resourcePath, prop, referModelAttribute} = item
      const _rest = new Rest(resourcePath, this.$apiURL)

      const parentParam = {}
      if (this.id) {
        parentParam[`${referModelAttribute}.equals`] = this.id
        _rest.GET({
          params: {...parentParam}
        }).then(res => {
          this[`${item.prop}Data`] = res.content
          this.$forceUpdate()
        })
      }
    }
  }
}
