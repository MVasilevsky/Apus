package apus.exception;

/**
 * The
 * <code>DatabaseException</code> class represent exception, when exists any
 * problems with SQL.
 *
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 */
public class DatabaseException extends BusinessException {

    public DatabaseException(Throwable thrwbl) {
        super(thrwbl);
    }

    public DatabaseException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public DatabaseException(String string) {
        super(string);
    }

    public DatabaseException() {
    }
}
