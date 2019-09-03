import Rest from '../lib/Rest.js'
import * as Types from '../_util/javaTypes.js'
import _ from 'lodash'

export default {
  methods: {
    pluginRules() {
      this.normalFormItems.forEach(item => {
        const {prop, valid, required, unique, equalsTo, minLength, maxLength, label, max, min, precision, type} = item

        this.$set(this.rules, prop, [])

        if (required) {
          this.rules[prop].push({
            required,
            message: `请填写${label}`,
            trigger: 'change'
          })
        }

        if (unique) {
          const restTemplate = new Rest(this.resourcePath, this.$apiURL)

          this.rules[prop].push({
            validator: (rule, value, callback) => {
              if (value && value !== this.previousFormModel[prop]) {
                const params = {}
                if (Types.isCommonOrSuperModel(item.type, item.modelType)) {
                  params[`${item.prop}${_.upperFirst(item.primaryKey)}.equals`] = value
                } else {
                  params[`${item.prop}.equals`] = value
                }

                restTemplate.GET({uri: 'count', params}).then((count) => {
                  if (count > 0) {
                    callback(new Error('已经存在'))
                  } else {
                    callback()
                  }
                }, (error) => {
                  callback(new Error('服务器错误，请稍后再试'))
                })
              }
            },
            trigger: 'blur'
          })

        }

        if (minLength) {
          this.rules[prop].push({
            min: minLength,
            message: `最小长度为${minLength}`,
            trigger: 'change'
          })
        }

        if (maxLength) {
          this.rules[prop].push({
            max: maxLength,
            message: `最大长度为${maxLength}`,
            trigger: 'change'
          })
        }

        if (equalsTo) {
          this.rules[prop].push({
            validator: (rule, value, callback) => {
              if (value) {
                if (value === this.formModel[equalsTo]) {
                  callback()
                } else {
                  const formItem = this.normalFormItems.find(item => item.prop === equalsTo)
                  callback(new Error(`必须与${formItem.label}一致`))
                }
              }
            },
            trigger: 'change'
          })
        }

        if (Types.isDecimal(type)) {
          if (max) {
            this.rules[prop].push({
              validator: (rule, value, callback) => {
                if (value) {
                  if (Number(value) > max) {
                    callback(`最大值不得超过${max}`)
                  } else {
                    callback()
                  }
                }
              },
              trigger: 'change'
            })
          }

          if (min) {
            this.rules[prop].push({
              validator: (rule, value, callback) => {
                if (value) {
                  if (Number(value) < min) {
                    callback(`最小值不得小于${min}`)
                  } else {
                    callback()
                  }
                }
              },
              trigger: 'change'
            })
          }

          if (precision) {
            this.rules[prop].push({
              validator: (rule, value, callback) => {
                if (value) {
                  if (value.includes('.')) {
                    const [d, p] = value.split('.')
                    if (p.length === precision) {
                      callback()
                    } else {
                      callback(new Error(`小数点精度必须为${precision}位`))
                    }
                  } else {
                    callback(new Error(`小数点精度必须为${precision}位`))
                  }
                }
              },
              trigger: 'change'
            })
          }
        }

        if (valid) {
          const {type, validator, asyncValidator, pattern} = valid

          if (type) {
            this.rules[prop].push({
              type: type,
              message: '格式不正确',
              trigger: 'change'
            })
          }

          if (pattern) {
            this.rules[prop].push({
              pattern: pattern,
              message: '格式不正确',
              trigger: 'change'
            })
          }

          if (validator) {
            this.rules[prop].push({
              validator: validator,
              formModel: this.formModel
            })
          }

          if (asyncValidator) {
            this.rules[prop].push({
              asyncValidator: asyncValidator,
              formModel: this.formModel
            })
          }
        }

      })
    },
  }
}
