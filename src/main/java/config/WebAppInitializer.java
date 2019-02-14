package config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.util.Log4jConfigListener;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @Description
 * @auther Mr.DayDream
 * @create 2019-02-13 16:05
 * @deprecated web项目的初始文件，有了它就不用再单独实现WebApplicationInitializer接口了，因为他已经实现了，只要是实现了这个的类都可以用来代替web.xml
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     *
     * 设置application的配置类
     *
     * */
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    /**
     *
     * 设置mvc的配置类
     *
     * */
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     *
     * 配置项目默认请求路径,用来映射dispatcherServlet
     *
     * */
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     *
     * 给dispatcherServlet添加过滤器
     *
     * */
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter filter=new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return new Filter[]{filter};
    }
    /**
     *
     * 通过这个方法可以单独添加servlet、listener、filter
     *
     * */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setAttribute("log4jConfigLocation","classpath:log4j.properties");
        servletContext.addListener(Log4jConfigListener.class);
        super.onStartup(servletContext);
    }
}
