package ood.locker_system.model;

import java.util.Date;

public class Locker {

	private String id;
	private String passcode;
	private LockerSize size;
	// current package
	private String packageId;
	// current lock time end
	private Date lockTimeEnd;
}
