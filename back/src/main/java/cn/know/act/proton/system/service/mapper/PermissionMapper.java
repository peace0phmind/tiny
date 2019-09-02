package cn.know.act.proton.system.service.mapper;

import cn.know.act.proton.system.domain.Permission;
import cn.know.act.proton.system.service.dto.PermissionDTO;
import cn.know.act.proton.system.domain.OperationPerm;
import cn.know.act.proton.system.service.dto.OperationPermDTO;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import org.mapstruct.Named;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import cn.know.act.proton.system.domain.AttributePerm;
import cn.know.act.proton.system.service.dto.AttributePermDTO;
import org.mapstruct.Mapper;
import cn.know.act.proton.core.service.IOptionalMapper;

@Mapper(componentModel = "spring", implementationName = "Sys<CLASS_NAME>Impl", uses = { IOptionalMapper.class })
public interface PermissionMapper {

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    OperationPermDTO toDtoOnlyId(OperationPerm operationPerm);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDto")
    @Mapping(target = "_type", ignore = true)
    OperationPermDTO toDto(OperationPerm operationPerm);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModel")
    @Mapping(target = "_type", ignore = true)
    OperationPermDTO toDtoWithModel(OperationPerm operationPerm);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModelAndCollection")
    @Mapping(target = "_type", ignore = true)
    OperationPermDTO toDtoWithModelAndCollection(OperationPerm operationPerm);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomainOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    OperationPerm toDomainOnlyId(OperationPermDTO operationPermDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomain")
    @Mapping(target = "_type", ignore = true)
    OperationPerm toDomain(OperationPermDTO operationPermDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "updateDomain")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "_type", ignore = true)
    void updateDomain(OperationPermDTO operationPermDTO, @MappingTarget OperationPerm operationPerm);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    AttributePermDTO toDtoOnlyId(AttributePerm attributePerm);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDto")
    @Mapping(target = "_type", ignore = true)
    AttributePermDTO toDto(AttributePerm attributePerm);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModel")
    @Mapping(target = "_type", ignore = true)
    AttributePermDTO toDtoWithModel(AttributePerm attributePerm);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModelAndCollection")
    @Mapping(target = "_type", ignore = true)
    AttributePermDTO toDtoWithModelAndCollection(AttributePerm attributePerm);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomainOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    AttributePerm toDomainOnlyId(AttributePermDTO attributePermDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomain")
    @Mapping(target = "_type", ignore = true)
    AttributePerm toDomain(AttributePermDTO attributePermDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "updateDomain")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "_type", ignore = true)
    void updateDomain(AttributePermDTO attributePermDTO, @MappingTarget AttributePerm attributePerm);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoOnlyId")
    default PermissionDTO toDtoOnlyId(Permission permission) {
        if (permission != null && permission.get_type() != null) {
            switch(permission.get_type()) {
                case OP:
                    return toDtoOnlyId(permission.asOperationPerm());
                case ATTR:
                    return toDtoOnlyId(permission.asAttributePerm());
            }
        }
        return null;
    }

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDto")
    default PermissionDTO toDto(Permission permission) {
        if (permission != null && permission.get_type() != null) {
            switch(permission.get_type()) {
                case OP:
                    return toDto(permission.asOperationPerm());
                case ATTR:
                    return toDto(permission.asAttributePerm());
            }
        }
        return null;
    }

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModel")
    default PermissionDTO toDtoWithModel(Permission permission) {
        if (permission != null && permission.get_type() != null) {
            switch(permission.get_type()) {
                case OP:
                    return toDtoWithModel(permission.asOperationPerm());
                case ATTR:
                    return toDtoWithModel(permission.asAttributePerm());
            }
        }
        return null;
    }

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModelAndCollection")
    default PermissionDTO toDtoWithModelAndCollection(Permission permission) {
        if (permission != null && permission.get_type() != null) {
            switch(permission.get_type()) {
                case OP:
                    return toDtoWithModelAndCollection(permission.asOperationPerm());
                case ATTR:
                    return toDtoWithModelAndCollection(permission.asAttributePerm());
            }
        }
        return null;
    }

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomainOnlyId")
    default Permission toDomainOnlyId(PermissionDTO permissionDTO) {
        if (permissionDTO != null && permissionDTO.get_type() != null) {
            switch(permissionDTO.get_type()) {
                case OP:
                    return toDomainOnlyId(permissionDTO.asOperationPermDTO());
                case ATTR:
                    return toDomainOnlyId(permissionDTO.asAttributePermDTO());
            }
        }
        return null;
    }

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomain")
    default Permission toDomain(PermissionDTO permissionDTO) {
        if (permissionDTO != null && permissionDTO.get_type() != null) {
            switch(permissionDTO.get_type()) {
                case OP:
                    return toDomain(permissionDTO.asOperationPermDTO());
                case ATTR:
                    return toDomain(permissionDTO.asAttributePermDTO());
            }
        }
        return null;
    }

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "updateDomain")
    default void updateDomain(PermissionDTO permissionDTO, @MappingTarget Permission permission) {
        if (permissionDTO != null && permissionDTO.get_type() != null) {
            switch(permissionDTO.get_type()) {
                case OP:
                    updateDomain(permissionDTO.asOperationPermDTO(), permission.asOperationPerm());
                    break;
                case ATTR:
                    updateDomain(permissionDTO.asAttributePermDTO(), permission.asAttributePerm());
                    break;
            }
        }
    }
}
