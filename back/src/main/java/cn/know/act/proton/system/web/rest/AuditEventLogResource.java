package cn.know.act.proton.system.web.rest;

import cn.know.act.proton.core.syslog.SysLog;
import cn.know.act.proton.core.web.util.ResponseUtil;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.service.criteria.AuditEventLogCriteria;
import cn.know.act.proton.system.service.criteria.AuditEventLogQueryService;
import cn.know.act.proton.system.service.dto.AuditEventLogDTO;
import cn.know.act.proton.system.service.inf.AuditEventLogService;
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
 * REST controller for managing {@link cn.know.act.proton.system.domain.AuditEventLog }.
 */
@RequestMapping("/api")
@RestController("SysAuditEventLogResource")
public class AuditEventLogResource {

    @Generated(IRW.CODE_GENERATOR)
    private static final String ENTITY_NAME = "AuditEventLog";

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(AuditEventLogResource.class);

    @Generated(IRW.CODE_GENERATOR)
    private final AuditEventLogService auditEventLogService;

    @Generated(IRW.CODE_GENERATOR)
    private final AuditEventLogQueryService auditEventLogQueryService;

    @Generated(IRW.CODE_GENERATOR)
    @Value("${proton.clientApp.name}")
    private String applicationName;

    @Generated(IRW.CODE_GENERATOR)
    public AuditEventLogResource(AuditEventLogService auditEventLogService, AuditEventLogQueryService auditEventLogQueryService) {
        this.auditEventLogService = auditEventLogService;
        this.auditEventLogQueryService = auditEventLogQueryService;
    }

    /**
     * {@code GET  /sys-audit-event-logs/:id} : get the "id" AuditEventLog.
     *
     * @param id the id of the AuditEventLog to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the AuditEventLog, or with status {@code 404 (Not Found)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("获取单个登录日志")
    @GetMapping("/sys-audit-event-logs/{id}")
    @PreAuthorize("hasPermission(#id, '', 'SysAuditEventLog_READ')")
    public ResponseEntity<AuditEventLogDTO> getAuditEventLog(@PathVariable Long id) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ${__domainSimpleClassName__} : {}", id);
        }
        Optional<AuditEventLogDTO> auditEventLogDTO = auditEventLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(auditEventLogDTO);
    }

    /**
     * {@code GET  /sys-audit-event-logs} : get all the AuditEventLogs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @param pageable the page object
     * @param _d       return detail dto
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of AuditEventLogs in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询登录日志列表")
    @GetMapping("/sys-audit-event-logs")
    @PreAuthorize("hasPermission(#criteria, 'SysAuditEventLog_READ')")
    public ResponseEntity<Page<AuditEventLogDTO>> findAuditEventLogs(AuditEventLogCriteria criteria, Pageable pageable, Boolean _d) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get AuditEventLogs");
        }
        if (_d == null) {
            _d = true;
        }
        return ResponseEntity.ok().body(auditEventLogQueryService.findByCriteria(criteria, pageable, _d));
    }

    /**
     * {@code GET  /sys-audit-event-logs/count} : count all the AuditEventLogs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询登录日志数量")
    @GetMapping("/sys-audit-event-logs/count")
    @PreAuthorize("hasPermission(#criteria, 'SysAuditEventLog_READ')")
    public ResponseEntity<Long> countAuditEventLogs(AuditEventLogCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to count TestEntities by criteria: {}", criteria);
        }
        return ResponseEntity.ok().body(auditEventLogQueryService.countByCriteria(criteria));
    }
}
