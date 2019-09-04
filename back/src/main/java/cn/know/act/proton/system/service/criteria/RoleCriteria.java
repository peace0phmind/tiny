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
import cn.know.act.proton.system.domain.Role;

/**
 * 角色
 */
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class RoleCriteria implements Serializable {

    /**
     * 名称
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter name;

    /**
     * 内部名称
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter innerName;

    /**
     * 描述
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter description;

    /**
     * 默认角色
     */
    @Generated(IRW.CODE_GENERATOR)
    private BooleanFilter defaultRole;

    /**
     * 权限
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    private UUIDFilter permissions;

    /**
     * 类型
     */
    @Generated(IRW.CODE_GENERATOR)
    private Role.TypeFilter type;

    /**
     * 菜单
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    private UUIDFilter menus;

    /**
     * 用户
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    private UUIDFilter users;

    @Generated(IRW.CODE_GENERATOR)
    public StringFilter getName() {
        return name;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setName(StringFilter name) {
        this.name = name;
    }

    @Generated(IRW.CODE_GENERATOR)
    public StringFilter getInnerName() {
        return innerName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setInnerName(StringFilter innerName) {
        this.innerName = innerName;
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
    public BooleanFilter getDefaultRole() {
        return defaultRole;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setDefaultRole(BooleanFilter defaultRole) {
        this.defaultRole = defaultRole;
    }

    @Generated(IRW.CODE_GENERATOR)
    public UUIDFilter getPermissions() {
        return permissions;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setPermissions(UUIDFilter permissions) {
        this.permissions = permissions;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Role.TypeFilter getType() {
        return type;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setType(Role.TypeFilter type) {
        this.type = type;
    }

    @Generated(IRW.CODE_GENERATOR)
    public UUIDFilter getMenus() {
        return menus;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setMenus(UUIDFilter menus) {
        this.menus = menus;
    }

    @Generated(IRW.CODE_GENERATOR)
    public UUIDFilter getUsers() {
        return users;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setUsers(UUIDFilter users) {
        this.users = users;
    }
}
