package my.com.etiqa.maybank;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        basePackages = "my.com.etiqa.maybank",
        entityManagerFactoryRef = "adminEntityManager",
        transactionManagerRef = "adminTransactionManager"
)
public class AdminDBConfig {

    @Autowired
    private Environment env;

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource")
    public HikariDataSource adminDataSource() {
        HikariDataSource ds =  DataSourceBuilder.create().type(HikariDataSource.class).build();
        ds.setRegisterMbeans(true);
    	ds.setIdleTimeout(10000);
    	ds.setMinimumIdle(1);
    	ds.setAllowPoolSuspension(true);
        return ds;
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean adminEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(adminDataSource());
        em.setPackagesToScan("my.com.etiqa.maybank");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Primary
    @Bean
    public PlatformTransactionManager adminTransactionManager() {
        JpaTransactionManager transactionManager  = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(adminEntityManager().getObject());
        return transactionManager;
    }

}
