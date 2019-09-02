package cn.know.act.proton.system.service.impl;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.domain.AuditEventLog;
import cn.know.act.proton.system.repository.jpa.AuditEventLogJpaRepository;
import cn.know.act.proton.system.service.dto.AuditEventLogDTO;
import cn.know.act.proton.system.service.inf.AuditEventLogService;
import cn.know.act.proton.system.service.mapper.AuditEventLogMapper;
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
 * Service Implementation for managing {@link AuditEventLog }.
 */
@Transactional
@Service("SysAuditEventLogServiceImpl")
public class AuditEventLogServiceImpl implements AuditEventLogService {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(AuditEventLogServiceImpl.class);

    @Generated(IRW.CODE_GENERATOR)
    private final AuditEventLogJpaRepository auditEventLogJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final AuditEventLogMapper auditEventLogMapper;

    @Generated(IRW.CODE_GENERATOR)
    private final CacheManager cacheManager;

    @Generated(IRW.CODE_GENERATOR)
    public AuditEventLogServiceImpl(AuditEventLogJpaRepository auditEventLogJpaRepository, AuditEventLogMapper auditEventLogMapper, CacheManager cacheManager) {
        this.auditEventLogJpaRepository = auditEventLogJpaRepository;
        this.auditEventLogMapper = auditEventLogMapper;
        this.cacheManager = cacheManager;
    }

    /**
     * Create a AuditEventLog.
     *
     * @param auditEventLogDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public AuditEventLogDTO create(AuditEventLogDTO auditEventLogDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save auditEventLog: {}", auditEventLogDTO);
        }
        Assert.isNull(auditEventLogDTO.getId(), "Create entity's id must be null.");
        AuditEventLog auditEventLog = auditEventLogJpaRepository.save(auditEventLogMapper.toDomain(auditEventLogDTO));
        AuditEventLogDTO result = auditEventLogMapper.toDto(auditEventLog);
        // TODO add search option
        return result;
    }

    /**
     * Update a AuditEventLog.
     *
     * @param auditEventLogDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public AuditEventLogDTO update(AuditEventLogDTO auditEventLogDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save auditEventLog: {}", auditEventLogDTO);
        }
        Assert.notNull(auditEventLogDTO.getId(), "Update entity's id must not be null.");
        AuditEventLog auditEventLog = auditEventLogJpaRepository.getOne(auditEventLogDTO.getId());
        auditEventLogMapper.updateDomain(auditEventLogDTO, auditEventLog);
        AuditEventLog ret = auditEventLogJpaRepository.save(auditEventLog);
        AuditEventLogDTO result = auditEventLogMapper.toDto(ret);
        // TODO add search option
        return result;
    }

    /**
     * Get all the AuditEventLogs.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Page<AuditEventLogDTO> findAll(Pageable pageable) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get all AuditEventLogs");
        }
        return auditEventLogJpaRepository.findAll(pageable).map(auditEventLogMapper::toDtoWithModel);
    }

    /**
     * Get one AuditEventLog by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Optional<AuditEventLogDTO> findOne(Long id) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get AuditEventLog: {}", id);
        }
        return auditEventLogJpaRepository.findWithModelById(id).map(auditEventLogMapper::toDtoWithModel);
    }

    /**
     * Delete the "ids" AuditEventLog.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public void deleteByIds(List<Long> ids) {
        if (log.isDebugEnabled()) {
            log.debug("Request to delete AuditEventLogs: {}", ids);
        }
        auditEventLogJpaRepository.deleteAll(auditEventLogJpaRepository.findAllById(ids));
    }
}
