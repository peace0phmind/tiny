package cn.know.act.tiny.service.impl;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.tiny.domain.TestType;
import cn.know.act.tiny.repository.jpa.TestTypeJpaRepository;
import cn.know.act.tiny.service.dto.TestTypeDTO;
import cn.know.act.tiny.service.inf.TestTypeService;
import cn.know.act.tiny.service.mapper.TestTypeMapper;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link TestType }.
 */
@Transactional
@Service("TnyTestTypeServiceImpl")
public class TestTypeServiceImpl implements TestTypeService {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(TestTypeServiceImpl.class);

    @Generated(IRW.CODE_GENERATOR)
    private final TestTypeJpaRepository testTypeJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final TestTypeMapper testTypeMapper;

    @Generated(IRW.CODE_GENERATOR)
    private final CacheManager cacheManager;

    @Generated(IRW.CODE_GENERATOR)
    public TestTypeServiceImpl(TestTypeJpaRepository testTypeJpaRepository, TestTypeMapper testTypeMapper, CacheManager cacheManager) {
        this.testTypeJpaRepository = testTypeJpaRepository;
        this.testTypeMapper = testTypeMapper;
        this.cacheManager = cacheManager;
    }

    /**
     * Create a TestType.
     *
     * @param testTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public TestTypeDTO create(TestTypeDTO testTypeDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save testType: {}", testTypeDTO);
        }
        Assert.isNull(testTypeDTO.getId(), "Create entity's id must be null.");
        TestType testType = testTypeJpaRepository.save(testTypeMapper.toDomain(testTypeDTO));
        TestTypeDTO result = testTypeMapper.toDto(testType);
        // TODO add search option
        return result;
    }

    /**
     * Update a TestType.
     *
     * @param testTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public TestTypeDTO update(TestTypeDTO testTypeDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save testType: {}", testTypeDTO);
        }
        Assert.notNull(testTypeDTO.getId(), "Update entity's id must not be null.");
        TestType testType = testTypeJpaRepository.getOne(testTypeDTO.getId());
        testTypeMapper.updateDomain(testTypeDTO, testType);
        TestType ret = testTypeJpaRepository.save(testType);
        TestTypeDTO result = testTypeMapper.toDto(ret);
        // TODO add search option
        return result;
    }

    /**
     * Get all the TestTypes.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Page<TestTypeDTO> findAll(Pageable pageable) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get all TestTypes");
        }
        return testTypeJpaRepository.findAll(pageable).map(testTypeMapper::toDtoWithModel);
    }

    /**
     * Get one TestType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Optional<TestTypeDTO> findOne(Long id) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get TestType: {}", id);
        }
        return testTypeJpaRepository.findWithModelById(id).map(testTypeMapper::toDtoWithModel);
    }

    /**
     * Delete the "ids" TestType.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public void deleteByIds(List<Long> ids) {
        if (log.isDebugEnabled()) {
            log.debug("Request to delete TestTypes: {}", ids);
        }
        testTypeJpaRepository.deleteAll(testTypeJpaRepository.findAllById(ids));
    }
}
