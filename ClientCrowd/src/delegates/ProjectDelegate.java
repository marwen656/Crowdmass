package delegates;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tn.esprit.pidev.massconnection.entities.Project;
import tn.esprit.pidev.massconnection.servicesinterface.ProjectCrudRemote;
import util.ServiceLocator;

public class ProjectDelegate {
	private static ProjectCrudRemote proxy = (ProjectCrudRemote) ServiceLocator
			.getInstance()
			.getProxy(
					"/MassEAR/MassconnectionEJB/ProjectCrud!tn.esprit.pidev.massconnection.servicesinterface.ProjectCrudRemote");

	public static List<Project> retrieveAllProject() {
		return proxy.findAll();
	}

	public static void createProject(Project project) {
		proxy.create(project);
	}

	public static void deleteProject(Project project) {
		proxy.remove(project);
	}

	public static void updateProject(Project project) {
		proxy.edit(project);
	}

	public static Project retrieveOneProject(int id) {

		return proxy.find(id);
	}

	public static Long countProjectToValidate() {
		return proxy.countProjectToValidate();
	}

	public static void deleteProjectById(int id) {
		proxy.deleteById(id);
	}

	public static void validateProject(int id) {
		proxy.validateCrowd(id);
	}

	public static void unValidateProject(int id) {
		proxy.unValidateCrowd(id);
	}

	public static Long countByCategory(int id) {
		return proxy.countByCategory(id);
	}
	public static List<Project> retrieveProjectToValidate(){
		return proxy.retrieveProjectToValidate();
	}
	public static void seeProject(int id){
		proxy.seeProject(id);
	}
	public static ArrayList<Project> searchProjects(String arg){
		return proxy.searchProjects(arg);
	}
	public static Map<String, Long> statisticsTypesOfFundingModel(){
		return proxy.statisticsTypesOfFundingModel();
	}
}
