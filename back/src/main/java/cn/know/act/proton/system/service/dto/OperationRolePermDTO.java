package cn.know.act.proton.system.service.dto;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import java.util.Optional;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import java.util.function.Consumer;

/**
 * 操作类权限
 */
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@EqualsAndHashCode(callSuper = true)
public class OperationRolePermDTO extends RolePermissionDTO implements Serializable {

    /**
     * 允许
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<Boolean> allow;

    @Generated(IRW.CODE_GENERATOR)
    public boolean isOperationRolePermDTO() {
        return true;
    }

    @Generated(IRW.CODE_GENERATOR)
    public OperationRolePermDTO asOperationRolePermDTO() {
        return this;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void ifOperationRolePermDTO(Consumer<OperationRolePermDTO> action) {
        action.accept(this);
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<OperationRolePermDTO> toOperationRolePermDTO() {
        return Optional.of(this);
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<Boolean> getAllow() {
        return allow;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setAllow(Optional<Boolean> allow) {
        this.allow = allow;
    }
}
