package com.academy.rest.function;

import com.academy.core.dto.PaymentBean;
import com.academy.rest.api.Payment;

import java.util.function.Function;

import static com.academy.utils.DateTimeUtils.dateToString;

public class PaymentBeanToPaymentFunction implements
		Function<PaymentBean, Payment> {

	@Override
	public Payment apply(PaymentBean from) {
		Payment payment =new Payment();
		payment.setAmount(from.getAmount());
		payment.setId(from.getId());
		payment.setMemberId(from.getMemberId());
		payment.setPaidUntil(dateToString(from.getPaidUntill()));
		payment.setPaymentDate(dateToString(from.getPaymentDate()));
		return payment;
	}

}
