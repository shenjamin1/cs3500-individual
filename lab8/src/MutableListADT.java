/**
 * Interface for a ListADT that is mutable.
 *
 * @param <T> type of the ListADT
 */
public interface MutableListADT<T> extends ListADT<T> {

  /**
   * Method that returns the immutable ListADT counterpart of this mutable ListADT.
   *
   * @return the ImmutableListADT
   */
  ImmutableListADT<T> getImmutableList();
}
