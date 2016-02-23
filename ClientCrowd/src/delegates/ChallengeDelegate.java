package delegates;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.pidev.massconnection.entities.Challenge;
import tn.esprit.pidev.massconnection.servicesinterface.ChallengeCrudRemote;
import util.ServiceLocator;

public class ChallengeDelegate {
	private static ChallengeCrudRemote proxy = (ChallengeCrudRemote) ServiceLocator
			.getInstance()
			.getProxy(
					"/MassEAR/MassconnectionEJB/ChallengeCrud!tn.esprit.pidev.massconnection.servicesinterface.ChallengeCrudRemote");

	public static List<Challenge> retrieveAllChallenge() {
		return proxy.findAll();
	}
		
	public static void createChallenge(Challenge challenge){
		proxy.create(challenge);
	}
	public static void deleteChallenge(Challenge challenge){
		proxy.remove(challenge);
	}
	public static void updateChallenge(Challenge challenge){
		proxy.edit(challenge);
	}
	public static Challenge retrieveOneChallenge(int id){
		
		return proxy.find(id);
	}
	public static Long countChallengeToValidate(){
		return proxy.countChallengeToValidate();
	}
	public static ArrayList<Challenge> searchChallenge(String arg){
		return proxy.searchChallenge(arg);
	}
	public static List<Challenge> retrieveChallengeToValidate(){
		return proxy.retrieveChallengeToValidate();
	}
	public static void seeChallenge(int id){
		proxy.seeChallenge(id);
	}
}
