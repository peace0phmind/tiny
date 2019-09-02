package cn.know.act.tiny.service.impl.item;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.tiny.domain.item.Item;
import cn.know.act.tiny.repository.jpa.item.ItemJpaRepository;
import cn.know.act.tiny.service.dto.item.ItemDTO;
import cn.know.act.tiny.service.inf.item.ItemService;
import cn.know.act.tiny.service.mapper.item.ItemMapper;
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
import java.util.UUID;

/**
 * Service Implementation for managing {@link Item }.
 */
@Transactional
@Service("TnyItemServiceImpl")
public class ItemServiceImpl implements ItemService {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Generated(IRW.CODE_GENERATOR)
    private final ItemJpaRepository itemJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final ItemMapper itemMapper;

    @Generated(IRW.CODE_GENERATOR)
    private final CacheManager cacheManager;

    @Generated(IRW.CODE_GENERATOR)
    public ItemServiceImpl(ItemJpaRepository itemJpaRepository, ItemMapper itemMapper, CacheManager cacheManager) {
        this.itemJpaRepository = itemJpaRepository;
        this.itemMapper = itemMapper;
        this.cacheManager = cacheManager;
    }

    /**
     * Create a Item.
     *
     * @param itemDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public ItemDTO create(ItemDTO itemDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save item: {}", itemDTO);
        }
        Assert.isNull(itemDTO.getId(), "Create entity's id must be null.");
        Item item = itemJpaRepository.save(itemMapper.toDomain(itemDTO));
        ItemDTO result = itemMapper.toDto(item);
        // TODO add search option
        return result;
    }

    /**
     * Update a Item.
     *
     * @param itemDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public ItemDTO update(ItemDTO itemDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save item: {}", itemDTO);
        }
        Assert.notNull(itemDTO.getId(), "Update entity's id must not be null.");
        Item item = itemJpaRepository.getOne(itemDTO.getId());
        itemMapper.updateDomain(itemDTO, item);
        Item ret = itemJpaRepository.save(item);
        ItemDTO result = itemMapper.toDto(ret);
        // TODO add search option
        return result;
    }

    /**
     * Get all the Items.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Page<ItemDTO> findAll(Pageable pageable) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get all Items");
        }
        return itemJpaRepository.findAll(pageable).map(itemMapper::toDtoWithModel);
    }

    /**
     * Get one Item by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Optional<ItemDTO> findOne(UUID id) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get Item: {}", id);
        }
        return itemJpaRepository.findWithModelById(id).map(itemMapper::toDtoWithModel);
    }

    /**
     * Delete the "ids" Item.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public void deleteByIds(List<UUID> ids) {
        if (log.isDebugEnabled()) {
            log.debug("Request to delete Items: {}", ids);
        }
        itemJpaRepository.deleteAll(itemJpaRepository.findAllById(ids));
    }
}
