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
import java.time.LocalDate;
import java.util.Set;
import java.util.LinkedHashSet;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import cn.know.act.proton.system.domain.User;

/**
 * 用户
 */
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@EqualsAndHashCode
public class UserDTO extends BaseDTO<UUID> implements Serializable {

    @Generated(IRW.CODE_GENERATOR)
    private UUID id;

    /**
     * 姓名
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 255) String> name;

    /**
     * 登录名
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 255) String> username;

    /**
     * 电话
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 255) String> phone;

    /**
     * 邮箱
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 255) String> email;

    /**
     * 生日
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<LocalDate> birthday;

    /**
     * 可用
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<Boolean> enabled;

    /**
     * 部门
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<DepartmentDTO> department;

    /**
     * 角色列表
     */
    @Generated(IRW.CODE_GENERATOR)
    @JsonDeserialize(as = LinkedHashSet.class)
    private Set<RoleDTO> roles;

    /**
     * 性别
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<User.Sex> sex;

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
    public Optional<String> getUsername() {
        return username;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setUsername(Optional<String> username) {
        this.username = username;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<String> getPhone() {
        return phone;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setPhone(Optional<String> phone) {
        this.phone = phone;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<String> getEmail() {
        return email;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setEmail(Optional<String> email) {
        this.email = email;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<LocalDate> getBirthday() {
        return birthday;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setBirthday(Optional<LocalDate> birthday) {
        this.birthday = birthday;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<Boolean> getEnabled() {
        return enabled;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setEnabled(Optional<Boolean> enabled) {
        this.enabled = enabled;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<DepartmentDTO> getDepartment() {
        return department;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setDepartment(Optional<DepartmentDTO> department) {
        this.department = department;
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
    public Optional<User.Sex> getSex() {
        return sex;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setSex(Optional<User.Sex> sex) {
        this.sex = sex;
    }
}
