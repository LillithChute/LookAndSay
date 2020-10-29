package lookandsay;

import java.util.Iterator;

/**
 * This interface represents a generic list.  This interface is also iterable.
 *
 * @param <T> The type of data this will work with, represented by T.
 */
public interface RIterator<T> extends Iterator<T> {

  /**
   * This will return the next element as a string.
   *
   * @return The next element as a string.
   */
  String getCurrentNumber();

  /**
   * This will return the current number and advance to the next element.
   *
   * @return The next element.
   */
  T next();

  /**
   * This will return the current number in the sequence and reverts to the previous number
   * in the sequence.
   *
   * @return The current number in the sequence.
   */
  T prev();

  /**
   * should return true if the next number to be returned is still lesser than end
   * (specified in the constructors), false otherwise.
   *
   * @return Either true or false.
   */
  boolean hasNext();

  /**
   * should return true if it is possible to go back one step, false otherwise.
   *
   * @return True or false.
   */
  boolean hasPrevious();
}
