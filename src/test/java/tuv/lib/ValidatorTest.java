package tuv.lib;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tuv.lib.models.User.Possition;
import tuv.lib.models.Validator;

public class ValidatorTest {

	@Test
	public void testEmptyString() {
		String str = "";
		assertEquals(Validator.isNullOrEmpty(str), true);
	}

	@Test
	public void testNullString() {
		String str = null;
		assertEquals(Validator.isNullOrEmpty(str), true);
	}

	@Test
	public void testWhiteString() {
		String str = "  \t ";
		assertEquals(Validator.isNullOrEmpty(str), true);
	}

	@Test
	public void testNormString() {
		String str = "	some not emptry test	     	";
		assertEquals(Validator.isNullOrEmpty(str), false);
	}

	@Test
	public void testNonChars() {
		String str = " $ome wro0ng test	";
		assertEquals(Validator.hasCharsOnly(str), false);
	}

	@Test
	public void testEmptryStrForChars() {
		String str = "   \t    ";
		assertEquals(Validator.hasCharsOnly(str), false);
	}

	@Test
	public void testNormChars() {
		String str = " some normal text";
		assertEquals(Validator.hasCharsOnly(str), true);
	}
	
	public void testEmptyPhone() {
		String str = "          ";
		assertEquals(Validator.checkPhone(str), false);
	}
	
	@Test
	public void testWrongSizeBigPhone() {
		String str = "123456789610";
		assertEquals(Validator.checkPhone(str), false);
	}
	
	@Test
	public void testWrongPhone() {
		String str = "12345abcde";
		assertEquals(Validator.checkPhone(str), false);
	}
	
	@Test
	public void testCorrectPhone() {
		String str = "1234567890";
		assertEquals(Validator.checkPhone(str), true);
	}
	
	@Test
	public void testWrongSizeSmallPhone() {
		String str = "12345670";
		assertEquals(Validator.checkPhone(str), false);
	}

	@Test
	public void testNonDigits() {
		String str = " 12344567sdfg ";
		assertEquals(Validator.hasDigitsOnly(str), false);
	}

	@Test
	public void testEmptryDigits() {
		String str = "   \t    ";
		assertEquals(Validator.hasCharsOnly(str), false);
	}

	@Test
	public void testNormalDigits() {
		String str = "15965479";
		assertEquals(Validator.hasDigitsOnly(str), true);
	}
	
	@Test
	public void testSpecialDigits() {
		String str = "1596#@!479";
		assertEquals(Validator.hasCharsOnly(str), false);
	}
	
	@Test
	public void testDigitsWithSpaces() {
		String str = "  1596567   ";
		assertEquals(Validator.hasDigitsOnly(str), false);
	}
}
