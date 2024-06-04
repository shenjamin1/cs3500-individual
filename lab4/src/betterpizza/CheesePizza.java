package betterpizza;

import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * This class represents a cheese pizza.
 */
public class CheesePizza extends AlaCartePizza {

  /**
   * Create a cheese pizza of the specified size with the specified crust.
   *
   * @param size  the size of this pizza
   * @param crust the crust of this pizza
   * @param toppings the toppings of this pizza
   */
  protected CheesePizza(Crust crust, Size size, Map<ToppingName, ToppingPortion> toppings) {
    super(crust, size, toppings);
  }

  /**
   * This class represents a cheese pizza builder.
   */
  public static class CheesePizzaBuilder extends PizzaBuilder<CheesePizzaBuilder> {
    public CheesePizzaBuilder() {
      this.addTopping(ToppingName.Cheese, ToppingPortion.Full);
      this.addTopping(ToppingName.Sauce, ToppingPortion.Full);
    }

    @Override
    public ObservablePizza build() throws IllegalStateException {
      if (this.size == null) {
        throw new IllegalStateException("Specify the size first.");
      }
      return new CheesePizza(crust, size, toppings);
    }

    @Override
    protected CheesePizzaBuilder returnBuilder() {
      return this;
    }

    /**
     * Option to remove the cheese from a default cheese pizza.
     * @return an instance of a CheesePizzaBuilder
     */
    public CheesePizzaBuilder noCheese() {
      this.toppings.remove(ToppingName.Cheese);
      return returnBuilder();
    }

    /**
     * Option to create a cheese pizza with only the left half with cheese.
     * @return an instance of a CheesePizzaBuilder
     */
    public CheesePizzaBuilder leftHalfCheese() {
      this.addTopping(ToppingName.Cheese, ToppingPortion.LeftHalf);
      return returnBuilder();
    }

    /**
     * Option to create a cheese pizza with only the right half with cheese.
     * @return an instance of a CheesePizzaBuilder
     */
    public CheesePizzaBuilder rightHalfCheese() {
      this.addTopping(ToppingName.Cheese, ToppingPortion.RightHalf);
      return returnBuilder();
    }
  }
}
