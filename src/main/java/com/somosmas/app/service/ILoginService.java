package com.somosmas.app.service;

import com.somosmas.app.exception.ConstraintViolationException;
import com.somosmas.app.model.request.LoginRequest;
import com.somosmas.app.model.response.LoginResponse;
import com.somosmas.app.exception.AuthenticationDeniedException;

public interface ILoginService {
    LoginResponse authentication (LoginRequest loginRequest) throws AuthenticationDeniedException, ConstraintViolationException;

}
