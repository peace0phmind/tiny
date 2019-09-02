package cn.know.act.tiny.service.mapper;

import cn.know.act.tiny.domain.TreeTest;
import cn.know.act.tiny.service.dto.TreeTestDTO;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import org.mapstruct.Named;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.Mapper;
import cn.know.act.proton.core.service.IOptionalMapper;
import cn.know.act.tiny.service.mapper.item.TreeItemMapper;
import cn.know.act.proton.system.service.mapper.UserMapper;
import org.mapstruct.CollectionMappingStrategy;

@Mapper(componentModel = "spring", implementationName = "Tny<CLASS_NAME>Impl", uses = { IOptionalMapper.class, TestTypeMapper.class, TreeItemMapper.class, UserMapper.class }, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface TreeTestMapper {

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    TreeTestDTO toDtoOnlyId(TreeTest treeTest);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDto")
    @Mapping(target = "testType", qualifiedByName = "toDtoOnlyId")
    @Mapping(target = "treeTestItems", ignore = true)
    @Mapping(target = "_parent", qualifiedByName = "toDtoOnlyId")
    @Mapping(target = "_children", qualifiedByName = "toDto")
    @Mapping(target = "creator", qualifiedByName = "toDtoOnlyId")
    @Mapping(target = "modifier", qualifiedByName = "toDtoOnlyId")
    TreeTestDTO toDto(TreeTest treeTest);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModel")
    @Mapping(target = "testType", qualifiedByName = "toDto")
    @Mapping(target = "treeTestItems", ignore = true)
    @Mapping(target = "_parent", qualifiedByName = "toDto")
    @Mapping(target = "_children", ignore = true)
    @Mapping(target = "creator", qualifiedByName = "toDto")
    @Mapping(target = "modifier", qualifiedByName = "toDto")
    TreeTestDTO toDtoWithModel(TreeTest treeTest);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModelAndCollection")
    @Mapping(target = "testType", qualifiedByName = "toDto")
    @Mapping(target = "treeTestItems", qualifiedByName = "toDto")
    @Mapping(target = "_parent", qualifiedByName = "toDto")
    @Mapping(target = "_children", qualifiedByName = "toDto")
    @Mapping(target = "creator", qualifiedByName = "toDto")
    @Mapping(target = "modifier", qualifiedByName = "toDto")
    TreeTestDTO toDtoWithModelAndCollection(TreeTest treeTest);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomainOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    TreeTest toDomainOnlyId(TreeTestDTO treeTestDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomain")
    @Mapping(target = "testType", qualifiedByName = "toDomainOnlyId")
    @Mapping(target = "treeTestItems", ignore = true)
    @Mapping(target = "_parent", qualifiedByName = "toDomainOnlyId")
    @Mapping(target = "_children", ignore = true)
    @Mapping(target = "_highlight", ignore = true)
    @Mapping(target = "_checked", ignore = true)
    @Mapping(target = "_leaf", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "modifier", ignore = true)
    @Mapping(target = "deletedDate", ignore = true)
    @Mapping(target = "_tnIdx", ignore = true)
    TreeTest toDomain(TreeTestDTO treeTestDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "updateDomain")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "testType", qualifiedByName = "toDomainOnlyId")
    @Mapping(target = "treeTestItems", ignore = true)
    @Mapping(target = "_parent", qualifiedByName = "toDomainOnlyId")
    @Mapping(target = "_children", ignore = true)
    @Mapping(target = "_highlight", ignore = true)
    @Mapping(target = "_checked", ignore = true)
    @Mapping(target = "_leaf", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "modifier", ignore = true)
    @Mapping(target = "deletedDate", ignore = true)
    @Mapping(target = "_tnIdx", ignore = true)
    void updateDomain(TreeTestDTO treeTestDTO, @MappingTarget TreeTest treeTest);
}
