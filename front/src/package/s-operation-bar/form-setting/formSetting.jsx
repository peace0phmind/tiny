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
    return {
      formLayoutArray: this.formLayout,
      formItemArray: this.formItems
    }
  },

  components: {
    draggable
  },

  created() {
    this.restTemplate = new Rest('dev', this.$apiURL)
  },

  computed: {},

  methods: {},

  render() {
    return (
      <div>

        <Row {...{style: {padding: '10px', background: '#f8f8f9'}}}>

          <Col span={5}>
            <Card title={'所有属性'} icon={'ios-options'} padding={0} shadow>
              <CellGroup>
                <draggable v-model={this.formItemArray} options={{
                  animation: 300,
                  group: 'people',
                  pull: 'clone',
                  put: false
                }}>
                  {
                    this.formItemArray.map(item => {
                      return (<div><Cell title={item.prop}/></div>)
                    })
                  }
                </draggable>
              </CellGroup>
            </Card>
          </Col>

          <Col span={12}>
            <Card title={'布局'} icon={'ios-options'} padding={0} shadow>
              <CellGroup>
                {
                  this.formLayoutArray.map(row => {
                    return (
                      <Cell {...{style: {height: '40px', borderBottom: '1px solid #efefef'}}}>
                        <draggable v-model={row} options={{
                          animation: 300,
                          group: 'people',
                          pull: 'clone'
                        }}>
                          {
                            row.map(item => {
                              return (<div {...{style: {display: 'inline-block'}}}><Tag>{item.prop}</Tag></div>)
                            })
                          }
                        </draggable>
                      </Cell>
                    )
                  })
                }
              </CellGroup>
            </Card>
          </Col>

          <Col span={6}>
            <Card title={'属性'} icon={'ios-options'} padding={0} shadow>

            </Card>
          </Col>

        </Row>

      </div>
    )

  }
}
