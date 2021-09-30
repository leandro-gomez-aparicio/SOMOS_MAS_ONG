package com.somosmas.app.service;

import com.somosmas.app.model.entity.Member;
import com.somosmas.app.model.request.MemberRequest;
import com.somosmas.app.model.response.ListMemberResponse;
import com.somosmas.app.model.response.MemberResponse;
import com.somosmas.app.repository.IMemberRepository;
import com.somosmas.app.service.abstraction.IMemberService;
import com.somosmas.app.util.ConvertUtil;
import com.somosmas.app.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MemberServiceImp implements IMemberService {

    private static final String MEMBER_ID_NOT_FOUND = "Member ID: {0} not found.";
    private static final String MEMBER_PAGE_NOT_FOUND = "Page {0} not found.";

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
        Member member = getMember(idMember);
        member.setSoftDelete(true);
        memberRepository.save(member);
    }

    private Member getMember(Long idMember) {
        return memberRepository.findById(idMember)
                .orElseThrow(() -> new NoSuchElementException(MessageFormat.format(MEMBER_ID_NOT_FOUND, idMember)));
    }

    @Override
    public ListMemberResponse getMembers(int pageReq, UriComponentsBuilder uriBuilder) throws NoSuchElementException{
    	
        Pageable page = PageRequest.of(pageReq, 10);
        Page<Member> pageMembers = memberRepository.findBySoftDeleteIsNullOrSoftDeleteIsFalse(page);       
        if (pageReq > pageMembers.getTotalPages()-1) {
        	throw new NoSuchElementException(MessageFormat.format(MEMBER_PAGE_NOT_FOUND, pageReq));
        }
        
        /* List Members*/
        List<MemberResponse> membersResponse = new ArrayList<>();       
        for(Member member : pageMembers.getContent()) {
        	membersResponse.add(ConvertUtil.convertToDto(member));
        }
        /* Create ListMemberResponse*/
        ListMemberResponse response = new ListMemberResponse();
        response.setMembers(membersResponse);
        
        /* Previous Page and Next Page Link Building */
    	uriBuilder.path("/members/");
        String nextPag = constructNextPageUri(uriBuilder, pageReq);
        String prevPag = constructPrevPageUri(uriBuilder, pageReq);      
        if (page.getPageNumber() == 0) {
        	prevPag = null;
        }
        if (page.getPageNumber() == pageMembers.getTotalPages()-1) {
        	nextPag = null;
        }
        
        response.setPrevPag(prevPag);
        response.setNextPag(nextPag);

        return response;
    }

    @Override
    public MemberResponse update(MemberRequest memberRequest, Long id) {
        Member member = getMember(id);

        Member memberUpdated = ConvertUtil.convertToEntity(memberRequest);
        memberUpdated.setIdMember(id);
        memberUpdated.setTimestamp(TimestampUtil.getCurrentTime());
        memberUpdated.setSoftDelete(member.getSoftDelete());
        memberRepository.save(memberUpdated);

        return ConvertUtil.convertToDto(memberUpdated);
    }
    
    String constructNextPageUri(final UriComponentsBuilder uriBuilder, final int page) {
        return uriBuilder.replaceQueryParam("page", page + 1).build().encode().toUriString();
    }
    
    String constructPrevPageUri(final UriComponentsBuilder uriBuilder, final int page) {
        return uriBuilder.replaceQueryParam("page", page - 1).build().encode().toUriString();
    }
}
