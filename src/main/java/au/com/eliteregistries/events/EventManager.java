package au.com.eliteregistries.events;

import au.com.eliteregistries.events.model.Event;
import au.com.eliteregistries.events.model.EventListener;

public interface EventManager {

    void addListener(Class c, EventListener listener);

    void removeListener(EventListener c);

    void triggerEvent(Event event);

    boolean containsListener(EventListener listener);

    int eventCount(Class eventAClass);
}
