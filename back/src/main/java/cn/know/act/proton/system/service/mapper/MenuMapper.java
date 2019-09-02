package cn.know.act.proton.system.service.mapper;

import cn.know.act.proton.system.domain.Menu;
import cn.know.act.proton.system.service.dto.MenuDTO;
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

@Mapper(componentModel = "spring", implementationName = "Sys<CLASS_NAME>Impl", uses = { IOptionalMapper.class, RoleMapper.class }, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface MenuMapper {

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    MenuDTO toDtoOnlyId(Menu menu);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDto")
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "_parent", qualifiedByName = "toDtoOnlyId")
    @Mapping(target = "_children", qualifiedByName = "toDto")
    MenuDTO toDto(Menu menu);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModel")
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "_parent", qualifiedByName = "toDto")
    @Mapping(target = "_children", ignore = true)
    MenuDTO toDtoWithModel(Menu menu);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModelAndCollection")
    @Mapping(target = "roles", qualifiedByName = "toDto")
    @Mapping(target = "_parent", qualifiedByName = "toDto")
    @Mapping(target = "_children", qualifiedByName = "toDto")
    MenuDTO toDtoWithModelAndCollection(Menu menu);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomainOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    Menu toDomainOnlyId(MenuDTO menuDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomain")
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "_parent", qualifiedByName = "toDomainOnlyId")
    @Mapping(target = "_children", ignore = true)
    @Mapping(target = "_highlight", ignore = true)
    @Mapping(target = "_checked", ignore = true)
    @Mapping(target = "_leaf", ignore = true)
    @Mapping(target = "_tnIdx", ignore = true)
    Menu toDomain(MenuDTO menuDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "updateDomain")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "_parent", qualifiedByName = "toDomainOnlyId")
    @Mapping(target = "_children", ignore = true)
    @Mapping(target = "_highlight", ignore = true)
    @Mapping(target = "_checked", ignore = true)
    @Mapping(target = "_leaf", ignore = true)
    @Mapping(target = "_tnIdx", ignore = true)
    void updateDomain(MenuDTO menuDTO, @MappingTarget Menu menu);
}
