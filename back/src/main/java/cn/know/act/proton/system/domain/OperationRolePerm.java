package cn.know.act.proton.system.domain;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import javax.persistence.Column;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.Objects;

/**
 * 操作类权限
 */
@ToString
@NoArgsConstructor
@DynamicUpdate
@Entity
@DiscriminatorValue(value = "OP")
public class OperationRolePerm extends RolePermission implements Serializable {

    /**
     * 允许
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "op_allow")
    private Boolean allow = true;

    @Generated(IRW.CODE_GENERATOR)
    public boolean isOperationRolePerm() {
        return true;
    }

    @Generated(IRW.CODE_GENERATOR)
    public OperationRolePerm asOperationRolePerm() {
        return this;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void ifOperationRolePerm(Consumer<OperationRolePerm> action) {
        action.accept(this);
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<OperationRolePerm> toOperationRolePerm() {
        return Optional.of(this);
    }

    @Override
    @Generated(IRW.CODE_GENERATOR)
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OperationRolePerm)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        OperationRolePerm that = (OperationRolePerm) o;
        return Objects.equals(allow, that.allow);
    }

    @Override
    @Generated(IRW.CODE_GENERATOR)
    public int hashCode() {
        return Objects.hash(super.hashCode(), allow);
    }

    @Generated(IRW.CODE_GENERATOR)
    public Boolean isAllow() {
        return allow == null ? Boolean.FALSE : allow;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setAllow(Boolean allow) {
        this.allow = allow == null ? Boolean.FALSE : allow;
    }
}
