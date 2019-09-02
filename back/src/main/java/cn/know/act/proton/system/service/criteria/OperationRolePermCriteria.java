package cn.know.act.proton.system.service.criteria;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import cn.know.act.proton.core.service.filter.BooleanFilter;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * 操作类权限
 */
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OperationRolePermCriteria extends RolePermissionCriteria implements Serializable {

    /**
     * 允许
     */
    @Generated(IRW.CODE_GENERATOR)
    private BooleanFilter allow;

    @Generated(IRW.CODE_GENERATOR)
    public boolean isOperationRolePermCriteria() {
        return true;
    }

    @Generated(IRW.CODE_GENERATOR)
    public OperationRolePermCriteria asOperationRolePermCriteria() {
        return this;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void ifOperationRolePermCriteria(Consumer<OperationRolePermCriteria> action) {
        action.accept(this);
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<OperationRolePermCriteria> toOperationRolePermCriteria() {
        return Optional.of(this);
    }

    @Generated(IRW.CODE_GENERATOR)
    public BooleanFilter getAllow() {
        return allow;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setAllow(BooleanFilter allow) {
        this.allow = allow;
    }
}
