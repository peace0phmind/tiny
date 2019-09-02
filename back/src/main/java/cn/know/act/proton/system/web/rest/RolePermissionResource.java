package cn.know.act.proton.system.web.rest;

import cn.know.act.proton.core.syslog.SysLog;
import cn.know.act.proton.core.web.util.HeaderUtil;
import cn.know.act.proton.core.web.util.ResponseUtil;
import cn.know.act.proton.core.web.rest.errors.BadRequestAlertException;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.service.criteria.RolePermissionCriteria;
import cn.know.act.proton.system.service.criteria.RolePermissionQueryService;
import cn.know.act.proton.system.service.dto.RolePermissionDTO;
import cn.know.act.proton.system.service.inf.RolePermissionService;
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
 * REST controller for managing {@link cn.know.act.proton.system.domain.RolePermission }.
 */
@RequestMapping("/api")
@RestController("SysRolePermissionResource")
public class RolePermissionResource {

    @Generated(IRW.CODE_GENERATOR)
    private static final String ENTITY_NAME = "RolePermission";

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(RolePermissionResource.class);

    @Generated(IRW.CODE_GENERATOR)
    private final RolePermissionService rolePermissionService;

    @Generated(IRW.CODE_GENERATOR)
    private final RolePermissionQueryService rolePermissionQueryService;

    @Generated(IRW.CODE_GENERATOR)
    @Value("${proton.clientApp.name}")
    private String applicationName;

    @Generated(IRW.CODE_GENERATOR)
    public RolePermissionResource(RolePermissionService rolePermissionService, RolePermissionQueryService rolePermissionQueryService) {
        this.rolePermissionService = rolePermissionService;
        this.rolePermissionQueryService = rolePermissionQueryService;
    }

    /**
     * {@code POST  /sys-role-permissions : Create a new RolePermission.
     *
     * @param rolePermissionDTO the RolePermission to add.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new RolePermission, or with status {@code 400 (Bad Request)} if the RolePermission has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("创建角色权限")
    @PostMapping("/sys-role-permissions")
    @PreAuthorize("hasPermission(#templateDTO, 'SysRolePermission_CREATE')")
    public ResponseEntity<RolePermissionDTO> createRolePermission(@Valid @RequestBody RolePermissionDTO rolePermissionDTO) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to create RolePermission: {}", rolePermissionDTO);
        }
        if (rolePermissionDTO.getId() != null) {
            throw new BadRequestAlertException("A new RolePermission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RolePermissionDTO result = rolePermissionService.create(rolePermissionDTO);
        return ResponseEntity.created(new URI("/api/sys-role-permissions/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * {@code GET  /sys-role-permissions/:id} : get the "id" RolePermission.
     *
     * @param id the id of the RolePermission to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the RolePermission, or with status {@code 404 (Not Found)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("获取单个角色权限")
    @GetMapping("/sys-role-permissions/{id}")
    @PreAuthorize("hasPermission(#id, '', 'SysRolePermission_READ')")
    public ResponseEntity<RolePermissionDTO> getRolePermission(@PathVariable UUID id) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ${__domainSimpleClassName__} : {}", id);
        }
        Optional<RolePermissionDTO> rolePermissionDTO = rolePermissionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rolePermissionDTO);
    }

    /**
     * {@code GET  /sys-role-permissions} : get all the RolePermissions.
     *
     * @param criteria the criteria which the requested entities should match.
     * @param pageable the page object
     * @param _d       return detail dto
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of RolePermissions in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询角色权限列表")
    @GetMapping("/sys-role-permissions")
    @PreAuthorize("hasPermission(#criteria, 'SysRolePermission_READ')")
    public ResponseEntity<Page<RolePermissionDTO>> findRolePermissions(RolePermissionCriteria criteria, Pageable pageable, Boolean _d) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get RolePermissions");
        }
        if (_d == null) {
            _d = true;
        }
        return ResponseEntity.ok().body(rolePermissionQueryService.findByCriteria(criteria, pageable, _d));
    }

    /**
     * {@code GET  /sys-role-permissions/count} : count all the RolePermissions.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询角色权限数量")
    @GetMapping("/sys-role-permissions/count")
    @PreAuthorize("hasPermission(#criteria, 'SysRolePermission_READ')")
    public ResponseEntity<Long> countRolePermissions(RolePermissionCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to count TestEntities by criteria: {}", criteria);
        }
        return ResponseEntity.ok().body(rolePermissionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code PUT  /sys-role-permissions } : Updates an existing RolePermission.
     *
     * @param rolePermissionDTO the RolePermission to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated RolePermission,
     * or with status {@code 400 (Bad Request)} if the RolePermission is not valid,
     * or with status {@code 500 (Internal Server Error)} if the RolePermission couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("更新角色权限")
    @PutMapping("/sys-role-permissions")
    @PreAuthorize("hasPermission(#templateDTO, 'SysRolePermission_UPDATE')")
    public ResponseEntity<RolePermissionDTO> updateRolePermission(@Valid @RequestBody RolePermissionDTO rolePermissionDTO) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to update RolePermission: {}", rolePermissionDTO);
        }
        if (rolePermissionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RolePermissionDTO result = rolePermissionService.update(rolePermissionDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * {@code DELETE  /sys-role-permissions/:id} : delete the "ids" RolePermission.
     *
     * @param ids the ids of the RolePermissions to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("删除角色权限")
    @DeleteMapping("/sys-role-permissions/{ids}")
    @PreAuthorize("hasPermission(#ids, '', 'SysRolePermission_DELETE')")
    public ResponseEntity<Void> deleteRolePermission(@PathVariable List<UUID> ids) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to delete RolePermission : {}", ids);
        }
        rolePermissionService.deleteByIds(ids);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, ids.toString())).build();
    }
}
