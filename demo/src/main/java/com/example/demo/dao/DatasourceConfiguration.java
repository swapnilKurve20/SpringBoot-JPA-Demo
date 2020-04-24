package com.example.demo.dao;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

/**
 * 
 * @author sjogade
 * Description - Datasource configuration
 */
@Configuration
public class DatasourceConfiguration implements DaoCommon {
	
	@Bean
	public DriverManagerDataSource getDataSource() {		
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName(driverClassName);
		ds.setUrl(url);	
		ds.setUsername(username);
		ds.setPassword(password);
		return ds;
	}
	
	@Bean(name="entityManagerFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setPackagesToScan("com.example.demo.*");
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        hibernateProperties.put("hibernate.show_sql", "true");
        hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }
    
    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setRollbackOnCommitFailure(true);
        txManager.setSessionFactory(sessionFactory().getObject());
        return txManager;
    }


}
