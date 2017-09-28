package com.academy.core.function;

import com.academy.core.domain.Payment;
import com.academy.core.dto.PaymentBean;

import java.util.function.Function;

public class PaymentBeanToPaymentFunction implements Function<PaymentBean, Payment> {

	@Override
	public Payment apply(PaymentBean bean) {
		
		Payment payment = new Payment();
		payment.setAmount(bean.getAmount());
		payment.setId(bean.getId());
		payment.setDate(bean.getPaymentDate());
		payment.setPaidUntill(bean.getPaidUntill());
		
		return payment;
	}

}
