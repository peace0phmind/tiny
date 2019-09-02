package cn.know.act.tiny;

import cn.know.act.proton.ProtonConfiguration;
import cn.know.act.proton.core.config.Constants;
import cn.know.act.proton.core.config.DefaultProfileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.StringUtils;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

@EnableScheduling
@EntityScan(basePackages = { "cn.know.act.tiny.domain" })
@Import(ProtonConfiguration.class)
@SpringBootApplication
public class Application implements InitializingBean {

    private static Logger log;

    private final Environment env;

    private static final String TIME_ZONE = "GMT+8";

    public Application(Environment env) {
        this.env = env;
    }

    public static void main(String[] args) {
        // set user.timezone to UTC, and create log from logger factory
        initTimeZone();
        log = LoggerFactory.getLogger(Application.class);
        SpringApplication app = new SpringApplication(Application.class);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        logApplicationStartup(env);
    }

    private static void initTimeZone() {
        System.setProperty("user.timezone", TIME_ZONE);
        try {
            System.setProperty("user.timezone.encode", URLEncoder.encode(TIME_ZONE, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("{}", e);
        }
    }

    private static void logApplicationStartup(Environment env) {
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        String serverPort = env.getProperty("server.port");
        String contextPath = env.getProperty("server.servlet.context-classPath");
        if (StringUtils.isEmpty(contextPath)) {
            contextPath = "/";
        }
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("The host name could not be determined, using `localhost` as fallback");
        }
        String userDir = System.getProperty("user.dir");
        log.info("\n----------------------------------------------------------\n\t" + "Application '{}' is running at '{}'! Access URLs:\n\t" + "Local: \t\t{}://localhost:{}{}\n\t" + "External: \t{}://{}:{}{}\n\t" + "Profile(s): \t{}\n----------------------------------------------------------", env.getProperty("spring.application.name"), userDir, protocol, serverPort, contextPath, protocol, hostAddress, serverPort, contextPath, env.getActiveProfiles());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        if (activeProfiles.contains(Constants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(Constants.SPRING_PROFILE_PRODUCTION)) {
            log.error("You have misconfigured your application! It should not run " + "with both the 'dev' and 'prod' profiles at the same time.");
        }
        if (activeProfiles.contains(Constants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(Constants.SPRING_PROFILE_CLOUD)) {
            log.error("You have misconfigured your application! It should not " + "run with both the 'dev' and 'cloud' profiles at the same time.");
        }
    }
}
