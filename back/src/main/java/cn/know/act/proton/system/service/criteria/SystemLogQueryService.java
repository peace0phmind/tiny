package cn.know.act.proton.system.service.criteria;

import cn.know.act.proton.core.service.QueryService;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.domain.SystemLog;
import cn.know.act.proton.system.domain.SystemLog_;
import cn.know.act.proton.system.repository.jpa.SystemLogJpaRepository;
import cn.know.act.proton.system.service.dto.SystemLogDTO;
import cn.know.act.proton.system.service.mapper.SystemLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Generated;

/**
 * Service for executing complex queries for {@link SystemLog } entities in the database.
 * The main input is a {@link SystemLogCriteria } which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SystemLogDTO } or a {@link Page} of {@link SystemLogDTO } which fulfills the criteria.
 */
@Transactional(readOnly = true)
@Service("SysSystemLogQueryService")
public class SystemLogQueryService extends QueryService<SystemLog> {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(SystemLogQueryService.class);

    @Generated(IRW.CODE_GENERATOR)
    private final SystemLogJpaRepository systemLogJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final SystemLogMapper systemLogMapper;

    @Generated(IRW.CODE_GENERATOR)
    public SystemLogQueryService(SystemLogJpaRepository systemLogJpaRepository, SystemLogMapper systemLogMapper) {
        this.systemLogJpaRepository = systemLogJpaRepository;
        this.systemLogMapper = systemLogMapper;
    }

    /**
     * Return a {@link Page} of {@link SystemLogDTO } which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param pageable The pageable, which should be returned.
     * @return the matching entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Transactional(readOnly = true)
    public Page<SystemLogDTO> findByCriteria(SystemLogCriteria criteria, Pageable pageable, boolean retDetail) {
        if (log.isDebugEnabled()) {
            log.debug("find by criteria : {}, pageable: {}", criteria, pageable);
        }
        final Specification<SystemLog> specification = createSpecification(criteria);
        if (retDetail) {
            return systemLogJpaRepository.findAll(specification, pageable).map(systemLogMapper::toDtoWithModel);
        } else {
            return systemLogJpaRepository.findAll(specification, pageable).map(systemLogMapper::toDto);
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
    public long countByCriteria(SystemLogCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("count by criteria : {}", criteria);
        }
        final Specification<SystemLog> specification = createSpecification(criteria);
        return systemLogJpaRepository.count(specification);
    }

    /**
     * Function to convert SystemLogCriteria to a {@link Specification}.
     */
    @Generated(IRW.CODE_GENERATOR)
    private Specification<SystemLog> createSpecification(SystemLogCriteria criteria) {
        Specification<SystemLog> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getUsername() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUsername(), SystemLog_.username));
            }
            if (criteria.getRequestTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRequestTime(), SystemLog_.requestTime));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), SystemLog_.description));
            }
            if (criteria.getIpAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIpAddress(), SystemLog_.ipAddress));
            }
            if (criteria.getCostTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCostTime(), SystemLog_.costTime));
            }
            if (criteria.getClassName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClassName(), SystemLog_.className));
            }
            if (criteria.getMethodName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMethodName(), SystemLog_.methodName));
            }
            if (criteria.getParams() != null) {
                specification = specification.and(buildStringSpecification(criteria.getParams(), SystemLog_.params));
            }
        }
        return specification;
    }
}
