package ParkingLotSuggestion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingLot {

	List<Integer> floors = new ArrayList<Integer>();
	int firstFloor;
	int lastFloor;

	// passing first floor, last floor and list of available places in different
	// floors. 0 floor is also included
	public ParkingLot(List<Integer> floors, int firstFloor, int lastFloor) {
		if ((floors.size() - 1 == lastFloor - firstFloor)) {
			this.floors = floors;
			this.firstFloor = firstFloor;
			this.lastFloor = lastFloor;
		} else {
			System.out.println(
					"Invalid input. Please check if entered correct amount of available places between first and last floors.");
		}
	}

	public int suggestFloor(int floorNumber, Car car) {

		int availableFloor = 0;
		int availableLowerFloor = 0;
		boolean isAvailable = false;
		boolean isAvailableLF = false;
		boolean isPlaces = true;
		int indexFloor = 0;
		int index = floorNumber - firstFloor;
		String carType = null;

		// method returns closestFloor. Since the parking lot have 0 floor, I am setting
		// default value of closestFloor to -100. If parking lot have no available
		// places, method returns -100.
		int closestFloor = -100;

		switch (car) {
		case PETROL:
			carType = "petrol car";

			if (floors.get(index) > 0) {
				closestFloor = index + firstFloor;
				indexFloor = index;
			} else {

				// finding closest available higher floor
				for (int a = index + 1; a < floors.size(); a++) {
					if (floors.get(a) > 0) {
						availableFloor = a;
						isAvailable = true;
						break;
					}
				}

				// finding closest available lower floor
				for (int a = index - 1; a >= 0; a--) {
					if (floors.get(a) > 0) {
						availableLowerFloor = a;
						isAvailableLF = true;
						break;
					}
				}

				if (!isAvailable && !isAvailableLF) {
					isPlaces = false;
					break;
				}

				if (!isAvailableLF && isAvailable) {
					closestFloor = availableFloor + firstFloor;
					indexFloor = availableFloor;
				}

				if (!isAvailable && isAvailableLF) {
					closestFloor = availableLowerFloor + firstFloor;
					indexFloor = availableLowerFloor;
				}

				if ((isAvailable && isAvailableLF)) {

					// if closest available higher floor is closer (or equal) than closest available
					// lower floor, car drives to closest higher floor
					if (availableFloor - index <= index - availableLowerFloor) {
						closestFloor = availableFloor + firstFloor;
						indexFloor = availableFloor;
					} else {
						closestFloor = availableLowerFloor + firstFloor;
						indexFloor = availableLowerFloor;
					}
				}
			}
			break;

		case ELECTRIC:
			carType = "electric car";

			// finding available floor from two last floors starting from second last
			for (int a = floors.size() - 2; a <= floors.size() - 1; a++) {
				if (floors.get(a) > 0) {
					availableFloor = a;
					isAvailable = true;
					break;
				}
			}

			if (!isAvailable) {
				isPlaces = false;
				break;
			}

			// checking if car enters last floor and last floor is available or if there are
			// only last floor which is available for electric cars
			if ((availableFloor == floors.size() - 1)
					|| ((floorNumber == lastFloor) && (floors.get(floors.size() - 1) > 0))) {
				closestFloor = lastFloor;
				indexFloor = floors.size() - 1;
			} else {
				closestFloor = lastFloor - 1;
				indexFloor = floors.size() - 2;

			}
			break;

		case VAN:
			carType = "van";

			// floors are without minus floors
			if (firstFloor >= 0) {
				isPlaces = false;
				break;
			}
			// only one minus floor exists
			if (firstFloor == -1) {
				if (floors.get(0) > 0) {
					closestFloor = firstFloor;
					indexFloor = 0;
				} else {
					isPlaces = false;
					break;
				}
			}
			// there are more one 1 minus floors
			if (firstFloor < -1) {
				// finding available floor from two first minus floors starting from second one
				for (int a = 1; a >= 0; a--) {
					if (floors.get(a) > 0) {
						availableFloor = a;
						isAvailable = true;
						break;
					}
				}

				if (!isAvailable) {
					isPlaces = false;
					break;
				}

				// checking if car enters first floor and this floor is available or if there
				// are only the first floor which is available for vans
				if ((availableFloor == 0) || ((floorNumber == firstFloor) && (floors.get(index) > 0))) {
					indexFloor = 0;
					closestFloor = firstFloor;
				} else {
					indexFloor = 1;
					closestFloor = firstFloor + 1;
				}
				break;
			}
		}

		if (isPlaces) {
			System.out.println("Drive " + carType + " to this floor: " + (closestFloor));
			floors.set(indexFloor, floors.get(indexFloor) - 1);
			System.out.println(
					"Free parking spaces after arrival of " + carType + ": " + Arrays.toString(floors.toArray()));

		} else {
			System.out.println("There are no available parking places for " + carType);
		}
		return closestFloor;
	}
}
