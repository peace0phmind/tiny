package cn.know.act.proton.system.service.mapper;

import cn.know.act.proton.system.domain.ScheduleJobLog;
import cn.know.act.proton.system.service.dto.ScheduleJobLogDTO;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import org.mapstruct.Named;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.Mapper;
import cn.know.act.proton.core.service.IOptionalMapper;

@Mapper(componentModel = "spring", implementationName = "Sys<CLASS_NAME>Impl", uses = { IOptionalMapper.class, ScheduleJobMapper.class })
public interface ScheduleJobLogMapper {

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    ScheduleJobLogDTO toDtoOnlyId(ScheduleJobLog scheduleJobLog);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDto")
    @Mapping(target = "job", qualifiedByName = "toDtoOnlyId")
    ScheduleJobLogDTO toDto(ScheduleJobLog scheduleJobLog);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModel")
    @Mapping(target = "job", qualifiedByName = "toDto")
    ScheduleJobLogDTO toDtoWithModel(ScheduleJobLog scheduleJobLog);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModelAndCollection")
    @Mapping(target = "job", qualifiedByName = "toDto")
    ScheduleJobLogDTO toDtoWithModelAndCollection(ScheduleJobLog scheduleJobLog);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomainOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    ScheduleJobLog toDomainOnlyId(ScheduleJobLogDTO scheduleJobLogDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomain")
    @Mapping(target = "job", qualifiedByName = "toDomainOnlyId")
    ScheduleJobLog toDomain(ScheduleJobLogDTO scheduleJobLogDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "updateDomain")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "job", qualifiedByName = "toDomainOnlyId")
    void updateDomain(ScheduleJobLogDTO scheduleJobLogDTO, @MappingTarget ScheduleJobLog scheduleJobLog);
}
