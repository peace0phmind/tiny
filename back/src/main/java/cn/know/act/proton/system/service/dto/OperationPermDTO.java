package cn.know.act.proton.system.service.dto;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import cn.know.act.proton.system.domain.OperationPerm;
import java.util.Optional;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import java.util.function.Consumer;
import java.util.List;

/**
 * 操作权限
 */
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@EqualsAndHashCode(callSuper = true)
public class OperationPermDTO extends PermissionDTO implements Serializable {

    private List<OperationPerm.Type> types;

    /**
     * 操作类型
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<OperationPerm.Type> type;

    public List<OperationPerm.Type> getTypes() {
        return types;
    }

    public void setTypes(List<OperationPerm.Type> types) {
        this.types = types;
    }

    @Generated(IRW.CODE_GENERATOR)
    public boolean isOperationPermDTO() {
        return true;
    }

    @Generated(IRW.CODE_GENERATOR)
    public OperationPermDTO asOperationPermDTO() {
        return this;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void ifOperationPermDTO(Consumer<OperationPermDTO> action) {
        action.accept(this);
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<OperationPermDTO> toOperationPermDTO() {
        return Optional.of(this);
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<OperationPerm.Type> getType() {
        return type;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setType(Optional<OperationPerm.Type> type) {
        this.type = type;
    }
}
