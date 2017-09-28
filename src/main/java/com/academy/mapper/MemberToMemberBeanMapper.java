package com.academy.mapper;

import com.academy.core.domain.Member;
import com.academy.core.dto.MemberBean;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

/**
 * Created by zloyzenka on 7/12/16.
 */
public class MemberToMemberBeanMapper extends BidirectionalConverter<Member, MemberBean> {
    @Override
    public MemberBean convertTo(Member source, Type<MemberBean> destinationType, MappingContext mappingContext) {

//        mappingContext.

        return null;
    }

    @Override
    public Member convertFrom(MemberBean source, Type<Member> destinationType, MappingContext mappingContext) {
        return null;
    }
}
