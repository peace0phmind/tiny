package cn.know.act.proton.system.service.inf;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.service.dto.ScheduleJobLogDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link cn.know.act.proton.system.domain.ScheduleJobLog}
 */
public interface ScheduleJobLogService {

    /**
     * Create a ScheduleJobLog.
     *
     * @param scheduleJobLogDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    ScheduleJobLogDTO create(ScheduleJobLogDTO scheduleJobLogDTO);

    /**
     * Update a ScheduleJobLog.
     *
     * @param scheduleJobLogDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    ScheduleJobLogDTO update(ScheduleJobLogDTO scheduleJobLogDTO);

    /**
     * Get all the scheduleJobLogs.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    Page<ScheduleJobLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" ScheduleJobLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    Optional<ScheduleJobLogDTO> findOne(Long id);

    /**
     * Delete the "ids" ScheduleJobLog.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    void deleteByIds(List<Long> ids);
}
