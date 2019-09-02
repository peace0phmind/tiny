package cn.know.act.proton.system.service.criteria;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import cn.know.act.proton.system.domain.OperationPerm;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * 操作权限
 */
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OperationPermCriteria extends PermissionCriteria implements Serializable {

    /**
     * 操作类型
     */
    @Generated(IRW.CODE_GENERATOR)
    private OperationPerm.TypeFilter type;

    @Generated(IRW.CODE_GENERATOR)
    public boolean isOperationPermCriteria() {
        return true;
    }

    @Generated(IRW.CODE_GENERATOR)
    public OperationPermCriteria asOperationPermCriteria() {
        return this;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void ifOperationPermCriteria(Consumer<OperationPermCriteria> action) {
        action.accept(this);
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<OperationPermCriteria> toOperationPermCriteria() {
        return Optional.of(this);
    }

    @Generated(IRW.CODE_GENERATOR)
    public OperationPerm.TypeFilter getType() {
        return type;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setType(OperationPerm.TypeFilter type) {
        this.type = type;
    }
}
