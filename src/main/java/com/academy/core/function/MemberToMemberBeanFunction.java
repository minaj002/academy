package com.academy.core.function;

import com.academy.core.domain.Member;
import com.academy.core.domain.Payment;
import com.academy.core.dto.MemberBean;
import com.academy.core.dto.SectionBean;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MemberToMemberBeanFunction implements Function<Member, MemberBean> {

	@Override
	public MemberBean apply(Member from) {
		MemberBean bean = new MemberBean();
		bean.setFirstName(from.getFirstName());
		bean.setLastName(from.getLastName());
		bean.setDateOfBirth(from.getDateOfBirth());
		bean.setJoinDate(from.getJoinDate());
		bean.setId(from.getId());
		bean.setPhone(from.getPhone());
		bean.setStreet(from.getAddress().getStreet());
		bean.setCity(from.getAddress().getCity());
		bean.setEmail(from.getEmail());
		bean.setSections(from.getSections().stream().map(section -> {
			SectionBean sectionBean = new SectionBean();
			sectionBean.setId(section.getId());
			sectionBean.setName(section.getName());
			return sectionBean;
		}).collect(Collectors.toList()));

		List<Payment> payments = from.getPayments();

		if (payments != null && !payments.isEmpty()) {
			Collections.sort(payments, new Comparator<Payment>() {
				@Override
				public int compare(Payment p1, Payment p2) {
					return p1.getPaidUntill().compareTo(p2.getPaidUntill());
				}
			});
			bean.setDueDate(payments.get(0).getPaidUntill());
		}
		return bean;
	}

}
