package cn.know.act.proton.system.service.criteria;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import cn.know.act.proton.system.domain.AttributeRolePerm;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * 属性类权限
 */
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AttributeRolePermCriteria extends RolePermissionCriteria implements Serializable {

    /**
     * 类型
     */
    @Generated(IRW.CODE_GENERATOR)
    private AttributeRolePerm.TypeFilter type;

    @Generated(IRW.CODE_GENERATOR)
    public boolean isAttributeRolePermCriteria() {
        return true;
    }

    @Generated(IRW.CODE_GENERATOR)
    public AttributeRolePermCriteria asAttributeRolePermCriteria() {
        return this;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void ifAttributeRolePermCriteria(Consumer<AttributeRolePermCriteria> action) {
        action.accept(this);
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<AttributeRolePermCriteria> toAttributeRolePermCriteria() {
        return Optional.of(this);
    }

    @Generated(IRW.CODE_GENERATOR)
    public AttributeRolePerm.TypeFilter getType() {
        return type;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setType(AttributeRolePerm.TypeFilter type) {
        this.type = type;
    }
}
