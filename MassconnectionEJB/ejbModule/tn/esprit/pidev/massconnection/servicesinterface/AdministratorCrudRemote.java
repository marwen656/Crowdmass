package tn.esprit.pidev.massconnection.servicesinterface;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pidev.massconnection.entities.Administrator;


@Remote
public interface AdministratorCrudRemote {
	
	public List<Administrator> findAll();

	public void create(Administrator administrator);

	public void edit(Administrator administrator);

	public void remove(Administrator administrator);

	public Administrator find(int administrator);
	 public boolean findByLoginAndPassword(Administrator administrator);

}
