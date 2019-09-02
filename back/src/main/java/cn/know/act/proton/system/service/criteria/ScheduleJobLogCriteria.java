package cn.know.act.proton.system.service.criteria;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import cn.know.act.proton.core.service.filter.UUIDFilter;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import cn.know.act.proton.core.service.filter.BooleanFilter;
import cn.know.act.proton.core.service.filter.StringFilter;
import cn.know.act.proton.core.service.filter.IntegerFilter;
import cn.know.act.proton.core.service.filter.LocalDateTimeFilter;

/**
 * 定时任务日志
 */
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class ScheduleJobLogCriteria implements Serializable {

    /**
     * 定时任务
     */
    @Generated(IRW.CODE_GENERATOR)
    private UUIDFilter job;

    /**
     * 成功
     */
    @Generated(IRW.CODE_GENERATOR)
    private BooleanFilter success;

    /**
     * 结果
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter result;

    /**
     * 耗时(单位：毫秒)
     */
    @Generated(IRW.CODE_GENERATOR)
    private IntegerFilter times;

    /**
     * 开始时间
     */
    @Generated(IRW.CODE_GENERATOR)
    private LocalDateTimeFilter startTime;

    @Generated(IRW.CODE_GENERATOR)
    public UUIDFilter getJob() {
        return job;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setJob(UUIDFilter job) {
        this.job = job;
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
    public StringFilter getResult() {
        return result;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setResult(StringFilter result) {
        this.result = result;
    }

    @Generated(IRW.CODE_GENERATOR)
    public IntegerFilter getTimes() {
        return times;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setTimes(IntegerFilter times) {
        this.times = times;
    }

    @Generated(IRW.CODE_GENERATOR)
    public LocalDateTimeFilter getStartTime() {
        return startTime;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setStartTime(LocalDateTimeFilter startTime) {
        this.startTime = startTime;
    }
}
