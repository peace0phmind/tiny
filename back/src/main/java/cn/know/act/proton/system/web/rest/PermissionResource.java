package cn.know.act.proton.system.web.rest;

import cn.know.act.proton.core.syslog.SysLog;
import cn.know.act.proton.core.web.util.HeaderUtil;
import cn.know.act.proton.core.web.util.ResponseUtil;
import cn.know.act.proton.core.web.rest.errors.BadRequestAlertException;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.service.criteria.PermissionCriteria;
import cn.know.act.proton.system.service.criteria.PermissionQueryService;
import cn.know.act.proton.system.service.dto.PermissionDTO;
import cn.know.act.proton.system.service.inf.PermissionService;
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
 * REST controller for managing {@link cn.know.act.proton.system.domain.Permission }.
 */
@RequestMapping("/api")
@RestController("SysPermissionResource")
public class PermissionResource {

    @Generated(IRW.CODE_GENERATOR)
    private static final String ENTITY_NAME = "Permission";

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(PermissionResource.class);

    @Generated(IRW.CODE_GENERATOR)
    private final PermissionService permissionService;

    @Generated(IRW.CODE_GENERATOR)
    private final PermissionQueryService permissionQueryService;

    @Generated(IRW.CODE_GENERATOR)
    @Value("${proton.clientApp.name}")
    private String applicationName;

    @Generated(IRW.CODE_GENERATOR)
    public PermissionResource(PermissionService permissionService, PermissionQueryService permissionQueryService) {
        this.permissionService = permissionService;
        this.permissionQueryService = permissionQueryService;
    }

    /**
     * {@code POST  /sys-permissions : Create a new Permission.
     *
     * @param permissionDTO the Permission to add.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new Permission, or with status {@code 400 (Bad Request)} if the Permission has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("创建权限")
    @PostMapping("/sys-permissions")
    @PreAuthorize("hasPermission(#templateDTO, 'SysPermission_CREATE')")
    public ResponseEntity<PermissionDTO> createPermission(@Valid @RequestBody PermissionDTO permissionDTO) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to create Permission: {}", permissionDTO);
        }
        if (permissionDTO.getId() != null) {
            throw new BadRequestAlertException("A new Permission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PermissionDTO result = permissionService.create(permissionDTO);
        return ResponseEntity.created(new URI("/api/sys-permissions/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * {@code GET  /sys-permissions/:id} : get the "id" Permission.
     *
     * @param id the id of the Permission to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the Permission, or with status {@code 404 (Not Found)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("获取单个权限")
    @GetMapping("/sys-permissions/{id}")
    @PreAuthorize("hasPermission(#id, '', 'SysPermission_READ')")
    public ResponseEntity<PermissionDTO> getPermission(@PathVariable UUID id) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ${__domainSimpleClassName__} : {}", id);
        }
        Optional<PermissionDTO> permissionDTO = permissionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(permissionDTO);
    }

    /**
     * {@code GET  /sys-permissions} : get all the Permissions.
     *
     * @param criteria the criteria which the requested entities should match.
     * @param pageable the page object
     * @param _d       return detail dto
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of Permissions in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询权限列表")
    @GetMapping("/sys-permissions")
    @PreAuthorize("hasPermission(#criteria, 'SysPermission_READ')")
    public ResponseEntity<Page<PermissionDTO>> findPermissions(PermissionCriteria criteria, Pageable pageable, Boolean _d) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get Permissions");
        }
        if (_d == null) {
            _d = true;
        }
        return ResponseEntity.ok().body(permissionQueryService.findByCriteria(criteria, pageable, _d));
    }

    /**
     * {@code GET  /sys-permissions/count} : count all the Permissions.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询权限数量")
    @GetMapping("/sys-permissions/count")
    @PreAuthorize("hasPermission(#criteria, 'SysPermission_READ')")
    public ResponseEntity<Long> countPermissions(PermissionCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to count TestEntities by criteria: {}", criteria);
        }
        return ResponseEntity.ok().body(permissionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code PUT  /sys-permissions } : Updates an existing Permission.
     *
     * @param permissionDTO the Permission to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated Permission,
     * or with status {@code 400 (Bad Request)} if the Permission is not valid,
     * or with status {@code 500 (Internal Server Error)} if the Permission couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("更新权限")
    @PutMapping("/sys-permissions")
    @PreAuthorize("hasPermission(#templateDTO, 'SysPermission_UPDATE')")
    public ResponseEntity<PermissionDTO> updatePermission(@Valid @RequestBody PermissionDTO permissionDTO) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to update Permission: {}", permissionDTO);
        }
        if (permissionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PermissionDTO result = permissionService.update(permissionDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * {@code DELETE  /sys-permissions/:id} : delete the "ids" Permission.
     *
     * @param ids the ids of the Permissions to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("删除权限")
    @DeleteMapping("/sys-permissions/{ids}")
    @PreAuthorize("hasPermission(#ids, '', 'SysPermission_DELETE')")
    public ResponseEntity<Void> deletePermission(@PathVariable List<UUID> ids) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to delete Permission : {}", ids);
        }
        permissionService.deleteByIds(ids);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, ids.toString())).build();
    }
}
