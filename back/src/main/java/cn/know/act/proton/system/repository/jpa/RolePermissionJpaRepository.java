package cn.know.act.proton.system.repository.jpa;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.domain.RolePermission;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import javax.annotation.Generated;
import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data repository for the {@link RolePermission }  entity.
 */
@SuppressWarnings("unused")
@Repository("SysRolePermissionJpaRepository")
public interface RolePermissionJpaRepository extends JpaRepository<RolePermission, UUID>, JpaSpecificationExecutor<RolePermission> {

    @Generated(IRW.CODE_GENERATOR)
    @Nullable
    Optional<RolePermission> findById(UUID id);

    @Generated(IRW.CODE_GENERATOR)
    @EntityGraph(attributePaths = { "permission", "role" })
    Optional<RolePermission> findWithModelById(UUID id);

    @Generated(IRW.CODE_GENERATOR)
    @Nullable
    RolePermission save(RolePermission rolePermission);
}
