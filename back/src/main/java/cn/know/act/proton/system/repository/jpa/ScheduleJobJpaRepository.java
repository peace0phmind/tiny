package cn.know.act.proton.system.repository.jpa;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.domain.ScheduleJob;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import javax.annotation.Generated;
import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data repository for the {@link ScheduleJob }  entity.
 */
@SuppressWarnings("unused")
@Repository("SysScheduleJobJpaRepository")
public interface ScheduleJobJpaRepository extends JpaRepository<ScheduleJob, UUID>, JpaSpecificationExecutor<ScheduleJob> {

    @Generated(IRW.CODE_GENERATOR)
    @Nullable
    Optional<ScheduleJob> findById(UUID id);

    @Generated(IRW.CODE_GENERATOR)
    @EntityGraph(attributePaths = { "creator", "modifier" })
    Optional<ScheduleJob> findWithModelById(UUID id);

    @Generated(IRW.CODE_GENERATOR)
    @Nullable
    ScheduleJob save(ScheduleJob scheduleJob);
}
