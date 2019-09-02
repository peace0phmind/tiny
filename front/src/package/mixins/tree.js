import Rest from '@/libs/proton/Rest'

export default {
  watch: {
    'screenHeight': function () {
      this.elasticHeight()
    }
  },
  created() {
    const {restfulResourcePath} = this.meta
    this.restTemplate = new Rest(restfulResourcePath)
    this.search()
  },
  mounted() {
    this.$refs.form.init()
    this.disabledCancel = true
    this.disabledSubmit = true
    this.elasticHeight()
  },
  methods: {
    loadNode(node, resolve) {
      if (node.level !== 0) {

        let children = node.getChildren(true)
        if (children && children.length > 0) {
          resolve(children)
          for (let c of children) {
            let childNode = this.$refs.tree.getNode(c);
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
      this.elasticHeight()
    },

    /* set treeData */
    setTreeData(treeData, expandAll = false) {
      /* remove all */
      this.$refs.tree.store.root.childNodes.forEach(node => node.remove())
      this.expandAll = expandAll;
      if (treeData) {
        this.$refs.tree.store.setData(treeData)
        for (let c of treeData) {
          let childNode = this.$refs.tree.getNode(c);
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
        } else {
          this.currentNode = node
        }
      } else {
        this.currentNode = node
      }
      if (status === true) {
        this.$refs.form.init(data.id)
        this.disabledCancel = false
        this.disabledSubmit = false
        // this.$refs.zForm.disableFormItems('_parent')
      } else {/* cancel selection */
        this.disabledCancel = true
        this.disabledSubmit = true
        this.$refs.form.init()
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
          const childNodes = draggingParent.childNodes;
          this.draggingParent.data._leaf = !childNodes || childNodes.length === 0;
        }
      })

      this.draggingParent = undefined
    },

    elasticHeight() {
      setTimeout(() => {
        let mainContentHeight = this.mainContentHeight || document.getElementById('mainContent').clientHeight
        let searchConditionHeight = document.getElementById('searchCondition').clientHeight
        let optionsHeight = document.getElementById('operationBar').clientHeight
        let height = mainContentHeight - searchConditionHeight - optionsHeight - 60
        this.treeHeight = `${height}px`
        this.formCardHeight = `${height - 20}px`
        this.formHeight = `${height - 80}px`
      }, 100)
    },

    insertRoot() {

    },

    removeBatch() {

    },

    append(node) {

    },

    insertBefore(node) {

    },

    insertAfter(node) {

    },

    showOrgTree(node) {

    },

    cancel() {

    },

    submit() {

    }
  }
}
