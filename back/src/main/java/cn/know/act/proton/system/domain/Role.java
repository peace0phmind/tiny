package cn.know.act.proton.system.domain;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import lombok.AllArgsConstructor;
import javax.persistence.Table;
import javax.persistence.Index;
import javax.persistence.Entity;
import java.util.UUID;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.LinkedHashSet;
import javax.persistence.OneToMany;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.ConstraintMode;
import java.text.MessageFormat;
import cn.know.act.proton.core.service.filter.Filter;
import java.util.Objects;
import org.springframework.security.core.GrantedAuthority;

/**
 * 角色
 */
@ToString
@NoArgsConstructor
@DynamicUpdate
@AllArgsConstructor
@Table(name = "sys_role", indexes = { @Index(name = "idx_sys_role_type_", columnList = "type_") })
@Entity(name = "SysRole")
public class Role implements Serializable, GrantedAuthority {

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
     * 内部名称
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 255)
    @Column(name = "inner_name", length = 255)
    private String innerName;

    /**
     * 描述
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 255)
    @Column(name = "description", length = 255)
    private String description;

    /**
     * 默认角色
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "default_role")
    private Boolean defaultRole = false;

    /**
     * 权限
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    @OneToMany(mappedBy = "role")
    private Set<RolePermission> permissions = new LinkedHashSet<>();

    /**
     * 类型
     */
    @Generated(IRW.CODE_GENERATOR)
    @Enumerated(value = EnumType.STRING)
    @Column(name = "type_", length = 11)
    private Role.Type type = Role.Type.STANDARD;

    /**
     * 菜单
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    @ManyToMany()
    @JoinTable(name = "sys_menu_role", indexes = { @Index(name = "sys_menu_role_role_id", columnList = "role_id"), @Index(name = "sys_menu_role_menu_id", columnList = "menu_id") }, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseForeignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), inverseJoinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "id"))
    private Set<Menu> menus = new LinkedHashSet<>();

    /**
     * 用户
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    @ManyToMany()
    @JoinTable(name = "sys_role_user", indexes = { @Index(name = "sys_role_user_role_id", columnList = "role_id"), @Index(name = "sys_role_user_user_id", columnList = "user_id") }, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseForeignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> users = new LinkedHashSet<>();

    @Override
    public String getAuthority() {
        return innerName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void addPermission(RolePermission item) {
        if (this.permissions == null) {
            this.permissions = new LinkedHashSet<>();
        }
        item.setRole(this);
        this.permissions.add(item);
    }

    @Generated(IRW.CODE_GENERATOR)
    public void addMenu(Menu item) {
        if (this.menus == null) {
            this.menus = new LinkedHashSet<>();
        }
        this.menus.add(item);
    }

    @Generated(IRW.CODE_GENERATOR)
    public void addUser(User item) {
        if (this.users == null) {
            this.users = new LinkedHashSet<>();
        }
        this.users.add(item);
    }

    @Generated(IRW.CODE_GENERATOR)
    public String get_instanceName() {
        return MessageFormat.format("{0}", id);
    }

    @Override
    @Generated(IRW.CODE_GENERATOR)
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }
        Role that = (Role) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(innerName, that.innerName) && Objects.equals(description, that.description) && Objects.equals(defaultRole, that.defaultRole) && Objects.equals(type, that.type);
    }

    @Override
    @Generated(IRW.CODE_GENERATOR)
    public int hashCode() {
        return Objects.hash(id, name, innerName, description, defaultRole, type);
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
    public String getInnerName() {
        return innerName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setInnerName(String innerName) {
        this.innerName = innerName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public String getDescription() {
        return description;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setDescription(String description) {
        this.description = description;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Boolean isDefaultRole() {
        return defaultRole == null ? Boolean.FALSE : defaultRole;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setDefaultRole(Boolean defaultRole) {
        this.defaultRole = defaultRole == null ? Boolean.FALSE : defaultRole;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Set<RolePermission> getPermissions() {
        return permissions;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setPermissions(Set<RolePermission> permissions) {
        this.permissions = permissions;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Role.Type getType() {
        return type;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setType(Role.Type type) {
        this.type = type;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Set<Menu> getMenus() {
        return menus;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Set<User> getUsers() {
        return users;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    /**
     * 类型
     */
    public enum Type {

        /**
         * 标准
         */
        STANDARD,
        /**
         * 超级
         */
        SUPER
    }

    public static class TypeFilter extends Filter<Type> {
    }
}
