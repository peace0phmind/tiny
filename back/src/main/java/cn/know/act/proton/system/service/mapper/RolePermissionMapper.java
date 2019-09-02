package cn.know.act.proton.system.service.mapper;

import cn.know.act.proton.system.domain.RolePermission;
import cn.know.act.proton.system.service.dto.RolePermissionDTO;
import cn.know.act.proton.system.domain.OperationRolePerm;
import cn.know.act.proton.system.service.dto.OperationRolePermDTO;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import org.mapstruct.Named;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import cn.know.act.proton.system.domain.AttributeRolePerm;
import cn.know.act.proton.system.service.dto.AttributeRolePermDTO;
import org.mapstruct.Mapper;
import cn.know.act.proton.core.service.IOptionalMapper;

@Mapper(componentModel = "spring", implementationName = "Sys<CLASS_NAME>Impl", uses = { IOptionalMapper.class, PermissionMapper.class, RoleMapper.class })
public interface RolePermissionMapper {

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    OperationRolePermDTO toDtoOnlyId(OperationRolePerm operationRolePerm);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDto")
    @Mapping(target = "permission", qualifiedByName = "toDtoOnlyId")
    @Mapping(target = "role", qualifiedByName = "toDtoOnlyId")
    @Mapping(target = "_type", ignore = true)
    OperationRolePermDTO toDto(OperationRolePerm operationRolePerm);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModel")
    @Mapping(target = "permission", qualifiedByName = "toDto")
    @Mapping(target = "role", qualifiedByName = "toDto")
    @Mapping(target = "_type", ignore = true)
    OperationRolePermDTO toDtoWithModel(OperationRolePerm operationRolePerm);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModelAndCollection")
    @Mapping(target = "permission", qualifiedByName = "toDto")
    @Mapping(target = "role", qualifiedByName = "toDto")
    @Mapping(target = "_type", ignore = true)
    OperationRolePermDTO toDtoWithModelAndCollection(OperationRolePerm operationRolePerm);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomainOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    OperationRolePerm toDomainOnlyId(OperationRolePermDTO operationRolePermDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomain")
    @Mapping(target = "permission", qualifiedByName = "toDomainOnlyId")
    @Mapping(target = "role", qualifiedByName = "toDomainOnlyId")
    @Mapping(target = "_type", ignore = true)
    OperationRolePerm toDomain(OperationRolePermDTO operationRolePermDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "updateDomain")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "permission", qualifiedByName = "toDomainOnlyId")
    @Mapping(target = "role", qualifiedByName = "toDomainOnlyId")
    @Mapping(target = "_type", ignore = true)
    void updateDomain(OperationRolePermDTO operationRolePermDTO, @MappingTarget OperationRolePerm operationRolePerm);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    AttributeRolePermDTO toDtoOnlyId(AttributeRolePerm attributeRolePerm);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDto")
    @Mapping(target = "permission", qualifiedByName = "toDtoOnlyId")
    @Mapping(target = "role", qualifiedByName = "toDtoOnlyId")
    @Mapping(target = "_type", ignore = true)
    AttributeRolePermDTO toDto(AttributeRolePerm attributeRolePerm);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModel")
    @Mapping(target = "permission", qualifiedByName = "toDto")
    @Mapping(target = "role", qualifiedByName = "toDto")
    @Mapping(target = "_type", ignore = true)
    AttributeRolePermDTO toDtoWithModel(AttributeRolePerm attributeRolePerm);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModelAndCollection")
    @Mapping(target = "permission", qualifiedByName = "toDto")
    @Mapping(target = "role", qualifiedByName = "toDto")
    @Mapping(target = "_type", ignore = true)
    AttributeRolePermDTO toDtoWithModelAndCollection(AttributeRolePerm attributeRolePerm);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomainOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    AttributeRolePerm toDomainOnlyId(AttributeRolePermDTO attributeRolePermDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomain")
    @Mapping(target = "permission", qualifiedByName = "toDomainOnlyId")
    @Mapping(target = "role", qualifiedByName = "toDomainOnlyId")
    @Mapping(target = "_type", ignore = true)
    AttributeRolePerm toDomain(AttributeRolePermDTO attributeRolePermDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "updateDomain")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "permission", qualifiedByName = "toDomainOnlyId")
    @Mapping(target = "role", qualifiedByName = "toDomainOnlyId")
    @Mapping(target = "_type", ignore = true)
    void updateDomain(AttributeRolePermDTO attributeRolePermDTO, @MappingTarget AttributeRolePerm attributeRolePerm);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoOnlyId")
    default RolePermissionDTO toDtoOnlyId(RolePermission rolePermission) {
        if (rolePermission != null && rolePermission.get_type() != null) {
            switch(rolePermission.get_type()) {
                case OP:
                    return toDtoOnlyId(rolePermission.asOperationRolePerm());
                case ATTR:
                    return toDtoOnlyId(rolePermission.asAttributeRolePerm());
            }
        }
        return null;
    }

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDto")
    default RolePermissionDTO toDto(RolePermission rolePermission) {
        if (rolePermission != null && rolePermission.get_type() != null) {
            switch(rolePermission.get_type()) {
                case OP:
                    return toDto(rolePermission.asOperationRolePerm());
                case ATTR:
                    return toDto(rolePermission.asAttributeRolePerm());
            }
        }
        return null;
    }

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModel")
    default RolePermissionDTO toDtoWithModel(RolePermission rolePermission) {
        if (rolePermission != null && rolePermission.get_type() != null) {
            switch(rolePermission.get_type()) {
                case OP:
                    return toDtoWithModel(rolePermission.asOperationRolePerm());
                case ATTR:
                    return toDtoWithModel(rolePermission.asAttributeRolePerm());
            }
        }
        return null;
    }

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModelAndCollection")
    default RolePermissionDTO toDtoWithModelAndCollection(RolePermission rolePermission) {
        if (rolePermission != null && rolePermission.get_type() != null) {
            switch(rolePermission.get_type()) {
                case OP:
                    return toDtoWithModelAndCollection(rolePermission.asOperationRolePerm());
                case ATTR:
                    return toDtoWithModelAndCollection(rolePermission.asAttributeRolePerm());
            }
        }
        return null;
    }

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomainOnlyId")
    default RolePermission toDomainOnlyId(RolePermissionDTO rolePermissionDTO) {
        if (rolePermissionDTO != null && rolePermissionDTO.get_type() != null) {
            switch(rolePermissionDTO.get_type()) {
                case OP:
                    return toDomainOnlyId(rolePermissionDTO.asOperationRolePermDTO());
                case ATTR:
                    return toDomainOnlyId(rolePermissionDTO.asAttributeRolePermDTO());
            }
        }
        return null;
    }

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomain")
    default RolePermission toDomain(RolePermissionDTO rolePermissionDTO) {
        if (rolePermissionDTO != null && rolePermissionDTO.get_type() != null) {
            switch(rolePermissionDTO.get_type()) {
                case OP:
                    return toDomain(rolePermissionDTO.asOperationRolePermDTO());
                case ATTR:
                    return toDomain(rolePermissionDTO.asAttributeRolePermDTO());
            }
        }
        return null;
    }

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "updateDomain")
    default void updateDomain(RolePermissionDTO rolePermissionDTO, @MappingTarget RolePermission rolePermission) {
        if (rolePermissionDTO != null && rolePermissionDTO.get_type() != null) {
            switch(rolePermissionDTO.get_type()) {
                case OP:
                    updateDomain(rolePermissionDTO.asOperationRolePermDTO(), rolePermission.asOperationRolePerm());
                    break;
                case ATTR:
                    updateDomain(rolePermissionDTO.asAttributeRolePermDTO(), rolePermission.asAttributeRolePerm());
                    break;
            }
        }
    }
}
