package delegates;

import java.util.List;

import tn.esprit.pidev.massconnection.entities.Feedback;
import tn.esprit.pidev.massconnection.servicesinterface.FeedbackCrudRemote;
import util.ServiceLocator;

public class FeedbackDelegate {
	private static FeedbackCrudRemote proxy = (FeedbackCrudRemote) ServiceLocator
			.getInstance()
			.getProxy(
					"/MassEAR/MassconnectionEJB/FeedbackCrud!tn.esprit.pidev.massconnection.servicesinterface.FeedbackCrudRemote");

	public static List<Feedback> retrieveAllCategories() {
		return proxy.findAll();
	}
		
	public static void createFeedback(Feedback feedback){
		proxy.create(feedback);
	}
	public static void deleteFeedback(Feedback feedback){
		proxy.remove(feedback);
	}
	public static void updateFeedback(Feedback feedback){
		proxy.edit(feedback);
	}
	public static Feedback retrieveOneFeedback(int id){
		
		return proxy.find(id);
	}

}
