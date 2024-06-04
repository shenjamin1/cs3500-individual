package vehicle;

/**
 * Interface for a manual transmission simulator, with options to
 * change speed, gear, and get a status.
 */
public interface ManualTransmission {

  /**
   * Return the status of this transmission as a String without any additional parameters.
   *
   * @return a String representation of the status.
   */
  String getStatus();

  /**
   * Get the current speed of the vehicle as a whole number.
   *
   * @return an int for the whole number representation.
   */
  int getSpeed();

  /**
   * Get the current gear of the vehicle as a whole number.
   *
   * @return an int for the whole number representation.
   */
  int getGear();

  /**
   * Increase the speed by a fixed amount without changing gears and return the
   * resulting transmission object. If the speed cannot be increased, then it
   * should return an object with the same speed as before. The speed change
   * amount is up to the implementation and is not an argument of this method.
   *
   * @return the resulting ManualTransmission object.
   */
  ManualTransmission increaseSpeed();

  /**
   * Decrease the speed by a fixed amount without changing gears and
   * return the resulting transmission object. If the speed cannot be
   * decreased, then it should return an object with the same speed as before.
   * The speed change amount is up to the implementation and is not an argument of this method.
   *
   * @return the resulting ManualTransmission object.
   */
  ManualTransmission decreaseSpeed();

  /**
   * Increase the gear by one without changing speed and return the resulting
   * transmission object. If the gear cannot be increased, then it should return an
   * object with the same gear as before.
   *
   * @return the resulting ManualTransmission object.
   */
  ManualTransmission increaseGear();

  /**
   * Decrease the gear by one without changing speed and return the resulting
   * transmission object. If the gear cannot be decreased, then it should return an
   * object with the same gear as before.
   *
   * @return the resulting ManualTransmission object.
   */
  ManualTransmission decreaseGear();
}
