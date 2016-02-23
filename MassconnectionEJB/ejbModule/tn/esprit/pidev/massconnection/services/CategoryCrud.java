package tn.esprit.pidev.massconnection.services;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.pidev.massconnection.entities.Category;
import tn.esprit.pidev.massconnection.servicesinterface.CategoryCrudLocal;
import tn.esprit.pidev.massconnection.servicesinterface.CategoryCrudRemote;

/**
 * Session Bean implementation class CategoryCrud
 */
@Stateless

public class CategoryCrud extends AbstractFacade<Category> implements CategoryCrudRemote, CategoryCrudLocal {

	@PersistenceContext(unitName = "MassconnectionEJB")
    private EntityManager em;
    public CategoryCrud() {
        super(Category.class);
    }

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public void editById(int id, String title) {
		Category c=em.find(Category.class,id);
		c.setTitle(title);
		em.merge(c);
	}
	public void newCat(Category category){
		em.persist(category);
	}


	

}
