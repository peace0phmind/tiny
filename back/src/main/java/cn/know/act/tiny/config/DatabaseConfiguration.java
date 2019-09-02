package cn.know.act.tiny.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableProtonJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableProtonJpaRepositories(basePackages = { "cn.know.act.tiny.repository.jpa" })
public class DatabaseConfiguration {
}
