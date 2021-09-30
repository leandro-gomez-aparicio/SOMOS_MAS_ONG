package com.somosmas.app.service.abstraction;

import org.springframework.web.util.UriComponentsBuilder;

import com.somosmas.app.model.request.MemberRequest;
import com.somosmas.app.model.response.ListMemberResponse;
import com.somosmas.app.model.response.MemberResponse;

public interface IMemberService {

    void create(MemberRequest memberRequest);

    void delete(Long idMember);

    ListMemberResponse getMembers(int page, UriComponentsBuilder uriBuilder);

    MemberResponse update(MemberRequest member, Long id);

}
