package cn.know.act.tiny.domain;

import cn.know.act.proton.system.domain.AuditDate;
import cn.know.act.proton.system.domain.AuditUser;
import cn.know.act.proton.system.domain.SoftDelete;
import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.EntityListeners;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import cn.know.act.proton.core.jpa.tree.TreeNode;
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
import cn.know.act.tiny.domain.item.TreeItem;
import java.util.Set;
import java.util.LinkedHashSet;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import cn.know.act.proton.system.domain.User;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import cn.know.act.proton.core.jpa.soft.delete.DeletedDate;
import java.text.MessageFormat;
import cn.know.act.proton.core.jpa.tree.TreePath;
import java.util.Objects;

/**
 * 树测试
 */
@ToString
@NoArgsConstructor
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@Table(name = "tny_tree_test", indexes = { @Index(name = "idx_tny_tree_test_test_type_id", columnList = "test_type_id"), @Index(name = "idx_tny_tree_test__parent_id", columnList = "_parent_id"), @Index(name = "idx_tny_tree_test_creator_id", columnList = "creator_id"), @Index(name = "idx_tny_tree_test_modifier_id", columnList = "modifier_id") })
@Entity(name = "TnyTreeTest")
public class TreeTest extends TreeNode<TreeTest, Long> implements AuditDate, AuditUser, SoftDelete, Serializable {

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
     * 测试类型
     */
    @Generated(IRW.CODE_GENERATOR)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_type_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private TestType testType;

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
     * tree测试item
     */
    @Generated(IRW.CODE_GENERATOR)
    @ManyToMany()
    @JoinTable(name = "tny_tree_item_tree_test", indexes = { @Index(name = "tny_tree_item_tree_test_tree_test_id", columnList = "tree_test_id"), @Index(name = "tny_tree_item_tree_test_tree_item_id", columnList = "tree_item_id") }, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), joinColumns = @JoinColumn(name = "tree_test_id", referencedColumnName = "id"), inverseForeignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), inverseJoinColumns = @JoinColumn(name = "tree_item_id", referencedColumnName = "id"))
    private Set<TreeItem> treeTestItems = new LinkedHashSet<>();

    /**
     * 父节点
     */
    @Generated(IRW.CODE_GENERATOR)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_parent_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private TreeTest _parent;

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

    @Generated(IRW.CODE_GENERATOR)
    public void addTreeTestItem(TreeItem item) {
        if (this.treeTestItems == null) {
            this.treeTestItems = new LinkedHashSet<>();
        }
        this.treeTestItems.add(item);
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
        if (!(o instanceof TreeTest)) {
            return false;
        }
        TreeTest that = (TreeTest) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && (testType != null && that.testType != null ? Objects.equals(testType.getId(), that.testType.getId()) : testType == that.testType) && Objects.equals(intType, that.intType) && Objects.equals(decimalType, that.decimalType) && Objects.equals(timeType, that.timeType) && Objects.equals(dateType, that.dateType) && Objects.equals(dateTimeType, that.dateTimeType) && Objects.equals(booleanType, that.booleanType) && (_parent != null && that._parent != null ? Objects.equals(_parent.getId(), that._parent.getId()) : _parent == that._parent) && Objects.equals(createdDate, that.createdDate) && Objects.equals(modifiedDate, that.modifiedDate) && (creator != null && that.creator != null ? Objects.equals(creator.getId(), that.creator.getId()) : creator == that.creator) && (modifier != null && that.modifier != null ? Objects.equals(modifier.getId(), that.modifier.getId()) : modifier == that.modifier) && Objects.equals(deletedDate, that.deletedDate);
    }

    @Override
    @Generated(IRW.CODE_GENERATOR)
    public int hashCode() {
        return Objects.hash(id, name, testType != null ? testType.getId() : null, intType, decimalType, timeType, dateType, dateTimeType, booleanType, _parent != null ? _parent.getId() : null, createdDate, modifiedDate, creator != null ? creator.getId() : null, modifier != null ? modifier.getId() : null, deletedDate);
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
    public TestType getTestType() {
        return testType;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setTestType(TestType testType) {
        this.testType = testType;
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
    public Set<TreeItem> getTreeTestItems() {
        return treeTestItems;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setTreeTestItems(Set<TreeItem> treeTestItems) {
        this.treeTestItems = treeTestItems;
    }

    @Generated(IRW.CODE_GENERATOR)
    public TreeTest get_parent() {
        return _parent;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void set_parent(TreeTest _parent) {
        this._parent = _parent;
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

    @Table(name = "tny_tree_test__tp")
    @Entity(name = "TnyTreeTest__tp")
    public static class TreeTestTreePath extends TreePath<TreeTest> {
    }
}
