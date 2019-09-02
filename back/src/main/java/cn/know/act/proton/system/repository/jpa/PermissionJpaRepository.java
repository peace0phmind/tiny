package cn.know.act.proton.system.repository.jpa;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import javax.annotation.Generated;
import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data repository for the {@link Permission }  entity.
 */
@SuppressWarnings("unused")
@Repository("SysPermissionJpaRepository")
public interface PermissionJpaRepository extends JpaRepository<Permission, UUID>, JpaSpecificationExecutor<Permission> {

    @Generated(IRW.CODE_GENERATOR)
    @Nullable
    Optional<Permission> findById(UUID id);

    @Generated(IRW.CODE_GENERATOR)
    Optional<Permission> findWithModelById(UUID id);

    @Generated(IRW.CODE_GENERATOR)
    @Nullable
    Permission save(Permission permission);
}
