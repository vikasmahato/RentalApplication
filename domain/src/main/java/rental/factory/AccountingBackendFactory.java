package rental.factory;

public interface AccountingBackendFactory {
    void init();
    boolean saveTokens();
    boolean connect();
    boolean disconect();
}
