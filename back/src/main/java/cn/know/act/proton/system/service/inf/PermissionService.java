package cn.know.act.proton.system.service.inf;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.service.dto.PermissionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Interface for managing {@link cn.know.act.proton.system.domain.Permission}
 */
public interface PermissionService {

    /**
     * Create a Permission.
     *
     * @param permissionDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    PermissionDTO create(PermissionDTO permissionDTO);

    /**
     * Update a Permission.
     *
     * @param permissionDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    PermissionDTO update(PermissionDTO permissionDTO);

    /**
     * Get all the permissions.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    Page<PermissionDTO> findAll(Pageable pageable);

    /**
     * Get the "id" Permission.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    Optional<PermissionDTO> findOne(UUID id);

    /**
     * Delete the "ids" Permission.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    void deleteByIds(List<UUID> ids);
}
