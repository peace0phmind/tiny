package cn.know.act.proton.system.domain;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import java.util.UUID;
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
import java.text.MessageFormat;
import cn.know.act.proton.core.jpa.tree.TreePath;
import java.util.Objects;

/**
 * 部门
 */
@ToString
@NoArgsConstructor
@DynamicUpdate
@AllArgsConstructor
@Table(name = "sys_department", indexes = { @Index(name = "idx_sys_department__parent_id", columnList = "_parent_id") })
@Entity(name = "SysDepartment")
public class Department extends TreeNode<Department, UUID> implements Serializable {

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
    @Size(max = 60)
    @Column(name = "name_", length = 60)
    private String name;

    /**
     * 代码
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 20)
    @Column(name = "code_", length = 20)
    private String code;

    /**
     * 描述
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 1024)
    @Column(name = "description", length = 1024)
    private String description;

    /**
     * 父节点
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_parent_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Department _parent;

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
        if (!(o instanceof Department)) {
            return false;
        }
        Department that = (Department) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(code, that.code) && Objects.equals(description, that.description) && (_parent != null && that._parent != null ? Objects.equals(_parent.getId(), that._parent.getId()) : _parent == that._parent);
    }

    @Override
    @Generated(IRW.CODE_GENERATOR)
    public int hashCode() {
        return Objects.hash(id, name, code, description, _parent != null ? _parent.getId() : null);
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
    public String getCode() {
        return code;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setCode(String code) {
        this.code = code;
    }

    @Generated(IRW.CODE_GENERATOR)
    public String getDescription() {
        return description;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setDescription(String description) {
        this.description = description;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Department get_parent() {
        return _parent;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void set_parent(Department _parent) {
        this._parent = _parent;
    }

    @Table(name = "sys_department__tp")
    @Entity(name = "SysDepartment__tp")
    public static class DepartmentTreePath extends TreePath<Department> {
    }
}
