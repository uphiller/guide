package com.battercodelab.guide.controller;

import com.battercodelab.guide.controller.dto.LoginDto;
import com.battercodelab.guide.controller.dto.SignupDto;
import com.battercodelab.guide.controller.mapper.MemberMapper;
import com.battercodelab.guide.entity.Member;
import com.battercodelab.guide.security.UserDetailsImpl;
import com.battercodelab.guide.service.UserService;
import com.battercodelab.guide.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class UserController {

    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final MemberMapper memberMapper;

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signup(@RequestBody SignupDto.Request signupDto) throws Exception {
        Member member = userService.registerUser(signupDto);
        authenticate(signupDto.getId(), signupDto.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(signupDto.getId());
        final String token = jwtTokenUtil.generateToken(userDetails);
        SignupDto.Response response = memberMapper.toSignupResponseDto(member, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginDto.Request userDto) throws Exception {
        authenticate(userDto.getId(), userDto.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getId());
        final String token = jwtTokenUtil.generateToken(userDetails);
        LoginDto.Response response = memberMapper.toLoginResponseDto(((UserDetailsImpl) userDetails).getUser(), token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void authenticate(String id, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(id, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
