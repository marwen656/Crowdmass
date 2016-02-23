package tn.esprit.pidev.massconnection.servicesinterface;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.pidev.massconnection.entities.Funds;

@Local
public interface FundsCrudLocal {
	public List<Funds> findAll();

	public void create(Funds funds);

	public void edit(Funds funds);

	public void remove(Funds funds);

	public Funds find(int funds);
}
