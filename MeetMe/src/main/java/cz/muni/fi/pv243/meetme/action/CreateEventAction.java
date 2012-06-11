package cz.muni.fi.pv243.meetme.action;

import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.international.status.Messages;

import cz.muni.fi.pv243.meetme.model.Event;

@Model
@Stateful
public class CreateEventAction {

	ResourceBundle msg = ResourceBundle.getBundle("cz.muni.fi.pv243.meetme.viewconfig.messages_cs");
	
	@PersistenceContext
	EntityManager em;
	
	private Event event;
	
	@PostConstruct
	public void initialize() {
		event = new Event();
	}
	
	@Inject
	Messages messages;
	
	public void create() {
		em.persist(event);
		messages.info(msg.getString("event.eventCreated"), event.getName());
	}
	
	public void createNoAuth() {
		
		//TODO nastaveni atributu pro neautentizovanou udalost
		em.persist(event);
		messages.info(msg.getString("event.eventCreated"), event.getName());
	}
	
	@Produces
	@Named
	public Event getNewEvent() {
		return event;
	}	
	
}