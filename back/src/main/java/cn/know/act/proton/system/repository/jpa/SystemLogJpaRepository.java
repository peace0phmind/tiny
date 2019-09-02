package cn.know.act.proton.system.repository.jpa;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.domain.SystemLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import javax.annotation.Generated;
import javax.annotation.Nullable;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring Data repository for the {@link SystemLog }  entity.
 */
@SuppressWarnings("unused")
@Repository("SysSystemLogJpaRepository")
public interface SystemLogJpaRepository extends JpaRepository<SystemLog, Long>, JpaSpecificationExecutor<SystemLog> {

    @Transactional(rollbackFor = Exception.class)
    @Nullable
    SystemLog save(SystemLog systemLog);

    @Generated(IRW.CODE_GENERATOR)
    @Nullable
    Optional<SystemLog> findById(Long id);

    @Generated(IRW.CODE_GENERATOR)
    Optional<SystemLog> findWithModelById(Long id);
}
