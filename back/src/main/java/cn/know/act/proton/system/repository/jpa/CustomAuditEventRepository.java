package cn.know.act.proton.system.repository.jpa;

import cn.know.act.proton.system.domain.AuditEventLog;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * An implementation of Spring Boot's {@link AuditEventRepository}.
 */
@Repository
public class CustomAuditEventRepository implements AuditEventRepository {

    private static final String AUTHORIZATION_FAILURE = "AUTHENTICATION_FAILURE";

    private final AuditEventLogJpaRepository auditEventLogJpaRepository;

    public CustomAuditEventRepository(AuditEventLogJpaRepository auditEventLogJpaRepository) {
        this.auditEventLogJpaRepository = auditEventLogJpaRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void add(AuditEvent event) {
        WebAuthenticationDetails details = (WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        AuditEventLog persistentAuditEvent = new AuditEventLog();
        persistentAuditEvent.setUsername(event.getPrincipal());
        persistentAuditEvent.setSuccess(AUTHORIZATION_FAILURE.equals(event.getType()) ? false : true);
        ZoneId zone = ZoneId.systemDefault();
        persistentAuditEvent.setLoginTime(LocalDateTime.ofInstant(event.getTimestamp(), zone));
        persistentAuditEvent.setIp(details == null ? "" : details.getRemoteAddress());
        auditEventLogJpaRepository.save(persistentAuditEvent);
    }

    @Override
    public List<AuditEvent> find(String principal, Instant after, String type) {
        return null;
    }

}
