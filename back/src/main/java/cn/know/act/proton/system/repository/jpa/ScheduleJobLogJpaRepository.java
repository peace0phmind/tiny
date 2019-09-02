package cn.know.act.proton.system.repository.jpa;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.domain.ScheduleJobLog;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import javax.annotation.Generated;
import javax.annotation.Nullable;
import java.util.Optional;

/**
 * Spring Data repository for the {@link ScheduleJobLog }  entity.
 */
@SuppressWarnings("unused")
@Repository("SysScheduleJobLogJpaRepository")
public interface ScheduleJobLogJpaRepository extends JpaRepository<ScheduleJobLog, Long>, JpaSpecificationExecutor<ScheduleJobLog> {

    @Generated(IRW.CODE_GENERATOR)
    @Nullable
    Optional<ScheduleJobLog> findById(Long id);

    @Generated(IRW.CODE_GENERATOR)
    @EntityGraph(attributePaths = { "job" })
    Optional<ScheduleJobLog> findWithModelById(Long id);

    @Generated(IRW.CODE_GENERATOR)
    @Nullable
    ScheduleJobLog save(ScheduleJobLog scheduleJobLog);
}
