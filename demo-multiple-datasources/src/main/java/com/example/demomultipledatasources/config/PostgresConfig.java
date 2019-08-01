package com.example.demomultipledatasources.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "personaEntityManagerFactory", transactionManagerRef = "personaTransactionManager", basePackages = {
		"com.example.demomultipledatasources.persona.repo" })
public class PostgresConfig {

	@Autowired
	private Environment env;

	@Bean(name = "personaDataSource")
	public DataSource comercioDatasource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getProperty("db2.datasource.url"));
		dataSource.setUsername(env.getProperty("db2.datasource.username"));
		dataSource.setPassword(env.getProperty("db2.datasource.password"));
		dataSource.setDriverClassName(env.getProperty("db2.datasource.driverClassName"));

		return dataSource;
	}

	@Bean(name = "personaEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(comercioDatasource());
		em.setPackagesToScan("com.example.demomultipledatasources.persona.model");

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);

		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.show-sql", env.getProperty("db2.jpa.show-sql"));
		properties.put("hibernate.dialect", env.getProperty("db2.jpa.database-platform"));

		em.setJpaPropertyMap(properties);

		return em;

	}

	@Bean(name = "personaTransactionManager")
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

		return transactionManager;
	}

}
