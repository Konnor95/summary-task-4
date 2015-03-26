package ua.nure.bekuzarov.SummaryTask4.validator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AuthorValidatorTest.class, BookValidatorTest.class,
        OrderValidatorTest.class, PublisherValidatorTest.class,
        UserValidatorTest.class
})
public final class AllValidatorsTest {
}
