package tn.esprit.pidev.massconnection.servicesinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import tn.esprit.pidev.massconnection.entities.Crowd;

@Local
public interface CrowdCrudLocal {
	public List<Crowd> findAll();

	public void create(Crowd crowd);

	public void edit(Crowd crowd);

	public void remove(Crowd crowd);

	public Crowd find(int category);

	public void deleteCrowd(int id);
	public Crowd FindCrowdById(int id);

	/**
	 * 
	 * @return a map with key=type of crowd and value=percent
	 */
	public Map<String, Long> statisticsTypesOfCrowds();

	public void lockCrowd(int id);

	public void unlockCrowd(int id);

	public ArrayList<Crowd> searchAccount(String arg);
	
	public Crowd retrieveCrowdtByinformations(String userName,
			String password);
	
	public boolean crowdExist(String userName);
	
	public Crowd retrieveCrowdtByUserName(String userName);
}
