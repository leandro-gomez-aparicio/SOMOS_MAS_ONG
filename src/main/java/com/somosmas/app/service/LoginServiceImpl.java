package com.somosmas.app.service;

import com.somosmas.app.exception.custom.AuthenticationDeniedException;
import com.somosmas.app.exception.custom.ConstraintViolationException;
import com.somosmas.app.model.entity.User;
import com.somosmas.app.model.request.LoginRequest;
import com.somosmas.app.model.response.UserDetailsResponse;
import com.somosmas.app.repository.IUserRepository;
import com.somosmas.app.service.abstraction.ILoginService;
import com.somosmas.app.util.ConvertUtil;
import com.somosmas.app.util.jwt.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    IUserRepository userRepository;
    @Autowired
    JwtUtil jwtUtil;

    private final Validator validator;

    public LoginServiceImpl() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public UserDetailsResponse authentication(LoginRequest loginRequest) throws AuthenticationDeniedException, ConstraintViolationException {
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
        validate(loginRequest);
        if (userOptional.isPresent()
                && (userOptional.get().isSameUser(loginRequest.getPassword()))) {
        	UserDetailsResponse loginResponse = ConvertUtil.convertToDto(userOptional.get());
        	loginResponse.setToken(jwtUtil.generateToken(loginResponse));
        	return loginResponse;
        } else {
            throw new AuthenticationDeniedException();
        }
    }

    //TODO: make more generic
    private void validate(LoginRequest request) throws ConstraintViolationException {
        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(request);
        for (ConstraintViolation<LoginRequest> violation : violations) {
            throw new ConstraintViolationException(violation.getMessage());
        }
    }
}
