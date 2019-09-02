package cn.know.act.proton.system.service.inf;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.service.dto.SystemLogDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link cn.know.act.proton.system.domain.SystemLog}
 */
public interface SystemLogService {

    /**
     * Create a SystemLog.
     *
     * @param systemLogDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    SystemLogDTO create(SystemLogDTO systemLogDTO);

    /**
     * Update a SystemLog.
     *
     * @param systemLogDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    SystemLogDTO update(SystemLogDTO systemLogDTO);

    /**
     * Get all the systemLogs.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    Page<SystemLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" SystemLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    Optional<SystemLogDTO> findOne(Long id);

    /**
     * Delete the "ids" SystemLog.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    void deleteByIds(List<Long> ids);
}
