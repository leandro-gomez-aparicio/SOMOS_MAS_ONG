package com.somosmas.app.service;

import com.somosmas.app.model.entity.User;

import java.util.Optional;

public interface IUserService {

    Optional<User> findById(Long id);

    void delete(Long id);

}
