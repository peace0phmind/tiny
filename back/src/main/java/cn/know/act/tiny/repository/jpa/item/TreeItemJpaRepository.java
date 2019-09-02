package cn.know.act.tiny.repository.jpa.item;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.tiny.domain.item.TreeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import javax.annotation.Generated;
import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data repository for the {@link TreeItem }  entity.
 */
@SuppressWarnings("unused")
@Repository("TnyTreeItemJpaRepository")
public interface TreeItemJpaRepository extends JpaRepository<TreeItem, UUID>, JpaSpecificationExecutor<TreeItem> {

    @Generated(IRW.CODE_GENERATOR)
    @Nullable
    Optional<TreeItem> findById(UUID id);

    @Generated(IRW.CODE_GENERATOR)
    Optional<TreeItem> findWithModelById(UUID id);

    @Generated(IRW.CODE_GENERATOR)
    @Nullable
    TreeItem save(TreeItem treeItem);
}
