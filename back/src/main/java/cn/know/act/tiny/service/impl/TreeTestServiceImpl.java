package cn.know.act.tiny.service.impl;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.core.util.JpaUtil;
import cn.know.act.tiny.domain.TreeTest;
import cn.know.act.tiny.repository.jpa.TreeTestJpaRepository;
import cn.know.act.tiny.service.dto.TreeTestDTO;
import cn.know.act.tiny.service.inf.TreeTestService;
import cn.know.act.tiny.service.mapper.TreeTestMapper;
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
import cn.know.act.tiny.repository.jpa.item.TreeItemJpaRepository;

/**
 * Service Implementation for managing {@link TreeTest }.
 */
@Transactional
@Service("TnyTreeTestServiceImpl")
public class TreeTestServiceImpl implements TreeTestService {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(TreeTestServiceImpl.class);

    @Generated(IRW.CODE_GENERATOR)
    private final TreeTestJpaRepository treeTestJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final TreeTestMapper treeTestMapper;

    @Generated(IRW.CODE_GENERATOR)
    private final CacheManager cacheManager;

    @Generated(IRW.CODE_GENERATOR)
    @Autowired
    private TreeItemJpaRepository treeItemJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    public TreeTestServiceImpl(TreeTestJpaRepository treeTestJpaRepository, TreeTestMapper treeTestMapper, CacheManager cacheManager) {
        this.treeTestJpaRepository = treeTestJpaRepository;
        this.treeTestMapper = treeTestMapper;
        this.cacheManager = cacheManager;
    }

    /**
     * Create a TreeTest.
     *
     * @param treeTestDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public TreeTestDTO create(TreeTestDTO treeTestDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save treeTest: {}", treeTestDTO);
        }
        Assert.isNull(treeTestDTO.getId(), "Create entity's id must be null.");
        TreeTest treeTest = treeTestJpaRepository.save(treeTestMapper.toDomain(treeTestDTO));
        JpaUtil.addItemRelation(treeTestDTO.getTreeTestItems(), treeTest.getTreeTestItems(), treeItemJpaRepository);
        TreeTestDTO result = treeTestMapper.toDto(treeTest);
        // TODO add search option
        return result;
    }

    /**
     * Update a TreeTest.
     *
     * @param treeTestDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public TreeTestDTO update(TreeTestDTO treeTestDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save treeTest: {}", treeTestDTO);
        }
        Assert.notNull(treeTestDTO.getId(), "Update entity's id must not be null.");
        TreeTest treeTest = treeTestJpaRepository.getOne(treeTestDTO.getId());
        JpaUtil.itemRelationUpdate(treeTestDTO.getTreeTestItems(), treeTest.getTreeTestItems(), treeItemJpaRepository);
        treeTestMapper.updateDomain(treeTestDTO, treeTest);
        TreeTest ret = treeTestJpaRepository.save(treeTest);
        TreeTestDTO result = treeTestMapper.toDto(ret);
        // TODO add search option
        return result;
    }

    /**
     * Get all the TreeTests.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Page<TreeTestDTO> findAll(Pageable pageable) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get all TreeTests");
        }
        return treeTestJpaRepository.findAll(pageable).map(treeTestMapper::toDtoWithModel);
    }

    /**
     * Get one TreeTest by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Optional<TreeTestDTO> findOne(Long id) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get TreeTest: {}", id);
        }
        return treeTestJpaRepository.findWithModelById(id).map(treeTestMapper::toDtoWithModel);
    }

    /**
     * Delete the "ids" TreeTest.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public void deleteByIds(List<Long> ids) {
        if (log.isDebugEnabled()) {
            log.debug("Request to delete TreeTests: {}", ids);
        }
        treeTestJpaRepository.deleteAll(treeTestJpaRepository.findAllById(ids));
    }

    /**
     * add a node TreeTest before the sibling
     *
     * @param treeTestDTO the node entity
     * @param siblingId              the sibling node id
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public TreeTestDTO addBefore(TreeTestDTO treeTestDTO, Long siblingId) {
        if (log.isDebugEnabled()) {
            log.debug("Request to add treeTest: {}, before: {}", treeTestDTO, siblingId);
        }
        TreeTest treeTest = treeTestMapper.toDomain(treeTestDTO);
        treeTest = treeTestJpaRepository.addBefore(treeTest, siblingId);
        TreeTestDTO result = treeTestMapper.toDto(treeTest);
        // TODO add search option
        return result;
    }

    /**
     * add a node TreeTest after the sibling
     *
     * @param treeTestDTO the node entity
     * @param siblingId              the sibling node id
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public TreeTestDTO addAfter(TreeTestDTO treeTestDTO, Long siblingId) {
        if (log.isDebugEnabled()) {
            log.debug("Request to add treeTest: {}, after: {}", treeTestDTO, siblingId);
        }
        TreeTest treeTest = treeTestMapper.toDomain(treeTestDTO);
        treeTest = treeTestJpaRepository.addAfter(treeTest, siblingId);
        TreeTestDTO result = treeTestMapper.toDto(treeTest);
        // TODO add search option
        return result;
    }

    /**
     * move the node under the parent.
     *
     * @param nodeId   the node entity id
     * @param parentId the parent node id
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public void moveUnder(Long nodeId, Long parentId) {
        if (log.isDebugEnabled()) {
            log.debug("Request to move: {}, under: {}", nodeId, parentId);
        }
        treeTestJpaRepository.moveUnder(nodeId, parentId);
    }

    /**
     * move the node before the sibling entity.
     *
     * @param nodeId    the node entity id
     * @param siblingId the sibling node id
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public void moveBefore(Long nodeId, Long siblingId) {
        if (log.isDebugEnabled()) {
            log.debug("Request to move: {}, before: {}", nodeId, siblingId);
        }
        treeTestJpaRepository.moveBefore(nodeId, siblingId);
    }

    /**
     * move the node after the sibling entity.
     *
     * @param nodeId    the node entity id
     * @param siblingId the sibling node id
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public void moveAfter(Long nodeId, Long siblingId) {
        if (log.isDebugEnabled()) {
            log.debug("Request to move: {}, after: {}", nodeId, siblingId);
        }
        treeTestJpaRepository.moveAfter(nodeId, siblingId);
    }
}
