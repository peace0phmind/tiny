package cn.know.act.proton.system.service.criteria;

import cn.know.act.proton.core.service.QueryService;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.domain.Role;
import cn.know.act.proton.system.domain.Role_;
import cn.know.act.proton.system.repository.jpa.RoleJpaRepository;
import cn.know.act.proton.system.service.dto.RoleDTO;
import cn.know.act.proton.system.service.mapper.RoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Generated;
import cn.know.act.proton.system.domain.RolePermission_;
import cn.know.act.proton.system.domain.Menu_;
import cn.know.act.proton.system.domain.User_;

/**
 * Service for executing complex queries for {@link Role } entities in the database.
 * The main input is a {@link RoleCriteria } which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RoleDTO } or a {@link Page} of {@link RoleDTO } which fulfills the criteria.
 */
@Transactional(readOnly = true)
@Service("SysRoleQueryService")
public class RoleQueryService extends QueryService<Role> {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(RoleQueryService.class);

    @Generated(IRW.CODE_GENERATOR)
    private final RoleJpaRepository roleJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final RoleMapper roleMapper;

    @Generated(IRW.CODE_GENERATOR)
    public RoleQueryService(RoleJpaRepository roleJpaRepository, RoleMapper roleMapper) {
        this.roleJpaRepository = roleJpaRepository;
        this.roleMapper = roleMapper;
    }

    /**
     * Return a {@link Page} of {@link RoleDTO } which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param pageable The pageable, which should be returned.
     * @return the matching entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Transactional(readOnly = true)
    public Page<RoleDTO> findByCriteria(RoleCriteria criteria, Pageable pageable, boolean retDetail) {
        if (log.isDebugEnabled()) {
            log.debug("find by criteria : {}, pageable: {}", criteria, pageable);
        }
        final Specification<Role> specification = createSpecification(criteria);
        if (retDetail) {
            return roleJpaRepository.findAll(specification, pageable).map(roleMapper::toDtoWithModel);
        } else {
            return roleJpaRepository.findAll(specification, pageable).map(roleMapper::toDto);
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
    public long countByCriteria(RoleCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("count by criteria : {}", criteria);
        }
        final Specification<Role> specification = createSpecification(criteria);
        return roleJpaRepository.count(specification);
    }

    /**
     * Function to convert RoleCriteria to a {@link Specification}.
     */
    @Generated(IRW.CODE_GENERATOR)
    private Specification<Role> createSpecification(RoleCriteria criteria) {
        Specification<Role> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Role_.name));
            }
            if (criteria.getInnerName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getInnerName(), Role_.innerName));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Role_.description));
            }
            if (criteria.getDefaultRole() != null) {
                specification = specification.and(buildSpecification(criteria.getDefaultRole(), Role_.defaultRole));
            }
            if (criteria.getPermissions() != null) {
                specification = specification.and(buildReferringESpecification(criteria.getPermissions(), Role_.permissions, RolePermission_.id));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildSpecification(criteria.getType(), Role_.type));
            }
            if (criteria.getMenus() != null) {
                specification = specification.and(buildReferringESpecification(criteria.getMenus(), Role_.menus, Menu_.id));
            }
            if (criteria.getUsers() != null) {
                specification = specification.and(buildReferringESpecification(criteria.getUsers(), Role_.users, User_.id));
            }
        }
        return specification;
    }
}
