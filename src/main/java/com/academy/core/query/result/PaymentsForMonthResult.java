package com.academy.core.query.result;

import com.academy.core.dto.PaymentBean;

import java.util.Collections;
import java.util.List;

public class PaymentsForMonthResult implements QueryResult {

	private List<PaymentBean> payments = Collections.emptyList();

	public List<PaymentBean> getPayments() {
		return payments;
	}

	public void setPayments(List<PaymentBean> payments) {
		this.payments = payments;
	}
	
	public void addPayment(PaymentBean payment){
		payments.add(payment);
	}
	
}
