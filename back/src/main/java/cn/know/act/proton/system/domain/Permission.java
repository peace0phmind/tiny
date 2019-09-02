package cn.know.act.proton.system.domain;

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
import java.text.MessageFormat;
import java.util.Optional;
import java.util.function.Consumer;
import javax.persistence.Transient;
import java.util.Objects;

/**
 * 权限
 */
@ToString
@NoArgsConstructor
@DynamicUpdate
@AllArgsConstructor
@Table(name = "sys_permission")
@Entity(name = "SysPermission")
@DiscriminatorColumn(name = "_type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Permission implements Serializable {

    @Generated(IRW.CODE_GENERATOR)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @org.hibernate.annotations.Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "char(36)")
    private UUID id;

    /**
     * 模型名称
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 255)
    @Column(name = "model_name", length = 255)
    private String modelName;

    /**
     * 模块中文名称
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 255)
    @Column(name = "model_cn_name", length = 255)
    private String modelCnName;

    /**
     * 模块uri路径
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 255)
    @Column(name = "model_uri", length = 255)
    private String modelUri;

    @Generated(IRW.CODE_GENERATOR)
    @Transient
    private _Type _type = _Type.valueOf(this.getClass());

    @Generated(IRW.CODE_GENERATOR)
    public String get_instanceName() {
        return MessageFormat.format("{0}", id);
    }

    @Generated(IRW.CODE_GENERATOR)
    public boolean isOperationPerm() {
        return false;
    }

    @Generated(IRW.CODE_GENERATOR)
    public OperationPerm asOperationPerm() {
        throw new IllegalStateException(String.format("%s is not an OperationPerm", this));
    }

    @Generated(IRW.CODE_GENERATOR)
    public void ifOperationPerm(Consumer<OperationPerm> action) {
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<OperationPerm> toOperationPerm() {
        return Optional.empty();
    }

    @Generated(IRW.CODE_GENERATOR)
    public boolean isAttributePerm() {
        return false;
    }

    @Generated(IRW.CODE_GENERATOR)
    public AttributePerm asAttributePerm() {
        throw new IllegalStateException(String.format("%s is not an AttributePerm", this));
    }

    @Generated(IRW.CODE_GENERATOR)
    public void ifAttributePerm(Consumer<AttributePerm> action) {
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<AttributePerm> toAttributePerm() {
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
        if (!(o instanceof Permission)) {
            return false;
        }
        Permission that = (Permission) o;
        return Objects.equals(id, that.id) && Objects.equals(modelName, that.modelName) && Objects.equals(modelCnName, that.modelCnName) && Objects.equals(modelUri, that.modelUri);
    }

    @Override
    @Generated(IRW.CODE_GENERATOR)
    public int hashCode() {
        return Objects.hash(id, modelName, modelCnName, modelUri);
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
    public String getModelName() {
        return modelName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public String getModelCnName() {
        return modelCnName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setModelCnName(String modelCnName) {
        this.modelCnName = modelCnName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public String getModelUri() {
        return modelUri;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setModelUri(String modelUri) {
        this.modelUri = modelUri;
    }

    public enum _Type {

        /**
         * 操作权限
         */
        OP(OperationPerm.class),
        /**
         * 属性权限
         */
        ATTR(AttributePerm.class);

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
