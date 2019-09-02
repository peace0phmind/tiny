package cn.know.act.proton.system.service.criteria;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import cn.know.act.proton.core.service.filter.StringFilter;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import cn.know.act.proton.core.service.filter.BooleanFilter;
import cn.know.act.proton.core.service.filter.UUIDFilter;

/**
 * 菜单
 */
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class MenuCriteria implements Serializable {

    /**
     * 名称
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter name;

    /**
     * 图标
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter icon;

    /**
     * 标题
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter title;

    /**
     * 路径
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter path;

    /**
     * 组件
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter component;

    /**
     * 面包屑中隐藏
     */
    @Generated(IRW.CODE_GENERATOR)
    private BooleanFilter hideInBread;

    /**
     * 菜单中隐藏
     */
    @Generated(IRW.CODE_GENERATOR)
    private BooleanFilter hideInMenu;

    /**
     * 切换标签后不缓存
     */
    @Generated(IRW.CODE_GENERATOR)
    private BooleanFilter notCache;

    /**
     * 角色
     */
    @Generated(IRW.CODE_GENERATOR)
    private UUIDFilter roles;

    /**
     * 父节点
     */
    @Generated(IRW.CODE_GENERATOR)
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
    public StringFilter getIcon() {
        return icon;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setIcon(StringFilter icon) {
        this.icon = icon;
    }

    @Generated(IRW.CODE_GENERATOR)
    public StringFilter getTitle() {
        return title;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setTitle(StringFilter title) {
        this.title = title;
    }

    @Generated(IRW.CODE_GENERATOR)
    public StringFilter getPath() {
        return path;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setPath(StringFilter path) {
        this.path = path;
    }

    @Generated(IRW.CODE_GENERATOR)
    public StringFilter getComponent() {
        return component;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setComponent(StringFilter component) {
        this.component = component;
    }

    @Generated(IRW.CODE_GENERATOR)
    public BooleanFilter getHideInBread() {
        return hideInBread;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setHideInBread(BooleanFilter hideInBread) {
        this.hideInBread = hideInBread;
    }

    @Generated(IRW.CODE_GENERATOR)
    public BooleanFilter getHideInMenu() {
        return hideInMenu;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setHideInMenu(BooleanFilter hideInMenu) {
        this.hideInMenu = hideInMenu;
    }

    @Generated(IRW.CODE_GENERATOR)
    public BooleanFilter getNotCache() {
        return notCache;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setNotCache(BooleanFilter notCache) {
        this.notCache = notCache;
    }

    @Generated(IRW.CODE_GENERATOR)
    public UUIDFilter getRoles() {
        return roles;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setRoles(UUIDFilter roles) {
        this.roles = roles;
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
