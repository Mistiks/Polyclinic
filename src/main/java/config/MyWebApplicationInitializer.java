package config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Class for using Java-based Spring configuration
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
public class MyWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Specify @Configuration and/or @Component classes for the root application context.
     *
     * @return the configuration for the root application context,
     * or null if creation and registration of a root context is not desired
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    /**
     * Specify @Configuration and/or @Component classes for the Servlet application context.
     *
     * @return the configuration for the Servlet application context,
     * or null if all configuration is specified through root config classes.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     * Specify the servlet mapping(s) for the DispatcherServlet
     *
     * @return mapping for the DispatcherServlet
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
