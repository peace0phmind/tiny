package cn.know.act.proton.system.service.dto;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;
import cn.know.act.proton.core.util.BaseDTO;
import lombok.EqualsAndHashCode;
import java.util.UUID;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import java.util.function.Consumer;
import javax.persistence.Transient;

/**
 * 角色权限
 */
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@EqualsAndHashCode
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "_type")
@JsonSubTypes({ @JsonSubTypes.Type(value = OperationRolePermDTO.class, name = "OP"), @JsonSubTypes.Type(value = AttributeRolePermDTO.class, name = "ATTR") })
public abstract class RolePermissionDTO extends BaseDTO<UUID> implements Serializable {

    @Generated(IRW.CODE_GENERATOR)
    private UUID id;

    /**
     * 权限
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    private Optional<PermissionDTO> permission;

    /**
     * 权限
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    private Optional<RoleDTO> role;

    @Generated(IRW.CODE_GENERATOR)
    private String _instanceName;

    @Generated(IRW.CODE_GENERATOR)
    @Transient
    private _Type _type = _Type.valueOf(this.getClass());

    @Generated(IRW.CODE_GENERATOR)
    public String get_instanceName() {
        return _instanceName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void set_instanceName(String _instanceName) {
        this._instanceName = _instanceName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public boolean isOperationRolePermDTO() {
        return false;
    }

    @Generated(IRW.CODE_GENERATOR)
    public OperationRolePermDTO asOperationRolePermDTO() {
        throw new IllegalStateException(String.format("%s is not an OperationRolePermDTO", this));
    }

    @Generated(IRW.CODE_GENERATOR)
    public void ifOperationRolePermDTO(Consumer<OperationRolePermDTO> action) {
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<OperationRolePermDTO> toOperationRolePermDTO() {
        return Optional.empty();
    }

    @Generated(IRW.CODE_GENERATOR)
    public boolean isAttributeRolePermDTO() {
        return false;
    }

    @Generated(IRW.CODE_GENERATOR)
    public AttributeRolePermDTO asAttributeRolePermDTO() {
        throw new IllegalStateException(String.format("%s is not an AttributeRolePermDTO", this));
    }

    @Generated(IRW.CODE_GENERATOR)
    public void ifAttributeRolePermDTO(Consumer<AttributeRolePermDTO> action) {
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<AttributeRolePermDTO> toAttributeRolePermDTO() {
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
    public UUID getId() {
        return id;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setId(UUID id) {
        this.id = id;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<PermissionDTO> getPermission() {
        return permission;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setPermission(Optional<PermissionDTO> permission) {
        this.permission = permission;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<RoleDTO> getRole() {
        return role;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setRole(Optional<RoleDTO> role) {
        this.role = role;
    }

    public enum _Type {

        /**
         * 操作类权限
         */
        OP(OperationRolePermDTO.class),
        /**
         * 属性类权限
         */
        ATTR(AttributeRolePermDTO.class);

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
