package cn.know.act.proton.system.repository.jpa;

import cn.know.act.proton.core.jpa.tree.TreeJpaRepository;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.domain.Menu;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;
import javax.annotation.Generated;
import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data repository for the {@link Menu }  entity.
 */
@SuppressWarnings("unused")
@Repository("SysMenuJpaRepository")
public interface MenuJpaRepository extends TreeJpaRepository<Menu, UUID> {

    Optional<Menu> findByName(String name);

    @Generated(IRW.CODE_GENERATOR)
    @Nullable
    Optional<Menu> findById(UUID id);

    @Generated(IRW.CODE_GENERATOR)
    @EntityGraph(attributePaths = { "_parent" })
    Optional<Menu> findWithModelById(UUID id);

    @Generated(IRW.CODE_GENERATOR)
    @Nullable
    Menu save(Menu menu);
}
