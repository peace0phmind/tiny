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
import java.time.LocalDate;
import javax.persistence.Transient;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.ConstraintMode;
import java.util.Set;
import java.util.LinkedHashSet;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import java.text.MessageFormat;
import cn.know.act.proton.core.service.filter.Filter;
import java.util.Objects;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

/**
 * 用户
 */
@ToString
@NoArgsConstructor
@DynamicUpdate
@AllArgsConstructor
@Table(name = "sys_user", indexes = { @Index(name = "idx_sys_user_department_id", columnList = "department_id"), @Index(name = "idx_sys_user_sex", columnList = "sex") })
@Entity(name = "SysUser")
public class User implements Serializable, UserDetails {

    @Generated(IRW.CODE_GENERATOR)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @org.hibernate.annotations.Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "char(36)")
    private UUID id;

    /**
     * 姓名
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 255)
    @Column(name = "name_", nullable = false, length = 255)
    private String name;

    /**
     * 登录名
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 255)
    @Column(name = "username", nullable = false, unique = true, length = 255)
    private String username;

    /**
     * 密码
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 255)
    @Column(name = "password_", length = 255)
    private String password;

    /**
     * 电话
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 255)
    @Column(name = "phone", length = 255)
    private String phone;

    /**
     * 邮箱
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 255)
    @Column(name = "email", length = 255)
    private String email;

    /**
     * 生日
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "birthday")
    private LocalDate birthday;

    /**
     * 账号未过期
     */
    @Generated(IRW.CODE_GENERATOR)
    @Transient
    private Boolean accountNonExpired = true;

    /**
     * 密码未过期
     */
    @Generated(IRW.CODE_GENERATOR)
    @Transient
    private Boolean credentialsNonExpired = true;

    /**
     * 账号未锁定
     */
    @Generated(IRW.CODE_GENERATOR)
    @Transient
    private Boolean accountNonLocked = true;

    /**
     * 可用
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "enabled")
    private Boolean enabled = true;

    /**
     * 部门
     */
    @Generated(IRW.CODE_GENERATOR)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Department department;

    /**
     * 角色列表
     */
    @Generated(IRW.CODE_GENERATOR)
    @ManyToMany()
    @JoinTable(name = "sys_role_user", indexes = { @Index(name = "sys_role_user_user_id", columnList = "user_id"), @Index(name = "sys_role_user_role_id", columnList = "role_id") }, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseForeignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new LinkedHashSet<>();

    /**
     * 性别
     */
    @Generated(IRW.CODE_GENERATOR)
    @Enumerated(value = EnumType.STRING)
    @Column(name = "sex", length = 9)
    private User.Sex sex;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired == null ? Boolean.FALSE : accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked == null ? Boolean.FALSE : accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired == null ? Boolean.FALSE : credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled == null ? Boolean.FALSE : enabled;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void addRole(Role item) {
        if (this.roles == null) {
            this.roles = new LinkedHashSet<>();
        }
        this.roles.add(item);
    }

    @Generated(IRW.CODE_GENERATOR)
    public String get_instanceName() {
        return MessageFormat.format("{0}", name);
    }

    @Override
    @Generated(IRW.CODE_GENERATOR)
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User that = (User) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) && Objects.equals(birthday, that.birthday) && Objects.equals(accountNonExpired, that.accountNonExpired) && Objects.equals(credentialsNonExpired, that.credentialsNonExpired) && Objects.equals(accountNonLocked, that.accountNonLocked) && Objects.equals(enabled, that.enabled) && (department != null && that.department != null ? Objects.equals(department.getId(), that.department.getId()) : department == that.department) && Objects.equals(sex, that.sex);
    }

    @Override
    @Generated(IRW.CODE_GENERATOR)
    public int hashCode() {
        return Objects.hash(id, name, username, password, phone, email, birthday, accountNonExpired, credentialsNonExpired, accountNonLocked, enabled, department != null ? department.getId() : null, sex);
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
    public String getUsername() {
        return username;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setUsername(String username) {
        this.username = username;
    }

    @Generated(IRW.CODE_GENERATOR)
    public String getPassword() {
        return password;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setPassword(String password) {
        this.password = password;
    }

    @Generated(IRW.CODE_GENERATOR)
    public String getPhone() {
        return phone;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Generated(IRW.CODE_GENERATOR)
    public String getEmail() {
        return email;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setEmail(String email) {
        this.email = email;
    }

    @Generated(IRW.CODE_GENERATOR)
    public LocalDate getBirthday() {
        return birthday;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired == null ? Boolean.FALSE : accountNonExpired;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired == null ? Boolean.FALSE : credentialsNonExpired;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked == null ? Boolean.FALSE : accountNonLocked;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled == null ? Boolean.FALSE : enabled;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Department getDepartment() {
        return department;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setDepartment(Department department) {
        this.department = department;
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
    public User.Sex getSex() {
        return sex;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setSex(User.Sex sex) {
        this.sex = sex;
    }

    /**
     * 性别
     */
    public enum Sex {

        /**
         * 男
         */
        MALE,
        /**
         * 女
         */
        FEMALE
    }

    public static class SexFilter extends Filter<Sex> {
    }
}
