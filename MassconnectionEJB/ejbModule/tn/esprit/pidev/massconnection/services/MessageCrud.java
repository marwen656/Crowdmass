package tn.esprit.pidev.massconnection.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.pidev.massconnection.entities.Challenge;
import tn.esprit.pidev.massconnection.entities.Message;
import tn.esprit.pidev.massconnection.servicesinterface.MessageCrudRemote;
import tn.esprit.pidev.massconnection.servicesinterface.MessageCrudLocal;

/**
 * Session Bean implementation class MessageCrud
 */
@Stateless
public class MessageCrud extends AbstractFacade<Message>implements MessageCrudRemote, MessageCrudLocal {

	@PersistenceContext(unitName = "MassconnectionEJB")
    private EntityManager em;
    public MessageCrud() {
       super(Message.class);
    }
	@Override
	protected EntityManager getEntityManager() {
		
		return em;
	}

	public List<Message> getMessageByid(int id) {
		TypedQuery<Message> query = em
				.createQuery(
						"select m from Message m where m.recepient.id= :id",
						Message.class);
		query.setParameter("id", id);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
