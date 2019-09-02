package cn.know.act.proton.system.service.criteria;

import cn.know.act.proton.core.service.QueryService;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.domain.RolePermission;
import cn.know.act.proton.system.domain.RolePermission_;
import cn.know.act.proton.system.repository.jpa.RolePermissionJpaRepository;
import cn.know.act.proton.system.service.dto.RolePermissionDTO;
import cn.know.act.proton.system.service.mapper.RolePermissionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Generated;
import cn.know.act.proton.system.domain.Role_;
import cn.know.act.proton.system.domain.OperationRolePerm;
import cn.know.act.proton.system.domain.OperationRolePerm_;
import cn.know.act.proton.system.domain.AttributeRolePerm;
import cn.know.act.proton.system.domain.AttributeRolePerm_;

/**
 * Service for executing complex queries for {@link RolePermission } entities in the database.
 * The main input is a {@link RolePermissionCriteria } which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RolePermissionDTO } or a {@link Page} of {@link RolePermissionDTO } which fulfills the criteria.
 */
@Transactional(readOnly = true)
@Service("SysRolePermissionQueryService")
public class RolePermissionQueryService extends QueryService<RolePermission> {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(RolePermissionQueryService.class);

    @Generated(IRW.CODE_GENERATOR)
    private final RolePermissionJpaRepository rolePermissionJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final RolePermissionMapper rolePermissionMapper;

    @Generated(IRW.CODE_GENERATOR)
    public RolePermissionQueryService(RolePermissionJpaRepository rolePermissionJpaRepository, RolePermissionMapper rolePermissionMapper) {
        this.rolePermissionJpaRepository = rolePermissionJpaRepository;
        this.rolePermissionMapper = rolePermissionMapper;
    }

    /**
     * Return a {@link Page} of {@link RolePermissionDTO } which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param pageable The pageable, which should be returned.
     * @return the matching entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Transactional(readOnly = true)
    public Page<RolePermissionDTO> findByCriteria(RolePermissionCriteria criteria, Pageable pageable, boolean retDetail) {
        if (log.isDebugEnabled()) {
            log.debug("find by criteria : {}, pageable: {}", criteria, pageable);
        }
        final Specification<RolePermission> specification = createSpecification(criteria);
        if (retDetail) {
            return rolePermissionJpaRepository.findAll(specification, pageable).map(rolePermissionMapper::toDtoWithModel);
        } else {
            return rolePermissionJpaRepository.findAll(specification, pageable).map(rolePermissionMapper::toDto);
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
    public long countByCriteria(RolePermissionCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("count by criteria : {}", criteria);
        }
        final Specification<RolePermission> specification = createSpecification(criteria);
        return rolePermissionJpaRepository.count(specification);
    }

    /**
     * Function to convert RolePermissionCriteria to a {@link Specification}.
     */
    @Generated(IRW.CODE_GENERATOR)
    private Specification<RolePermission> createSpecification(RolePermissionCriteria criteria) {
        Specification specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getRole() != null) {
                specification = specification.and(buildReferringESpecification(criteria.getRole(), RolePermission_.role, Role_.id));
            }
            if (criteria.isOperationRolePermCriteria()) {
                specification = specification.and(buildType(OperationRolePerm.class));
                OperationRolePermCriteria operationRolePermCriteria = criteria.asOperationRolePermCriteria();
                if (operationRolePermCriteria.getAllow() != null) {
                    specification = specification.and(buildSpecification(operationRolePermCriteria.getAllow(), OperationRolePerm_.allow));
                }
            }
            if (criteria.isAttributeRolePermCriteria()) {
                specification = specification.and(buildType(AttributeRolePerm.class));
                AttributeRolePermCriteria attributeRolePermCriteria = criteria.asAttributeRolePermCriteria();
                if (attributeRolePermCriteria.getType() != null) {
                    specification = specification.and(buildSpecification(attributeRolePermCriteria.getType(), AttributeRolePerm_.type));
                }
            }
        }
        return specification;
    }
}
