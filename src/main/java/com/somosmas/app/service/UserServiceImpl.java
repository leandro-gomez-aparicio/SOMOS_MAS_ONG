package com.somosmas.app.service;

import com.somosmas.app.config.security.RoleType;
import com.somosmas.app.exception.UserAlreadyExistException;
import com.somosmas.app.model.entity.User;
import com.somosmas.app.model.request.RegisterUserRequest;
import com.somosmas.app.model.response.RegisterUserResponse;
import com.somosmas.app.repository.IRoleRepository;
import com.somosmas.app.repository.IUserRepository;
import com.somosmas.app.service.abstraction.IUserService;
import com.somosmas.app.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserDetailsService, IUserService {

    private static final String USER_ID_NOT_FOUND = "User ID: {0} not found.";
    private static final String USER_NOT_FOUND_ERROR_MESSAGE = "User not found: {0}";

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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

    @Override
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) throws UserAlreadyExistException {
        if ((userRepository.findByEmail(registerUserRequest.getEmail())).isPresent()) {
            throw new UserAlreadyExistException(registerUserRequest.getEmail());
        }

        User user = new User();
        user.setEmail(registerUserRequest.getEmail());
        user.setFirstName(registerUserRequest.getFirstName());
        user.setLastName(registerUserRequest.getLastName());
        user.setPhoto(registerUserRequest.getPhoto());
        user.setTimestamp(Timestamp.from(Instant.now()));
        user.setSoftDelete(false);
        user.setRole(roleRepository.findByName(RoleType.ROLE_USER.name()));
        user.setPassword(bCryptPasswordEncoder.encode(registerUserRequest.getPassword()));
        userRepository.save(user);

        // TODO: this should be replace by JWT
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setToken(UUID.randomUUID().toString());
        registerUserResponse.setEmail(registerUserRequest.getEmail());
        return registerUserResponse;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(MessageFormat.format(USER_NOT_FOUND_ERROR_MESSAGE, username));
        }
        return (UserDetails) user.get();
    }
}
