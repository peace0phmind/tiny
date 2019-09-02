package cn.know.act.tiny.service.criteria.item;

import cn.know.act.proton.core.service.QueryService;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.tiny.domain.item.TreeItem;
import cn.know.act.tiny.domain.item.TreeItem_;
import cn.know.act.tiny.repository.jpa.item.TreeItemJpaRepository;
import cn.know.act.tiny.service.dto.item.TreeItemDTO;
import cn.know.act.tiny.service.mapper.item.TreeItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Generated;
import cn.know.act.tiny.domain.TreeTest_;

/**
 * Service for executing complex queries for {@link TreeItem } entities in the database.
 * The main input is a {@link TreeItemCriteria } which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TreeItemDTO } or a {@link Page} of {@link TreeItemDTO } which fulfills the criteria.
 */
@Transactional(readOnly = true)
@Service("TnyTreeItemQueryService")
public class TreeItemQueryService extends QueryService<TreeItem> {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(TreeItemQueryService.class);

    @Generated(IRW.CODE_GENERATOR)
    private final TreeItemJpaRepository treeItemJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final TreeItemMapper treeItemMapper;

    @Generated(IRW.CODE_GENERATOR)
    public TreeItemQueryService(TreeItemJpaRepository treeItemJpaRepository, TreeItemMapper treeItemMapper) {
        this.treeItemJpaRepository = treeItemJpaRepository;
        this.treeItemMapper = treeItemMapper;
    }

    /**
     * Return a {@link Page} of {@link TreeItemDTO } which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param pageable The pageable, which should be returned.
     * @return the matching entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Transactional(readOnly = true)
    public Page<TreeItemDTO> findByCriteria(TreeItemCriteria criteria, Pageable pageable, boolean retDetail) {
        if (log.isDebugEnabled()) {
            log.debug("find by criteria : {}, pageable: {}", criteria, pageable);
        }
        final Specification<TreeItem> specification = createSpecification(criteria);
        if (retDetail) {
            return treeItemJpaRepository.findAll(specification, pageable).map(treeItemMapper::toDtoWithModel);
        } else {
            return treeItemJpaRepository.findAll(specification, pageable).map(treeItemMapper::toDto);
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
    public long countByCriteria(TreeItemCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("count by criteria : {}", criteria);
        }
        final Specification<TreeItem> specification = createSpecification(criteria);
        return treeItemJpaRepository.count(specification);
    }

    /**
     * Function to convert TreeItemCriteria to a {@link Specification}.
     */
    @Generated(IRW.CODE_GENERATOR)
    private Specification<TreeItem> createSpecification(TreeItemCriteria criteria) {
        Specification<TreeItem> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), TreeItem_.name));
            }
            if (criteria.getValue() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValue(), TreeItem_.value));
            }
            if (criteria.getTests() != null) {
                specification = specification.and(buildReferringESpecification(criteria.getTests(), TreeItem_.tests, TreeTest_.id));
            }
        }
        return specification;
    }
}
