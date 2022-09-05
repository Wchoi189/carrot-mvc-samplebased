package com.springapp.mvc.board.config;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import javax.sql.DataSource;

@Configuration
@Import(WebMvcConfig.class)
@MapperScan("com.springapp.mvc.board.mapper")
@PropertySource("classpath:db.properties")
public class AppContext {

    private static final Logger log = LoggerFactory.getLogger(AppContext.class);

    //db.properties
    @Value("${db.driver}")
    String driver;
    @Value("${db.url}")
    String url;
    @Value("${db.username}")
    String username;
    @Value("${db.password}")
    String password;

    @Bean("data")
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDefaultAutoCommit(false);
        return dataSource;

    }
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        return sqlSessionFactory.getObject();
    }

    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        //1 Megabyte = 1,000,000 bytes * 5 (MAX UPLOAD SIZE === 5MB)
        multipartResolver.setMaxUploadSize(5 * 1024 * 1024);
        return multipartResolver;
    }

}
