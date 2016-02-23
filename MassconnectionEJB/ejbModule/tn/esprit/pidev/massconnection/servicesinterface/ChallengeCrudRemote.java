package tn.esprit.pidev.massconnection.servicesinterface;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pidev.massconnection.entities.Challenge;

@Remote
public interface ChallengeCrudRemote {
	public List<Challenge> findAll();

	public void create(Challenge challenge);

	public void edit(Challenge challenge);

	public void remove(Challenge challenge);

	public Challenge find(int challenge);

	public Long countChallengeToValidate();

	public ArrayList<Challenge> searchChallenge(String arg);
	
	public List<Challenge> retrieveChallengeToValidate();
	
	public void seeChallenge(int id);
}
