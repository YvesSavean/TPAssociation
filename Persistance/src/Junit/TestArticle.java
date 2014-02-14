package Junit;

import static org.junit.Assert.*;

import org.junit.Test;

import Bean.Adherent;
import EntityManager.ArticleEntityManager;

public class TestArticle {

	@Test
	public void testTrouverArticle(){
		ArticleEntityManager aem= new ArticleEntityManager();
		assertTrue("aq"==aem.trouver("aq").getCode());
		aem.close();
	}

}
