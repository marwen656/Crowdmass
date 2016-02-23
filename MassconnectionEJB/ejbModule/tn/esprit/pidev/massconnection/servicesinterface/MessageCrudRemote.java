package tn.esprit.pidev.massconnection.servicesinterface;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pidev.massconnection.entities.Message;

@Remote
public interface MessageCrudRemote {
	public List<Message> findAll();

	public void create(Message message);

	public void edit(Message message);

	public void remove(Message message);

	public Message find(int message);
}
