package com.somosmas.app.service.abstraction;

import com.somosmas.app.model.request.MemberRequest;
import com.somosmas.app.model.response.ListMemberResponse;

public interface IMemberService {
    
        void create(MemberRequest memberRequest);
        
	void delete (Long idMember);
        
        ListMemberResponse getMembers();
        
}
