package Connexion;

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
	
	public Adherent trouver(int id){
		return em.find(Adherent.class, id);
	}
	
	public void supprimer(Adherent lAdherent){
		em.remove(lAdherent);
	}
	
	public void supprimer(int id){
		em.remove(id);
	}
	
	public void creer(Adherent lAdherent){
		em.persist(lAdherent);
	}
	
	public void modifier(Adherent lAdherent){
		em.merge(lAdherent);
	}
	
	public List<Adherent> ChercherAdherents(){
		Query query = em.createQuery("select * from ADHERENT");
		return query.getResultList();
	}
	

}
