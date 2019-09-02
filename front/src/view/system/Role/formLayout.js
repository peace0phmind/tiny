/*
  二位数组，一个子数组表示一行，填写item的prop字段，忽略COLLECTION type类型
*/
const formLayout = [
  [{prop: 'name'}, {prop: 'innerName'}],
  [{prop: 'description', renderTo: 'input_textarea'}],
  [{prop: 'defaultRole'}],
  [{prop: 'type', renderTo: 'radio'}]
]

export default formLayout
