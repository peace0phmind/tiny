package cn.know.act.proton.system.service.criteria;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import cn.know.act.proton.core.service.filter.StringFilter;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import cn.know.act.proton.core.service.filter.LocalDateFilter;
import cn.know.act.proton.core.service.filter.BooleanFilter;
import cn.know.act.proton.core.service.filter.UUIDFilter;
import cn.know.act.proton.system.domain.User;

/**
 * 用户
 */
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class UserCriteria implements Serializable {

    /**
     * 姓名
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter name;

    /**
     * 登录名
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter username;

    /**
     * 电话
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter phone;

    /**
     * 邮箱
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter email;

    /**
     * 生日
     */
    @Generated(IRW.CODE_GENERATOR)
    private LocalDateFilter birthday;

    /**
     * 可用
     */
    @Generated(IRW.CODE_GENERATOR)
    private BooleanFilter enabled;

    /**
     * 部门
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    private UUIDFilter department;

    /**
     * 角色列表
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    private UUIDFilter roles;

    /**
     * 性别
     */
    @Generated(IRW.CODE_GENERATOR)
    private User.SexFilter sex;

    @Generated(IRW.CODE_GENERATOR)
    public StringFilter getName() {
        return name;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setName(StringFilter name) {
        this.name = name;
    }

    @Generated(IRW.CODE_GENERATOR)
    public StringFilter getUsername() {
        return username;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setUsername(StringFilter username) {
        this.username = username;
    }

    @Generated(IRW.CODE_GENERATOR)
    public StringFilter getPhone() {
        return phone;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setPhone(StringFilter phone) {
        this.phone = phone;
    }

    @Generated(IRW.CODE_GENERATOR)
    public StringFilter getEmail() {
        return email;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setEmail(StringFilter email) {
        this.email = email;
    }

    @Generated(IRW.CODE_GENERATOR)
    public LocalDateFilter getBirthday() {
        return birthday;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setBirthday(LocalDateFilter birthday) {
        this.birthday = birthday;
    }

    @Generated(IRW.CODE_GENERATOR)
    public BooleanFilter getEnabled() {
        return enabled;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setEnabled(BooleanFilter enabled) {
        this.enabled = enabled;
    }

    @Generated(IRW.CODE_GENERATOR)
    public UUIDFilter getDepartment() {
        return department;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setDepartment(UUIDFilter department) {
        this.department = department;
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
    public User.SexFilter getSex() {
        return sex;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setSex(User.SexFilter sex) {
        this.sex = sex;
    }
}
