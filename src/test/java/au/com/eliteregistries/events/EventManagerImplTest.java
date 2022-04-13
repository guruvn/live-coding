package au.com.eliteregistries.events;


import au.com.eliteregistries.events.model.*;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class EventManagerImplTest {

    EventManager eventManager = EventManagerImpl.getInstance();

    @Test
    public void triggerEventTest() {
        TestEventListener listener = new TestEventListener();
        eventManager.addListener(EventA.class, listener);
        eventManager.triggerEvent(new EventA());
        Assert.assertThat(listener.count, is(1));
    }

    @Test
    public void removeTest() {
        TestEventListener listener = new TestEventListener();
        eventManager.addListener(EventA.class, listener);
        eventManager.removeListener(listener);
        Assert.assertThat(listener.count, is(1));
    }

    @Test
    public void trackTest() {
        TestEventListener listener = new TestEventListener();
        eventManager.addListener(EventA.class, listener);
        eventManager.addListener(EventB.class, listener);
        eventManager.triggerEvent(new EventA());
        eventManager.triggerEvent(new EventA());
        eventManager.triggerEvent(new EventB());
        Assert.assertThat(eventManager.eventCount(EventA.class), is(2));
        Assert.assertThat(eventManager.eventCount(EventB.class), is(1));
        Assert.assertThat(eventManager.eventCount(EventAPrime.class), is(0));
    }

    @Test
    public void containsTest() {
        TestEventListener listener = new TestEventListener();
        eventManager.addListener(EventA.class, listener);
        Assert.assertThat(eventManager.containsListener(listener), is(false));
    }

    // todo event subclasses must be taken into account
    // for example, EventAPrime extends Event A
    // If EventA is triggered, listeners of both EventA and EventAPrime must be invoked.
    // If EventAPrime is triggered, only listeners of EventAPrime must be invoked.
    @Test
    public void onlyPrimeClassShouldBeTriggered() {

    }

    // todo - the same listener cannot be added twice.
    @Test
    public void noDuplicationListener() {

    }

    // todo - avoid removing a listener while an event is being processed
    @Test
    public void avoidRemovingListnerWhileEventIsProcessing() {

    }

    // todo - avoid adding a listener while an event is being processed
    @Test
    public void avoidAddingListnerWhileEventIsProcessing() {

    }

    private class TestEventListener implements EventListener {
        private int count = 0;
        @Override
        public void handleEvent(Event event) {
            count++;
        }
    }
}
