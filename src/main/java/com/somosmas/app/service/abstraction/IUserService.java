package com.somosmas.app.service.abstraction;

import com.somosmas.app.exception.UserAlreadyExistException;
import com.somosmas.app.model.entity.User;
import com.somosmas.app.model.request.RegisterUserRequest;
import com.somosmas.app.model.response.RegisterUserResponse;

import java.util.Optional;

public interface IUserService {

    Optional<User> findById(Long id);

    void delete(Long id);

    RegisterUserResponse register(RegisterUserRequest registerUserRequest) throws UserAlreadyExistException;
}
