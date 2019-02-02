package ParkingLotSuggestion;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		ParkingLot parkingLot = new ParkingLot(new ArrayList<Integer>(Arrays.asList(2, 1, 1, 0, 1, 1)), -3, 2);
		parkingLot.suggestFloor(1, Car.PETROL);
		parkingLot.suggestFloor(2, Car.ELECTRIC);
		parkingLot.suggestFloor(1, Car.VAN);
		parkingLot.suggestFloor(1, Car.VAN);

	}

}
