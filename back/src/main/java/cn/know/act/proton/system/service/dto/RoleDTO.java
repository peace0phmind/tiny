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
import cn.know.act.proton.system.domain.Role;

/**
 * 角色
 */
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@EqualsAndHashCode
public class RoleDTO extends BaseDTO<UUID> implements Serializable {

    @Generated(IRW.CODE_GENERATOR)
    private UUID id;

    /**
     * 名称
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 255) String> name;

    /**
     * 内部名称
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 255) String> innerName;

    /**
     * 描述
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 255) String> description;

    /**
     * 默认角色
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<Boolean> defaultRole;

    /**
     * 权限
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    @JsonDeserialize(as = LinkedHashSet.class)
    private Set<RolePermissionDTO> permissions;

    /**
     * 类型
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<Role.Type> type;

    /**
     * 菜单
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    @JsonDeserialize(as = LinkedHashSet.class)
    private Set<MenuDTO> menus;

    /**
     * 用户
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    @JsonDeserialize(as = LinkedHashSet.class)
    private Set<UserDTO> users;

    @Generated(IRW.CODE_GENERATOR)
    private String _instanceName;

    @Generated(IRW.CODE_GENERATOR)
    public void addPermission(RolePermissionDTO item) {
        if (this.permissions == null) {
            this.permissions = new LinkedHashSet<>();
        }
        this.permissions.add(item);
    }

    @Generated(IRW.CODE_GENERATOR)
    public void addMenu(MenuDTO item) {
        if (this.menus == null) {
            this.menus = new LinkedHashSet<>();
        }
        this.menus.add(item);
    }

    @Generated(IRW.CODE_GENERATOR)
    public void addUser(UserDTO item) {
        if (this.users == null) {
            this.users = new LinkedHashSet<>();
        }
        this.users.add(item);
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
    public Optional<String> getInnerName() {
        return innerName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setInnerName(Optional<String> innerName) {
        this.innerName = innerName;
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
    public Optional<Boolean> getDefaultRole() {
        return defaultRole;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setDefaultRole(Optional<Boolean> defaultRole) {
        this.defaultRole = defaultRole;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Set<RolePermissionDTO> getPermissions() {
        return permissions;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setPermissions(Set<RolePermissionDTO> permissions) {
        this.permissions = permissions;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<Role.Type> getType() {
        return type;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setType(Optional<Role.Type> type) {
        this.type = type;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Set<MenuDTO> getMenus() {
        return menus;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setMenus(Set<MenuDTO> menus) {
        this.menus = menus;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Set<UserDTO> getUsers() {
        return users;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setUsers(Set<UserDTO> users) {
        this.users = users;
    }
}
