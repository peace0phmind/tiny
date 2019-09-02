package cn.know.act.proton.system.service.criteria;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import cn.know.act.proton.core.service.filter.StringFilter;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * 属性权限
 */
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AttributePermCriteria extends PermissionCriteria implements Serializable {

    /**
     * 属性名称
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter attributeName;

    @Generated(IRW.CODE_GENERATOR)
    public boolean isAttributePermCriteria() {
        return true;
    }

    @Generated(IRW.CODE_GENERATOR)
    public AttributePermCriteria asAttributePermCriteria() {
        return this;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void ifAttributePermCriteria(Consumer<AttributePermCriteria> action) {
        action.accept(this);
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<AttributePermCriteria> toAttributePermCriteria() {
        return Optional.of(this);
    }

    @Generated(IRW.CODE_GENERATOR)
    public StringFilter getAttributeName() {
        return attributeName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setAttributeName(StringFilter attributeName) {
        this.attributeName = attributeName;
    }
}
