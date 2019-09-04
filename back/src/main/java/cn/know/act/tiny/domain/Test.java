package cn.know.act.tiny.domain;

import cn.know.act.proton.system.domain.AuditDate;
import cn.know.act.proton.system.domain.AuditUser;
import cn.know.act.proton.system.domain.SoftDelete;
import cn.know.act.proton.system.domain._Version;
import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.EntityListeners;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.AllArgsConstructor;
import javax.persistence.Table;
import javax.persistence.Index;
import javax.persistence.Entity;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.validation.constraints.Size;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.ConstraintMode;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import java.util.Set;
import java.util.LinkedHashSet;
import javax.persistence.ElementCollection;
import javax.persistence.JoinTable;
import cn.know.act.tiny.domain.item.Item;
import javax.persistence.OneToMany;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import cn.know.act.proton.system.domain.User;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import cn.know.act.proton.core.jpa.soft.delete.DeletedDate;
import javax.persistence.Version;
import java.text.MessageFormat;
import cn.know.act.proton.core.service.filter.Filter;
import java.util.Objects;

/**
 * 测试
 */
@ToString
@NoArgsConstructor
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@Table(name = "tny_test", indexes = { @Index(name = "idx_tny_test_template_type_id", columnList = "template_type_id"), @Index(name = "idx_tny_test_status_", columnList = "status_"), @Index(name = "idx_tny_test_creator_id", columnList = "creator_id"), @Index(name = "idx_tny_test_modifier_id", columnList = "modifier_id") })
@Entity(name = "TnyTest")
public class Test implements AuditDate, AuditUser, SoftDelete, _Version, Serializable {

    @Generated(IRW.CODE_GENERATOR)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /**
     * 名称
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 255)
    @Column(name = "name_", length = 255)
    private String name;

    /**
     * 模板类型
     */
    @Generated(IRW.CODE_GENERATOR)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_type_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private TestType templateType;

    /**
     * int类型
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "int_type")
    private Integer intType;

    /**
     * decimal类型
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "decimal_type", scale = 2, columnDefinition = "Decimal(19, 2)")
    private BigDecimal decimalType;

    /**
     * 时间类型
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "time_type")
    private LocalTime timeType;

    /**
     * 日期类型
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "date_type")
    private LocalDate dateType;

    /**
     * 日期时间类型
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "date_time_type")
    private LocalDateTime dateTimeType;

    /**
     * 开关类型
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "boolean_type")
    private Boolean booleanType = false;

    /**
     * 状态
     */
    @Generated(IRW.CODE_GENERATOR)
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status_", length = 10)
    private Test.Status status;

    /**
     * 多值枚举
     */
    @Generated(IRW.CODE_GENERATOR)
    @Enumerated(value = EnumType.STRING)
    @Column(name = "multi_enum", length = 10)
    @ElementCollection
    @JoinTable(name = "tny_test_multi_enum", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), joinColumns = @JoinColumn(name = "test_id"))
    private Set<Test.MultiEnum> multiEnums = new LinkedHashSet<>();

    /**
     * 测试items
     */
    @Generated(IRW.CODE_GENERATOR)
    @OneToMany(mappedBy = "test")
    private Set<Item> items = new LinkedHashSet<>();

    /**
     * 创建时间
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;

    /**
     * 修改时间
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    /**
     * 创建人
     */
    @Generated(IRW.CODE_GENERATOR)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @CreatedBy
    private User creator;

    /**
     * 修改人
     */
    @Generated(IRW.CODE_GENERATOR)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modifier_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @LastModifiedBy
    private User modifier;

    /**
     * 删除时间
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "deleted_date")
    @DeletedDate
    private LocalDateTime deletedDate;

    /**
     * 版本
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "_version")
    @Version
    private Integer _version;

    @Generated(IRW.CODE_GENERATOR)
    public void addItem(Item item) {
        if (this.items == null) {
            this.items = new LinkedHashSet<>();
        }
        item.setTest(this);
        this.items.add(item);
    }

    @Generated(IRW.CODE_GENERATOR)
    public String get_instanceName() {
        return MessageFormat.format("{0}", name);
    }

    @Override
    @Generated(IRW.CODE_GENERATOR)
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Test)) {
            return false;
        }
        Test that = (Test) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && (templateType != null && that.templateType != null ? Objects.equals(templateType.getId(), that.templateType.getId()) : templateType == that.templateType) && Objects.equals(intType, that.intType) && Objects.equals(decimalType, that.decimalType) && Objects.equals(timeType, that.timeType) && Objects.equals(dateType, that.dateType) && Objects.equals(dateTimeType, that.dateTimeType) && Objects.equals(booleanType, that.booleanType) && Objects.equals(status, that.status) && Objects.equals(createdDate, that.createdDate) && Objects.equals(modifiedDate, that.modifiedDate) && (creator != null && that.creator != null ? Objects.equals(creator.getId(), that.creator.getId()) : creator == that.creator) && (modifier != null && that.modifier != null ? Objects.equals(modifier.getId(), that.modifier.getId()) : modifier == that.modifier) && Objects.equals(deletedDate, that.deletedDate);
    }

    @Override
    @Generated(IRW.CODE_GENERATOR)
    public int hashCode() {
        return Objects.hash(id, name, templateType != null ? templateType.getId() : null, intType, decimalType, timeType, dateType, dateTimeType, booleanType, status, createdDate, modifiedDate, creator != null ? creator.getId() : null, modifier != null ? modifier.getId() : null, deletedDate);
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
    public String getName() {
        return name;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setName(String name) {
        this.name = name;
    }

    @Generated(IRW.CODE_GENERATOR)
    public TestType getTemplateType() {
        return templateType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setTemplateType(TestType templateType) {
        this.templateType = templateType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Integer getIntType() {
        return intType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setIntType(Integer intType) {
        this.intType = intType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public BigDecimal getDecimalType() {
        return decimalType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setDecimalType(BigDecimal decimalType) {
        this.decimalType = decimalType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public LocalTime getTimeType() {
        return timeType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setTimeType(LocalTime timeType) {
        this.timeType = timeType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public LocalDate getDateType() {
        return dateType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setDateType(LocalDate dateType) {
        this.dateType = dateType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public LocalDateTime getDateTimeType() {
        return dateTimeType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setDateTimeType(LocalDateTime dateTimeType) {
        this.dateTimeType = dateTimeType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Boolean isBooleanType() {
        return booleanType == null ? Boolean.FALSE : booleanType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setBooleanType(Boolean booleanType) {
        this.booleanType = booleanType == null ? Boolean.FALSE : booleanType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Test.Status getStatus() {
        return status;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setStatus(Test.Status status) {
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
    public Set<Item> getItems() {
        return items;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Generated(IRW.CODE_GENERATOR)
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public User getCreator() {
        return creator;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Generated(IRW.CODE_GENERATOR)
    public User getModifier() {
        return modifier;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setModifier(User modifier) {
        this.modifier = modifier;
    }

    @Generated(IRW.CODE_GENERATOR)
    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Integer get_version() {
        return _version;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void set_version(Integer _version) {
        this._version = _version;
    }

    /**
     * 状态
     */
    public enum Status {

        /**
         * 初始化
         */
        INIT,
        /**
         * 可使用
         */
        USABLE,
        /**
         * 已删除
         */
        REMOVED
    }

    public static class StatusFilter extends Filter<Status> {
    }

    /**
     * 多值枚举
     */
    public enum MultiEnum {

        /**
         * 初始化
         */
        INIT,
        /**
         * 可使用
         */
        USABLE,
        /**
         * 已删除
         */
        REMOVED
    }

    public static class MultiEnumFilter extends Filter<MultiEnum> {
    }
}
