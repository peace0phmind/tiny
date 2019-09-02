package cn.know.act.tiny.service.inf.item;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.tiny.service.dto.item.ItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Interface for managing {@link cn.know.act.tiny.domain.item.Item}
 */
public interface ItemService {

    /**
     * Create a Item.
     *
     * @param itemDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    ItemDTO create(ItemDTO itemDTO);

    /**
     * Update a Item.
     *
     * @param itemDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    ItemDTO update(ItemDTO itemDTO);

    /**
     * Get all the items.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    Page<ItemDTO> findAll(Pageable pageable);

    /**
     * Get the "id" Item.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    Optional<ItemDTO> findOne(UUID id);

    /**
     * Delete the "ids" Item.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    void deleteByIds(List<UUID> ids);
}
