package config;

import config.root.RootConfig;
import config.web.WebConfig;
import javax.servlet.MultipartConfigElement;
import javax.servlet.FilterRegistration;
import org.springframework.web.filter.CharacterEncodingFilter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public final class AppWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ RootConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        // Asignamos DispatcherServlet a "/"
        return new String[] { "/" };
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        super.customizeRegistration(registration);
        registration.setMultipartConfig(new MultipartConfigElement("/", 2097152, 4194304, 0));
    }
    
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        // set active profiles
        servletContext.setInitParameter("spring.profiles.active", "development");
        // add CharacterEncodingFilter solve encode problems with Tomcat server
        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filter", new CharacterEncodingFilter());
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        /*
            To make sure the characterEncodingFilter is first in the chain you need to change the middle argument in addMappingForUrlPatterns to false. 
            The value false ensures that the CharacterEncodingFilter is the first filter in the chain, the value true adds the filter to the end of the filterChain
        */
        encodingFilter.addMappingForUrlPatterns(null, false, "/*");
    }
}
