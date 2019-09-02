package cn.know.act.proton.system.service.dto;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import java.util.Optional;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import javax.validation.constraints.Size;
import java.util.function.Consumer;

/**
 * 属性权限
 */
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@EqualsAndHashCode(callSuper = true)
public class AttributePermDTO extends PermissionDTO implements Serializable {

    /**
     * 属性名称
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 255) String> attributeName;

    @Generated(IRW.CODE_GENERATOR)
    public boolean isAttributePermDTO() {
        return true;
    }

    @Generated(IRW.CODE_GENERATOR)
    public AttributePermDTO asAttributePermDTO() {
        return this;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void ifAttributePermDTO(Consumer<AttributePermDTO> action) {
        action.accept(this);
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<AttributePermDTO> toAttributePermDTO() {
        return Optional.of(this);
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<String> getAttributeName() {
        return attributeName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setAttributeName(Optional<String> attributeName) {
        this.attributeName = attributeName;
    }
}
