package cn.know.act.proton.system.service.mapper;

import cn.know.act.proton.system.domain.Department;
import cn.know.act.proton.system.service.dto.DepartmentDTO;
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

@Mapper(componentModel = "spring", implementationName = "Sys<CLASS_NAME>Impl", uses = { IOptionalMapper.class }, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface DepartmentMapper {

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    DepartmentDTO toDtoOnlyId(Department department);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDto")
    @Mapping(target = "_parent", qualifiedByName = "toDtoOnlyId")
    @Mapping(target = "_children", qualifiedByName = "toDto")
    DepartmentDTO toDto(Department department);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModel")
    @Mapping(target = "_parent", qualifiedByName = "toDto")
    @Mapping(target = "_children", ignore = true)
    DepartmentDTO toDtoWithModel(Department department);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModelAndCollection")
    @Mapping(target = "_parent", qualifiedByName = "toDto")
    @Mapping(target = "_children", qualifiedByName = "toDto")
    DepartmentDTO toDtoWithModelAndCollection(Department department);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomainOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    Department toDomainOnlyId(DepartmentDTO departmentDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomain")
    @Mapping(target = "_parent", qualifiedByName = "toDomainOnlyId")
    @Mapping(target = "_children", ignore = true)
    @Mapping(target = "_highlight", ignore = true)
    @Mapping(target = "_checked", ignore = true)
    @Mapping(target = "_leaf", ignore = true)
    @Mapping(target = "_tnIdx", ignore = true)
    Department toDomain(DepartmentDTO departmentDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "updateDomain")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "_parent", qualifiedByName = "toDomainOnlyId")
    @Mapping(target = "_children", ignore = true)
    @Mapping(target = "_highlight", ignore = true)
    @Mapping(target = "_checked", ignore = true)
    @Mapping(target = "_leaf", ignore = true)
    @Mapping(target = "_tnIdx", ignore = true)
    void updateDomain(DepartmentDTO departmentDTO, @MappingTarget Department department);
}
