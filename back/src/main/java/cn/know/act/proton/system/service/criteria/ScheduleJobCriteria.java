package cn.know.act.proton.system.service.criteria;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import cn.know.act.proton.core.service.filter.StringFilter;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import cn.know.act.proton.core.service.filter.BooleanFilter;
import cn.know.act.proton.core.service.filter.LongFilter;
import cn.know.act.proton.core.service.filter.LocalDateTimeFilter;
import cn.know.act.proton.core.service.filter.UUIDFilter;

/**
 * 定时任务
 */
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class ScheduleJobCriteria implements Serializable {

    /**
     * Bean名称
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter beanName;

    /**
     * 方法名
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter methodName;

    /**
     * 参数
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter params;

    /**
     * cron表达式
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter cronExpression;

    /**
     * 激活
     */
    @Generated(IRW.CODE_GENERATOR)
    private BooleanFilter active;

    /**
     * 单实例
     */
    @Generated(IRW.CODE_GENERATOR)
    private BooleanFilter singleCase;

    /**
     * 备注
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter remarks;

    /**
     * 日志
     */
    @Generated(IRW.CODE_GENERATOR)
    private LongFilter logs;

    /**
     * 创建时间
     */
    @Generated(IRW.CODE_GENERATOR)
    private LocalDateTimeFilter createdDate;

    /**
     * 修改时间
     */
    @Generated(IRW.CODE_GENERATOR)
    private LocalDateTimeFilter modifiedDate;

    /**
     * 创建人
     */
    @Generated(IRW.CODE_GENERATOR)
    private UUIDFilter creator;

    /**
     * 修改人
     */
    @Generated(IRW.CODE_GENERATOR)
    private UUIDFilter modifier;

    @Generated(IRW.CODE_GENERATOR)
    public StringFilter getBeanName() {
        return beanName;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setBeanName(StringFilter beanName) {
        this.beanName = beanName;
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

    @Generated(IRW.CODE_GENERATOR)
    public StringFilter getCronExpression() {
        return cronExpression;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setCronExpression(StringFilter cronExpression) {
        this.cronExpression = cronExpression;
    }

    @Generated(IRW.CODE_GENERATOR)
    public BooleanFilter getActive() {
        return active;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setActive(BooleanFilter active) {
        this.active = active;
    }

    @Generated(IRW.CODE_GENERATOR)
    public BooleanFilter getSingleCase() {
        return singleCase;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setSingleCase(BooleanFilter singleCase) {
        this.singleCase = singleCase;
    }

    @Generated(IRW.CODE_GENERATOR)
    public StringFilter getRemarks() {
        return remarks;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setRemarks(StringFilter remarks) {
        this.remarks = remarks;
    }

    @Generated(IRW.CODE_GENERATOR)
    public LongFilter getLogs() {
        return logs;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setLogs(LongFilter logs) {
        this.logs = logs;
    }

    @Generated(IRW.CODE_GENERATOR)
    public LocalDateTimeFilter getCreatedDate() {
        return createdDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setCreatedDate(LocalDateTimeFilter createdDate) {
        this.createdDate = createdDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public LocalDateTimeFilter getModifiedDate() {
        return modifiedDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setModifiedDate(LocalDateTimeFilter modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Generated(IRW.CODE_GENERATOR)
    public UUIDFilter getCreator() {
        return creator;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setCreator(UUIDFilter creator) {
        this.creator = creator;
    }

    @Generated(IRW.CODE_GENERATOR)
    public UUIDFilter getModifier() {
        return modifier;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setModifier(UUIDFilter modifier) {
        this.modifier = modifier;
    }
}
