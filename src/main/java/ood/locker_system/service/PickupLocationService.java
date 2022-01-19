package ood.locker_system.service;

import ood.locker_system.model.PickupLocation;

public class PickupLocationService {

	public boolean isFilled(PickupLocation pickupLocation) {
		return pickupLocation.getAvailableCount() == 0;
	}
}
