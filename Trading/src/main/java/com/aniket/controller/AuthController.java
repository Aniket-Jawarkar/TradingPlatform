package com.aniket.controller;


import com.aniket.Service.CustomeUserDetailsService;
import com.aniket.config.JwtProvider;
import com.aniket.model.TwoFactorOTP;
import com.aniket.model.User;
import com.aniket.repository.UserRepository;
import com.aniket.response.AuthResponse;
import com.aniket.service.TwoFactorOtpService;
import com.aniket.utils.OtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    UserRepository userRepository ;
    private JwtProvider jwtProvider;
    private CustomeUserDetailsService customeUserDetailsService;
    private TwoFactorOtpService twoFactorOtpService;

    public AuthController(UserRepository userRepository, JwtProvider jwtProvider, CustomeUserDetailsService customeUserDetailsService, TwoFactorOtpService twoFactorOtpService) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.customeUserDetailsService = customeUserDetailsService;
        this.twoFactorOtpService = twoFactorOtpService;
    }

    @PostMapping("/signup")
    private ResponseEntity<AuthResponse> register (@RequestBody User user) throws Exception {



        User isEmailExists = userRepository.findByEmail(user.getEmail());

        if(isEmailExists != null){
            throw new Exception("Email is Already used with Another account");
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFullName(user.getFullName());
        newUser.setPassword(user.getPassword());
        User savedUser = userRepository.save(newUser);
        Authentication auth =
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(auth);


        String jwt = jwtProvider.generateToken(auth);

        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);

        res.setStatus(true);
        res.setMessage("regiter success");
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    private ResponseEntity<AuthResponse> login (@RequestBody User user) throws Exception {


        String userName = user.getEmail();
        String password = user.getPassword();




        Authentication auth = authenticate(userName,password);


        String jwt = jwtProvider.generateToken(auth);

        User authUser = userRepository.findByEmail(userName);

        if(user.getTwoFactorAuth().isEnabled()){
            AuthResponse res = new AuthResponse();
            res.setMessage("Two Factor auth is eneabled");

            res.setTwoFactorAuthEnabled(true);
            String otp = OtpUtils.generateOTP();

            TwoFactorOTP oldTwoFactorOTP =twoFactorOtpService.findByUser(authUser.getId());

            if(oldTwoFactorOTP != null){
                twoFactorOtpService.deleteTwoFactorOtp(oldTwoFactorOTP);

            }
             TwoFactorOTP newTwoFactorOTP = twoFactorOtpService.createTwoFactorOtp(
                     authUser,otp,jwt
             );


            res.setSession(newTwoFactorOTP.getId());
            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
        }
        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);

        res.setStatus(true);
        res.setMessage("Login success");
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    private Authentication authenticate(String userName, String password) {
        UserDetails userDetails=customeUserDetailsService.loadUserByUsername (userName);
        if (userDetails==null){
            throw new BadCredentialsException("invalid username");
        }
        if(!password.equals(userDetails.getPassword())) {
            throw new BadCredentialsException("invalid password");
        }
        return new UsernamePasswordAuthenticationToken (userDetails, password, userDetails.getAuthorities());
    }
}
