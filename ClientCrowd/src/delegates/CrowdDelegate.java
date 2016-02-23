package delegates;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tn.esprit.pidev.massconnection.entities.Crowd;
import tn.esprit.pidev.massconnection.servicesinterface.CrowdCrudRemote;
import util.ServiceLocator;

public class CrowdDelegate {
	private static CrowdCrudRemote proxy = (CrowdCrudRemote) ServiceLocator
			.getInstance()
			.getProxy(
					"/MassEAR/MassconnectionEJB/CrowdCrud!tn.esprit.pidev.massconnection.servicesinterface.CrowdCrudRemote");

	public static List<Crowd> retrieveAllCrowds() {
		return proxy.findAll();
	}
		
	public static void createCrowd(Crowd crowd){
		proxy.create(crowd);
	}
	public static void deleteCrowd(Crowd crowd){
		proxy.remove(crowd);
	}
	public static void removeCrowd(int id){
		proxy.deleteCrowd(id);
	}
	public static void updateCrowd(Crowd crowd){
		proxy.edit(crowd);
	}
	public static Crowd retrieveOneCrowd(int id){
		
		return proxy.find(id);
	}
	public static Map<String, Long> GetStaticsTypeOfCrowd(){
		return proxy.statisticsTypesOfCrowds();
	}

	public static void blockCrowd(int id){
		proxy.lockCrowd(id);
	}
	public static void unblockCrowd(int id){
		proxy.unlockCrowd(id);
	}
	public static ArrayList<Crowd> searchAccount(String arg){
		return proxy.searchAccount(arg);
	}
}
