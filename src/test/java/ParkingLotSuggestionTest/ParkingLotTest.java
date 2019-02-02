package ParkingLotSuggestionTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import ParkingLotSuggestion.Car;
import ParkingLotSuggestion.ParkingLot;

public class ParkingLotTest {

	@Test
	public void whenMoreThanOneMinusFloorIsPassed() {
		ParkingLot parkingLot = new ParkingLot(new ArrayList<Integer>(Arrays.asList(2, 1, 1, 0, 1, 1)), -2, 3);
		assertEquals(2, parkingLot.suggestFloor(1, Car.PETROL));
		assertEquals(3, parkingLot.suggestFloor(2, Car.ELECTRIC));
		assertEquals(-1, parkingLot.suggestFloor(1, Car.VAN));
	}

	@Test
	public void whenOnlyOneMinusFloorIsPassed() {
		ParkingLot parkingLot = new ParkingLot(new ArrayList<Integer>(Arrays.asList(1, 1, 1, 0, 1, 1)), -1, 4);
		assertEquals(1, parkingLot.suggestFloor(1, Car.PETROL));
		assertEquals(-1, parkingLot.suggestFloor(2, Car.VAN));
		assertEquals(-100, parkingLot.suggestFloor(1, Car.VAN));
	}

	@Test
	public void whenNoMinusFloorsArePassed() {
		ParkingLot parkingLot = new ParkingLot(new ArrayList<Integer>(Arrays.asList(1, 1, 1, 0, 1, 1)), 1, 6);
		assertEquals(1, parkingLot.suggestFloor(1, Car.PETROL));
		assertEquals(-100, parkingLot.suggestFloor(2, Car.VAN));
		assertEquals(5, parkingLot.suggestFloor(1, Car.ELECTRIC));
		assertEquals(6, parkingLot.suggestFloor(5, Car.ELECTRIC));
	}

}
