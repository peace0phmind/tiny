package cn.know.act.proton.system.service.impl;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.domain.Department;
import cn.know.act.proton.system.repository.jpa.DepartmentJpaRepository;
import cn.know.act.proton.system.service.dto.DepartmentDTO;
import cn.know.act.proton.system.service.inf.DepartmentService;
import cn.know.act.proton.system.service.mapper.DepartmentMapper;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Implementation for managing {@link Department }.
 */
@Transactional
@Service("SysDepartmentServiceImpl")
public class DepartmentServiceImpl implements DepartmentService {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Generated(IRW.CODE_GENERATOR)
    private final DepartmentJpaRepository departmentJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final DepartmentMapper departmentMapper;

    @Generated(IRW.CODE_GENERATOR)
    private final CacheManager cacheManager;

    @Generated(IRW.CODE_GENERATOR)
    public DepartmentServiceImpl(DepartmentJpaRepository departmentJpaRepository, DepartmentMapper departmentMapper, CacheManager cacheManager) {
        this.departmentJpaRepository = departmentJpaRepository;
        this.departmentMapper = departmentMapper;
        this.cacheManager = cacheManager;
    }

    /**
     * Create a Department.
     *
     * @param departmentDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public DepartmentDTO create(DepartmentDTO departmentDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save department: {}", departmentDTO);
        }
        Assert.isNull(departmentDTO.getId(), "Create entity's id must be null.");
        Department department = departmentJpaRepository.save(departmentMapper.toDomain(departmentDTO));
        DepartmentDTO result = departmentMapper.toDto(department);
        // TODO add search option
        return result;
    }

    /**
     * Update a Department.
     *
     * @param departmentDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public DepartmentDTO update(DepartmentDTO departmentDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save department: {}", departmentDTO);
        }
        Assert.notNull(departmentDTO.getId(), "Update entity's id must not be null.");
        Department department = departmentJpaRepository.getOne(departmentDTO.getId());
        departmentMapper.updateDomain(departmentDTO, department);
        Department ret = departmentJpaRepository.save(department);
        DepartmentDTO result = departmentMapper.toDto(ret);
        // TODO add search option
        return result;
    }

    /**
     * Get all the Departments.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Page<DepartmentDTO> findAll(Pageable pageable) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get all Departments");
        }
        return departmentJpaRepository.findAll(pageable).map(departmentMapper::toDtoWithModel);
    }

    /**
     * Get one Department by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Optional<DepartmentDTO> findOne(UUID id) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get Department: {}", id);
        }
        return departmentJpaRepository.findWithModelById(id).map(departmentMapper::toDtoWithModel);
    }

    /**
     * Delete the "ids" Department.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public void deleteByIds(List<UUID> ids) {
        if (log.isDebugEnabled()) {
            log.debug("Request to delete Departments: {}", ids);
        }
        departmentJpaRepository.deleteAll(departmentJpaRepository.findAllById(ids));
    }

    /**
     * add a node Department before the sibling
     *
     * @param departmentDTO the node entity
     * @param siblingId              the sibling node id
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public DepartmentDTO addBefore(DepartmentDTO departmentDTO, UUID siblingId) {
        if (log.isDebugEnabled()) {
            log.debug("Request to add department: {}, before: {}", departmentDTO, siblingId);
        }
        Department department = departmentMapper.toDomain(departmentDTO);
        department = departmentJpaRepository.addBefore(department, siblingId);
        DepartmentDTO result = departmentMapper.toDto(department);
        // TODO add search option
        return result;
    }

    /**
     * add a node Department after the sibling
     *
     * @param departmentDTO the node entity
     * @param siblingId              the sibling node id
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public DepartmentDTO addAfter(DepartmentDTO departmentDTO, UUID siblingId) {
        if (log.isDebugEnabled()) {
            log.debug("Request to add department: {}, after: {}", departmentDTO, siblingId);
        }
        Department department = departmentMapper.toDomain(departmentDTO);
        department = departmentJpaRepository.addAfter(department, siblingId);
        DepartmentDTO result = departmentMapper.toDto(department);
        // TODO add search option
        return result;
    }

    /**
     * move the node under the parent.
     *
     * @param nodeId   the node entity id
     * @param parentId the parent node id
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public void moveUnder(UUID nodeId, UUID parentId) {
        if (log.isDebugEnabled()) {
            log.debug("Request to move: {}, under: {}", nodeId, parentId);
        }
        departmentJpaRepository.moveUnder(nodeId, parentId);
    }

    /**
     * move the node before the sibling entity.
     *
     * @param nodeId    the node entity id
     * @param siblingId the sibling node id
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public void moveBefore(UUID nodeId, UUID siblingId) {
        if (log.isDebugEnabled()) {
            log.debug("Request to move: {}, before: {}", nodeId, siblingId);
        }
        departmentJpaRepository.moveBefore(nodeId, siblingId);
    }

    /**
     * move the node after the sibling entity.
     *
     * @param nodeId    the node entity id
     * @param siblingId the sibling node id
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public void moveAfter(UUID nodeId, UUID siblingId) {
        if (log.isDebugEnabled()) {
            log.debug("Request to move: {}, after: {}", nodeId, siblingId);
        }
        departmentJpaRepository.moveAfter(nodeId, siblingId);
    }
}
