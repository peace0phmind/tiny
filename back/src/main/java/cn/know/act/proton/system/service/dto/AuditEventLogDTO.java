package cn.know.act.proton.system.service.dto;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;
import cn.know.act.proton.core.util.BaseDTO;
import lombok.EqualsAndHashCode;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import java.util.Optional;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * 登录日志
 */
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@EqualsAndHashCode
public class AuditEventLogDTO extends BaseDTO<Long> implements Serializable {

    @Generated(IRW.CODE_GENERATOR)
    private Long id;

    /**
     * 用户名称
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 255) String> username;

    /**
     * 登录时间
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<LocalDateTime> loginTime;

    /**
     * 是否成功
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<Boolean> success;

    /**
     * ip地址
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 255) String> ip;

    @Generated(IRW.CODE_GENERATOR)
    private String _instanceName;

    @Generated(IRW.CODE_GENERATOR)
    public String get_instanceName() {
        return _instanceName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void set_instanceName(String _instanceName) {
        this._instanceName = _instanceName;
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
    public Optional<String> getUsername() {
        return username;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setUsername(Optional<String> username) {
        this.username = username;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<LocalDateTime> getLoginTime() {
        return loginTime;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setLoginTime(Optional<LocalDateTime> loginTime) {
        this.loginTime = loginTime;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<Boolean> getSuccess() {
        return success;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setSuccess(Optional<Boolean> success) {
        this.success = success;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<String> getIp() {
        return ip;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setIp(Optional<String> ip) {
        this.ip = ip;
    }
}
