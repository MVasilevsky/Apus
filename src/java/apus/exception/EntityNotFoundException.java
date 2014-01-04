package apus.exception;

/**
 * The
 * <code>EntityNotFoundException</code> class represent exception, when entity
 * not found in database.
 *
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 */
public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(Throwable thrwbl) {
        super(thrwbl);
    }

    public EntityNotFoundException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public EntityNotFoundException(String string) {
        super(string);
    }

    public EntityNotFoundException() {
    }
}
