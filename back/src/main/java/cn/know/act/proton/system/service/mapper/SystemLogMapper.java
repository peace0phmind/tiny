package cn.know.act.proton.system.service.mapper;

import cn.know.act.proton.system.domain.SystemLog;
import cn.know.act.proton.system.service.dto.SystemLogDTO;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import org.mapstruct.Named;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.Mapper;
import cn.know.act.proton.core.service.IOptionalMapper;
import cn.know.act.proton.core.syslog.SysLogEvent;

@Mapper(componentModel = "spring", implementationName = "Sys<CLASS_NAME>Impl", uses = { IOptionalMapper.class })
public interface SystemLogMapper {

    @Named(value = "fromEvent")
    SystemLog fromEvent(SysLogEvent sysLogEvent);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    SystemLogDTO toDtoOnlyId(SystemLog systemLog);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDto")
    SystemLogDTO toDto(SystemLog systemLog);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModel")
    SystemLogDTO toDtoWithModel(SystemLog systemLog);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModelAndCollection")
    SystemLogDTO toDtoWithModelAndCollection(SystemLog systemLog);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomainOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    SystemLog toDomainOnlyId(SystemLogDTO systemLogDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomain")
    SystemLog toDomain(SystemLogDTO systemLogDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "updateDomain")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDomain(SystemLogDTO systemLogDTO, @MappingTarget SystemLog systemLog);
}
