package cn.know.act.proton.config;

import cn.know.act.proton.config.security.SecurityUtils;
import cn.know.act.proton.system.domain.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<User> {
    @Override
    public Optional<User> getCurrentAuditor() {
        return Optional.ofNullable(SecurityUtils.getCurrentUser().orElse(null));
    }
}
