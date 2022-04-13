package au.com.eliteregistries.events;

import au.com.eliteregistries.events.model.Event;

import java.util.HashMap;
import java.util.Map;

class EventTracker {

    private Map tracker = new HashMap();

    public void track(Event c) {
        Class key = c.getClass();
        if (!tracker.containsKey(key)) {
            tracker.put(key, 0);
        }
        tracker.put(key, ((Integer) tracker.get(key)) + 1);
    }

    public int getNumberOfEventsFromType(Class c) {
        return (Integer) tracker.get(c);
    }
}
