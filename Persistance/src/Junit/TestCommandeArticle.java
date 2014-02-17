package Junit;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Test;

import Bean.Article;
import Bean.Commande;
import Bean.CommandeArticle;
import Bean.CommandeArticleId;

public class TestCommandeArticle {
	private final static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	@Test
	public void testSansPK() {
		//un article de la commande
		CommandeArticle ca = new CommandeArticle();
		ca.setQuantiteArticle(2);
		
		//vérification
		Set<ConstraintViolation<CommandeArticle>> constraintViolations = validator.validate(ca);
		print(constraintViolations);
		validator.validate(ca);
	}
	
	@Test
	public void testArticleInvalide() {
		//une commande
		Commande commande = new Commande();
		commande.setId(1234);
		commande.setDateCommande(new Date());

		//un article de la commande
		CommandeArticle ca = new CommandeArticle();
		CommandeArticleId laKey = new CommandeArticleId();
		laKey.setArticle(null);
		laKey.setCommande(commande.getId());
		ca.setKey(laKey);
		ca.setQuantiteArticle(2);
		
		//vérification
		Set<ConstraintViolation<CommandeArticle>> constraintViolations = validator.validate(ca);
		print(constraintViolations);
		validator.validate(ca);
	}
	
	@Test
	public void testCommandeInvalide() {
		//un article
		Article article = new Article();
		article.setCode("aqua");
		article.setNom("Aquarium Crabe");
		article.setPrix(90);
		article.setStock(2);

		//un article de la commande
		CommandeArticle ca = new CommandeArticle();
		CommandeArticleId laKey = new CommandeArticleId();
		laKey.setArticle(article.getCode());
		laKey.setCommande(null);
		ca.setKey(laKey);
		ca.setQuantiteArticle(2);
		
		//vérification
		Set<ConstraintViolation<CommandeArticle>> constraintViolations = validator.validate(ca);
		print(constraintViolations);
		validator.validate(ca);
	}
	
	@Test
	public void testQuantiteInvalide() {
		//une commande
		Commande commande = new Commande();
		commande.setId(1234);
		commande.setDateCommande(new Date());
		
		//un article
		Article article = new Article();
		article.setCode("aqua");
		article.setNom("Aquarium Crabe");
		article.setPrix(90);
		article.setStock(2);

		//un article de la commande
		CommandeArticle ca = new CommandeArticle();
		CommandeArticleId laKey = new CommandeArticleId();
		laKey.setArticle(article.getCode());
		laKey.setCommande(commande.getId());
		ca.setKey(laKey);
		ca.setQuantiteArticle(-1);
		
		//vérification
		Set<ConstraintViolation<CommandeArticle>> constraintViolations = validator.validate(ca);
		print(constraintViolations);
		validator.validate(ca);
	}
	
	private <T> void print( Set<ConstraintViolation<T>> errors ) {
		System.out.println("Debut test :");
		for ( ConstraintViolation<T> cv : errors ) {
			System.out.println(" . " + cv.getPropertyPath() 
					+ " (in " + cv.getRootBeanClass().getSimpleName() 
					+ ") " + cv.getMessage() );
		}
		System.out.println("fin test.");
	}

}
