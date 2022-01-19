package ood.locker_system.model;

import java.util.List;

public class PickupLocation {

	private String pickupLocationId;
	private Location location;
	private Schedule schedule;
	private List<Locker> lockers;
	private boolean empty;
	private int availableCount;

	public String getPickupLocationId() {
		return pickupLocationId;
	}

	public Location getLocation() {
		return location;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public List<Locker> getLockers() {
		return lockers;
	}

	public boolean isEmpty() {
		return empty;
	}

	public int getAvailableCount() {
		return availableCount;
	}
}