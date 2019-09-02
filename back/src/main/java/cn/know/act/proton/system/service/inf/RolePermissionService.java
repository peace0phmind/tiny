package cn.know.act.proton.system.service.inf;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.service.dto.RolePermissionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Interface for managing {@link cn.know.act.proton.system.domain.RolePermission}
 */
public interface RolePermissionService {

    /**
     * Create a RolePermission.
     *
     * @param rolePermissionDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    RolePermissionDTO create(RolePermissionDTO rolePermissionDTO);

    /**
     * Update a RolePermission.
     *
     * @param rolePermissionDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    RolePermissionDTO update(RolePermissionDTO rolePermissionDTO);

    /**
     * Get all the rolePermissions.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    Page<RolePermissionDTO> findAll(Pageable pageable);

    /**
     * Get the "id" RolePermission.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    Optional<RolePermissionDTO> findOne(UUID id);

    /**
     * Delete the "ids" RolePermission.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    void deleteByIds(List<UUID> ids);
}
