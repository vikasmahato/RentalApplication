package rental.factory;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.inject.Inject;
import javax.inject.Named;
import javax.annotation.PostConstruct;

@Named
@Lazy
@PropertySource(value="classpath:/application.properties", ignoreResourceNotFound=true)
public class MicrosoftDynamicsBackendFactory implements AccountingBackendFactory {

    @Inject
    Environment springEnvironment;

    @PostConstruct
    @Override
    public void init() {
        this.connect();
    }

    @Override
    public boolean saveTokens() {
        return false;
    }

    @Override
    public boolean connect() {
        this.saveTokens();
        return false;
    }

    @Override
    public boolean disconect() {
        return false;
    }
}
