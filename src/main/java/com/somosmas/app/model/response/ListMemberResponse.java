package com.somosmas.app.model.response;

import java.util.List;

public class ListMemberResponse {

    private List<MemberResponse> members;
    
    public ListMemberResponse() {
    
    }

    public List<MemberResponse> getMembers() {
        return members;
    }

    public void setMembers(List<MemberResponse> members) {
        this.members = members;
    }
    
}
