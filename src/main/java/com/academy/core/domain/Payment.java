package com.academy.core.domain;

import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;
import java.util.Date;

public class Payment {

	@Indexed
	private String id;
	
	private BigDecimal amount;
	
	private Date date;
	
	private Date paidUntill;
	
	private String academyName;
	
	private String memberId;

	public String getId() {
		return id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Date getDate() {
		return date;
	}

	public Date getPaidUntill() {
		return paidUntill;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setPaidUntill(Date paidUntill) {
		this.paidUntill = paidUntill;
	}

	public String getAcademyName() {
		return academyName;
	}

	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
}
