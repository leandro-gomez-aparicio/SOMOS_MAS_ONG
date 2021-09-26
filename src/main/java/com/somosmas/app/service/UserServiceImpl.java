package com.somosmas.app.service;

import com.somosmas.app.config.security.RoleType;
import com.somosmas.app.exception.custom.SendEmailException;
import com.somosmas.app.exception.custom.UserAlreadyExistException;
import com.somosmas.app.model.entity.Organization;
import com.somosmas.app.model.entity.User;
import com.somosmas.app.model.request.UserDetailsRequest;
import com.somosmas.app.model.response.ListUserResponse;
import com.somosmas.app.model.response.UserDetailsResponse;
import com.somosmas.app.repository.IOrganizationRepository;
import com.somosmas.app.repository.IRoleRepository;
import com.somosmas.app.repository.IUserRepository;
import com.somosmas.app.service.abstraction.IUserService;
import com.somosmas.app.util.ConvertUtil;
import com.somosmas.app.util.TimestampUtil;
import com.somosmas.app.util.jwt.JwtUtil;
import com.somosmas.app.util.mail.SendEmail;
import com.somosmas.app.util.mail.template.ContactTemplate;
import com.somosmas.app.util.mail.template.ContentTemplate;
import com.somosmas.app.util.mail.template.EmailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, IUserService {

    private static final String USER_ID_NOT_FOUND = "User ID: {0} not found.";
    private static final String USER_NOT_FOUND_ERROR_MESSAGE = "User not found: {0}";
    private static final String USER_REGISTERED_WELCOME_EMAIL_SUBJECT = "Welcome to Somos mas {0} !";
    private static final String WELCOME_MESSAGE_CONTENT_TYPE = "text/plain";

    @Autowired
    private IOrganizationRepository organizationRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private SendEmail sendEmail;


    @Override
    public void delete(Long id) throws NoSuchElementException {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(MessageFormat.format(USER_ID_NOT_FOUND, id)));
        user.setSoftDelete(true);
        userRepository.save(user);
    }

    @Override
    public UserDetailsResponse register(UserDetailsRequest registerUserRequest) throws UserAlreadyExistException, SendEmailException {
        if ((userRepository.findByEmail(registerUserRequest.getEmail())).isPresent()) {
            throw new UserAlreadyExistException(registerUserRequest.getEmail());
        }

        User user = buildUser(registerUserRequest);
        userRepository.save(user);

        Organization organization = organizationRepository.findAll().get(0);
        String subject = MessageFormat.format(USER_REGISTERED_WELCOME_EMAIL_SUBJECT, user.getFirstName());
        ContactTemplate contact = new ContactTemplate(user.getFirstName(), user.getLastName(), user.getEmail());
        ContentTemplate content = new ContentTemplate(WELCOME_MESSAGE_CONTENT_TYPE, organization.getWelcomeText());
        sendEmail.execute(new EmailTemplate(subject, organization, contact, content));

        return buildResponse(user);
    }

    private UserDetailsResponse buildResponse(User user) {
        UserDetailsResponse response = ConvertUtil.convertToDto(user);
        response.setRole(user.getRole().getName());
        response.setToken(jwtUtil.generateToken(response));
        return response;
    }

    private User buildUser(UserDetailsRequest registerUserRequest) {
        User user = ConvertUtil.convertToEntity(registerUserRequest);
        user.setTimestamp(TimestampUtil.getCurrentTime());
        user.setSoftDelete(false);
        user.setRole(roleRepository.findByName(RoleType.ROLE_USER.name()));
        user.setPassword(bCryptPasswordEncoder.encode(registerUserRequest.getPassword()));
        return user;
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
    public ListUserResponse listActiveUsers() {
        List<UserDetailsResponse> list = ConvertUtil.convertToDto(userRepository.findBySoftDeleteIsNullOrSoftDeleteIsFalse());
        ListUserResponse listUserResponse = new ListUserResponse();
        listUserResponse.setUsers(list);
        return listUserResponse;
    }

    @Override
    public UserDetailsResponse getUserDetailsBy(String authorizationHeader) throws UsernameNotFoundException {
        String userEmail = jwtUtil.extractUserEmail(authorizationHeader);
        Optional<User> userEntity = userRepository.findByEmail(userEmail);
        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException(MessageFormat.format(USER_NOT_FOUND_ERROR_MESSAGE, userEmail));
        }
        UserDetailsResponse userResponse = new UserDetailsResponse();
        userResponse.setEmail(userEntity.get().getEmail());
        userResponse.setFirstName(userEntity.get().getFirstName());
        userResponse.setLastName(userEntity.get().getLastName());
        userResponse.setPhoto(userEntity.get().getPhoto());
        return userResponse;
    }

    public UserDetailsResponse update(Long id, UserDetailsRequest userRequest) throws NoSuchElementException {
        userRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(MessageFormat.format(USER_ID_NOT_FOUND, id)));

        User user = ConvertUtil.convertToEntity(userRequest);
        user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        user.setTimestamp(TimestampUtil.getCurrentTime());
        user.setIdUser(id);

        userRepository.save(user);
        return ConvertUtil.convertToDto(user);
    }

}
