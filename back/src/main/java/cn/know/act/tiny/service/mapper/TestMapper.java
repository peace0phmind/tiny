package cn.know.act.tiny.service.mapper;

import cn.know.act.tiny.domain.Test;
import cn.know.act.tiny.service.dto.TestDTO;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import org.mapstruct.Named;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.Mapper;
import cn.know.act.proton.core.service.IOptionalMapper;
import cn.know.act.tiny.service.mapper.item.ItemMapper;
import cn.know.act.proton.system.service.mapper.UserMapper;
import org.mapstruct.CollectionMappingStrategy;

@Mapper(componentModel = "spring", implementationName = "Tny<CLASS_NAME>Impl", uses = { IOptionalMapper.class, ItemMapper.class, TestTypeMapper.class, UserMapper.class }, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface TestMapper {

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    TestDTO toDtoOnlyId(Test test);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDto")
    @Mapping(target = "templateType", qualifiedByName = "toDtoOnlyId")
    @Mapping(target = "items", ignore = true)
    @Mapping(target = "creator", qualifiedByName = "toDtoOnlyId")
    @Mapping(target = "modifier", qualifiedByName = "toDtoOnlyId")
    TestDTO toDto(Test test);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModel")
    @Mapping(target = "templateType", qualifiedByName = "toDto")
    @Mapping(target = "items", ignore = true)
    @Mapping(target = "creator", qualifiedByName = "toDto")
    @Mapping(target = "modifier", qualifiedByName = "toDto")
    TestDTO toDtoWithModel(Test test);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModelAndCollection")
    @Mapping(target = "templateType", qualifiedByName = "toDto")
    @Mapping(target = "items", qualifiedByName = "toDto")
    @Mapping(target = "creator", qualifiedByName = "toDto")
    @Mapping(target = "modifier", qualifiedByName = "toDto")
    TestDTO toDtoWithModelAndCollection(Test test);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomainOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    Test toDomainOnlyId(TestDTO testDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomain")
    @Mapping(target = "templateType", qualifiedByName = "toDomainOnlyId")
    @Mapping(target = "items", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "modifier", ignore = true)
    @Mapping(target = "deletedDate", ignore = true)
    Test toDomain(TestDTO testDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "updateDomain")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "templateType", qualifiedByName = "toDomainOnlyId")
    @Mapping(target = "items", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "modifier", ignore = true)
    @Mapping(target = "deletedDate", ignore = true)
    void updateDomain(TestDTO testDTO, @MappingTarget Test test);
}
