package cn.know.act.proton.system.service.criteria;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import cn.know.act.proton.core.service.filter.StringFilter;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import cn.know.act.proton.core.service.filter.UUIDFilter;

/**
 * 部门
 */
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class DepartmentCriteria implements Serializable {

    /**
     * 名称
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter name;

    /**
     * 代码
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter code;

    /**
     * 描述
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter description;

    /**
     * 父节点
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    private UUIDFilter _parent;

    @Generated(IRW.CODE_GENERATOR)
    public StringFilter getName() {
        return name;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setName(StringFilter name) {
        this.name = name;
    }

    @Generated(IRW.CODE_GENERATOR)
    public StringFilter getCode() {
        return code;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setCode(StringFilter code) {
        this.code = code;
    }

    @Generated(IRW.CODE_GENERATOR)
    public StringFilter getDescription() {
        return description;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setDescription(StringFilter description) {
        this.description = description;
    }

    @Generated(IRW.CODE_GENERATOR)
    public UUIDFilter get_parent() {
        return _parent;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void set_parent(UUIDFilter _parent) {
        this._parent = _parent;
    }
}
