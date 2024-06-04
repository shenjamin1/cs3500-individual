package vehicle;

/**
 * Implementation of the ManualTransmission interface that takes a speed
 * range with 5 gears, each gear having a low speed and high speed. There are
 * options for incrementing or decrementing the speed and gears by 1 each time
 * and status messages for each attempt at a change.
 */
public class RegularManualTransmission implements ManualTransmission {

  private String status;
  private int speed;
  private int gear;
  private final int[] lowGearSpeedRanges;
  private final int[] highGearSpeedRanges;

  /**
   * Constructor that takes the speed ranges for the 5 gears as two integral numbers
   * each: low and high. Thus, it takes arguments as l1,h1,l2,h2,...,l5,h5. For each gear x,
   * lx should be less than or equal to hx. Furthermore, the lower speed for gear 1 should be
   * strictly lesser than that of gear 2, and so on. Finally, (only) adjacent-gear ranges may
   * overlap (e.g. l2 may be between l1 and h1, etc.) but the ranges cannot be non-overlapping (
   * i.e. each speed is covered by a gear range). The lower speed of the first gear should be 0,
   * and the highest speed in the last gear represents the speed limit of the vehicle (it cannot
   * go faster than this).
   *
   * @param gearSpeedRanges speed ranges for the 5 gears (low and high)
   * @throws IllegalArgumentException if any conditions are not fulfilled
   */
  public RegularManualTransmission(int... gearSpeedRanges) throws IllegalArgumentException {
    if (gearSpeedRanges.length != 10) {
      throw new IllegalArgumentException();
    }
    this.speed = 0;
    this.gear = 1;
    lowGearSpeedRanges = new int[5];
    highGearSpeedRanges = new int[5];
    for (int i = 0; i < gearSpeedRanges.length; i += 2) {
      lowGearSpeedRanges[i / 2] = gearSpeedRanges[i];
      highGearSpeedRanges[i / 2] = gearSpeedRanges[i + 1];
    }
    checkConstruction();
  }

  private void checkConstruction() throws IllegalArgumentException {
    if (lowGearSpeedRanges[0] != 0) {
      throw new IllegalArgumentException();
    }
    for (int i = 0; i < 5; i++) {
      if (lowGearSpeedRanges[i] > highGearSpeedRanges[i]) {
        throw new IllegalArgumentException();
      }
    }
    for (int i = 0; i < 4; i++) {
      if (lowGearSpeedRanges[i] >= lowGearSpeedRanges[i + 1]) {
        throw new IllegalArgumentException();
      }
    }
    for (int i = 0; i < 4; i++) {
      if (lowGearSpeedRanges[i + 1] > highGearSpeedRanges[i]) {
        throw new IllegalArgumentException();
      }
    }
  }

  @Override
  public String getStatus() {
    return this.status;
  }

  @Override
  public int getSpeed() {
    return this.speed;
  }

  @Override
  public int getGear() {
    return this.gear;
  }

  @Override
  public ManualTransmission increaseSpeed() {
    if (speed == highGearSpeedRanges[4]) {
      status = "Cannot increase speed. Reached maximum speed.";
    } else if (speed + 1 > highGearSpeedRanges[gear - 1]) {
      status = "Cannot increase speed, increase gear first.";
    } else {
      speed++;
      status = "OK: everything is OK.";
      if (gear < 5 && speed >= lowGearSpeedRanges[gear] && speed <= highGearSpeedRanges[gear]) {
        status = "OK: you may increase the gear.";
      }
    }
    return this;
  }

  @Override
  public ManualTransmission decreaseSpeed() {
    if (speed == 0) {
      status = "Cannot decrease speed. Reached minimum speed.";
    } else if (speed - 1 < lowGearSpeedRanges[gear - 1]) {
      status = "Cannot decrease speed, decrease gear first.";
    } else {
      speed--;
      status = "OK: everything is OK.";
      if (gear > 1 && speed <= highGearSpeedRanges[gear - 2]
              && speed >= lowGearSpeedRanges[gear - 2]) {
        status = "OK: you may decrease the gear.";
      }
    }
    return this;
  }

  @Override
  public ManualTransmission increaseGear() {
    if (gear == 5) {
      status = "Cannot increase gear. Reached maximum gear.";
    } else if (speed < lowGearSpeedRanges[gear]) {
      status = "Cannot increase gear, increase speed first.";
    } else {
      gear++;
      status = "OK: everything is OK.";
    }
    return this;
  }

  @Override
  public ManualTransmission decreaseGear() {
    if (gear == 1) {
      status = "Cannot decrease gear. Reached minimum gear.";
    } else if (speed > highGearSpeedRanges[gear - 2]) {
      status = "Cannot decrease gear, decrease speed first.";
    } else {
      gear--;
      status = "OK: everything is OK.";
    }
    return this;
  }
}