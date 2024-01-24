package edu.criandoapi.criandoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.criandoapi.criandoapi.domain.repository.UserRepository;
import edu.criandoapi.criandoapi.domain.user.LoginDto;
import edu.criandoapi.criandoapi.domain.user.LoginResponseDto;
import edu.criandoapi.criandoapi.domain.user.RegisterDto;
import edu.criandoapi.criandoapi.domain.user.User;
import edu.criandoapi.criandoapi.infra.security.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto model) {

        var userNamePassword = new UsernamePasswordAuthenticationToken(model.login(), model.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto model) {

        if (this.userRepository.findByLogin(model.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(model.password());
        User newUser = new User(model.login(), encryptedPassword, model.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

}
