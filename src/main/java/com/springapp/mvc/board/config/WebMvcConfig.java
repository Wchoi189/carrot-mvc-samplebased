package com.springapp.mvc.board.config;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebMvc
@ComponentScan("com.springapp.mvc.board")

public class WebMvcConfig implements WebMvcConfigurer {

  
    private ApplicationContext applicationContext;
    // + JSP View Resolver
    @Bean
    public InternalResourceViewResolver jspViewResolver(){
        final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/jsp/");
        viewResolver.setViewNames("*.jsp");
        return viewResolver;
    }

//    * HTML 뷰만 사용
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/html/templates/");
//        templateResolver.setPrefix("/WEB-INF/views/html/fragments");
//        templateResolver.setPrefix("/WEB-INF/views/html/layouts");
//        templateResolver.setPrefix("/WEB-INF/views/html/");

        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("utf-8");
        templateResolver.setCacheable(false);
        return templateResolver;
    }
    // 타임리프 레이아웃 기능

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setDialect(new LayoutDialect());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    // 타임리크 뷰 기능 활성화 + 한글 깸 예방
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8"); // 한글 깨짐 방지
        registry.viewResolver(resolver);

    }
    // 정적 자원 위치 선언
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        WebMvcConfigurer.super.addResourceHandlers(registry);

        // Static Resources 설정
        registry.addResourceHandler("/", "/**","/resources/**")
                .addResourceLocations("/", "classpath:/static/","/resources/")
                .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));
    }
    @Bean
        public ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
            SpringTemplateEngine engine = new SpringTemplateEngine();
            engine.addDialect(new Java8TimeDialect());
            engine.setTemplateResolver(templateResolver);
            return engine;
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
    //to provide multipart resolver which can resolve files sent as multipart-request
    @Bean
    public MultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }


}