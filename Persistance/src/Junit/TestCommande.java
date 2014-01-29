package Junit;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Test;
import Bean.Commande;

public class TestCommande {
	private final static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	@Test 
	public void testIdInvalide() {
		Commande uneCommande = new Commande();
		uneCommande.setId(null);
		uneCommande.setDateCommande(new Date());
		Set<ConstraintViolation<Commande>> constraintViolations = validator.validate(uneCommande);
		print(constraintViolations);
		validator.validate(uneCommande);
	}
	
	@Test 
	public void testIdNormal() {
		Commande uneCommande = new Commande();
		uneCommande.setId(1234);
		uneCommande.setDateCommande(new Date());
		Set<ConstraintViolation<Commande>> constraintViolations = validator.validate(uneCommande);
		print(constraintViolations);
		validator.validate(uneCommande);
	}
	
	@Test 
	public void testDateInvalide() {
		Commande uneCommande = new Commande();
		uneCommande.setId(1234);
		uneCommande.setDateCommande(null);
		Set<ConstraintViolation<Commande>> constraintViolations = validator.validate(uneCommande);
		print(constraintViolations);
		validator.validate(uneCommande);
	}
	
	@Test 
	public void testDateNormal() {
		Commande uneCommande = new Commande();
		uneCommande.setId(1234);
		uneCommande.setDateCommande(new Date());
		Set<ConstraintViolation<Commande>> constraintViolations = validator.validate(uneCommande);
		print(constraintViolations);
		validator.validate(uneCommande);
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