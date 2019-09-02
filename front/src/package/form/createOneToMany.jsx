import createOneToManyBidirectionalNotExist from './createOneToManyBidirectionalNotExist'
import createOneToManyBidirectionalExist from './createOneToManyBidirectionalExist'

export default {

  mixins: [createOneToManyBidirectionalNotExist, createOneToManyBidirectionalExist],

  methods: {
    createOneToManyBidirectional({item}) {
      const {isExsit} = item
      if (isExsit) {
        return this.createOneToManyBidirectionalExist({item})
      } else {
        return this.createOneToManyBidirectionalNotExist({item})
      }

    },

    createOneToManyNotBidirectional({item}) {

    }
  }
}
