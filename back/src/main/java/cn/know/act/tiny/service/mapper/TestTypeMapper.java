package cn.know.act.tiny.service.mapper;

import cn.know.act.tiny.domain.TestType;
import cn.know.act.tiny.service.dto.TestTypeDTO;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import org.mapstruct.Named;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.Mapper;
import cn.know.act.proton.core.service.IOptionalMapper;

@Mapper(componentModel = "spring", implementationName = "Tny<CLASS_NAME>Impl", uses = { IOptionalMapper.class })
public interface TestTypeMapper {

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    TestTypeDTO toDtoOnlyId(TestType testType);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDto")
    TestTypeDTO toDto(TestType testType);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModel")
    TestTypeDTO toDtoWithModel(TestType testType);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModelAndCollection")
    TestTypeDTO toDtoWithModelAndCollection(TestType testType);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomainOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    TestType toDomainOnlyId(TestTypeDTO testTypeDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomain")
    TestType toDomain(TestTypeDTO testTypeDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "updateDomain")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDomain(TestTypeDTO testTypeDTO, @MappingTarget TestType testType);
}
