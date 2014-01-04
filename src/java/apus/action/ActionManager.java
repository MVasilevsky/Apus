package apus.action;

import java.util.HashMap;
import java.util.Map;

/**
 * The <code>ActionManager</code> class contents actions collection, used in Front Controller design pattern 
 * @author M. Vasilevsky
 */
public class ActionManager {
    private Map<String,Action> actionMap = new HashMap<String, Action>();
    
    public void addAction(Action action) {
        actionMap.put(action.getName(), action);
    }
    
    public Action findAction(String name) {
        return actionMap.get(name);
    }
}
