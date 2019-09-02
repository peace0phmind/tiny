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
import java.util.Set;
import java.util.LinkedHashSet;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

/**
 * 菜单
 */
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@EqualsAndHashCode
public class MenuDTO extends BaseDTO<UUID> implements Serializable {

    @Generated(IRW.CODE_GENERATOR)
    private UUID id;

    /**
     * 名称
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 255) String> name;

    /**
     * 图标
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 255) String> icon;

    /**
     * 标题
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 255) String> title;

    /**
     * 路径
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 255) String> path;

    /**
     * 组件
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 255) String> component;

    /**
     * 面包屑中隐藏
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<Boolean> hideInBread;

    /**
     * 菜单中隐藏
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<Boolean> hideInMenu;

    /**
     * 切换标签后不缓存
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<Boolean> notCache;

    /**
     * 角色
     */
    @Generated(IRW.CODE_GENERATOR)
    @JsonDeserialize(as = LinkedHashSet.class)
    private Set<RoleDTO> roles;

    /**
     * 父节点
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<MenuDTO> _parent;

    /**
     * 子节点
     */
    @Generated(IRW.CODE_GENERATOR)
    private List<MenuDTO> _children;

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
    public void addRole(RoleDTO item) {
        if (this.roles == null) {
            this.roles = new LinkedHashSet<>();
        }
        this.roles.add(item);
    }

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
    public Optional<String> getIcon() {
        return icon;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setIcon(Optional<String> icon) {
        this.icon = icon;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<String> getTitle() {
        return title;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setTitle(Optional<String> title) {
        this.title = title;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<String> getPath() {
        return path;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setPath(Optional<String> path) {
        this.path = path;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<String> getComponent() {
        return component;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setComponent(Optional<String> component) {
        this.component = component;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<Boolean> getHideInBread() {
        return hideInBread;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setHideInBread(Optional<Boolean> hideInBread) {
        this.hideInBread = hideInBread;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<Boolean> getHideInMenu() {
        return hideInMenu;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setHideInMenu(Optional<Boolean> hideInMenu) {
        this.hideInMenu = hideInMenu;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<Boolean> getNotCache() {
        return notCache;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setNotCache(Optional<Boolean> notCache) {
        this.notCache = notCache;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Set<RoleDTO> getRoles() {
        return roles;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<MenuDTO> get_parent() {
        return _parent;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void set_parent(Optional<MenuDTO> _parent) {
        this._parent = _parent;
    }

    @Generated(IRW.CODE_GENERATOR)
    public List<MenuDTO> get_children() {
        return _children;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void set_children(List<MenuDTO> _children) {
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
