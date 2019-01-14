package br.com.iperonprev.security;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class MvcConfig  extends WebMvcConfigurerAdapter{

	 @Bean(name = "dataSource")
	 public DriverManagerDataSource dataSource() {
	     DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	     driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	     driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/iperonprev");
	     driverManagerDataSource.setUsername("root");
	     driverManagerDataSource.setPassword("123");
	     return driverManagerDataSource;
	 }
	 
	 @Bean
	 public InternalResourceViewResolver viewResolver() {
	  InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	  resolver.setPrefix("/paginas/");
	  resolver.setSuffix(".xhtml");
	  return resolver;
	 }     
}
