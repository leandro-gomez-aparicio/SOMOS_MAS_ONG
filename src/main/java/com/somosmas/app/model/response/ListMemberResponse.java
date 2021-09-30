package com.somosmas.app.model.response;

import java.util.List;

public class ListMemberResponse {

    private List<MemberResponse> members;
    private String prevPag;
    private String nextPag;
    
    public ListMemberResponse() {
    
    }

    public List<MemberResponse> getMembers() {
        return members;
    }

    public void setMembers(List<MemberResponse> members) {
        this.members = members;
    }

	public String getPrevPag() {
		return prevPag;
	}

	public void setPrevPag(String prevPag) {
		this.prevPag = prevPag;
	}

	public String getNextPag() {
		return nextPag;
	}

	public void setNextPag(String nextPag) {
		this.nextPag = nextPag;
	}
    
}
