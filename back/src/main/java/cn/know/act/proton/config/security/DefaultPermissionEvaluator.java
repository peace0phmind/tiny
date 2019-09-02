package cn.know.act.proton.config.security;


import cn.know.act.proton.system.domain.*;
import cn.know.act.proton.system.repository.jpa.RoleJpaRepository;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class DefaultPermissionEvaluator implements PermissionEvaluator {

    private static final Logger log = LoggerFactory.getLogger(DefaultPermissionEvaluator.class);

    private final ObjectFactory<RoleJpaRepository> roleJpaRepositoryObjectFactory;

    public DefaultPermissionEvaluator(ObjectFactory<RoleJpaRepository> roleJpaRepositoryObjectFactory) {
        this.roleJpaRepositoryObjectFactory = roleJpaRepositoryObjectFactory;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        log.debug("Has permission: {}, {}, {}.", authentication, targetDomainObject, permission);

        return hasPermission(authentication, permission, permissions -> {
            permissions.stream().filter(x -> {
                if (x.isAttributeRolePerm()) {
                    AttributeRolePerm.Type type = x.asAttributeRolePerm().getType();
                    if (type == AttributeRolePerm.Type.READ_ONLY || type == AttributeRolePerm.Type.HIDE) {
                        return true;
                    }
                }
                return false;
            }).map(x -> x.asAttributeRolePerm().getPermission().asAttributePerm()).forEach(x -> {
                try {
                    FieldUtils.writeField(targetDomainObject, x.getAttributeName(), null, true);
                } catch (IllegalAccessException e) {
                    log.warn("Set {} attribute name {} error", targetDomainObject, x.getAttributeName());
                }
            });
        });
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        log.debug("Has permission: {}, {}, {}, {}.", authentication, targetId, targetType, permission);

        return hasPermission(authentication, permission, null);
    }

    private boolean hasPermission(Authentication authentication, Object permission, Consumer<List<RolePermission>> doRolePermission) {
        OperationPerm operationPerm = getOperationFromPermission(permission);
        if (operationPerm != null) {
            if (authentication.getPrincipal() instanceof User) {
                User user = (User) authentication.getPrincipal();
                if (!user.isEnabled()) {
                    throw new RuntimeException("该用户已被锁定, 请联系管理员。");
                }

                Collection<Role> roles = user.getRoles();
                if (roles.stream().anyMatch(r -> r.getType() == Role.Type.SUPER)) {
                    return true;
                }

                if (doRolePermission != null) {
                    doRolePermission.accept(roles.stream().map(r -> roleJpaRepositoryObjectFactory.getObject().findById(r.getId()))
                            .filter(r -> r.isPresent()).flatMap(r -> r.get().getPermissions().stream()).distinct().collect(Collectors.toList()));
                }

                // TODO, role denying
//                if (roles.stream().filter(r -> r.getType() == Role.Type.DENYING).count() > 0) {
//                    long allow = roles.stream().filter(r -> r.getType() == Role.Type.DENYING).map(r -> roleJpaRepositoryObjectFactory.getObject().findById(r.getId()))
//                            .filter(r -> r.isPresent()).flatMap(r -> r.get().getPermissions().stream()).distinct().filter(x -> {
//                                if (x.isOperationRolePerm()) {
//                                    OperationPerm perm = x.asOperationRolePerm().getPermission().asOperationPerm();
//                                    if (perm.getModelName().equals(operationPerm.getModelName()) && perm.getType().equals(operationPerm.getType()) && x.asOperationRolePerm().isAllow()) {
//                                        return true;
//                                    }
//
//                                    return false;
//                                }
//                                return false;
//                            }).count();
//                    if (allow > 0) {
//                        return true;
//                    } else {
//                        return false;
//                    }
//                }

                if (roles.stream().filter(r -> r.getType() == Role.Type.STANDARD).count() > 0) {
                    long notAllow = roles.stream().filter(r -> r.getType() == Role.Type.STANDARD).map(r -> roleJpaRepositoryObjectFactory.getObject().findById(r.getId()))
                            .filter(r -> r.isPresent()).flatMap(r -> r.get().getPermissions().stream()).distinct().filter(x -> {
                                if (x.isOperationRolePerm()) {
                                    OperationPerm perm = x.asOperationRolePerm().getPermission().asOperationPerm();
                                    if (perm.getModelName().equals(operationPerm.getModelName()) && perm.getType().equals(operationPerm.getType()) && !x.asOperationRolePerm().isAllow()) {
                                        return true;
                                    }

                                    return false;
                                }
                                return false;
                            }).count();

                    if (notAllow > 0) {
                        return false;
                    } else {
                        return true;
                    }
                }

                // do not
                return false;
            }
        }

        return false;
    }

    private OperationPerm getOperationFromPermission(Object permission) {
        if (permission instanceof String) {

            String[] splitOpPerm = String.valueOf(permission).split("_");
            if (splitOpPerm.length == 2) {
                OperationPerm operationPerm = new OperationPerm();
                operationPerm.setModelName(splitOpPerm[0]);
                operationPerm.setType(OperationPerm.Type.valueOf(splitOpPerm[1].toUpperCase()));

                if (operationPerm.getType() != null) {
                    return operationPerm;
                }
            }
        }

        return null;
    }
}
