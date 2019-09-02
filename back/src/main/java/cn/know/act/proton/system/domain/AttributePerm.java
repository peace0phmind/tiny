package cn.know.act.proton.system.domain;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import javax.validation.constraints.Size;
import javax.persistence.Column;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.Objects;

/**
 * 属性权限
 */
@ToString
@NoArgsConstructor
@DynamicUpdate
@Entity
@DiscriminatorValue(value = "ATTR")
public class AttributePerm extends Permission implements Serializable {

    /**
     * 属性名称
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 255)
    @Column(name = "attr_attribute_name", length = 255)
    private String attributeName;

    @Generated(IRW.CODE_GENERATOR)
    public boolean isAttributePerm() {
        return true;
    }

    @Generated(IRW.CODE_GENERATOR)
    public AttributePerm asAttributePerm() {
        return this;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void ifAttributePerm(Consumer<AttributePerm> action) {
        action.accept(this);
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<AttributePerm> toAttributePerm() {
        return Optional.of(this);
    }

    @Override
    @Generated(IRW.CODE_GENERATOR)
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AttributePerm)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        AttributePerm that = (AttributePerm) o;
        return Objects.equals(attributeName, that.attributeName);
    }

    @Override
    @Generated(IRW.CODE_GENERATOR)
    public int hashCode() {
        return Objects.hash(super.hashCode(), attributeName);
    }

    @Generated(IRW.CODE_GENERATOR)
    public String getAttributeName() {
        return attributeName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
}
