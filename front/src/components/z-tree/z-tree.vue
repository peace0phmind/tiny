<template>
  <div>
    <el-tree ref="tree" :load="loadNode" :lazy="lazy" :props="props" :show-checkbox="showCheckbox"
             node-key="id" icon-class="el-icon-caret-right" :highlight-current="true" :check-strictly="checkStrictly"
             auto-expand-parent :default-expanded-keys="expandedKeys"
             :expand-on-click-node="false" :default-expand-all="expandAll" :draggable="draggable"
             :allow-drop="allowDrop" @node-click="nodeClick" @node-expand="nodeExpand" @node-drag-start="nodeDragStart"
             @node-drop="nodeDrop" @node-contextmenu="nodeContextMenu">
      <template slot-scope="{ node, data }">
        <slot :node="node" :data="data"></slot>
      </template>
    </el-tree>
  </div>
</template>

<script>
  import Rest from '@/libs/proton/Rest'

  export default {
    name: "z-tree",
    props: {
      data: {
        type: Array
      },
      resourcePath: {},
      showCheckbox: {
        default() {
          return false
        }
      },
      checkStrictly: {
        default() {
          return false
        }
      },
      defaultLevel: {
        default() {
          return 2
        }
      },
      draggable: {
        default() {
          return false
        }
      },
      props: {
        default() {
          return {
            label: '_instanceName',
            children: '_children',
            isLeaf: '_leaf'
          }
        }
      }
    },
    data() {
      return {
        treeData: [],
        expandedKeys: [],
        expandAll: false,
        currentNode: undefined,
        lazy: true,
        draggingParent: null
      }
    },
    created() {
      this.rest = new Rest(this.resourcePath)
      /* if has data, set treeData */
      this.data && (this.treeData = this.data)
      /* if has resourcePath, set treeData from remote server & disable data */
      // this.resourcePath && this.loadData({level: this.defaultLevel})
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
            this.rest.GET({uri: `${node.key}/tree`}).then((res) => {
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
      /* toggleClick */
      nodeClick(data, node, e) {
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
        this.$emit('node-click', status, data, node)
      },
      /* expand node */
      nodeExpand(data, node, e) {
        this.$emit('node-expand', data, node)
      },
      /* node drag start */
      nodeDragStart(node, event) {
        this.draggingParent = node.parent
      },
      /* node drop */
      nodeDrop(node, toNode, dropType, event) {
        this.$emit('node-drop', node, toNode, dropType, this.draggingParent, event)
        this.draggingParent = null
      },
      /* node context menu */
      nodeContextMenu(event, data, node, component) {
        console.log(event)
        console.log(data, node, component)
      },
      /* append a new node to the specified node */
      /* refNode can be data or node or key */
      append(data, refNode) {
        this.$refs.tree.append(data, refNode)
      },
      /* remove the specified node at page */
      /* refNode can be data or node */
      remove(refNode) {
        this.$refs.tree.remove(refNode)
      },
      /* insert a new node before the specified node at page */
      /* refNode can be data or node or key */
      insertBefore(data, refNode) {
        this.$refs.tree.insertBefore(data, refNode)
      },
      /* insert a new node after the specified node at page */
      /* refNode can be data or node or key */
      insertAfter(data, refNode) {
        this.$refs.tree.insertAfter(data, refNode)
      },
      /* get tree data */
      getTreeData() {
        return this.treeData
      },
      /* get checked nodes */
      getCheckedNodes() {
        return this.$refs.tree.getCheckedNodes()
      },
      /* set checked keys */
      setCheckedKeys(keys, leafOnly = false) {
        this.$refs.tree.setCheckedKeys(keys, leafOnly)
      },
    }
  }
</script>

<style scoped>

</style>
