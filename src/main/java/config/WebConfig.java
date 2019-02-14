package config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.io.IOException;

/**
 * @Description
 * @auther Mr.DayDream
 * @create 2019-02-13 16:12
 * @deprecated 相当于xml方式的mvc配置文件
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "controller")
public class WebConfig extends WebMvcConfigurerAdapter {
    /**
     *
     * 配置jsp视图解析器
     *
     * */
    @Bean
    public ViewResolver internalResourceViewResolver(){
        InternalResourceViewResolver resolver=new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setOrder(1);
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    /**
     *
     * 将freemarker.properties装载进来
     *
     * */
    @Bean
    public PropertiesFactoryBean propertiesFactoryBean(){
        PropertiesFactoryBean propertiesFactoryBean=new PropertiesFactoryBean();
        propertiesFactoryBean.setLocations(new PathMatchingResourcePatternResolver().getResource("classpath:freeMarkerConfig.properties"));
        return propertiesFactoryBean;
    }
    /**
     *
     * 初始化FreeMarker模板
     *
     * */
    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(PropertiesFactoryBean propertiesFactoryBean) throws IOException {
        FreeMarkerConfigurer freeMarkerConfigurer=new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/");
        freeMarkerConfigurer.setFreemarkerSettings(propertiesFactoryBean.getObject());
        return freeMarkerConfigurer;
    }
    /**
     *
     * 配置FreeMarker视图解析器
     *
     * */
    @Bean
    public ViewResolver freeMarkerViewResolver(){
        FreeMarkerViewResolver freeMarkerViewResolver=new FreeMarkerViewResolver();
        freeMarkerViewResolver.setExposeRequestAttributes(true);
        freeMarkerViewResolver.setExposeSessionAttributes(true);
        freeMarkerViewResolver.setViewClass(FreeMarkerView.class);
        freeMarkerViewResolver.setSuffix(".html");
        freeMarkerViewResolver.setOrder(0);
        return freeMarkerViewResolver;
    }

    /**
     *
     * 配置静态资源的访问(这样配置以后，就可以访问到WEB-INF下的静态资源)
     *
     * */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resource/**").addResourceLocations("/WEB-INF/static/");
    }

    /**
     *
     * 配置静态资源的访问(配置这个以后，可以直接访问web根目录下的静态资源[也就是webapp下的静态资源])
     *
     * */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     *
     * 设置项目默认访问页面
     *
     * */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    /**
     *
     * 配置上传文件
     * */
    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
    }
}
