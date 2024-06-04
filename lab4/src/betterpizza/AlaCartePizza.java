package betterpizza;

import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * This class represents an ala carte pizza (i.e. a pizza that can
 * have an arbitrary number of ingredients.
 */
public class AlaCartePizza implements ObservablePizza {
  protected Crust crust;
  protected Size size;
  protected Map<ToppingName, ToppingPortion> toppings;

  /**
   * Create a pizza given its crust type, size and toppings.
   */
  protected AlaCartePizza(Crust crust, Size size, Map<ToppingName, ToppingPortion> toppings)
          throws IllegalStateException {
    if (size == null || crust == null || toppings == null) {
      throw new IllegalStateException("Parameters cannot be null.");
    }
    this.crust = crust;
    this.size = size;
    this.toppings = toppings;
  }

  @Override
  public ToppingPortion hasTopping(ToppingName name) {
    return this.toppings.getOrDefault(name, null);
  }

  @Override
  public double cost() {
    double cost = 0.0;
    for (Map.Entry<ToppingName, ToppingPortion> item : this.toppings.entrySet()) {
      cost += item.getKey().getCost() * item.getValue().getCostMultiplier();
    }
    return cost + this.size.getBaseCost();
  }

  /**
   * This class represents an ala carte pizza builder.
   */
  public static class AlaCartePizzaBuilder extends PizzaBuilder<AlaCartePizzaBuilder> {
    @Override
    public ObservablePizza build() throws IllegalStateException {
      if (this.size == null) {
        throw new IllegalStateException("Specify the size first.");
      }
      return new AlaCartePizza(crust, size, toppings);
    }

    @Override
    protected AlaCartePizzaBuilder returnBuilder() {
      return this;
    }
  }
}
