package ua.nure.bekuzarov.SummaryTask4;


import org.junit.Test;

public final class TranslatorTest extends TranslationTest {

    @Test
    public void testNull() {
        getTranslator().translate("text", "123");
    }

}
