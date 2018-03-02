package eventsCollection;

import java.util.List;
import java.util.ArrayList;

import event.Event;

public class EventsCollection {
	private List<Event> eventList;
	
	public EventsCollection() {
		this.eventList = new ArrayList<Event>();
	}
	
	public EventsCollection(List<Event> eventList) {
		this.eventList = eventList;
	}
	
	public void addEvent(Event event) {
		if (event == null) {
			throw new NullPointerException();
		}
		if (!this.eventList.contains(event)) {
			this.eventList.add(event);
		}
	}
	
	public void removeEvent(Event event) {
		if (event == null) {
			throw new NullPointerException();
		}
		if (!this.eventList.contains(event)) {
			throw new IllegalArgumentException();
		}
		this.eventList.remove(event);
	}
	
	public void removeAllEvents() {
		this.eventList.clear();
	}
	
	public List<Event> getEventList() {
		return this.eventList;
	}
}
