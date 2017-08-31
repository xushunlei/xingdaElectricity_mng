package com.xinda.entity;

import java.math.BigDecimal;

public class Meter {
    private Integer meterId;

    private String meterName;

    private String meterDescription;

    private Integer meterType;

    private Integer meterStatus;

    private String meterAddress;

    private Integer meterRate;

    private String meterPort;

    private String meterUserName;

    private String meterUserIdcard;

    private String meterUserMobile;

    private String meterUserAddress;

    private BigDecimal meterBalance;

    private BigDecimal meterTotalPay;

    private BigDecimal meterValue;

    private BigDecimal meterMaxOverdraft;

    private BigDecimal meterCurrOverdraft;

    private Zone meterFromZone;

    private Proxy meterFromProxy;

    public Integer getMeterId() {
        return meterId;
    }

    public void setMeterId(Integer meterId) {
        this.meterId = meterId;
    }

    public String getMeterName() {
        return meterName;
    }

    public void setMeterName(String meterName) {
        this.meterName = meterName == null ? null : meterName.trim();
    }

    public String getMeterDescription() {
        return meterDescription;
    }

    public void setMeterDescription(String meterDescription) {
        this.meterDescription = meterDescription == null ? null : meterDescription.trim();
    }

    public Integer getMeterType() {
        return meterType;
    }

    public void setMeterType(Integer meterType) {
        this.meterType = meterType;
    }

    public Integer getMeterStatus() {
        return meterStatus;
    }

    public void setMeterStatus(Integer meterStatus) {
        this.meterStatus = meterStatus;
    }

    public String getMeterAddress() {
        return meterAddress;
    }

    public void setMeterAddress(String meterAddress) {
        this.meterAddress = meterAddress == null ? null : meterAddress.trim();
    }

    public Integer getMeterRate() {
        return meterRate;
    }

    public void setMeterRate(Integer meterRate) {
        this.meterRate = meterRate;
    }

    public String getMeterPort() {
        return meterPort;
    }

    public void setMeterPort(String meterPort) {
        this.meterPort = meterPort == null ? null : meterPort.trim();
    }

    public String getMeterUserName() {
        return meterUserName;
    }

    public void setMeterUserName(String meterUserName) {
        this.meterUserName = meterUserName == null ? null : meterUserName.trim();
    }

    public String getMeterUserIdcard() {
        return meterUserIdcard;
    }

    public void setMeterUserIdcard(String meterUserIdcard) {
        this.meterUserIdcard = meterUserIdcard == null ? null : meterUserIdcard.trim();
    }

    public String getMeterUserMobile() {
        return meterUserMobile;
    }

    public void setMeterUserMobile(String meterUserMobile) {
        this.meterUserMobile = meterUserMobile == null ? null : meterUserMobile.trim();
    }

    public String getMeterUserAddress() {
        return meterUserAddress;
    }

    public void setMeterUserAddress(String meterUserAddress) {
        this.meterUserAddress = meterUserAddress == null ? null : meterUserAddress.trim();
    }

    public BigDecimal getMeterBalance() {
        return meterBalance;
    }

    public void setMeterBalance(BigDecimal meterBalance) {
        this.meterBalance = meterBalance;
    }

    public BigDecimal getMeterTotalPay() {
        return meterTotalPay;
    }

    public void setMeterTotalPay(BigDecimal meterTotalPay) {
        this.meterTotalPay = meterTotalPay;
    }

    public BigDecimal getMeterValue() {
        return meterValue;
    }

    public void setMeterValue(BigDecimal meterValue) {
        this.meterValue = meterValue;
    }

    public BigDecimal getMeterMaxOverdraft() {
        return meterMaxOverdraft;
    }

    public void setMeterMaxOverdraft(BigDecimal meterMaxOverdraft) {
        this.meterMaxOverdraft = meterMaxOverdraft;
    }

    public BigDecimal getMeterCurrOverdraft() {
        return meterCurrOverdraft;
    }

    public void setMeterCurrOverdraft(BigDecimal meterCurrOverdraft) {
        this.meterCurrOverdraft = meterCurrOverdraft;
    }

	public Zone getMeterFromZone() {
		return meterFromZone;
	}

	public void setMeterFromZone(Zone meterFromZone) {
		this.meterFromZone = meterFromZone;
	}

	public Proxy getMeterFromProxy() {
		return meterFromProxy;
	}

	public void setMeterFromProxy(Proxy meterFromProxy) {
		this.meterFromProxy = meterFromProxy;
	}

	@Override
	public String toString() {
		return "Meter [meterId=" + meterId + ", meterName=" + meterName
				+ ", meterDescription=" + meterDescription + ", meterType="
				+ meterType + ", meterStatus=" + meterStatus
				+ ", meterAddress=" + meterAddress + ", meterRate=" + meterRate
				+ ", meterPort=" + meterPort + ", meterUserName="
				+ meterUserName + ", meterUserIdcard=" + meterUserIdcard
				+ ", meterUserMobile=" + meterUserMobile
				+ ", meterUserAddress=" + meterUserAddress + ", meterBalance="
				+ meterBalance + ", meterTotalPay=" + meterTotalPay
				+ ", meterValue=" + meterValue + ", meterMaxOverdraft="
				+ meterMaxOverdraft + ", meterCurrOverdraft="
				+ meterCurrOverdraft + ", meterFromZone=" + meterFromZone
				+ ", meterFromproxy=" + meterFromProxy + "]";
	}
}