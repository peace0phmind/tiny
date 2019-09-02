package cn.know.act.proton.system.service.mapper;

import cn.know.act.proton.system.domain.Role;
import cn.know.act.proton.system.service.dto.RoleDTO;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import org.mapstruct.Named;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.Mapper;
import cn.know.act.proton.core.service.IOptionalMapper;
import org.mapstruct.CollectionMappingStrategy;

@Mapper(componentModel = "spring", implementationName = "Sys<CLASS_NAME>Impl", uses = { IOptionalMapper.class, MenuMapper.class, RolePermissionMapper.class, UserMapper.class }, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface RoleMapper {

    @Named(value = "toPermissionsDetails")
    @Mapping(target = "permissions", qualifiedByName = "toDtoWithModel")
    @Mapping(target = "menus", ignore = true)
    @Mapping(target = "users", ignore = true)
    RoleDTO toPermissionsDetails(Role role);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    RoleDTO toDtoOnlyId(Role role);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDto")
    @Mapping(target = "permissions", ignore = true)
    @Mapping(target = "menus", ignore = true)
    @Mapping(target = "users", ignore = true)
    RoleDTO toDto(Role role);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModel")
    @Mapping(target = "permissions", ignore = true)
    @Mapping(target = "menus", ignore = true)
    @Mapping(target = "users", ignore = true)
    RoleDTO toDtoWithModel(Role role);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModelAndCollection")
    @Mapping(target = "permissions", qualifiedByName = "toDto")
    @Mapping(target = "menus", qualifiedByName = "toDto")
    @Mapping(target = "users", qualifiedByName = "toDto")
    RoleDTO toDtoWithModelAndCollection(Role role);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomainOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    Role toDomainOnlyId(RoleDTO roleDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomain")
    @Mapping(target = "permissions", ignore = true)
    @Mapping(target = "menus", ignore = true)
    @Mapping(target = "users", ignore = true)
    Role toDomain(RoleDTO roleDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "updateDomain")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "permissions", ignore = true)
    @Mapping(target = "menus", ignore = true)
    @Mapping(target = "users", ignore = true)
    void updateDomain(RoleDTO roleDTO, @MappingTarget Role role);
}
