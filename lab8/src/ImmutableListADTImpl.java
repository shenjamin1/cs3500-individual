import java.util.function.Function;

/**
 * This class represents an immutable ListADT implementation.
 *
 * @param <T> the type of elements in the list
 */
public class ImmutableListADTImpl<T> implements ImmutableListADT<T> {
  private final ListADTImpl<T> innerList;

  private ImmutableListADTImpl(ListADTImpl<T> innerList) {
    this.innerList = innerList;
  }

  @Override
  public MutableListADT<T> getMutableList() {
    return new MutableListADTImpl<T>(innerList);
  }

  @Override
  public <R> ImmutableListADT<R> map(Function<T, R> converter) {
    ListADTImpl<R> mappedList = (ListADTImpl<R>) innerList.map(converter);
    return new ImmutableListADTImpl<>(mappedList);
  }

  @Override
  public int getSize() {
    return innerList.getSize();
  }

  @Override
  public T get(int index) throws IllegalArgumentException {
    return innerList.get(index);
  }

  private void addBack(T item) {
    innerList.addBack(item);
  }

  /**
   * Builder that creates an immutable ListADT implementation.
   *
   * @param <T> the type of elements in the list
   */
  public static class ImmutableListADTBuilder<T> {
    private final ImmutableListADTImpl<T> immutableList;

    /**
     * Constructor for the builder that initializes an immutable ListADT implementation.
     */
    public ImmutableListADTBuilder() {
      this.immutableList = new ImmutableListADTImpl<>(new ListADTImpl<>());
    }

    /**
     * Adds an element to the back of the immutable ListADT implementation.
     *
     * @param item item to be added
     * @return the builder
     */
    public ImmutableListADTBuilder<T> addBack(T item) {
      immutableList.addBack(item);
      return this;
    }

    /**
     * Method that finally builds the immutable ListADT implementation.
     *
     * @return the immutable ListADT implementation
     */
    public ImmutableListADTImpl<T> build() {
      return immutableList;
    }
  }
}
