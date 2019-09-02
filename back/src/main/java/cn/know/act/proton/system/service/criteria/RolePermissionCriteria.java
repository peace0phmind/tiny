package cn.know.act.proton.system.service.criteria;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import cn.know.act.proton.core.service.filter.UUIDFilter;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import java.util.Optional;
import java.util.function.Consumer;
import javax.persistence.Transient;

/**
 * 角色权限
 */
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "_type")
@JsonSubTypes({ @JsonSubTypes.Type(value = OperationRolePermCriteria.class, name = "OP"), @JsonSubTypes.Type(value = AttributeRolePermCriteria.class, name = "ATTR") })
public abstract class RolePermissionCriteria implements Serializable {

    /**
     * 权限
     */
    @Generated(IRW.CODE_GENERATOR)
    private UUIDFilter role;

    @Generated(IRW.CODE_GENERATOR)
    @Transient
    private _Type _type = _Type.valueOf(this.getClass());

    @Generated(IRW.CODE_GENERATOR)
    public boolean isOperationRolePermCriteria() {
        return false;
    }

    @Generated(IRW.CODE_GENERATOR)
    public OperationRolePermCriteria asOperationRolePermCriteria() {
        throw new IllegalStateException(String.format("%s is not an OperationRolePermCriteria", this));
    }

    @Generated(IRW.CODE_GENERATOR)
    public void ifOperationRolePermCriteria(Consumer<OperationRolePermCriteria> action) {
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<OperationRolePermCriteria> toOperationRolePermCriteria() {
        return Optional.empty();
    }

    @Generated(IRW.CODE_GENERATOR)
    public boolean isAttributeRolePermCriteria() {
        return false;
    }

    @Generated(IRW.CODE_GENERATOR)
    public AttributeRolePermCriteria asAttributeRolePermCriteria() {
        throw new IllegalStateException(String.format("%s is not an AttributeRolePermCriteria", this));
    }

    @Generated(IRW.CODE_GENERATOR)
    public void ifAttributeRolePermCriteria(Consumer<AttributeRolePermCriteria> action) {
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<AttributeRolePermCriteria> toAttributeRolePermCriteria() {
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

    @Generated(IRW.CODE_GENERATOR)
    public UUIDFilter getRole() {
        return role;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setRole(UUIDFilter role) {
        this.role = role;
    }

    public enum _Type {

        /**
         * 操作类权限
         */
        OP(OperationRolePermCriteria.class),
        /**
         * 属性类权限
         */
        ATTR(AttributeRolePermCriteria.class);

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
