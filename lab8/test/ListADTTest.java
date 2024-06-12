import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Class that tests Mutable and Immutable ListADT together.
 */
public class ListADTTest {
  @Test
  public void testTogether() {
    MutableListADT<Integer> mutableIntList = new MutableListADTImpl<>();
    mutableIntList.addBack(1);
    mutableIntList.addBack(2);
    mutableIntList.addBack(3);
    assertEquals((Integer) 1, mutableIntList.get(0));
    assertEquals((Integer) 2, mutableIntList.get(1));
    assertEquals((Integer) 3, mutableIntList.get(2));
    ImmutableListADT<Integer> immutableIntListADT
            = new ImmutableListADTImpl
            .ImmutableListADTBuilder<Integer>()
            .addBack(1)
            .addBack(2)
            .addBack(3)
            .build();
    MutableListADT<Integer> mutableIntListClone = immutableIntListADT.getMutableList();
    mutableIntListClone.addBack(4);
    mutableIntListClone.addBack(5);
    assertEquals(3, mutableIntList.getSize());
    assertEquals(3, immutableIntListADT.getSize());
    assertEquals(5, mutableIntListClone.getSize());
  }
}
