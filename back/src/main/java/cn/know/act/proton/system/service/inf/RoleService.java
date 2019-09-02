package cn.know.act.proton.system.service.inf;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.service.dto.RoleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Interface for managing {@link cn.know.act.proton.system.domain.Role}
 */
public interface RoleService {

    /**
     * Create a Role.
     *
     * @param roleDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    RoleDTO create(RoleDTO roleDTO);

    /**
     * Update a Role.
     *
     * @param roleDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    RoleDTO update(RoleDTO roleDTO);

    /**
     * Get all the roles.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    Page<RoleDTO> findAll(Pageable pageable);

    /**
     * Get the "id" Role.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    Optional<RoleDTO> findOne(UUID id);

    /**
     * Delete the "ids" Role.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    void deleteByIds(List<UUID> ids);
}
