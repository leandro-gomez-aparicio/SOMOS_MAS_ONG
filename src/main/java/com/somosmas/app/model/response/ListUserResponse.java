package com.somosmas.app.model.response;

import java.util.List;

public class ListUserResponse {
    private List<UserDetailsResponse> users;

    public ListUserResponse() {
    }

    public List<UserDetailsResponse> getUsers() {
        return users;
    }

    public void setUsers(List<UserDetailsResponse> users) {
        this.users = users;
    }
}
