package apus.exception;

/**
 * The
 * <code>BusinessException</code> class represent common exception in business
 * logic.
 *
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 * @see RuntimeException
 * @see Throwable
 */
public class BusinessException extends RuntimeException {

    public BusinessException(Throwable thrwbl) {
        super(thrwbl);
    }

    public BusinessException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public BusinessException(String string) {
        super(string);
    }

    public BusinessException() {
    }
}
