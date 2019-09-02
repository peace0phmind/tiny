package cn.know.act.proton.system.repository.jpa;

import cn.know.act.proton.core.syslog.SysLogEvent;
import cn.know.act.proton.core.syslog.SysLogRepository;
import cn.know.act.proton.system.service.mapper.SystemLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class SysLogDBRepository implements SysLogRepository {

    private static final Logger log = LoggerFactory.getLogger(SysLogDBRepository.class);

    private final SystemLogJpaRepository systemLogJpaRepository;

    private final SystemLogMapper systemLogMapper;

    public SysLogDBRepository(SystemLogJpaRepository systemLogJpaRepository, SystemLogMapper systemLogMapper) {
        log.warn("Use SysLogDBRepository");
        this.systemLogJpaRepository = systemLogJpaRepository;
        this.systemLogMapper = systemLogMapper;
    }

    @Override
    public void save(SysLogEvent sysLogEvent) {
        systemLogJpaRepository.save(systemLogMapper.fromEvent(sysLogEvent));
    }
}
