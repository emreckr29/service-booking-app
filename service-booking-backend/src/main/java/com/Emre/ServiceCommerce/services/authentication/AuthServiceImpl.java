package com.Emre.ServiceCommerce.services.authentication;

import com.Emre.ServiceCommerce.dto.SignUpRequestDto;
import com.Emre.ServiceCommerce.dto.UserDto;
import com.Emre.ServiceCommerce.entities.User;
import com.Emre.ServiceCommerce.enums.UserRole;
import com.Emre.ServiceCommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl  implements  AuthService{
    @Autowired
    private UserRepository userRepository;

    public UserDto signUpClient(SignUpRequestDto signUpRequestDto){
        User user = new User();

        user.setEmail(signUpRequestDto.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signUpRequestDto.getPassword()));
        user.setName(signUpRequestDto.getName());
        user.setPhone(signUpRequestDto.getPhone());
        user.setLastname(signUpRequestDto.getLastname());
        user.setRole(UserRole.CLIENT);

        return userRepository.save(user).getUserDto();
    }

    public boolean presentByEmail(String email){
        return userRepository.findFirstByEmail(email) != null;
    }

    public UserDto signUpCompany(SignUpRequestDto signUpRequestDto){
        User user = new User();

        user.setEmail(signUpRequestDto.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signUpRequestDto.getPassword()));
        user.setName(signUpRequestDto.getName());
        user.setPhone(signUpRequestDto.getPhone());
        user.setRole(UserRole.COMPANY);

        return userRepository.save(user).getUserDto();
    }

}
