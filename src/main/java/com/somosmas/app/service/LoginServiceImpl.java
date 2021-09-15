package com.somosmas.app.service;

import com.somosmas.app.model.entity.User;
import com.somosmas.app.model.request.UserDetailsRequest;
import com.somosmas.app.model.response.UserDetailsResponse;
import com.somosmas.app.repository.IUserRepository;
import com.somosmas.app.service.abstraction.ILoginService;
import com.somosmas.app.util.ConvertUtil;
import com.somosmas.app.util.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public UserDetailsResponse authentication(UserDetailsRequest userDetailsRequest) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetailsRequest.getUsername(), userDetailsRequest.getPassword());
        authenticationManager.authenticate(authenticationToken);

        Optional<User> entity = userRepository.findByEmail(userDetailsRequest.getEmail());
        UserDetailsResponse response = new UserDetailsResponse();
        if (entity.isPresent()) {
            response = ConvertUtil.convertToDto(entity.get());
            response.setRole(entity.get().getRole().getName());
            response.setToken(jwtUtil.generateToken(response));
        }
        return response;
    }

}
