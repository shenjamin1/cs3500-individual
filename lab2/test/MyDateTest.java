import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for MyDate class.
 */
public class MyDateTest {
  MyDate birthday;
  MyDate leapYear;
  MyDate newYears;
  MyDate notLeapYear;
  MyDate tooManyDays;
  MyDate tooFewDays;
  MyDate negative;
  MyDate autograder;
  MyDate autograder2;

  @Before
  public void setup() {
    birthday = new MyDate(10, 10, 2005);
    leapYear = new MyDate(29, 2, 2000);
    newYears = new MyDate(1, 1, 2000);
    autograder = new MyDate(28, 2, 2000);
    autograder2 = new MyDate(28, 1, 2012);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptionNotLeapYear() {
    notLeapYear = new MyDate(29, 2, 2001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptionTooManyDays() {
    tooManyDays = new MyDate(32, 10, 2005);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptionTooFewDays() {
    tooFewDays = new MyDate(0, 10, 2005);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptionNegativeInput() {
    negative = new MyDate(10, 10, -2005);
  }

  @Test
  public void testConstructor() {
    assertEquals("2005-10-10", birthday.toString());
    assertEquals("2000-02-29", leapYear.toString());
  }

  @Test
  public void testAdvanceZero() {
    birthday.advance(0);
    assertEquals("2005-10-10", birthday.toString());
  }

  @Test
  public void testAdvancePositive() {
    birthday.advance(10);
    assertEquals("2005-10-20", birthday.toString());
    birthday.advance(20);
    assertEquals("2005-11-09", birthday.toString());
    newYears.advance(-1);
    newYears.advance(10);
    assertEquals("2000-01-10", newYears.toString());
    newYears.advance(365);
    assertEquals("2001-01-09", newYears.toString());
    autograder2.advance(32);
    assertEquals("2012-02-29", autograder2.toString());
  }

  @Test
  public void testAdvanceNegative() {
    birthday.advance(-9);
    assertEquals("2005-10-01", birthday.toString());
    birthday.advance(-9);
    assertEquals("2005-09-22", birthday.toString());
    newYears.advance(-1);
    assertEquals("1999-12-31", newYears.toString());
    newYears.advance(-365);
    assertEquals("1998-12-31", newYears.toString());
  }

  @Test
  public void testAdvanceLeapYearPositive() {
    leapYear.advance(-9);
    leapYear.advance(20);
    assertEquals("2000-03-11", leapYear.toString());
    autograder.advance(1);
    assertEquals("2000-02-29", autograder.toString());
  }

  @Test
  public void testAdvanceLeapYearNegative() {
    leapYear.advance(10);
    leapYear.advance(-20);
    assertEquals("2000-02-19", leapYear.toString());
  }

  @Test
  public void testToString() {
    assertEquals("2005-10-10", birthday.toString());
  }
}