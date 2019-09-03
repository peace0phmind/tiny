package cn.know.act.tiny.service.dto.item;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;
import cn.know.act.proton.core.util.BaseDTO;
import lombok.EqualsAndHashCode;
import java.util.UUID;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import java.util.Optional;
import javax.validation.constraints.Size;
import cn.know.act.tiny.service.dto.TestDTO;

/**
 * 测试item
 */
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@EqualsAndHashCode
public class ItemDTO extends BaseDTO<UUID> implements Serializable {

    @Generated(IRW.CODE_GENERATOR)
    private UUID id;

    /**
     * 名称
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 255) String> name;

    /**
     * 值
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 255) String> value;

    /**
     * test
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<TestDTO> test;

    @Generated(IRW.CODE_GENERATOR)
    private String _instanceName;

    @Generated(IRW.CODE_GENERATOR)
    public String get_instanceName() {
        return _instanceName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void set_instanceName(String _instanceName) {
        this._instanceName = _instanceName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public UUID getId() {
        return id;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setId(UUID id) {
        this.id = id;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<String> getName() {
        return name;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setName(Optional<String> name) {
        this.name = name;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<String> getValue() {
        return value;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setValue(Optional<String> value) {
        this.value = value;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<TestDTO> getTest() {
        return test;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setTest(Optional<TestDTO> test) {
        this.test = test;
    }
}
