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

public class CommandeEntityManager {
	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction t;

	public CommandeEntityManager() {
		emf = Persistence.createEntityManagerFactory("Persistance");
		em = (EntityManager) emf.createEntityManager();
		t = em.getTransaction();
	}

	public Commande trouver(int id) {
		return em.find(Commande.class, id);
	}

	public void supprimer(Commande commande) {
		// pour supprimer une commande, il faut au préalable supprimer les
		// articles de la commande (table COMMANDE_ARTICLE) car existence d'une
		// clé étrangère vers la table COMMANDE
		t.begin();
		em.remove(commande);
		t.commit();
	}

	public void supprimer(int id) {
		t.begin();
		em.remove(id);
		t.commit();
	}

	public void creer(Commande commande) {
		t.begin();
		em.persist(commande);
		t.commit();
	}

	public void modifier(Commande commande) {
		t.begin();
		em.merge(commande);
		t.commit();
	}
	
	public List<Commande> chercherCommandeDunAdherent(Adherent adh) {
		Query query = em.createQuery("select cm from Commande cm where cm.lAdherent = :a");
		query.setParameter("a", adh);
		return query.getResultList();
	}

	public void close() {
		em.close();
		emf.close();
	}

}
