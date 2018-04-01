package testsTypeFinder;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ UnitTestsQualifiedNames.class, UnitTestsSimpleNames.class, UnitTestsJarFiles.class, UnitTestsAllJavaTypes.class})
public class AllTests {

}
