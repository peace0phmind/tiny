package cn.know.act.proton.system.domain;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import java.util.UUID;
import cn.know.act.proton.core.jpa.tree.TreeNode;
import lombok.AllArgsConstructor;
import javax.persistence.Table;
import javax.persistence.Index;
import javax.persistence.Entity;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.LinkedHashSet;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.ConstraintMode;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import java.text.MessageFormat;
import cn.know.act.proton.core.jpa.tree.TreePath;
import java.util.Objects;

/**
 * 菜单
 */
@ToString
@NoArgsConstructor
@DynamicUpdate
@AllArgsConstructor
@Table(name = "sys_menu", indexes = { @Index(name = "idx_sys_menu__parent_id", columnList = "_parent_id") })
@Entity(name = "SysMenu")
public class Menu extends TreeNode<Menu, UUID> implements Serializable {

    @Generated(IRW.CODE_GENERATOR)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @org.hibernate.annotations.Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "char(36)")
    private UUID id;

    /**
     * 名称
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 255)
    @Column(name = "name_", length = 255)
    private String name;

    /**
     * 图标
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 255)
    @Column(name = "icon", length = 255)
    private String icon;

    /**
     * 标题
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 255)
    @Column(name = "title", length = 255)
    private String title;

    /**
     * 路径
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 255)
    @Column(name = "path", length = 255)
    private String path;

    /**
     * 组件
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 255)
    @Column(name = "component", length = 255)
    private String component;

    /**
     * 面包屑中隐藏
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "hide_in_bread")
    private Boolean hideInBread = false;

    /**
     * 菜单中隐藏
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "hide_in_menu")
    private Boolean hideInMenu = false;

    /**
     * 切换标签后不缓存
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "not_cache")
    private Boolean notCache = false;

    /**
     * 角色
     */
    @Generated(IRW.CODE_GENERATOR)
    @ManyToMany()
    @JoinTable(name = "sys_menu_role", indexes = { @Index(name = "sys_menu_role_menu_id", columnList = "menu_id"), @Index(name = "sys_menu_role_role_id", columnList = "role_id") }, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), joinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "id"), inverseForeignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new LinkedHashSet<>();

    /**
     * 父节点
     */
    @Generated(IRW.CODE_GENERATOR)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_parent_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Menu _parent;

    @Generated(IRW.CODE_GENERATOR)
    public void addRole(Role item) {
        if (this.roles == null) {
            this.roles = new LinkedHashSet<>();
        }
        this.roles.add(item);
    }

    @Generated(IRW.CODE_GENERATOR)
    public String get_instanceName() {
        return MessageFormat.format("{0}", title);
    }

    @Override
    @Generated(IRW.CODE_GENERATOR)
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Menu)) {
            return false;
        }
        Menu that = (Menu) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(icon, that.icon) && Objects.equals(title, that.title) && Objects.equals(path, that.path) && Objects.equals(component, that.component) && Objects.equals(hideInBread, that.hideInBread) && Objects.equals(hideInMenu, that.hideInMenu) && Objects.equals(notCache, that.notCache) && (_parent != null && that._parent != null ? Objects.equals(_parent.getId(), that._parent.getId()) : _parent == that._parent);
    }

    @Override
    @Generated(IRW.CODE_GENERATOR)
    public int hashCode() {
        return Objects.hash(id, name, icon, title, path, component, hideInBread, hideInMenu, notCache, _parent != null ? _parent.getId() : null);
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
    public String getName() {
        return name;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setName(String name) {
        this.name = name;
    }

    @Generated(IRW.CODE_GENERATOR)
    public String getIcon() {
        return icon;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Generated(IRW.CODE_GENERATOR)
    public String getTitle() {
        return title;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setTitle(String title) {
        this.title = title;
    }

    @Generated(IRW.CODE_GENERATOR)
    public String getPath() {
        return path;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setPath(String path) {
        this.path = path;
    }

    @Generated(IRW.CODE_GENERATOR)
    public String getComponent() {
        return component;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setComponent(String component) {
        this.component = component;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Boolean isHideInBread() {
        return hideInBread == null ? Boolean.FALSE : hideInBread;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setHideInBread(Boolean hideInBread) {
        this.hideInBread = hideInBread == null ? Boolean.FALSE : hideInBread;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Boolean isHideInMenu() {
        return hideInMenu == null ? Boolean.FALSE : hideInMenu;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setHideInMenu(Boolean hideInMenu) {
        this.hideInMenu = hideInMenu == null ? Boolean.FALSE : hideInMenu;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Boolean isNotCache() {
        return notCache == null ? Boolean.FALSE : notCache;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setNotCache(Boolean notCache) {
        this.notCache = notCache == null ? Boolean.FALSE : notCache;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Set<Role> getRoles() {
        return roles;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Menu get_parent() {
        return _parent;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void set_parent(Menu _parent) {
        this._parent = _parent;
    }

    @Table(name = "sys_menu__tp")
    @Entity(name = "SysMenu__tp")
    public static class MenuTreePath extends TreePath<Menu> {
    }
}
