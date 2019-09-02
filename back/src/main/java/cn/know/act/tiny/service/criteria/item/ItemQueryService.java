package cn.know.act.tiny.service.criteria.item;

import cn.know.act.proton.core.service.QueryService;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.tiny.domain.item.Item;
import cn.know.act.tiny.domain.item.Item_;
import cn.know.act.tiny.repository.jpa.item.ItemJpaRepository;
import cn.know.act.tiny.service.dto.item.ItemDTO;
import cn.know.act.tiny.service.mapper.item.ItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Generated;
import cn.know.act.tiny.domain.Test_;

/**
 * Service for executing complex queries for {@link Item } entities in the database.
 * The main input is a {@link ItemCriteria } which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ItemDTO } or a {@link Page} of {@link ItemDTO } which fulfills the criteria.
 */
@Transactional(readOnly = true)
@Service("TnyItemQueryService")
public class ItemQueryService extends QueryService<Item> {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(ItemQueryService.class);

    @Generated(IRW.CODE_GENERATOR)
    private final ItemJpaRepository itemJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final ItemMapper itemMapper;

    @Generated(IRW.CODE_GENERATOR)
    public ItemQueryService(ItemJpaRepository itemJpaRepository, ItemMapper itemMapper) {
        this.itemJpaRepository = itemJpaRepository;
        this.itemMapper = itemMapper;
    }

    /**
     * Return a {@link Page} of {@link ItemDTO } which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param pageable The pageable, which should be returned.
     * @return the matching entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Transactional(readOnly = true)
    public Page<ItemDTO> findByCriteria(ItemCriteria criteria, Pageable pageable, boolean retDetail) {
        if (log.isDebugEnabled()) {
            log.debug("find by criteria : {}, pageable: {}", criteria, pageable);
        }
        final Specification<Item> specification = createSpecification(criteria);
        if (retDetail) {
            return itemJpaRepository.findAll(specification, pageable).map(itemMapper::toDtoWithModel);
        } else {
            return itemJpaRepository.findAll(specification, pageable).map(itemMapper::toDto);
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
    public long countByCriteria(ItemCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("count by criteria : {}", criteria);
        }
        final Specification<Item> specification = createSpecification(criteria);
        return itemJpaRepository.count(specification);
    }

    /**
     * Function to convert ItemCriteria to a {@link Specification}.
     */
    @Generated(IRW.CODE_GENERATOR)
    private Specification<Item> createSpecification(ItemCriteria criteria) {
        Specification<Item> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Item_.name));
            }
            if (criteria.getValue() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValue(), Item_.value));
            }
            if (criteria.getTest() != null) {
                specification = specification.and(buildReferringESpecification(criteria.getTest(), Item_.test, Test_.id));
            }
        }
        return specification;
    }
}
