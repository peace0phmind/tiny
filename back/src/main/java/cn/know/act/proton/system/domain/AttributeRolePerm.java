package cn.know.act.proton.system.domain;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Column;
import cn.know.act.proton.core.service.filter.Filter;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.Objects;

/**
 * 属性类权限
 */
@ToString
@NoArgsConstructor
@DynamicUpdate
@Entity
@DiscriminatorValue(value = "ATTR")
public class AttributeRolePerm extends RolePermission implements Serializable {

    /**
     * 类型
     */
    @Generated(IRW.CODE_GENERATOR)
    @Enumerated(value = EnumType.STRING)
    @Column(name = "attr_type", length = 12)
    private AttributeRolePerm.Type type = AttributeRolePerm.Type.MODIFY;

    @Generated(IRW.CODE_GENERATOR)
    public boolean isAttributeRolePerm() {
        return true;
    }

    @Generated(IRW.CODE_GENERATOR)
    public AttributeRolePerm asAttributeRolePerm() {
        return this;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void ifAttributeRolePerm(Consumer<AttributeRolePerm> action) {
        action.accept(this);
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<AttributeRolePerm> toAttributeRolePerm() {
        return Optional.of(this);
    }

    @Override
    @Generated(IRW.CODE_GENERATOR)
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AttributeRolePerm)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        AttributeRolePerm that = (AttributeRolePerm) o;
        return Objects.equals(type, that.type);
    }

    @Override
    @Generated(IRW.CODE_GENERATOR)
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }

    @Generated(IRW.CODE_GENERATOR)
    public AttributeRolePerm.Type getType() {
        return type;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setType(AttributeRolePerm.Type type) {
        this.type = type;
    }

    /**
     * 类型
     */
    public enum Type {

        /**
         * 可修改
         */
        MODIFY,
        /**
         * 只读
         */
        READ_ONLY,
        /**
         * 隐藏
         */
        HIDE
    }

    public static class TypeFilter extends Filter<Type> {
    }
}
