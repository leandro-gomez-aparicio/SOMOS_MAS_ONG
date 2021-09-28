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
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MemberServiceImp implements IMemberService {

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
        Member member = getMember(idMember);
        member.setSoftDelete(true);
        memberRepository.save(member);
    }

    private Member getMember(Long idMember) {
        return memberRepository.findById(idMember)
                .orElseThrow(() -> new NoSuchElementException(MessageFormat.format(MEMBER_ID_NOT_FOUND, idMember)));
    }

    @Override
    public ListMemberResponse getMembers() {
        List<Member> members = memberRepository.findBySoftDeleteIsNullOrSoftDeleteIsFalse();

        ListMemberResponse response = new ListMemberResponse();

        if (members.isEmpty()) {
            return response;
        }

        List<MemberResponse> membersResponses = new ArrayList<>();
        members.stream().map(ConvertUtil::convertToDto).forEachOrdered(membersResponses::add);
        response.setMembers(membersResponses);
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
}
