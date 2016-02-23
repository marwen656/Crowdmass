package tn.esprit.pidev.massconnection.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.pidev.massconnection.entities.Feedback;
import tn.esprit.pidev.massconnection.servicesinterface.FeedbackCrudLocal;
import tn.esprit.pidev.massconnection.servicesinterface.FeedbackCrudRemote;

/**
 * Session Bean implementation class FeedbackCrud
 */
@Stateless
public class FeedbackCrud extends AbstractFacade<Feedback> implements FeedbackCrudRemote, FeedbackCrudLocal {


	@PersistenceContext(unitName = "MassconnectionEJB")
    private EntityManager em;
    public FeedbackCrud() {
    	super(Feedback.class);
    }
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
