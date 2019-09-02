package cn.know.act.proton.system.domain;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import lombok.AllArgsConstructor;
import javax.persistence.Table;
import javax.persistence.Index;
import javax.persistence.Entity;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.ConstraintMode;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.text.MessageFormat;
import java.util.Objects;

/**
 * 定时任务日志
 */
@ToString
@NoArgsConstructor
@DynamicUpdate
@AllArgsConstructor
@Table(name = "sys_schedule_job_log", indexes = { @Index(name = "idx_sys_schedule_job_log_job_id", columnList = "job_id") })
@Entity(name = "SysScheduleJobLog")
public class ScheduleJobLog implements Serializable {

    @Generated(IRW.CODE_GENERATOR)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /**
     * 定时任务
     */
    @Generated(IRW.CODE_GENERATOR)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private ScheduleJob job;

    /**
     * 成功
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "success")
    private Boolean success = false;

    /**
     * 结果
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 255)
    @Column(name = "result", length = 255)
    private String result;

    /**
     * 耗时(单位：毫秒)
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "times")
    private Integer times;

    /**
     * 开始时间
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "start_time")
    private LocalDateTime startTime;

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
        if (!(o instanceof ScheduleJobLog)) {
            return false;
        }
        ScheduleJobLog that = (ScheduleJobLog) o;
        return Objects.equals(id, that.id) && (job != null && that.job != null ? Objects.equals(job.getId(), that.job.getId()) : job == that.job) && Objects.equals(success, that.success) && Objects.equals(result, that.result) && Objects.equals(times, that.times) && Objects.equals(startTime, that.startTime);
    }

    @Override
    @Generated(IRW.CODE_GENERATOR)
    public int hashCode() {
        return Objects.hash(id, job != null ? job.getId() : null, success, result, times, startTime);
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
    public ScheduleJob getJob() {
        return job;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setJob(ScheduleJob job) {
        this.job = job;
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
    public String getResult() {
        return result;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setResult(String result) {
        this.result = result;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Integer getTimes() {
        return times;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setTimes(Integer times) {
        this.times = times;
    }

    @Generated(IRW.CODE_GENERATOR)
    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}
