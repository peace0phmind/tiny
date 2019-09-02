package cn.know.act.proton.system.service.criteria;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import cn.know.act.proton.core.service.filter.StringFilter;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import cn.know.act.proton.core.service.filter.LocalDateTimeFilter;
import cn.know.act.proton.core.service.filter.IntegerFilter;

/**
 * 系统日志
 */
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class SystemLogCriteria implements Serializable {

    /**
     * 用户名
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter username;

    /**
     * 请求时间
     */
    @Generated(IRW.CODE_GENERATOR)
    private LocalDateTimeFilter requestTime;

    /**
     * 描述
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter description;

    /**
     * ip地址
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter ipAddress;

    /**
     * 花费时间
     */
    @Generated(IRW.CODE_GENERATOR)
    private IntegerFilter costTime;

    /**
     * 类名
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter className;

    /**
     * 方法名
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter methodName;

    /**
     * 请求体
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter params;

    @Generated(IRW.CODE_GENERATOR)
    public StringFilter getUsername() {
        return username;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setUsername(StringFilter username) {
        this.username = username;
    }

    @Generated(IRW.CODE_GENERATOR)
    public LocalDateTimeFilter getRequestTime() {
        return requestTime;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setRequestTime(LocalDateTimeFilter requestTime) {
        this.requestTime = requestTime;
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
    public StringFilter getIpAddress() {
        return ipAddress;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setIpAddress(StringFilter ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Generated(IRW.CODE_GENERATOR)
    public IntegerFilter getCostTime() {
        return costTime;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setCostTime(IntegerFilter costTime) {
        this.costTime = costTime;
    }

    @Generated(IRW.CODE_GENERATOR)
    public StringFilter getClassName() {
        return className;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setClassName(StringFilter className) {
        this.className = className;
    }

    @Generated(IRW.CODE_GENERATOR)
    public StringFilter getMethodName() {
        return methodName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setMethodName(StringFilter methodName) {
        this.methodName = methodName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public StringFilter getParams() {
        return params;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setParams(StringFilter params) {
        this.params = params;
    }
}
