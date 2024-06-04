package betterpizza;

import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * This class represents a vegetarian pizza.
 */
public class VeggiePizza extends AlaCartePizza {

  /**
   * Create a veggie pizza with all vegetarian toppings, of the specified
   * size with the specified crust.
   *
   * @param size  the size of this pizza
   * @param crust the crust of this pizza
   * @param toppings the toppings of this pizza
   */
  public VeggiePizza(Crust crust, Size size, Map<ToppingName, ToppingPortion> toppings) {
    super(crust, size, toppings);
  }

  /**
   * This class represents a veggie pizza builder.
   */
  public static class VeggiePizzaBuilder extends PizzaBuilder<VeggiePizzaBuilder> {

    /**
     * Creates a veggie pizza builder with the default veggie pizza toppings.
     */
    public VeggiePizzaBuilder() {
      this.addTopping(ToppingName.Cheese, ToppingPortion.Full);
      this.addTopping(ToppingName.Sauce, ToppingPortion.Full);
      this.addTopping(ToppingName.BlackOlive, ToppingPortion.Full);
      this.addTopping(ToppingName.GreenPepper, ToppingPortion.Full);
      this.addTopping(ToppingName.Onion, ToppingPortion.Full);
      this.addTopping(ToppingName.Jalapeno, ToppingPortion.Full);
      this.addTopping(ToppingName.Tomato, ToppingPortion.Full);
    }

    @Override
    public ObservablePizza build() throws IllegalStateException {
      if (this.size == null) {
        throw new IllegalStateException("Specify the size first.");
      }
      return new VeggiePizza(crust, size, toppings);
    }

    @Override
    protected VeggiePizzaBuilder returnBuilder() {
      return this;
    }

    /**
     * Option to remove the cheese from a default veggie pizza.
     * @return an instance of a VeggiePizzaBuilder
     */
    public VeggiePizzaBuilder noCheese() {
      this.toppings.remove(ToppingName.Cheese);
      return returnBuilder();
    }

    /**
     * Option to remove the sauce from a default veggie pizza.
     * @return an instance of a VeggiePizzaBuilder
     */
    public VeggiePizzaBuilder noSauce() {
      this.toppings.remove(ToppingName.Sauce);
      return returnBuilder();
    }

    /**
     * Option to remove the black olives from a default veggie pizza.
     * @return an instance of a VeggiePizzaBuilder
     */
    public VeggiePizzaBuilder noBlackOlive() {
      this.toppings.remove(ToppingName.BlackOlive);
      return returnBuilder();
    }

    /**
     * Option to remove the green peppers from a default veggie pizza.
     * @return an instance of a VeggiePizzaBuilder
     */
    public VeggiePizzaBuilder noGreenPepper() {
      this.toppings.remove(ToppingName.GreenPepper);
      return returnBuilder();
    }

    /**
     * Option to remove the onions from a default veggie pizza.
     * @return an instance of a VeggiePizzaBuilder
     */
    public VeggiePizzaBuilder noOnion() {
      this.toppings.remove(ToppingName.Onion);
      return returnBuilder();
    }

    /**
     * Option to remove the jalapenos from a default veggie pizza.
     * @return an instance of a VeggiePizzaBuilder
     */
    public VeggiePizzaBuilder noJalapeno() {
      this.toppings.remove(ToppingName.Jalapeno);
      return returnBuilder();
    }

    /**
     * Option to remove the tomatoes from a default veggie pizza.
     * @return an instance of a VeggiePizzaBuilder
     */
    public VeggiePizzaBuilder noTomato() {
      this.toppings.remove(ToppingName.Tomato);
      return returnBuilder();
    }
  }
}
