package tn.esprit.pidev.massconnection.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.pidev.massconnection.entities.Challenge;
import tn.esprit.pidev.massconnection.entities.Crowd;
import tn.esprit.pidev.massconnection.entities.Project;
import tn.esprit.pidev.massconnection.servicesinterface.ChallengeCrudLocal;
import tn.esprit.pidev.massconnection.servicesinterface.ChallengeCrudRemote;

/**
 * Session Bean implementation class ChallengeCrud
 */
@Stateless
public class ChallengeCrud extends AbstractFacade<Challenge> implements
		ChallengeCrudRemote, ChallengeCrudLocal {

	@PersistenceContext(unitName = "MassconnectionEJB")
	private EntityManager em;

	public ChallengeCrud() {
		super(Challenge.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public Long countChallengeToValidate() {
		Query query = em
				.createQuery("select count(c) from Challenge c where c.seen=0");
		return (Long) query.getSingleResult();
	}

	public ArrayList<Challenge> searchChallenge(String arg) {

		ArrayList<Integer> challengeId;
		ArrayList<Challenge> challenges = new ArrayList<Challenge>();

		Query req = em
				.createNativeQuery("select c.id from challenge c WHERE title like :a or description like :a");
		challengeId = (ArrayList<Integer>) req.setParameter("a", '%' + arg + '%')
				.getResultList();

		for (Integer id : challengeId) {
			challenges.add(find(id));
		}
		return challenges;
	}
	public void seeChallenge(int id) {
		Challenge challenge = em.find(Challenge.class, id);
		challenge.setSeen(1);
		em.merge(challenge);
	}
	
	public List<Challenge> retrieveChallengeToValidate() {
		Query query = em.createQuery("select c from Challenge c where c.seen=0");
		return query.getResultList();
	}
	@Override
	public List<Challenge> getAllChallenge() {
		Query req=em.createQuery("select c from Challenge c");
		return req.getResultList();
	}

	@Override
	public List<Challenge> getChallengeByUsername(int id) {
		TypedQuery<Challenge> query = em
				.createQuery(
						"select c from Challenge c where c.crowd.id= :id",
						Challenge.class);
		query.setParameter("id", id);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	@Override
	public Challenge FindChallengeById(int id){
		Challenge challenge=em.find(Challenge.class, id);
		return challenge;
		
	}
	public List<Challenge> searchChallenge2(String arg) {

		TypedQuery<Challenge> req = em.createNamedQuery("challange.all",
				Challenge.class);
		req.setParameter("args",'%' + arg + '%');

		return req.getResultList();

	}
}
