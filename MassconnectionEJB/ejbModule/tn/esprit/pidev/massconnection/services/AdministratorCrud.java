package tn.esprit.pidev.massconnection.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.pidev.massconnection.entities.Administrator;
import tn.esprit.pidev.massconnection.servicesinterface.AdministratorCrudLocal;
import tn.esprit.pidev.massconnection.servicesinterface.AdministratorCrudRemote;

/**
 * Session Bean implementation class AdministratorCrud
 */
@Stateless
public class AdministratorCrud extends AbstractFacade<Administrator>implements AdministratorCrudRemote, AdministratorCrudLocal {

	@PersistenceContext(unitName = "MassconnectionEJB")
    private EntityManager em;
    public AdministratorCrud() {
        super(Administrator.class);
    }
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	@Override
	public boolean findByLoginAndPassword(Administrator administrator)
    {
        Administrator a = null;
        
        try{
            
        Query query = em.createNamedQuery("Administrator.findByLoginAndPassword");
        query.setParameter("username", administrator.getUserName());
        query.setParameter("password", administrator.getPassword());
        
        a = (Administrator) query.getSingleResult();
        if (a!=null){
        	return true;
        	
        }
        }
        catch(NoResultException e){
            return false;
        }
		return false;
    }
	

	

}
