package au.com.eliteregistries.events;

import au.com.eliteregistries.events.model.Event;
import au.com.eliteregistries.events.model.EventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManagerImpl implements EventManager {

    private static final EventManagerImpl INSTANCE = new EventManagerImpl();

    private final Map listeners;

    private EventTracker eventTracker;

    private EventManagerImpl() {
        listeners = new HashMap();
        eventTracker = new EventTracker();
    }

    @Override
    public void addListener(Class c, EventListener listener) {
        if (!listeners.containsKey(c)) {
            listeners.put(c, new ArrayList());
        }
        ((List) listeners.get(c)).add(listener);
    }

    @Override
    public void triggerEvent(Event event) {
        eventTracker.track(event);
        List eventListeners = (List) listeners.get(event.getClass());
        for (Object listener : eventListeners) {
            ((EventListener) listener).handleEvent(event);
        }
    }

    @Override
    public void removeListener(EventListener c) {

    }

    @Override
    public boolean containsListener(EventListener listener) {
        return false;
    }

    @Override
    public int eventCount(Class eventClass) {
        return eventTracker.getNumberOfEventsFromType(eventClass);
    }

    public static EventManager getInstance() {
        return INSTANCE;
    }
}
