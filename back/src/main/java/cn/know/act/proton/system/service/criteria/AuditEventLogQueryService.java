package cn.know.act.proton.system.service.criteria;

import cn.know.act.proton.core.service.QueryService;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.domain.AuditEventLog;
import cn.know.act.proton.system.domain.AuditEventLog_;
import cn.know.act.proton.system.repository.jpa.AuditEventLogJpaRepository;
import cn.know.act.proton.system.service.dto.AuditEventLogDTO;
import cn.know.act.proton.system.service.mapper.AuditEventLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Generated;

/**
 * Service for executing complex queries for {@link AuditEventLog } entities in the database.
 * The main input is a {@link AuditEventLogCriteria } which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AuditEventLogDTO } or a {@link Page} of {@link AuditEventLogDTO } which fulfills the criteria.
 */
@Transactional(readOnly = true)
@Service("SysAuditEventLogQueryService")
public class AuditEventLogQueryService extends QueryService<AuditEventLog> {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(AuditEventLogQueryService.class);

    @Generated(IRW.CODE_GENERATOR)
    private final AuditEventLogJpaRepository auditEventLogJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final AuditEventLogMapper auditEventLogMapper;

    @Generated(IRW.CODE_GENERATOR)
    public AuditEventLogQueryService(AuditEventLogJpaRepository auditEventLogJpaRepository, AuditEventLogMapper auditEventLogMapper) {
        this.auditEventLogJpaRepository = auditEventLogJpaRepository;
        this.auditEventLogMapper = auditEventLogMapper;
    }

    /**
     * Return a {@link Page} of {@link AuditEventLogDTO } which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param pageable The pageable, which should be returned.
     * @return the matching entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Transactional(readOnly = true)
    public Page<AuditEventLogDTO> findByCriteria(AuditEventLogCriteria criteria, Pageable pageable, boolean retDetail) {
        if (log.isDebugEnabled()) {
            log.debug("find by criteria : {}, pageable: {}", criteria, pageable);
        }
        final Specification<AuditEventLog> specification = createSpecification(criteria);
        if (retDetail) {
            return auditEventLogJpaRepository.findAll(specification, pageable).map(auditEventLogMapper::toDtoWithModel);
        } else {
            return auditEventLogJpaRepository.findAll(specification, pageable).map(auditEventLogMapper::toDto);
        }
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Transactional(readOnly = true)
    public long countByCriteria(AuditEventLogCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("count by criteria : {}", criteria);
        }
        final Specification<AuditEventLog> specification = createSpecification(criteria);
        return auditEventLogJpaRepository.count(specification);
    }

    /**
     * Function to convert AuditEventLogCriteria to a {@link Specification}.
     */
    @Generated(IRW.CODE_GENERATOR)
    private Specification<AuditEventLog> createSpecification(AuditEventLogCriteria criteria) {
        Specification<AuditEventLog> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getUsername() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUsername(), AuditEventLog_.username));
            }
            if (criteria.getLoginTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLoginTime(), AuditEventLog_.loginTime));
            }
            if (criteria.getSuccess() != null) {
                specification = specification.and(buildSpecification(criteria.getSuccess(), AuditEventLog_.success));
            }
            if (criteria.getIp() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIp(), AuditEventLog_.ip));
            }
        }
        return specification;
    }
}
