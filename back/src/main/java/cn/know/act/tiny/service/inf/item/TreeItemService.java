package cn.know.act.tiny.service.inf.item;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.tiny.service.dto.item.TreeItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Interface for managing {@link cn.know.act.tiny.domain.item.TreeItem}
 */
public interface TreeItemService {

    /**
     * Create a TreeItem.
     *
     * @param treeItemDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    TreeItemDTO create(TreeItemDTO treeItemDTO);

    /**
     * Update a TreeItem.
     *
     * @param treeItemDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    TreeItemDTO update(TreeItemDTO treeItemDTO);

    /**
     * Get all the treeItems.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    Page<TreeItemDTO> findAll(Pageable pageable);

    /**
     * Get the "id" TreeItem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    Optional<TreeItemDTO> findOne(UUID id);

    /**
     * Delete the "ids" TreeItem.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    void deleteByIds(List<UUID> ids);
}
