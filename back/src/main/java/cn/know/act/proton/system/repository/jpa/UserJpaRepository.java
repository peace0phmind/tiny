package cn.know.act.proton.system.repository.jpa;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import javax.annotation.Generated;
import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data repository for the {@link User }  entity.
 */
@SuppressWarnings("unused")
@Repository("SysUserJpaRepository")
public interface UserJpaRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {

    @Generated(IRW.CODE_GENERATOR)
    String CACHE_NAME = "SysUserCache";

    @Nullable
    // @Cacheable(cacheNames = CACHE_NAME, key = "#id")
    @EntityGraph(attributePaths = "roles")
    Optional<User> findById(UUID id);

    @EntityGraph(attributePaths = "roles")
    Optional<User> findOneByUsername(String username);

    @Nullable
    // @CacheEvict(cacheNames = CACHE_NAME, key = "#result.id")
    User save(User user);

    @EntityGraph(attributePaths = "roles")
    Optional<User> findOneWithRolesByUsername(String username);

    @Generated(IRW.CODE_GENERATOR)
    @EntityGraph(attributePaths = { "department" })
    Optional<User> findWithModelById(UUID id);
}
