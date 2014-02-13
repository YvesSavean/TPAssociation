package EntityManager;

import java.util.List;

import javax.persistence.*;

import Bean.Adherent;

public class AdherentEntityManager {
	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction t;

	public AdherentEntityManager() {
		emf = Persistence.createEntityManagerFactory("Persistance");
		em = (EntityManager) emf.createEntityManager();
		t = em.getTransaction();
	}

	public Adherent trouver(String string) {
		return em.find(Adherent.class, string);
	}

	public void supprimer(Adherent lAdherent) {
		t.begin();
			em.remove(lAdherent);
		t.commit();
	}

	public void creer(Adherent lAdherent) {
		t.begin();
			em.persist(lAdherent);
		t.commit();
	}

	public void modifier(Adherent lAdherent) {
		t.begin();
			em.merge(lAdherent);
		t.commit();
	}

	public List<Adherent> ChercherAdherents() {
		/* ce n'est pas du SQL mais du HQL d'Hibernate */
		Query query = em.createQuery("from Adherent ");
		return query.getResultList();
	}

	public void close() {
		em.close();
		emf.close();
	}

}
