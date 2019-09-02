package cn.know.act.proton.system.service.impl;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.domain.ScheduleJobLog;
import cn.know.act.proton.system.repository.jpa.ScheduleJobLogJpaRepository;
import cn.know.act.proton.system.service.dto.ScheduleJobLogDTO;
import cn.know.act.proton.system.service.inf.ScheduleJobLogService;
import cn.know.act.proton.system.service.mapper.ScheduleJobLogMapper;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ScheduleJobLog }.
 */
@Transactional
@Service("SysScheduleJobLogServiceImpl")
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(ScheduleJobLogServiceImpl.class);

    @Generated(IRW.CODE_GENERATOR)
    private final ScheduleJobLogJpaRepository scheduleJobLogJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final ScheduleJobLogMapper scheduleJobLogMapper;

    @Generated(IRW.CODE_GENERATOR)
    private final CacheManager cacheManager;

    @Generated(IRW.CODE_GENERATOR)
    public ScheduleJobLogServiceImpl(ScheduleJobLogJpaRepository scheduleJobLogJpaRepository, ScheduleJobLogMapper scheduleJobLogMapper, CacheManager cacheManager) {
        this.scheduleJobLogJpaRepository = scheduleJobLogJpaRepository;
        this.scheduleJobLogMapper = scheduleJobLogMapper;
        this.cacheManager = cacheManager;
    }

    /**
     * Create a ScheduleJobLog.
     *
     * @param scheduleJobLogDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public ScheduleJobLogDTO create(ScheduleJobLogDTO scheduleJobLogDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save scheduleJobLog: {}", scheduleJobLogDTO);
        }
        Assert.isNull(scheduleJobLogDTO.getId(), "Create entity's id must be null.");
        ScheduleJobLog scheduleJobLog = scheduleJobLogJpaRepository.save(scheduleJobLogMapper.toDomain(scheduleJobLogDTO));
        ScheduleJobLogDTO result = scheduleJobLogMapper.toDto(scheduleJobLog);
        // TODO add search option
        return result;
    }

    /**
     * Update a ScheduleJobLog.
     *
     * @param scheduleJobLogDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public ScheduleJobLogDTO update(ScheduleJobLogDTO scheduleJobLogDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save scheduleJobLog: {}", scheduleJobLogDTO);
        }
        Assert.notNull(scheduleJobLogDTO.getId(), "Update entity's id must not be null.");
        ScheduleJobLog scheduleJobLog = scheduleJobLogJpaRepository.getOne(scheduleJobLogDTO.getId());
        scheduleJobLogMapper.updateDomain(scheduleJobLogDTO, scheduleJobLog);
        ScheduleJobLog ret = scheduleJobLogJpaRepository.save(scheduleJobLog);
        ScheduleJobLogDTO result = scheduleJobLogMapper.toDto(ret);
        // TODO add search option
        return result;
    }

    /**
     * Get all the ScheduleJobLogs.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Page<ScheduleJobLogDTO> findAll(Pageable pageable) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get all ScheduleJobLogs");
        }
        return scheduleJobLogJpaRepository.findAll(pageable).map(scheduleJobLogMapper::toDtoWithModel);
    }

    /**
     * Get one ScheduleJobLog by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Optional<ScheduleJobLogDTO> findOne(Long id) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get ScheduleJobLog: {}", id);
        }
        return scheduleJobLogJpaRepository.findWithModelById(id).map(scheduleJobLogMapper::toDtoWithModel);
    }

    /**
     * Delete the "ids" ScheduleJobLog.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public void deleteByIds(List<Long> ids) {
        if (log.isDebugEnabled()) {
            log.debug("Request to delete ScheduleJobLogs: {}", ids);
        }
        scheduleJobLogJpaRepository.deleteAll(scheduleJobLogJpaRepository.findAllById(ids));
    }
}
