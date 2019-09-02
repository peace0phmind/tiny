package cn.know.act.proton.system.service.criteria;

import cn.know.act.proton.core.service.QueryService;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.domain.ScheduleJobLog;
import cn.know.act.proton.system.domain.ScheduleJobLog_;
import cn.know.act.proton.system.repository.jpa.ScheduleJobLogJpaRepository;
import cn.know.act.proton.system.service.dto.ScheduleJobLogDTO;
import cn.know.act.proton.system.service.mapper.ScheduleJobLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Generated;
import cn.know.act.proton.system.domain.ScheduleJob_;

/**
 * Service for executing complex queries for {@link ScheduleJobLog } entities in the database.
 * The main input is a {@link ScheduleJobLogCriteria } which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ScheduleJobLogDTO } or a {@link Page} of {@link ScheduleJobLogDTO } which fulfills the criteria.
 */
@Transactional(readOnly = true)
@Service("SysScheduleJobLogQueryService")
public class ScheduleJobLogQueryService extends QueryService<ScheduleJobLog> {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(ScheduleJobLogQueryService.class);

    @Generated(IRW.CODE_GENERATOR)
    private final ScheduleJobLogJpaRepository scheduleJobLogJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final ScheduleJobLogMapper scheduleJobLogMapper;

    @Generated(IRW.CODE_GENERATOR)
    public ScheduleJobLogQueryService(ScheduleJobLogJpaRepository scheduleJobLogJpaRepository, ScheduleJobLogMapper scheduleJobLogMapper) {
        this.scheduleJobLogJpaRepository = scheduleJobLogJpaRepository;
        this.scheduleJobLogMapper = scheduleJobLogMapper;
    }

    /**
     * Return a {@link Page} of {@link ScheduleJobLogDTO } which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param pageable The pageable, which should be returned.
     * @return the matching entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Transactional(readOnly = true)
    public Page<ScheduleJobLogDTO> findByCriteria(ScheduleJobLogCriteria criteria, Pageable pageable, boolean retDetail) {
        if (log.isDebugEnabled()) {
            log.debug("find by criteria : {}, pageable: {}", criteria, pageable);
        }
        final Specification<ScheduleJobLog> specification = createSpecification(criteria);
        if (retDetail) {
            return scheduleJobLogJpaRepository.findAll(specification, pageable).map(scheduleJobLogMapper::toDtoWithModel);
        } else {
            return scheduleJobLogJpaRepository.findAll(specification, pageable).map(scheduleJobLogMapper::toDto);
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
    public long countByCriteria(ScheduleJobLogCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("count by criteria : {}", criteria);
        }
        final Specification<ScheduleJobLog> specification = createSpecification(criteria);
        return scheduleJobLogJpaRepository.count(specification);
    }

    /**
     * Function to convert ScheduleJobLogCriteria to a {@link Specification}.
     */
    @Generated(IRW.CODE_GENERATOR)
    private Specification<ScheduleJobLog> createSpecification(ScheduleJobLogCriteria criteria) {
        Specification<ScheduleJobLog> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getJob() != null) {
                specification = specification.and(buildReferringESpecification(criteria.getJob(), ScheduleJobLog_.job, ScheduleJob_.id));
            }
            if (criteria.getSuccess() != null) {
                specification = specification.and(buildSpecification(criteria.getSuccess(), ScheduleJobLog_.success));
            }
            if (criteria.getResult() != null) {
                specification = specification.and(buildStringSpecification(criteria.getResult(), ScheduleJobLog_.result));
            }
            if (criteria.getTimes() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTimes(), ScheduleJobLog_.times));
            }
            if (criteria.getStartTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartTime(), ScheduleJobLog_.startTime));
            }
        }
        return specification;
    }
}
