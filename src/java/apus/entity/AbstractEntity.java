package apus.entity;

/**
 * The
 * <code>AbstractEntity</code> interface defines methods that must be
 * implemented in every class-essence. These methods allow you to work with an
 * unique object identifier (id).
 *
 * It's an interface because we used not just entities but also proxy objects
 * for every model object
 *
 * @author Maxim Vasilevsky
 * @author Roman Dyatkovsky
 */
public interface AbstractEntity {

    public int getId();

    public void setId(int id);
}
