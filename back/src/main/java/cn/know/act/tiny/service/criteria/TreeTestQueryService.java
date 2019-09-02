package cn.know.act.tiny.service.criteria;

import cn.know.act.proton.core.service.QueryService;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.tiny.domain.TreeTest;
import cn.know.act.tiny.domain.TreeTest_;
import cn.know.act.tiny.repository.jpa.TreeTestJpaRepository;
import cn.know.act.tiny.service.dto.TreeTestDTO;
import cn.know.act.tiny.service.mapper.TreeTestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import cn.know.act.tiny.domain.TestType_;
import cn.know.act.tiny.domain.item.TreeItem_;
import cn.know.act.proton.system.domain.User_;

/**
 * Service for executing complex queries for {@link TreeTest } entities in the database.
 * The main input is a {@link TreeTestCriteria } which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TreeTestDTO } or a {@link Page} of {@link TreeTestDTO } which fulfills the criteria.
 */
@Transactional(readOnly = true)
@Service("TnyTreeTestQueryService")
public class TreeTestQueryService extends QueryService<TreeTest> {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(TreeTestQueryService.class);

    @Generated(IRW.CODE_GENERATOR)
    private final TreeTestJpaRepository treeTestJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final TreeTestMapper treeTestMapper;

    @Generated(IRW.CODE_GENERATOR)
    public TreeTestQueryService(TreeTestJpaRepository treeTestJpaRepository, TreeTestMapper treeTestMapper) {
        this.treeTestJpaRepository = treeTestJpaRepository;
        this.treeTestMapper = treeTestMapper;
    }

    /**
     * Return a {@link Page} of {@link TreeTestDTO } which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param pageable The pageable, which should be returned.
     * @return the matching entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Transactional(readOnly = true)
    public Page<TreeTestDTO> findByCriteria(TreeTestCriteria criteria, Pageable pageable, boolean retDetail) {
        if (log.isDebugEnabled()) {
            log.debug("find by criteria : {}, pageable: {}", criteria, pageable);
        }
        final Specification<TreeTest> specification = createSpecification(criteria);
        if (retDetail) {
            return treeTestJpaRepository.findAll(specification, pageable).map(treeTestMapper::toDtoWithModel);
        } else {
            return treeTestJpaRepository.findAll(specification, pageable).map(treeTestMapper::toDto);
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
    public long countByCriteria(TreeTestCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("count by criteria : {}", criteria);
        }
        final Specification<TreeTest> specification = createSpecification(criteria);
        return treeTestJpaRepository.count(specification);
    }

    /**
     * Return a {@link List} of {@link TreeTestDTO } which is the node's children
     * <p>
     * if nodeId is null, will get a {@link List} of roots {@link TreeTestDTO }
     *
     * @param nodeId the parent node
     * @param level  the level of children from the parent node
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    @Transactional(readOnly = true)
    public List<TreeTestDTO> getTree(Long nodeId, Integer level, Boolean includeNode) {
        if (log.isDebugEnabled()) {
            log.debug("get tree, node id: {}, level: {} , includeNode: {}", nodeId, level, includeNode);
        }
        return treeTestJpaRepository.getTree(nodeId, level, includeNode == null ? false : includeNode).stream().map(treeTestMapper::toDto).collect(Collectors.toList());
    }

    @Generated(IRW.CODE_GENERATOR)
    @Transactional(readOnly = true)
    public Optional<TreeTestDTO> findParents(Long nodeId, Integer level) {
        if (log.isDebugEnabled()) {
            log.debug("get node parents: {}, level: {}", nodeId, level);
        }
        return treeTestJpaRepository.getParents(nodeId, level).map(treeTestMapper::toDto);
    }

    /**
     * Return a {@link List} of {@link TreeTestDTO } which matches the criteria from the database.
     * <p>
     * The list will include the nodes to the root node
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param pageable The pageable, which should be returned.
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    @Transactional(readOnly = true)
    public List<TreeTestDTO> findTree(TreeTestCriteria criteria, Pageable pageable) {
        if (log.isDebugEnabled()) {
            log.debug("find tree by criteria: {}, pageable: {} ", criteria, pageable);
        }
        final Specification<TreeTest> specification = createSpecification(criteria);
        return treeTestJpaRepository.findTree(specification, pageable).stream().map(treeTestMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Function to convert TreeTestCriteria to a {@link Specification}.
     */
    @Generated(IRW.CODE_GENERATOR)
    private Specification<TreeTest> createSpecification(TreeTestCriteria criteria) {
        Specification<TreeTest> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), TreeTest_.name));
            }
            if (criteria.getTestType() != null) {
                specification = specification.and(buildReferringESpecification(criteria.getTestType(), TreeTest_.testType, TestType_.id));
            }
            if (criteria.getIntType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIntType(), TreeTest_.intType));
            }
            if (criteria.getDecimalType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDecimalType(), TreeTest_.decimalType));
            }
            if (criteria.getTimeType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTimeType(), TreeTest_.timeType));
            }
            if (criteria.getDateType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateType(), TreeTest_.dateType));
            }
            if (criteria.getDateTimeType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateTimeType(), TreeTest_.dateTimeType));
            }
            if (criteria.getBooleanType() != null) {
                specification = specification.and(buildSpecification(criteria.getBooleanType(), TreeTest_.booleanType));
            }
            if (criteria.getTreeTestItems() != null) {
                specification = specification.and(buildReferringESpecification(criteria.getTreeTestItems(), TreeTest_.treeTestItems, TreeItem_.id));
            }
            if (criteria.get_parent() != null) {
                specification = specification.and(buildReferringESpecification(criteria.get_parent(), TreeTest_._parent, TreeTest_.id));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), TreeTest_.createdDate));
            }
            if (criteria.getModifiedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifiedDate(), TreeTest_.modifiedDate));
            }
            if (criteria.getCreator() != null) {
                specification = specification.and(buildReferringESpecification(criteria.getCreator(), TreeTest_.creator, User_.id));
            }
            if (criteria.getModifier() != null) {
                specification = specification.and(buildReferringESpecification(criteria.getModifier(), TreeTest_.modifier, User_.id));
            }
            if (criteria.getDeletedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeletedDate(), TreeTest_.deletedDate));
            }
        }
        return specification;
    }
}
