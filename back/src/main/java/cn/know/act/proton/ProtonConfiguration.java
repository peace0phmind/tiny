package cn.know.act.proton;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableProtonJpaRepositories;

@ComponentScan(basePackages = "cn.know.act.proton")
@EntityScan(basePackages = "cn.know.act.proton.system.domain")
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@EnableProtonJpaRepositories(basePackages = {"cn.know.act.proton.system.repository.jpa"})
@EnableConfigurationProperties({LiquibaseProperties.class})
public class ProtonConfiguration {
}
