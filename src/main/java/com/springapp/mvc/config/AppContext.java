package com.springapp.mvc.config;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.springapp.mvc.mapper")
public class AppContext {
    @Bean("data")
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/interview_assignment_db");
        dataSource.setUsername("user");
        dataSource.setPassword("pass");
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
        //1 Megabyte = 1,000,000 bytes * 5 (MAX UPLOAD SIZE === 5MB)
        multipartResolver.setMaxUploadSize(5000000);
        return multipartResolver;
    }
    @Bean
    public String uploadPath (){
        String path = "/upload";
        return path;
    }


}
