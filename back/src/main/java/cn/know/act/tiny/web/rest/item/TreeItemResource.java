package cn.know.act.tiny.web.rest.item;

import cn.know.act.proton.core.syslog.SysLog;
import cn.know.act.proton.core.web.util.HeaderUtil;
import cn.know.act.proton.core.web.util.ResponseUtil;
import cn.know.act.proton.core.web.rest.errors.BadRequestAlertException;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.tiny.service.criteria.item.TreeItemCriteria;
import cn.know.act.tiny.service.criteria.item.TreeItemQueryService;
import cn.know.act.tiny.service.dto.item.TreeItemDTO;
import cn.know.act.tiny.service.inf.item.TreeItemService;
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
 * REST controller for managing {@link cn.know.act.tiny.domain.item.TreeItem }.
 */
@RequestMapping("/api")
@RestController("TnyTreeItemResource")
public class TreeItemResource {

    @Generated(IRW.CODE_GENERATOR)
    private static final String ENTITY_NAME = "TreeItem";

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(TreeItemResource.class);

    @Generated(IRW.CODE_GENERATOR)
    private final TreeItemService treeItemService;

    @Generated(IRW.CODE_GENERATOR)
    private final TreeItemQueryService treeItemQueryService;

    @Generated(IRW.CODE_GENERATOR)
    @Value("${proton.clientApp.name}")
    private String applicationName;

    @Generated(IRW.CODE_GENERATOR)
    public TreeItemResource(TreeItemService treeItemService, TreeItemQueryService treeItemQueryService) {
        this.treeItemService = treeItemService;
        this.treeItemQueryService = treeItemQueryService;
    }

    /**
     * {@code POST  /tny-tree-items : Create a new TreeItem.
     *
     * @param treeItemDTO the TreeItem to add.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new TreeItem, or with status {@code 400 (Bad Request)} if the TreeItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("创建tree测试item")
    @PostMapping("/tny-tree-items")
    @PreAuthorize("hasPermission(#templateDTO, 'TnyTreeItem_CREATE')")
    public ResponseEntity<TreeItemDTO> createTreeItem(@Valid @RequestBody TreeItemDTO treeItemDTO) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to create TreeItem: {}", treeItemDTO);
        }
        if (treeItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new TreeItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TreeItemDTO result = treeItemService.create(treeItemDTO);
        return ResponseEntity.created(new URI("/api/tny-tree-items/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * {@code GET  /tny-tree-items/:id} : get the "id" TreeItem.
     *
     * @param id the id of the TreeItem to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the TreeItem, or with status {@code 404 (Not Found)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("获取单个tree测试item")
    @GetMapping("/tny-tree-items/{id}")
    @PreAuthorize("hasPermission(#id, '', 'TnyTreeItem_READ')")
    public ResponseEntity<TreeItemDTO> getTreeItem(@PathVariable UUID id) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ${__domainSimpleClassName__} : {}", id);
        }
        Optional<TreeItemDTO> treeItemDTO = treeItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(treeItemDTO);
    }

    /**
     * {@code GET  /tny-tree-items} : get all the TreeItems.
     *
     * @param criteria the criteria which the requested entities should match.
     * @param pageable the page object
     * @param _d       return detail dto
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of TreeItems in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询tree测试item列表")
    @GetMapping("/tny-tree-items")
    @PreAuthorize("hasPermission(#criteria, 'TnyTreeItem_READ')")
    public ResponseEntity<Page<TreeItemDTO>> findTreeItems(TreeItemCriteria criteria, Pageable pageable, Boolean _d) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get TreeItems");
        }
        if (_d == null) {
            _d = true;
        }
        return ResponseEntity.ok().body(treeItemQueryService.findByCriteria(criteria, pageable, _d));
    }

    /**
     * {@code GET  /tny-tree-items/count} : count all the TreeItems.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询tree测试item数量")
    @GetMapping("/tny-tree-items/count")
    @PreAuthorize("hasPermission(#criteria, 'TnyTreeItem_READ')")
    public ResponseEntity<Long> countTreeItems(TreeItemCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to count TestEntities by criteria: {}", criteria);
        }
        return ResponseEntity.ok().body(treeItemQueryService.countByCriteria(criteria));
    }

    /**
     * {@code PUT  /tny-tree-items } : Updates an existing TreeItem.
     *
     * @param treeItemDTO the TreeItem to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated TreeItem,
     * or with status {@code 400 (Bad Request)} if the TreeItem is not valid,
     * or with status {@code 500 (Internal Server Error)} if the TreeItem couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("更新tree测试item")
    @PutMapping("/tny-tree-items")
    @PreAuthorize("hasPermission(#templateDTO, 'TnyTreeItem_UPDATE')")
    public ResponseEntity<TreeItemDTO> updateTreeItem(@Valid @RequestBody TreeItemDTO treeItemDTO) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to update TreeItem: {}", treeItemDTO);
        }
        if (treeItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TreeItemDTO result = treeItemService.update(treeItemDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * {@code DELETE  /tny-tree-items/:id} : delete the "ids" TreeItem.
     *
     * @param ids the ids of the TreeItems to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("删除tree测试item")
    @DeleteMapping("/tny-tree-items/{ids}")
    @PreAuthorize("hasPermission(#ids, '', 'TnyTreeItem_DELETE')")
    public ResponseEntity<Void> deleteTreeItem(@PathVariable List<UUID> ids) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to delete TreeItem : {}", ids);
        }
        treeItemService.deleteByIds(ids);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, ids.toString())).build();
    }
}
