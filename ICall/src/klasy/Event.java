package klasy;

import java.util.Date;

/**
 * The Class Event.
 *
 * @author Wenaro
 */
public class Event {

	/** The title. */
	private String title;

	/** The description. */
	private String description;

	/** The start date. */
	private Date startDate;

	/** The end date. */
	private Date endDate;

	/** The editable. */
	private boolean editable;

	
	
	/**
	 * Instantiates a new event.
	 */
	public Event() {
		setTitle(getTitle());
		setStartDate(getStartDate());
		setEndDate(getEndDate());
		setDescription(getDescription());
	}
	
	/**
	 * Instantiates a new event.
	 *
	 * @param event the event
	 */
	public Event(Event event) {
		setTitle(event.title);
		setStartDate(event.getStartDate());
		setEndDate(event.getEndDate());
		setDescription(event.getDescription());
	}
	
	/**
	 * Instantiates a new event.
	 *
	 * @param eventTitle the event title
	 * @param dateStart the date start
	 * @param dateEnd the date end
	 * @param description the description
	 */
	public Event(String eventTitle, Date dateStart, Date dateEnd,
			String description){
		setTitle(eventTitle);
		setStartDate(dateStart);
		setEndDate(dateEnd);
		setDescription(description);
	}
	
	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate
	 *            the new start date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate
	 *            the new end date
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Checks if is editable.
	 *
	 * @return true, if is editable
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * Sets the editable.
	 *
	 * @param editable
	 *            the new editable
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

}