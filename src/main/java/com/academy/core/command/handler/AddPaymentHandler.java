package com.academy.core.command.handler;

import com.academy.core.command.AddPaymentCommand;
import com.academy.core.command.result.AddPaymentResult;
import com.academy.core.domain.Member;
import com.academy.core.domain.Payment;
import com.academy.core.dto.PaymentBean;
import com.academy.core.function.PaymentBeanToPaymentFunction;
import com.academy.mongo.MemberRepository;
import com.academy.mongo.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AddPaymentHandler implements CommandHandler<AddPaymentCommand, AddPaymentResult> {

	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	private static final Function<PaymentBean, Payment> PAYMENT_BEAN_TO_PAYMENT = new PaymentBeanToPaymentFunction();
	
	@Override
	public AddPaymentResult execute(AddPaymentCommand command) {

		Member member = memberRepository.findOne(command.getPayment().getMemberId());
		
		Payment payment = PAYMENT_BEAN_TO_PAYMENT.apply(command.getPayment());
		
		payment.setMemberId(member.getId());
		payment.setAcademyName(member.getAcademyName());
		payment = paymentRepository.save(payment);
		member.addPayment(payment);
		
		memberRepository.save(member);
		
		return new AddPaymentResult(member.getId());
	}

	
}
