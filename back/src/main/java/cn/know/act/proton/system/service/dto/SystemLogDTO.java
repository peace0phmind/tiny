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
 * 系统日志
 */
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@EqualsAndHashCode
public class SystemLogDTO extends BaseDTO<Long> implements Serializable {

    @Generated(IRW.CODE_GENERATOR)
    private Long id;

    /**
     * 用户名
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 255) String> username;

    /**
     * 请求时间
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<LocalDateTime> requestTime;

    /**
     * 描述
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 255) String> description;

    /**
     * ip地址
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 32) String> ipAddress;

    /**
     * 花费时间
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<Integer> costTime;

    /**
     * 类名
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 1024) String> className;

    /**
     * 方法名
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 1024) String> methodName;

    /**
     * 请求体
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 4096) String> params;

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
    public Optional<LocalDateTime> getRequestTime() {
        return requestTime;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setRequestTime(Optional<LocalDateTime> requestTime) {
        this.requestTime = requestTime;
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
    public Optional<String> getIpAddress() {
        return ipAddress;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setIpAddress(Optional<String> ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<Integer> getCostTime() {
        return costTime;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setCostTime(Optional<Integer> costTime) {
        this.costTime = costTime;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<String> getClassName() {
        return className;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setClassName(Optional<String> className) {
        this.className = className;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<String> getMethodName() {
        return methodName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setMethodName(Optional<String> methodName) {
        this.methodName = methodName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<String> getParams() {
        return params;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setParams(Optional<String> params) {
        this.params = params;
    }
}
