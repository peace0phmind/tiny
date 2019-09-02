import Rest from '../lib/Rest'
import createNormalFormItemNode from './createNormalFormItemNode'
import init from './init.jsx'
import pluginRules from './pluginRules.jsx'
import util from './util.jsx'
import getFormDataIfValid from './getFormDataIfValid.jsx'
import events from './events.jsx'
import createTabFormItemNode from './createTabFormItemNode.jsx'
import watcher from './watcher.jsx'
import * as viewMode from '../_util/viewMode.js'

export default {
  name: 'zyx-form',

  provide() {
    return {
      zyxForm: this
    }
  },

  mixins: [
    createNormalFormItemNode,
    init,
    pluginRules,
    util,
    getFormDataIfValid,
    events,
    createTabFormItemNode,
    watcher
  ],

  props: {
    formItems: {},
    labelWidth: {
      type: Number,
      default: 80
    },
    formLayout: {
      type: Array,
      default() {
        return []
      }
    },
    labelPosition: {
      type: String,
      default: 'right',
      validator: (v) => ['left', 'right', 'top'].includes(v)
    },
    resourcePath: {
      required: true
    },
    formProps: {
      type: Object,
      default() {
        return {
          list: 'content',
          total: 'totalElements',
          id: 'id'
        }
      }
    },
    readonly: {default: false},
    enums: {
      default() {
        return {}
      }
    },
  },

  data() {
    return {
      id: undefined,
      normalFormItems: JSON.parse(JSON.stringify(this.formItems)).filter(item => !item.collection || (item.modelType === 'ENUM' && item.collection)),
      tabFormItems: JSON.parse(JSON.stringify(this.formItems)).filter(item => item.collection && item.modelType !== 'ENUM' && item.viewMode === viewMode.FORM_TABLE),
      formModel: {},
      previousFormModel: {},
      changedModel: {},
      rules: {},
      modelSelection: {},
      disabled: {},
      readOnly: {},
      disabledSubmit: true,
      loadingSubmit: false,
      activeTab: undefined
    }
  },

  computed: {
    restTemplate() {
      return new Rest(this.resourcePath, this.$apiURL)
    }
  },

  render() {

    const normalFormItemNode = this.createNormalFormItemNode()

    const tabFormItems = this.createTabFormItems()

    const operationSlot = this.$scopedSlots.operation

    let operation

    if (operationSlot) operation = operationSlot()
    else {
      operation = (
        <Row {...{style: {textAlign: 'right', padding: '8px 5px'}}}>
          <Button {...{style: {marginRight: '8px'}}} onClick={this.cancel}>取消</Button>
          <Button type={'primary'} loading={this.loadingSubmit} disabled={this.disabledSubmit}
                  onClick={this.save}>{this.loadingSubmit && '提交中' || '提交'}</Button>
        </Row>
      )
    }

    return (
      <div>
        <Form ref={'form'} attrs={{model: this.formModel, rules: this.rules}} label-position={this.labelPosition}
              label-width={this.labelWidth}>
          {normalFormItemNode}
        </Form>
        {tabFormItems}
        {!this.readonly && operation}
      </div>
    )
  },

  methods: {
    cancel() {
      this.$emit('on-cancel')
    },

    save() {
      this.$emit('on-save')
    }
  }
}
