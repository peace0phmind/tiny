package cn.know.act.proton.system.service.mapper;

import cn.know.act.proton.system.domain.User;
import cn.know.act.proton.system.service.dto.UserDTO;
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

@Mapper(componentModel = "spring", implementationName = "Sys<CLASS_NAME>Impl", uses = { DepartmentMapper.class, IOptionalMapper.class, RoleMapper.class }, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface UserMapper {

    @Named(value = "toDtoWithModelAndRolesPermission")
    @Mapping(target = "department", qualifiedByName = "toDto")
    @Mapping(target = "roles", qualifiedByName = "toPermissionsDetails")
    UserDTO toDtoWithModelAndRolesPermission(User user);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    UserDTO toDtoOnlyId(User user);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDto")
    @Mapping(target = "department", qualifiedByName = "toDtoOnlyId")
    @Mapping(target = "roles", ignore = true)
    UserDTO toDto(User user);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModel")
    @Mapping(target = "department", qualifiedByName = "toDto")
    @Mapping(target = "roles", ignore = true)
    UserDTO toDtoWithModel(User user);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModelAndCollection")
    @Mapping(target = "department", qualifiedByName = "toDto")
    @Mapping(target = "roles", qualifiedByName = "toDto")
    UserDTO toDtoWithModelAndCollection(User user);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomainOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    User toDomainOnlyId(UserDTO userDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomain")
    @Mapping(target = "department", qualifiedByName = "toDomainOnlyId")
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "accountNonExpired", ignore = true)
    @Mapping(target = "credentialsNonExpired", ignore = true)
    @Mapping(target = "accountNonLocked", ignore = true)
    User toDomain(UserDTO userDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "updateDomain")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "department", qualifiedByName = "toDomainOnlyId")
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "accountNonExpired", ignore = true)
    @Mapping(target = "credentialsNonExpired", ignore = true)
    @Mapping(target = "accountNonLocked", ignore = true)
    void updateDomain(UserDTO userDTO, @MappingTarget User user);
}
