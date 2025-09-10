package com.Emre.ServiceCommerce.services.authentication;

import com.Emre.ServiceCommerce.dto.SignUpRequestDto;
import com.Emre.ServiceCommerce.dto.UserDto;

public interface AuthService {
    UserDto signUpClient(SignUpRequestDto signUpRequestDto);
    boolean presentByEmail(String email);
    UserDto signUpCompany(SignUpRequestDto signUpRequestDto); 

}
