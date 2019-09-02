package cn.know.act.proton.system.service.criteria;

import cn.know.act.proton.core.service.QueryService;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.domain.ScheduleJob;
import cn.know.act.proton.system.domain.ScheduleJob_;
import cn.know.act.proton.system.repository.jpa.ScheduleJobJpaRepository;
import cn.know.act.proton.system.service.dto.ScheduleJobDTO;
import cn.know.act.proton.system.service.mapper.ScheduleJobMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Generated;
import cn.know.act.proton.system.domain.ScheduleJobLog_;
import cn.know.act.proton.system.domain.User_;

/**
 * Service for executing complex queries for {@link ScheduleJob } entities in the database.
 * The main input is a {@link ScheduleJobCriteria } which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ScheduleJobDTO } or a {@link Page} of {@link ScheduleJobDTO } which fulfills the criteria.
 */
@Transactional(readOnly = true)
@Service("SysScheduleJobQueryService")
public class ScheduleJobQueryService extends QueryService<ScheduleJob> {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(ScheduleJobQueryService.class);

    @Generated(IRW.CODE_GENERATOR)
    private final ScheduleJobJpaRepository scheduleJobJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final ScheduleJobMapper scheduleJobMapper;

    @Generated(IRW.CODE_GENERATOR)
    public ScheduleJobQueryService(ScheduleJobJpaRepository scheduleJobJpaRepository, ScheduleJobMapper scheduleJobMapper) {
        this.scheduleJobJpaRepository = scheduleJobJpaRepository;
        this.scheduleJobMapper = scheduleJobMapper;
    }

    /**
     * Return a {@link Page} of {@link ScheduleJobDTO } which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param pageable The pageable, which should be returned.
     * @return the matching entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Transactional(readOnly = true)
    public Page<ScheduleJobDTO> findByCriteria(ScheduleJobCriteria criteria, Pageable pageable, boolean retDetail) {
        if (log.isDebugEnabled()) {
            log.debug("find by criteria : {}, pageable: {}", criteria, pageable);
        }
        final Specification<ScheduleJob> specification = createSpecification(criteria);
        if (retDetail) {
            return scheduleJobJpaRepository.findAll(specification, pageable).map(scheduleJobMapper::toDtoWithModel);
        } else {
            return scheduleJobJpaRepository.findAll(specification, pageable).map(scheduleJobMapper::toDto);
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
    public long countByCriteria(ScheduleJobCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("count by criteria : {}", criteria);
        }
        final Specification<ScheduleJob> specification = createSpecification(criteria);
        return scheduleJobJpaRepository.count(specification);
    }

    /**
     * Function to convert ScheduleJobCriteria to a {@link Specification}.
     */
    @Generated(IRW.CODE_GENERATOR)
    private Specification<ScheduleJob> createSpecification(ScheduleJobCriteria criteria) {
        Specification<ScheduleJob> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getBeanName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBeanName(), ScheduleJob_.beanName));
            }
            if (criteria.getMethodName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMethodName(), ScheduleJob_.methodName));
            }
            if (criteria.getParams() != null) {
                specification = specification.and(buildStringSpecification(criteria.getParams(), ScheduleJob_.params));
            }
            if (criteria.getCronExpression() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCronExpression(), ScheduleJob_.cronExpression));
            }
            if (criteria.getActive() != null) {
                specification = specification.and(buildSpecification(criteria.getActive(), ScheduleJob_.active));
            }
            if (criteria.getSingleCase() != null) {
                specification = specification.and(buildSpecification(criteria.getSingleCase(), ScheduleJob_.singleCase));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), ScheduleJob_.remarks));
            }
            if (criteria.getLogs() != null) {
                specification = specification.and(buildReferringESpecification(criteria.getLogs(), ScheduleJob_.logs, ScheduleJobLog_.id));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), ScheduleJob_.createdDate));
            }
            if (criteria.getModifiedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifiedDate(), ScheduleJob_.modifiedDate));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildReferringESpecification(criteria.getCreator(), ScheduleJob_.creator, User_.id));
            }
            if (criteria.getModifier() != null) {
                specification = specification.and(buildReferringESpecification(criteria.getModifier(), ScheduleJob_.modifier, User_.id));
            }
        }
        return specification;
    }
}
