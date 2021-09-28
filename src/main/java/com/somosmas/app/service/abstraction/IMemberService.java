package com.somosmas.app.service.abstraction;

import com.somosmas.app.model.request.MemberRequest;
import com.somosmas.app.model.response.ListMemberResponse;
import com.somosmas.app.model.response.MemberResponse;

public interface IMemberService {

    void create(MemberRequest memberRequest);

    void delete(Long idMember);

    ListMemberResponse getMembers();

    MemberResponse update(MemberRequest member, Long id);

}
