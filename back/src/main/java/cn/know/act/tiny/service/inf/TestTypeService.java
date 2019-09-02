package cn.know.act.tiny.service.inf;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.tiny.service.dto.TestTypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link cn.know.act.tiny.domain.TestType}
 */
public interface TestTypeService {

    /**
     * Create a TestType.
     *
     * @param testTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    TestTypeDTO create(TestTypeDTO testTypeDTO);

    /**
     * Update a TestType.
     *
     * @param testTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    TestTypeDTO update(TestTypeDTO testTypeDTO);

    /**
     * Get all the testTypes.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    Page<TestTypeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" TestType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    Optional<TestTypeDTO> findOne(Long id);

    /**
     * Delete the "ids" TestType.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    void deleteByIds(List<Long> ids);
}
