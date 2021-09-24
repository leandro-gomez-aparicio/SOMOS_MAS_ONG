package com.somosmas.app.service.abstraction;

import com.somosmas.app.exception.custom.SendEmailException;
import com.somosmas.app.exception.custom.UserAlreadyExistException;
import com.somosmas.app.model.request.UserDetailsRequest;
import com.somosmas.app.model.response.ListUserResponse;
import com.somosmas.app.model.response.UserDetailsResponse;

import java.util.NoSuchElementException;

public interface IUserService {

    ListUserResponse listActiveUsers();

    void delete(Long id);

    UserDetailsResponse getUserDetailsBy(String authorizationHeader);

    UserDetailsResponse register(UserDetailsRequest registerUserRequest) throws UserAlreadyExistException, SendEmailException;

    UserDetailsResponse update(Long id, UserDetailsRequest userRequest) throws NoSuchElementException;
}
