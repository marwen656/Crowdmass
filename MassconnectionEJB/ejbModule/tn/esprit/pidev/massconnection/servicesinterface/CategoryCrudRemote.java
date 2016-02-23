package tn.esprit.pidev.massconnection.servicesinterface;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pidev.massconnection.entities.Category;

@Remote
public interface CategoryCrudRemote {
	public List<Category> findAll();
	
	public void newCat(Category category);

	public void create(Category category);

	public void edit(Category category);

	public void remove(Category category);

	public Category find(int id);

	public void deleteById(int id);
	
	public void editById(int id,String title);
	
}
