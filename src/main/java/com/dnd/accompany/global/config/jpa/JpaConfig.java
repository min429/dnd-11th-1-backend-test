package com.dnd.accompany.global.config.jpa;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManagerFactory;

@EnableJpaAuditing
@EnableJpaRepositories(
	basePackages = {
		"com.dnd"
	},
	entityManagerFactoryRef = "accompanyEntityManagerFactory",
	transactionManagerRef = "accompanyTransactionManager"
)
@Configuration
public class JpaConfig {
	@Bean
	@ConfigurationProperties(prefix = "datasource.db")
	public DataSource accompanyDataSource() {
		return new HikariDataSource();
	}

	@Primary
	@Bean
	@DependsOn("accompanyDataSource")
	public DataSource accompanyLazyDataSource(
		@Qualifier("accompanyDataSource") DataSource dbDataSource
	) {
		return new LazyConnectionDataSourceProxy(dbDataSource);
	}

	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean accompanyEntityManagerFactory(
		EntityManagerFactoryBuilder entityManagerFactoryBuilder,
		@Qualifier("accompanyLazyDataSource") DataSource lazyDataSource
	) {
		return entityManagerFactoryBuilder
			.dataSource(lazyDataSource)
			.packages("com.dnd")
			.properties(jpaProperties())
			.persistenceUnit("dnd")
			.build();
	}

	@Bean
	public PlatformTransactionManager accompanyTransactionManager(
		@Qualifier("accompanyEntityManagerFactory") EntityManagerFactory accompanyEntityManagerFactory
	) {
		return new JpaTransactionManager(accompanyEntityManagerFactory);
	}

	private Map<String, Object> jpaProperties() {
		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.physical_naming_strategy", CamelCaseToUnderscoresNamingStrategy.class.getName());

		return properties;
	}
}
