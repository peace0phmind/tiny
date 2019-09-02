package cn.know.act.proton.system.service.dto;

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
import java.util.List;

/**
 * 部门
 */
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@EqualsAndHashCode
public class DepartmentDTO extends BaseDTO<UUID> implements Serializable {

    @Generated(IRW.CODE_GENERATOR)
    private UUID id;

    /**
     * 名称
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 60) String> name;

    /**
     * 代码
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 20) String> code;

    /**
     * 描述
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 1024) String> description;

    /**
     * 父节点
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<DepartmentDTO> _parent;

    /**
     * 子节点
     */
    @Generated(IRW.CODE_GENERATOR)
    private List<DepartmentDTO> _children;

    /**
     * 高亮
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<Boolean> _highlight;

    /**
     * 选中
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<Boolean> _checked;

    /**
     * 是否叶节点
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<Boolean> _leaf;

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
    public Optional<String> getCode() {
        return code;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setCode(Optional<String> code) {
        this.code = code;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<String> getDescription() {
        return description;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setDescription(Optional<String> description) {
        this.description = description;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<DepartmentDTO> get_parent() {
        return _parent;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void set_parent(Optional<DepartmentDTO> _parent) {
        this._parent = _parent;
    }

    @Generated(IRW.CODE_GENERATOR)
    public List<DepartmentDTO> get_children() {
        return _children;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void set_children(List<DepartmentDTO> _children) {
        this._children = _children;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<Boolean> get_highlight() {
        return _highlight;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void set_highlight(Optional<Boolean> _highlight) {
        this._highlight = _highlight;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<Boolean> get_checked() {
        return _checked;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void set_checked(Optional<Boolean> _checked) {
        this._checked = _checked;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<Boolean> get_leaf() {
        return _leaf;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void set_leaf(Optional<Boolean> _leaf) {
        this._leaf = _leaf;
    }
}
