package cn.know.act.proton.system.jobs;

import cn.know.act.proton.system.service.dto.ScheduleJobDTO;
import cn.know.act.proton.system.service.dto.ScheduleJobLogDTO;
import cn.know.act.proton.system.service.inf.ScheduleJobLogService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


/**
 * 示例带参定时任务
 * cn.know.act.proton.system.jobs.SampleJob
 *
 * @author Exrickx
 */
@Slf4j
public class SampleJob implements Job {

    @Autowired
    private ScheduleJobLogService scheduleJobLogService;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        boolean success = false;
        try {

            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
            long fireTime = jobExecutionContext.getFireTime().getTime();
            long nextFireTime = jobExecutionContext.getNextFireTime().getTime();
            Integer times = Integer.parseInt(String.valueOf(nextFireTime - fireTime));
            ScheduleJobLogDTO scheduleJobLogDTO = new ScheduleJobLogDTO();
            ScheduleJobDTO scheduleJobDTO = new ScheduleJobDTO();
            scheduleJobDTO.setId((UUID) jobDataMap.get("jobId"));
            scheduleJobLogDTO.setJob(Optional.of(scheduleJobDTO));
            scheduleJobLogDTO.setSuccess(Optional.of(success));
            scheduleJobLogDTO.setTimes(Optional.of(times));
            scheduleJobLogDTO.setStartTime(Optional.of(LocalDateTime.now()));
            scheduleJobLogService.create(scheduleJobLogDTO);
        }
    }
}
