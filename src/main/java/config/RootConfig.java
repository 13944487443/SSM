package config;

import aop.TestAop;
import com.github.pagehelper.PageHelper;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

/**
 * @Description
 * @auther Mr.DayDream
 * @create 2019-02-13 16:47
 * @deprecated 相当于xml方式的application文件
 */
@Configuration
@ComponentScan(basePackages = {"service.impl","dao"})
@MapperScan(basePackages = "dao")
@EnableAspectJAutoProxy
public class RootConfig {

    /**
     *
     * 配置数据源,spring装载properties可以通过PropertiesFactoryBean【PropertiesFactoryBean主要用来装配单例的Properties】，也可以通过PropertiesLoaderUtils工具类
     *
     * */
    @Bean
    public DataSource dataSource() throws IOException, PropertyVetoException {
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        Properties properties=PropertiesLoaderUtils.loadProperties(new PathMatchingResourcePatternResolver().getResource("classpath:jdbc.properties"));
        dataSource.setDriverClass(properties.getProperty("jdbc.driver"));
        dataSource.setJdbcUrl(properties.getProperty("jdbc.url"));
        dataSource.setUser(properties.getProperty("jdbc.username"));
        dataSource.setPassword(properties.getProperty("jdbc.password"));
        dataSource.setInitialPoolSize(Integer.parseInt(properties.getProperty("jdbc.initialPoolSize")));
        dataSource.setMinPoolSize(Integer.parseInt(properties.getProperty("jdbc.minPoolSize")));
        dataSource.setMinPoolSize(Integer.parseInt(properties.getProperty("jdbc.maxPoolSize")));
        return dataSource;
    }
    /**
     *
     * 整合sqlSessionFactory
     *
     * */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource,PageHelper pageHelper) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*Mapper.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("entity");
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
        return sqlSessionFactoryBean.getObject();
    }

    /**
     *
     * 配置分页插件
     *
     * */
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper=new PageHelper();
        Properties properties= new Properties();
        properties.setProperty("helperDialect","true");
        properties.setProperty("reasonable","true");
        properties.setProperty("autoRuntimeDialect","true");
        pageHelper.setProperties(properties);
        return pageHelper;
    }

    @Bean
    public TestAop testAop(){
        return new TestAop();
    }
}
