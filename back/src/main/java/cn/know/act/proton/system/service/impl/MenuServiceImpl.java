package cn.know.act.proton.system.service.impl;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.core.util.JpaUtil;
import cn.know.act.proton.system.domain.Menu;
import cn.know.act.proton.system.repository.jpa.MenuJpaRepository;
import cn.know.act.proton.system.service.dto.MenuDTO;
import cn.know.act.proton.system.service.inf.MenuService;
import cn.know.act.proton.system.service.mapper.MenuMapper;
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
import cn.know.act.proton.system.repository.jpa.RoleJpaRepository;

/**
 * Service Implementation for managing {@link Menu }.
 */
@Transactional
@Service("SysMenuServiceImpl")
public class MenuServiceImpl implements MenuService {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Generated(IRW.CODE_GENERATOR)
    private final MenuJpaRepository menuJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final MenuMapper menuMapper;

    @Generated(IRW.CODE_GENERATOR)
    private final CacheManager cacheManager;

    @Generated(IRW.CODE_GENERATOR)
    @Autowired
    private RoleJpaRepository roleJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    public MenuServiceImpl(MenuJpaRepository menuJpaRepository, MenuMapper menuMapper, CacheManager cacheManager) {
        this.menuJpaRepository = menuJpaRepository;
        this.menuMapper = menuMapper;
        this.cacheManager = cacheManager;
    }

    /**
     * Create a Menu.
     *
     * @param menuDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public MenuDTO create(MenuDTO menuDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save menu: {}", menuDTO);
        }
        Assert.isNull(menuDTO.getId(), "Create entity's id must be null.");
        Menu menu = menuJpaRepository.save(menuMapper.toDomain(menuDTO));
        JpaUtil.addItemRelation(menuDTO.getRoles(), menu.getRoles(), roleJpaRepository);
        MenuDTO result = menuMapper.toDto(menu);
        // TODO add search option
        return result;
    }

    /**
     * Update a Menu.
     *
     * @param menuDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public MenuDTO update(MenuDTO menuDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save menu: {}", menuDTO);
        }
        Assert.notNull(menuDTO.getId(), "Update entity's id must not be null.");
        Menu menu = menuJpaRepository.getOne(menuDTO.getId());
        JpaUtil.itemRelationUpdate(menuDTO.getRoles(), menu.getRoles(), roleJpaRepository);
        menuMapper.updateDomain(menuDTO, menu);
        Menu ret = menuJpaRepository.save(menu);
        MenuDTO result = menuMapper.toDto(ret);
        // TODO add search option
        return result;
    }

    /**
     * Get all the Menus.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Page<MenuDTO> findAll(Pageable pageable) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get all Menus");
        }
        return menuJpaRepository.findAll(pageable).map(menuMapper::toDtoWithModel);
    }

    /**
     * Get one Menu by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Optional<MenuDTO> findOne(UUID id) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get Menu: {}", id);
        }
        return menuJpaRepository.findWithModelById(id).map(menuMapper::toDtoWithModel);
    }

    /**
     * Delete the "ids" Menu.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public void deleteByIds(List<UUID> ids) {
        if (log.isDebugEnabled()) {
            log.debug("Request to delete Menus: {}", ids);
        }
        menuJpaRepository.deleteAll(menuJpaRepository.findAllById(ids));
    }

    /**
     * add a node Menu before the sibling
     *
     * @param menuDTO the node entity
     * @param siblingId              the sibling node id
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public MenuDTO addBefore(MenuDTO menuDTO, UUID siblingId) {
        if (log.isDebugEnabled()) {
            log.debug("Request to add menu: {}, before: {}", menuDTO, siblingId);
        }
        Menu menu = menuMapper.toDomain(menuDTO);
        menu = menuJpaRepository.addBefore(menu, siblingId);
        MenuDTO result = menuMapper.toDto(menu);
        // TODO add search option
        return result;
    }

    /**
     * add a node Menu after the sibling
     *
     * @param menuDTO the node entity
     * @param siblingId              the sibling node id
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public MenuDTO addAfter(MenuDTO menuDTO, UUID siblingId) {
        if (log.isDebugEnabled()) {
            log.debug("Request to add menu: {}, after: {}", menuDTO, siblingId);
        }
        Menu menu = menuMapper.toDomain(menuDTO);
        menu = menuJpaRepository.addAfter(menu, siblingId);
        MenuDTO result = menuMapper.toDto(menu);
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
        menuJpaRepository.moveUnder(nodeId, parentId);
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
        menuJpaRepository.moveBefore(nodeId, siblingId);
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
        menuJpaRepository.moveAfter(nodeId, siblingId);
    }
}
