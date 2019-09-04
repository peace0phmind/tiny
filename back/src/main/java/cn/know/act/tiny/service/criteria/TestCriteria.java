package cn.know.act.tiny.service.criteria;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import cn.know.act.proton.core.service.filter.StringFilter;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import cn.know.act.proton.core.service.filter.LongFilter;
import cn.know.act.proton.core.service.filter.IntegerFilter;
import cn.know.act.proton.core.service.filter.LocalTimeFilter;
import cn.know.act.proton.core.service.filter.LocalDateFilter;
import cn.know.act.proton.core.service.filter.LocalDateTimeFilter;
import cn.know.act.proton.core.service.filter.BooleanFilter;
import cn.know.act.tiny.domain.Test;
import cn.know.act.proton.core.service.filter.UUIDFilter;

/**
 * 测试
 */
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class TestCriteria implements Serializable {

    /**
     * 名称
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter name;

    /**
     * 模板类型
     */
    @Generated(IRW.CODE_GENERATOR)
    private LongFilter templateType;

    /**
     * int类型
     */
    @Generated(IRW.CODE_GENERATOR)
    private IntegerFilter intType;

    /**
     * decimal类型
     */
    @Generated(IRW.CODE_GENERATOR)
    private IntegerFilter decimalType;

    /**
     * 时间类型
     */
    @Generated(IRW.CODE_GENERATOR)
    private LocalTimeFilter timeType;

    /**
     * 日期类型
     */
    @Generated(IRW.CODE_GENERATOR)
    private LocalDateFilter dateType;

    /**
     * 日期时间类型
     */
    @Generated(IRW.CODE_GENERATOR)
    private LocalDateTimeFilter dateTimeType;

    /**
     * 开关类型
     */
    @Generated(IRW.CODE_GENERATOR)
    private BooleanFilter booleanType;

    /**
     * 状态
     */
    @Generated(IRW.CODE_GENERATOR)
    private Test.StatusFilter status;

    /**
     * 多值枚举
     */
    @Generated(IRW.CODE_GENERATOR)
    private Test.MultiEnumFilter multiEnums;

    /**
     * 测试items
     */
    @Generated(IRW.CODE_GENERATOR)
    private UUIDFilter items;

    /**
     * 创建时间
     */
    @Generated(IRW.CODE_GENERATOR)
    private LocalDateTimeFilter createdDate;

    /**
     * 修改时间
     */
    @Generated(IRW.CODE_GENERATOR)
    private LocalDateTimeFilter modifiedDate;

    /**
     * 创建人
     */
    @Generated(IRW.CODE_GENERATOR)
    private UUIDFilter creator;

    /**
     * 修改人
     */
    @Generated(IRW.CODE_GENERATOR)
    private UUIDFilter modifier;

    /**
     * 删除时间
     */
    @Generated(IRW.CODE_GENERATOR)
    private LocalDateTimeFilter deletedDate;

    @Generated(IRW.CODE_GENERATOR)
    public StringFilter getName() {
        return name;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setName(StringFilter name) {
        this.name = name;
    }

    @Generated(IRW.CODE_GENERATOR)
    public LongFilter getTemplateType() {
        return templateType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setTemplateType(LongFilter templateType) {
        this.templateType = templateType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public IntegerFilter getIntType() {
        return intType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setIntType(IntegerFilter intType) {
        this.intType = intType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public IntegerFilter getDecimalType() {
        return decimalType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setDecimalType(IntegerFilter decimalType) {
        this.decimalType = decimalType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public LocalTimeFilter getTimeType() {
        return timeType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setTimeType(LocalTimeFilter timeType) {
        this.timeType = timeType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public LocalDateFilter getDateType() {
        return dateType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setDateType(LocalDateFilter dateType) {
        this.dateType = dateType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public LocalDateTimeFilter getDateTimeType() {
        return dateTimeType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setDateTimeType(LocalDateTimeFilter dateTimeType) {
        this.dateTimeType = dateTimeType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public BooleanFilter getBooleanType() {
        return booleanType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setBooleanType(BooleanFilter booleanType) {
        this.booleanType = booleanType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Test.StatusFilter getStatus() {
        return status;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setStatus(Test.StatusFilter status) {
        this.status = status;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Test.MultiEnumFilter getMultiEnums() {
        return multiEnums;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setMultiEnums(Test.MultiEnumFilter multiEnums) {
        this.multiEnums = multiEnums;
    }

    @Generated(IRW.CODE_GENERATOR)
    public UUIDFilter getItems() {
        return items;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setItems(UUIDFilter items) {
        this.items = items;
    }

    @Generated(IRW.CODE_GENERATOR)
    public LocalDateTimeFilter getCreatedDate() {
        return createdDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setCreatedDate(LocalDateTimeFilter createdDate) {
        this.createdDate = createdDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public LocalDateTimeFilter getModifiedDate() {
        return modifiedDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setModifiedDate(LocalDateTimeFilter modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public UUIDFilter getCreator() {
        return creator;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setCreator(UUIDFilter creator) {
        this.creator = creator;
    }

    @Generated(IRW.CODE_GENERATOR)
    public UUIDFilter getModifier() {
        return modifier;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setModifier(UUIDFilter modifier) {
        this.modifier = modifier;
    }

    @Generated(IRW.CODE_GENERATOR)
    public LocalDateTimeFilter getDeletedDate() {
        return deletedDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setDeletedDate(LocalDateTimeFilter deletedDate) {
        this.deletedDate = deletedDate;
    }
}
