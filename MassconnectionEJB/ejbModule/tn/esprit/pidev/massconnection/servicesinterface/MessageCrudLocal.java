package tn.esprit.pidev.massconnection.servicesinterface;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.pidev.massconnection.entities.Message;

@Local
public interface MessageCrudLocal {
	public List<Message> findAll();

	public void create(Message message);

	public void edit(Message message);

	public void remove(Message message);

	public Message find(int message);

	public List<Message> getMessageByid(int id);
}
