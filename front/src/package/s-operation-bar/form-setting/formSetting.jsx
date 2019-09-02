import Rest from '../../lib/Rest'
import * as Types from '../../_util/javaTypes.js'
import * as RenderTypes from '../../_util/renderType'
import draggable from 'vuedraggable'
import '../index.less'

export default {
  name: 'form-setting',

  props: {
    formLayout: {},
    formItems: {},
    relativePath: {}
  },

  data() {
    return {}
  },

  components: {
    draggable
  },

  created() {
    this.restTemplate = new Rest('dev', this.$apiURL)
  },

  methods() {

  },

  render() {

  }
}
