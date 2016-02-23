package tn.esprit.pidev.massconnection.servicesinterface;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.pidev.massconnection.entities.Notification;

@Local
public interface NotificationCrudLocal {
	public List<Notification> findAll();

	public void create(Notification notification);

	public void edit(Notification notification);

	public void remove(Notification notification);

	public Notification find(int notification);
}
