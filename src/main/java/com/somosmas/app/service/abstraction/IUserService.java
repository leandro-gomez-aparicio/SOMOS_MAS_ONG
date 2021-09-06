package com.somosmas.app.service.abstraction;

import com.somosmas.app.exception.UserAlreadyExistException;
import com.somosmas.app.model.entity.User;
import com.somosmas.app.model.request.RegisterUserRequest;
import com.somosmas.app.model.response.RegisterUserResponse;
import com.somosmas.app.model.response.UserDetailsResponse;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    Optional<User> findById(Long id);
    List<UserDetailsResponse> listActiveUsers();
    void delete(Long id);

    RegisterUserResponse register(RegisterUserRequest registerUserRequest) throws UserAlreadyExistException;
}
