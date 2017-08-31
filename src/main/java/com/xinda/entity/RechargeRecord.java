package com.xinda.entity;

import java.math.BigDecimal;
import java.util.Date;

public class RechargeRecord {
    private Integer recordId;

    private Meter recordMeter;

    private User recordUser;
    
    private BigDecimal recordMoney;

    private Date recordTime;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Meter getRecordMeter() {
		return recordMeter;
	}

	public void setRecordMeter(Meter recordMeter) {
		this.recordMeter = recordMeter;
	}

	public User getRecordUser() {
		return recordUser;
	}

	public void setRecordUser(User recordUser) {
		this.recordUser = recordUser;
	}

	public BigDecimal getRecordMoney() {
        return recordMoney;
    }

    public void setRecordMoney(BigDecimal recordMoney) {
        this.recordMoney = recordMoney;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

	@Override
	public String toString() {
		return "RechargeRecord [recordId=" + recordId + ", recordMeter="
				+ recordMeter + ", recordUser=" + recordUser + ", recordMoney="
				+ recordMoney + ", recordTime=" + recordTime + "]";
	}
}