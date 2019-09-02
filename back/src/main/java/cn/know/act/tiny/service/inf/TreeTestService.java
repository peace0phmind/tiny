package cn.know.act.tiny.service.inf;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.tiny.service.dto.TreeTestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link cn.know.act.tiny.domain.TreeTest}
 */
public interface TreeTestService {

    /**
     * Create a TreeTest.
     *
     * @param treeTestDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    TreeTestDTO create(TreeTestDTO treeTestDTO);

    /**
     * Update a TreeTest.
     *
     * @param treeTestDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    TreeTestDTO update(TreeTestDTO treeTestDTO);

    /**
     * Get all the treeTests.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    Page<TreeTestDTO> findAll(Pageable pageable);

    /**
     * Get the "id" TreeTest.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    Optional<TreeTestDTO> findOne(Long id);

    /**
     * Delete the "ids" TreeTest.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    void deleteByIds(List<Long> ids);

    /**
     * add a node TreeTest before the sibling
     *
     * @param treeTestDTO the node entity
     * @param siblingId              the sibling node id
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    TreeTestDTO addBefore(TreeTestDTO treeTestDTO, Long siblingId);

    /**
     * add a node TreeTest after the sibling
     *
     * @param treeTestDTO the node entity
     * @param siblingId              the sibling node id
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    TreeTestDTO addAfter(TreeTestDTO treeTestDTO, Long siblingId);

    /**
     * move the node under the parent.
     *
     * @param nodeId   the node entity id
     * @param parentId the parent node id
     */
    @Generated(IRW.CODE_GENERATOR)
    void moveUnder(Long nodeId, Long parentId);

    /**
     * move the node before the sibling entity.
     *
     * @param nodeId    the node entity id
     * @param siblingId the sibling node id
     */
    @Generated(IRW.CODE_GENERATOR)
    void moveBefore(Long nodeId, Long siblingId);

    /**
     * move the node after the sibling entity.
     *
     * @param nodeId    the node entity id
     * @param siblingId the sibling node id
     */
    @Generated(IRW.CODE_GENERATOR)
    void moveAfter(Long nodeId, Long siblingId);
}
