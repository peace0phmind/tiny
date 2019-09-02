package cn.know.act.tiny.repository.jpa;

import cn.know.act.proton.core.jpa.tree.TreeJpaRepository;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.tiny.domain.TreeTest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;
import javax.annotation.Generated;
import javax.annotation.Nullable;
import java.util.Optional;

/**
 * Spring Data repository for the {@link TreeTest }  entity.
 */
@SuppressWarnings("unused")
@Repository("TnyTreeTestJpaRepository")
public interface TreeTestJpaRepository extends TreeJpaRepository<TreeTest, Long> {

    @Generated(IRW.CODE_GENERATOR)
    @Nullable
    Optional<TreeTest> findById(Long id);

    @Generated(IRW.CODE_GENERATOR)
    @EntityGraph(attributePaths = { "testType", "_parent", "creator", "modifier" })
    Optional<TreeTest> findWithModelById(Long id);

    @Generated(IRW.CODE_GENERATOR)
    @Nullable
    TreeTest save(TreeTest treeTest);
}
