package apus.common;

/**
 * @author Maxim Vasilevsky
 */
public class Configuration {

    // APUS_DB_PATH = jdbc:mysql://localhost:3306/apus?characterEncoding=utf8
    public static final String DB_URL = System.getenv("APUS_DB_PATH");
    public static final String DB_LOGIN = "root";
    public static final String DB_PASSWORD = "12345";
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final int CONNECTION_POOL_SIZE = 3;
}
