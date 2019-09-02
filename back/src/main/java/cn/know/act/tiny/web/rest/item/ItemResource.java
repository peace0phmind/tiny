package cn.know.act.tiny.web.rest.item;

import cn.know.act.proton.core.syslog.SysLog;
import cn.know.act.proton.core.web.util.HeaderUtil;
import cn.know.act.proton.core.web.util.ResponseUtil;
import cn.know.act.proton.core.web.rest.errors.BadRequestAlertException;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.tiny.service.criteria.item.ItemCriteria;
import cn.know.act.tiny.service.criteria.item.ItemQueryService;
import cn.know.act.tiny.service.dto.item.ItemDTO;
import cn.know.act.tiny.service.inf.item.ItemService;
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
 * REST controller for managing {@link cn.know.act.tiny.domain.item.Item }.
 */
@RequestMapping("/api")
@RestController("TnyItemResource")
public class ItemResource {

    @Generated(IRW.CODE_GENERATOR)
    private static final String ENTITY_NAME = "Item";

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(ItemResource.class);

    @Generated(IRW.CODE_GENERATOR)
    private final ItemService itemService;

    @Generated(IRW.CODE_GENERATOR)
    private final ItemQueryService itemQueryService;

    @Generated(IRW.CODE_GENERATOR)
    @Value("${proton.clientApp.name}")
    private String applicationName;

    @Generated(IRW.CODE_GENERATOR)
    public ItemResource(ItemService itemService, ItemQueryService itemQueryService) {
        this.itemService = itemService;
        this.itemQueryService = itemQueryService;
    }

    /**
     * {@code POST  /tny-items : Create a new Item.
     *
     * @param itemDTO the Item to add.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new Item, or with status {@code 400 (Bad Request)} if the Item has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("创建测试item")
    @PostMapping("/tny-items")
    @PreAuthorize("hasPermission(#templateDTO, 'TnyItem_CREATE')")
    public ResponseEntity<ItemDTO> createItem(@Valid @RequestBody ItemDTO itemDTO) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to create Item: {}", itemDTO);
        }
        if (itemDTO.getId() != null) {
            throw new BadRequestAlertException("A new Item cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemDTO result = itemService.create(itemDTO);
        return ResponseEntity.created(new URI("/api/tny-items/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * {@code GET  /tny-items/:id} : get the "id" Item.
     *
     * @param id the id of the Item to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the Item, or with status {@code 404 (Not Found)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("获取单个测试item")
    @GetMapping("/tny-items/{id}")
    @PreAuthorize("hasPermission(#id, '', 'TnyItem_READ')")
    public ResponseEntity<ItemDTO> getItem(@PathVariable UUID id) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ${__domainSimpleClassName__} : {}", id);
        }
        Optional<ItemDTO> itemDTO = itemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemDTO);
    }

    /**
     * {@code GET  /tny-items} : get all the Items.
     *
     * @param criteria the criteria which the requested entities should match.
     * @param pageable the page object
     * @param _d       return detail dto
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of Items in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询测试item列表")
    @GetMapping("/tny-items")
    @PreAuthorize("hasPermission(#criteria, 'TnyItem_READ')")
    public ResponseEntity<Page<ItemDTO>> findItems(ItemCriteria criteria, Pageable pageable, Boolean _d) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get Items");
        }
        if (_d == null) {
            _d = true;
        }
        return ResponseEntity.ok().body(itemQueryService.findByCriteria(criteria, pageable, _d));
    }

    /**
     * {@code GET  /tny-items/count} : count all the Items.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询测试item数量")
    @GetMapping("/tny-items/count")
    @PreAuthorize("hasPermission(#criteria, 'TnyItem_READ')")
    public ResponseEntity<Long> countItems(ItemCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to count TestEntities by criteria: {}", criteria);
        }
        return ResponseEntity.ok().body(itemQueryService.countByCriteria(criteria));
    }

    /**
     * {@code PUT  /tny-items } : Updates an existing Item.
     *
     * @param itemDTO the Item to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated Item,
     * or with status {@code 400 (Bad Request)} if the Item is not valid,
     * or with status {@code 500 (Internal Server Error)} if the Item couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("更新测试item")
    @PutMapping("/tny-items")
    @PreAuthorize("hasPermission(#templateDTO, 'TnyItem_UPDATE')")
    public ResponseEntity<ItemDTO> updateItem(@Valid @RequestBody ItemDTO itemDTO) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to update Item: {}", itemDTO);
        }
        if (itemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ItemDTO result = itemService.update(itemDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * {@code DELETE  /tny-items/:id} : delete the "ids" Item.
     *
     * @param ids the ids of the Items to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("删除测试item")
    @DeleteMapping("/tny-items/{ids}")
    @PreAuthorize("hasPermission(#ids, '', 'TnyItem_DELETE')")
    public ResponseEntity<Void> deleteItem(@PathVariable List<UUID> ids) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to delete Item : {}", ids);
        }
        itemService.deleteByIds(ids);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, ids.toString())).build();
    }
}
