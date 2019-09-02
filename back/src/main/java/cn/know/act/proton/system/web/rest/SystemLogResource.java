package cn.know.act.proton.system.web.rest;

import cn.know.act.proton.core.syslog.SysLog;
import cn.know.act.proton.core.web.util.ResponseUtil;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.service.criteria.SystemLogCriteria;
import cn.know.act.proton.system.service.criteria.SystemLogQueryService;
import cn.know.act.proton.system.service.dto.SystemLogDTO;
import cn.know.act.proton.system.service.inf.SystemLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Generated;
import java.util.Optional;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing {@link cn.know.act.proton.system.domain.SystemLog }.
 */
@RequestMapping("/api")
@RestController("SysSystemLogResource")
public class SystemLogResource {

    @Generated(IRW.CODE_GENERATOR)
    private static final String ENTITY_NAME = "SystemLog";

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(SystemLogResource.class);

    @Generated(IRW.CODE_GENERATOR)
    private final SystemLogService systemLogService;

    @Generated(IRW.CODE_GENERATOR)
    private final SystemLogQueryService systemLogQueryService;

    @Generated(IRW.CODE_GENERATOR)
    @Value("${proton.clientApp.name}")
    private String applicationName;

    @Generated(IRW.CODE_GENERATOR)
    public SystemLogResource(SystemLogService systemLogService, SystemLogQueryService systemLogQueryService) {
        this.systemLogService = systemLogService;
        this.systemLogQueryService = systemLogQueryService;
    }

    /**
     * {@code GET  /sys-system-logs/:id} : get the "id" SystemLog.
     *
     * @param id the id of the SystemLog to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the SystemLog, or with status {@code 404 (Not Found)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("获取单个系统日志")
    @GetMapping("/sys-system-logs/{id}")
    @PreAuthorize("hasPermission(#id, '', 'SysSystemLog_READ')")
    public ResponseEntity<SystemLogDTO> getSystemLog(@PathVariable Long id) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ${__domainSimpleClassName__} : {}", id);
        }
        Optional<SystemLogDTO> systemLogDTO = systemLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(systemLogDTO);
    }

    /**
     * {@code GET  /sys-system-logs} : get all the SystemLogs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @param pageable the page object
     * @param _d       return detail dto
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of SystemLogs in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询系统日志列表")
    @GetMapping("/sys-system-logs")
    @PreAuthorize("hasPermission(#criteria, 'SysSystemLog_READ')")
    public ResponseEntity<Page<SystemLogDTO>> findSystemLogs(SystemLogCriteria criteria, Pageable pageable, Boolean _d) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get SystemLogs");
        }
        if (_d == null) {
            _d = true;
        }
        return ResponseEntity.ok().body(systemLogQueryService.findByCriteria(criteria, pageable, _d));
    }

    /**
     * {@code GET  /sys-system-logs/count} : count all the SystemLogs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询系统日志数量")
    @GetMapping("/sys-system-logs/count")
    @PreAuthorize("hasPermission(#criteria, 'SysSystemLog_READ')")
    public ResponseEntity<Long> countSystemLogs(SystemLogCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to count TestEntities by criteria: {}", criteria);
        }
        return ResponseEntity.ok().body(systemLogQueryService.countByCriteria(criteria));
    }
}
