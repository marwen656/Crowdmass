package tn.esprit.pidev.massconnection.servicesinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import tn.esprit.pidev.massconnection.entities.Crowd;

@Remote
public interface CrowdCrudRemote {
	public List<Crowd> findAll();

	public void create(Crowd crowd);

	public void edit(Crowd crowd);

	public void remove(Crowd crowd);

	public Crowd find(int crowd);

	public void deleteCrowd(int id);

	/**
	 * 
	 * @return a map with key=type of crowd and value=percent
	 */
	public Map<String, Long> statisticsTypesOfCrowds();

	public void lockCrowd(int id);

	public void unlockCrowd(int id);

	public ArrayList<Crowd> searchAccount(String arg);
}
