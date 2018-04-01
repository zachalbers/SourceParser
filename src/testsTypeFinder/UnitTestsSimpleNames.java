package testsTypeFinder;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import mainFiles.TypeFinder;

public class UnitTestsSimpleNames {

	// BASEDIR should be the directory to the 'SENG300-Iteration1' folder
	private static String BASEDIR = System.getProperty("user.dir");

	
		/**
	 * Tests that the correct exception is thrown when passed too few arguments
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTooFewArgs() {

	  String[] args = {};
	  
	    TypeFinder finder = new TypeFinder();
	    finder.run(args);
	}


	/**
	 * Tests that the correct exception is thrown when passed too many arguments
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTooManyArgs() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir1", "String", "int"};
	  
	    TypeFinder finder = new TypeFinder();
	    finder.run(args);
	}

	/**
	 * Tests that the correct exception is thrown when passed just the directory
	 */
	@Test
	public void testJustDir() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir1"};
	  
	    TypeFinder finder = new TypeFinder();
	    finder.run(args);
	    assertNull(finder.outputString);
	    assertNotNull(finder.allOutputStrings);
	}
	/**
	 * Tests for the correct output when given an empty file.
	 */
	@Test
	public void testEmptyFile() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir1", "String"};



	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  assertEquals("String. Declarations found: 0; references found: 0.", finder.outputString);
	  
	}

	/**
	 * Tests that the correct output is given when file is in a bunch of directories.
	 * used to test recusrive function
	 */
	@Test
	public void testRecursiveDir() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir18", "int"};
	  
	    TypeFinder finder = new TypeFinder();
	    finder.run(args);
	    assertEquals("int. Declarations found: 0; references found: 9.", finder.outputString);
	}
	
	/**
	 * Tests for the correct output when given files with comments containing declarations and references.
	 */
	@Test
	public void testComments() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir2", "String"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  assertEquals("String. Declarations found: 0; references found: 1.", finder.outputString);
	  
	}


	/**
	 * Tests for the correct output when given an enum with declarations and references.
	 */
	@Test
	public void testEnum() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir3", "Day"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("Day. Declarations found: 1; references found: 2.", finder.outputString);
	  
	}	

	/**
	 * Tests for the correct output when given a class with declarations and references.
	 */
	@Test
	public void testClasses() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir3", "Vehicle"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("Vehicle. Declarations found: 1; references found: 3.", finder.outputString);
	  
	}

	/**
	 * Tests for the correct output when given an annotation with declarations and references
	 */
	@Test
	public void testAnnotation() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir14", "annotation"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);

	  assertEquals("annotation. Declarations found: 1; references found: 1.", finder.outputString);
	  
	}

	/**
	 * Tests for the correct output when given an Inner Class with declarations and references
	 */
	@Test
	public void testInnerClass() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir15", "InnerClass"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);

	  assertEquals("InnerClass. Declarations found: 1; references found: 2.", finder.outputString);
	  
	}
	
	/**
	 * Tests for the correct output when given an Nested Class with declarations and references
	 */
	@Test
	public void testNestedClass() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir16", "NestedClass"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("NestedClass. Declarations found: 1; references found: 2.", finder.outputString);
	  
	}
	
	/**
	 * Tests for the correct output when given an Constructor with declarations and references
	 */
	@Test
	public void testConstructor() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir17", "Test"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("Test. Declarations found: 1; references found: 3.", finder.outputString);
	  
	}
	
	/**
	 * Tests for the correct output when given a directory where there is no java file (in this case there is one java file that is empty)
	 * But it shouldn't affect this test
	 */
	@Test
	public void testNotJavaFile() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir1", "int"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("int. Declarations found: 0; references found: 0.", finder.outputString);
	  
	}
	
	/**
	 * Tests that the correct exception is thrown when passed an invalid directory.
	 */
	@Test(expected = NullPointerException.class)
	public void testFalseDir() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "NoDir", "Vehicle"};
	  
	    TypeFinder finder = new TypeFinder();
	    finder.run(args);
	}


	/**
	 * Tests that the correct output is given when the java-type is in multiple files.
	 */
	@Test
	public void testMultipleFiles() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir3", "String"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("String. Declarations found: 0; references found: 7.", finder.outputString);
	}


	/**
	 * Tests that basic parameters in methods and initializers are counted correctly.
	 */
	@Test
	public void testParameters() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir4", "int"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("int. Declarations found: 0; references found: 9.",  finder.outputString);
	}

	/**
	 * Tests that the correct output is given when the type is in an import statement.
	 */
	@Test
	public void testImport() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir5", "BufferedReader"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("BufferedReader. Declarations found: 0; references found: 3.", finder.outputString);
	}

	/**
	 * Tests that the correct output is given when the type is an exception used in a try-catch-statement.
	 */
	@Test
	public void testCatchExceptions() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir5", "IOException"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("IOException. Declarations found: 0; references found: 3.", finder.outputString);
	}

	/**
	 * Tests that the correct output is given when throwing a new exception.
	 */
	@Test
	public void testThrowNewExceptions() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir5", "IllegalArgumentException"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("IllegalArgumentException. Declarations found: 0; references found: 1.", finder.outputString);
	}

	/**
	 * Tests that the correct output is given when a method throws an exception.
	 */
	@Test
	public void testThrowsExceptions() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir5", "NullPointerException"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("NullPointerException. Declarations found: 0; references found: 1.", finder.outputString);
	}

	/**
	 * Tests that the correct output is given when a method throws an exception.
	 */
	@Test
	public void testReturnTypes() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir6", "Double"};

	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  
	  assertEquals("Double. Declarations found: 0; references found: 3.", finder.outputString);
	}
	
	


}
