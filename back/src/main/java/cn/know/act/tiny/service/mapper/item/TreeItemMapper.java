package cn.know.act.tiny.service.mapper.item;

import cn.know.act.tiny.domain.item.TreeItem;
import cn.know.act.tiny.service.dto.item.TreeItemDTO;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import org.mapstruct.Named;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.Mapper;
import cn.know.act.proton.core.service.IOptionalMapper;
import cn.know.act.tiny.service.mapper.TreeTestMapper;
import org.mapstruct.CollectionMappingStrategy;

@Mapper(componentModel = "spring", implementationName = "Tny<CLASS_NAME>Impl", uses = { IOptionalMapper.class, TreeTestMapper.class }, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface TreeItemMapper {

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    TreeItemDTO toDtoOnlyId(TreeItem treeItem);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDto")
    @Mapping(target = "tests", ignore = true)
    TreeItemDTO toDto(TreeItem treeItem);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModel")
    @Mapping(target = "tests", ignore = true)
    TreeItemDTO toDtoWithModel(TreeItem treeItem);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModelAndCollection")
    @Mapping(target = "tests", qualifiedByName = "toDto")
    TreeItemDTO toDtoWithModelAndCollection(TreeItem treeItem);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomainOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    TreeItem toDomainOnlyId(TreeItemDTO treeItemDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomain")
    @Mapping(target = "tests", ignore = true)
    TreeItem toDomain(TreeItemDTO treeItemDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "updateDomain")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "tests", ignore = true)
    void updateDomain(TreeItemDTO treeItemDTO, @MappingTarget TreeItem treeItem);
}
