package tn.esprit.pidev.massconnection.servicesinterface;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.pidev.massconnection.entities.Category;

@Local
public interface CategoryCrudLocal {
	public List<Category> findAll();

	public void create(Category category);
	
	public void newCat(Category category);

	public void edit(Category category);

	public void remove(Category category);

	public Category find(int category);

	public void deleteById(int id);
}
