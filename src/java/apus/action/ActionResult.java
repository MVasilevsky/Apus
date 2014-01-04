package apus.action;

/**
 * The <code>ActionResult</code> class represents action, which must servlet do next  
 * @author M. Vasilevsky
 */
public class ActionResult {
    
    private final static boolean MODE_REDIRECT = false;
    private final static boolean MODE_FORWARD = true;
    private boolean mode;
    
    private String string;

    private ActionResult(boolean mode, String string) {
        this.mode = mode;
        this.string = string;
    }

    public String getAction() {
        return string;
    }
    
    public String getViewJsp() {
        return string;
    }

    public boolean isRedirect() {
        return mode == MODE_REDIRECT;
    }
    
    public boolean isForward() {
        return mode == MODE_FORWARD;
    }
    
    public static ActionResult forward(String viewJsp) {
        return new ActionResult(MODE_FORWARD, viewJsp);
    }
    
    public static ActionResult redirect(String actionName) {
        return new ActionResult(MODE_REDIRECT, actionName);
    }    
}
