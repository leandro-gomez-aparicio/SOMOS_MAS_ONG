package com.somosmas.app.service;

import com.somosmas.app.model.entity.User;
import com.somosmas.app.repository.IUserRepository;
import com.somosmas.app.service.abstraction.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private static final String USER_ID_NOT_FOUND = "User ID: {0} not found.";

    @Autowired
    private IUserRepository userRepository;

    @Override
    public void delete(Long id) throws NoSuchElementException {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(MessageFormat.format(USER_ID_NOT_FOUND, id)));
        user.setSoftDelete(true);
        userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

}
