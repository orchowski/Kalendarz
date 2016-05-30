package beany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import klasy.Event;


/**
 * The Class EventBean.
 *
 * @author Wenaro
 */
@ManagedBean
@SessionScoped
public class EventBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The event. */
	private Event event;
	
	//private boolean isOnList;
	
	/** The events. */
	private List<Event> events;
	
	/** The deleted. */
	private boolean deleted;
	
	/**
	 * Inits the event and events.
	 */
	@PostConstruct
	public void init() {
		
		event = new Event();
		events = new ArrayList<Event>();
	}
	
	/**
	 * Adds the event.
	 */
	public void add() {

			events.add(event);
			clearEvent();
	}

	/**
	 * Clear event.
	 */
	public void clearEvent() {
		
		event = new Event();
	}
	
	/**
	 * Clear list of events.
	 */
	public void clear() {
		
		events.removeAll(events);
	}

	
	/**
	 * Removes the.
	 *
	 * @param event the event
	 */
	public void remove(Event event) {
		
		events.remove(event);	
	}

	/**
	 * Save action.
	 */
	public void saveAction() {
		
		for (Event event : events) {
			event.setEditable(false);
		}	
	}

	/**
	 * Set editable for event.
	 *
	 * @param event the event
	 */
	public void edit(Event event) {
		
		event.setEditable(true);
	}

	/**
	 * Copy event.
	 *
	 * @param event the event
	 */
	public void copy(Event event){
		
		events.add(new Event(event));
	}
	
	/**
	 * Gets the event.
	 *
	 * @return the event
	 */
	public Event getEvent() {
		
		return event;
	}

	/**
	 * Gets the events.
	 *
	 * @return the events
	 */
	public List<Event> getEvents() {
		
		return events;
	}

	/**
	 * Sets the event.
	 *
	 * @param event the new event
	 */
	public void setEvent(Event event) {
		
		this.event = event;
	}

	/**
	 * Sets the events.
	 *
	 * @param events the new events
	 */
	public void setEvents(List<Event> events) {
		
		this.events = events;
	}

	/**
	 * Checks if is deleted.
	 *
	 * @return true, if is deleted
	 */
	public boolean isDeleted() {
		
		return deleted;
	}

	/**
	 * Sets the deleted.
	 *
	 * @param deleted the new deleted
	 */
	public void setDeleted(boolean deleted) {
		
		this.deleted = deleted;
	}
}