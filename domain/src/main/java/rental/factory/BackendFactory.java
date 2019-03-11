package rental.factory;

import org.springframework.context.annotation.Lazy;

import javax.inject.Named;

@Named
@Lazy
public class BackendFactory implements AccountingBackendFactory {
    @Override
    public void init() {

    }

    @Override
    public boolean saveTokens() {
        return false;
    }

    @Override
    public boolean connect() {
        return false;
    }

    @Override
    public boolean disconect() {
        return false;
    }
}
