/**
 * An interface for a ListADT that is immutable.
 *
 * @param <T> type of the ListADT
 */
public interface ImmutableListADT<T> extends CommonListADT<T> {

  /**
   * Method that returns the mutable ListADT counterpart of this immutable ListADT.
   *
   * @return the MutableListADT counterpart
   */
  MutableListADT<T> getMutableList();
}
