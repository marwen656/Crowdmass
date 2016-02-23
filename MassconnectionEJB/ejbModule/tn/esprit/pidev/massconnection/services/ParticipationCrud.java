package tn.esprit.pidev.massconnection.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.pidev.massconnection.entities.Challenge;
import tn.esprit.pidev.massconnection.entities.Participation;
import tn.esprit.pidev.massconnection.servicesinterface.ParticipationCrudLocal;
import tn.esprit.pidev.massconnection.servicesinterface.ParticipationCrudRemote;

/**
 * Session Bean implementation class ParticipationCrud
 */
@Stateless
public class ParticipationCrud extends AbstractFacade<Participation> implements
		ParticipationCrudRemote, ParticipationCrudLocal {

	@PersistenceContext(unitName = "MassconnectionEJB")
	private EntityManager em;

	public ParticipationCrud() {
		super(Participation.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public List<String> topParticipationPerChallenge() {
		Map<String, Long> stat = new HashMap<String, Long>();
		Query query = em
				.createNativeQuery("select count(*)  ,c.title from participation p,challenge c where p.idChallenge=c.id group by c.title order by count(*) desc");

		List<String> list = query.setMaxResults(4).getResultList();
		return list;

	}
	public Participation GetParticipationByCrowdAndChallenge(int idcrowd,int idchallenge){
	//	Query query = em.createQuery("select p from Participation p where p.idCrowd= :idcrowd and p.idChalenge= :idchallenge");
		TypedQuery<Participation> query = em
				.createQuery(
						"select p from Participation p where p.participationPK.idCrowd= :idcrowd and p.participationPK.idChallenge= :idchallenge",
						Participation.class);
		query.setParameter("idcrowd", idcrowd);
		query.setParameter("idchallenge", idchallenge);
		
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
