package cn.know.act.proton.system.jobs;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.time.LocalDateTime;

/**
 * 示例带参定时任务
 * cn.know.act.proton.system.jobs.SampleParamJob
 *
 * @author Exrickx
 */
@Slf4j
public class SampleParamJob implements Job {

    /**
     * 若参数变量名修改 QuartzJobController中也需对应修改
     */
    private String params;

    public void setParams(String params) {
        this.params = params;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info("示例带参定时任务2 时间: {}, {}", LocalDateTime.now(), this.params);
    }
}
