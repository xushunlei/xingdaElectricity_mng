package com.xinda.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Log {
    private Long logId;

    private Date logDate;

    private Meter logMeter;

    private BigDecimal logValue;

    private BigDecimal logUnitPrice;

    private BigDecimal logTotalPrice;

    public Long getLogId() {
        return logId;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public Meter getLogMeter() {
		return logMeter;
	}

	public void setLogMeter(Meter logMeter) {
		this.logMeter = logMeter;
	}

	public BigDecimal getLogValue() {
        return logValue;
    }

    public void setLogValue(BigDecimal logValue) {
        this.logValue = logValue;
    }

    public BigDecimal getLogUnitPrice() {
        return logUnitPrice;
    }

    public void setLogUnitPrice(BigDecimal logUnitPrice) {
        this.logUnitPrice = logUnitPrice;
    }

    public BigDecimal getLogTotalPrice() {
        return logTotalPrice;
    }

    public void setLogTotalPrice(BigDecimal logTotalPrice) {
        this.logTotalPrice = logTotalPrice;
    }

	@Override
	public String toString() {
		return "Log [logId=" + logId + ", logDate=" + logDate + ", logMeter="
				+ logMeter + ", logValue=" + logValue + ", logUnitPrice="
				+ logUnitPrice + ", logTotalPrice=" + logTotalPrice + "]";
	}
}