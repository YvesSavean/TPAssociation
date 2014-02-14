package Junit;

import static org.junit.Assert.*;

import org.junit.Test;

import Bean.Article;
import EntityManager.ArticleEntityManager;

public class TestArticleEntityManager {
	
	@Test
	public void TestDecrement(){
		ArticleEntityManager aem = new ArticleEntityManager();
		Article lArticle = new Article();
		lArticle.setStock(2);
		lArticle.setCode("ex");
		lArticle.setPrix(125);
		lArticle.setNom("exemple");
		aem.creer(lArticle);
		aem.décrementer(lArticle);
		assertTrue(1==aem.trouver("ex").getStock());
		aem.supprimer(lArticle);
	}
	
	@Test
	public void testIncrement() {
		ArticleEntityManager aem = new ArticleEntityManager();
		Article lArticle = new Article();
		lArticle.setStock(1);
		lArticle.setCode("ex");
		lArticle.setPrix(125);
		lArticle.setNom("exemple");
		aem.creer(lArticle);
		aem.incrementer(lArticle);
		assertTrue(2==aem.trouver("ex").getStock());
		aem.supprimer(lArticle);
	}
	
	@Test
	public void testCreationArticle() {
		ArticleEntityManager aem = new ArticleEntityManager();
		Article lArticle = new Article();
		lArticle.setStock(1);
		lArticle.setCode("ex");
		lArticle.setPrix(125);
		lArticle.setNom("exemple");
		aem.creer(lArticle);
		aem.supprimer(lArticle);
	}
}
