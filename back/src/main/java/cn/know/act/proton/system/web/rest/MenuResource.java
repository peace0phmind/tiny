package cn.know.act.proton.system.web.rest;

import cn.know.act.proton.core.syslog.SysLog;
import cn.know.act.proton.core.web.util.HeaderUtil;
import cn.know.act.proton.core.web.util.ResponseUtil;
import cn.know.act.proton.core.web.rest.errors.BadRequestAlertException;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.service.criteria.MenuCriteria;
import cn.know.act.proton.system.service.criteria.MenuQueryService;
import cn.know.act.proton.system.service.dto.MenuDTO;
import cn.know.act.proton.system.service.inf.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Generated;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing {@link cn.know.act.proton.system.domain.Menu }.
 */
@RequestMapping("/api")
@RestController("SysMenuResource")
public class MenuResource {

    @Generated(IRW.CODE_GENERATOR)
    private static final String ENTITY_NAME = "Menu";

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(MenuResource.class);

    @Generated(IRW.CODE_GENERATOR)
    private final MenuService menuService;

    @Generated(IRW.CODE_GENERATOR)
    private final MenuQueryService menuQueryService;

    @Generated(IRW.CODE_GENERATOR)
    @Value("${proton.clientApp.name}")
    private String applicationName;

    @Generated(IRW.CODE_GENERATOR)
    public MenuResource(MenuService menuService, MenuQueryService menuQueryService) {
        this.menuService = menuService;
        this.menuQueryService = menuQueryService;
    }

    /**
     * {@code POST  /sys-menus : Create a new Menu.
     *
     * @param menuDTO the Menu to add.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new Menu, or with status {@code 400 (Bad Request)} if the Menu has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("创建菜单")
    @PostMapping("/sys-menus")
    @PreAuthorize("hasPermission(#templateDTO, 'SysMenu_CREATE')")
    public ResponseEntity<MenuDTO> createMenu(@Valid @RequestBody MenuDTO menuDTO) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to create Menu: {}", menuDTO);
        }
        if (menuDTO.getId() != null) {
            throw new BadRequestAlertException("A new Menu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MenuDTO result = menuService.create(menuDTO);
        return ResponseEntity.created(new URI("/api/sys-menus/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * {@code GET  /sys-menus/:id} : get the "id" Menu.
     *
     * @param id the id of the Menu to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the Menu, or with status {@code 404 (Not Found)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("获取单个菜单")
    @GetMapping("/sys-menus/{id}")
    @PreAuthorize("hasPermission(#id, '', 'SysMenu_READ')")
    public ResponseEntity<MenuDTO> getMenu(@PathVariable UUID id) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ${__domainSimpleClassName__} : {}", id);
        }
        Optional<MenuDTO> menuDTO = menuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(menuDTO);
    }

    /**
     * {@code GET  /sys-menus} : get all the Menus.
     *
     * @param criteria the criteria which the requested entities should match.
     * @param pageable the page object
     * @param _d       return detail dto
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of Menus in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询菜单列表")
    @GetMapping("/sys-menus")
    @PreAuthorize("hasPermission(#criteria, 'SysMenu_READ')")
    public ResponseEntity<Page<MenuDTO>> findMenus(MenuCriteria criteria, Pageable pageable, Boolean _d) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get Menus");
        }
        if (_d == null) {
            _d = true;
        }
        return ResponseEntity.ok().body(menuQueryService.findByCriteria(criteria, pageable, _d));
    }

    /**
     * {@code GET  /sys-menus/count} : count all the Menus.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询菜单数量")
    @GetMapping("/sys-menus/count")
    @PreAuthorize("hasPermission(#criteria, 'SysMenu_READ')")
    public ResponseEntity<Long> countMenus(MenuCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to count TestEntities by criteria: {}", criteria);
        }
        return ResponseEntity.ok().body(menuQueryService.countByCriteria(criteria));
    }

    /**
     * {@code PUT  /sys-menus } : Updates an existing Menu.
     *
     * @param menuDTO the Menu to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated Menu,
     * or with status {@code 400 (Bad Request)} if the Menu is not valid,
     * or with status {@code 500 (Internal Server Error)} if the Menu couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("更新菜单")
    @PutMapping("/sys-menus")
    @PreAuthorize("hasPermission(#templateDTO, 'SysMenu_UPDATE')")
    public ResponseEntity<MenuDTO> updateMenu(@Valid @RequestBody MenuDTO menuDTO) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to update Menu: {}", menuDTO);
        }
        if (menuDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MenuDTO result = menuService.update(menuDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * {@code DELETE  /sys-menus/:id} : delete the "ids" Menu.
     *
     * @param ids the ids of the Menus to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("删除菜单")
    @DeleteMapping("/sys-menus/{ids}")
    @PreAuthorize("hasPermission(#ids, '', 'SysMenu_DELETE')")
    public ResponseEntity<Void> deleteMenu(@PathVariable List<UUID> ids) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to delete Menu : {}", ids);
        }
        menuService.deleteByIds(ids);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, ids.toString())).build();
    }

    /**
     * {@code POST  /sys-menus : Create a new Menu before the sibling.
     *
     * @param menuDTO the Menu to add.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new Menu, or with status {@code 400 (Bad Request)} if the Menu has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("节点前创建菜单")
    @PostMapping("/sys-menus/tree/before/{siblingId}")
    @PreAuthorize("hasPermission(#templateDTO, 'SysMenu_CREATE')")
    public ResponseEntity<MenuDTO> addMenuBefore(@Valid @RequestBody MenuDTO menuDTO, @PathVariable UUID siblingId) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to add Menu: {}, before: {}", menuDTO, siblingId);
        }
        if (menuDTO.getId() != null) {
            throw new BadRequestAlertException("A new Menu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MenuDTO result = menuService.addBefore(menuDTO, siblingId);
        return ResponseEntity.created(new URI("/api/sys-menus/" + result.getId())).body(result);
    }

    /**
     * {@code POST  /sys-menus : Create a new Menu after the sibling.
     *
     * @param menuDTO the Menu to add.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new Menu, or with status {@code 400 (Bad Request)} if the Menu has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("节点后创建菜单")
    @PostMapping("/sys-menus/tree/after/{siblingId}")
    @PreAuthorize("hasPermission(#templateDTO, 'SysMenu_CREATE')")
    public ResponseEntity<MenuDTO> addMenuAfter(@Valid @RequestBody MenuDTO menuDTO, @PathVariable UUID siblingId) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to add Menu: {}, after: {}", menuDTO, siblingId);
        }
        if (menuDTO.getId() != null) {
            throw new BadRequestAlertException("A new Menu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MenuDTO result = menuService.addAfter(menuDTO, siblingId);
        return ResponseEntity.created(new URI("/api/sys-menus/" + result.getId())).body(result);
    }

    /**
     * {@code PUT  /sys-menus/tree/:nodeId/under/:parentId : move the "nodeId" Menu under the "parentId" node .
     *
     * @param nodeId   the id of the Menu to be moved.
     * @param parentId the id of the Menu is parent.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("移动菜单到节点下")
    @PutMapping("/sys-menus/tree/{nodeId}/under/{parentId}")
    @PreAuthorize("hasPermission(#nodeId, '', 'SysMenu_MOVE')")
    public ResponseEntity<Void> moveMenuUnder(@PathVariable UUID nodeId, @PathVariable UUID parentId) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to move Menu : {}, under : {}", nodeId, parentId);
        }
        menuService.moveUnder(nodeId, parentId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@code PUT  /sys-menus/tree/:nodeId/before/:siblingId : move the "nodeId" Menu before the "siblingId" node .
     *
     * @param nodeId    the id of the Menu to be moved.
     * @param siblingId the id of the Menu is the node's sibling.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("移动菜单到节点前")
    @PutMapping("/sys-menus/tree/{nodeId}/before/{siblingId}")
    @PreAuthorize("hasPermission(#nodeId, '', 'SysMenu_MOVE')")
    public ResponseEntity<Void> moveMenuBefore(@PathVariable UUID nodeId, @PathVariable UUID siblingId) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to move Menu : {}, before : {}", nodeId, siblingId);
        }
        menuService.moveBefore(nodeId, siblingId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@code PUT  /sys-menus/tree/:nodeId/after/:siblingId : move the "nodeId" Menu after the "siblingId" node .
     *
     * @param nodeId    the id of the Menu to be moved.
     * @param siblingId the id of the Menu is the node's sibling.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("移动菜单到节点后")
    @PutMapping("/sys-menus/tree/{nodeId}/after/{siblingId}")
    @PreAuthorize("hasPermission(#nodeId, '', 'SysMenu_MOVE')")
    public ResponseEntity<Void> moveMenuAfter(@PathVariable UUID nodeId, @PathVariable UUID siblingId) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to move Menu : {}, after : {}", nodeId, siblingId);
        }
        menuService.moveAfter(nodeId, siblingId);
        return ResponseEntity.noContent().build();
    }

    /**
     * get tree roots
     *
     * @param level
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("获取菜单树")
    @GetMapping("/sys-menus/tree")
    @PreAuthorize("hasPermission(null, '', 'SysMenu_READ')")
    public ResponseEntity<List<MenuDTO>> getMenuTrees(Integer level, Boolean includeNode) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ${__domainSimpleClassName__} tree roots: level = {}", level);
        }
        return ResponseEntity.ok().body(menuQueryService.getTree(null, level, includeNode));
    }

    /**
     * get node children
     *
     * @param id
     * @param level
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("获取某个菜单树")
    @GetMapping("/sys-menus/{id}/tree")
    @PreAuthorize("hasPermission(#id, '', 'SysMenu_READ')")
    public ResponseEntity<List<MenuDTO>> getMenuTree(@PathVariable UUID id, Integer level, Boolean includeNode) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ${__domainSimpleClassName__} tree: {}, {}", id, level);
        }
        return ResponseEntity.ok().body(menuQueryService.getTree(id, level, includeNode));
    }

    /**
     * find node parents, return parent to node
     *
     * @param nodeId
     * @param level
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("获取菜单父节点树")
    @GetMapping("/sys-menus/{nodeId}/_parent")
    @PreAuthorize("hasPermission(#nodeId, '', 'SysMenu_READ')")
    public ResponseEntity<MenuDTO> findMenuTreeParents(@PathVariable UUID nodeId, Integer level) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ${__domainSimpleClassName__}'s parents : {}, level: {}", nodeId, level);
        }
        Optional<MenuDTO> menuDTO = menuQueryService.findParents(nodeId, level);
        return ResponseUtil.wrapOrNotFound(menuDTO);
    }

    /**
     * find tree node by condition
     *
     * @param criteria the criteria which the requested entities should match.
     * @param pageable the page object
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("查询返回菜单树")
    @GetMapping("/sys-menus/tree/search")
    @PreAuthorize("hasPermission(#criteria, 'SysMenu_READ')")
    public ResponseEntity<List<MenuDTO>> findMenuTrees(MenuCriteria criteria, Pageable pageable) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ${__domainSimpleClassName__} tree: criteria = {}, pageable = {}", criteria, pageable);
        }
        return ResponseEntity.ok().body(menuQueryService.findTree(criteria, pageable));
    }
}
