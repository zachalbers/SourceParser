package testsTypeFinder;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import mainFiles.TypeFinder;

public class UnitTestsJarFiles {
	
	// BASEDIR should be the directory to the 'SENG300-Iteration1' folder
	private static String BASEDIR = System.getProperty("user.dir");

	/**
	 * The following tests are analogous to "UnitTestsSimpleNames" but for is testing jar files
	 */
	
	/**
	 * Test for correct declaration and reference count given the directory path is directly to a jar file for int
	 */
	@Test
	public void testDirisJar() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir9" + File.separator + "testJarFile.jar", "int"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  assertEquals("int. Declarations found: 0; references found: 1.", finder.outputString);
	}
	
	/**
	 * Tests that the correct output is given when the java-type is in jar file that is in a jar file
	 * Used to test the recursive function
	 */
	@Test
	public void testRecursiveJar() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir11", "int"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("int. Declarations found: 0; references found: 2.", finder.outputString);
	}
	
	/**
	 * Tests that the correct output is given when the java-type is in a jar file and in a directory
	 * Used to test the recursive function
	 */
	@Test
	public void testRecursiveDirectoryJar() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir11", "TestClass3"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("TestClass3. Declarations found: 1; references found: 2.", finder.outputString);
	}
	
	
	/**
	 * Test for correct declaration and reference count given a Jar file for int 
	 */
	@Test
	public void testIntJar() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir9", "int"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  assertEquals("int. Declarations found: 0; references found: 1.", finder.outputString);
	}
	
	
	/**
	 * Test for correct declaration and reference count given a Jar file for String 
	 * (with the inclusion of comments with containing declarations and references)
	 */
	@Test
	public void testStringJar() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir9", "String"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  assertEquals("String. Declarations found: 0; references found: 2.", finder.outputString);
	}
	
	/**
	 * Tests for the correct output when given an enum with declarations and references, for a given jar file
	 */
	@Test
	public void testEnumJar() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir10", "Day"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  assertEquals("Day. Declarations found: 1; references found: 2.",  finder.outputString);	  
	}
	
	/**
	 * Tests for the correct output when given a class with declarations and references in a given jar file
	 */
	@Test
	public void testClassesJar() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir10", "Vehicle"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("Vehicle. Declarations found: 1; references found: 3.", finder.outputString);
	}
	
	/**
	 * Tests that the correct output is given when the java-type is in multiple jars files
	 */
	@Test
	public void testMultipleJar() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir11", "int"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("int. Declarations found: 0; references found: 2.", finder.outputString);
	}
	
	/**
	 * Tests that basic parameters in methods and initializers are counted correctly in jar files
	 */
	@Test
	public void testParametersJar() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir12", "int"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("int. Declarations found: 0; references found: 9.",  finder.outputString);
	}

	
	/**
	 * Tests that the correct output is given when the type is in an import statement for a given jar file
	 */
	@Test
	public void testImportJar() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir13", "BufferedReader"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("BufferedReader. Declarations found: 0; references found: 3.", finder.outputString);
	}
	
	/**
	 * Tests that the correct output is given when the type is an exception used in a try-catch-statement given a jar file
	 */
	@Test
	public void testCatchExceptionsJar() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir5", "IOException"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("IOException. Declarations found: 0; references found: 3.", finder.outputString);
	}

	/**
	 * Tests that the correct output is given when throwing a new exception for a given jar file
	 */
	@Test
	public void testThrowNewExceptions() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir13", "IllegalArgumentException"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("IllegalArgumentException. Declarations found: 0; references found: 1.", finder.outputString);
	}

	/**
	 * Tests that the correct output is given when a method throws an exception for a given jar file
	 */
	@Test
	public void testThrowsExceptions() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir13", "NullPointerException"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("NullPointerException. Declarations found: 0; references found: 1.", finder.outputString);
	}
	
	
	/**
	 * The following tests are analogous to "UnitTestsQualifiedNames" but for is testing jar files
	 */	
	
	
	
	/**
	 * Test for correct declaration and reference count given a Jar file for fully qualified name for String 
	 * (with the inclusion of comments with containing declarations and references)
	 */
	@Test
	public void testStringFullNameJar() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir9", "java.lang.String"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  assertEquals("java.lang.String. Declarations found: 0; references found: 2.",  finder.outputString);
	}
	
	
	/**
	 * Tests for the correct output when given an enum with a full java-type name with declarations and references.
	 */
	@Test
	public void testEnumFullNameJar() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir10", "package3.Vehicle.Day"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("package3.Vehicle.Day. Declarations found: 1; references found: 2.", finder.outputString);
	}	
	
	/**
	 * Tests for the correct output when given a specific class with declarations and references.
	 */
	@Test
	public void testClassesWithFullNameJar() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir10", "package3.Vehicle"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("package3.Vehicle. Declarations found: 1; references found: 3.", finder.outputString);
	  
	}
	
	/**
	 * Tests that the correct output is given when the type is in multiple files, but packages are different for a given jar file
	 */
	@Test
	public void testMultiplePackagesJar() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir10", "package3.Person"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("package3.Person. Declarations found: 1; references found: 0.", finder.outputString);
	}
	
	/**
	 * Tests that the correct output is given when the type is in an import statement for full java-type names.
	 * Given a jar file
	 */
	@Test
	public void testImportWithPackagesJar() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir13", "java.io.BufferedReader"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("java.io.BufferedReader. Declarations found: 0; references found: 3.", finder.outputString);
	}

	/**
	 * Tests that the correct output is given when the type is an exception used in a try-catch-statement for full java-type names.
	 */
	@Test
	public void testCatchExceptionsWithFullNameJar() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir13", "java.io.IOException"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("java.io.IOException. Declarations found: 0; references found: 3.", finder.outputString);
	}

	/**
	 * Tests that the correct output is given when throwing a new exception for full java-type names.
	 */
	@Test
	public void testThrowNewExceptionsWithFullNameJar() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir13", "java.lang.IllegalArgumentException"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("java.lang.IllegalArgumentException. Declarations found: 0; references found: 1.", finder.outputString);
	}

	/**
	 * Tests that the correct output is given when a method throws an exception for full java-type names.
	 */
	@Test
	public void testThrowsExceptionsWithFullNameJar() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir13", "java.lang.NullPointerException"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("java.lang.NullPointerException. Declarations found: 0; references found: 1.", finder.outputString);
	}

	
}
