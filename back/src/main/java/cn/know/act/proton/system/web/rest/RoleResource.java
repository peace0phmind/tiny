package cn.know.act.proton.system.web.rest;

import cn.know.act.proton.core.syslog.SysLog;
import cn.know.act.proton.core.web.util.HeaderUtil;
import cn.know.act.proton.core.web.util.ResponseUtil;
import cn.know.act.proton.core.web.rest.errors.BadRequestAlertException;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.service.criteria.RoleCriteria;
import cn.know.act.proton.system.service.criteria.RoleQueryService;
import cn.know.act.proton.system.service.dto.RoleDTO;
import cn.know.act.proton.system.service.inf.RoleService;
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
 * REST controller for managing {@link cn.know.act.proton.system.domain.Role }.
 */
@RequestMapping("/api")
@RestController("SysRoleResource")
public class RoleResource {

    @Generated(IRW.CODE_GENERATOR)
    private static final String ENTITY_NAME = "Role";

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(RoleResource.class);

    @Generated(IRW.CODE_GENERATOR)
    private final RoleService roleService;

    @Generated(IRW.CODE_GENERATOR)
    private final RoleQueryService roleQueryService;

    @Generated(IRW.CODE_GENERATOR)
    @Value("${proton.clientApp.name}")
    private String applicationName;

    @Generated(IRW.CODE_GENERATOR)
    public RoleResource(RoleService roleService, RoleQueryService roleQueryService) {
        this.roleService = roleService;
        this.roleQueryService = roleQueryService;
    }

    /**
     * {@code POST  /sys-roles : Create a new Role.
     *
     * @param roleDTO the Role to add.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new Role, or with status {@code 400 (Bad Request)} if the Role has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("创建角色")
    @PostMapping("/sys-roles")
    @PreAuthorize("hasPermission(#templateDTO, 'SysRole_CREATE')")
    public ResponseEntity<RoleDTO> createRole(@Valid @RequestBody RoleDTO roleDTO) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to create Role: {}", roleDTO);
        }
        if (roleDTO.getId() != null) {
            throw new BadRequestAlertException("A new Role cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RoleDTO result = roleService.create(roleDTO);
        return ResponseEntity.created(new URI("/api/sys-roles/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * {@code GET  /sys-roles/:id} : get the "id" Role.
     *
     * @param id the id of the Role to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the Role, or with status {@code 404 (Not Found)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("获取单个角色")
    @GetMapping("/sys-roles/{id}")
    @PreAuthorize("hasPermission(#id, '', 'SysRole_READ')")
    public ResponseEntity<RoleDTO> getRole(@PathVariable UUID id) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ${__domainSimpleClassName__} : {}", id);
        }
        Optional<RoleDTO> roleDTO = roleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(roleDTO);
    }

    /**
     * {@code GET  /sys-roles} : get all the Roles.
     *
     * @param criteria the criteria which the requested entities should match.
     * @param pageable the page object
     * @param _d       return detail dto
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of Roles in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询角色列表")
    @GetMapping("/sys-roles")
    @PreAuthorize("hasPermission(#criteria, 'SysRole_READ')")
    public ResponseEntity<Page<RoleDTO>> findRoles(RoleCriteria criteria, Pageable pageable, Boolean _d) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get Roles");
        }
        if (_d == null) {
            _d = true;
        }
        return ResponseEntity.ok().body(roleQueryService.findByCriteria(criteria, pageable, _d));
    }

    /**
     * {@code GET  /sys-roles/count} : count all the Roles.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询角色数量")
    @GetMapping("/sys-roles/count")
    @PreAuthorize("hasPermission(#criteria, 'SysRole_READ')")
    public ResponseEntity<Long> countRoles(RoleCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to count TestEntities by criteria: {}", criteria);
        }
        return ResponseEntity.ok().body(roleQueryService.countByCriteria(criteria));
    }

    /**
     * {@code PUT  /sys-roles } : Updates an existing Role.
     *
     * @param roleDTO the Role to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated Role,
     * or with status {@code 400 (Bad Request)} if the Role is not valid,
     * or with status {@code 500 (Internal Server Error)} if the Role couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("更新角色")
    @PutMapping("/sys-roles")
    @PreAuthorize("hasPermission(#templateDTO, 'SysRole_UPDATE')")
    public ResponseEntity<RoleDTO> updateRole(@Valid @RequestBody RoleDTO roleDTO) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to update Role: {}", roleDTO);
        }
        if (roleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RoleDTO result = roleService.update(roleDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * {@code DELETE  /sys-roles/:id} : delete the "ids" Role.
     *
     * @param ids the ids of the Roles to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("删除角色")
    @DeleteMapping("/sys-roles/{ids}")
    @PreAuthorize("hasPermission(#ids, '', 'SysRole_DELETE')")
    public ResponseEntity<Void> deleteRole(@PathVariable List<UUID> ids) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to delete Role : {}", ids);
        }
        roleService.deleteByIds(ids);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, ids.toString())).build();
    }
}
