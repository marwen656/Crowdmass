package tn.esprit.pidev.massconnection.servicesinterface;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pidev.massconnection.entities.Notification;

@Remote
public interface NotificationCrudRemote {
	public List<Notification> findAll();

	public void create(Notification notification);

	public void edit(Notification notification);

	public void remove(Notification notification);

	public Notification find(int notification);
}
