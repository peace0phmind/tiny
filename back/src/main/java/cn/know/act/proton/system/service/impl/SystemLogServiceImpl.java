package cn.know.act.proton.system.service.impl;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.domain.SystemLog;
import cn.know.act.proton.system.repository.jpa.SystemLogJpaRepository;
import cn.know.act.proton.system.service.dto.SystemLogDTO;
import cn.know.act.proton.system.service.inf.SystemLogService;
import cn.know.act.proton.system.service.mapper.SystemLogMapper;
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
 * Service Implementation for managing {@link SystemLog }.
 */
@Transactional
@Service("SysSystemLogServiceImpl")
public class SystemLogServiceImpl implements SystemLogService {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(SystemLogServiceImpl.class);

    @Generated(IRW.CODE_GENERATOR)
    private final SystemLogJpaRepository systemLogJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final SystemLogMapper systemLogMapper;

    @Generated(IRW.CODE_GENERATOR)
    private final CacheManager cacheManager;

    @Generated(IRW.CODE_GENERATOR)
    public SystemLogServiceImpl(SystemLogJpaRepository systemLogJpaRepository, SystemLogMapper systemLogMapper, CacheManager cacheManager) {
        this.systemLogJpaRepository = systemLogJpaRepository;
        this.systemLogMapper = systemLogMapper;
        this.cacheManager = cacheManager;
    }

    /**
     * Create a SystemLog.
     *
     * @param systemLogDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public SystemLogDTO create(SystemLogDTO systemLogDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save systemLog: {}", systemLogDTO);
        }
        Assert.isNull(systemLogDTO.getId(), "Create entity's id must be null.");
        SystemLog systemLog = systemLogJpaRepository.save(systemLogMapper.toDomain(systemLogDTO));
        SystemLogDTO result = systemLogMapper.toDto(systemLog);
        // TODO add search option
        return result;
    }

    /**
     * Update a SystemLog.
     *
     * @param systemLogDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public SystemLogDTO update(SystemLogDTO systemLogDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save systemLog: {}", systemLogDTO);
        }
        Assert.notNull(systemLogDTO.getId(), "Update entity's id must not be null.");
        SystemLog systemLog = systemLogJpaRepository.getOne(systemLogDTO.getId());
        systemLogMapper.updateDomain(systemLogDTO, systemLog);
        SystemLog ret = systemLogJpaRepository.save(systemLog);
        SystemLogDTO result = systemLogMapper.toDto(ret);
        // TODO add search option
        return result;
    }

    /**
     * Get all the SystemLogs.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Page<SystemLogDTO> findAll(Pageable pageable) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get all SystemLogs");
        }
        return systemLogJpaRepository.findAll(pageable).map(systemLogMapper::toDtoWithModel);
    }

    /**
     * Get one SystemLog by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Optional<SystemLogDTO> findOne(Long id) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get SystemLog: {}", id);
        }
        return systemLogJpaRepository.findWithModelById(id).map(systemLogMapper::toDtoWithModel);
    }

    /**
     * Delete the "ids" SystemLog.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public void deleteByIds(List<Long> ids) {
        if (log.isDebugEnabled()) {
            log.debug("Request to delete SystemLogs: {}", ids);
        }
        systemLogJpaRepository.deleteAll(systemLogJpaRepository.findAllById(ids));
    }
}
