package root.core;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@PropertySource("classpath:hibernate.properties")
@ComponentScan(basePackages = "root.db")
public class DBConfigurations {

    @Autowired
    private Environment env;

    private static final String[] packages = {
            "root.db.entity"
    };

    @Bean
    public DataSource h2DataSource() {
        return DataSourceBuilder.create()
                .driverClassName(env.getProperty("h2.driverClassName"))
                .url(env.getProperty("h2.url"))
                .username(env.getProperty("h2.username"))
                .password(env.getProperty("h2.password"))
                .build();
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(@Qualifier("h2DataSource") DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(packages);
        sessionFactory.setHibernateProperties(properties());
        return sessionFactory;
    }

    @Bean
    public Properties properties() {
        Properties hp = new Properties();
        hp.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));

        hp.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        hp.setProperty("hibernate.default_schema", env.getProperty("hibernate.default_schema"));

        hp.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        hp.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        hp.setProperty("hibernate.use_sql_comments", env.getProperty("hibernate.use_sql_comments"));

        hp.setProperty("hibernate.jdbc.batch_size", env.getProperty("hibernate.jdbc.batch_size"));
        hp.setProperty("hibernate.connection.pool_size", "5");

        return hp;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        transactionManager.setNestedTransactionAllowed(true);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(@Qualifier("h2DataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPersistenceUnitName("h2Unit");
        entityManagerFactoryBean.setPackagesToScan(packages);

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setJpaProperties(properties());

        return entityManagerFactoryBean;
    }

}