package lookandsay;

import java.math.BigInteger;

/**
 * A look-and-say sequence is a sequence of numbers. Given the current number, the next number
 * is obtained by reading the current number out loud, writing what we say. For example, if
 * the current number is "1", then we read that as "one 1" and thus the next number in the
 * sequence will be "11".
 */
public class LookAndSayIterator implements RIterator {

  private BigInteger seed;
  private BigInteger end = new BigInteger("99999999999999999999999999999999999999999999999999"
          + "99999999999999999999999999999999999999999999999999");

  /**
   * This creates an instance of the LookAndSee class.
   *
   * @param seedValue The number at which the sequence must begin.
   * @param endValue The number at which the sequence should stop when reached.
   */
  public LookAndSayIterator(BigInteger seedValue, BigInteger endValue) {

    validateSeedValue(seedValue, endValue);

    this.seed = seedValue;
    this.end = endValue;
  }

  /**
   * This creates an instance of the LookAndSee class.
   *
   * @param seedValue The number at which the sequence must begin.
   */
  public LookAndSayIterator(BigInteger seedValue) {

    validateSeedValue(seedValue, this.end);

    this.seed = seedValue;
  }

  /**
   * This creates an instance of the LookAndSee class.
   */
  public LookAndSayIterator() {
    this.seed = new BigInteger("1");
  }

  /**
   *  If the seed is not positive, or is not less than the end or has any zeroes in it, the
   *  constructor should throw an IllegalArgumentException.
   *
   * @param seedValue The number at which the sequence must begin.
   * @param endValue The number at which the sequence should stop when reached.
   */
  private void validateSeedValue(BigInteger seedValue, BigInteger endValue) {
    // The signum method checks the sign of the number.
    if (seedValue.signum() == -1) {
      throw new IllegalArgumentException("The seed value must be positive.");
    }

    // Make sure the seed is less than the end value.
    if (seedValue.compareTo(endValue) == 1 || seedValue.compareTo(endValue) == 0) {
      throw new IllegalArgumentException("The seed value must be less than the end value.");
    }

    // Make sure the seed value doesn't contain any zeros.  Must convert the number to a
    // string representation and check if it has a zero.
    if (seedValue.toString().contains("0")) {
      throw new IllegalArgumentException("The seed value must not include a zero.");
    }
  }

  @Override
  public Object next() {
    return null;
  }

  @Override
  public Object prev() {
    return null;
  }

  @Override
  public boolean hasNext() {
    return false;
  }

  @Override
  public boolean hasPrevious() {
    return false;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
