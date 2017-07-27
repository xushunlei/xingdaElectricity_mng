package com.xinda.entity;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 每日抄录一遍电表用电量
 * @author Administrator
 *
 */
public class DosageRecord {
    private Integer drId;

    private Date runDay;

    private String drMeter;

    private BigDecimal dosage;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

    public Integer getDrId() {
        return drId;
    }

    public void setDrId(Integer drId) {
        this.drId = drId;
    }

    public Date getRunDay() {
        return runDay;
    }

    public void setRunDay(Date runDay) {
        this.runDay = runDay;
    }

    public String getDrMeter() {
        return drMeter;
    }

    public void setDrMeter(String drMeter) {
        this.drMeter = drMeter == null ? null : drMeter.trim();
    }

    public BigDecimal getDosage() {
        return dosage;
    }

    public void setDosage(BigDecimal dosage) {
        this.dosage = dosage;
    }

    public BigDecimal getUnitPrice() {
		return unitPrice;
	}

    public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

    public BigDecimal getTotalPrice() {
		return totalPrice;
	}

    public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "DosageRecord [drId=" + drId + ", runDay=" + runDay
				+ ", drMeter=" + drMeter + ", dosage=" + dosage
				+ ", unitPrice=" + unitPrice + ", totalPrice=" + totalPrice
				+ "]";
	}
}