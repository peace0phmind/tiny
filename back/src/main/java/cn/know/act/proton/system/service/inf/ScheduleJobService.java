package cn.know.act.proton.system.service.inf;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.service.dto.ScheduleJobDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.quartz.SchedulerException;

/**
 * Service Interface for managing {@link cn.know.act.proton.system.domain.ScheduleJob}
 */
public interface ScheduleJobService {

    void pauseOrResumeScheduleJob(ScheduleJobDTO scheduleJobDTO) throws SchedulerException;

    /**
     * Create a ScheduleJob.
     *
     * @param scheduleJobDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    ScheduleJobDTO create(ScheduleJobDTO scheduleJobDTO);

    /**
     * Update a ScheduleJob.
     *
     * @param scheduleJobDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    ScheduleJobDTO update(ScheduleJobDTO scheduleJobDTO);

    /**
     * Get all the scheduleJobs.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    Page<ScheduleJobDTO> findAll(Pageable pageable);

    /**
     * Get the "id" ScheduleJob.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    Optional<ScheduleJobDTO> findOne(UUID id);

    /**
     * Delete the "ids" ScheduleJob.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    void deleteByIds(List<UUID> ids);
}
