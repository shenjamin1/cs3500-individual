package betterpizza;

import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * This class represents the operations offered by a single immutable pizza.
 */
public interface ObservablePizza {
  /**
   * Get the cost of this pizza.
   *
   * @return the cost of this pizza in MM.CC format.
   */
  double cost();

  /**
   * Adds toppings onto the pizza.
   *
   * @param name the name of the topping.
   * @return    the portion of this topping on this pizza, or null if the given topping is not
   *            on this pizza.
   */
  ToppingPortion hasTopping(ToppingName name);
}
