package com.xinda.entity2;

import java.math.BigDecimal;
import java.util.Date;

import com.xinda.entity.Meter;
import com.xinda.entity.User;

public class TransactionRecord {
    private Integer trid;

    private Meter operaMeter;

    private User operaUser;

    private Byte type;

    private BigDecimal operaAmout;

    private Date tradingTime;

	public Integer getTrid() {
		return trid;
	}

	public void setTrid(Integer trid) {
		this.trid = trid;
	}

	public Meter getOperaMeter() {
		return operaMeter;
	}

	public void setOperaMeter(Meter operaMeter) {
		this.operaMeter = operaMeter;
	}

	public User getOperaUser() {
		return operaUser;
	}

	public void setOperaUser(User operaUser) {
		this.operaUser = operaUser;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public BigDecimal getOperaAmout() {
		return operaAmout;
	}

	public void setOperaAmout(BigDecimal operaAmout) {
		this.operaAmout = operaAmout;
	}

	public Date getTradingTime() {
		return tradingTime;
	}

	public void setTradingTime(Date tradingTime) {
		this.tradingTime = tradingTime;
	}
}