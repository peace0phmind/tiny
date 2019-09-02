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
 * 系统日志
 */
@ToString
@NoArgsConstructor
@DynamicUpdate
@AllArgsConstructor
@Table(name = "sys_system_log")
@Entity(name = "SysSystemLog")
public class SystemLog implements Serializable {

    @Generated(IRW.CODE_GENERATOR)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /**
     * 用户名
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 255)
    @Column(name = "username", length = 255)
    private String username;

    /**
     * 请求时间
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "request_time")
    private LocalDateTime requestTime;

    /**
     * 描述
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 255)
    @Column(name = "description", length = 255)
    private String description;

    /**
     * ip地址
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 32)
    @Column(name = "ip_address", length = 32)
    private String ipAddress;

    /**
     * 花费时间
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "cost_time")
    private Integer costTime;

    /**
     * 类名
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 1024)
    @Column(name = "class_name", length = 1024)
    private String className;

    /**
     * 方法名
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 1024)
    @Column(name = "method_name", length = 1024)
    private String methodName;

    /**
     * 请求体
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 4096)
    @Column(name = "params", length = 4096)
    private String params;

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
        if (!(o instanceof SystemLog)) {
            return false;
        }
        SystemLog that = (SystemLog) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(requestTime, that.requestTime) && Objects.equals(description, that.description) && Objects.equals(ipAddress, that.ipAddress) && Objects.equals(costTime, that.costTime) && Objects.equals(className, that.className) && Objects.equals(methodName, that.methodName) && Objects.equals(params, that.params);
    }

    @Override
    @Generated(IRW.CODE_GENERATOR)
    public int hashCode() {
        return Objects.hash(id, username, requestTime, description, ipAddress, costTime, className, methodName, params);
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
    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }

    @Generated(IRW.CODE_GENERATOR)
    public String getDescription() {
        return description;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setDescription(String description) {
        this.description = description;
    }

    @Generated(IRW.CODE_GENERATOR)
    public String getIpAddress() {
        return ipAddress;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Integer getCostTime() {
        return costTime;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setCostTime(Integer costTime) {
        this.costTime = costTime;
    }

    @Generated(IRW.CODE_GENERATOR)
    public String getClassName() {
        return className;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setClassName(String className) {
        this.className = className;
    }

    @Generated(IRW.CODE_GENERATOR)
    public String getMethodName() {
        return methodName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public String getParams() {
        return params;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setParams(String params) {
        this.params = params;
    }
}
