package beany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import klasy.Event;

/**
 * @author Wenaro
 *
 */
@ManagedBean
@SessionScoped
public class EventBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Event event;
	private List<Event> events;

	@PostConstruct
	public void init() {
		event = new Event();
		events = new ArrayList<Event>();
	}

	public boolean contain() {
		boolean isOnList = false;
		for (Event eventf : events) {
			isOnList = event.getTitle().equals(eventf.getTitle());
		}
		return isOnList;
	}

	public void add() {
		if (contain()) {
			clearEvent();
		} else {
			events.add(event);
			clearEvent();
		}
	}

	public void clearEvent() {
		event = new Event();
	}

	public void clear() {
		events.removeAll(events);
	}

	public String remove(Event event) {
		events.remove(event);
		return null;
	}

	public String saveAction() {
		for (Event event : events) {
			event.setEditable(false);
		}
		return null;
	}

	public String edit(Event event) {
		event.setEditable(true);
		return null;
	}

	public Event getEvent() {
		return event;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

}