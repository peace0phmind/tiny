package cn.know.act.proton.system.service.impl;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.core.util.JpaUtil;
import cn.know.act.proton.system.domain.Role;
import cn.know.act.proton.system.repository.jpa.RoleJpaRepository;
import cn.know.act.proton.system.service.dto.RoleDTO;
import cn.know.act.proton.system.service.inf.RoleService;
import cn.know.act.proton.system.service.mapper.RoleMapper;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import cn.know.act.proton.system.repository.jpa.RolePermissionJpaRepository;
import cn.know.act.proton.system.service.mapper.RolePermissionMapper;
import cn.know.act.proton.system.domain.RolePermission;
import cn.know.act.proton.system.repository.jpa.MenuJpaRepository;
import cn.know.act.proton.system.repository.jpa.UserJpaRepository;
import java.util.stream.Collectors;
import org.springframework.util.CollectionUtils;

/**
 * Service Implementation for managing {@link Role }.
 */
@Transactional
@Service("SysRoleServiceImpl")
public class RoleServiceImpl implements RoleService {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Generated(IRW.CODE_GENERATOR)
    private final RoleJpaRepository roleJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final RoleMapper roleMapper;

    @Generated(IRW.CODE_GENERATOR)
    private final CacheManager cacheManager;

    @Generated(IRW.CODE_GENERATOR)
    @Autowired
    private RolePermissionJpaRepository rolePermissionJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Generated(IRW.CODE_GENERATOR)
    @Autowired
    private MenuJpaRepository menuJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    @Autowired
    private UserJpaRepository userJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    public RoleServiceImpl(RoleJpaRepository roleJpaRepository, RoleMapper roleMapper, CacheManager cacheManager) {
        this.roleJpaRepository = roleJpaRepository;
        this.roleMapper = roleMapper;
        this.cacheManager = cacheManager;
    }

    /**
     * Update a Role.
     *
     * @param roleDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RoleDTO update(RoleDTO roleDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save role: {}", roleDTO);
        }
        Assert.notNull(roleDTO.getId(), "Update entity's id must not be null.");
        Role role = roleJpaRepository.getOne(roleDTO.getId());
        JpaUtil.itemRelationUpdate(roleDTO.getUsers(), role.getUsers(), userJpaRepository);
        JpaUtil.itemRelationUpdate(roleDTO.getMenus(), role.getMenus(), menuJpaRepository);
        if (!CollectionUtils.isEmpty(roleDTO.getPermissions())) {
            List<RolePermission> rolePermissions = roleDTO.getPermissions().stream().map(rolePermissionMapper::toDomain).collect(Collectors.toList());
            List<UUID> permissionIds = rolePermissions.stream().map(rp -> rp.getPermission().getId()).collect(Collectors.toList());
            // get list need to remove
            List<RolePermission> needToRemove = role.getPermissions().stream().filter(rp -> !permissionIds.contains(rp.getPermission().getId())).collect(Collectors.toList());
            // get list need to create
            List<UUID> idsWithEachOther = role.getPermissions().stream().filter(rp -> permissionIds.contains(rp.getPermission().getId())).map(p -> p.getPermission().getId()).collect(Collectors.toList());
            permissionIds.removeAll(idsWithEachOther);
            List<RolePermission> needToCreate = rolePermissions.stream().filter(rp -> permissionIds.contains(rp.getPermission().getId())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(needToRemove)) {
                rolePermissionJpaRepository.deleteAll(needToRemove);
            }
            if (!CollectionUtils.isEmpty(needToCreate)) {
                needToCreate.forEach(rp -> rp.setRole(role));
                rolePermissionJpaRepository.saveAll(needToCreate);
            }
        }
        JpaUtil.listUpdate(roleDTO.getPermissions(), rolePermissionJpaRepository, RolePermission::setRole, role, item -> item.getRole() != null && item.getRole().getId().equals(role.getId()), rolePermissionMapper::toDomain, rolePermissionMapper::toDtoOnlyId, rolePermissionMapper::updateDomain);
        roleMapper.updateDomain(roleDTO, role);
        Role ret = roleJpaRepository.save(role);
        RoleDTO result = roleMapper.toDto(ret);
        // TODO add search option
        return result;
    }

    /**
     * Create a Role.
     *
     * @param roleDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public RoleDTO create(RoleDTO roleDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save role: {}", roleDTO);
        }
        Assert.isNull(roleDTO.getId(), "Create entity's id must be null.");
        Role role = roleJpaRepository.save(roleMapper.toDomain(roleDTO));
        JpaUtil.addItemRelation(roleDTO.getUsers(), role.getUsers(), userJpaRepository);
        JpaUtil.addItemRelation(roleDTO.getMenus(), role.getMenus(), menuJpaRepository);
        JpaUtil.createList(roleDTO.getPermissions(), rolePermissionJpaRepository, RolePermission::setRole, role, rolePermissionMapper::toDomain);
        RoleDTO result = roleMapper.toDto(role);
        // TODO add search option
        return result;
    }

    /**
     * Get all the Roles.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Page<RoleDTO> findAll(Pageable pageable) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get all Roles");
        }
        return roleJpaRepository.findAll(pageable).map(roleMapper::toDtoWithModel);
    }

    /**
     * Get one Role by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Optional<RoleDTO> findOne(UUID id) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get Role: {}", id);
        }
        return roleJpaRepository.findWithModelById(id).map(roleMapper::toDtoWithModel);
    }

    /**
     * Delete the "ids" Role.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public void deleteByIds(List<UUID> ids) {
        if (log.isDebugEnabled()) {
            log.debug("Request to delete Roles: {}", ids);
        }
        roleJpaRepository.deleteAll(roleJpaRepository.findAllById(ids));
        ids.forEach(cacheManager.getCache(RoleJpaRepository.CACHE_NAME)::evict);
    }
}
