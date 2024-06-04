import org.junit.Before;
import org.junit.Test;

import vehicle.ManualTransmission;
import vehicle.RegularManualTransmission;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Testing class for RegularManualTransmission.java.
 */
public class RegularManualTransmissionTest {

  ManualTransmission transmission;
  ManualTransmission easyTransmissions;

  /**
   * Initializes perfectly fine transmission before each test to be used.
   */
  @Before
  public void setup() {
    transmission = new RegularManualTransmission(
            0, 3, 2, 5, 4, 8, 6, 11, 8, 14
    );
    easyTransmissions = new RegularManualTransmission(
            0, 1, 1, 2, 2, 3, 3, 4, 4, 5
    );
  }

  /**
   * Tests for invalid constructions caused by failure to meet
   * speed range requirements.
   */
  @Test
  public void testInvalidConstructions() {
    try {
      ManualTransmission not10Speeds = new RegularManualTransmission(
              1
      );
      fail("Did not throw exception when there weren't enough speeds.");
    } catch (IllegalArgumentException e) {
      //passes
    }

    try {
      ManualTransmission lxGreaterThanHx = new RegularManualTransmission(
              -1, 1, 1, 1, 2, 2, 3, 3, 4, 4
      );
      fail("Did not throw exception when speed range doesn't start with 0.");
    } catch (IllegalArgumentException e) {
      //passes
    }

    try {
      ManualTransmission lxGreaterThanHx = new RegularManualTransmission(
              0, 1, 2, 1, 2, 2, 3, 3, 4, 4
      );
      fail("Did not throw exception when lx is greater than hx.");
    } catch (IllegalArgumentException e) {
      //passes
    }

    try {
      ManualTransmission lx1GreaterThanLx2 = new RegularManualTransmission(
              0, 1, 1, 1, 2, 2, 1, 3, 4, 4
      );
      fail("Did not throw exception when lx is greater than lx+1.");
    } catch (IllegalArgumentException e) {
      //passes
    }

    try {
      ManualTransmission lx1EqualToLx2 = new RegularManualTransmission(
              0, 1, 1, 1, 1, 2, 3, 3, 4, 4
      );
      fail("Did not throw exception when lx is equal to lx+1.");
    } catch (IllegalArgumentException e) {
      //passes
    }

    try {
      ManualTransmission lxGreaterThanHx = new RegularManualTransmission(
              0, 1, 1, 1, 3, 5, 5, 5, 5, 6
      );
      fail("Did not throw exception when non-adjacent gear ranges overlap.");
    } catch (IllegalArgumentException e) {
      //passes
    }

    try {
      ManualTransmission lxGreaterThanHx = new RegularManualTransmission(
              0, 1, 2, 3, 4, 5, 6, 7, 8, 9
      );
      fail("Did not throw exception when gear ranges are non-overlapping.");
    } catch (IllegalArgumentException e) {
      //passes
    }
  }

  /**
   * Tests getStatus() when everything is fine.
   */
  @Test
  public void testGetStatus() {
    transmission.increaseSpeed();
    assertEquals("OK: everything is OK.", transmission.getStatus());
  }

  /**
   * Tests getSpeed().
   */
  @Test
  public void testGetSpeed() {
    assertEquals(0, transmission.getSpeed());
  }

  /**
   * Tests getGear().
   */
  @Test
  public void testGetGear() {
    assertEquals(1, transmission.getGear());
  }

  /**
   * Tests for all possibilities from increaseSpeed().
   */
  @Test
  public void testIncreaseSpeed() {
    transmission.increaseSpeed();
    assertEquals("OK: everything is OK.", transmission.getStatus());
    transmission.increaseSpeed();
    assertEquals("OK: you may increase the gear.", transmission.getStatus());
    transmission.increaseSpeed();
    assertEquals("OK: you may increase the gear.", transmission.getStatus());
    transmission.increaseSpeed();
    assertEquals("Cannot increase speed, increase gear first.", transmission.getStatus());
    transmission.increaseGear();
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 3; j++) {
        transmission.increaseSpeed();
      }
      transmission.increaseGear();
    }
    transmission.increaseSpeed();
    assertEquals("Cannot increase speed. Reached maximum speed.", transmission.getStatus());
  }

  /**
   * Tests for all possibilities from decreaseSpeed().
   */
  @Test
  public void testDecreaseSpeed() {
    transmission.decreaseSpeed();
    assertEquals("Cannot decrease speed. Reached minimum speed.", transmission.getStatus());
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 3; j++) {
        transmission.increaseSpeed();
      }
      transmission.increaseGear();
    }
    transmission.decreaseSpeed();
    assertEquals("OK: everything is OK.", transmission.getStatus());
    transmission.decreaseSpeed();
    transmission.decreaseSpeed();
    assertEquals("OK: you may decrease the gear.", transmission.getStatus());
    transmission.decreaseSpeed();
    transmission.decreaseSpeed();
    transmission.decreaseSpeed();
    assertEquals("OK: you may decrease the gear.", transmission.getStatus());
    transmission.decreaseSpeed();
    assertEquals("Cannot decrease speed, decrease gear first.", transmission.getStatus());
  }

  /**
   * Tests for all possibilities from increaseGear().
   */
  @Test
  public void testIncreaseGear() {
    transmission.increaseGear();
    assertEquals("Cannot increase gear, increase speed first.", transmission.getStatus());
    transmission.increaseSpeed();
    transmission.increaseSpeed();
    transmission.increaseGear();
    assertEquals("OK: everything is OK.", transmission.getStatus());
    for (int i = 0; i < 5; i++) {
      easyTransmissions.increaseGear();
      easyTransmissions.increaseSpeed();
    }
    easyTransmissions.increaseGear();
    assertEquals("Cannot increase gear. Reached maximum gear.", easyTransmissions.getStatus());
  }

  /**
   * Tests for all possibilities from decreaseGear().
   */
  @Test
  public void decreaseGear() {
    //    0, 3, 2, 5, 4, 8, 6, 11, 8, 14
    transmission.decreaseGear();
    assertEquals("Cannot decrease gear. Reached minimum gear.", transmission.getStatus());
    for (int i = 0; i < 5; i++) {
      easyTransmissions.increaseGear();
      easyTransmissions.increaseSpeed();
    }
    transmission.increaseSpeed();
    transmission.increaseSpeed();
    transmission.increaseSpeed();
    transmission.increaseGear();
    transmission.decreaseGear();
    assertEquals("OK: everything is OK.", transmission.getStatus());

    easyTransmissions.decreaseGear();
    assertEquals("Cannot decrease gear, decrease speed first.", easyTransmissions.getStatus());
  }
}
