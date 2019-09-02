package cn.know.act.tiny.service.impl;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.core.util.JpaUtil;
import cn.know.act.tiny.domain.Test;
import cn.know.act.tiny.repository.jpa.TestJpaRepository;
import cn.know.act.tiny.service.dto.TestDTO;
import cn.know.act.tiny.service.inf.TestService;
import cn.know.act.tiny.service.mapper.TestMapper;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;
import cn.know.act.tiny.repository.jpa.item.ItemJpaRepository;
import cn.know.act.tiny.service.mapper.item.ItemMapper;
import cn.know.act.tiny.domain.item.Item;

/**
 * Service Implementation for managing {@link Test }.
 */
@Transactional
@Service("TnyTestServiceImpl")
public class TestServiceImpl implements TestService {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(TestServiceImpl.class);

    @Generated(IRW.CODE_GENERATOR)
    private final TestJpaRepository testJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final TestMapper testMapper;

    @Generated(IRW.CODE_GENERATOR)
    private final CacheManager cacheManager;

    @Generated(IRW.CODE_GENERATOR)
    @Autowired
    private ItemJpaRepository itemJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    @Autowired
    private ItemMapper itemMapper;

    @Generated(IRW.CODE_GENERATOR)
    public TestServiceImpl(TestJpaRepository testJpaRepository, TestMapper testMapper, CacheManager cacheManager) {
        this.testJpaRepository = testJpaRepository;
        this.testMapper = testMapper;
        this.cacheManager = cacheManager;
    }

    /**
     * Create a Test.
     *
     * @param testDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public TestDTO create(TestDTO testDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save test: {}", testDTO);
        }
        Assert.isNull(testDTO.getId(), "Create entity's id must be null.");
        Test test = testJpaRepository.save(testMapper.toDomain(testDTO));
        JpaUtil.createList(testDTO.getItems(), itemJpaRepository, Item::setTest, test, itemMapper::toDomain);
        TestDTO result = testMapper.toDto(test);
        // TODO add search option
        return result;
    }

    /**
     * Update a Test.
     *
     * @param testDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public TestDTO update(TestDTO testDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save test: {}", testDTO);
        }
        Assert.notNull(testDTO.getId(), "Update entity's id must not be null.");
        Test test = testJpaRepository.getOne(testDTO.getId());
        JpaUtil.listUpdate(testDTO.getItems(), itemJpaRepository, Item::setTest, test, item -> item.getTest() != null && item.getTest().getId().equals(test.getId()), itemMapper::toDomain, itemMapper::toDtoOnlyId, itemMapper::updateDomain);
        testMapper.updateDomain(testDTO, test);
        Test ret = testJpaRepository.save(test);
        TestDTO result = testMapper.toDto(ret);
        // TODO add search option
        return result;
    }

    /**
     * Get all the Tests.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Page<TestDTO> findAll(Pageable pageable) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get all Tests");
        }
        return testJpaRepository.findAll(pageable).map(testMapper::toDtoWithModel);
    }

    /**
     * Get one Test by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Optional<TestDTO> findOne(Long id) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get Test: {}", id);
        }
        return testJpaRepository.findWithModelById(id).map(testMapper::toDtoWithModel);
    }

    /**
     * Delete the "ids" Test.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public void deleteByIds(List<Long> ids) {
        if (log.isDebugEnabled()) {
            log.debug("Request to delete Tests: {}", ids);
        }
        testJpaRepository.deleteAll(testJpaRepository.findAllById(ids));
    }
}
