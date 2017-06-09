package com.xinda.entity;

import java.math.BigDecimal;

public class Meter
{
	private Integer meterId;

	private String meterNumber;

	private Byte meterType;

	private Byte meterStatus;

	private String meterTotalValue;

	private BigDecimal meterMaxBalance;

	private BigDecimal meterBalance;

	private Long meterPromptAmount;

	private Long meterStopAmount;

	private Long meterMaxOverdraft;

	private BigDecimal meterCurrentOverdraft;

	private BigDecimal meterTotalConsumption;

	private BigDecimal electricityPrice;

	private User meterUser;

	private Branch meterBranch;


	public Integer getMeterId()
	{
		return meterId;
	}

	public void setMeterId(Integer meterId)
	{
		this.meterId = meterId;
	}

	public String getMeterNumber()
	{
		return meterNumber;
	}

	public void setMeterNumber(String meterNumber)
	{
		this.meterNumber = meterNumber == null ? null : meterNumber.trim();
	}

	public Byte getMeterType()
	{
		return meterType;
	}

	public void setMeterType(Byte meterType)
	{
		this.meterType = meterType;
	}

	public Byte getMeterStatus()
	{
		return meterStatus;
	}

	public void setMeterStatus(Byte meterStatus)
	{
		this.meterStatus = meterStatus;
	}

	public String getMeterTotalValue()
	{
		return meterTotalValue;
	}

	public void setMeterTotalValue(String meterTotalValue)
	{
		this.meterTotalValue = meterTotalValue == null ? null : meterTotalValue.trim();
	}

	public BigDecimal getMeterMaxBalance()
	{
		return meterMaxBalance;
	}

	public void setMeterMaxBalance(BigDecimal meterMaxBalance)
	{
		this.meterMaxBalance = meterMaxBalance;
	}

	public BigDecimal getMeterBalance()
	{
		return meterBalance;
	}

	public void setMeterBalance(BigDecimal meterBalance)
	{
		this.meterBalance = meterBalance;
	}

	public Long getMeterPromptAmount()
	{
		return meterPromptAmount;
	}

	public void setMeterPromptAmount(Long meterPromptAmount)
	{
		this.meterPromptAmount = meterPromptAmount;
	}

	public Long getMeterStopAmount()
	{
		return meterStopAmount;
	}

	public void setMeterStopAmount(Long meterStopAmount)
	{
		this.meterStopAmount = meterStopAmount;
	}

	public Long getMeterMaxOverdraft()
	{
		return meterMaxOverdraft;
	}

	public void setMeterMaxOverdraft(Long meterMaxOverdraft)
	{
		this.meterMaxOverdraft = meterMaxOverdraft;
	}

	public BigDecimal getMeterCurrentOverdraft()
	{
		return meterCurrentOverdraft;
	}

	public void setMeterCurrentOverdraft(BigDecimal meterCurrentOverdraft)
	{
		this.meterCurrentOverdraft = meterCurrentOverdraft;
	}

	public BigDecimal getMeterTotalConsumption()
	{
		return meterTotalConsumption;
	}

	public void setMeterTotalConsumption(BigDecimal meterTotalConsumption)
	{
		this.meterTotalConsumption = meterTotalConsumption;
	}

	public BigDecimal getElectricityPrice()
	{
		return electricityPrice;
	}

	public void setElectricityPrice(BigDecimal electricityPrice)
	{
		this.electricityPrice = electricityPrice;
	}

	public User getMeterUser()
	{
		return meterUser;
	}

	public void setMeterUser(User meterUser)
	{
		this.meterUser = meterUser;
	}

	public Branch getMeterBranch()
	{
		return meterBranch;
	}

	public void setMeterBranch(Branch meterBranch)
	{
		this.meterBranch = meterBranch;
	}

	@Override
	public String toString()
	{
		return "Meter [meterId=" + meterId + ", meterNumber=" + meterNumber + ", meterType=" + meterType + ", meterStatus=" + meterStatus + ", meterTotalValue=" + meterTotalValue + ", meterMaxBalance=" + meterMaxBalance + ", meterBalance=" + meterBalance + ", meterPromptAmount=" + meterPromptAmount + ", meterStopAmount=" + meterStopAmount + ", meterMaxOverdraft=" + meterMaxOverdraft + ", meterCurrentOverdraft=" + meterCurrentOverdraft + ", meterTotalConsumption=" + meterTotalConsumption + ", electricityPrice=" + electricityPrice + ", meterUser=" + meterUser + ", meterBranch=" + meterBranch + "]";
	}
}