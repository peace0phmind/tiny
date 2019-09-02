package cn.know.act.tiny.repository.jpa;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.tiny.domain.TestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import javax.annotation.Generated;
import javax.annotation.Nullable;
import java.util.Optional;

/**
 * Spring Data repository for the {@link TestType }  entity.
 */
@SuppressWarnings("unused")
@Repository("TnyTestTypeJpaRepository")
public interface TestTypeJpaRepository extends JpaRepository<TestType, Long>, JpaSpecificationExecutor<TestType> {

    @Generated(IRW.CODE_GENERATOR)
    @Nullable
    Optional<TestType> findById(Long id);

    @Generated(IRW.CODE_GENERATOR)
    Optional<TestType> findWithModelById(Long id);

    @Generated(IRW.CODE_GENERATOR)
    @Nullable
    TestType save(TestType testType);
}
