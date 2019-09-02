import Rest from '../lib/Rest'
import * as Types from '../_util/javaTypes.js'
import * as utils from '../_util/util.js'
import { getCurrentPageTitle } from '../lib/tools.js'
import * as operationType from '../_util/operationType.js'
import XLSX from 'xlsx'
import _ from 'lodash'
import { copyObjectRenameAttrInArrRecursive } from '../lib/tools'

const modes = {
  modal: 'Modal',
  drawer: 'Drawer'
}

export default {
  props: {
    readonly: {
      default: false
    }
  },
  data () {
    return {
      modes
    }
  },
  created () {
    const { restfulResourcePath } = this.meta
    this.restTemplate = new Rest(restfulResourcePath, this.$apiURL)
    if (this.shouldLoad) {
      this.load()
    }
  },
  mounted () {
    this.width = document.documentElement.clientWidth * 0.8
    if (this.afterMounted) this.afterMounted()
  },
  // watch: {
  //   '$route': 'load'
  // },
  beforeRouteEnter (to, from, next) {
    next(vm => {
      vm.$refs.search.reset()
      vm.load()
    })
  },
  methods: {
    changeSearch (param) {
      this.queryParams = param
      this.load()
    },
    onPageChange (page) {
      this.page = page
      this.load()
    },
    onPageSizeChange (size) {
      this.size = size
      this.load()
    },
    onselectionchange (selection) {
      this.$emit('on-check', selection)
    },
    onSortChange ({ prop, order }) {
      this.load({ sort: { prop, order } })
    },
    load (option) {
      if (this.beforeLoad) this.beforeLoad()

      let _param = this._fetchSearchParam()

      if (option) {
        const { sort } = option
        if (sort) {
          _param.sort = `${sort.prop},${sort.order}`
        }
      }

      _param = { params: _param }

      const handler = res => {
        const _list = this.meta.props.list
        this.data = _list && res[_list] || res
        const list = _list && res[_list] || res
        const _data = []

        if (option) {
          const { emit, operationType } = option
          if (emit) {
            this.$emit('on-item-change', { data: list, operationType, front: false })
          }
        }

        let _total = this.meta.props.total
        this.total = res[_total]

      }
      this.restTemplate
          .GET({ ..._param })
          .then(handler)
      if (this.afterLoad) this.afterLoad()
    },

    // fecth search param from search condition component and join pageable
    _fetchSearchParam () {
      const column = this.meta._columns.find(column => column.defaultSort)

      const { meta: { searchParams } } = this.$route

      let _param = {
        page: this.page - 1,
        size: this.size,
        sort: column && `${column.slot},desc` || 'id,desc',
        ...this.queryParams,
        ...this.extraParams
      }

      if (searchParams) {
        Object.entries(searchParams).forEach(([k, v]) => {
          const searchItem = this.meta._searchItems.find(item => item.key === k)
          if (searchItem) {
            const { type, modelType, tree, resourcePath, primaryKeyType } = searchItem
            if (Types.isString(type)) {
              if (_param[k] === undefined) _param[`${k}.contains`] = v
              this.$nextTick(() => {
                this.$refs.search.setValue(k, v)
              })
            } else if (Types.isBoolean(type)) {
              if (_param[k] === undefined) _param[`${k}.equals`] = Boolean(v)
              this.$nextTick(() => {
                this.$refs.search.setValue(k, Boolean(v))
              })
            } else if (Types.isDecimal(type)) {
              const value = _.split(v, ',', 2).map(value => Number(v))
              if (_param[k] === undefined) {
                _param[`${k}.greaterOrEqualThan`] = value[0]
                _param[`${k}.lessThan`] = value[1]
              }
              this.$nextTick(() => {
                this.$refs.search.setValue(k, value)
              })
            } else if (Types.isModel(type)) {
              if (Types.isCommonOrSuperModel(type, modelType)) {
                if (tree) {
                  let _rest = new Rest(resourcePath, this.$apiURL)
                  _rest.GET({
                    uri: `tree`,
                    params: { level: 10 }
                  }).then((res) => {
                    const arr = copyObjectRenameAttrInArrRecursive({
                      src: res,
                      correspondence: { _instanceName: 'label', id: 'value', _children: 'children' }
                    })
                    if (_param[k] === undefined) _param[`${k}.in`] = arr
                    this.$nextTick(() => {
                      this.$refs.search.setValue(k, arr)
                    })
                  })
                } else {
                  if (_param[k] === undefined) _param[`${k}.in`] = v
                  this.$nextTick(() => {
                    if (primaryKeyType === 'Long') {
                      this.$refs.search.setValue(k, Number(v))
                    }else {
                      this.$refs.search.setValue(k, v)
                    }
                  })
                }
              } else {
                let value
                if (v.includes(',')) {
                  value = _.split(v, ',')
                } else {
                  value = v
                }
                if (_param[k] === undefined) _param[`${k}.in`] = value
                this.$nextTick(() => {
                  this.$refs.search.setValue(k, value)
                })
              }
            } else if (Types.isDateType(type)) {
              if (_param[k] === undefined) {
                if (v.includes(',')) {
                  const value = _.split(v, ',', 2)
                  _param[`${k}.greaterOrEqualThan`] = value[0]
                  _param[`${k}.lessThan`] = value[1]

                  this.$nextTick(() => {
                    this.$refs.search.setValue(k, [new Date(Date.parse(value[0].replace(/-/g, '/'))), new Date(Date.parse(value[1].replace(/-/g, '/')))])
                  })
                }
              }
            }
          }
        })
      }
      return _param
    },
    handleAdd () {
      this.title = '新增'
      this.id = undefined
      this.modalShow = true
      setTimeout(() => {
        this.$refs.form.init()
        if (this.extraParams) {
          const extra = this.extraParams
          let k = Object.keys(extra).find(key => key.includes('.equals'))
          if (k) {
            const slot = k.substring(0, k.length - 9)
            const key = `${slot}.id`
            const v = extra[k]
            const column = this.meta._columns.find(column => column.slot === slot)
            if (column.tree) {
              const { resourcePath, primaryKey } = column
              const _rest = new Rest(resourcePath, this.$apiURL)
              _rest.GET({ uri: `${v}/_parent` }).then(tree => {
                const arr = []
                const flatIds = (tree, arr) => {
                  arr.push(tree[primaryKey])
                  if (tree._children && tree._children.length) {
                    flatIds(tree._children[0], arr)
                  }
                }
                flatIds(tree, arr)
                this.$refs.form.setValue(slot, arr)
              })
            } else {
              this.$refs.form.setValue(slot, v)
            }
            this.$refs.form.disableFormItems(slot)
          }
        }
        if (this.afterAdd) this.afterAdd()
      }, 100)
    },
    handleEdit (id) {
      this.title = '编辑'
      this.id = id
      this.modalShow = true
      setTimeout(() => {
        this.$refs.form.init(id)
        if (this.afterEdit) this.afterEdit(id)
      }, 100)
    },
    cancel () {
      if (this.beforeCancel && this.beforeCancel() === false) return

      if (Object.keys(this.$refs.form.changed).length > 0) {
        this.$Modal.confirm({
          title: '警告',
          content: `确定取消编辑吗？`,
          onOk: () => {
            this.modalShow = false
          },
          onCancel: () => {
            this.modalShow = true
          }
        })
      } else {
        this.modalShow = false
      }
      if (this.afterCancel) this.afterCancel()
    },
    submit () {
      if (this.beforeSubmit && this.beforeSubmit() === false) return

      const vm = this
      const _data = this.$refs.form.getFormDataIfValid()
      if (_data) {
        this.$refs.form.loadingSubmit = true
        if (this.localMode) {
          // 提交到本地
          this.modalShow = false
          this.$Message.success('操作成功')
          this.data.push(_data)
          this.$emit('on-item-change', {
            data: this.data,
            operationType: this.extraParams.id === -2 ? operationType.EDIT : operationType.ADD,
            front: true
          })
          this.$refs.form.loadingSubmit = false
        } else {
          // 提交到后台
          this.submitLoading = true
          let method = 'POST'
          this.id && (method = 'PUT')
          if (this.extraParams && Object.keys(this.extraParams).length > 0) {
            Object.keys(this.extraParams).forEach(key => {
              const k = key.split('.')[0].substr(0, key.split('.')[0].length - 2)
              _data[k] = {}
              _data[k].id = this.extraParams[key]
            })
          }
          let extra = {}
          if (this.extraSubmitParams) extra = { ...this.extraSubmitParams }
          this.restTemplate[method]({ data: _data, params: { ...extra } }).then((res) => {
            this.submitLoading = false
            this.modalShow = false
            this.$Message.success('操作成功')
            this.$refs.form.loadingSubmit = false
            this.load({
              emit: true,
              operationType: this.extraParams.id === -2 ? operationType.EDIT : operationType.ADD
            })
            if (this.afterSubmit) this.afterSubmit()
          }, (error) => {
            this.$Message.error('提交失败')
            this.$refs.form.loadingSubmit = false
          })
        }
      } else {
        this.$Message.error('请填写完成，再提交!')
      }
    },
    handleRemove (id, index) {
      this.$Modal.confirm({
        title: '警告',
        content: `确定删除吗？`,
        onOk: () => {
          if (this.localMode) {
            // 本地删除
            this.$Message.success('删除成功')
            this.data.splice(index, 1)
            this.$emit('on-item-change', {
              data: this.data,
              operationType: operationType.REMOVE,
              front: true
            })
          } else {
            // 后台删除
            this.restTemplate.DELETE({ uri: id }).then(() => {
              this.$Message.success('删除成功')
              this.load({ emit: true, operationType: operationType.REMOVE })
            })
          }
        }
      })
    },
    handleRemoveAll () {
      const selection = this.$refs.table.getSelection()
      if (selection.length > 0) {
        let ids = selection.map(s => s[this.meta.props.id])
        this.$Modal.confirm({
          title: '警告',
          content: `已选择 ${selection.length} 项，确定删除吗？`,
          onOk: () => {
            if (this.localMode) {
              // 本地删除
              this.$Message.success('暂不支持此操作')
              // for(let id of ids) {
              //   this.data = util.removeFromArr(this.data, {id: id})
              // }
              // this.$emit('on-remove-success', this.data)
            } else {
              // 后台删除
              this.restTemplate.DELETE({ uri: ids }).then(() => {
                this.$Message.success('删除成功')
                this.load()
              })
            }
          }
        })
      } else {
        this.$Message.warning('请勾选要删除的数据!')
      }
    },
    frontExport: function (fileTitle) {
      if (this.data.length == 0) {
        this.$Message.warning('列表暂无数据!')
        return
      }
      //将标题与数据结合
      let dataArray = new Array()
//      console.log(this.data);
//      console.log(this.meta._columns);
      for (let i = 0; i < this.data.length; i++) {
        let dataJson = {}
        let exportData = this.data[i]
        for (let j = 1; j < this.meta._columns.length; j++) {
          let column = this.meta._columns[j]
          let attr = column.title
          // add Attr when there is a value in slot
          if (exportData[column.slot] || exportData[column.slot] == 0) {
            dataJson[attr] = exportData[column.slot]
          }

          // 对布尔值进行处理
          if (typeof exportData[column.slot] == 'boolean') {
            dataJson[attr] = exportData[column.slot] ? '是' : '否'
          }
          // 对对象进行处理
          else if (typeof exportData[column.slot] == 'object') {
            dataJson[attr] = exportData[column.slot]._instanceName
          }
          // 对字符串进行处理
          if (typeof exportData[column.slot] == 'string') {
            dataJson[attr] = exportData[column.slot]
            // 对时间字符串进行处理
            if (column.type == 'DATETIME') {
              if (column.dateType == 'DATE_ONLY') {
                continue
              }
              let date = new Date(exportData[column.slot])
              dataJson[attr] = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate() + ' ' + date.getHours() + ':' + date.getMinutes() + ':' + date.getSeconds()
//              console.log(dataJson[attr]);
              continue
            }
          }
          // 对枚举进行处理
          if (column.modelType == 'ENUM') {

            let enumData = this.meta._enums[column.enumName]
            let status = enumData[exportData[column.slot]]
            if (status) {
              dataJson[attr] = status.name
            }
          }

        }
        dataArray.push(dataJson)
      }

//      console.log(dataArray);
      const wb = { SheetNames: ['Sheet1'], Sheets: {}, Props: {} }
      const wopts = { bookType: 'xlsx', bookSST: false, type: 'binary' }

      let title = getCurrentPageTitle(this) + '的清单列表'

      if (typeof fileTitle == 'string') {
        title = fileTitle
      }
      wb.Sheets['Sheet1'] = XLSX.utils.json_to_sheet(dataArray)//通过json_to_sheet转成单页(Sheet)数据
      saveAs(new Blob([s2ab(XLSX.write(wb, wopts))], { type: 'application/octet-stream' }), title + '.' + (wopts.bookType == 'biff2' ? 'xls' : wopts.bookType))
    },
  }
}

function saveAs (obj, fileName) {//当然可以自定义简单的下载文件实现方式
  let tmpa = document.createElement('a')
  tmpa.download = fileName || '下载'
  tmpa.href = URL.createObjectURL(obj) //绑定a标签
  tmpa.click() //模拟点击实现下载
  setTimeout(function () { //延时释放
    URL.revokeObjectURL(obj) //用URL.revokeObjectURL()来释放这个object URL
  }, 100)
}

function s2ab (s) {
  if (typeof ArrayBuffer !== 'undefined') {
    let buf = new ArrayBuffer(s.length)
    let view = new Uint8Array(buf)
    for (let i = 0; i != s.length; ++i) view[i] = s.charCodeAt(i) & 0xFF
    return buf
  } else {
    let buf = new Array(s.length)
    for (let i = 0; i != s.length; ++i) buf[i] = s.charCodeAt(i) & 0xFF;
    return buf;
  }
}
