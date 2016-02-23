package tn.esprit.pidev.massconnection.servicesinterface;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pidev.massconnection.entities.Feedback;

@Remote
public interface FeedbackCrudRemote {
	public List<Feedback> findAll();

	public void create(Feedback feedback);

	public void edit(Feedback feedback);

	public void remove(Feedback feedback);

	public Feedback find(int feedback);
}
