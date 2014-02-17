package EntityManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Bean.Adherent;
import Bean.Commande;
import Bean.CommandeArticle;

public class CommandeArticleEntityManager {
	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction t;

	public CommandeArticleEntityManager() {
		emf = Persistence.createEntityManagerFactory("Persistance");
		em = (EntityManager) emf.createEntityManager();
		t = em.getTransaction();
	}

	public void supprimer(CommandeArticle ca) {
		t.begin();
		em.remove(ca);
		t.commit();
	}

	public void creer(CommandeArticle ca) {
		t.begin();
		em.persist(ca);
		t.commit();
	}

	public void modifier(CommandeArticle ca) {
		t.begin();
		em.merge(ca);
		t.commit();
	}

	public List<CommandeArticle> chercherCommandeArticleAdh(Adherent adh,
			Commande cmd) {
		Query query = em.createQuery("from CommandeArticle where commande = "
				+ cmd + " and adherent = " + adh + "");
		return query.getResultList();

	}
	
	public List<CommandeArticle> chercherCommandeArticleAdh(
			Commande cmd) {
<<<<<<< HEAD
<<<<<<< HEAD
		Map<Integer,CommandeArticle> lesLignesArticles = new HashMap<Integer,CommandeArticle>();
		Query query = em.createNativeQuery("select * from COMMANDE_ARTICLE c where id = "+cmd.getId(),CommandeArticle.class);
=======
		Query query = em.createQuery("select ca from CommandeArticle ca where ca.Key.commande = :c");
		query.setParameter("c", cmd.getId());
>>>>>>> e3118d903b02a1b403fe033751f9c77d7067a22a
=======
		Query query = em.createQuery("select ca from CommandeArticle ca where ca.Key.commande = :c");
		query.setParameter("c", cmd.getId());
>>>>>>> e3118d903b02a1b403fe033751f9c77d7067a22a
		return query.getResultList();

	}
	
	public void close() {
		em.close();
		emf.close();
	}
	
	

}
