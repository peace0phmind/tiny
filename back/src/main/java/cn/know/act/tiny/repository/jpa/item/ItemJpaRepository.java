package cn.know.act.tiny.repository.jpa.item;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.tiny.domain.item.Item;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import javax.annotation.Generated;
import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data repository for the {@link Item }  entity.
 */
@SuppressWarnings("unused")
@Repository("TnyItemJpaRepository")
public interface ItemJpaRepository extends JpaRepository<Item, UUID>, JpaSpecificationExecutor<Item> {

    @Generated(IRW.CODE_GENERATOR)
    @Nullable
    Optional<Item> findById(UUID id);

    @Generated(IRW.CODE_GENERATOR)
    @EntityGraph(attributePaths = { "test" })
    Optional<Item> findWithModelById(UUID id);

    @Generated(IRW.CODE_GENERATOR)
    @Nullable
    Item save(Item item);
}
