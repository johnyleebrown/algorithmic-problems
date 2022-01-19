package ood.locker_system.service;

import static ood.locker_system.util.LockerComparators.DEFAULT_COMPARATOR;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import ood.locker_system.model.Locker;
import ood.locker_system.model.Package;
import ood.locker_system.model.PickupLocation;

class LockerService {

	PickupLocationService pickupLocationService;

	public LockerService(PickupLocationService pickupLocationService) {
		this.pickupLocationService = pickupLocationService;
	}

	public LockerService INSTANCE() {
		return new LockerService(new PickupLocationService());
	}

	/**
	 * @return lockerId where the package is placed.
	 */
	public Optional<String> placePackage(PickupLocation pickupLocation, Package _package) {

		// check if is filled
		if (pickupLocationService.isFilled(pickupLocation)) {
			return Optional.empty();
		}

		// get empty lockers
		List<Locker> emptyLockers = getEmptyLockers(pickupLocation);
		if (emptyLockers.size() == 0) {
			return Optional.empty();
		}

		// sort by size
		emptyLockers.sort(DEFAULT_COMPARATOR);

		// binary search - get first good locker
		return getFirstGoodLocker(emptyLockers, _package);
	}

	private Optional<String> getFirstGoodLocker(List<Locker> emptyLockers,
			Package _package) {
		int n = emptyLockers.size();
		int lo = -1;
		int hi = n;
		while (hi - lo > 1) {
			int mid = lo + (hi - lo) / 2;
			if (canFit(_package, emptyLockers.get(mid))) {
				hi = mid;
			} else {
				lo = mid;
			}
		}
		Optional<String> result = Optional.empty();
		if (hi < n) {
			Locker locker = emptyLockers.get(hi);
			if (canFit(_package, locker)) {
				result = Optional.of(locker.getLockerId());
			}
		}
		return result;
	}

	private boolean canFit(Package aPackage, Locker locker) {
		return false;
	}

	private List<Locker> getEmptyLockers(PickupLocation pickupLocation) {
		return pickupLocation.getLockers().stream()
				.filter(Locker::isEmpty)
				.collect(Collectors.toList());
	}

	/**
	 * @return true if the package was retrieved successfully.
	 */
	//	public boolean retrievePackage(String packageId) {
	//
	//	}
}
