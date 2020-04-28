package com.example.demo.dao;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.demo.exceptions.CustomExceptionHandler;

/**
 * 
 * @author sjogade Description - Datasource configuration
 */
@Configuration
@EnableTransactionManagement
public class DatasourceConfiguration extends CustomExceptionHandler implements DaoCommon {

	private DriverManagerDataSource ds = null;
	private LocalSessionFactoryBean sessionFactory = null;
	private HibernateTransactionManager txManager = null;
	
	@Bean
	public DriverManagerDataSource getDataSource() {

		try {
			if (ds == null) {
				ds = new DriverManagerDataSource();
				ds.setDriverClassName(driverClassName);
				ds.setUrl(url);
				ds.setUsername(username);
				ds.setPassword(password);
			//	ds.getConnection().setAutoCommit(false);
			}
		} catch (Exception e) {
			logExcepton(e);
		}
		return ds;
	}

	@Bean(name = "entityManagerFactory")
	public LocalSessionFactoryBean sessionFactory() {

		if (sessionFactory == null) {
			try {
				sessionFactory = new LocalSessionFactoryBean();
				sessionFactory.setDataSource(getDataSource());
				sessionFactory.setPackagesToScan("com.example.demo.*");
				Properties hibernateProperties = new Properties();
				hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
				hibernateProperties.put("hibernate.show_sql", "true");
				hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
				sessionFactory.setHibernateProperties(hibernateProperties);
			} catch (Exception e) {
				logExcepton(e);
			}
		}
		return sessionFactory;
	}

	@Bean(name="hibernateTransactionManager")
	public HibernateTransactionManager transactionManager() {

		if (txManager == null) {
			try {
				txManager = new HibernateTransactionManager();
				txManager.setRollbackOnCommitFailure(true);
				txManager.setSessionFactory(sessionFactory().getObject());
			} catch (Exception e) {
				logExcepton(e);
			}
		}
		return txManager;
	}
}
