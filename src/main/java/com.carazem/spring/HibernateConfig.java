package com.carazem.spring;

import com.carazem.config.ConfigService;
import com.carazem.config.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.carazem")
public class HibernateConfig {

    @Autowired
    private ConfigService configService;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(configService.get(Keys.DB_DRIVER));
        dataSource.setUrl(configService.get(Keys.DB_URL));
        dataSource.setUsername(configService.get(Keys.DB_USER));
        dataSource.setPassword(configService.get(Keys.DB_PASSWORD));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactory.setDataSource(dataSource);

        entityManagerFactory.setPackagesToScan("com.carazem");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

        Properties additionalProperties = new Properties();
        additionalProperties.put("hibernate.show_sql", configService.get(Keys.HIBERNATE_SHOW_SQL));
        additionalProperties.put("hibernate.format_sql", configService.get(Keys.HIBERNATE_FORMAT_SQL));
        additionalProperties.put("hibernate.generate_statistics", configService.get(Keys.HIBERNATE_GENERATE_STATISTICS));
        additionalProperties.put("hibernate.hbm2ddl.auto", configService.get(Keys.HIBERNATE_HBM2DDL));
        entityManagerFactory.setJpaProperties(additionalProperties);

        return entityManagerFactory;
    }

    @Bean
    public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
