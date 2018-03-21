import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

// This is mainly inspired from JavaTests
// https://github.com/matyb/java-koans/

public class I18nTest {

  @Test
  public void localizedOutputOfDates() {
    Calendar cal = Calendar.getInstance();
    cal.set(2011, 3, 3);
    Date date = cal.getTime();
    Locale localeNlBE = new Locale("nl", "BE");
    DateFormat dateformatNlBE = DateFormat.getDateInstance(DateFormat.FULL, localeNlBE);
    assertEquals(dateformatNlBE.format(date), "zondag 3 april 2011");

    Locale localeFrBE = new Locale("fr", "BE");
    DateFormat dateformatFrBE = DateFormat.getDateInstance(DateFormat.FULL, localeFrBE);
    assertEquals(dateformatFrBE.format(date), "dimanche 3 avril 2011");
  }

  @Test
  public void getCountryInformation() {
      Locale locBE = new Locale("fr", "BE");
      assertEquals(locBE.getDisplayCountry(), "Belgium");
      assertEquals(locBE.getDisplayCountry(locBE), "Belgique");
      assertEquals(locBE.getDisplayCountry(new Locale("nl", "BE")), "België");
  }

  @Test
  public void formatCurrency() {
      float someAmount = 442.23f; // Don't use floats for money in real life. Really. It's a bad idea.
      Locale locBE = new Locale("fr", "BE");
      NumberFormat nf = NumberFormat.getCurrencyInstance(locBE);
      assertEquals(nf.format(someAmount), "442,23 €");
  }
}
