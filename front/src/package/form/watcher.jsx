import {findObjChange} from '../lib/tools'
import _ from 'lodash'

export default {
  watch: {
    formModel: {
      handler(newFormModel, oldFormModel) {
        if (this.id) { //修改
          if (Object.keys(this.previousFormModel).length > 0) {
            if (Object.keys(newFormModel).length !== Object.keys(this.previousFormModel).length || !_.isEqual(this.previousFormModel, newFormModel)) {
              const changed = findObjChange(newFormModel, this.previousFormModel)
              if (changed) {
                Object.entries(changed).forEach(([k, v]) => this.changedModel[k] = JSON.parse(JSON.stringify(v)))
                this.disabledSubmit = false
              }
            } else {
              this.disabledSubmit = true
            }
          } else this.disabledSubmit = true
        } else { //新增
          if (!_.isEqual({}, newFormModel))
            this.disabledSubmit = false
          else
            this.disabledSubmit = true
        }
      },
      deep: true
    }
  },
}
