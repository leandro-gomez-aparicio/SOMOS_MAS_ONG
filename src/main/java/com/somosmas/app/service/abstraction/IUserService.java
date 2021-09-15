package com.somosmas.app.service.abstraction;

import com.somosmas.app.exception.custom.UserAlreadyExistException;
import com.somosmas.app.model.entity.User;
import com.somosmas.app.model.request.UserDetailsRequest;
import com.somosmas.app.model.response.UserDetailsResponse;
import com.somosmas.app.model.response.ListUserResponse;

import java.util.Optional;

public interface IUserService {

    Optional<User> findById(Long id);

    ListUserResponse listActiveUsers();

    void delete(Long id);

    UserDetailsResponse getUserDetailsBy(String token);

    UserDetailsResponse register(UserDetailsRequest registerUserRequest) throws UserAlreadyExistException;
}
