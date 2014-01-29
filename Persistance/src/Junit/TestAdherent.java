package Junit;

import static org.junit.Assert.*;

import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Test;

public class TestAdherent {
	private final static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
