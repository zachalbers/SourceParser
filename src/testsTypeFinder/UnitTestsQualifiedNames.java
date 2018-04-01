package testsTypeFinder;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import mainFiles.TypeFinder;

public class UnitTestsQualifiedNames {

	// BASEDIR should be the directory to the 'SENG300-Iteration1' folder
	private static String BASEDIR = System.getProperty("user.dir");
	
	
	/**
	 * The following tests test full java-type names, including packages and import statements.
	 */




	/**
	 * Tests for the correct output when given files with comments containing declarations and references
	 * for full java-type names.
	 */
	@Test
	public void testCommentsWithFullName() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir2", "java.lang.String"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  assertEquals("java.lang.String. Declarations found: 0; references found: 1.",  finder.outputString);
	  
	}


	/**
	 * Tests for the correct output when given an enum with a full java-type name with declarations and references.
	 */
	@Test
	public void testEnumWithFullName() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir3", "package3.Vehicle.Day"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("package3.Vehicle.Day. Declarations found: 1; references found: 2.", finder.outputString);
	  
	}	

	/**
	 * Tests for the correct output when given a specific class with declarations and references.
	 */
	@Test
	public void testClassesWithFullName() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir3", "package3.Vehicle"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("package3.Vehicle. Declarations found: 1; references found: 3.", finder.outputString);
	  
	}


	

	/**
	 * Tests that the correct output is given when the type is in multiple files, but packages are different.
	 */
	@Test
	public void testMultiplePackages() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir3", "package3.Person"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("package3.Person. Declarations found: 1; references found: 0.", finder.outputString);
	}


	/**
	 * Tests that the correct output is given when the type is in an import statement for full java-type names.
	 */
	@Test
	public void testImportWithPackages() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir5", "java.io.BufferedReader"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("java.io.BufferedReader. Declarations found: 0; references found: 3.", finder.outputString);
	}

	/**
	 * Tests that the correct output is given when the type is an exception used in a try-catch-statement for full java-type names.
	 */
	@Test
	public void testCatchExceptionsWithFullName() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir5", "java.io.IOException"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("java.io.IOException. Declarations found: 0; references found: 3.", finder.outputString);
	}

	/**
	 * Tests that the correct output is given when throwing a new exception for full java-type names.
	 */
	@Test
	public void testThrowNewExceptionsWithFullName() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir5", "java.lang.IllegalArgumentException"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("java.lang.IllegalArgumentException. Declarations found: 0; references found: 1.", finder.outputString);
	}

	/**
	 * Tests that the correct output is given when a method throws an exception for full java-type names.
	 */
	@Test
	public void testThrowsExceptionsWithFullName() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir5", "java.lang.NullPointerException"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("java.lang.NullPointerException. Declarations found: 0; references found: 1.", finder.outputString);
	}

	/**
	 * Tests that the correct output is given when a method throws an exception for full java-type names.
	 */
	@Test
	public void testReturnTypeswithFullName() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir6", "Double"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("Double. Declarations found: 0; references found: 3.", finder.outputString);
	}
	
	

}
