package ood.locker_system.model;

import java.util.Date;

public class Locker {

	private String lockerId;
	private String passcode;
	private LockerSize size;
	// current package
	private String packageId;
	// current lock time end
	private Date lockTimeEnd;
	private boolean empty;

	public String getLockerId() {
		return lockerId;
	}

	public String getPasscode() {
		return passcode;
	}

	public LockerSize getSize() {
		return size;
	}

	public String getPackageId() {
		return packageId;
	}

	public Date getLockTimeEnd() {
		return lockTimeEnd;
	}

	public boolean isEmpty() {
		return empty;
	}
}
