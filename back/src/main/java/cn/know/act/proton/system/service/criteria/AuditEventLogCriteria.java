package cn.know.act.proton.system.service.criteria;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import cn.know.act.proton.core.service.filter.StringFilter;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import cn.know.act.proton.core.service.filter.LocalDateTimeFilter;
import cn.know.act.proton.core.service.filter.BooleanFilter;

/**
 * 登录日志
 */
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class AuditEventLogCriteria implements Serializable {

    /**
     * 用户名称
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter username;

    /**
     * 登录时间
     */
    @Generated(IRW.CODE_GENERATOR)
    private LocalDateTimeFilter loginTime;

    /**
     * 是否成功
     */
    @Generated(IRW.CODE_GENERATOR)
    private BooleanFilter success;

    /**
     * ip地址
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter ip;

    @Generated(IRW.CODE_GENERATOR)
    public StringFilter getUsername() {
        return username;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setUsername(StringFilter username) {
        this.username = username;
    }

    @Generated(IRW.CODE_GENERATOR)
    public LocalDateTimeFilter getLoginTime() {
        return loginTime;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setLoginTime(LocalDateTimeFilter loginTime) {
        this.loginTime = loginTime;
    }

    @Generated(IRW.CODE_GENERATOR)
    public BooleanFilter getSuccess() {
        return success;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setSuccess(BooleanFilter success) {
        this.success = success;
    }

    @Generated(IRW.CODE_GENERATOR)
    public StringFilter getIp() {
        return ip;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setIp(StringFilter ip) {
        this.ip = ip;
    }
}
