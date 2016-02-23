package tn.esprit.pidev.massconnection.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.pidev.massconnection.entities.Challenge;
import tn.esprit.pidev.massconnection.entities.Crowd;
import tn.esprit.pidev.massconnection.servicesinterface.CrowdCrudLocal;
import tn.esprit.pidev.massconnection.servicesinterface.CrowdCrudRemote;

/**
 * Session Bean implementation class CrowdCrud
 */
@Stateless
public class CrowdCrud extends AbstractFacade<Crowd> implements
		CrowdCrudRemote, CrowdCrudLocal {

	@PersistenceContext(unitName = "MassconnectionEJB")
	private EntityManager em;

	public CrowdCrud() {
		super(Crowd.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public Map<String, Long> statisticsTypesOfCrowds() {
		Long nbre;
		Query query;
		Map<String, Long> stat = new HashMap<String, Long>();

		query = em.createNamedQuery("Crowd.statisticsCrowdsCreator");

		nbre =   (Long) query.getSingleResult();
		stat.put("Creator", nbre);

		query = em.createNamedQuery("Crowd.statisticsCrowdsInvestor");

		nbre =   (Long) query.getSingleResult();
		stat.put("Investor", nbre);
		query = em.createNamedQuery("Crowd.statisticsCrowdsChallengeCreator");
		nbre =   (Long) query.getSingleResult();
		stat.put("Challenge Creator", nbre);

		query = em.createNamedQuery("Crowd.statisticsCrowdsChallengeSolver");

		nbre =   (Long) query.getSingleResult();
		stat.put("Challenge Solver", nbre);
		return stat;

	}
	public void deleteCrowd(int id) {
		Crowd cp=em.find(Crowd.class,id );
			em.remove(cp);
			
			
		}
	/*
	 * Method block the crowd which his id passed in parametre
	 * @see tn.esprit.pidev.massconnection.servicesinterface.CrowdCrudRemote#lockCrowd(int)
	 */
	public void lockCrowd(int id){
		Crowd crowd=em.find(Crowd.class, id);
		crowd.setStatus(0);
		em.merge(crowd);
		
	}
	/*
	 * Method unblock the crowd which his id passed in parametre
	 * @see tn.esprit.pidev.massconnection.servicesinterface.CrowdCrudRemote#lockCrowd(int)
	 */
	public void unlockCrowd(int id){
		Crowd crowd=em.find(Crowd.class, id);
		crowd.setStatus(1);
		em.merge(crowd);
		
	}
	
	public ArrayList<Crowd> searchAccount(String arg) {
		
		ArrayList<Integer> crowds ;
		ArrayList<Crowd> corwds = new ArrayList<Crowd>();
		
		Query req=em.createNativeQuery("select cr.id from t_user cr WHERE cr.userName like :a or cr.firstName like :a or cr.lastName like :a and cr.DTYPE='Crowd'");
		crowds=	(ArrayList<Integer>)req.setParameter("a",'%' + arg + '%' ).getResultList();



	for (Integer id : crowds) {
		corwds.add(find(id));
	}
		return corwds;
	}
	
	public Crowd retrieveCrowdtByinformations(String userName,
			String password) {
		TypedQuery<Crowd> query = em
				.createQuery(
						"select c from Crowd c where c.userName= :userName and c.password= :password",
						Crowd.class);
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}
	public Crowd retrieveCrowdtByUserName(String userName) {
		TypedQuery<Crowd> query = em
				.createQuery(
						"select c from Crowd c where c.userName= :userName",
						Crowd.class);
		query.setParameter("userName", userName);
		
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}
	public boolean crowdExist(String userName){
		
		Query query =em.createQuery("select c from Crowd c where c.userName= :userName");
		query.setParameter("userName", userName);
		if (query.getSingleResult()!=null)
			return true;
		return false;
	}
	@Override
	public Crowd FindCrowdById(int id){
		Crowd crowd=em.find(Crowd.class, id);
		return crowd;
		
	}
}
