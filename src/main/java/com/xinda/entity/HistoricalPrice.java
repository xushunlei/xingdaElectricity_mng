package com.xinda.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class HistoricalPrice {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
    private Integer pid;

    private Timestamp createDate;

    private User priceOperator;

    private BigDecimal price;
    
    private Timestamp startDate;

    private Timestamp expiredDate;

    private Byte active;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }
    public String getCreateDateStr() {
        return createDate==null?"":sdf.format(createDate);
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

	public User getPriceOperator() {
		return priceOperator;
	}

	public void setPriceOperator(User priceOperator) {
		this.priceOperator = priceOperator;
	}

	public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Timestamp getStartDate() {
		return startDate;
	}
    
    public String getStartDateStr() {
		return startDate==null?"":sdf.format(startDate);
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getExpiredDate() {
        return expiredDate;
    }
	
	public String getExpiredDateStr() {
        return expiredDate==null?"":sdf.format(createDate);
    }

    public void setExpiredDate(Timestamp expiredDate) {
        this.expiredDate = expiredDate;
    }

	public Byte getActive() {
		return active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "HistoricalPrice [pid=" + pid + ", createDate=" + createDate
				+ ", priceOperator=" + priceOperator + ", price=" + price
				+ ", startDate=" + startDate + ", expiredDate=" + expiredDate
				+ ", active=" + active + "]";
	}
    
}