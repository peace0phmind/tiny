package cn.know.act.proton.system.service.mapper;

import cn.know.act.proton.system.domain.AuditEventLog;
import cn.know.act.proton.system.service.dto.AuditEventLogDTO;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import org.mapstruct.Named;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.Mapper;
import cn.know.act.proton.core.service.IOptionalMapper;

@Mapper(componentModel = "spring", implementationName = "Sys<CLASS_NAME>Impl", uses = { IOptionalMapper.class })
public interface AuditEventLogMapper {

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    AuditEventLogDTO toDtoOnlyId(AuditEventLog auditEventLog);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDto")
    AuditEventLogDTO toDto(AuditEventLog auditEventLog);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModel")
    AuditEventLogDTO toDtoWithModel(AuditEventLog auditEventLog);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDtoWithModelAndCollection")
    AuditEventLogDTO toDtoWithModelAndCollection(AuditEventLog auditEventLog);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomainOnlyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    AuditEventLog toDomainOnlyId(AuditEventLogDTO auditEventLogDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "toDomain")
    AuditEventLog toDomain(AuditEventLogDTO auditEventLogDTO);

    @Generated(IRW.CODE_GENERATOR)
    @Named(value = "updateDomain")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDomain(AuditEventLogDTO auditEventLogDTO, @MappingTarget AuditEventLog auditEventLog);
}
