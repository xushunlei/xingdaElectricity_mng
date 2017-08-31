package com.xinda.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Price {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
    private Integer priceId;

    private BigDecimal priceValue;

    private Date priceCreateDate;

    private Date priceStartDate;

    private Date priceOverDate;

    private User priceOperator;

    private Integer priceActive;

    public Integer getPriceId() {
        return priceId;
    }

    public void setPriceId(Integer priceId) {
        this.priceId = priceId;
    }

    public BigDecimal getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(BigDecimal priceValue) {
        this.priceValue = priceValue;
    }

    public Date getPriceCreateDate() {
        return priceCreateDate;
    }
    public String getPriceCreateDateStr() {
    	return priceCreateDate==null?"":sdf.format(priceCreateDate);
    }

    public void setPriceCreateDate(Date priceCreateDate) {
        this.priceCreateDate = priceCreateDate;
    }

    public Date getPriceStartDate() {
        return priceStartDate;
    }
    public String getPriceStartDateStr() {
    	return priceStartDate==null?"":sdf.format(priceStartDate);
    }

    public void setPriceStartDate(Date priceStartDate) {
        this.priceStartDate = priceStartDate;
    }

    public Date getPriceOverDate() {
        return priceOverDate;
    }
    public String getPriceOverDateSre() {
    	return priceOverDate==null?"":sdf.format(priceOverDate);
    }

    public void setPriceOverDate(Date priceOverDate) {
        this.priceOverDate = priceOverDate;
    }

    public Integer getPriceActive() {
        return priceActive;
    }

    public void setPriceActive(Integer priceActive) {
        this.priceActive = priceActive;
    }

	public User getPriceOperator() {
		return priceOperator;
	}

	public void setPriceOperator(User priceOperator) {
		this.priceOperator = priceOperator;
	}

	@Override
	public String toString() {
		return "Price [priceId=" + priceId + ", priceValue=" + priceValue
				+ ", priceCreateDate=" + priceCreateDate + ", priceStartDate="
				+ priceStartDate + ", priceOverDate=" + priceOverDate
				+ ", priceOperator=" + priceOperator + ", priceActive="
				+ priceActive + "]";
	}
}