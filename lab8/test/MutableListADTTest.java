import org.junit.Before;
import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

/**
 * Class that tests MutableListADT.
 */
public class MutableListADTTest {
  private MutableListADTImpl<String> mutableStringListADT;
  private MutableListADTImpl<Integer> mutableIntListADT;

  @Before
  public void setup() {
    mutableStringListADT = new MutableListADTImpl<>();
    mutableStringListADT.addBack("Hello");
    mutableStringListADT.addBack("World");
    mutableStringListADT.addBack("!");
    mutableIntListADT = new MutableListADTImpl<>();
    mutableIntListADT.addBack(1);
    mutableIntListADT.addBack(2);
    mutableIntListADT.addBack(3);
  }

  @Test
  public void testGetImmutableListADT() {
    ImmutableListADT<String> immutableStringListADT = new ImmutableListADTImpl
            .ImmutableListADTBuilder<String>()
            .addBack("Hello")
            .addBack("World")
            .addBack("!")
            .build();
    for (int i = 0; i < mutableStringListADT.getSize(); i++) {
      assertEquals(immutableStringListADT.get(i), mutableStringListADT.getImmutableList().get(i));
    }
  }

  @Test
  public void testMap() {
    Function<Integer, Integer> square = x -> x ^ 2;
    MutableListADTImpl<Integer> squareMapped = mutableIntListADT.map(square);
    for (int i = 0; i < mutableIntListADT.getSize(); i++) {
      Integer squareResult = mutableIntListADT.get(i) ^ 2;
      assertEquals(squareMapped.get(i), squareResult);
    }
    assertEquals((Integer) 1, mutableIntListADT.get(0));
    assertEquals((Integer) 2, mutableIntListADT.get(1));
    assertEquals((Integer) 3, mutableIntListADT.get(2));
  }
}
