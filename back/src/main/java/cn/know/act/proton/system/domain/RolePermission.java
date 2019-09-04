package cn.know.act.proton.system.domain;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import lombok.AllArgsConstructor;
import javax.persistence.Table;
import javax.persistence.Index;
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
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.ConstraintMode;
import java.text.MessageFormat;
import java.util.Optional;
import java.util.function.Consumer;
import javax.persistence.Transient;
import java.util.Objects;

/**
 * 角色权限
 */
@ToString
@NoArgsConstructor
@DynamicUpdate
@AllArgsConstructor
@Table(name = "sys_role_permission", indexes = { @Index(name = "idx_sys_role_permission_permission_id", columnList = "permission_id"), @Index(name = "idx_sys_role_permission_role_id", columnList = "role_id") })
@Entity(name = "SysRolePermission")
@DiscriminatorColumn(name = "_type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class RolePermission implements Serializable {

    @Generated(IRW.CODE_GENERATOR)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @org.hibernate.annotations.Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "char(36)")
    private UUID id;

    /**
     * 权限
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Permission permission;

    /**
     * 权限
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Role role;

    @Generated(IRW.CODE_GENERATOR)
    @Transient
    private _Type _type = _Type.valueOf(this.getClass());

    @Generated(IRW.CODE_GENERATOR)
    public String get_instanceName() {
        return MessageFormat.format("{0}", id);
    }

    @Generated(IRW.CODE_GENERATOR)
    public boolean isOperationRolePerm() {
        return false;
    }

    @Generated(IRW.CODE_GENERATOR)
    public OperationRolePerm asOperationRolePerm() {
        throw new IllegalStateException(String.format("%s is not an OperationRolePerm", this));
    }

    @Generated(IRW.CODE_GENERATOR)
    public void ifOperationRolePerm(Consumer<OperationRolePerm> action) {
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<OperationRolePerm> toOperationRolePerm() {
        return Optional.empty();
    }

    @Generated(IRW.CODE_GENERATOR)
    public boolean isAttributeRolePerm() {
        return false;
    }

    @Generated(IRW.CODE_GENERATOR)
    public AttributeRolePerm asAttributeRolePerm() {
        throw new IllegalStateException(String.format("%s is not an AttributeRolePerm", this));
    }

    @Generated(IRW.CODE_GENERATOR)
    public void ifAttributeRolePerm(Consumer<AttributeRolePerm> action) {
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<AttributeRolePerm> toAttributeRolePerm() {
        return Optional.empty();
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
        if (!(o instanceof RolePermission)) {
            return false;
        }
        RolePermission that = (RolePermission) o;
        return Objects.equals(id, that.id) && (permission != null && that.permission != null ? Objects.equals(permission.getId(), that.permission.getId()) : permission == that.permission) && (role != null && that.role != null ? Objects.equals(role.getId(), that.role.getId()) : role == that.role);
    }

    @Override
    @Generated(IRW.CODE_GENERATOR)
    public int hashCode() {
        return Objects.hash(id, permission != null ? permission.getId() : null, role != null ? role.getId() : null);
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
    public Permission getPermission() {
        return permission;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Role getRole() {
        return role;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setRole(Role role) {
        this.role = role;
    }

    public enum _Type {

        /**
         * 操作类权限
         */
        OP(OperationRolePerm.class),
        /**
         * 属性类权限
         */
        ATTR(AttributeRolePerm.class);

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
