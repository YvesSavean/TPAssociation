package EntityManager;

import java.util.List;

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
		Query query = em.createQuery("select ca from CommandeArticle ca where ca.Key.commande = :c");
		query.setParameter("c", cmd.getId());
		return query.getResultList();

	}
	
	public void close() {
		em.close();
		emf.close();
	}
	
	

}
