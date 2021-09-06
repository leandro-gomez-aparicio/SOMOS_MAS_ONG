package com.somosmas.app.model.response;

import com.somosmas.app.model.common.UserDetails;

public class RegisterUserResponse extends UserDetails {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
