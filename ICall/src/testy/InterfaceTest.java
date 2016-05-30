package testy;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import beany.EventBean;
import klasy.Event;


/**
 * The test InterfaceTest.
 *
 * @author Wenaro
 */
public class InterfaceTest {

	/** The event. */
	private Event event = new Event("testTitle", new Date((2015 - 1901), 12, 13), new Date((2015 - 1901), 12, 13),
			"test opis");
	
	/** The event bean. */
	private EventBean eventBean = new EventBean();

	/** The events. */
	private List<Event> events;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {

		events = new ArrayList<Event>();
		eventBean.setEvents(events);
		eventBean.add();
	}

	/**
	 * Add event.
	 */
	@Test
	public void aAddTest() {

		assertNotEquals(0, events.size());
	}

	/**
	 * Remove all event from list.
	 */
	@Test
	public void removeAllTest() {

		assertNotEquals(0, events.size());
		eventBean.clear();
		assertEquals(0, events.size());
	}

	/**
	 * Copy event.
	 */
	@Test
	public void copyEvent() {
		
		assertNotEquals(0, events.size());
		int size = events.size();
		eventBean.copy(event);
		assertEquals(size + 1, events.size());
	}

	/**
	 * Edits the test.
	 */
	@Test
	public void editTest() {
		
		assertNotEquals(0, events.size());
		eventBean.edit(event);
		assertTrue(event.isEditable());
	}

	/**
	 * Save action test.
	 */
	@Test
	public void saveActionTest() {
		
		assertNotEquals(0, events.size());
		eventBean.edit(event);
		assertFalse(eventBean.getEvent().isEditable());
	}

	/**
	 * Clear event test.
	 */
	@Test
	public void clearEventTest() {
		
		assertNotEquals(0, events.size());
		eventBean.clearEvent();
		assertNull(eventBean.getEvent().getTitle());
	}

	/**
	 * Removes the one event from list.
	 */
	@Test
	public void removeOneTest() {

		assertNotEquals(0, events.size());
		eventBean.remove(event);
		assertFalse(events.contains(event));
	}
}
