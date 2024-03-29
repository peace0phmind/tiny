package cn.know.act.proton.system.service.impl;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.domain.Permission;
import cn.know.act.proton.system.repository.jpa.PermissionJpaRepository;
import cn.know.act.proton.system.service.dto.PermissionDTO;
import cn.know.act.proton.system.service.inf.PermissionService;
import cn.know.act.proton.system.service.mapper.PermissionMapper;
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
import cn.know.act.proton.system.domain.OperationPerm;
import cn.know.act.proton.system.service.dto.OperationPermDTO;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Permission }.
 */
@Transactional
@Service("SysPermissionServiceImpl")
public class PermissionServiceImpl implements PermissionService {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(PermissionServiceImpl.class);

    @Generated(IRW.CODE_GENERATOR)
    private final PermissionJpaRepository permissionJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final PermissionMapper permissionMapper;

    @Generated(IRW.CODE_GENERATOR)
    private final CacheManager cacheManager;

    @Generated(IRW.CODE_GENERATOR)
    public PermissionServiceImpl(PermissionJpaRepository permissionJpaRepository, PermissionMapper permissionMapper, CacheManager cacheManager) {
        this.permissionJpaRepository = permissionJpaRepository;
        this.permissionMapper = permissionMapper;
        this.cacheManager = cacheManager;
    }

    /**
     * Create a Permission.
     *
     * @param permissionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PermissionDTO create(PermissionDTO permissionDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save permission: {}", permissionDTO);
        }
        Assert.isNull(permissionDTO.getId(), "Create entity's id must be null.");
        Permission permission = permissionMapper.toDomain(permissionDTO);
        if (StringUtils.hasText(permission.getModelName())) {
            if (permissionDTO.isOperationPermDTO()) {
                OperationPermDTO operationPermDTO = permissionDTO.asOperationPermDTO();
                List<OperationPerm.Type> types = operationPermDTO.getTypes();
                if (!CollectionUtils.isEmpty(types)) {
                    List<Permission> allPermission = permissionJpaRepository.findAllByModelName(permission.getModelName()).stream().filter(x -> x.isOperationPerm()).collect(Collectors.toList());
                    List<Permission> needToRemove = allPermission.stream().filter(p -> !types.contains(p.asOperationPerm().getType())).collect(Collectors.toList());
                    List<OperationPerm.Type> inEachOther = allPermission.stream().filter(p -> types.contains(p.asOperationPerm().getType())).map(op -> op.asOperationPerm().getType()).collect(Collectors.toList());
                    types.removeAll(inEachOther);
                    permissionJpaRepository.deleteAll(needToRemove);
                    for (OperationPerm.Type type : types) {
                        OperationPerm operationPerm = new OperationPerm();
                        operationPerm.setModelName(permission.getModelName());
                        operationPerm.setModelCnName(permission.getModelCnName());
                        operationPerm.setModelUri(permission.getModelUri());
                        operationPerm.setType(type);
                        permissionJpaRepository.save(operationPerm);
                    }
                }
            }
            if (permission.isAttributePerm()) {
                throw new RuntimeException("尚未支持");
            }
        }
        return permissionDTO;
    }

    /**
     * Update a Permission.
     *
     * @param permissionDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public PermissionDTO update(PermissionDTO permissionDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save permission: {}", permissionDTO);
        }
        Assert.notNull(permissionDTO.getId(), "Update entity's id must not be null.");
        Permission permission = permissionJpaRepository.getOne(permissionDTO.getId());
        permissionMapper.updateDomain(permissionDTO, permission);
        Permission ret = permissionJpaRepository.save(permission);
        PermissionDTO result = permissionMapper.toDto(ret);
        // TODO add search option
        return result;
    }

    /**
     * Get all the Permissions.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Page<PermissionDTO> findAll(Pageable pageable) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get all Permissions");
        }
        return permissionJpaRepository.findAll(pageable).map(permissionMapper::toDtoWithModel);
    }

    /**
     * Get one Permission by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Optional<PermissionDTO> findOne(UUID id) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get Permission: {}", id);
        }
        return permissionJpaRepository.findWithModelById(id).map(permissionMapper::toDtoWithModel);
    }

    /**
     * Delete the "ids" Permission.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public void deleteByIds(List<UUID> ids) {
        if (log.isDebugEnabled()) {
            log.debug("Request to delete Permissions: {}", ids);
        }
        permissionJpaRepository.deleteAll(permissionJpaRepository.findAllById(ids));
    }
}
