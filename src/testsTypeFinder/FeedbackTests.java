package testsTypeFinder;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import mainFiles.TypeFinder;

public class FeedbackTests {
	
	// BASEDIR should be the directory to the 'SENG300-Iteration1' folder
	private static String BASEDIR = System.getProperty("user.dir");

	/**
	 * test1
	 */
	@Test
	public void test1() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir8", "A"};



	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  assertEquals("A. Declarations found: 1; references found: 0.", finder.outputString);
	  
	}

	/**
	 * test2
	 */
	@Test
	public void test2() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir8", "B"};



	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  assertEquals("B. Declarations found: 1; references found: 9.", finder.outputString);
	  
	}

	/**
	 * test3
	 */
	@Test
	public void test3() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir8", "C"};



	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  assertEquals("C. Declarations found: 1; references found: 5.", finder.outputString);
	  
	}

	/**
	 * test4
	 */
	@Test
	public void test4() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir8", "D"};



	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  assertEquals("D. Declarations found: 1; references found: 0.", finder.outputString);
	  
	}

	/**
	 * test5
	 */
	@Test
	public void test5() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir8", "D.E"};



	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  assertEquals("D.E. Declarations found: 1; references found: 2.", finder.outputString);
	  
	}

	/**
	 * test6
	 */
	@Test
	public void test6() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir8", "E"};



	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  assertEquals("E. Declarations found: 2; references found: 5.", finder.outputString);
	  
	}

	/**
	 * test7
	 */
	@Test
	public void test7() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir8", "foo.E"};



	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  assertEquals("foo.E. Declarations found: 1; references found: 4.", finder.outputString);
	  
	}

	/**
	 * test8
	 */
	@Test
	public void test8() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir8", "F"};



	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  assertEquals("F. Declarations found: 0; references found: 0.", finder.outputString);
	  
	}

	/**
	 * test9
	 */
	@Test
	public void test9() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir8", "int"};



	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  assertEquals("int. Declarations found: 0; references found: 1.", finder.outputString);
	  
	}

}
