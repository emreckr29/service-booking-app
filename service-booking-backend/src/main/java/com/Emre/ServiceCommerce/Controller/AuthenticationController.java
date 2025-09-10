package com.Emre.ServiceCommerce.Controller;

import com.Emre.ServiceCommerce.dto.AuthenticationRequest;
import com.Emre.ServiceCommerce.dto.SignUpRequestDto;
import com.Emre.ServiceCommerce.dto.UserDto;
import com.Emre.ServiceCommerce.entities.User;
import com.Emre.ServiceCommerce.repositories.UserRepository;
import com.Emre.ServiceCommerce.services.authentication.AuthService;
import com.Emre.ServiceCommerce.services.jwt.UserDetailsServiceImpl;
import com.Emre.ServiceCommerce.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization";
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/client/sign-up")
    public ResponseEntity<?> signUpClient(@RequestBody SignUpRequestDto signUpRequestDto){
        if (authService.presentByEmail(signUpRequestDto.getEmail())){
            return new ResponseEntity<>("Client already exists with this email", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto newUser = authService.signUpClient(signUpRequestDto);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/company/sign-up")
    public ResponseEntity<?> signUpCompany(@RequestBody SignUpRequestDto signUpRequestDto){
        if (authService.presentByEmail(signUpRequestDto.getEmail())){
            return new ResponseEntity<>("Company already exists with this email", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto newUser = authService.signUpCompany(signUpRequestDto);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException, JSONException {
        try {
            System.out.println("Geldi");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        }
        catch (BadCredentialsException e) {
            System.out.println("invalid username");
            throw new BadCredentialsException("Invalid username or password", e);
        }

        System.out.println("devamke");

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        System.out.println("1");

        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        System.out.println("2");

        User user = userRepository.findFirstByEmail(authenticationRequest.getUsername());

        System.out.println("3");

        response.getWriter().write(new JSONObject()
                .put("userId", user.getId())
                .put("role", user.getRole())
                .toString()
        );

        System.out.println("4");

        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader("Access-Control-Allow-Headers", "Authorization" + " X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");

        response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwt);

        System.out.println(jwt);

        System.out.println(user.getEmail());
    }
}
