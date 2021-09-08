package com.somosmas.app.service.abstraction;

import com.somosmas.app.model.request.UserDetailsRequest;
import com.somosmas.app.model.response.UserDetailsResponse;

public interface ILoginService {

    UserDetailsResponse authentication(UserDetailsRequest userDetailsRequest);

}
