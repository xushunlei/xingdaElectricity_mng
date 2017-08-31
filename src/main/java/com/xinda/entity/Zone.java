package com.xinda.entity;

public class Zone {
    private Integer zoneId;

    private String zoneName;

    private String zoneDescription;

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName == null ? null : zoneName.trim();
    }

    public String getZoneDescription() {
        return zoneDescription;
    }

    public void setZoneDescription(String zoneDescription) {
        this.zoneDescription = zoneDescription == null ? null : zoneDescription.trim();
    }

	@Override
	public String toString() {
		return "Zone [zoneId=" + zoneId + ", zoneName=" + zoneName
				+ ", zoneDescription=" + zoneDescription + "]";
	}
}