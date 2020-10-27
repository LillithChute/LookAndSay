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
    // Start with a seed of one.  It should return 11.
    assertEquals("11", new LookAndSayIterator().next().getCurrentNumber());

    // Start with a seed of "21".  This should return a next of "1211"
    assertEquals("1211", new LookAndSayIterator(new BigInteger("21")).next().getCurrentNumber());
  }

  private RIterator iterateNumberOfTimes(RIterator newValue, int numberOfTimes) {
    for (int i = 0; i < numberOfTimes; i++) {
      newValue = (RIterator) newValue.next();
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
}
