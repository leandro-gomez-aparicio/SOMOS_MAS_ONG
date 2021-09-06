package com.somosmas.app.service.abstraction;

import com.somosmas.app.exception.custom.AuthenticationDeniedException;
import com.somosmas.app.exception.custom.ConstraintViolationException;
import com.somosmas.app.model.request.LoginRequest;
import com.somosmas.app.model.response.UserDetailsResponse;

public interface ILoginService {

    UserDetailsResponse authentication(LoginRequest loginRequest) throws AuthenticationDeniedException, ConstraintViolationException;

}
