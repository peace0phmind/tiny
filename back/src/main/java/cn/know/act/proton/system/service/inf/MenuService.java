package cn.know.act.proton.system.service.inf;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.service.dto.MenuDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Interface for managing {@link cn.know.act.proton.system.domain.Menu}
 */
public interface MenuService {

    /**
     * Create a Menu.
     *
     * @param menuDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    MenuDTO create(MenuDTO menuDTO);

    /**
     * Update a Menu.
     *
     * @param menuDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    MenuDTO update(MenuDTO menuDTO);

    /**
     * Get all the menus.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    Page<MenuDTO> findAll(Pageable pageable);

    /**
     * Get the "id" Menu.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    Optional<MenuDTO> findOne(UUID id);

    /**
     * Delete the "ids" Menu.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    void deleteByIds(List<UUID> ids);

    /**
     * add a node Menu before the sibling
     *
     * @param menuDTO the node entity
     * @param siblingId              the sibling node id
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    MenuDTO addBefore(MenuDTO menuDTO, UUID siblingId);

    /**
     * add a node Menu after the sibling
     *
     * @param menuDTO the node entity
     * @param siblingId              the sibling node id
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    MenuDTO addAfter(MenuDTO menuDTO, UUID siblingId);

    /**
     * move the node under the parent.
     *
     * @param nodeId   the node entity id
     * @param parentId the parent node id
     */
    @Generated(IRW.CODE_GENERATOR)
    void moveUnder(UUID nodeId, UUID parentId);

    /**
     * move the node before the sibling entity.
     *
     * @param nodeId    the node entity id
     * @param siblingId the sibling node id
     */
    @Generated(IRW.CODE_GENERATOR)
    void moveBefore(UUID nodeId, UUID siblingId);

    /**
     * move the node after the sibling entity.
     *
     * @param nodeId    the node entity id
     * @param siblingId the sibling node id
     */
    @Generated(IRW.CODE_GENERATOR)
    void moveAfter(UUID nodeId, UUID siblingId);
}
