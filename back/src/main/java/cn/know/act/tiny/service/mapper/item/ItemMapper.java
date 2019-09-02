package cn.know.act.tiny.service.mapper.item;

import cn.know.act.tiny.domain.item.Item;
import cn.know.act.tiny.service.dto.item.ItemDTO;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import org.mapstruct.Named;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.Mapper;
import cn.know.act.proton.core.service.IOptionalMapper;
import cn.know.act.tiny.service.mapper.TestMapper;

@Mapper(componentModel = "spring", implementationName = "Tny<CLASS_NAME>Impl", uses = { IOptionalMapper.class, TestMapper.class })
public interface ItemMapper {

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    ItemDTO toDtoOnlyId(Item item);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDto")
    @Mapping(target = "test", qualifiedByName = "toDtoOnlyId")
    ItemDTO toDto(Item item);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModel")
    @Mapping(target = "test", qualifiedByName = "toDto")
    ItemDTO toDtoWithModel(Item item);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModelAndCollection")
    @Mapping(target = "test", qualifiedByName = "toDto")
    ItemDTO toDtoWithModelAndCollection(Item item);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomainOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    Item toDomainOnlyId(ItemDTO itemDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomain")
    @Mapping(target = "test", qualifiedByName = "toDomainOnlyId")
    Item toDomain(ItemDTO itemDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "updateDomain")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "test", qualifiedByName = "toDomainOnlyId")
    void updateDomain(ItemDTO itemDTO, @MappingTarget Item item);
}
