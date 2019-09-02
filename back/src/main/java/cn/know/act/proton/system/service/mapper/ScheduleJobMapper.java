package cn.know.act.proton.system.service.mapper;

import cn.know.act.proton.system.domain.ScheduleJob;
import cn.know.act.proton.system.service.dto.ScheduleJobDTO;
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

@Mapper(componentModel = "spring", implementationName = "Sys<CLASS_NAME>Impl", uses = { IOptionalMapper.class, ScheduleJobLogMapper.class, UserMapper.class }, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface ScheduleJobMapper {

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    ScheduleJobDTO toDtoOnlyId(ScheduleJob scheduleJob);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDto")
    @Mapping(target = "logs", ignore = true)
    @Mapping(target = "creator", qualifiedByName = "toDtoOnlyId")
    @Mapping(target = "modifier", qualifiedByName = "toDtoOnlyId")
    ScheduleJobDTO toDto(ScheduleJob scheduleJob);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModel")
    @Mapping(target = "logs", ignore = true)
    @Mapping(target = "creator", qualifiedByName = "toDto")
    @Mapping(target = "modifier", qualifiedByName = "toDto")
    ScheduleJobDTO toDtoWithModel(ScheduleJob scheduleJob);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModelAndCollection")
    @Mapping(target = "logs", qualifiedByName = "toDto")
    @Mapping(target = "creator", qualifiedByName = "toDto")
    @Mapping(target = "modifier", qualifiedByName = "toDto")
    ScheduleJobDTO toDtoWithModelAndCollection(ScheduleJob scheduleJob);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomainOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    ScheduleJob toDomainOnlyId(ScheduleJobDTO scheduleJobDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomain")
    @Mapping(target = "logs", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "modifier", ignore = true)
    ScheduleJob toDomain(ScheduleJobDTO scheduleJobDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "updateDomain")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "logs", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "modifier", ignore = true)
    void updateDomain(ScheduleJobDTO scheduleJobDTO, @MappingTarget ScheduleJob scheduleJob);
}
