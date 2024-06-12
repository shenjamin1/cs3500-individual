import java.util.function.Function;

/**
 * This class represents a mutable ListADT implementation.
 *
 * @param <T> the type of elements in the list
 */
public class MutableListADTImpl<T> extends ListADTImpl<T> implements MutableListADT<T> {

  /**
   * Constructor that creates an empty mutable ListADT implementation.
   */
  public MutableListADTImpl() {
    super();
  }

  /**
   * Constructor that creates a mutable ListADT implementation.
   *
   * @param innerList the inner list of the mutable ListADT
   */
  public MutableListADTImpl(ListADT<T> innerList) {
    for (int i = 0; i < innerList.getSize(); i++) {
      this.addBack(innerList.get(i));
    }
  }

  @Override
  public ImmutableListADT<T> getImmutableList() {
    ImmutableListADTImpl.ImmutableListADTBuilder<T> immutableList
            = new ImmutableListADTImpl.ImmutableListADTBuilder<>();
    for (int i = 0; i < this.getSize(); i++) {
      immutableList.addBack(this.get(i));
    }
    return immutableList.build();
  }

  @Override
  public <R> MutableListADTImpl<R> map(Function<T, R> converter) {
    return new MutableListADTImpl<>(super.map(converter));
  }
}
