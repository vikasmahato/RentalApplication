package rental.factory;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.inject.Named;
import javax.annotation.PostConstruct;

@Named
@Lazy
@PropertySource(value="classpath:/application.properties", ignoreResourceNotFound=true)
public class MicrosoftDynamicsBackendFactory implements AccountingBackendFactory {

    private Environment springEnvironment;

    public MicrosoftDynamicsBackendFactory(Environment springEnvironment) {
        this.springEnvironment = springEnvironment;
    }

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
