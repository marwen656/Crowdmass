package tn.esprit.pidev.massconnection.servicesinterface;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import tn.esprit.pidev.massconnection.entities.Challenge;

@Local
public interface ChallengeCrudLocal {
	public List<Challenge> findAll();

	public void create(Challenge challenge);

	public void edit(Challenge challenge);

	public void remove(Challenge challenge);

	public Challenge find(int challenge);

	public Long countChallengeToValidate();

	public ArrayList<Challenge> searchChallenge(String arg);

	public List<Challenge> retrieveChallengeToValidate();

	public void seeChallenge(int id);

	public List<Challenge> getAllChallenge();

	public List<Challenge> getChallengeByUsername(int id);

	public Challenge FindChallengeById(int id);

	public List<Challenge> searchChallenge2(String arg);
}
