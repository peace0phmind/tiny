package cn.know.act.proton.system.web.rest;

import cn.know.act.proton.core.syslog.SysLog;
import cn.know.act.proton.core.web.util.ResponseUtil;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.service.criteria.ScheduleJobLogCriteria;
import cn.know.act.proton.system.service.criteria.ScheduleJobLogQueryService;
import cn.know.act.proton.system.service.dto.ScheduleJobLogDTO;
import cn.know.act.proton.system.service.inf.ScheduleJobLogService;
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
 * REST controller for managing {@link cn.know.act.proton.system.domain.ScheduleJobLog }.
 */
@RequestMapping("/api")
@RestController("SysScheduleJobLogResource")
public class ScheduleJobLogResource {

    @Generated(IRW.CODE_GENERATOR)
    private static final String ENTITY_NAME = "ScheduleJobLog";

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(ScheduleJobLogResource.class);

    @Generated(IRW.CODE_GENERATOR)
    private final ScheduleJobLogService scheduleJobLogService;

    @Generated(IRW.CODE_GENERATOR)
    private final ScheduleJobLogQueryService scheduleJobLogQueryService;

    @Generated(IRW.CODE_GENERATOR)
    @Value("${proton.clientApp.name}")
    private String applicationName;

    @Generated(IRW.CODE_GENERATOR)
    public ScheduleJobLogResource(ScheduleJobLogService scheduleJobLogService, ScheduleJobLogQueryService scheduleJobLogQueryService) {
        this.scheduleJobLogService = scheduleJobLogService;
        this.scheduleJobLogQueryService = scheduleJobLogQueryService;
    }

    /**
     * {@code GET  /sys-schedule-job-logs/:id} : get the "id" ScheduleJobLog.
     *
     * @param id the id of the ScheduleJobLog to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ScheduleJobLog, or with status {@code 404 (Not Found)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("获取单个定时任务日志")
    @GetMapping("/sys-schedule-job-logs/{id}")
    @PreAuthorize("hasPermission(#id, '', 'SysScheduleJobLog_READ')")
    public ResponseEntity<ScheduleJobLogDTO> getScheduleJobLog(@PathVariable Long id) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ${__domainSimpleClassName__} : {}", id);
        }
        Optional<ScheduleJobLogDTO> scheduleJobLogDTO = scheduleJobLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(scheduleJobLogDTO);
    }

    /**
     * {@code GET  /sys-schedule-job-logs} : get all the ScheduleJobLogs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @param pageable the page object
     * @param _d       return detail dto
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ScheduleJobLogs in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询定时任务日志列表")
    @GetMapping("/sys-schedule-job-logs")
    @PreAuthorize("hasPermission(#criteria, 'SysScheduleJobLog_READ')")
    public ResponseEntity<Page<ScheduleJobLogDTO>> findScheduleJobLogs(ScheduleJobLogCriteria criteria, Pageable pageable, Boolean _d) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ScheduleJobLogs");
        }
        if (_d == null) {
            _d = true;
        }
        return ResponseEntity.ok().body(scheduleJobLogQueryService.findByCriteria(criteria, pageable, _d));
    }

    /**
     * {@code GET  /sys-schedule-job-logs/count} : count all the ScheduleJobLogs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询定时任务日志数量")
    @GetMapping("/sys-schedule-job-logs/count")
    @PreAuthorize("hasPermission(#criteria, 'SysScheduleJobLog_READ')")
    public ResponseEntity<Long> countScheduleJobLogs(ScheduleJobLogCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to count TestEntities by criteria: {}", criteria);
        }
        return ResponseEntity.ok().body(scheduleJobLogQueryService.countByCriteria(criteria));
    }
}
