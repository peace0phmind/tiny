package cn.know.act.proton.system.web.rest;

import cn.know.act.proton.core.syslog.SysLog;
import cn.know.act.proton.core.web.util.HeaderUtil;
import cn.know.act.proton.core.web.util.ResponseUtil;
import cn.know.act.proton.core.web.rest.errors.BadRequestAlertException;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.service.criteria.ScheduleJobCriteria;
import cn.know.act.proton.system.service.criteria.ScheduleJobQueryService;
import cn.know.act.proton.system.service.dto.ScheduleJobDTO;
import cn.know.act.proton.system.service.inf.ScheduleJobService;
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
import org.quartz.SchedulerException;

/**
 * REST controller for managing {@link cn.know.act.proton.system.domain.ScheduleJob }.
 */
@RequestMapping("/api")
@RestController("SysScheduleJobResource")
public class ScheduleJobResource {

    @Generated(IRW.CODE_GENERATOR)
    private static final String ENTITY_NAME = "ScheduleJob";

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(ScheduleJobResource.class);

    @Generated(IRW.CODE_GENERATOR)
    private final ScheduleJobService scheduleJobService;

    @Generated(IRW.CODE_GENERATOR)
    private final ScheduleJobQueryService scheduleJobQueryService;

    @Generated(IRW.CODE_GENERATOR)
    @Value("${proton.clientApp.name}")
    private String applicationName;

    @Generated(IRW.CODE_GENERATOR)
    public ScheduleJobResource(ScheduleJobService scheduleJobService, ScheduleJobQueryService scheduleJobQueryService) {
        this.scheduleJobService = scheduleJobService;
        this.scheduleJobQueryService = scheduleJobQueryService;
    }

    @PutMapping("/sys-schedule-jobs-pauseOrResume")
    @PreAuthorize("hasPermission(#templateDTO, 'SysScheduleJob_UPDATE')")
    public ResponseEntity<Void> pauseOrResumeScheduleJob(@Valid @RequestBody ScheduleJobDTO scheduleJobDTO) throws SchedulerException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to update ScheduleJob: {}", scheduleJobDTO);
        }
        if (scheduleJobDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        scheduleJobService.pauseOrResumeScheduleJob(scheduleJobDTO);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, "")).build();
    }

    /**
     * {@code POST  /sys-schedule-jobs : Create a new ScheduleJob.
     *
     * @param scheduleJobDTO the ScheduleJob to add.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ScheduleJob, or with status {@code 400 (Bad Request)} if the ScheduleJob has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("创建定时任务")
    @PostMapping("/sys-schedule-jobs")
    @PreAuthorize("hasPermission(#templateDTO, 'SysScheduleJob_CREATE')")
    public ResponseEntity<ScheduleJobDTO> createScheduleJob(@Valid @RequestBody ScheduleJobDTO scheduleJobDTO) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to create ScheduleJob: {}", scheduleJobDTO);
        }
        if (scheduleJobDTO.getId() != null) {
            throw new BadRequestAlertException("A new ScheduleJob cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ScheduleJobDTO result = scheduleJobService.create(scheduleJobDTO);
        return ResponseEntity.created(new URI("/api/sys-schedule-jobs/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * {@code GET  /sys-schedule-jobs/:id} : get the "id" ScheduleJob.
     *
     * @param id the id of the ScheduleJob to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ScheduleJob, or with status {@code 404 (Not Found)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("获取单个定时任务")
    @GetMapping("/sys-schedule-jobs/{id}")
    @PreAuthorize("hasPermission(#id, '', 'SysScheduleJob_READ')")
    public ResponseEntity<ScheduleJobDTO> getScheduleJob(@PathVariable UUID id) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ${__domainSimpleClassName__} : {}", id);
        }
        Optional<ScheduleJobDTO> scheduleJobDTO = scheduleJobService.findOne(id);
        return ResponseUtil.wrapOrNotFound(scheduleJobDTO);
    }

    /**
     * {@code GET  /sys-schedule-jobs} : get all the ScheduleJobs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @param pageable the page object
     * @param _d       return detail dto
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ScheduleJobs in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询定时任务列表")
    @GetMapping("/sys-schedule-jobs")
    @PreAuthorize("hasPermission(#criteria, 'SysScheduleJob_READ')")
    public ResponseEntity<Page<ScheduleJobDTO>> findScheduleJobs(ScheduleJobCriteria criteria, Pageable pageable, Boolean _d) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to get ScheduleJobs");
        }
        if (_d == null) {
            _d = true;
        }
        return ResponseEntity.ok().body(scheduleJobQueryService.findByCriteria(criteria, pageable, _d));
    }

    /**
     * {@code GET  /sys-schedule-jobs/count} : count all the ScheduleJobs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("根据条件查询定时任务数量")
    @GetMapping("/sys-schedule-jobs/count")
    @PreAuthorize("hasPermission(#criteria, 'SysScheduleJob_READ')")
    public ResponseEntity<Long> countScheduleJobs(ScheduleJobCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to count TestEntities by criteria: {}", criteria);
        }
        return ResponseEntity.ok().body(scheduleJobQueryService.countByCriteria(criteria));
    }

    /**
     * {@code PUT  /sys-schedule-jobs } : Updates an existing ScheduleJob.
     *
     * @param scheduleJobDTO the ScheduleJob to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ScheduleJob,
     * or with status {@code 400 (Bad Request)} if the ScheduleJob is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ScheduleJob couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("更新定时任务")
    @PutMapping("/sys-schedule-jobs")
    @PreAuthorize("hasPermission(#templateDTO, 'SysScheduleJob_UPDATE')")
    public ResponseEntity<ScheduleJobDTO> updateScheduleJob(@Valid @RequestBody ScheduleJobDTO scheduleJobDTO) throws URISyntaxException {
        if (log.isDebugEnabled()) {
            log.debug("REST request to update ScheduleJob: {}", scheduleJobDTO);
        }
        if (scheduleJobDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ScheduleJobDTO result = scheduleJobService.update(scheduleJobDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * {@code DELETE  /sys-schedule-jobs/:id} : delete the "ids" ScheduleJob.
     *
     * @param ids the ids of the ScheduleJobs to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @Generated(IRW.CODE_GENERATOR)
    @SysLog("删除定时任务")
    @DeleteMapping("/sys-schedule-jobs/{ids}")
    @PreAuthorize("hasPermission(#ids, '', 'SysScheduleJob_DELETE')")
    public ResponseEntity<Void> deleteScheduleJob(@PathVariable List<UUID> ids) {
        if (log.isDebugEnabled()) {
            log.debug("REST request to delete ScheduleJob : {}", ids);
        }
        scheduleJobService.deleteByIds(ids);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, ids.toString())).build();
    }
}
