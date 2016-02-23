package delegates;

import java.util.List;

import tn.esprit.pidev.massconnection.entities.Solution;
import tn.esprit.pidev.massconnection.servicesinterface.SolutionCrudRemote;
import util.ServiceLocator;

public class SolutionDelegate {
	private static SolutionCrudRemote proxy = (SolutionCrudRemote) ServiceLocator
			.getInstance()
			.getProxy(
					"/MassEAR/MassconnectionEJB/SolutionCrud!tn.esprit.pidev.massconnection.servicesinterface.SolutionCrudRemote");

	public static List<Solution> retrieveAllCategories() {
		return proxy.findAll();
	}
		
	public static void createSolution(Solution solution){
		proxy.create(solution);
	}
	public static void deleteSolution(Solution solution){
		proxy.remove(solution);
	}
	public static void updateSolution(Solution solution){
		proxy.edit(solution);
	}
	public static Solution retrieveOneSolution(int id){
		
		return proxy.find(id);
	}

	public static List<Solution> retrieveSolutionByChallenge(int id) {
		return proxy.findSolutionbyChallenge(id);
		
	}

	public static String retrievePath(int id) {
		return proxy.getPath(id);
	}
}
