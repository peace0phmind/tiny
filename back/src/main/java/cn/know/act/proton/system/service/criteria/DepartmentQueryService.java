package cn.know.act.proton.system.service.criteria;

import cn.know.act.proton.core.service.QueryService;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.domain.Department;
import cn.know.act.proton.system.domain.Department_;
import cn.know.act.proton.system.repository.jpa.DepartmentJpaRepository;
import cn.know.act.proton.system.service.dto.DepartmentDTO;
import cn.know.act.proton.system.service.mapper.DepartmentMapper;
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

/**
 * Service for executing complex queries for {@link Department } entities in the database.
 * The main input is a {@link DepartmentCriteria } which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DepartmentDTO } or a {@link Page} of {@link DepartmentDTO } which fulfills the criteria.
 */
@Transactional(readOnly = true)
@Service("SysDepartmentQueryService")
public class DepartmentQueryService extends QueryService<Department> {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(DepartmentQueryService.class);

    @Generated(IRW.CODE_GENERATOR)
    private final DepartmentJpaRepository departmentJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final DepartmentMapper departmentMapper;

    @Generated(IRW.CODE_GENERATOR)
    public DepartmentQueryService(DepartmentJpaRepository departmentJpaRepository, DepartmentMapper departmentMapper) {
        this.departmentJpaRepository = departmentJpaRepository;
        this.departmentMapper = departmentMapper;
    }

    /**
     * Return a {@link Page} of {@link DepartmentDTO } which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param pageable The pageable, which should be returned.
     * @return the matching entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Transactional(readOnly = true)
    public Page<DepartmentDTO> findByCriteria(DepartmentCriteria criteria, Pageable pageable, boolean retDetail) {
        if (log.isDebugEnabled()) {
            log.debug("find by criteria : {}, pageable: {}", criteria, pageable);
        }
        final Specification<Department> specification = createSpecification(criteria);
        if (retDetail) {
            return departmentJpaRepository.findAll(specification, pageable).map(departmentMapper::toDtoWithModel);
        } else {
            return departmentJpaRepository.findAll(specification, pageable).map(departmentMapper::toDto);
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
    public long countByCriteria(DepartmentCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("count by criteria : {}", criteria);
        }
        final Specification<Department> specification = createSpecification(criteria);
        return departmentJpaRepository.count(specification);
    }

    /**
     * Return a {@link List} of {@link DepartmentDTO } which is the node's children
     * <p>
     * if nodeId is null, will get a {@link List} of roots {@link DepartmentDTO }
     *
     * @param nodeId the parent node
     * @param level  the level of children from the parent node
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    @Transactional(readOnly = true)
    public List<DepartmentDTO> getTree(UUID nodeId, Integer level, Boolean includeNode) {
        if (log.isDebugEnabled()) {
            log.debug("get tree, node id: {}, level: {} , includeNode: {}", nodeId, level, includeNode);
        }
        return departmentJpaRepository.getTree(nodeId, level, includeNode == null ? false : includeNode).stream().map(departmentMapper::toDto).collect(Collectors.toList());
    }

    @Generated(IRW.CODE_GENERATOR)
    @Transactional(readOnly = true)
    public Optional<DepartmentDTO> findParents(UUID nodeId, Integer level) {
        if (log.isDebugEnabled()) {
            log.debug("get node parents: {}, level: {}", nodeId, level);
        }
        return departmentJpaRepository.getParents(nodeId, level).map(departmentMapper::toDto);
    }

    /**
     * Return a {@link List} of {@link DepartmentDTO } which matches the criteria from the database.
     * <p>
     * The list will include the nodes to the root node
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param pageable The pageable, which should be returned.
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    @Transactional(readOnly = true)
    public List<DepartmentDTO> findTree(DepartmentCriteria criteria, Pageable pageable) {
        if (log.isDebugEnabled()) {
            log.debug("find tree by criteria: {}, pageable: {} ", criteria, pageable);
        }
        final Specification<Department> specification = createSpecification(criteria);
        return departmentJpaRepository.findTree(specification, pageable).stream().map(departmentMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Function to convert DepartmentCriteria to a {@link Specification}.
     */
    @Generated(IRW.CODE_GENERATOR)
    private Specification<Department> createSpecification(DepartmentCriteria criteria) {
        Specification<Department> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Department_.name));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Department_.code));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Department_.description));
            }
            if (criteria.get_parent() != null) {
                specification = specification.and(buildReferringESpecification(criteria.get_parent(), Department_._parent, Department_.id));
            }
        }
        return specification;
    }
}
