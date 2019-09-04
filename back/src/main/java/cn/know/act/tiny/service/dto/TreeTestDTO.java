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
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import cn.know.act.tiny.service.dto.item.TreeItemDTO;
import java.util.Set;
import java.util.LinkedHashSet;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;
import cn.know.act.proton.system.service.dto.UserDTO;

/**
 * 树测试
 */
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@EqualsAndHashCode
public class TreeTestDTO extends BaseDTO<Long> implements Serializable {

    @Generated(IRW.CODE_GENERATOR)
    private Long id;

    /**
     * 名称
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 255) String> name;

    /**
     * 测试类型
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    private Optional<TestTypeDTO> testType;

    /**
     * int类型
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<Integer> intType;

    /**
     * decimal类型
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<BigDecimal> decimalType;

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
     * tree测试item
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    @JsonDeserialize(as = LinkedHashSet.class)
    private Set<TreeItemDTO> treeTestItems;

    /**
     * 父节点
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    private Optional<TreeTestDTO> _parent;

    /**
     * 子节点
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    private List<TreeTestDTO> _children;

    /**
     * 高亮
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<Boolean> _highlight;

    /**
     * 选中
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<Boolean> _checked;

    /**
     * 是否叶节点
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<Boolean> _leaf;

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

    @Generated(IRW.CODE_GENERATOR)
    private String _instanceName;

    @Generated(IRW.CODE_GENERATOR)
    public void addTreeTestItem(TreeItemDTO item) {
        if (this.treeTestItems == null) {
            this.treeTestItems = new LinkedHashSet<>();
        }
        this.treeTestItems.add(item);
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
    public Optional<TestTypeDTO> getTestType() {
        return testType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setTestType(Optional<TestTypeDTO> testType) {
        this.testType = testType;
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
    public Set<TreeItemDTO> getTreeTestItems() {
        return treeTestItems;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setTreeTestItems(Set<TreeItemDTO> treeTestItems) {
        this.treeTestItems = treeTestItems;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<TreeTestDTO> get_parent() {
        return _parent;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void set_parent(Optional<TreeTestDTO> _parent) {
        this._parent = _parent;
    }

    @Generated(IRW.CODE_GENERATOR)
    public List<TreeTestDTO> get_children() {
        return _children;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void set_children(List<TreeTestDTO> _children) {
        this._children = _children;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<Boolean> get_highlight() {
        return _highlight;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void set_highlight(Optional<Boolean> _highlight) {
        this._highlight = _highlight;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<Boolean> get_checked() {
        return _checked;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void set_checked(Optional<Boolean> _checked) {
        this._checked = _checked;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<Boolean> get_leaf() {
        return _leaf;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void set_leaf(Optional<Boolean> _leaf) {
        this._leaf = _leaf;
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
}
