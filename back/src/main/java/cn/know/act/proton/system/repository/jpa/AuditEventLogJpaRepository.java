package cn.know.act.proton.system.repository.jpa;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.domain.AuditEventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import javax.annotation.Generated;
import javax.annotation.Nullable;
import java.util.Optional;

/**
 * Spring Data repository for the {@link AuditEventLog }  entity.
 */
@SuppressWarnings("unused")
@Repository("SysAuditEventLogJpaRepository")
public interface AuditEventLogJpaRepository extends JpaRepository<AuditEventLog, Long>, JpaSpecificationExecutor<AuditEventLog> {

    @Generated(IRW.CODE_GENERATOR)
    @Nullable
    Optional<AuditEventLog> findById(Long id);

    @Generated(IRW.CODE_GENERATOR)
    Optional<AuditEventLog> findWithModelById(Long id);

    @Generated(IRW.CODE_GENERATOR)
    @Nullable
    AuditEventLog save(AuditEventLog auditEventLog);
}
