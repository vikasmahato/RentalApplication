package rental.factory;

import com.intuit.oauth2.client.OAuth2PlatformClient;

import com.intuit.oauth2.config.OAuth2Config;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@Lazy
@PropertySource(value="classpath:/application.properties", ignoreResourceNotFound=true)
public class QuickbooksBackendFactory implements AccountingBackendFactory {

    @Inject
    Environment springEnvironment;

    OAuth2PlatformClient client;
    OAuth2Config oauth2Config;

    @PostConstruct
    @Override
    public void init() {
       /* //initialize the config
        oauth2Config = new OAuth2Config.OAuth2ConfigBuilder(env.getProperty("OAuth2AppClientId"), env.getProperty("OAuth2AppClientSecret")) //set client id, secret
                .callDiscoveryAPI(Environment.SANDBOX) // call discovery API to populate urls
                .buildConfig();
        //build the client
        client  = new OAuth2PlatformClient(oauth2Config);*/

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


    public OAuth2PlatformClient getOAuth2PlatformClient()  {
        return client;
    }

    public OAuth2Config getOAuth2Config()  {
        return oauth2Config;
    }

    public String getPropertyValue(String proppertyName) {
        return springEnvironment.getProperty(proppertyName);
    }
}
