package com.somosmas.app.service;

import com.somosmas.app.config.security.RoleType;
import com.somosmas.app.exception.custom.UserAlreadyExistException;
import com.somosmas.app.model.entity.User;
import com.somosmas.app.model.request.UserDetailsRequest;
import com.somosmas.app.model.response.UserDetailsResponse;
import com.somosmas.app.repository.IRoleRepository;
import com.somosmas.app.repository.IUserRepository;
import com.somosmas.app.service.abstraction.IUserService;
import com.somosmas.app.util.ConvertUtil;
import com.somosmas.app.util.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    @Autowired
    private JwtUtil jwtUtil;

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
    public UserDetailsResponse register(UserDetailsRequest registerUserRequest) throws UserAlreadyExistException {
        if ((userRepository.findByEmail(registerUserRequest.getEmail())).isPresent()) {
            throw new UserAlreadyExistException(registerUserRequest.getEmail());
        }

        User user = ConvertUtil.convertToEntity(registerUserRequest);
        user.setTimestamp(Timestamp.from(Instant.now()));
        user.setSoftDelete(false);
        user.setRole(roleRepository.findByName(RoleType.ROLE_USER.name()));
        user.setPassword(bCryptPasswordEncoder.encode(registerUserRequest.getPassword()));
        userRepository.save(user);
        UserDetailsResponse response = ConvertUtil.convertToDto(user);
        response.setToken(jwtUtil.generateToken(response));
        return response;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(MessageFormat.format(USER_NOT_FOUND_ERROR_MESSAGE, username));
        }
        return user.get();
    }

    @Override
    public List<UserDetailsResponse> listActiveUsers() {
        return ConvertUtil.convertToDto(userRepository.findBySoftDeleteIsNullOrSoftDeleteIsFalse(), UserDetailsResponse.class);
    }

    @Override
    public UserDetailsResponse getUserDetailsBy(String token) throws UsernameNotFoundException {
        String userEmail = jwtUtil.extractUserEmail(token);
        Optional<User> userEntity = userRepository.findByEmail(userEmail);
        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException(MessageFormat.format(USER_NOT_FOUND_ERROR_MESSAGE, userEmail));
        }
        UserDetailsResponse userResponse = new UserDetailsResponse();
        userResponse.setEmail(userEntity.get().getEmail());
        userResponse.setFirstName(userEntity.get().getFirstName());
        userResponse.setLastName(userEntity.get().getLastName());
        userResponse.setPhoto(userEntity.get().getPhoto());
        ;
        return userResponse;
    }

}
