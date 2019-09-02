package cn.know.act.proton.system.repository.jpa;

import cn.know.act.proton.core.jpa.tree.TreeJpaRepository;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.domain.Department;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;
import javax.annotation.Generated;
import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data repository for the {@link Department }  entity.
 */
@SuppressWarnings("unused")
@Repository("SysDepartmentJpaRepository")
public interface DepartmentJpaRepository extends TreeJpaRepository<Department, UUID> {

    @Generated(IRW.CODE_GENERATOR)
    @Nullable
    Optional<Department> findById(UUID id);

    @Generated(IRW.CODE_GENERATOR)
    @EntityGraph(attributePaths = { "_parent" })
    Optional<Department> findWithModelById(UUID id);

    @Generated(IRW.CODE_GENERATOR)
    @Nullable
    Department save(Department department);
}
