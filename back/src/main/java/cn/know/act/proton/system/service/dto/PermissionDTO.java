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
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import java.util.function.Consumer;
import javax.persistence.Transient;

/**
 * 权限
 */
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@EqualsAndHashCode
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "_type")
@JsonSubTypes({ @JsonSubTypes.Type(value = OperationPermDTO.class, name = "OP"), @JsonSubTypes.Type(value = AttributePermDTO.class, name = "ATTR") })
public abstract class PermissionDTO extends BaseDTO<UUID> implements Serializable {

    @Generated(IRW.CODE_GENERATOR)
    private UUID id;

    /**
     * 模型名称
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 255) String> modelName;

    /**
     * 模块中文名称
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 255) String> modelCnName;

    /**
     * 模块uri路径
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 255) String> modelUri;

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
    public boolean isOperationPermDTO() {
        return false;
    }

    @Generated(IRW.CODE_GENERATOR)
    public OperationPermDTO asOperationPermDTO() {
        throw new IllegalStateException(String.format("%s is not an OperationPermDTO", this));
    }

    @Generated(IRW.CODE_GENERATOR)
    public void ifOperationPermDTO(Consumer<OperationPermDTO> action) {
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<OperationPermDTO> toOperationPermDTO() {
        return Optional.empty();
    }

    @Generated(IRW.CODE_GENERATOR)
    public boolean isAttributePermDTO() {
        return false;
    }

    @Generated(IRW.CODE_GENERATOR)
    public AttributePermDTO asAttributePermDTO() {
        throw new IllegalStateException(String.format("%s is not an AttributePermDTO", this));
    }

    @Generated(IRW.CODE_GENERATOR)
    public void ifAttributePermDTO(Consumer<AttributePermDTO> action) {
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<AttributePermDTO> toAttributePermDTO() {
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
    public Optional<String> getModelName() {
        return modelName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setModelName(Optional<String> modelName) {
        this.modelName = modelName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<String> getModelCnName() {
        return modelCnName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setModelCnName(Optional<String> modelCnName) {
        this.modelCnName = modelCnName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<String> getModelUri() {
        return modelUri;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setModelUri(Optional<String> modelUri) {
        this.modelUri = modelUri;
    }

    public enum _Type {

        /**
         * 操作权限
         */
        OP(OperationPermDTO.class),
        /**
         * 属性权限
         */
        ATTR(AttributePermDTO.class);

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
