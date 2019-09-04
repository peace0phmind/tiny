package cn.know.act.tiny.service.dto;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;
import cn.know.act.proton.core.util.BaseDTO;
import lombok.EqualsAndHashCode;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import java.util.Optional;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.DecimalMax;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import cn.know.act.tiny.domain.Test;
import java.util.Set;
import java.util.LinkedHashSet;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import cn.know.act.tiny.service.dto.item.ItemDTO;
import cn.know.act.proton.system.service.dto.UserDTO;

/**
 * 测试
 */
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@EqualsAndHashCode
public class TestDTO extends BaseDTO<Long> implements Serializable {

    @Generated(IRW.CODE_GENERATOR)
    private Long id;

    /**
     * 名称
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 255) String> name;

    /**
     * 模板类型
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    private Optional<TestTypeDTO> templateType;

    /**
     * int类型
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Min(value = 10) @Max(value = 1000) Integer> intType;

    /**
     * decimal类型
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@DecimalMin(value = "20") @DecimalMax(value = "2000") BigDecimal> decimalType;

    /**
     * 时间类型
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<LocalTime> timeType;

    /**
     * 日期类型
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<LocalDate> dateType;

    /**
     * 日期时间类型
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<LocalDateTime> dateTimeType;

    /**
     * 开关类型
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<Boolean> booleanType;

    /**
     * 状态
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<Test.Status> status;

    /**
     * 多值枚举
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    @JsonDeserialize(as = LinkedHashSet.class)
    private Set<Test.MultiEnum> multiEnums;

    /**
     * 测试items
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    @JsonDeserialize(as = LinkedHashSet.class)
    private Set<ItemDTO> items;

    /**
     * 创建时间
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<LocalDateTime> createdDate;

    /**
     * 修改时间
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<LocalDateTime> modifiedDate;

    /**
     * 创建人
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    private Optional<UserDTO> creator;

    /**
     * 修改人
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    private Optional<UserDTO> modifier;

    /**
     * 删除时间
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<LocalDateTime> deletedDate;

    /**
     * 版本
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<Integer> _version;

    @Generated(IRW.CODE_GENERATOR)
    private String _instanceName;

    @Generated(IRW.CODE_GENERATOR)
    public void addItem(ItemDTO item) {
        if (this.items == null) {
            this.items = new LinkedHashSet<>();
        }
        this.items.add(item);
    }

    @Generated(IRW.CODE_GENERATOR)
    public String get_instanceName() {
        return _instanceName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void set_instanceName(String _instanceName) {
        this._instanceName = _instanceName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Long getId() {
        return id;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<String> getName() {
        return name;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setName(Optional<String> name) {
        this.name = name;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<TestTypeDTO> getTemplateType() {
        return templateType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setTemplateType(Optional<TestTypeDTO> templateType) {
        this.templateType = templateType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<Integer> getIntType() {
        return intType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setIntType(Optional<Integer> intType) {
        this.intType = intType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<BigDecimal> getDecimalType() {
        return decimalType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setDecimalType(Optional<BigDecimal> decimalType) {
        this.decimalType = decimalType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<LocalTime> getTimeType() {
        return timeType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setTimeType(Optional<LocalTime> timeType) {
        this.timeType = timeType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<LocalDate> getDateType() {
        return dateType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setDateType(Optional<LocalDate> dateType) {
        this.dateType = dateType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<LocalDateTime> getDateTimeType() {
        return dateTimeType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setDateTimeType(Optional<LocalDateTime> dateTimeType) {
        this.dateTimeType = dateTimeType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<Boolean> getBooleanType() {
        return booleanType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setBooleanType(Optional<Boolean> booleanType) {
        this.booleanType = booleanType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<Test.Status> getStatus() {
        return status;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setStatus(Optional<Test.Status> status) {
        this.status = status;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Set<Test.MultiEnum> getMultiEnums() {
        return multiEnums;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setMultiEnums(Set<Test.MultiEnum> multiEnums) {
        this.multiEnums = multiEnums;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Set<ItemDTO> getItems() {
        return items;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setItems(Set<ItemDTO> items) {
        this.items = items;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<LocalDateTime> getCreatedDate() {
        return createdDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setCreatedDate(Optional<LocalDateTime> createdDate) {
        this.createdDate = createdDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<LocalDateTime> getModifiedDate() {
        return modifiedDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setModifiedDate(Optional<LocalDateTime> modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<UserDTO> getCreator() {
        return creator;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setCreator(Optional<UserDTO> creator) {
        this.creator = creator;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<UserDTO> getModifier() {
        return modifier;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setModifier(Optional<UserDTO> modifier) {
        this.modifier = modifier;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<LocalDateTime> getDeletedDate() {
        return deletedDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setDeletedDate(Optional<LocalDateTime> deletedDate) {
        this.deletedDate = deletedDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<Integer> get_version() {
        return _version;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void set_version(Optional<Integer> _version) {
        this._version = _version;
    }
}
