// THESE J UNIT TEST CASES ARE NO LONGER VALID



package testsTypeFinder;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import mainFiles.TypeFinder;

public class UnitTestsTypeFinder {
	
	// BASEDIR should be the directory to the 'SENG300-Iteration1' folder
	private static String BASEDIR = System.getProperty("user.dir");

	
	/**
	 * Tests Normally Declared Classes.
	 * Tests for the correct output when given Declarations, Class Instances, and Variable Declarations.
	 * 
	 */
	@Test
	public void testNormalClassDeclerations() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir1"};
	  
	  List<String> correctOutput = new ArrayList<String>();
	  correctOutput.add("Normal Classes. Declarations found: 1; references found: 4.");

	  
	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  assertEquals(correctOutput,  finder.allOutputStrings);
	  
	}
	
	
	/**
	 * Tests Normally Declared Classes.
	 * Tests for the correct output when given method return types, method parameters,
	 * constructors, and Superclass references for Normal Classes.
	 * 
	 */
	@Test
	public void testNormalClassReferences() {
	  String[] args = {BASEDIR + "" + File.separator + "TestFiles" + File.separator + "testDir2"};
	  
	  List<String> correctOutput = new ArrayList<String>();
	  correctOutput.add("Normal Classes. Declarations found: 2; references found: 5.");

	  
	  TypeFinder finder = new TypeFinder();
	  finder.run(args);
	  assertEquals(correctOutput,  finder.allOutputStrings);
	  
	}
	
	
	

}
