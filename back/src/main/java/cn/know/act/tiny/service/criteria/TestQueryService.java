package cn.know.act.tiny.service.criteria;

import cn.know.act.proton.core.service.QueryService;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.tiny.domain.Test;
import cn.know.act.tiny.domain.Test_;
import cn.know.act.tiny.repository.jpa.TestJpaRepository;
import cn.know.act.tiny.service.dto.TestDTO;
import cn.know.act.tiny.service.mapper.TestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Generated;
import javax.persistence.criteria.JoinType;
import cn.know.act.tiny.domain.TestType_;
import cn.know.act.tiny.domain.item.Item_;
import cn.know.act.proton.system.domain.User_;

/**
 * Service for executing complex queries for {@link Test } entities in the database.
 * The main input is a {@link TestCriteria } which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TestDTO } or a {@link Page} of {@link TestDTO } which fulfills the criteria.
 */
@Transactional(readOnly = true)
@Service("TnyTestQueryService")
public class TestQueryService extends QueryService<Test> {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(TestQueryService.class);

    @Generated(IRW.CODE_GENERATOR)
    private final TestJpaRepository testJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final TestMapper testMapper;

    @Generated(IRW.CODE_GENERATOR)
    public TestQueryService(TestJpaRepository testJpaRepository, TestMapper testMapper) {
        this.testJpaRepository = testJpaRepository;
        this.testMapper = testMapper;
    }

    /**
     * Return a {@link Page} of {@link TestDTO } which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param pageable The pageable, which should be returned.
     * @return the matching entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Transactional(readOnly = true)
    public Page<TestDTO> findByCriteria(TestCriteria criteria, Pageable pageable, boolean retDetail) {
        if (log.isDebugEnabled()) {
            log.debug("find by criteria : {}, pageable: {}", criteria, pageable);
        }
        final Specification<Test> specification = createSpecification(criteria);
        if (retDetail) {
            return testJpaRepository.findAll(specification, pageable).map(testMapper::toDtoWithModel);
        } else {
            return testJpaRepository.findAll(specification, pageable).map(testMapper::toDto);
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
    public long countByCriteria(TestCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("count by criteria : {}", criteria);
        }
        final Specification<Test> specification = createSpecification(criteria);
        return testJpaRepository.count(specification);
    }

    /**
     * Function to convert TestCriteria to a {@link Specification}.
     */
    @Generated(IRW.CODE_GENERATOR)
    private Specification<Test> createSpecification(TestCriteria criteria) {
        Specification<Test> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Test_.name));
            }
            if (criteria.getTemplateType() != null) {
                specification = specification.and(buildReferringESpecification(criteria.getTemplateType(), Test_.templateType, TestType_.id));
            }
            if (criteria.getIntType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIntType(), Test_.intType));
            }
            if (criteria.getDecimalType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDecimalType(), Test_.decimalType));
            }
            if (criteria.getTimeType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTimeType(), Test_.timeType));
            }
            if (criteria.getDateType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateType(), Test_.dateType));
            }
            if (criteria.getDateTimeType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateTimeType(), Test_.dateTimeType));
            }
            if (criteria.getBooleanType() != null) {
                specification = specification.and(buildSpecification(criteria.getBooleanType(), Test_.booleanType));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getStatus(), Test_.status));
            }
            if (criteria.getMultiEnums() != null) {
                specification = specification.and(buildDistinctSpecification(criteria.getMultiEnums(), root -> root.join(Test_.multiEnums, JoinType.LEFT)));
            }
            if (criteria.getItems() != null) {
                specification = specification.and(buildReferringESpecification(criteria.getItems(), Test_.items, Item_.id));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), Test_.createdDate));
            }
            if (criteria.getModifiedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifiedDate(), Test_.modifiedDate));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildReferringESpecification(criteria.getCreator(), Test_.creator, User_.id));
            }
            if (criteria.getModifier() != null) {
                specification = specification.and(buildReferringESpecification(criteria.getModifier(), Test_.modifier, User_.id));
            }
            if (criteria.getDeletedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeletedDate(), Test_.deletedDate));
            }
        }
        return specification;
    }
}
