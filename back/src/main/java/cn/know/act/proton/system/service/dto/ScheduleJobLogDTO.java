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
 * 定时任务日志
 */
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@EqualsAndHashCode
public class ScheduleJobLogDTO extends BaseDTO<Long> implements Serializable {

    @Generated(IRW.CODE_GENERATOR)
    private Long id;

    /**
     * 定时任务
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    private Optional<ScheduleJobDTO> job;

    /**
     * 成功
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<Boolean> success;

    /**
     * 结果
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<@Size(max = 255) String> result;

    /**
     * 耗时(单位：毫秒)
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<Integer> times;

    /**
     * 开始时间
     */
    @Generated(IRW.CODE_GENERATOR)
    private Optional<LocalDateTime> startTime;

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
    public Optional<ScheduleJobDTO> getJob() {
        return job;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setJob(Optional<ScheduleJobDTO> job) {
        this.job = job;
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
    public Optional<String> getResult() {
        return result;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setResult(Optional<String> result) {
        this.result = result;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<Integer> getTimes() {
        return times;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setTimes(Optional<Integer> times) {
        this.times = times;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<LocalDateTime> getStartTime() {
        return startTime;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setStartTime(Optional<LocalDateTime> startTime) {
        this.startTime = startTime;
    }
}
