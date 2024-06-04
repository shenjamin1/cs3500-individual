package betterpizza;

import java.util.HashMap;
import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * This class represents a pizza builder.
 */
public abstract class PizzaBuilder<T extends PizzaBuilder<T>> {
  protected Crust crust;
  protected Size size;
  protected Map<ToppingName, ToppingPortion> toppings = new HashMap<>();

  /**
   * Initializes the crust for the pizza of the pizza builder.
   *
   * @param crust crust type for the pizza being built
   * @return an instance of the PizzaBuilder
   */
  public T crust(Crust crust) {
    this.crust = crust;
    return returnBuilder();
  }

  /**
   * Initializes the size for the pizza of the pizza builder.
   *
   * @param size size for the pizza being built
   * @return an instance of the PizzaBuilder
   */
  public T size(Size size) {
    this.size = size;
    return returnBuilder();
  }

  /**
   * Adds a topping onto the pizza of the pizza builder.
   *
   * @param toppingName    name of the topping to be added onto the pizza
   * @param toppingPortion amount of the topping to be added onto the pizza
   * @return an instance of the PizzaBuilder
   */
  public T addTopping(ToppingName toppingName, ToppingPortion toppingPortion) {
    toppings.put(toppingName, toppingPortion);
    return returnBuilder();
  }

  /**
   * Finalizes the build of the pizza.
   *
   * @return an ObservablePizza
   * @throws IllegalStateException if no size has been specified
   */
  public abstract ObservablePizza build() throws IllegalStateException;

  protected abstract T returnBuilder();
}
