import Rest from '../lib/Rest'
import html2canvas from 'html2canvas'

const operation = {
  append: 'append',
  insertRoot: 'insertRoot',
  insertBefore: 'insertBefore',
  insertAfter: 'insertAfter'
}

export default {
  watch: {
    'screenHeight': function () {
      !this.readonly && this.elasticHeight()
    }
  },
  props: {
    readonly: {default: false},
    searchable: {default: true},
    selecting: {default: false},
    checkedNodes: {
      default() {
        return []
      }
    },
    extraParams: {},
    multipleSelect: {default: true}
  },
  data() {
    return {
      orgTreeLevel: 5,
      submitToServer: true, // 提交到服务器
    }
  },
  created() {
    const {restfulResourcePath, props} = this.meta
    this.restTemplate = new Rest(restfulResourcePath, this.$apiURL)
    this.changeSearch()
  },
  computed: {
    bodyElasticHeight() {
      return document.documentElement.clientHeight * 0.54
    }
  },
  mounted() {
    !this.readonly && this.elasticHeight()
    this.$refs.editForm.init()
    this.$refs.editForm.disableFormItems(true)
    this.orgTreeModalWidth = document.documentElement.clientWidth * 0.8
    const {props} = this.meta
    const checkedKeys = this.checkedNodes.map(node => node[props.id])

    const checkNode = (nodes) => {
      for (let node of nodes) {
        if (checkedKeys.includes(node.key)) {
          node.checked = true
        }
        if (node.childNodes && node.childNodes.length > 0) {
          checkNode(node.childNodes)
        }
      }
    }

    // setTimeout(()=> {checkNode(this.$refs.tree.store.root.childNodes)}, 500)
    setTimeout(() => {
      Object.keys(this.$refs.tree.store.nodesMap).forEach(id => {
        if (checkedKeys.includes(id)) {
          this.$set(this.$refs.tree.store.nodesMap[id], 'checked', true)
        }
      })
    }, 500)

  },
  methods: {
    loadNode(node, resolve) {
      if (node.level !== 0) {

        let children = node.getChildren(true)
        if (children && children.length > 0) {
          resolve(children)
          for (let c of children) {
            let childNode = this.$refs.tree.getNode(c)
            if (this.expandAll) {
              let children = childNode.getChildren()
              if (children && children.length > 0) {
                childNode.expand()
              }
            } else {
              if (childNode.level <= this.defaultLevel) {
                childNode.expand()
              }
            }
          }
        } else if (node.isLeafByUser) {
          return resolve([])
        } else if (node.key) {
          this.restTemplate.GET({uri: `${node.key}/tree`}).then((res) => {
            resolve(res)
            for (let c of children) {
              let childNode = this.$refs.tree.getNode(c)
              if (childNode && childNode.level <= this.defaultLevel) {
                childNode.expand()
              } else {
                break
              }
            }
          })
        }
      }
    },

    changeSearch(parameter) {
      if (parameter && Object.keys(parameter).length > 0) {
        this.restTemplate.GET({
          uri: 'tree/search',
          params: {...parameter}
        }).then((res) => {
          this.setTreeData(res, true)
        })
        this.parameter = JSON.parse(JSON.stringify(parameter))
      } else { /* otherwise, reinitialize data */
        this.restTemplate.GET({
          uri: `tree`,
          params: {level: this.defaultExpandLevel + 1}
        }).then((res) => {
          this.setTreeData(res)
        })
        this.parameter = Object.assign({}, {})
      }
    },

    onSearchDrop() {
      !this.readonly && this.elasticHeight()
    },

    /* set treeData */
    setTreeData(treeData, expandAll = false) {
      /* remove all */
      this.$refs.tree.store.root.childNodes.forEach(node => node.remove())
      this.expandAll = expandAll
      if (treeData) {
        this.$refs.tree.store.setData(treeData)
        for (let c of treeData) {
          let childNode = this.$refs.tree.getNode(c)
          if (expandAll) {
            let children = childNode.getChildren()
            if (children && children.length > 0) {
              childNode.expand()
            }
          } else {
            if (childNode.level <= this.defaultLevel) {
              childNode.expand()
            }
          }
        }
      }
    },

    allowDrop() {
      return true
    },

    nodeClick(data, node) {
      let status = true
      if (this.currentNode) {
        if (this.currentNode.key === node.key) {
          node.isCurrent = false
          status = false
          this.currentNode = undefined
          this.cancel()
        } else {
          if (Object.keys(this.$refs.editForm.changedModel).length > 0) {
            this.$Modal.confirm({
              title: '警告',
              content: `确定取消编辑吗？`,
              onOk: () => {
                this.currentNode = node
                if (status === true) {
                  this.$refs.editForm.init(data.id)
                  if (this.afterEdit) this.afterEdit(data)
                  this.$refs.editForm.disableFormItems(false)
                  this.$refs.editForm.disableFormItems(true, '_parent')
                  if (!this.multipleSelect) {
                    this.$emit('on-check', [data])
                  }
                } else {/* cancel selection */
                  this.cancel()
                }
              },
              onCancel: () => {
                this.$set(this.currentNode, 'isCurrent', true)
                node.isCurrent = false
              }
            })
          } else {
            this.currentNode = node
            if (status === true) {
              this.$refs.editForm.init(data.id)
              if (this.afterEdit) this.afterEdit(data)
              this.$refs.editForm.disableFormItems(false)
              this.$refs.editForm.disableFormItems(true, '_parent')
              if (!this.multipleSelect) {
                this.$emit('on-check', [data])
              }
            } else {/* cancel selection */
              this.cancel()
            }
          }
        }

      } else {
        this.currentNode = node
        if (status === true) {
          this.$refs.editForm.init(data.id)
          if (this.afterEdit) this.afterEdit(data)
          this.$refs.editForm.disableFormItems(false)
          this.$refs.editForm.disableFormItems(true, '_parent')
          if (!this.multipleSelect) {
            this.$emit('on-check', [data])
          }
        } else {/* cancel selection */
          this.cancel()
        }
      }
    },
    /* node drag start */
    nodeDragStart(node, event) {
      this.draggingParent = node.parent
    },

    /* node drop */
    nodeDrop(node, toNode, dropType, event) {
      let drag = dropType
      drag == 'inner' && (drag = 'under')
      let uri = `tree/${node.data.id}/${drag}/${toNode.data.id}`
      this.restTemplate.PUT({uri: uri}).then((res) => {
        this.$Message.success('操作成功')
        toNode.data._leaf = false
        toNode.expand()

        if (this.draggingParent) {
          const childNodes = draggingParent.childNodes
          this.draggingParent.data._leaf = !childNodes || childNodes.length === 0
        }
      })

      this.draggingParent = undefined
    },

    elasticHeight() {
      setTimeout(() => {
        let mainContentHeight = this.mainContentHeight || document.getElementById('mainContent').clientHeight
        if (this.selecting) mainContentHeight = mainContentHeight * 0.74
        let searchConditionHeight = document.getElementById('searchCondition').clientHeight
        let optionsHeight = document.getElementById('operationBar').clientHeight
        let height = mainContentHeight - searchConditionHeight - optionsHeight - 60
        this.treeHeight = `${height}px`
        this.formCardHeight = `${height}px`
        this.formHeight = `${height - 60}px`
      }, 100)
    },

    getSelection() {
      return this.$refs.tree.getCheckedNodes()
    },

    checkChange() {
      this.$emit('on-check', this.$refs.tree.getCheckedNodes(false, true))
    },

    insertRoot() {
      this.title = '插入根节点'
      this.currentOperation = operation.insertRoot
      this.modalShow = true
      setTimeout(() => {
        this.$refs.addForm.init()
        this.$refs.addForm.disableFormItems(true, '_parent')
      }, 100)
    },

    removeBatch() {
      let nodes = this.$refs.tree.getCheckedNodes()
      if (nodes.length > 0) {
        let ids = nodes.map(data => data.id)
        this.$Modal.confirm({
          title: '警告',
          content: `已选择 ${ids.length} 项，确定删除吗？`,
          onOk: () => {
            this.restTemplate.DELETE({uri: ids}).then(() => {
              this.$Message.success('删除成功')
              nodes.forEach(data => {
                this.$refs.tree.remove(data)
              })
              this.currentNode = undefined
              this.cancel()
            })
          }
        })
      } else {
        this.$Message.warning('未勾选任何数据!')
      }
    },

    append(node) {
      if (this.currentNode) {
        this.title = `在${node._instanceName}下插入子节点`
        this.currentOperation = operation.append
        this.modalShow = true
        setTimeout(() => {
          this.$refs.addForm.init()
          this.$refs.addForm.setValue('_parent', node.id)
          this.$refs.addForm.disableFormItems(true, '_parent')
        }, 100)
      } else {
        this.$Message.warning('请选择一个节点')
      }
    },

    insertBefore(node) {
      this.title = `在${node._instanceName}前插入子节点`
      this.currentOperation = operation.insertBefore
      this.modalShow = true
      setTimeout(() => {
        this.$refs.addForm.init()
        this.$refs.addForm.disableFormItems(true, '_parent')
      }, 100)
    },

    insertAfter(node) {
      this.title = `在${node._instanceName}后插入子节点`
      this.currentOperation = operation.insertAfter
      this.modalShow = true
      setTimeout(() => {
        this.$refs.addForm.init()
        this.$refs.addForm.disableFormItems(true, '_parent')
      }, 100)
    },

    showOrgTree(node) {

      const {orgTreeSetting: {color, fontSize, defaultExpandLevel, levelOptions}} = this.meta
      this.orgTreeLevel = defaultExpandLevel
      const orgNodeRender = (h, data) => {
        return h('div', {
          style: {
            position: 'relative',
            display: 'inline-block',
            cursor: 'pointer',
            textAlign: 'center',
            color: color,
            fontSize: fontSize
          }
        }, data._instanceName)
      }

      this.restTemplate.GET({
        uri: `${node.id}/tree`,
        params: {level: this.orgTreeLevel, includeNode: true}
      }).then((res) => {
        this.orgTreeData = res[0]
        const vm = this
        const width = document.documentElement.clientWidth * 0.8
        this.$Modal.info({
          title: `${node._instanceName}-图谱`,
          closable: true,
          footerShow: false,
          width: width,
          render: (h) => {
            return h('div', {
              style: {
                textAlign: 'center'
              }
            }, [
              h('v-org-tree', {
                props: {
                  data: vm.orgTreeData,
                  nodeRender: orgNodeRender,
                  expandAll: true,
                  props: vm.meta.orgTreeProps
                },
                on: {
                  'onNodeClick': function (e, data, expand) {
                    console.log('click', data, expand)
                    expand()
                  }
                },
                domProps: {
                  id: 'orgTree'
                }
              }),
              h('div', {
                style: {
                  textAlign: 'right'
                }
              }, [
                h('el-link', {
                  props: {
                    icon: 'el-icon-download',
                    type: 'primary'
                  },
                  on: {
                    'click': function () {
                      html2canvas(document.getElementById('orgTree')).then(canvas => {
                        const imgUrl = canvas.toDataURL('image/png')
                        const alink = document.createElement('a')
                        alink.href = imgUrl
                        alink.download = `${node._instanceName}-图谱` //图片名
                        alink.click()
                      })
                    }
                  }
                }, '保存为图片'),
                h('el-link', {
                  style: {
                    marginLeft: '10px'
                  },
                }, '默认层级:'),
                h('Select', {
                  style: {
                    width: '60px',
                    display: 'inline-block',
                    marginLeft: '10px'
                  },
                  props: {
                    placeholder: '',
                    value: vm.orgTreeLevel
                  },
                  on: {
                    'on-change': function (v) {
                      vm.orgTreeLevel = v
                      vm.restTemplate.GET({
                        uri: `${node.id}/tree`,
                        params: {level: vm.orgTreeLevel, includeNode: true}
                      }).then((res) => {
                        vm.orgTreeData = res[0]
                      })
                    }
                  }
                }, [
                  levelOptions.map(level => {
                    return h('Option', {
                      props: {
                        value: level
                      }
                    }, level)
                  })
                ])
              ])
            ])
          }
        })

      })
    },

    cancel() {
      if (Object.keys(this.$refs.editForm.changedModel).length > 0) {
        this.$Modal.confirm({
          title: '警告',
          content: `确定取消编辑吗？`,
          onOk: () => {
            this.$refs.editForm.init()
            this.$refs.editForm.disableFormItems(true)
            this.$refs.editForm.changedModel = {}
          },
          onCancel: () => {

          }
        })
      } else {
        this.$refs.editForm.init()
        this.$refs.editForm.disableFormItems(true)
        this.$refs.editForm.changedModel = {}
      }
    },

    submit() {
      const _data = this.$refs.editForm.getFormDataIfValid()
      if (_data) {
        this.restTemplate.PUT({data: _data}).then((res) => {
          this.currentNode.data = JSON.parse(JSON.stringify(res))
          this.$Message.success('修改成功')
          // this.cancel()
          this.$refs.editForm.changedModel = {}
        }, (error) => {
          this.$Message.error('提交失败')
        })
      } else {
        this.$Message.error('校验不通过，请重新填写!')
      }
    },

    cancelAdd() {
      this.modalShow = false
      this.$refs.addForm.init()
    },

    submitAdd() {
      this.submitLoading = true
      const _data = this.$refs.addForm.getFormDataIfValid()
      if (_data) {
        if (this.currentOperation === operation.insertRoot) {
          this.restTemplate.POST({data: _data}).then((res) => {
            this.$refs.tree.append(res)
            this.$Message.success('插入根节点成功')
            this.cancelAdd()
          }, (error) => {
            this.$Message.error('插入失败')
          })
        } else if (this.currentOperation === operation.append) {
          this.restTemplate.POST({data: _data}).then((res) => {
            this.$refs.tree.append(res, this.currentNode)
            this.currentNode.expand()
            this.currentNode.data._leaf = false
            this.$Message.success('插入子节点成功')
            this.cancelAdd()
          }, (error) => {
            this.$Message.error('插入失败')
          })
        } else if (this.currentOperation === operation.insertBefore) {
          let uri = `tree/before/${this.currentNode.key}`
          this.restTemplate.POST({uri: uri, data: _data}).then((res) => {
            this.$refs.tree.insertBefore(res, this.currentNode)
            this.$Message.success('插入成功')
            this.cancelAdd()
          }, (error) => {
            this.$Message.error('插入失败')
          })
        } else if (this.currentOperation === operation.insertAfter) {
          let uri = `tree/after/${this.currentNode.key}`
          this.restTemplate.POST({uri: uri, data: _data}).then((res) => {
            this.$refs.tree.insertAfter(res, this.currentNode)
            this.$Message.success('插入成功')
            this.cancelAdd()
          }, (error) => {
            this.$Message.error('插入失败')
          })
        }
      } else {
        this.$Message.error('校验不通过，请重新填写!')
      }
      this.submitLoading = false
    }
  }
}
