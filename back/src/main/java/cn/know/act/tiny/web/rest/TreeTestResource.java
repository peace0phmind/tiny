package cn.know.act.tiny.web.rest;

import cn.know.act.proton.core.syslog.SysLog;
import cn.know.act.proton.core.web.util.HeaderUtil;
import cn.know.act.proton.core.web.util.ResponseUtil;
import cn.know.act.proton.core.web.rest.errors.BadRequestAlertException;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.tiny.service.criteria.TreeTestCriteria;
import cn.know.act.tiny.service.criteria.TreeTestQueryService;
import cn.know.act.tiny.service.dto.TreeTestDTO;
import cn.know.act.tiny.service.inf.TreeTestService;
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
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing {@link cn.know.act.tiny.domain.TreeTest }.
 */
@RequestMapping("/api")
@RestController("TnyTreeTestResource")
public class TreeTestResource {

    @Generated(IRW.CODE_GENERATOR)
    private static final String ENTITY_NAME = "TreeTest";

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(TreeTestResource.class);

    @Generated(IRW.CODE_GENERATOR)
    private final TreeTestService treeTestService;

    @Generated(IRW.CODE_GENERATOR)
    private final TreeTestQueryService treeTestQueryService;

    @Generated(IRW.CODE_GENERATOR)
    @Value("${proton.clientApp.name}")
    private String applicationName;

    @Generated(IRW.CODE_GENERATOR)
    public TreeTestResource(TreeTestService treeTestService, TreeTestQueryService treeTestQueryService) {
        this.treeTestService = treeTestService;
        this.treeTestQueryService = treeTestQueryService;
    }

    /**
     * {@code POST  /tny-tree-tests : Create a new TreeTest.
     *
     * @param treeTestDTO the TreeTest to add.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new TreeTest, or with status {@code 400 (Bad Request)} if the TreeTest has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("创建树测试")
    @PostMapping("/tny-tree-tests")
    @PreAuthorize("hasPermission(#templateDTO, 'TnyTreeTest_CREATE')")
    public ResponseEntity<TreeTestDTO> createTreeTest(@Valid @RequestBody TreeTestDTO treeTestDTO) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to create TreeTest: {}", treeTestDTO);
        }
        if (treeTestDTO.getId() != null) {
            throw new BadRequestAlertException("A new TreeTest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TreeTestDTO result = treeTestService.create(treeTestDTO);
        return ResponseEntity.created(new URI("/api/tny-tree-tests/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * {@code GET  /tny-tree-tests/:id} : get the "id" TreeTest.
     *
     * @param id the id of the TreeTest to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the TreeTest, or with status {@code 404 (Not Found)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("获取单个树测试")
    @GetMapping("/tny-tree-tests/{id}")
    @PreAuthorize("hasPermission(#id, '', 'TnyTreeTest_READ')")
    public ResponseEntity<TreeTestDTO> getTreeTest(@PathVariable Long id) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ${__domainSimpleClassName__} : {}", id);
        }
        Optional<TreeTestDTO> treeTestDTO = treeTestService.findOne(id);
        return ResponseUtil.wrapOrNotFound(treeTestDTO);
    }

    /**
     * {@code GET  /tny-tree-tests} : get all the TreeTests.
     *
     * @param criteria the criteria which the requested entities should match.
     * @param pageable the page object
     * @param _d       return detail dto
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of TreeTests in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询树测试列表")
    @GetMapping("/tny-tree-tests")
    @PreAuthorize("hasPermission(#criteria, 'TnyTreeTest_READ')")
    public ResponseEntity<Page<TreeTestDTO>> findTreeTests(TreeTestCriteria criteria, Pageable pageable, Boolean _d) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get TreeTests");
        }
        if (_d == null) {
            _d = true;
        }
        return ResponseEntity.ok().body(treeTestQueryService.findByCriteria(criteria, pageable, _d));
    }

    /**
     * {@code GET  /tny-tree-tests/count} : count all the TreeTests.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询树测试数量")
    @GetMapping("/tny-tree-tests/count")
    @PreAuthorize("hasPermission(#criteria, 'TnyTreeTest_READ')")
    public ResponseEntity<Long> countTreeTests(TreeTestCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to count TestEntities by criteria: {}", criteria);
        }
        return ResponseEntity.ok().body(treeTestQueryService.countByCriteria(criteria));
    }

    /**
     * {@code PUT  /tny-tree-tests } : Updates an existing TreeTest.
     *
     * @param treeTestDTO the TreeTest to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated TreeTest,
     * or with status {@code 400 (Bad Request)} if the TreeTest is not valid,
     * or with status {@code 500 (Internal Server Error)} if the TreeTest couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("更新树测试")
    @PutMapping("/tny-tree-tests")
    @PreAuthorize("hasPermission(#templateDTO, 'TnyTreeTest_UPDATE')")
    public ResponseEntity<TreeTestDTO> updateTreeTest(@Valid @RequestBody TreeTestDTO treeTestDTO) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to update TreeTest: {}", treeTestDTO);
        }
        if (treeTestDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TreeTestDTO result = treeTestService.update(treeTestDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * {@code DELETE  /tny-tree-tests/:id} : delete the "ids" TreeTest.
     *
     * @param ids the ids of the TreeTests to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("删除树测试")
    @DeleteMapping("/tny-tree-tests/{ids}")
    @PreAuthorize("hasPermission(#ids, '', 'TnyTreeTest_DELETE')")
    public ResponseEntity<Void> deleteTreeTest(@PathVariable List<Long> ids) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to delete TreeTest : {}", ids);
        }
        treeTestService.deleteByIds(ids);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, ids.toString())).build();
    }

    /**
     * {@code POST  /tny-tree-tests : Create a new TreeTest before the sibling.
     *
     * @param treeTestDTO the TreeTest to add.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new TreeTest, or with status {@code 400 (Bad Request)} if the TreeTest has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("节点前创建树测试")
    @PostMapping("/tny-tree-tests/tree/before/{siblingId}")
    @PreAuthorize("hasPermission(#templateDTO, 'TnyTreeTest_CREATE')")
    public ResponseEntity<TreeTestDTO> addTreeTestBefore(@Valid @RequestBody TreeTestDTO treeTestDTO, @PathVariable Long siblingId) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to add TreeTest: {}, before: {}", treeTestDTO, siblingId);
        }
        if (treeTestDTO.getId() != null) {
            throw new BadRequestAlertException("A new TreeTest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TreeTestDTO result = treeTestService.addBefore(treeTestDTO, siblingId);
        return ResponseEntity.created(new URI("/api/tny-tree-tests/" + result.getId())).body(result);
    }

    /**
     * {@code POST  /tny-tree-tests : Create a new TreeTest after the sibling.
     *
     * @param treeTestDTO the TreeTest to add.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new TreeTest, or with status {@code 400 (Bad Request)} if the TreeTest has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("节点后创建树测试")
    @PostMapping("/tny-tree-tests/tree/after/{siblingId}")
    @PreAuthorize("hasPermission(#templateDTO, 'TnyTreeTest_CREATE')")
    public ResponseEntity<TreeTestDTO> addTreeTestAfter(@Valid @RequestBody TreeTestDTO treeTestDTO, @PathVariable Long siblingId) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to add TreeTest: {}, after: {}", treeTestDTO, siblingId);
        }
        if (treeTestDTO.getId() != null) {
            throw new BadRequestAlertException("A new TreeTest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TreeTestDTO result = treeTestService.addAfter(treeTestDTO, siblingId);
        return ResponseEntity.created(new URI("/api/tny-tree-tests/" + result.getId())).body(result);
    }

    /**
     * {@code PUT  /tny-tree-tests/tree/:nodeId/under/:parentId : move the "nodeId" TreeTest under the "parentId" node .
     *
     * @param nodeId   the id of the TreeTest to be moved.
     * @param parentId the id of the TreeTest is parent.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("移动树测试到节点下")
    @PutMapping("/tny-tree-tests/tree/{nodeId}/under/{parentId}")
    @PreAuthorize("hasPermission(#nodeId, '', 'TnyTreeTest_MOVE')")
    public ResponseEntity<Void> moveTreeTestUnder(@PathVariable Long nodeId, @PathVariable Long parentId) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to move TreeTest : {}, under : {}", nodeId, parentId);
        }
        treeTestService.moveUnder(nodeId, parentId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@code PUT  /tny-tree-tests/tree/:nodeId/before/:siblingId : move the "nodeId" TreeTest before the "siblingId" node .
     *
     * @param nodeId    the id of the TreeTest to be moved.
     * @param siblingId the id of the TreeTest is the node's sibling.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("移动树测试到节点前")
    @PutMapping("/tny-tree-tests/tree/{nodeId}/before/{siblingId}")
    @PreAuthorize("hasPermission(#nodeId, '', 'TnyTreeTest_MOVE')")
    public ResponseEntity<Void> moveTreeTestBefore(@PathVariable Long nodeId, @PathVariable Long siblingId) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to move TreeTest : {}, before : {}", nodeId, siblingId);
        }
        treeTestService.moveBefore(nodeId, siblingId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@code PUT  /tny-tree-tests/tree/:nodeId/after/:siblingId : move the "nodeId" TreeTest after the "siblingId" node .
     *
     * @param nodeId    the id of the TreeTest to be moved.
     * @param siblingId the id of the TreeTest is the node's sibling.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("移动树测试到节点后")
    @PutMapping("/tny-tree-tests/tree/{nodeId}/after/{siblingId}")
    @PreAuthorize("hasPermission(#nodeId, '', 'TnyTreeTest_MOVE')")
    public ResponseEntity<Void> moveTreeTestAfter(@PathVariable Long nodeId, @PathVariable Long siblingId) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to move TreeTest : {}, after : {}", nodeId, siblingId);
        }
        treeTestService.moveAfter(nodeId, siblingId);
        return ResponseEntity.noContent().build();
    }

    /**
     * get tree roots
     *
     * @param level
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("获取树测试树")
    @GetMapping("/tny-tree-tests/tree")
    @PreAuthorize("hasPermission(null, '', 'TnyTreeTest_READ')")
    public ResponseEntity<List<TreeTestDTO>> getTreeTestTrees(Integer level, Boolean includeNode) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ${__domainSimpleClassName__} tree roots: level = {}", level);
        }
        return ResponseEntity.ok().body(treeTestQueryService.getTree(null, level, includeNode));
    }

    /**
     * get node children
     *
     * @param id
     * @param level
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("获取某个树测试树")
    @GetMapping("/tny-tree-tests/{id}/tree")
    @PreAuthorize("hasPermission(#id, '', 'TnyTreeTest_READ')")
    public ResponseEntity<List<TreeTestDTO>> getTreeTestTree(@PathVariable Long id, Integer level, Boolean includeNode) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ${__domainSimpleClassName__} tree: {}, {}", id, level);
        }
        return ResponseEntity.ok().body(treeTestQueryService.getTree(id, level, includeNode));
    }

    /**
     * find node parents, return parent to node
     *
     * @param nodeId
     * @param level
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("获取树测试父节点树")
    @GetMapping("/tny-tree-tests/{nodeId}/_parent")
    @PreAuthorize("hasPermission(#nodeId, '', 'TnyTreeTest_READ')")
    public ResponseEntity<TreeTestDTO> findTreeTestTreeParents(@PathVariable Long nodeId, Integer level) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ${__domainSimpleClassName__}'s parents : {}, level: {}", nodeId, level);
        }
        Optional<TreeTestDTO> treeTestDTO = treeTestQueryService.findParents(nodeId, level);
        return ResponseUtil.wrapOrNotFound(treeTestDTO);
    }

    /**
     * find tree node by condition
     *
     * @param criteria the criteria which the requested entities should match.
     * @param pageable the page object
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("查询返回树测试树")
    @GetMapping("/tny-tree-tests/tree/search")
    @PreAuthorize("hasPermission(#criteria, 'TnyTreeTest_READ')")
    public ResponseEntity<List<TreeTestDTO>> findTreeTestTrees(TreeTestCriteria criteria, Pageable pageable) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ${__domainSimpleClassName__} tree: criteria = {}, pageable = {}", criteria, pageable);
        }
        return ResponseEntity.ok().body(treeTestQueryService.findTree(criteria, pageable));
    }
}
