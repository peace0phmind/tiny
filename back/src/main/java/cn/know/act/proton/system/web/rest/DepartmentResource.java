package cn.know.act.proton.system.web.rest;

import cn.know.act.proton.core.syslog.SysLog;
import cn.know.act.proton.core.web.util.HeaderUtil;
import cn.know.act.proton.core.web.util.ResponseUtil;
import cn.know.act.proton.core.web.rest.errors.BadRequestAlertException;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.service.criteria.DepartmentCriteria;
import cn.know.act.proton.system.service.criteria.DepartmentQueryService;
import cn.know.act.proton.system.service.dto.DepartmentDTO;
import cn.know.act.proton.system.service.inf.DepartmentService;
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
 * REST controller for managing {@link cn.know.act.proton.system.domain.Department }.
 */
@RequestMapping("/api")
@RestController("SysDepartmentResource")
public class DepartmentResource {

    @Generated(IRW.CODE_GENERATOR)
    private static final String ENTITY_NAME = "Department";

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(DepartmentResource.class);

    @Generated(IRW.CODE_GENERATOR)
    private final DepartmentService departmentService;

    @Generated(IRW.CODE_GENERATOR)
    private final DepartmentQueryService departmentQueryService;

    @Generated(IRW.CODE_GENERATOR)
    @Value("${proton.clientApp.name}")
    private String applicationName;

    @Generated(IRW.CODE_GENERATOR)
    public DepartmentResource(DepartmentService departmentService, DepartmentQueryService departmentQueryService) {
        this.departmentService = departmentService;
        this.departmentQueryService = departmentQueryService;
    }

    /**
     * {@code POST  /sys-departments : Create a new Department.
     *
     * @param departmentDTO the Department to add.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new Department, or with status {@code 400 (Bad Request)} if the Department has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("创建部门")
    @PostMapping("/sys-departments")
    @PreAuthorize("hasPermission(#templateDTO, 'SysDepartment_CREATE')")
    public ResponseEntity<DepartmentDTO> createDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to create Department: {}", departmentDTO);
        }
        if (departmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new Department cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DepartmentDTO result = departmentService.create(departmentDTO);
        return ResponseEntity.created(new URI("/api/sys-departments/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * {@code GET  /sys-departments/:id} : get the "id" Department.
     *
     * @param id the id of the Department to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the Department, or with status {@code 404 (Not Found)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("获取单个部门")
    @GetMapping("/sys-departments/{id}")
    @PreAuthorize("hasPermission(#id, '', 'SysDepartment_READ')")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable UUID id) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ${__domainSimpleClassName__} : {}", id);
        }
        Optional<DepartmentDTO> departmentDTO = departmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(departmentDTO);
    }

    /**
     * {@code GET  /sys-departments} : get all the Departments.
     *
     * @param criteria the criteria which the requested entities should match.
     * @param pageable the page object
     * @param _d       return detail dto
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of Departments in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询部门列表")
    @GetMapping("/sys-departments")
    @PreAuthorize("hasPermission(#criteria, 'SysDepartment_READ')")
    public ResponseEntity<Page<DepartmentDTO>> findDepartments(DepartmentCriteria criteria, Pageable pageable, Boolean _d) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get Departments");
        }
        if (_d == null) {
            _d = true;
        }
        return ResponseEntity.ok().body(departmentQueryService.findByCriteria(criteria, pageable, _d));
    }

    /**
     * {@code GET  /sys-departments/count} : count all the Departments.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询部门数量")
    @GetMapping("/sys-departments/count")
    @PreAuthorize("hasPermission(#criteria, 'SysDepartment_READ')")
    public ResponseEntity<Long> countDepartments(DepartmentCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to count TestEntities by criteria: {}", criteria);
        }
        return ResponseEntity.ok().body(departmentQueryService.countByCriteria(criteria));
    }

    /**
     * {@code PUT  /sys-departments } : Updates an existing Department.
     *
     * @param departmentDTO the Department to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated Department,
     * or with status {@code 400 (Bad Request)} if the Department is not valid,
     * or with status {@code 500 (Internal Server Error)} if the Department couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("更新部门")
    @PutMapping("/sys-departments")
    @PreAuthorize("hasPermission(#templateDTO, 'SysDepartment_UPDATE')")
    public ResponseEntity<DepartmentDTO> updateDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to update Department: {}", departmentDTO);
        }
        if (departmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DepartmentDTO result = departmentService.update(departmentDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * {@code DELETE  /sys-departments/:id} : delete the "ids" Department.
     *
     * @param ids the ids of the Departments to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("删除部门")
    @DeleteMapping("/sys-departments/{ids}")
    @PreAuthorize("hasPermission(#ids, '', 'SysDepartment_DELETE')")
    public ResponseEntity<Void> deleteDepartment(@PathVariable List<UUID> ids) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to delete Department : {}", ids);
        }
        departmentService.deleteByIds(ids);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, ids.toString())).build();
    }

    /**
     * {@code POST  /sys-departments : Create a new Department before the sibling.
     *
     * @param departmentDTO the Department to add.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new Department, or with status {@code 400 (Bad Request)} if the Department has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("节点前创建部门")
    @PostMapping("/sys-departments/tree/before/{siblingId}")
    @PreAuthorize("hasPermission(#templateDTO, 'SysDepartment_CREATE')")
    public ResponseEntity<DepartmentDTO> addDepartmentBefore(@Valid @RequestBody DepartmentDTO departmentDTO, @PathVariable UUID siblingId) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to add Department: {}, before: {}", departmentDTO, siblingId);
        }
        if (departmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new Department cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DepartmentDTO result = departmentService.addBefore(departmentDTO, siblingId);
        return ResponseEntity.created(new URI("/api/sys-departments/" + result.getId())).body(result);
    }

    /**
     * {@code POST  /sys-departments : Create a new Department after the sibling.
     *
     * @param departmentDTO the Department to add.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new Department, or with status {@code 400 (Bad Request)} if the Department has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("节点后创建部门")
    @PostMapping("/sys-departments/tree/after/{siblingId}")
    @PreAuthorize("hasPermission(#templateDTO, 'SysDepartment_CREATE')")
    public ResponseEntity<DepartmentDTO> addDepartmentAfter(@Valid @RequestBody DepartmentDTO departmentDTO, @PathVariable UUID siblingId) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to add Department: {}, after: {}", departmentDTO, siblingId);
        }
        if (departmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new Department cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DepartmentDTO result = departmentService.addAfter(departmentDTO, siblingId);
        return ResponseEntity.created(new URI("/api/sys-departments/" + result.getId())).body(result);
    }

    /**
     * {@code PUT  /sys-departments/tree/:nodeId/under/:parentId : move the "nodeId" Department under the "parentId" node .
     *
     * @param nodeId   the id of the Department to be moved.
     * @param parentId the id of the Department is parent.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("移动部门到节点下")
    @PutMapping("/sys-departments/tree/{nodeId}/under/{parentId}")
    @PreAuthorize("hasPermission(#nodeId, '', 'SysDepartment_MOVE')")
    public ResponseEntity<Void> moveDepartmentUnder(@PathVariable UUID nodeId, @PathVariable UUID parentId) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to move Department : {}, under : {}", nodeId, parentId);
        }
        departmentService.moveUnder(nodeId, parentId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@code PUT  /sys-departments/tree/:nodeId/before/:siblingId : move the "nodeId" Department before the "siblingId" node .
     *
     * @param nodeId    the id of the Department to be moved.
     * @param siblingId the id of the Department is the node's sibling.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("移动部门到节点前")
    @PutMapping("/sys-departments/tree/{nodeId}/before/{siblingId}")
    @PreAuthorize("hasPermission(#nodeId, '', 'SysDepartment_MOVE')")
    public ResponseEntity<Void> moveDepartmentBefore(@PathVariable UUID nodeId, @PathVariable UUID siblingId) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to move Department : {}, before : {}", nodeId, siblingId);
        }
        departmentService.moveBefore(nodeId, siblingId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@code PUT  /sys-departments/tree/:nodeId/after/:siblingId : move the "nodeId" Department after the "siblingId" node .
     *
     * @param nodeId    the id of the Department to be moved.
     * @param siblingId the id of the Department is the node's sibling.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("移动部门到节点后")
    @PutMapping("/sys-departments/tree/{nodeId}/after/{siblingId}")
    @PreAuthorize("hasPermission(#nodeId, '', 'SysDepartment_MOVE')")
    public ResponseEntity<Void> moveDepartmentAfter(@PathVariable UUID nodeId, @PathVariable UUID siblingId) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to move Department : {}, after : {}", nodeId, siblingId);
        }
        departmentService.moveAfter(nodeId, siblingId);
        return ResponseEntity.noContent().build();
    }

    /**
     * get tree roots
     *
     * @param level
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("获取部门树")
    @GetMapping("/sys-departments/tree")
    @PreAuthorize("hasPermission(null, '', 'SysDepartment_READ')")
    public ResponseEntity<List<DepartmentDTO>> getDepartmentTrees(Integer level, Boolean includeNode) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ${__domainSimpleClassName__} tree roots: level = {}", level);
        }
        return ResponseEntity.ok().body(departmentQueryService.getTree(null, level, includeNode));
    }

    /**
     * get node children
     *
     * @param id
     * @param level
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("获取某个部门树")
    @GetMapping("/sys-departments/{id}/tree")
    @PreAuthorize("hasPermission(#id, '', 'SysDepartment_READ')")
    public ResponseEntity<List<DepartmentDTO>> getDepartmentTree(@PathVariable UUID id, Integer level, Boolean includeNode) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ${__domainSimpleClassName__} tree: {}, {}", id, level);
        }
        return ResponseEntity.ok().body(departmentQueryService.getTree(id, level, includeNode));
    }

    /**
     * find node parents, return parent to node
     *
     * @param nodeId
     * @param level
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("获取部门父节点树")
    @GetMapping("/sys-departments/{nodeId}/_parent")
    @PreAuthorize("hasPermission(#nodeId, '', 'SysDepartment_READ')")
    public ResponseEntity<DepartmentDTO> findDepartmentTreeParents(@PathVariable UUID nodeId, Integer level) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ${__domainSimpleClassName__}'s parents : {}, level: {}", nodeId, level);
        }
        Optional<DepartmentDTO> departmentDTO = departmentQueryService.findParents(nodeId, level);
        return ResponseUtil.wrapOrNotFound(departmentDTO);
    }

    /**
     * find tree node by condition
     *
     * @param criteria the criteria which the requested entities should match.
     * @param pageable the page object
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("查询返回部门树")
    @GetMapping("/sys-departments/tree/search")
    @PreAuthorize("hasPermission(#criteria, 'SysDepartment_READ')")
    public ResponseEntity<List<DepartmentDTO>> findDepartmentTrees(DepartmentCriteria criteria, Pageable pageable) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ${__domainSimpleClassName__} tree: criteria = {}, pageable = {}", criteria, pageable);
        }
        return ResponseEntity.ok().body(departmentQueryService.findTree(criteria, pageable));
    }
}
