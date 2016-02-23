package delegates;

import java.util.List;

import tn.esprit.pidev.massconnection.entities.Category;
import tn.esprit.pidev.massconnection.servicesinterface.AdministratorCrudRemote;
import tn.esprit.pidev.massconnection.servicesinterface.CategoryCrudRemote;
import util.ServiceLocator;

/**
 * 
 * @author Amine
 * 
 */
public class CategoryDelegate {
	//
	private static CategoryCrudRemote proxy = (CategoryCrudRemote) ServiceLocator
			.getInstance()
			.getProxy(
					"/MassEAR/MassconnectionEJB/CategoryCrud!tn.esprit.pidev.massconnection.servicesinterface.CategoryCrudRemote");

	public static List<Category> retrieveAllCategories() {
		return proxy.findAll();
	}
		
	public static void createCategory(Category category){
		proxy.create(category);
	}
	public static void newCat(Category category){
		proxy.newCat(category);
	}
	public static void deleteCategory(Category category){
		proxy.remove(category);
	}
	public static void updateCategory(Category category){
		proxy.edit(category);
	}
	public static Category retrieveOneCategory(int id){
		
		return proxy.find(id);
	}
	public static void deleteCategoryById(int id){
		proxy.deleteById(id);
	}
	public static void editById(int id, String title){
	proxy.editById(id, title);	
	}
	
}
