package cn.know.act.proton.system.domain;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.EntityListeners;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
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
import java.util.Set;
import java.util.LinkedHashSet;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.ConstraintMode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import java.text.MessageFormat;
import java.util.Objects;

/**
 * 定时任务
 */
@ToString
@NoArgsConstructor
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@Table(name = "sys_schedule_job", indexes = { @Index(name = "idx_sys_schedule_job_creator_id", columnList = "creator_id"), @Index(name = "idx_sys_schedule_job_modifier_id", columnList = "modifier_id") })
@Entity(name = "SysScheduleJob")
public class ScheduleJob implements AuditDate, AuditUser, Serializable {

    @Generated(IRW.CODE_GENERATOR)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @org.hibernate.annotations.Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "char(36)")
    private UUID id;

    /**
     * Bean名称
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 200)
    @Column(name = "bean_name", length = 200)
    private String beanName;

    /**
     * 方法名
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 100)
    @Column(name = "method_name", length = 100)
    private String methodName;

    /**
     * 参数
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 2000)
    @Column(name = "params", length = 2000)
    private String params;

    /**
     * cron表达式
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 100)
    @Column(name = "cron_expression", length = 100)
    private String cronExpression;

    /**
     * 激活
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "active")
    private Boolean active = false;

    /**
     * 单实例
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "single_case")
    private Boolean singleCase = false;

    /**
     * 备注
     */
    @Generated(IRW.CODE_GENERATOR)
    @Size(max = 255)
    @Column(name = "remarks", length = 255)
    private String remarks;

    /**
     * 日志
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    @OneToMany(mappedBy = "job")
    private Set<ScheduleJobLog> logs = new LinkedHashSet<>();

    /**
     * 创建时间
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;

    /**
     * 修改时间
     */
    @Generated(IRW.CODE_GENERATOR)
    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    /**
     * 创建人
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @CreatedBy
    private User creator;

    /**
     * 修改人
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modifier_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @LastModifiedBy
    private User modifier;

    @Generated(IRW.CODE_GENERATOR)
    public void addLog(ScheduleJobLog item) {
        if (this.logs == null) {
            this.logs = new LinkedHashSet<>();
        }
        item.setJob(this);
        this.logs.add(item);
    }

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
        if (!(o instanceof ScheduleJob)) {
            return false;
        }
        ScheduleJob that = (ScheduleJob) o;
        return Objects.equals(id, that.id) && Objects.equals(beanName, that.beanName) && Objects.equals(methodName, that.methodName) && Objects.equals(params, that.params) && Objects.equals(cronExpression, that.cronExpression) && Objects.equals(active, that.active) && Objects.equals(singleCase, that.singleCase) && Objects.equals(remarks, that.remarks) && Objects.equals(createdDate, that.createdDate) && Objects.equals(modifiedDate, that.modifiedDate) && (creator != null && that.creator != null ? Objects.equals(creator.getId(), that.creator.getId()) : creator == that.creator) && (modifier != null && that.modifier != null ? Objects.equals(modifier.getId(), that.modifier.getId()) : modifier == that.modifier);
    }

    @Override
    @Generated(IRW.CODE_GENERATOR)
    public int hashCode() {
        return Objects.hash(id, beanName, methodName, params, cronExpression, active, singleCase, remarks, createdDate, modifiedDate, creator != null ? creator.getId() : null, modifier != null ? modifier.getId() : null);
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
    public String getBeanName() {
        return beanName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setBeanName(String beanName) {
        this.beanName = beanName;
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

    @Generated(IRW.CODE_GENERATOR)
    public String getCronExpression() {
        return cronExpression;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Boolean isActive() {
        return active == null ? Boolean.FALSE : active;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setActive(Boolean active) {
        this.active = active == null ? Boolean.FALSE : active;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Boolean isSingleCase() {
        return singleCase == null ? Boolean.FALSE : singleCase;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setSingleCase(Boolean singleCase) {
        this.singleCase = singleCase == null ? Boolean.FALSE : singleCase;
    }

    @Generated(IRW.CODE_GENERATOR)
    public String getRemarks() {
        return remarks;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Generated(IRW.CODE_GENERATOR)
    public Set<ScheduleJobLog> getLogs() {
        return logs;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setLogs(Set<ScheduleJobLog> logs) {
        this.logs = logs;
    }

    @Generated(IRW.CODE_GENERATOR)
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public User getCreator() {
        return creator;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Generated(IRW.CODE_GENERATOR)
    public User getModifier() {
        return modifier;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setModifier(User modifier) {
        this.modifier = modifier;
    }
}
