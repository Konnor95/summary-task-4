package ua.nure.bekuzarov.SummaryTask4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ua.nure.bekuzarov.SummaryTask4.validator.AllValidatorsTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllValidatorsTest.class, SerializationTest.class,
        UtilTest.class, TranslatorTest.class
})
public class AllTests {

}
