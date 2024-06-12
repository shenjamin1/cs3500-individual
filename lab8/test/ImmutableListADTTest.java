import org.junit.Before;
import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

/**
 * Class that tests ImmutableListADT.
 */
public class ImmutableListADTTest {
  private ImmutableListADT<String> immutableStringListADT;
  private ImmutableListADT<Integer> immutableIntListADT;

  @Before
  public void setup() {
    immutableStringListADT = new ImmutableListADTImpl
            .ImmutableListADTBuilder<String>()
            .addBack("Hello")
            .addBack("World")
            .addBack("!")
            .build();

    immutableIntListADT = new ImmutableListADTImpl
            .ImmutableListADTBuilder<Integer>()
            .addBack(1)
            .addBack(2)
            .addBack(3)
            .build();
  }

  @Test
  public void testBuild() {
    String[] expected = {"Hello", "World", "!"};
    for (int i = 0; i < immutableStringListADT.getSize(); i++) {
      assertEquals(expected[i], immutableStringListADT.get(i));
    }
  }

  @Test
  public void testGetMutableListADT() {
    ListADT<String> stringListADT = new ListADTImpl<>();
    stringListADT.addBack("Hello");
    stringListADT.addBack("World");
    stringListADT.addBack("!");
    MutableListADT<String> mutableStringListADT = new MutableListADTImpl<>(stringListADT);
    assertEquals(mutableStringListADT.toString()
            , immutableStringListADT.getMutableList().toString());
    for (int i = 0; i < mutableStringListADT.getSize(); i++) {
      assertEquals(mutableStringListADT.get(i), immutableStringListADT.getMutableList().get(i));
    }
  }

  @Test
  public void testMap() {
    Function<Integer, Integer> square = x -> x^2;
    ImmutableListADTImpl<Integer> squareMapped
            = (ImmutableListADTImpl<Integer>) immutableIntListADT.map(square);
    for (int i = 0; i < immutableIntListADT.getSize(); i++) {
      Integer squareResult = immutableIntListADT.get(i)^2;
      assertEquals(squareMapped.get(i), squareResult);
    }
    assertEquals((Integer) 1, immutableIntListADT.get(0));
    assertEquals((Integer) 2, immutableIntListADT.get(1));
    assertEquals((Integer) 3, immutableIntListADT.get(2));
  }
}
