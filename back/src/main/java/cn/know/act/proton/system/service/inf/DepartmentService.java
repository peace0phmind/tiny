package cn.know.act.proton.system.service.inf;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.service.dto.DepartmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Interface for managing {@link cn.know.act.proton.system.domain.Department}
 */
public interface DepartmentService {

    /**
     * Create a Department.
     *
     * @param departmentDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    DepartmentDTO create(DepartmentDTO departmentDTO);

    /**
     * Update a Department.
     *
     * @param departmentDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    DepartmentDTO update(DepartmentDTO departmentDTO);

    /**
     * Get all the departments.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    Page<DepartmentDTO> findAll(Pageable pageable);

    /**
     * Get the "id" Department.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    Optional<DepartmentDTO> findOne(UUID id);

    /**
     * Delete the "ids" Department.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    void deleteByIds(List<UUID> ids);

    /**
     * add a node Department before the sibling
     *
     * @param departmentDTO the node entity
     * @param siblingId              the sibling node id
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    DepartmentDTO addBefore(DepartmentDTO departmentDTO, UUID siblingId);

    /**
     * add a node Department after the sibling
     *
     * @param departmentDTO the node entity
     * @param siblingId              the sibling node id
     * @return
     */
    @Generated(IRW.CODE_GENERATOR)
    DepartmentDTO addAfter(DepartmentDTO departmentDTO, UUID siblingId);

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
