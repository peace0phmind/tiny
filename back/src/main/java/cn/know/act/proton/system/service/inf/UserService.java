package cn.know.act.proton.system.service.inf;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.service.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import cn.know.act.proton.system.service.dto.PasswordDTO;
import cn.know.act.proton.system.service.dto.MenuDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Service Interface for managing {@link cn.know.act.proton.system.domain.User}
 */
public interface UserService extends UserDetailsService {

    Optional<UserDTO> getUserWithAuthorities();

    Optional<List<MenuDTO>> getUserMenus();

    boolean resetPassword(UUID userId, PasswordDTO passwordDTO, boolean checkCurrentPassword);

    /**
     * Create a User.
     *
     * @param userDTO the entity to save.
     * @return the persisted entity.
     */
    UserDTO create(UserDTO userDTO, PasswordDTO passwordDTO);

    /**
     * Create a User.
     *
     * @param userDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    UserDTO create(UserDTO userDTO);

    /**
     * Update a User.
     *
     * @param userDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    UserDTO update(UserDTO userDTO);

    /**
     * Get all the users.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    Page<UserDTO> findAll(Pageable pageable);

    /**
     * Get the "id" User.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    Optional<UserDTO> findOne(UUID id);

    /**
     * Delete the "ids" User.
     *
     * @param ids the ids of the entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    void deleteByIds(List<UUID> ids);
}
