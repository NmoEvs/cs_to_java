import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.time.*;
import java.time.format.*;
import java.time.temporal.*;
import java.util.Locale;

public class DateTimeTest {
  @Test
  public void localTime() {
    LocalTime t1 = LocalTime.of(7, 30);
    assertEquals(t1, LocalTime.parse("07:30"));
    // assertEquals(t1, LocalTime.parse(null));
  }

  @Test
  public void localTimeMinus() {
    LocalTime t1 = LocalTime.parse("10:30");
    LocalTime t2 = t1.minus(2, ChronoUnit.HOURS);
    assertEquals(t2, LocalTime.parse("08:30"));
    // assertEquals(t2, LocalTime.parse(null));
  }

  @Test
  public void dayOfTheWeek() {
    Locale locale = Locale.getDefault();
    DayOfWeek day = DayOfWeek.MONDAY.plus(3);
    assertEquals("Thursday", day.getDisplayName(TextStyle.FULL, locale));
    // assertEquals(null, day.getDisplayName(TextStyle.FULL, locale));
  }

  @Test
  public void localDate() {
    LocalDate date = LocalDate.of(2016, Month.JUNE, 28);
    LocalDate nextWed = date.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
    assertEquals("2016-06-29", nextWed.toString());
    // assertEquals(null, nextWed.toString());
  }

  @Test
  public void instantAndDuration() {
    Instant zeroEpoch = Instant.ofEpochSecond(0L);
    Duration gap = Duration.ofSeconds(10);
    Instant newInstant = zeroEpoch.plus(gap);
    assertEquals(10000, ChronoUnit.MILLIS.between(zeroEpoch, newInstant));
    // assertEquals(null, ChronoUnit.MILLIS.between(zeroEpoch, newInstant));
  }

  @Test
  public void period() {
    LocalDate today = LocalDate.now();
    LocalDate training_day = LocalDate.of(2016, Month.JUNE, 28);

    Period p = Period.between(training_day, today);
    // assertEquals(-1, ChronoUnit.DAYS.between(training_day, today));
    // assertEquals(null, ChronoUnit.DAYS.between(training_day, today));
  }
}
