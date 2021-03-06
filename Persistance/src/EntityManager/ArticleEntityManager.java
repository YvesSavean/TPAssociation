package EntityManager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Bean.Article;

public class ArticleEntityManager {
	private EntityManagerFactory emf;
	private EntityManager em ;
	private EntityTransaction t;
	
	public  ArticleEntityManager(){
		emf = Persistence.createEntityManagerFactory("Persistance");
		em = (EntityManager) emf.createEntityManager();
		t = em.getTransaction();
	}
	
	public Article trouver(String id){
		return em.find(Article.class, id);
	}
	
	public void supprimer(Article lArticle){
		t.begin();
			em.remove(lArticle);
		t.commit();
	}
	
	public void supprimer(int id){
		t.begin();
			em.remove(id);
		t.commit();
	}
	
	public void creer(Article lArticle){
		t.begin();
			em.persist(lArticle);
		t.commit();
	}
	
	public void modifier(Article lArticle){
		t.begin();
			em.merge(lArticle);
		t.commit();
	}
	
	
	public List<Article> ChercherArticles(){
		Query query = em.createQuery("from Article");
		return query.getResultList();
	}

	public void incrementer(Article lArticle) {
		lArticle.setStock(lArticle.getStock()+1);
		this.modifier(lArticle);
	}

	public void décrementer(Article lArticle) {
		if(lArticle.getStock()!=0){
			lArticle.setStock(lArticle.getStock()-1);
			this.modifier(lArticle);
		}
	}
	 public void close(){
		 em.close();
		 emf.close();
	 }
}

