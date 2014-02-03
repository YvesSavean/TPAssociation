package EntityManager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Bean.Article;

public class ArticleEntityManager {
	private EntityManagerFactory emf;
	private EntityManager em ;
	
	public  ArticleEntityManager(){
		emf = Persistence.createEntityManagerFactory("Persistence");
		em = (EntityManager) emf.createEntityManager();
	}
	
	public Article trouver(int id){
		return em.find(Article.class, id);
	}
	
	public void supprimer(Article lArticle){
		em.remove(lArticle);
	}
	
	public void supprimer(int id){
		em.remove(id);
	}
	
	public void creer(Article lArticle){
		em.persist(lArticle);
	}
	
	public void modifier(Article lArticle){
		em.merge(lArticle);
	}
	
	public List<Article> ChercherAdherents(){
		Query query = em.createQuery("select * from ARTICLE");
		return query.getResultList();
	}
	
}
