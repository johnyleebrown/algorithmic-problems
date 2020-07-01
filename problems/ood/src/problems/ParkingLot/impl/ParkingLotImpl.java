package problems.ParkingLot.impl;

import problems.ParkingLot.ParkingLotLevel;

public class ParkingLotImpl {
    private static final int PARKING_LOT_LEVEL_NUMBER_DEFAULT = 5;
    private final ParkingLotLevel[] parkingLotLevels;
    private int numberOfAvailableSpots;

    public ParkingLotImpl() {
        parkingLotLevels = new ParkingLotLevel[PARKING_LOT_LEVEL_NUMBER_DEFAULT];
    }

    public ParkingLotImpl(int numberOfParkingLotLevels) {
        parkingLotLevels = new ParkingLotLevel[numberOfParkingLotLevels];
    }

    private boolean hasAvailableSpots() {
        return numberOfAvailableSpots > 0;
    }
}
