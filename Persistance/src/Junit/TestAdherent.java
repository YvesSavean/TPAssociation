package Junit;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.Test;

import Bean.Adherent;

public class TestAdherent {
	private final static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	

	@Test
	public void testSansIdentifiant() {
		Adherent unAdherent = new Adherent();
		unAdherent.setNomDeFamille("Cristofson");
		unAdherent.setMotDePasse("12dgerg");
		unAdherent.setPrenom("cristophe");
		Set<ConstraintViolation<Adherent>> constraintViolations = validator.validate(unAdherent);
		print(constraintViolations);
		validator.validate(unAdherent);
	}
		
	@Test
	public void testSizeIdentifiantBonneValeurs() {
		Adherent unAdherent = new Adherent();
		unAdherent.setIdentifiant("GUGUEN");
		unAdherent.setNomDeFamille("Cristofson");
		unAdherent.setMotDePasse("12dgerg");
		unAdherent.setPrenom("cristophe");
		Set<ConstraintViolation<Adherent>> constraintViolations = validator.validate(unAdherent);
		print(constraintViolations);
		validator.validate(unAdherent);
	}
	
	@Test
	public void testSizeIdentifiantSizeTropfaible() {
		Adherent unAdherent = new Adherent();
		unAdherent.setIdentifiant("   ");
		unAdherent.setNomDeFamille("Cristofson");
		unAdherent.setMotDePasse("12dgerg");
		unAdherent.setPrenom("cristophe");
		Set<ConstraintViolation<Adherent>> constraintViolations = validator.validate(unAdherent);
		print(constraintViolations);
		validator.validate(unAdherent);
	}
	
	@Test
	public void testSizeIdentifiantSizeTropGrand() {
		Adherent unAdherent = new Adherent();
		unAdherent.setIdentifiant("afjfhdufkzlgjeijkdkglemgkdjtnekgfk");
		unAdherent.setNomDeFamille("Cristofson");
		unAdherent.setMotDePasse("12dgerg");
		unAdherent.setPrenom("cristophe");
		Set<ConstraintViolation<Adherent>> constraintViolations = validator.validate(unAdherent);
		print(constraintViolations);
		validator.validate(unAdherent);
	}
	
	@Test
	public void testSansMotDePasse() {
		Adherent unAdherent = new Adherent();
		unAdherent.setIdentifiant("GUGUEN");
		unAdherent.setNomDeFamille("Cristofson");
		unAdherent.setPrenom("cristophe");
		Set<ConstraintViolation<Adherent>> constraintViolations = validator.validate(unAdherent);
		print(constraintViolations);
		validator.validate(unAdherent);
	}
	
	@Test
	public void testMotDePasseSizeTropPetite() {
		Adherent unAdherent = new Adherent();
		unAdherent.setIdentifiant("GUGUEN");
		unAdherent.setNomDeFamille("Cristofson");
		unAdherent.setPrenom("cristophe");
		unAdherent.setMotDePasse(" ");
		Set<ConstraintViolation<Adherent>> constraintViolations = validator.validate(unAdherent);
		print(constraintViolations);
		validator.validate(unAdherent);
	}
	
	@Test
	public void testMotDePasseSizeNormal() {
		Adherent unAdherent = new Adherent();
		unAdherent.setIdentifiant("GUGUEN");
		unAdherent.setNomDeFamille("Cristofson");
		unAdherent.setPrenom("cristophe");
		unAdherent.setMotDePasse("gqzefqze");
		Set<ConstraintViolation<Adherent>> constraintViolations = validator.validate(unAdherent);
		print(constraintViolations);
		validator.validate(unAdherent);
	}
	
	@Test
	public void testMotDePasseSizeTropGrande() {
		Adherent unAdherent = new Adherent();
		unAdherent.setIdentifiant("GUGUEN");
		unAdherent.setNomDeFamille("Cristofson");
		unAdherent.setPrenom("cristophe");
		unAdherent.setMotDePasse("fgesggzfbveszbqsghegsdghsegqseg");
		Set<ConstraintViolation<Adherent>> constraintViolations = validator.validate(unAdherent);
		print(constraintViolations);
		validator.validate(unAdherent);
	}
	
	@Test
	public void testSansNomfamille() {
		Adherent unAdherent = new Adherent();
		unAdherent.setIdentifiant("GUGUEN");
		unAdherent.setPrenom("cristophe");
		unAdherent.setMotDePasse("gqzefqze");
		Set<ConstraintViolation<Adherent>> constraintViolations = validator.validate(unAdherent);
		print(constraintViolations);
		validator.validate(unAdherent);
	}
	
	@Test
	public void testNomfamilleNormal() {
		Adherent unAdherent = new Adherent();
		unAdherent.setIdentifiant("GUGUEN");
		unAdherent.setNomDeFamille("Cristofson");
		unAdherent.setPrenom("cristophe");
		unAdherent.setMotDePasse("gqzefqze");
		Set<ConstraintViolation<Adherent>> constraintViolations = validator.validate(unAdherent);
		print(constraintViolations);
		validator.validate(unAdherent);
	}
	
	@Test
	public void testNomfamilleSizeTropPetite() {
		Adherent unAdherent = new Adherent();
		unAdherent.setIdentifiant("GUGUEN");
		unAdherent.setNomDeFamille(" ");
		unAdherent.setPrenom("cristophe");
		unAdherent.setMotDePasse("gqzefqze");
		Set<ConstraintViolation<Adherent>> constraintViolations = validator.validate(unAdherent);
		print(constraintViolations);
		validator.validate(unAdherent);
	}
	
	@Test
	public void testNomfamilleSizeTropGrande() {
		Adherent unAdherent = new Adherent();
		unAdherent.setIdentifiant("GUGUEN");
		unAdherent.setNomDeFamille("fzfqfhzrhsherhshshsgfsehsdfergsehfsderg");
		unAdherent.setPrenom("cristophe");
		unAdherent.setMotDePasse("gqzefqze");
		Set<ConstraintViolation<Adherent>> constraintViolations = validator.validate(unAdherent);
		print(constraintViolations);
		validator.validate(unAdherent);
	}
	
	@Test
	public void testSansPrenom() {
		Adherent unAdherent = new Adherent();
		unAdherent.setIdentifiant("GUGUEN");
		unAdherent.setNomDeFamille("Cristofson");
		unAdherent.setMotDePasse("gqzefqze");
		Set<ConstraintViolation<Adherent>> constraintViolations = validator.validate(unAdherent);
		print(constraintViolations);
		validator.validate(unAdherent);
	}
	
	@Test
	public void testAvecPrenomTropCourt() {
		Adherent unAdherent = new Adherent();
		unAdherent.setIdentifiant("GUGUEN");
		unAdherent.setNomDeFamille("Cristofson");
		unAdherent.setPrenom(" ");
		unAdherent.setMotDePasse("gqzefqze");
		Set<ConstraintViolation<Adherent>> constraintViolations = validator.validate(unAdherent);
		print(constraintViolations);
		validator.validate(unAdherent);
	}
	
	@Test
	public void testAvecPrenomTroplong() {
		Adherent unAdherent = new Adherent();
		unAdherent.setIdentifiant("GUGUEN");
		unAdherent.setNomDeFamille("Cristofson");
		unAdherent.setPrenom("qzgserhfihdfjhndrigdrigjdgidjgdkhgdiodfh");
		unAdherent.setMotDePasse("gqzefqze");
		Set<ConstraintViolation<Adherent>> constraintViolations = validator.validate(unAdherent);
		print(constraintViolations);
		validator.validate(unAdherent);
	}
	
	@Test
	public void testAvecPrenomNormal() {
		Adherent unAdherent = new Adherent();
		unAdherent.setIdentifiant("GUGUEN");
		unAdherent.setNomDeFamille("Cristofson");
		unAdherent.setPrenom("cristophe");
		unAdherent.setMotDePasse("gqzefqze");
		Set<ConstraintViolation<Adherent>> constraintViolations = validator.validate(unAdherent);
		print(constraintViolations);
		validator.validate(unAdherent);
	}
	
	@Test
	public void testAdresseTropGrande() {
		Adherent unAdherent = new Adherent();
		unAdherent.setIdentifiant("GUGUEN");
		unAdherent.setNomDeFamille("Cristofson");
		unAdherent.setPrenom("cristophe");
		unAdherent.setMotDePasse("gqzefqze");
		unAdherent.setAdresse("fserghtrhtrhrhthfrthfhrfthfthtrhfrthrghtrhftrhftrghfrthftrhdeg");
		Set<ConstraintViolation<Adherent>> constraintViolations = validator.validate(unAdherent);
		print(constraintViolations);
		validator.validate(unAdherent);
	}
	
	@Test
	public void testComplementAdresseTropGrande() {
		Adherent unAdherent = new Adherent();
		unAdherent.setIdentifiant("GUGUEN");
		unAdherent.setNomDeFamille("Cristofson");
		unAdherent.setPrenom("cristophe");
		unAdherent.setMotDePasse("gqzefqze");
		unAdherent.setComplementAdresse("fserghtrhtrhrhthfrthfhrfthfthtrhfrthrghtrhftrhftrghfrthftrhdeg");
		Set<ConstraintViolation<Adherent>> constraintViolations = validator.validate(unAdherent);
		print(constraintViolations);
		validator.validate(unAdherent);
	}
	
	@Test
	public void testCodePostalTropGrande() {
		Adherent unAdherent = new Adherent();
		unAdherent.setIdentifiant("GUGUEN");
		unAdherent.setNomDeFamille("Cristofson");
		unAdherent.setPrenom("cristophe");
		unAdherent.setMotDePasse("gqzefqze");
		unAdherent.setCodePostal("123456");
		Set<ConstraintViolation<Adherent>> constraintViolations = validator.validate(unAdherent);
		print(constraintViolations);
		validator.validate(unAdherent);
	}

	@Test
	public void testVilleTropGrande() {
		Adherent unAdherent = new Adherent();
		unAdherent.setIdentifiant("GUGUEN");
		unAdherent.setNomDeFamille("Cristofson");
		unAdherent.setPrenom("cristophe");
		unAdherent.setMotDePasse("gqzefqze");
		unAdherent.setVille("qerhtrhjtrhtrhftrxgjfgtjftrxjrftxjn,xfjnxfjxfjxfrjcfseheherhdrhftnhcf");
		Set<ConstraintViolation<Adherent>> constraintViolations = validator.validate(unAdherent);
		print(constraintViolations);
		validator.validate(unAdherent);
	}
	
	@Test
	public void testPaysTropGrand() {
		Adherent unAdherent = new Adherent();
		unAdherent.setIdentifiant("GUGUEN");
		unAdherent.setNomDeFamille("Cristofson");
		unAdherent.setPrenom("cristophe");
		unAdherent.setMotDePasse("gqzefqze");
		unAdherent.setPays("qerhtrhjtrhtrhftrxgjfgtjftrxjrftxjn,xfjnxfjxfjxfrjcfseheherhdrhftnhcf");
		Set<ConstraintViolation<Adherent>> constraintViolations = validator.validate(unAdherent);
		print(constraintViolations);
		validator.validate(unAdherent);
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
