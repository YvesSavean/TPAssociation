package EntityManager;

import java.util.List;

import javax.persistence.*;

import Bean.Adherent;

public class AdherentEntityManager  {
	private EntityManagerFactory emf;
	private EntityManager em ;
	
	public AdherentEntityManager(){
		emf = Persistence.createEntityManagerFactory("Persistance");
		em = (EntityManager) emf.createEntityManager();
	}
	
	public Adherent trouver(String string){
		return em.find(Adherent.class, string);
	}
	
	public void supprimer(Adherent lAdherent){
		em.remove(lAdherent);
	}
	
	public void supprimer(String id){
		em.remove(id);
	}
	
	public void creer(Adherent lAdherent){
		em.persist(lAdherent);
	}
	
	public void modifier(Adherent lAdherent){
		em.merge(lAdherent);
	}
	
	public List<Adherent> ChercherAdherents(){
		Query query = em.createQuery("from Adherent ");
		return query.getResultList();
	}
	
	public void close(){
		em.close();
		emf.close();
	}

}
