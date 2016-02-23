package tn.esprit.pidev.massconnection.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.pidev.massconnection.entities.Funds;
import tn.esprit.pidev.massconnection.servicesinterface.FundsCrudLocal;
import tn.esprit.pidev.massconnection.servicesinterface.FundsCrudRemote;

/**
 * Session Bean implementation class FundsCrud
 */
@Stateless
public class FundsCrud extends AbstractFacade<Funds>implements FundsCrudRemote, FundsCrudLocal {

	@PersistenceContext(unitName = "MassconnectionEJB")
    private EntityManager em;
    public FundsCrud() {
    	super(Funds.class);
    }

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
