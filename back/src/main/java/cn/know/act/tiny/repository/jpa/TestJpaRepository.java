package cn.know.act.tiny.repository.jpa;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.tiny.domain.Test;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import javax.annotation.Generated;
import javax.annotation.Nullable;
import java.util.Optional;

/**
 * Spring Data repository for the {@link Test }  entity.
 */
@SuppressWarnings("unused")
@Repository("TnyTestJpaRepository")
public interface TestJpaRepository extends JpaRepository<Test, Long>, JpaSpecificationExecutor<Test> {

    @Generated(IRW.CODE_GENERATOR)
    @Nullable
    Optional<Test> findById(Long id);

    @Generated(IRW.CODE_GENERATOR)
    @EntityGraph(attributePaths = { "templateType", "creator", "modifier" })
    Optional<Test> findWithModelById(Long id);

    @Generated(IRW.CODE_GENERATOR)
    @Nullable
    Test save(Test test);
}
