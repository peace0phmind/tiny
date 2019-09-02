package cn.know.act.proton.system.service.criteria;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import cn.know.act.proton.core.service.filter.StringFilter;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import java.util.Optional;
import java.util.function.Consumer;
import javax.persistence.Transient;

/**
 * 权限
 */
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "_type")
@JsonSubTypes({ @JsonSubTypes.Type(value = OperationPermCriteria.class, name = "OP"), @JsonSubTypes.Type(value = AttributePermCriteria.class, name = "ATTR") })
public abstract class PermissionCriteria implements Serializable {

    /**
     * 模型名称
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter modelName;

    /**
     * 模块中文名称
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter modelCnName;

    /**
     * 模块uri路径
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter modelUri;

    @Generated(IRW.CODE_GENERATOR)
    @Transient
    private _Type _type = _Type.valueOf(this.getClass());

    @Generated(IRW.CODE_GENERATOR)
    public boolean isOperationPermCriteria() {
        return false;
    }

    @Generated(IRW.CODE_GENERATOR)
    public OperationPermCriteria asOperationPermCriteria() {
        throw new IllegalStateException(String.format("%s is not an OperationPermCriteria", this));
    }

    @Generated(IRW.CODE_GENERATOR)
    public void ifOperationPermCriteria(Consumer<OperationPermCriteria> action) {
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<OperationPermCriteria> toOperationPermCriteria() {
        return Optional.empty();
    }

    @Generated(IRW.CODE_GENERATOR)
    public boolean isAttributePermCriteria() {
        return false;
    }

    @Generated(IRW.CODE_GENERATOR)
    public AttributePermCriteria asAttributePermCriteria() {
        throw new IllegalStateException(String.format("%s is not an AttributePermCriteria", this));
    }

    @Generated(IRW.CODE_GENERATOR)
    public void ifAttributePermCriteria(Consumer<AttributePermCriteria> action) {
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<AttributePermCriteria> toAttributePermCriteria() {
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
    public StringFilter getModelName() {
        return modelName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setModelName(StringFilter modelName) {
        this.modelName = modelName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public StringFilter getModelCnName() {
        return modelCnName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setModelCnName(StringFilter modelCnName) {
        this.modelCnName = modelCnName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public StringFilter getModelUri() {
        return modelUri;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setModelUri(StringFilter modelUri) {
        this.modelUri = modelUri;
    }

    public enum _Type {

        /**
         * 操作权限
         */
        OP(OperationPermCriteria.class),
        /**
         * 属性权限
         */
        ATTR(AttributePermCriteria.class);

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
