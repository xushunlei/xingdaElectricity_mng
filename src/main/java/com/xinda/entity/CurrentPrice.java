package com.xinda.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CurrentPrice {
    private Integer id;

    private Date createDate;

    private BigDecimal price;

    private User priceOperator;
    
    private Date startDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

	public User getPriceOperator() {
		return priceOperator;
	}

	public void setPriceOperator(User operator) {
		this.priceOperator = operator;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
}