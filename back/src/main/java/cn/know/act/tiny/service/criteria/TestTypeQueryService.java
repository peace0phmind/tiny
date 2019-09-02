package cn.know.act.tiny.service.criteria;

import cn.know.act.proton.core.service.QueryService;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.tiny.domain.TestType;
import cn.know.act.tiny.domain.TestType_;
import cn.know.act.tiny.repository.jpa.TestTypeJpaRepository;
import cn.know.act.tiny.service.dto.TestTypeDTO;
import cn.know.act.tiny.service.mapper.TestTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Generated;

/**
 * Service for executing complex queries for {@link TestType } entities in the database.
 * The main input is a {@link TestTypeCriteria } which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TestTypeDTO } or a {@link Page} of {@link TestTypeDTO } which fulfills the criteria.
 */
@Transactional(readOnly = true)
@Service("TnyTestTypeQueryService")
public class TestTypeQueryService extends QueryService<TestType> {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(TestTypeQueryService.class);

    @Generated(IRW.CODE_GENERATOR)
    private final TestTypeJpaRepository testTypeJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final TestTypeMapper testTypeMapper;

    @Generated(IRW.CODE_GENERATOR)
    public TestTypeQueryService(TestTypeJpaRepository testTypeJpaRepository, TestTypeMapper testTypeMapper) {
        this.testTypeJpaRepository = testTypeJpaRepository;
        this.testTypeMapper = testTypeMapper;
    }

    /**
     * Return a {@link Page} of {@link TestTypeDTO } which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param pageable The pageable, which should be returned.
     * @return the matching entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Transactional(readOnly = true)
    public Page<TestTypeDTO> findByCriteria(TestTypeCriteria criteria, Pageable pageable, boolean retDetail) {
        if (log.isDebugEnabled()) {
            log.debug("find by criteria : {}, pageable: {}", criteria, pageable);
        }
        final Specification<TestType> specification = createSpecification(criteria);
        if (retDetail) {
            return testTypeJpaRepository.findAll(specification, pageable).map(testTypeMapper::toDtoWithModel);
        } else {
            return testTypeJpaRepository.findAll(specification, pageable).map(testTypeMapper::toDto);
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
    public long countByCriteria(TestTypeCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("count by criteria : {}", criteria);
        }
        final Specification<TestType> specification = createSpecification(criteria);
        return testTypeJpaRepository.count(specification);
    }

    /**
     * Function to convert TestTypeCriteria to a {@link Specification}.
     */
    @Generated(IRW.CODE_GENERATOR)
    private Specification<TestType> createSpecification(TestTypeCriteria criteria) {
        Specification<TestType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), TestType_.name));
            }
        }
        return specification;
    }
}
