package cn.know.act.proton.system.web.rest;

import cn.know.act.proton.core.syslog.SysLog;
import cn.know.act.proton.core.web.util.HeaderUtil;
import cn.know.act.proton.core.web.util.ResponseUtil;
import cn.know.act.proton.core.web.rest.errors.BadRequestAlertException;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.service.criteria.UserCriteria;
import cn.know.act.proton.system.service.criteria.UserQueryService;
import cn.know.act.proton.system.service.dto.UserDTO;
import cn.know.act.proton.system.service.inf.UserService;
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
import cn.know.act.proton.core.syslog.SysLogIgnore;
import cn.know.act.proton.system.service.dto.PasswordDTO;

/**
 * REST controller for managing {@link cn.know.act.proton.system.domain.User }.
 */
@RequestMapping("/api")
@RestController("SysUserResource")
public class UserResource {

    @Generated(IRW.CODE_GENERATOR)
    private static final String ENTITY_NAME = "User";

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    @Generated(IRW.CODE_GENERATOR)
    private final UserService userService;

    @Generated(IRW.CODE_GENERATOR)
    private final UserQueryService userQueryService;

    @Generated(IRW.CODE_GENERATOR)
    @Value("${proton.clientApp.name}")
    private String applicationName;

    @Generated(IRW.CODE_GENERATOR)
    public UserResource(UserService userService, UserQueryService userQueryService) {
        this.userService = userService;
        this.userQueryService = userQueryService;
    }

    @SysLog("重置密码")
    @PutMapping("/sys-users/{id}/password")
    @PreAuthorize("hasPermission(#templateDTO, 'SysUser_UPDATE')")
    public ResponseEntity<Boolean> setPassword(@PathVariable UUID id, @SysLogIgnore PasswordDTO passwordDTO) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to set User password: {}", id);
        }
        boolean result = userService.resetPassword(id, passwordDTO, false);
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code POST  /sys-users : Create a new User.
     *
     * @param userDTO the User to add.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new User, or with status {@code 400 (Bad Request)} if the User has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @SysLog("创建用户")
    @PostMapping("/sys-users")
    @PreAuthorize("hasPermission(#templateDTO, 'SysUser_CREATE')")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO, @SysLogIgnore PasswordDTO passwordDTO) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to create User: {}", userDTO);
        }
        if (userDTO.getId() != null) {
            throw new BadRequestAlertException("A new User cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserDTO result = userService.create(userDTO, passwordDTO);
        return ResponseEntity.created(new URI("/api/sys-users/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * {@code GET  /sys-users/:id} : get the "id" User.
     *
     * @param id the id of the User to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the User, or with status {@code 404 (Not Found)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("获取单个用户")
    @GetMapping("/sys-users/{id}")
    @PreAuthorize("hasPermission(#id, '', 'SysUser_READ')")
    public ResponseEntity<UserDTO> getUser(@PathVariable UUID id) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ${__domainSimpleClassName__} : {}", id);
        }
        Optional<UserDTO> userDTO = userService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userDTO);
    }

    /**
     * {@code GET  /sys-users} : get all the Users.
     *
     * @param criteria the criteria which the requested entities should match.
     * @param pageable the page object
     * @param _d       return detail dto
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of Users in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询用户列表")
    @GetMapping("/sys-users")
    @PreAuthorize("hasPermission(#criteria, 'SysUser_READ')")
    public ResponseEntity<Page<UserDTO>> findUsers(UserCriteria criteria, Pageable pageable, Boolean _d) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get Users");
        }
        if (_d == null) {
            _d = true;
        }
        return ResponseEntity.ok().body(userQueryService.findByCriteria(criteria, pageable, _d));
    }

    /**
     * {@code GET  /sys-users/count} : count all the Users.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询用户数量")
    @GetMapping("/sys-users/count")
    @PreAuthorize("hasPermission(#criteria, 'SysUser_READ')")
    public ResponseEntity<Long> countUsers(UserCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to count TestEntities by criteria: {}", criteria);
        }
        return ResponseEntity.ok().body(userQueryService.countByCriteria(criteria));
    }

    /**
     * {@code PUT  /sys-users } : Updates an existing User.
     *
     * @param userDTO the User to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated User,
     * or with status {@code 400 (Bad Request)} if the User is not valid,
     * or with status {@code 500 (Internal Server Error)} if the User couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("更新用户")
    @PutMapping("/sys-users")
    @PreAuthorize("hasPermission(#templateDTO, 'SysUser_UPDATE')")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to update User: {}", userDTO);
        }
        if (userDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserDTO result = userService.update(userDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * {@code DELETE  /sys-users/:id} : delete the "ids" User.
     *
     * @param ids the ids of the Users to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("删除用户")
    @DeleteMapping("/sys-users/{ids}")
    @PreAuthorize("hasPermission(#ids, '', 'SysUser_DELETE')")
    public ResponseEntity<Void> deleteUser(@PathVariable List<UUID> ids) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to delete User : {}", ids);
        }
        userService.deleteByIds(ids);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, ids.toString())).build();
    }
}
