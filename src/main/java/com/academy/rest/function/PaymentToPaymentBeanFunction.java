package com.academy.rest.function;

import com.academy.core.dto.PaymentBean;
import com.academy.rest.api.Payment;
import org.joda.time.DateTime;

import java.util.function.Function;

public class PaymentToPaymentBeanFunction implements
		Function<Payment, PaymentBean> {

	@Override
	public PaymentBean apply(Payment from) {

		PaymentBean bean = new PaymentBean();
		
		bean.setAmount(from.getAmount());
		bean.setId(from.getId());
		bean.setPaidUntill(DateTime.parse(from.getPaidUntil()).toDate());
		bean.setPaymentDate(DateTime.parse(from.getPaymentDate()).toDate());
		bean.setMemberId(from.getMemberId());
		
		return bean;
	}

}
