package com.somosmas.app.service;

import java.text.MessageFormat;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somosmas.app.model.entity.Member;
import com.somosmas.app.model.request.MemberRequest;
import com.somosmas.app.repository.IMemberRepository;
import com.somosmas.app.service.abstraction.IMemberService;
import com.somosmas.app.util.ConvertUtil;
import com.somosmas.app.util.TimestampUtil;

@Service
public class MemberServiceImp implements IMemberService{
	
	private static final String MEMBER_ID_NOT_FOUND = "Member ID: {0} not found.";

	@Autowired
	private IMemberRepository memberRepository;
	
        @Override
        public void create(MemberRequest memberRequest) {
            Member member = ConvertUtil.convertToEntity(memberRequest);

            member.setTimestamp(TimestampUtil.getCurrentTime());
            member.setSoftDelete(false);

            memberRepository.save(member);
        }
    
	@Override
	public void delete(Long idMember) throws NoSuchElementException {
		Member member = memberRepository.findById(idMember)
				.orElseThrow(() -> new NoSuchElementException(MessageFormat.format(MEMBER_ID_NOT_FOUND, idMember)));
		member.setSoftDelete(true);
		memberRepository.save(member);
	}

}
