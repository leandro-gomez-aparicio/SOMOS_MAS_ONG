package com.somosmas.app.service.abstraction;

import com.somosmas.app.model.request.MemberRequest;


public interface IMemberService {
    
        void create(MemberRequest memberRequest);
        
	void delete (Long idMember);
}
