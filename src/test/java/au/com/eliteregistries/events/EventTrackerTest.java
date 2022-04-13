package au.com.eliteregistries.events;

import au.com.eliteregistries.events.model.EventA;
import au.com.eliteregistries.events.model.EventB;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EventTrackerTest {

    private EventTracker eventTracker;

    @Before
    public void setup() {
        eventTracker = new EventTracker();
    }

    @Test
    public void trackTest() {
        EventA eventA = new EventA();
        EventB eventB = new EventB();
        eventTracker.track(eventA);
        eventTracker.track(eventA);
        eventTracker.track(eventB);
        assertThat(eventTracker.getNumberOfEventsFromType(EventA.class), is(2));
        assertThat(eventTracker.getNumberOfEventsFromType(EventB.class), is(1));
    }

}
