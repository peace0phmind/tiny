package cn.know.act.proton.system.service.criteria;

import cn.know.act.proton.core.service.QueryService;
import cn.know.act.proton.core.util.IRW;
import cn.know.act.proton.system.domain.User;
import cn.know.act.proton.system.domain.User_;
import cn.know.act.proton.system.repository.jpa.UserJpaRepository;
import cn.know.act.proton.system.service.dto.UserDTO;
import cn.know.act.proton.system.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Generated;
import cn.know.act.proton.system.domain.Department_;
import cn.know.act.proton.system.domain.Role_;

/**
 * Service for executing complex queries for {@link User } entities in the database.
 * The main input is a {@link UserCriteria } which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link UserDTO } or a {@link Page} of {@link UserDTO } which fulfills the criteria.
 */
@Transactional(readOnly = true)
@Service("SysUserQueryService")
public class UserQueryService extends QueryService<User> {

    @Generated(IRW.CODE_GENERATOR)
    private final Logger log = LoggerFactory.getLogger(UserQueryService.class);

    @Generated(IRW.CODE_GENERATOR)
    private final UserJpaRepository userJpaRepository;

    @Generated(IRW.CODE_GENERATOR)
    private final UserMapper userMapper;

    @Generated(IRW.CODE_GENERATOR)
    public UserQueryService(UserJpaRepository userJpaRepository, UserMapper userMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userMapper = userMapper;
    }

    /**
     * Return a {@link Page} of {@link UserDTO } which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param pageable The pageable, which should be returned.
     * @return the matching entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Transactional(readOnly = true)
    public Page<UserDTO> findByCriteria(UserCriteria criteria, Pageable pageable, boolean retDetail) {
        if (log.isDebugEnabled()) {
            log.debug("find by criteria : {}, pageable: {}", criteria, pageable);
        }
        final Specification<User> specification = createSpecification(criteria);
        if (retDetail) {
            return userJpaRepository.findAll(specification, pageable).map(userMapper::toDtoWithModel);
        } else {
            return userJpaRepository.findAll(specification, pageable).map(userMapper::toDto);
        }
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Generated(IRW.CODE_GENERATOR)
    @Transactional(readOnly = true)
    public long countByCriteria(UserCriteria criteria) {
        if (log.isDebugEnabled()) {
            log.debug("count by criteria : {}", criteria);
        }
        final Specification<User> specification = createSpecification(criteria);
        return userJpaRepository.count(specification);
    }

    /**
     * Function to convert UserCriteria to a {@link Specification}.
     */
    @Generated(IRW.CODE_GENERATOR)
    private Specification<User> createSpecification(UserCriteria criteria) {
        Specification<User> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), User_.name));
            }
            if (criteria.getUsername() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUsername(), User_.username));
            }
            if (criteria.getPhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhone(), User_.phone));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), User_.email));
            }
            if (criteria.getBirthday() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBirthday(), User_.birthday));
            }
            if (criteria.getEnabled() != null) {
                specification = specification.and(buildSpecification(criteria.getEnabled(), User_.enabled));
            }
            if (criteria.getDepartment() != null) {
                specification = specification.and(buildReferringESpecification(criteria.getDepartment(), User_.department, Department_.id));
            }
            if (criteria.getRoles() != null) {
                specification = specification.and(buildReferringESpecification(criteria.getRoles(), User_.roles, Role_.id));
            }
            if (criteria.getSex() != null) {
                specification = specification.and(buildSpecification(criteria.getSex(), User_.sex));
            }
        }
        return specification;
    }
}
