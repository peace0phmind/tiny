package cn.know.act.proton.system.service.criteria;

import cn.know.act.proton.core.service.QueryService;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.domain.Permission;
import cn.know.act.proton.system.domain.Permission_;
import cn.know.act.proton.system.repository.jpa.PermissionJpaRepository;
import cn.know.act.proton.system.service.dto.PermissionDTO;
import cn.know.act.proton.system.service.mapper.PermissionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Generated;
import cn.know.act.proton.system.domain.OperationPerm;
import cn.know.act.proton.system.domain.OperationPerm_;
import cn.know.act.proton.system.domain.AttributePerm;
import cn.know.act.proton.system.domain.AttributePerm_;

/**
 * Service for executing complex queries for {@link Permission } entities in the database.
 * The main input is a {@link PermissionCriteria } which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PermissionDTO } or a {@link Page} of {@link PermissionDTO } which fulfills the criteria.
 */
@Transactional(readOnly = true)
@Service("SysPermissionQueryService")
public class PermissionQueryService extends QueryService<Permission> {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(PermissionQueryService.class);

    @Generated(IRW.CODE_GENERATOR)
    private final PermissionJpaRepository permissionJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final PermissionMapper permissionMapper;

    @Generated(IRW.CODE_GENERATOR)
    public PermissionQueryService(PermissionJpaRepository permissionJpaRepository, PermissionMapper permissionMapper) {
        this.permissionJpaRepository = permissionJpaRepository;
        this.permissionMapper = permissionMapper;
    }

    /**
     * Return a {@link Page} of {@link PermissionDTO } which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param pageable The pageable, which should be returned.
     * @return the matching entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Transactional(readOnly = true)
    public Page<PermissionDTO> findByCriteria(PermissionCriteria criteria, Pageable pageable, boolean retDetail) {
        if (log.isDebugEnabled()) {
            log.debug("find by criteria : {}, pageable: {}", criteria, pageable);
        }
        final Specification<Permission> specification = createSpecification(criteria);
        if (retDetail) {
            return permissionJpaRepository.findAll(specification, pageable).map(permissionMapper::toDtoWithModel);
        } else {
            return permissionJpaRepository.findAll(specification, pageable).map(permissionMapper::toDto);
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
    public long countByCriteria(PermissionCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("count by criteria : {}", criteria);
        }
        final Specification<Permission> specification = createSpecification(criteria);
        return permissionJpaRepository.count(specification);
    }

    /**
     * Function to convert PermissionCriteria to a {@link Specification}.
     */
    @Generated(IRW.CODE_GENERATOR)
    private Specification<Permission> createSpecification(PermissionCriteria criteria) {
        Specification specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getModelName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModelName(), Permission_.modelName));
            }
            if (criteria.getModelCnName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModelCnName(), Permission_.modelCnName));
            }
            if (criteria.getModelUri() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModelUri(), Permission_.modelUri));
            }
            if (criteria.isOperationPermCriteria()) {
                specification = specification.and(buildType(OperationPerm.class));
                OperationPermCriteria operationPermCriteria = criteria.asOperationPermCriteria();
                if (operationPermCriteria.getType() != null) {
                    specification = specification.and(buildSpecification(operationPermCriteria.getType(), OperationPerm_.type));
                }
            }
            if (criteria.isAttributePermCriteria()) {
                specification = specification.and(buildType(AttributePerm.class));
                AttributePermCriteria attributePermCriteria = criteria.asAttributePermCriteria();
                if (attributePermCriteria.getAttributeName() != null) {
                    specification = specification.and(buildStringSpecification(attributePermCriteria.getAttributeName(), AttributePerm_.attributeName));
                }
            }
        }
        return specification;
    }
}
