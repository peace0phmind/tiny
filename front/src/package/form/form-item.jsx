import * as formItemRenderTypes from '../_util/formItemRenderType.js'
import * as Types from '../_util/javaTypes'
import {TEXT, TEXT_AREA, NUMBER, PASSWORD, DATE, EMAIL, URL, TEL} from '../_util/inputType'
import zyxInput from './form-item/input.jsx'
import zyxSwitch from './form-item/switch.jsx'
import zyxInputNumber from './form-item/input-number.jsx'
import zyxSlider from './form-item/slider.jsx'
import zyxSelect from './form-item/select.jsx'
import zyxDatePicker from './form-item/date-picker.jsx'
import zyxTimePicker from './form-item/time-picker.jsx'
import zyxRadio from './form-item/radio.jsx'
import zyxCheckbox from './form-item/checkbox.jsx'
import zyxCascader from './form-item/cascader.jsx'

export default {
  name: 'zyx-form-item',

  inject: ['zyxForm'],

  components: {
    zyxInput,
    zyxSwitch,
    zyxInputNumber,
    zyxSlider,
    zyxSelect,
    zyxDatePicker,
    zyxTimePicker,
    zyxRadio,
    zyxCheckbox,
    zyxCascader
  },

  props: {
    item: {},
    formModel: {},
    disabled: {},
    readonly: {},
    enums: {},
    treeModelPool: {},
    formProps: {}
  },

  render() {
    let {item: {prop, view, renderTo, label, type, dateType, tree}} = this

    const vm = this
    if (!renderTo) {
      if (Types.isString(type)) renderTo = formItemRenderTypes.INPUT_TEXT
      else if (Types.isDecimal(type)) renderTo = formItemRenderTypes.SLIDER
      else if (Types.isBoolean(type)) renderTo = formItemRenderTypes.SWITCH
      else if (Types.isDateTime(type, dateType)) renderTo = formItemRenderTypes.DATE_TIME
      else if (Types.isDateOnly(type, dateType)) renderTo = formItemRenderTypes.DATE_ONLY
      else if (Types.isTimeOnly(type, dateType)) renderTo = formItemRenderTypes.TIME_ONLY
      else if (Types.isModel(type)) {
        renderTo = !tree && formItemRenderTypes.SELECT || formItemRenderTypes.CASCADE
      }
    }

    let formItemNode

    if (renderTo === formItemRenderTypes.INPUT_TEXT) {// 单行文本输入框
      formItemNode = (
        <zyx-input ref={`${prop}_item`} form-model={this.formModel} disabled={this.disabled} readonly={this.readonly} item={this.item}
                   type={TEXT}></zyx-input>)
    } else if (renderTo === formItemRenderTypes.INPUT_PASSWORD) {// 密码输入框
      formItemNode = (
        <zyx-input ref={`${prop}_item`} form-model={this.formModel} disabled={this.disabled} readonly={this.readonly} item={this.item}
                   type={PASSWORD}></zyx-input>)
    } else if (renderTo === formItemRenderTypes.INPUT_URL) {// url 输入框
      formItemNode = (
        <zyx-input ref={`${prop}_item`} form-model={this.formModel} disabled={this.disabled} readonly={this.readonly} item={this.item}
                   type={URL}></zyx-input>)
    } else if (renderTo === formItemRenderTypes.INPUT_EMAIL) {// 邮箱输入框
      formItemNode = (
        <zyx-input ref={`${prop}_item`} form-model={this.formModel} disabled={this.disabled} readonly={this.readonly} item={this.item}
                   type={EMAIL}></zyx-input>)
    } else if (renderTo === formItemRenderTypes.INPUT_DATE) {// 日期输入框
      formItemNode = (
        <zyx-input ref={`${prop}_item`} form-model={this.formModel} disabled={this.disabled} readonly={this.readonly} item={this.item}
                   type={DATE}></zyx-input>)
    } else if (renderTo === formItemRenderTypes.INPUT_NUMBER) {// 数字文本框
      formItemNode = (
        <zyx-input ref={`${prop}_item`} form-model={this.formModel} disabled={this.disabled} readonly={this.readonly} item={this.item}
                   type={NUMBER}></zyx-input>)
    } else if (renderTo === formItemRenderTypes.INPUT_TEL) {// 电话输入框
      formItemNode = (
        <zyx-input ref={`${prop}_item`} form-model={this.formModel} disabled={this.disabled} readonly={this.readonly} item={this.item}
                   type={TEL}></zyx-input>)
    } else if (renderTo === formItemRenderTypes.INPUT_TEXTAREA) {// 多行文本输入框
      formItemNode = (
        <zyx-input ref={`${prop}_item`} form-model={this.formModel} disabled={this.disabled} readonly={this.readonly} item={this.item}
                   type={TEXT_AREA}></zyx-input>)
    } else if (renderTo === formItemRenderTypes.ICON_CHOOSE) {// 图标选择器
      if (this.readonly) formItemNode = (<Icon type={this.formModel[prop]}></Icon>)
      else formItemNode = (<icon-choose ref={`${prop}_item`} v-model={this.formModel[prop]} disabled={this.disabled[prop]}></icon-choose>)
    } else if (renderTo === formItemRenderTypes.SWITCH) {// 开关
      formItemNode = (<zyx-switch ref={`${prop}_item`} form-model={this.formModel} disabled={this.disabled} readonly={this.readonly}
                                  item={this.item}></zyx-switch>)
    } else if (renderTo === formItemRenderTypes.NUMBER) {// 数值框
      formItemNode = (<zyx-input-number ref={`${prop}_item`} form-model={this.formModel} disabled={this.disabled} readonly={this.readonly}
                                        item={this.item}></zyx-input-number>)
    } else if (renderTo === formItemRenderTypes.SLIDER) {// 滑块
      formItemNode = (<zyx-slider ref={`${prop}_item`} form-model={this.formModel} disabled={this.disabled} readonly={this.readonly}
                                  item={this.item}></zyx-slider>)
    } else if (renderTo === formItemRenderTypes.SELECT) {// 下拉框
      formItemNode = (<zyx-select ref={`${prop}_item`} form-model={this.formModel} disabled={this.disabled} readonly={this.readonly}
                                  item={this.item} enums={this.enums} form-props={this.formProps}
                                  tree-model-pool={this.treeModelPool}></zyx-select>)
    } else if (renderTo === formItemRenderTypes.CHECKBOX) {// 多选框组
      formItemNode = (
        <zyx-checkbox ref={`${prop}_item`} form-model={this.formModel} disabled={this.disabled} readonly={this.readonly} item={this.item}
                      enums={this.enums}></zyx-checkbox>)
    } else if (renderTo === formItemRenderTypes.RADIO) {// 单选框组
      formItemNode = (
        <zyx-radio ref={`${prop}_item`} form-model={this.formModel} disabled={this.disabled} readonly={this.readonly} item={this.item}
                   enums={this.enums}></zyx-radio>)
    } else if (renderTo === formItemRenderTypes.CASCADE) {// 级联框
      formItemNode = (
        <zyx-cascader ref={`${prop}_item`} form-model={this.formModel} disabled={this.disabled} readonly={this.readonly}
                      item={this.item} form-props={this.formProps}></zyx-cascader>)
    } else if (renderTo === formItemRenderTypes.DATE_TIME) {// 日期时间框
      formItemNode = (<zyx-date-picker ref={`${prop}_item`} form-model={this.formModel} disabled={this.disabled} readonly={this.readonly}
                                       item={this.item} type={'datetime'}></zyx-date-picker>)
    } else if (renderTo === formItemRenderTypes.DATE_ONLY) {// 日期框
      formItemNode = (<zyx-date-picker ref={`${prop}_item`} form-model={this.formModel} disabled={this.disabled} readonly={this.readonly}
                                       item={this.item} type={'date'}></zyx-date-picker>)
    } else if (renderTo === formItemRenderTypes.TIME_ONLY) {// 时间框
      formItemNode = (<zyx-date-picker ref={`${prop}_item`} form-model={this.formModel} disabled={this.disabled} readonly={this.readonly}
                                       item={this.item}></zyx-date-picker>)
    }


    if (this.readonly) {

      return (
        <div {...{style: {margin: '8px 0px', fontSize: '14px'}}}><b>{label}:</b>
          <span {...{style: {color: '#373737'}}}>{formItemNode}</span></div>
      )

    } else {

      return (
        <FormItem label={label} prop={prop}>
          {this.zyxForm.$scopedSlots[prop] &&
          this.zyxForm.$scopedSlots[prop]({
            formModel: this.formModel,
            formProps: this.formProps
          }) ||
          formItemNode}
        </FormItem>
      )
    }
  }
}
