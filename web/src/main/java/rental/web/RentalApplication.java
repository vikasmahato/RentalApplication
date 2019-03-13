package rental.web;

import constants.ApplicationConstants;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import rental.factory.AccountingBackendFactory;
import rental.factory.BackendFactory;
import rental.factory.MicrosoftDynamicsBackendFactory;
import rental.factory.QuickbooksBackendFactory;

import javax.validation.constraints.NotNull;
import java.util.Collections;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"rental"})
@EntityScan(basePackages = {"rental"})
@ComponentScan(basePackages = {"rental"})
public class RentalApplication {

    private final Environment springEnvironment;

    private final AccountingBackendFactory accountingBackendFactory;

    private static final Logger logger = Logger.getLogger(HomeController.class);

    public RentalApplication(Environment springEnvironment, AccountingBackendFactory accountingBackendFactory) {
        this.springEnvironment = springEnvironment;
        this.accountingBackendFactory = accountingBackendFactory;
    }

    public static void main(String[] args) {
        SpringApplication.run(RentalApplication.class, args);
    }

    @Bean(name = "accountingBackendFactory")
    public AccountingBackendFactory accountingBackendFactory(@NotNull ApplicationContext context) {
        String accountingBackend = springEnvironment.getProperty("accounting_backend");

        switch (accountingBackend) {
            case ApplicationConstants.ACCOUNTING_BACKEND_QUICKBOOKS:
                logger.info("Quickbooks backend selected");
                return context.getBean(QuickbooksBackendFactory.class);

            case ApplicationConstants.ACCOUNTING_BACKEND_MICROSOFT_DYNAMICS:
                logger.info("Microsoft Dynamics backend selected");
                return context.getBean(MicrosoftDynamicsBackendFactory.class);

            default:
                logger.warn("No Accounting backend specified");
                return context.getBean(BackendFactory.class);

        }
    }

    // Fix the CORS errors
    @Bean
    public FilterRegistrationBean simpleCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // *** URL below needs to match the Vue client URL and port ***
        config.setAllowedOrigins(Collections.singletonList("http://localhost:8081"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
