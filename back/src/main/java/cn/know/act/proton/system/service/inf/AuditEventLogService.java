package cn.know.act.proton.system.service.inf;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.service.dto.AuditEventLogDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link cn.know.act.proton.system.domain.AuditEventLog}
 */
public interface AuditEventLogService {

    /**
     * Create a AuditEventLog.
     *
     * @param auditEventLogDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    AuditEventLogDTO create(AuditEventLogDTO auditEventLogDTO);

    /**
     * Update a AuditEventLog.
     *
     * @param auditEventLogDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    AuditEventLogDTO update(AuditEventLogDTO auditEventLogDTO);

    /**
     * Get all the auditEventLogs.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    Page<AuditEventLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" AuditEventLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    Optional<AuditEventLogDTO> findOne(Long id);

    /**
     * Delete the "ids" AuditEventLog.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    void deleteByIds(List<Long> ids);
}
