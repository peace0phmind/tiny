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
import java.util.Set;
import java.util.LinkedHashSet;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDateTime;

/**
 * 定时任务
 */
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@EqualsAndHashCode
public class ScheduleJobDTO extends BaseDTO<UUID> implements Serializable {

    @Generated(IRW.CODE_GENERATOR)
    private UUID id;

    /**
     * Bean名称
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 200) String> beanName;

    /**
     * 方法名
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 100) String> methodName;

    /**
     * 参数
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 2000) String> params;

    /**
     * cron表达式
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 100) String> cronExpression;

    /**
     * 激活
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<Boolean> active;

    /**
     * 单实例
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<Boolean> singleCase;

    /**
     * 备注
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 255) String> remarks;

    /**
     * 日志
     */
    @Generated(IRW.CODE_GENERATOR)
    @JsonDeserialize(as = LinkedHashSet.class)
    private Set<ScheduleJobLogDTO> logs;

    /**
     * 创建时间
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<LocalDateTime> createdDate;

    /**
     * 修改时间
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<LocalDateTime> modifiedDate;

    /**
     * 创建人
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<UserDTO> creator;

    /**
     * 修改人
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<UserDTO> modifier;

    @Generated(IRW.CODE_GENERATOR)
    private String _instanceName;

    @Generated(IRW.CODE_GENERATOR)
    public void addLog(ScheduleJobLogDTO item) {
        if (this.logs == null) {
            this.logs = new LinkedHashSet<>();
        }
        this.logs.add(item);
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
    public Optional<String> getBeanName() {
        return beanName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setBeanName(Optional<String> beanName) {
        this.beanName = beanName;
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

    @Generated(IRW.CODE_GENERATOR)
    public Optional<String> getCronExpression() {
        return cronExpression;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setCronExpression(Optional<String> cronExpression) {
        this.cronExpression = cronExpression;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<Boolean> getActive() {
        return active;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setActive(Optional<Boolean> active) {
        this.active = active;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<Boolean> getSingleCase() {
        return singleCase;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setSingleCase(Optional<Boolean> singleCase) {
        this.singleCase = singleCase;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<String> getRemarks() {
        return remarks;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setRemarks(Optional<String> remarks) {
        this.remarks = remarks;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Set<ScheduleJobLogDTO> getLogs() {
        return logs;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setLogs(Set<ScheduleJobLogDTO> logs) {
        this.logs = logs;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<LocalDateTime> getCreatedDate() {
        return createdDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setCreatedDate(Optional<LocalDateTime> createdDate) {
        this.createdDate = createdDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<LocalDateTime> getModifiedDate() {
        return modifiedDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setModifiedDate(Optional<LocalDateTime> modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<UserDTO> getCreator() {
        return creator;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setCreator(Optional<UserDTO> creator) {
        this.creator = creator;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<UserDTO> getModifier() {
        return modifier;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setModifier(Optional<UserDTO> modifier) {
        this.modifier = modifier;
    }
}
