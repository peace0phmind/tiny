package cn.know.act.proton.system.service.impl;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.domain.ScheduleJob;
import cn.know.act.proton.system.repository.jpa.ScheduleJobJpaRepository;
import cn.know.act.proton.system.service.dto.ScheduleJobDTO;
import cn.know.act.proton.system.service.inf.ScheduleJobService;
import cn.know.act.proton.system.service.mapper.ScheduleJobMapper;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import cn.know.act.proton.system.repository.jpa.ScheduleJobLogJpaRepository;
import cn.know.act.proton.system.service.mapper.ScheduleJobLogMapper;
import org.quartz.*;

/**
 * Service Implementation for managing {@link ScheduleJob }.
 */
@Transactional
@Service("SysScheduleJobServiceImpl")
public class ScheduleJobServiceImpl implements ScheduleJobService {

    @Autowired
    private Scheduler scheduler;

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(ScheduleJobServiceImpl.class);

    @Generated(IRW.CODE_GENERATOR)
    private final ScheduleJobJpaRepository scheduleJobJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final ScheduleJobMapper scheduleJobMapper;

    @Generated(IRW.CODE_GENERATOR)
    private final CacheManager cacheManager;

    @Generated(IRW.CODE_GENERATOR)
    @Autowired
    private ScheduleJobLogJpaRepository scheduleJobLogJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    @Autowired
    private ScheduleJobLogMapper scheduleJobLogMapper;

    @Generated(IRW.CODE_GENERATOR)
    public ScheduleJobServiceImpl(ScheduleJobJpaRepository scheduleJobJpaRepository, ScheduleJobMapper scheduleJobMapper, CacheManager cacheManager) {
        this.scheduleJobJpaRepository = scheduleJobJpaRepository;
        this.scheduleJobMapper = scheduleJobMapper;
        this.cacheManager = cacheManager;
    }

    public static Job getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (Job) class1.newInstance();
    }

    /**
     * 添加定时任务
     *
     * @param jobClassName
     * @param cronExpression
     * @param parameter
     */
    public void addScheduleJob(UUID jobId, String jobClassName, String cronExpression, String parameter) {
        try {
            // 启动调度器
            scheduler.start();
            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName).usingJobData("parameter", parameter).build();
            // 设置任务传递参数
            jobDetail.getJobDataMap().put("jobId", jobId);
            jobDetail.getJobDataMap().put("parameter", parameter);
            // 表达式调度构建器(即任务执行的时间) 使用withMisfireHandlingInstructionDoNothing() 忽略掉调度暂停过程中没有执行的调度
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName).withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.error(e.toString());
            throw new RuntimeException("创建定时任务失败");
        } catch (Exception e) {
            throw new RuntimeException("后台找不到该类名任务");
        }
    }

    /**
     * 暂停或恢复定时任务
     *
     * @param scheduleJobDTO
     * @throws SchedulerException
     */
    public void pauseOrResumeScheduleJob(ScheduleJobDTO scheduleJobDTO) {
        ScheduleJob scheduleJob = scheduleJobMapper.toDomain(scheduleJobDTO);
        if (scheduleJob.isActive()) {
            try {
                scheduler.pauseJob(JobKey.jobKey(scheduleJob.getBeanName()));
                scheduleJob.setActive(false);
                scheduleJobJpaRepository.save(scheduleJob);
            } catch (SchedulerException e) {
                throw new RuntimeException("停止定时任务失败");
            }
        } else {
            try {
                // 先删除后新增定时任务
                delScheduleJob(scheduleJob.getBeanName());
                addScheduleJob(scheduleJob.getId(), scheduleJob.getBeanName(), scheduleJob.getCronExpression(), scheduleJob.getParams());
                // scheduler.resumeJob(JobKey.jobKey(scheduleJob.getBeanName()));
                scheduleJob.setActive(true);
                scheduleJobJpaRepository.save(scheduleJob);
            } catch (Exception e) {
                throw new RuntimeException("激活定时任务失败");
            }
        }
    }

    /**
     * 删除定时任务
     *
     * @param jobClassName
     */
    public void delScheduleJob(String jobClassName) {
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName));
            scheduler.deleteJob(JobKey.jobKey(jobClassName));
        } catch (Exception e) {
            throw new RuntimeException("删除定时任务失败");
        }
    }

    /**
     * Create a ScheduleJob.
     *
     * @param scheduleJobDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ScheduleJobDTO create(ScheduleJobDTO scheduleJobDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save scheduleJob: {}", scheduleJobDTO);
        }
        Assert.isNull(scheduleJobDTO.getId(), "Create entity's id must be null.");
        ScheduleJob scheduleJob = scheduleJobMapper.toDomain(scheduleJobDTO);
        if (scheduleJob.isActive()) {
            // 新增定时任务
            addScheduleJob(scheduleJob.getId(), scheduleJob.getBeanName(), scheduleJob.getCronExpression(), scheduleJob.getParams());
        }
        scheduleJob = scheduleJobJpaRepository.save(scheduleJob);
        ScheduleJobDTO result = scheduleJobMapper.toDto(scheduleJob);
        // TODO add search option
        return result;
    }

    /**
     * Update a ScheduleJob.
     *
     * @param scheduleJobDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ScheduleJobDTO update(ScheduleJobDTO scheduleJobDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save scheduleJob: {}", scheduleJobDTO);
        }
        Assert.notNull(scheduleJobDTO.getId(), "Update entity's id must not be null.");
        ScheduleJob scheduleJob = scheduleJobJpaRepository.getOne(scheduleJobDTO.getId());
        scheduleJobMapper.updateDomain(scheduleJobDTO, scheduleJob);
        if (scheduleJob.isActive()) {
            // 先删除后新增定时任务
            delScheduleJob(scheduleJob.getBeanName());
            addScheduleJob(scheduleJob.getId(), scheduleJob.getBeanName(), scheduleJob.getCronExpression(), scheduleJob.getParams());
        }
        ScheduleJob retTemplate = scheduleJobJpaRepository.save(scheduleJob);
        ScheduleJobDTO result = scheduleJobMapper.toDto(scheduleJob);
        // TODO add search option
        return result;
    }

    /**
     * Get all the ScheduleJobs.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Page<ScheduleJobDTO> findAll(Pageable pageable) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get all ScheduleJobs");
        }
        return scheduleJobJpaRepository.findAll(pageable).map(scheduleJobMapper::toDtoWithModel);
    }

    /**
     * Get one ScheduleJob by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Optional<ScheduleJobDTO> findOne(UUID id) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get ScheduleJob: {}", id);
        }
        return scheduleJobJpaRepository.findWithModelById(id).map(scheduleJobMapper::toDtoWithModel);
    }

    /**
     * Delete the "ids" ScheduleJob.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public void deleteByIds(List<UUID> ids) {
        if (log.isDebugEnabled()) {
            log.debug("Request to delete ScheduleJobs: {}", ids);
        }
        scheduleJobJpaRepository.deleteAll(scheduleJobJpaRepository.findAllById(ids));
    }
}
