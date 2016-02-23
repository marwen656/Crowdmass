package tn.esprit.pidev.massconnection.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.pidev.massconnection.entities.Category;
import tn.esprit.pidev.massconnection.entities.Project;
import tn.esprit.pidev.massconnection.servicesinterface.ProjectCrudLocal;
import tn.esprit.pidev.massconnection.servicesinterface.ProjectCrudRemote;

/**
 * Session Bean implementation class ProjectCrud
 */
@Stateless
public class ProjectCrud extends AbstractFacade<Project> implements
		ProjectCrudRemote, ProjectCrudLocal {

	@PersistenceContext(unitName = "MassconnectionEJB")
	private EntityManager em;

	public ProjectCrud() {
		super(Project.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public Long countProjectToValidate() {
		Query query = em
				.createQuery("select count(p) from Project p where p.seen=0");
		return (Long) query.getSingleResult();
	}

	public List<Project> retrieveProjectToValidate() {
		Query query = em.createQuery("select p from Project p where p.seen=0");
		return query.getResultList();
	}

	public void seeProject(int id) {
		Project project = em.find(Project.class, id);
		project.setSeen(1);
		em.merge(project);
	}

	public void validateCrowd(int id) {
		Project project = em.find(Project.class, id);
		project.setValidationStatus(true);
		em.merge(project);

	}

	public void unValidateCrowd(int id) {
		Project project = em.find(Project.class, id);
		project.setValidationStatus(false);
		em.merge(project);

	}

	public Long countByCategory(int id) {
		Category category = em.find(Category.class, id);
		Query query = em
				.createQuery("select count(p) from Project p where p.category= :c");
		query.setParameter("c", category);
		return (Long) query.getSingleResult();

	}
	@Override
	public Map<String, Long> statisticsTypesOfFundingModel() {
		Long nbre;
		Query query;
		Map<String, Long> stat = new HashMap<String, Long>();

		query = em.createNamedQuery("Project.statisticsDon");

		nbre =   (Long) query.getSingleResult();
		stat.put("Don", nbre);

		query = em.createNamedQuery("Project.statisticsReward");

		nbre =   (Long) query.getSingleResult();
		stat.put("Reward", nbre);
		query = em.createNamedQuery("Project.statisticsEquity");
		nbre =   (Long) query.getSingleResult();
		stat.put("Equity", nbre);

		query = em.createNamedQuery("Project.statisticsCredit");

		nbre =   (Long) query.getSingleResult();
		stat.put("Credit", nbre);
		return stat;

	}

	
	public ArrayList<Project> searchProjects(String arg) {
		
		ArrayList<Integer> projsId ;
		ArrayList<Project> projects = new ArrayList<Project>();
		
		Query req=em.createNativeQuery("select p.id from project p WHERE title like :a or description like :a or fundingModel like :a or validationstatus like :a");
	projsId=	(ArrayList<Integer>)req.setParameter("a",'%' + arg + '%' ).getResultList();



	for (Integer id : projsId) {
		projects.add(find(id));
	}
		return projects;
	}
}
