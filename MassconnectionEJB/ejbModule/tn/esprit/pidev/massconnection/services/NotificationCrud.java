package tn.esprit.pidev.massconnection.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.pidev.massconnection.entities.Notification;
import tn.esprit.pidev.massconnection.servicesinterface.NotificationCrudLocal;
import tn.esprit.pidev.massconnection.servicesinterface.NotificationCrudRemote;

/**
 * Session Bean implementation class NotificationCrud
 */

@Stateless
public class NotificationCrud extends AbstractFacade<Notification> implements NotificationCrudRemote, NotificationCrudLocal {

	@PersistenceContext(unitName = "MassconnectionEJB")
    private EntityManager em;
    public NotificationCrud() {
        super(Notification.class);
    }
    
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}