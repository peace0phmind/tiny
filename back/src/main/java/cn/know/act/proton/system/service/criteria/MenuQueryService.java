package cn.know.act.proton.system.service.criteria;

import cn.know.act.proton.core.service.QueryService;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.domain.Menu;
import cn.know.act.proton.system.domain.Menu_;
import cn.know.act.proton.system.repository.jpa.MenuJpaRepository;
import cn.know.act.proton.system.service.dto.MenuDTO;
import cn.know.act.proton.system.service.mapper.MenuMapper;
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
import java.util.UUID;
import cn.know.act.proton.system.domain.Role_;

/**
 * Service for executing complex queries for {@link Menu } entities in the database.
 * The main input is a {@link MenuCriteria } which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MenuDTO } or a {@link Page} of {@link MenuDTO } which fulfills the criteria.
 */
@Transactional(readOnly = true)
@Service("SysMenuQueryService")
public class MenuQueryService extends QueryService<Menu> {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(MenuQueryService.class);

    @Generated(IRW.CODE_GENERATOR)
    private final MenuJpaRepository menuJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final MenuMapper menuMapper;

    @Generated(IRW.CODE_GENERATOR)
    public MenuQueryService(MenuJpaRepository menuJpaRepository, MenuMapper menuMapper) {
        this.menuJpaRepository = menuJpaRepository;
        this.menuMapper = menuMapper;
    }

    /**
     * Return a {@link Page} of {@link MenuDTO } which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param pageable The pageable, which should be returned.
     * @return the matching entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Transactional(readOnly = true)
    public Page<MenuDTO> findByCriteria(MenuCriteria criteria, Pageable pageable, boolean retDetail) {
        if (log.isDebugEnabled()) {
            log.debug("find by criteria : {}, pageable: {}", criteria, pageable);
        }
        final Specification<Menu> specification = createSpecification(criteria);
        if (retDetail) {
            return menuJpaRepository.findAll(specification, pageable).map(menuMapper::toDtoWithModel);
        } else {
            return menuJpaRepository.findAll(specification, pageable).map(menuMapper::toDto);
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
    public long countByCriteria(MenuCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("count by criteria : {}", criteria);
        }
        final Specification<Menu> specification = createSpecification(criteria);
        return menuJpaRepository.count(specification);
    }

    /**
     * Return a {@link List} of {@link MenuDTO } which is the node's children
     * <p>
     * if nodeId is null, will get a {@link List} of roots {@link MenuDTO }
     *
     * @param nodeId the parent node
     * @param level  the level of children from the parent node
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    @Transactional(readOnly = true)
    public List<MenuDTO> getTree(UUID nodeId, Integer level, Boolean includeNode) {
        if (log.isDebugEnabled()) {
            log.debug("get tree, node id: {}, level: {} , includeNode: {}", nodeId, level, includeNode);
        }
        return menuJpaRepository.getTree(nodeId, level, includeNode == null ? false : includeNode).stream().map(menuMapper::toDto).collect(Collectors.toList());
    }

    @Generated(IRW.CODE_GENERATOR)
    @Transactional(readOnly = true)
    public Optional<MenuDTO> findParents(UUID nodeId, Integer level) {
        if (log.isDebugEnabled()) {
            log.debug("get node parents: {}, level: {}", nodeId, level);
        }
        return menuJpaRepository.getParents(nodeId, level).map(menuMapper::toDto);
    }

    /**
     * Return a {@link List} of {@link MenuDTO } which matches the criteria from the database.
     * <p>
     * The list will include the nodes to the root node
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param pageable The pageable, which should be returned.
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    @Transactional(readOnly = true)
    public List<MenuDTO> findTree(MenuCriteria criteria, Pageable pageable) {
        if (log.isDebugEnabled()) {
            log.debug("find tree by criteria: {}, pageable: {} ", criteria, pageable);
        }
        final Specification<Menu> specification = createSpecification(criteria);
        return menuJpaRepository.findTree(specification, pageable).stream().map(menuMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Function to convert MenuCriteria to a {@link Specification}.
     */
    @Generated(IRW.CODE_GENERATOR)
    private Specification<Menu> createSpecification(MenuCriteria criteria) {
        Specification<Menu> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Menu_.name));
            }
            if (criteria.getIcon() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIcon(), Menu_.icon));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Menu_.title));
            }
            if (criteria.getPath() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPath(), Menu_.path));
            }
            if (criteria.getComponent() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComponent(), Menu_.component));
            }
            if (criteria.getHideInBread() != null) {
                specification = specification.and(buildSpecification(criteria.getHideInBread(), Menu_.hideInBread));
            }
            if (criteria.getHideInMenu() != null) {
                specification = specification.and(buildSpecification(criteria.getHideInMenu(), Menu_.hideInMenu));
            }
            if (criteria.getNotCache() != null) {
                specification = specification.and(buildSpecification(criteria.getNotCache(), Menu_.notCache));
            }
            if (criteria.getRoles() != null) {
                specification = specification.and(buildReferringESpecification(criteria.getRoles(), Menu_.roles, Role_.id));
            }
            if (criteria.get_parent() != null) {
                specification = specification.and(buildReferringESpecification(criteria.get_parent(), Menu_._parent, Menu_.id));
            }
        }
        return specification;
    }
}
