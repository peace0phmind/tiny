package cn.know.act.tiny.domain.item;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import lombok.AllArgsConstructor;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.UUID;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.validation.constraints.Size;
import cn.know.act.tiny.domain.TreeTest;
import java.util.Set;
import java.util.LinkedHashSet;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.ConstraintMode;
import java.text.MessageFormat;
import javax.persistence.Transient;
import java.util.Objects;

/**
 * tree测试item
 */
@ToString
@NoArgsConstructor
@DynamicUpdate
@AllArgsConstructor
@Table(name = "tny_tree_item")
@Entity(name = "TnyTreeItem")
@DiscriminatorColumn(name = "_type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class TreeItem implements Serializable {

    @Generated(IRW.CODE_GENERATOR)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @org.hibernate.annotations.Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "char(36)")
    private UUID id;

    /**
     * 名称
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 255)
    @Column(name = "name_", length = 255)
    private String name;

    /**
     * 值
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 255)
    @Column(name = "value_", nullable = false, length = 255)
    private String value;

    /**
     * test
     */
    @Generated(IRW.CODE_GENERATOR)
    @ManyToMany()
    @JoinTable(name = "tny_tree_item_tree_test", indexes = { @Index(name = "tny_tree_item_tree_test_tree_item_id", columnList = "tree_item_id"), @Index(name = "tny_tree_item_tree_test_tree_test_id", columnList = "tree_test_id") }, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), joinColumns = @JoinColumn(name = "tree_item_id", referencedColumnName = "id"), inverseForeignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), inverseJoinColumns = @JoinColumn(name = "tree_test_id", referencedColumnName = "id"))
    private Set<TreeTest> tests = new LinkedHashSet<>();

    @Generated(IRW.CODE_GENERATOR)
    @Transient
    private _Type _type = _Type.valueOf(this.getClass());

    @Generated(IRW.CODE_GENERATOR)
    public void addTest(TreeTest item) {
        if (this.tests == null) {
            this.tests = new LinkedHashSet<>();
        }
        this.tests.add(item);
    }

    @Generated(IRW.CODE_GENERATOR)
    public String get_instanceName() {
        return MessageFormat.format("{0}", id);
    }

    @Generated(IRW.CODE_GENERATOR)
    public _Type get_type() {
        return this._type;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void set_type(_Type _type) {
        this._type = _type;
    }

    @Override
    @Generated(IRW.CODE_GENERATOR)
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TreeItem)) {
            return false;
        }
        TreeItem that = (TreeItem) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(value, that.value);
    }

    @Override
    @Generated(IRW.CODE_GENERATOR)
    public int hashCode() {
        return Objects.hash(id, name, value);
    }

    @Generated(IRW.CODE_GENERATOR)
    public UUID getId() {
        return id;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setId(UUID id) {
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
    public String getValue() {
        return value;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setValue(String value) {
        this.value = value;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Set<TreeTest> getTests() {
        return tests;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setTests(Set<TreeTest> tests) {
        this.tests = tests;
    }

    public enum _Type {
        ;

        private Class field;

        @Generated(IRW.CODE_GENERATOR)
        _Type(Class field) {
            this.field = field;
        }

        @Generated(IRW.CODE_GENERATOR)
        public static _Type valueOf(Class field) {
            for (_Type type : values()) {
                if (type.field.equals(field)) {
                    return type;
                }
            }
            return null;
        }

        @Generated(IRW.CODE_GENERATOR)
        public Class getField() {
            return field;
        }
    }
}
