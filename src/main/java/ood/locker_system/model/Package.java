package ood.locker_system.model;

public class Package {

	private String packageId;
	private PackageSize size;
	private String userId;
	private Contents contents;
	private String destination;

	public String getPackageId() {
		return packageId;
	}

	public PackageSize getSize() {
		return size;
	}

	public String getUserId() {
		return userId;
	}

	public Contents getContents() {
		return contents;
	}

	public String getDestination() {
		return destination;
	}
}
