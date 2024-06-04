import org.junit.Before;
import org.junit.Test;

import betterpizza.AlaCartePizza;
import betterpizza.CheesePizza;
import betterpizza.ObservablePizza;
import betterpizza.VeggiePizza;
import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Testing class for ObservablePizza.
 */
public class ObservablePizzaTest {
  private ObservablePizza alacarte;
  private ObservablePizza cheese;
  private ObservablePizza halfCheese;
  private ObservablePizza pizza;
  private ObservablePizza veggie;
  private ObservablePizza leftHalfCheese;
  private ObservablePizza veggieNoCheese;
  private ObservablePizza veggieNoSauce;
  private ObservablePizza veggieNoBlackOlive;
  private ObservablePizza veggieNoGreenPepper;
  private ObservablePizza veggieNoOnion;
  private ObservablePizza veggieNoJalapeno;
  private ObservablePizza veggieNoTomato;

  /**
   * Sets up various types of pizzas for testing.
   */
  @Before
  public void setup() {
    alacarte = new AlaCartePizza.AlaCartePizzaBuilder()
            .crust(Crust.Classic)
            .size(Size.Medium)
            .addTopping(ToppingName.Cheese, ToppingPortion.Full)
            .addTopping(ToppingName.Sauce,ToppingPortion.Full)
            .addTopping(ToppingName.GreenPepper,ToppingPortion.Full)
            .addTopping(ToppingName.Onion,ToppingPortion.Full)
            .addTopping(ToppingName.Jalapeno,ToppingPortion.LeftHalf)
            .build();

    cheese = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .build();

    halfCheese = new AlaCartePizza.AlaCartePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .addTopping(ToppingName.Cheese, ToppingPortion.LeftHalf)
            .addTopping(ToppingName.Sauce, ToppingPortion.Full)
            .build();

    pizza = new AlaCartePizza.AlaCartePizzaBuilder()
            .crust(Crust.Stuffed)
            .size(Size.Medium)
            .build();

    veggie = new VeggiePizza.VeggiePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .build();

    leftHalfCheese = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Medium)
            .leftHalfCheese()
            .build();

    veggieNoCheese = new VeggiePizza.VeggiePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .noCheese()
            .build();

    veggieNoSauce = new VeggiePizza.VeggiePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .noSauce()
            .build();

    veggieNoBlackOlive = new VeggiePizza.VeggiePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .noBlackOlive()
            .build();

    veggieNoGreenPepper = new VeggiePizza.VeggiePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .noGreenPepper()
            .build();

    veggieNoOnion = new VeggiePizza.VeggiePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .noOnion()
            .build();

    veggieNoJalapeno = new VeggiePizza.VeggiePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .noJalapeno()
            .build();

    veggieNoTomato = new VeggiePizza.VeggiePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .noTomato()
            .build();
  }

  /**
   * Tests for cost().
   */
  @Test
  public void testCost() {
    assertEquals(8.25, alacarte.cost(), 0.01);
    assertEquals(9, cheese.cost(), 0.01);
    assertEquals(8.5, halfCheese.cost(), 0.01);
    assertEquals(5, pizza.cost(), 0.01);
    assertEquals(11.5, veggie.cost(), 0.01);
    assertEquals(6.5, leftHalfCheese.cost(), 0.01);
  }

  /**
   * Tests for hasTopping().
   */
  @Test
  public void testHasTopping() {
    assertEquals(ToppingPortion.Full, veggie.hasTopping(ToppingName.BlackOlive));
    assertNull(veggieNoCheese.hasTopping(ToppingName.Cheese));
    assertNull(veggieNoSauce.hasTopping(ToppingName.Sauce));
    assertNull(veggieNoBlackOlive.hasTopping(ToppingName.BlackOlive));
    assertNull(veggieNoGreenPepper.hasTopping(ToppingName.GreenPepper));
    assertNull(veggieNoOnion.hasTopping(ToppingName.Onion));
    assertNull(veggieNoJalapeno.hasTopping(ToppingName.Jalapeno));
    assertNull(veggieNoTomato.hasTopping(ToppingName.Tomato));
  }
}
