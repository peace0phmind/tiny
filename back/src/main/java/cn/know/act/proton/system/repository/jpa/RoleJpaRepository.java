package cn.know.act.proton.system.repository.jpa;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.domain.Role;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import javax.annotation.Generated;
import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;
import java.util.List;
import org.springframework.cache.annotation.CacheConfig;

/**
 * Spring Data repository for the {@link Role }  entity.
 */
@SuppressWarnings("unused")
@Repository("SysRoleJpaRepository")
@CacheConfig(cacheNames = { "SysRoleCache" })
public interface RoleJpaRepository extends JpaRepository<Role, UUID>, JpaSpecificationExecutor<Role> {

    @Generated(IRW.CODE_GENERATOR)
    String CACHE_NAME = "SysRoleCache";

    @Nullable
    // @Cacheable(key = "#id.toString()")
    @EntityGraph(attributePaths = { "permissions", "permissions.permission" })
    Optional<Role> findById(UUID id);

    List<Role> findAllByDefaultRole(Boolean defaultRole);

    Optional<Role> findByName(String name);

    @Nullable
    // @CacheEvict(cacheNames = CACHE_NAME, key = "#result.id")
    Role save(Role role);

    @Generated(IRW.CODE_GENERATOR)
    Optional<Role> findWithModelById(UUID id);
}
