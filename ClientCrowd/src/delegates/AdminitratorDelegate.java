package delegates;

import tn.esprit.pidev.massconnection.entities.Administrator;
import tn.esprit.pidev.massconnection.servicesinterface.AdministratorCrudRemote;
import util.ServiceLocator;
/**
 * 
 * @author Amine
 *
 */
public class AdminitratorDelegate {
	private static AdministratorCrudRemote proxy= (AdministratorCrudRemote)ServiceLocator.getInstance().getProxy("/MassEAR/MassconnectionEJB/AdministratorCrud!tn.esprit.pidev.massconnection.servicesinterface.AdministratorCrudRemote");
	public static boolean  authenticate(Administrator administrator ){
		return proxy.findByLoginAndPassword(administrator);
		
	}
	public static Administrator getOneAdministrator(int id){
		return proxy.find(id);
	}

}
