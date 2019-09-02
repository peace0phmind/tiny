package cn.know.act.tiny.service.impl.item;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.core.util.JpaUtil;
import cn.know.act.tiny.domain.item.TreeItem;
import cn.know.act.tiny.repository.jpa.item.TreeItemJpaRepository;
import cn.know.act.tiny.service.dto.item.TreeItemDTO;
import cn.know.act.tiny.service.inf.item.TreeItemService;
import cn.know.act.tiny.service.mapper.item.TreeItemMapper;
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
import java.util.UUID;
import cn.know.act.tiny.repository.jpa.TreeTestJpaRepository;

/**
 * Service Implementation for managing {@link TreeItem }.
 */
@Transactional
@Service("TnyTreeItemServiceImpl")
public class TreeItemServiceImpl implements TreeItemService {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(TreeItemServiceImpl.class);

    @Generated(IRW.CODE_GENERATOR)
    private final TreeItemJpaRepository treeItemJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final TreeItemMapper treeItemMapper;

    @Generated(IRW.CODE_GENERATOR)
    private final CacheManager cacheManager;

    @Generated(IRW.CODE_GENERATOR)
    @Autowired
    private TreeTestJpaRepository treeTestJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    public TreeItemServiceImpl(TreeItemJpaRepository treeItemJpaRepository, TreeItemMapper treeItemMapper, CacheManager cacheManager) {
        this.treeItemJpaRepository = treeItemJpaRepository;
        this.treeItemMapper = treeItemMapper;
        this.cacheManager = cacheManager;
    }

    /**
     * Create a TreeItem.
     *
     * @param treeItemDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public TreeItemDTO create(TreeItemDTO treeItemDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save treeItem: {}", treeItemDTO);
        }
        Assert.isNull(treeItemDTO.getId(), "Create entity's id must be null.");
        TreeItem treeItem = treeItemJpaRepository.save(treeItemMapper.toDomain(treeItemDTO));
        JpaUtil.addItemRelation(treeItemDTO.getTests(), treeItem.getTests(), treeTestJpaRepository);
        TreeItemDTO result = treeItemMapper.toDto(treeItem);
        // TODO add search option
        return result;
    }

    /**
     * Update a TreeItem.
     *
     * @param treeItemDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public TreeItemDTO update(TreeItemDTO treeItemDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save treeItem: {}", treeItemDTO);
        }
        Assert.notNull(treeItemDTO.getId(), "Update entity's id must not be null.");
        TreeItem treeItem = treeItemJpaRepository.getOne(treeItemDTO.getId());
        JpaUtil.itemRelationUpdate(treeItemDTO.getTests(), treeItem.getTests(), treeTestJpaRepository);
        treeItemMapper.updateDomain(treeItemDTO, treeItem);
        TreeItem ret = treeItemJpaRepository.save(treeItem);
        TreeItemDTO result = treeItemMapper.toDto(ret);
        // TODO add search option
        return result;
    }

    /**
     * Get all the TreeItems.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Page<TreeItemDTO> findAll(Pageable pageable) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get all TreeItems");
        }
        return treeItemJpaRepository.findAll(pageable).map(treeItemMapper::toDtoWithModel);
    }

    /**
     * Get one TreeItem by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Optional<TreeItemDTO> findOne(UUID id) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get TreeItem: {}", id);
        }
        return treeItemJpaRepository.findWithModelById(id).map(treeItemMapper::toDtoWithModel);
    }

    /**
     * Delete the "ids" TreeItem.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public void deleteByIds(List<UUID> ids) {
        if (log.isDebugEnabled()) {
            log.debug("Request to delete TreeItems: {}", ids);
        }
        treeItemJpaRepository.deleteAll(treeItemJpaRepository.findAllById(ids));
    }
}
