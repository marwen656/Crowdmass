package tn.esprit.pidev.massconnection.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.pidev.massconnection.entities.Solution;
import tn.esprit.pidev.massconnection.servicesinterface.SolutionCrudLocal;
import tn.esprit.pidev.massconnection.servicesinterface.SolutionCrudRemote;

/**
 * Session Bean implementation class SolutionCrud
 */
@Stateless
public class SolutionCrud extends AbstractFacade<Solution> implements
		SolutionCrudRemote, SolutionCrudLocal {

	@PersistenceContext(unitName = "MassconnectionEJB")
	private EntityManager em;

	public SolutionCrud() {
		super(Solution.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public List<Solution> findSolutionbyChallenge(int id) {
		Query query = em
				.createNativeQuery(
						"select s.* from solution s , participation p , challenge c  where s.participation_idChallenge = p.idChallenge and p.idChallenge = c.id and c.id= "
								+ id, Solution.class);
		return (ArrayList<Solution>) query.getResultList();
	}

	@Override
	public String getPath(int id) {
		Solution solution = em.find(Solution.class, id);

		return solution.getPath();
	}

	@Override
	public List<Solution> FindSolutionsByChallenge(int id) {
		TypedQuery<Solution> query = em.createQuery(
						"select s from Solution s where s.participation.challenge.id= :id",
						Solution.class);
		query.setParameter("id", id);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
