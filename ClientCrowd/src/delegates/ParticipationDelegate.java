package delegates;

import java.util.List;
import java.util.Map;



import tn.esprit.pidev.massconnection.servicesinterface.ParticipationCrudRemote;
import util.ServiceLocator;

public class ParticipationDelegate {
	private static ParticipationCrudRemote proxy = (ParticipationCrudRemote) ServiceLocator
			.getInstance()
			.getProxy(
					"/MassEAR/MassconnectionEJB/ParticipationCrud!tn.esprit.pidev.massconnection.servicesinterface.ParticipationCrudRemote");
	public static  List<String> topParticipationPerChallenge(){
		return proxy.topParticipationPerChallenge();
	}

}
