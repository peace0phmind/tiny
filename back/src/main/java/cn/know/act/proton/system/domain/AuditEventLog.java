package cn.know.act.proton.system.domain;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import lombok.AllArgsConstructor;
import javax.persistence.Table;
import javax.persistence.Entity;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.text.MessageFormat;
import java.util.Objects;

/**
 * 登录日志
 */
@ToString
@NoArgsConstructor
@DynamicUpdate
@AllArgsConstructor
@Table(name = "sys_audit_event_log")
@Entity(name = "SysAuditEventLog")
public class AuditEventLog implements Serializable {

    @Generated(IRW.CODE_GENERATOR)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /**
     * 用户名称
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 255)
    @Column(name = "username", length = 255)
    private String username;

    /**
     * 登录时间
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "login_time")
    private LocalDateTime loginTime;

    /**
     * 是否成功
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "success")
    private Boolean success = false;

    /**
     * ip地址
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 255)
    @Column(name = "ip", length = 255)
    private String ip;

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
        if (!(o instanceof AuditEventLog)) {
            return false;
        }
        AuditEventLog that = (AuditEventLog) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(loginTime, that.loginTime) && Objects.equals(success, that.success) && Objects.equals(ip, that.ip);
    }

    @Override
    @Generated(IRW.CODE_GENERATOR)
    public int hashCode() {
        return Objects.hash(id, username, loginTime, success, ip);
    }

    @Generated(IRW.CODE_GENERATOR)
    public Long getId() {
        return id;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setId(Long id) {
        this.id = id;
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
    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Boolean isSuccess() {
        return success == null ? Boolean.FALSE : success;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setSuccess(Boolean success) {
        this.success = success == null ? Boolean.FALSE : success;
    }

    @Generated(IRW.CODE_GENERATOR)
    public String getIp() {
        return ip;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setIp(String ip) {
        this.ip = ip;
    }
}
