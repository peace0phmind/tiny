package cn.know.act.proton.system.service.impl;

import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.core.util.JpaUtil;
import cn.know.act.proton.system.domain.User;
import cn.know.act.proton.system.repository.jpa.UserJpaRepository;
import cn.know.act.proton.system.service.dto.UserDTO;
import cn.know.act.proton.system.service.inf.UserService;
import cn.know.act.proton.system.service.mapper.UserMapper;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import cn.know.act.proton.system.repository.jpa.RoleJpaRepository;
import cn.know.act.proton.system.domain.Menu;
import cn.know.act.proton.system.domain.Menu_;
import org.springframework.data.jpa.domain.Specification;
import cn.know.act.proton.system.service.dto.PasswordDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import cn.know.act.proton.system.service.dto.MenuDTO;
import cn.know.act.proton.system.domain.Role;
import cn.know.act.proton.system.repository.jpa.MenuJpaRepository;
import cn.know.act.proton.system.service.mapper.MenuMapper;
import java.util.stream.Collectors;
import cn.know.act.proton.config.security.SecurityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * Service Implementation for managing {@link User }.
 */
@Transactional
@Service("SysUserServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MenuJpaRepository menuJpaRepository;

    @Autowired
    private RoleJpaRepository roleJpaRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Generated(IRW.CODE_GENERATOR)
    private final UserJpaRepository userJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final UserMapper userMapper;

    @Generated(IRW.CODE_GENERATOR)
    private final CacheManager cacheManager;

    @Generated(IRW.CODE_GENERATOR)
    public UserServiceImpl(UserJpaRepository userJpaRepository, UserMapper userMapper, CacheManager cacheManager) {
        this.userJpaRepository = userJpaRepository;
        this.userMapper = userMapper;
        this.cacheManager = cacheManager;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Authenticating {}", username);
        return userJpaRepository.findOneByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User name " + username + " was not found in the database"));
    }

    @Transactional(readOnly = true)
    public Optional<UserDTO> getUserWithAuthorities() {
        return SecurityUtils.getCurrentUserWithStatusCheck().flatMap(u -> userJpaRepository.findById(u.getId())).map(userMapper::toDtoWithModelAndRolesPermission);
    }

    @Transactional(readOnly = true)
    public Optional<List<MenuDTO>> getUserMenus() {
        Optional<User> user = SecurityUtils.getCurrentUserWithStatusCheck().flatMap(u -> userJpaRepository.findById(u.getId()));
        return user.map(x -> {
            if (x.getRoles().stream().anyMatch(r -> r.getType() == Role.Type.SUPER)) {
                return menuJpaRepository.findTree((Specification<Menu>) (root, query, cb) -> cb.equal(root.get(Menu_.hideInMenu), false), Pageable.unpaged()).stream().map(menuMapper::toDtoWithModelAndCollection).collect(Collectors.toList());
            } else {
                List<UUID> ids = x.getRoles().stream().flatMap(r -> r.getMenus().stream()).filter(m -> !m.isHideInMenu()).map(m -> m.getId()).distinct().collect(Collectors.toList());
                return menuJpaRepository.findTreeByIds(ids).stream().map(menuMapper::toDtoWithModelAndCollection).collect(Collectors.toList());
            }
        });
    }

    /**
     * Get one User by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserDTO> findOne(UUID id) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get User: {}", id);
        }
        return userJpaRepository.findWithModelById(id).map(userMapper::toDtoWithModelAndCollection);
    }

    @Override
    public boolean resetPassword(UUID userId, PasswordDTO passwordDTO, boolean checkCurrentPassword) {
        Assert.notNull(userId, "用户id不能为空");
        if (passwordDTO == null) {
            return false;
        }
        Optional<User> currentUser = SecurityUtils.getCurrentUser();
        if (!currentUser.isPresent()) {
            throw new RuntimeException("请先登录再继续密码修改。");
        }
        User user = userJpaRepository.findById(userId).orElseThrow(() -> new RuntimeException("User could not be found"));
        if (checkCurrentPassword) {
            if (!userId.equals(currentUser.get().getId())) {
                throw new RuntimeException("用户只能修改自己的密码。");
            }
            boolean passwordMatches = passwordEncoder.matches(passwordDTO.getCurrentPassword(), user.getPassword());
            if (!passwordMatches) {
                throw new RuntimeException("当前密码不正确");
            }
        } else {
            if (userId.equals(currentUser.get().getId())) {
                throw new RuntimeException("用户不能设置自己的密码。");
            }
        }
        user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
        userJpaRepository.save(user);
        return true;
    }

    /**
     * Create a User.
     *
     * @param userDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UserDTO create(UserDTO userDTO, PasswordDTO passwordDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save user: {}", userDTO);
        }
        Assert.isNull(userDTO.getId(), "Create entity's id must be null.");
        User user = userMapper.toDomain(userDTO);
        if (passwordDTO != null && StringUtils.hasText(passwordDTO.getNewPassword())) {
            user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
        }
        user = userJpaRepository.save(user);
        JpaUtil.addItemRelation(userDTO.getRoles(), user.getRoles(), roleJpaRepository);
        if (CollectionUtils.isEmpty(userDTO.getRoles())) {
            List<Role> allDefaultRoles = roleJpaRepository.findAllByDefaultRole(true);
            if (!CollectionUtils.isEmpty(allDefaultRoles)) {
                user.getRoles().addAll(allDefaultRoles);
            }
        }
        UserDTO result = userMapper.toDto(user);
        // TODO add search option
        return result;
    }

    /**
     * Update a User.
     *
     * @param userDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UserDTO update(UserDTO userDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save user: {}", userDTO);
        }
        Assert.notNull(userDTO.getId(), "Update entity's id must not be null.");
        Optional<User> currentUser = SecurityUtils.getCurrentUser();
        if (!currentUser.isPresent()) {
            throw new RuntimeException("用户需先登录，才可以修改用户信息。");
        }
        if (userDTO.getEnabled() != null) {
            if (userDTO.getId().equals(currentUser.get().getId())) {
                throw new RuntimeException("用户不能锁定自己。");
            }
        }
        User user = userJpaRepository.getOne(userDTO.getId());
        JpaUtil.itemRelationUpdate(userDTO.getRoles(), user.getRoles(), roleJpaRepository);
        userMapper.updateDomain(userDTO, user);
        User ret = userJpaRepository.save(user);
        UserDTO result = userMapper.toDto(ret);
        // TODO add search option
        return result;
    }

    /**
     * Delete the "ids" User.
     *
     * @param ids the ids of the entities.
     */
    @Override
    public void deleteByIds(List<UUID> ids) {
        if (log.isDebugEnabled()) {
            log.debug("Request to delete Users: {}", ids);
        }
        Optional<User> currentUser = SecurityUtils.getCurrentUser();
        if (currentUser.isPresent()) {
            if (ids.contains(currentUser.get().getId())) {
                throw new RuntimeException("用户无法删除自己");
            }
            userJpaRepository.deleteAll(userJpaRepository.findAllById(ids));
            ids.forEach(cacheManager.getCache(UserJpaRepository.CACHE_NAME)::evict);
        }
    }

    /**
     * Create a User.
     *
     * @param userDTO the entity to save.
     * @return the persisted entity.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    public UserDTO create(UserDTO userDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request to save user: {}", userDTO);
        }
        Assert.isNull(userDTO.getId(), "Create entity's id must be null.");
        User user = userJpaRepository.save(userMapper.toDomain(userDTO));
        JpaUtil.addItemRelation(userDTO.getRoles(), user.getRoles(), roleJpaRepository);
        UserDTO result = userMapper.toDto(user);
        // TODO add search option
        return result;
    }

    /**
     * Get all the Users.
     *
     * @return the list of entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Override
    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(Pageable pageable) {
        if (log.isDebugEnabled()) {
            log.debug("Request to get all Users");
        }
        return userJpaRepository.findAll(pageable).map(userMapper::toDtoWithModel);
    }
}
