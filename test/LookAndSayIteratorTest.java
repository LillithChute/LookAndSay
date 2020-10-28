import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import lookandsay.LookAndSayIterator;
import lookandsay.RIterator;
import org.junit.Before;
import org.junit.Test;

/**
 * This will test the look and say concrete implementation.
 */
public class LookAndSayIteratorTest {

  private BigInteger badSeedNegative;
  private BigInteger badSeedContainsZero;
  private BigInteger badSeedTooHigh;
  private BigInteger seedValue;
  private BigInteger endValue;
  private RIterator lookAndSay;

  /**
   * Setup the necessary values.
   */
  @Before
  public void setup() {
    badSeedNegative = new BigInteger("-25");
    badSeedContainsZero = new BigInteger("2020");
    badSeedTooHigh = new BigInteger("54321");
    seedValue = new BigInteger("123");
    endValue = new BigInteger("4321");
  }

  //region Constructor testing.
  @Test(expected = IllegalArgumentException.class)
  public void badSeedNegativeTest() {
    lookAndSay = new LookAndSayIterator(badSeedNegative, endValue);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badSeedContainsZeroTest() {
    lookAndSay = new LookAndSayIterator(badSeedContainsZero, endValue);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badSeedHigherThanEndValueTest() {
    lookAndSay = new LookAndSayIterator(badSeedTooHigh, endValue);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badSeedNegativeOneParamConstructorTest() {
    lookAndSay = new LookAndSayIterator(badSeedNegative);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badSeedContainsZeroOneParamConstructorTest() {
    lookAndSay = new LookAndSayIterator(badSeedContainsZero);
  }

  @Test
  public void lookAndSaySetSeedAndEnd() {
    lookAndSay = new LookAndSayIterator(seedValue, endValue);
    assertEquals("seed: 123  end: 4321", lookAndSay.toString());
  }

  @Test
  public void lookAndSaySetSeed() {
    lookAndSay = new LookAndSayIterator(seedValue);
    assertEquals("seed: 123  end: 999999999999999999999999999999999999999999999"
            + "9999999999999999999999999999999999999999999999999999999", lookAndSay.toString());
  }

  @Test
  public void lookAndSayEmptyConstructorTest() {
    lookAndSay = new LookAndSayIterator();
    assertEquals("seed: 1  end: 999999999999999999999999999999999999999999999"
            + "9999999999999999999999999999999999999999999999999999999", lookAndSay.toString());
  }
  //endregion

  // region Tests for next.
  @Test
  public void nextTest() {
    // Start with a seed of one.  It should return 1 and move to the next number.
    lookAndSay = new LookAndSayIterator();

    assertEquals("1", lookAndSay.next().toString());
    assertEquals("11", lookAndSay.next().toString());

    // Start with a seed of "21".  Next would return 21 and move to the next.
    // Next again should return a next of "1211"
    lookAndSay = new LookAndSayIterator(new BigInteger("21"));
    lookAndSay.next();
    assertEquals("1211", lookAndSay.next().toString());
  }

  private RIterator iterateNumberOfTimes(RIterator newValue, int numberOfTimes) {
    for (int i = 0; i < numberOfTimes; i++) {
      newValue.next();
    }

    return newValue;
  }

  @Test
  public void repeatNextMultipleTimesTest() {
    // Start with a seed of one.  Use next four times.  It should return "111221"
    RIterator newValue = new LookAndSayIterator();

    newValue = iterateNumberOfTimes(newValue, 4);

    assertEquals("111221", newValue.getCurrentNumber());
  }

  // endregion

  // region Tests for hasNext
  @Test
  public void hasNextTrueTest() {
    // Start with a seed of one with an end of 100 9's.
    assertEquals(true, new LookAndSayIterator().hasNext());

    // Start with a seed of "21".  This should return a next of "1211"
    RIterator iterator = new LookAndSayIterator(new BigInteger("1"), new BigInteger("2222"));
    iterator = iterateNumberOfTimes(iterator, 2);

    assertEquals("21", iterator.getCurrentNumber());
    assertEquals(true, iterator.hasNext());
  }

  @Test
  public void hasNextFalseTest() {
    // Start with a seed of "21".  This should return a next of "1211"
    RIterator iterator = new LookAndSayIterator(new BigInteger("1"), new BigInteger("2222"));
    iterator = iterateNumberOfTimes(iterator, 2);

    assertEquals("21", iterator.getCurrentNumber());
    assertEquals(true, iterator.hasNext());

    // Iterate once more and then it should NOT have a next number
    iterator = iterateNumberOfTimes(iterator, 1);

    assertEquals("1211", iterator.getCurrentNumber());
    assertEquals(false, iterator.hasNext());
  }
  // endregion

  // region Tests for prev()
  @Test
  public void prevTest() {
    lookAndSay = new LookAndSayIterator(new BigInteger("21"));
    assertEquals("11", lookAndSay.prev().toString());
  }

  @Test
  public void prevMultipleTest() {
    // Multiple iteration.
    lookAndSay = new LookAndSayIterator(new BigInteger("13112221"));
    lookAndSay.prev();
    lookAndSay.prev();
    assertEquals("1211", lookAndSay.prev().toString());
  }
  // endregion

  // region Mixed use of next and prev test.
  @Test
  public void nextAndPrevMixedTest() {
    lookAndSay = new LookAndSayIterator(new BigInteger("13112221"));
    lookAndSay.prev();
    lookAndSay.prev();
    lookAndSay.prev();
    assertEquals("21", lookAndSay.prev().toString());
    lookAndSay.next();
    lookAndSay.next();
    assertEquals("312211", lookAndSay.next().toString());
  }
  // endregion

  // region Test hasPrevious() method
  @Test
  public void hasPreviousTrueTest() {
    // Start with a seed of 21 with an end of 100 9's.
    assertEquals(true, new LookAndSayIterator(new BigInteger("21")).hasPrevious());

    // Start with a seed of "21".
    RIterator iterator = new LookAndSayIterator(new BigInteger("21"), new BigInteger("2222"));

    assertEquals("11", iterator.prev().toString());
    assertEquals(true, iterator.hasPrevious());
  }

  @Test
  public void hasPrevFalseTest() {
    // Start with a seed of "1".
    RIterator iterator = new LookAndSayIterator();
    assertEquals("1", iterator.getCurrentNumber());
    assertEquals(false, iterator.hasPrevious());

    // Iterate once more and then it should NOT have a next number
    iterator = new LookAndSayIterator(new BigInteger("21"));

    assertEquals("21", iterator.getCurrentNumber());
    assertEquals(true, iterator.hasPrevious());
    iterator.prev();
    assertEquals(true, iterator.hasPrevious());
    iterator.prev();
    assertEquals(false, iterator.hasPrevious());
  }
  // endregion
}
