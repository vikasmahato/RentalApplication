package rental.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import rental.main.RentalApplication;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class AnnotationsBasedApplicationInitializer extends AbstractContextLoaderInitializer {

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        AnnotationConfigWebApplicationContext rootContext
                = new AnnotationConfigWebApplicationContext();
        rootContext.register(RentalApplication.class);
        return rootContext;
    }
}