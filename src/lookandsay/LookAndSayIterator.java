package lookandsay;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A look-and-say sequence is a sequence of numbers. Given the current number, the next number
 * is obtained by reading the current number out loud, writing what we say. For example, if
 * the current number is "1", then we read that as "one 1" and thus the next number in the
 * sequence will be "11".
 */
public class LookAndSayIterator implements RIterator<BigInteger> {

  // Constructor variables.
  private final BigInteger seed;
  private final BigInteger end;

  // Look and say variables.
  private String currentNumber;
  private String advanceNumber;
  private String previousNumber;
  private static final BigInteger LOWEST_VALUE_FOR_POSSIBLE_HASPREV = new BigInteger("0");
  private static final List<String> invalidNumbers = new ArrayList<>(
          Arrays.asList("0", "2", "3", "4", "5", "6", "7", "8", "9", "10"));

  /**
   * This creates an instance of the LookAndSay class.
   *
   * @param seedValue The number at which the sequence must begin.
   * @param endValue The number at which the sequence should stop when reached.
   */
  public LookAndSayIterator(BigInteger seedValue, BigInteger endValue) {

    validateSeedValue(seedValue, endValue);

    this.seed = seedValue;
    this.end = endValue;
    this.currentNumber = seed.toString();
  }

  /**
   * This creates an instance of the LookAndSay class.
   *
   * @param seedValue The number at which the sequence must begin.
   */
  public LookAndSayIterator(BigInteger seedValue) {
    this(seedValue, new BigInteger("9".repeat(100)));
  }

  /**
   * This creates an instance of the LookAndSay class.
   */
  public LookAndSayIterator() {
    this(new BigInteger("1"), new BigInteger("9".repeat(100)));
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

  /**
   * This is a recursively defined sequence of numbers as noted in the parent
   * class definition.  This generates the actual number.
   *
   * @param number The number used to generate the next number.
   * @return The next number in the sequence.
   */
  private static String generateNumber(String number) {
    StringBuilder result = new StringBuilder();

    char repeat = number.charAt(0);
    number = number.substring(1) + " ";
    int times = 1;

    for (char actual : number.toCharArray()) {

      if (actual != repeat) {
        result.append(times + "" + repeat);
        times = 1;
        repeat = actual;
      } else {
        times += 1;
      }

    }

    return result.toString();
  }

  /**
  * This is a recursively defined sequence of numbers as noted in the parent
  * class definition.  This generates the previous number in the sequence. For example,
  * given "1211" it would return "21"
  *
  * @param number The number used to generate the previous number.
  * @return The previous number in the sequence.
  */
  private String generateNumberReverse(String number) {

    if (number.length() % 2 != 0 || invalidNumbers.contains(number)) {
      // Only an even number of digits can be reversed.  So, return 1 which will indicate that
      // there is no previous.
      return "0";
    }

    // Break the number into a list of individual strings.
    List<String> numberSequence = Arrays.asList(number.split(""));

    StringBuilder result = new StringBuilder();

    for (int j = 0; j < numberSequence.size(); j += 2) {
      int howManyOfThisDigit = Integer.parseInt(numberSequence.get(j));
      String theDigit = numberSequence.get(j + 1);

      for (int k = 0; k < howManyOfThisDigit; k++) {
        result.append(theDigit);
      }
    }

    return result.toString();
  }

  @Override
  public BigInteger next() {
    // We are moving to the next number, so set this current number as the new previous.
    if (this.previousNumber == null) {
      this.previousNumber = this.seed.toString();
    } else if (this.advanceNumber == null) {
      // Advance all the numbers
      this.previousNumber = this.currentNumber;
    } else {
      // Advance all the numbers
      this.previousNumber = this.currentNumber;
      this.currentNumber = this.advanceNumber;
    }

    // Get the next number in the sequence and this becomes the new current.
    this.advanceNumber = generateNumber(currentNumber);

    return new BigInteger(this.currentNumber);
  }

  @Override
  public BigInteger prev() {
    // If next has been called, then we have a previous number.  Use that as the basis for getting
    // the number before that.
    this.previousNumber = generateNumberReverse(this.currentNumber);
    this.currentNumber = this.previousNumber;

    return new BigInteger(this.currentNumber);
  }

  @Override
  public boolean hasNext() {
    // Get what the next value would be
    BigInteger nextValue = new BigInteger(generateNumber(this.currentNumber));

    return new BigInteger(currentNumber).compareTo(this.end) != 1;
  }

  @Override
  public boolean hasPrevious() {
    // Get what the next value would be
    BigInteger prevValue = new BigInteger(generateNumberReverse(this.currentNumber));

    // A zero prevValue means there is likely no previous value
    return !prevValue.toString().equals("0");
  }

  @Override
  public String getCurrentNumber() {
    return this.currentNumber;
  }

  @Override
  public String toString() {
    StringBuilder values = new StringBuilder();

    return values.append("seed: ")
            .append(seed.toString())
            .append("  ")
            .append("end: ")
            .append(end.toString())
            .toString();
  }
}
