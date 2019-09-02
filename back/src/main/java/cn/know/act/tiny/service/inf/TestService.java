package cn.know.act.tiny.service.inf;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.tiny.service.dto.TestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link cn.know.act.tiny.domain.Test}
 */
public interface TestService {

    /**
     * Create a Test.
     *
     * @param testDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    TestDTO create(TestDTO testDTO);

    /**
     * Update a Test.
     *
     * @param testDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    TestDTO update(TestDTO testDTO);

    /**
     * Get all the tests.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    Page<TestDTO> findAll(Pageable pageable);

    /**
     * Get the "id" Test.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    Optional<TestDTO> findOne(Long id);

    /**
     * Delete the "ids" Test.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    void deleteByIds(List<Long> ids);
}
