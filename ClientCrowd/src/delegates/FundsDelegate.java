package delegates;

import java.util.List;

import tn.esprit.pidev.massconnection.entities.Funds;
import tn.esprit.pidev.massconnection.servicesinterface.FundsCrudRemote;
import util.ServiceLocator;

public class FundsDelegate {
	private static FundsCrudRemote proxy = (FundsCrudRemote) ServiceLocator
			.getInstance()
			.getProxy(
					"/MassEAR/MassconnectionEJB/FundsCrud!tn.esprit.pidev.massconnection.servicesinterface.FundsCrudRemote");

	public static List<Funds> retrieveAllCategories() {
		return proxy.findAll();
	}
		
	public static void createFunds(Funds funds){
		proxy.create(funds);
	}
	public static void deleteFunds(Funds funds){
		proxy.remove(funds);
	}
	public static void updateFunds(Funds funds){
		proxy.edit(funds);
	}
	public static Funds retrieveOneFunds(int id){
		
		return proxy.find(id);
	}
}
