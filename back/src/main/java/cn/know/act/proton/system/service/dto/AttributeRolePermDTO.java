package cn.know.act.proton.system.service.dto;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import cn.know.act.proton.system.domain.AttributeRolePerm;
import java.util.Optional;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import java.util.function.Consumer;

/**
 * 属性类权限
 */
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@EqualsAndHashCode(callSuper = true)
public class AttributeRolePermDTO extends RolePermissionDTO implements Serializable {

    /**
     * 类型
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<AttributeRolePerm.Type> type;

    @Generated(IRW.CODE_GENERATOR)
    public boolean isAttributeRolePermDTO() {
        return true;
    }

    @Generated(IRW.CODE_GENERATOR)
    public AttributeRolePermDTO asAttributeRolePermDTO() {
        return this;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void ifAttributeRolePermDTO(Consumer<AttributeRolePermDTO> action) {
        action.accept(this);
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<AttributeRolePermDTO> toAttributeRolePermDTO() {
        return Optional.of(this);
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<AttributeRolePerm.Type> getType() {
        return type;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setType(Optional<AttributeRolePerm.Type> type) {
        this.type = type;
    }
}
