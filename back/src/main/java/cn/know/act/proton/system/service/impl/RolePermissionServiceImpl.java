package cn.know.act.proton.system.service.impl;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.domain.RolePermission;
import cn.know.act.proton.system.repository.jpa.RolePermissionJpaRepository;
import cn.know.act.proton.system.service.dto.RolePermissionDTO;
import cn.know.act.proton.system.service.inf.RolePermissionService;
import cn.know.act.proton.system.service.mapper.RolePermissionMapper;
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
 * Service Implementation for managing {@link RolePermission }.
 */
@Transactional
@Service("SysRolePermissionServiceImpl")
public class RolePermissionServiceImpl implements RolePermissionService {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(RolePermissionServiceImpl.class);

    @Generated(IRW.CODE_GENERATOR)
    private final RolePermissionJpaRepository rolePermissionJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final RolePermissionMapper rolePermissionMapper;

    @Generated(IRW.CODE_GENERATOR)
    private final CacheManager cacheManager;

    @Generated(IRW.CODE_GENERATOR)
    public RolePermissionServiceImpl(RolePermissionJpaRepository rolePermissionJpaRepository, RolePermissionMapper rolePermissionMapper, CacheManager cacheManager) {
        this.rolePermissionJpaRepository = rolePermissionJpaRepository;
        this.rolePermissionMapper = rolePermissionMapper;
        this.cacheManager = cacheManager;
    }

    /**
     * Create a RolePermission.
     *
     * @param rolePermissionDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public RolePermissionDTO create(RolePermissionDTO rolePermissionDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save rolePermission: {}", rolePermissionDTO);
        }
        Assert.isNull(rolePermissionDTO.getId(), "Create entity's id must be null.");
        RolePermission rolePermission = rolePermissionJpaRepository.save(rolePermissionMapper.toDomain(rolePermissionDTO));
        RolePermissionDTO result = rolePermissionMapper.toDto(rolePermission);
        // TODO add search option
        return result;
    }

    /**
     * Update a RolePermission.
     *
     * @param rolePermissionDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public RolePermissionDTO update(RolePermissionDTO rolePermissionDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save rolePermission: {}", rolePermissionDTO);
        }
        Assert.notNull(rolePermissionDTO.getId(), "Update entity's id must not be null.");
        RolePermission rolePermission = rolePermissionJpaRepository.getOne(rolePermissionDTO.getId());
        rolePermissionMapper.updateDomain(rolePermissionDTO, rolePermission);
        RolePermission ret = rolePermissionJpaRepository.save(rolePermission);
        RolePermissionDTO result = rolePermissionMapper.toDto(ret);
        // TODO add search option
        return result;
    }

    /**
     * Get all the RolePermissions.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Page<RolePermissionDTO> findAll(Pageable pageable) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get all RolePermissions");
        }
        return rolePermissionJpaRepository.findAll(pageable).map(rolePermissionMapper::toDtoWithModel);
    }

    /**
     * Get one RolePermission by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Optional<RolePermissionDTO> findOne(UUID id) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get RolePermission: {}", id);
        }
        return rolePermissionJpaRepository.findWithModelById(id).map(rolePermissionMapper::toDtoWithModel);
    }

    /**
     * Delete the "ids" RolePermission.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public void deleteByIds(List<UUID> ids) {
        if (log.isDebugEnabled()) {
            log.debug("Request to delete RolePermissions: {}", ids);
        }
        rolePermissionJpaRepository.deleteAll(rolePermissionJpaRepository.findAllById(ids));
    }
}
