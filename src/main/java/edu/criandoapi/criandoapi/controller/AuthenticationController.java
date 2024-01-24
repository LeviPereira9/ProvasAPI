/*
 * package edu.criandoapi.criandoapi.controller;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.ResponseEntity;
 * import org.springframework.security.authentication.AuthenticationManager;
 * import org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken;
 * import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 * import org.springframework.web.bind.annotation.PostMapping;
 * import org.springframework.web.bind.annotation.RequestMapping;
 * import org.springframework.web.bind.annotation.RestController;
 * 
 * import edu.criandoapi.criandoapi.domain.repository.UserRepository;
 * import edu.criandoapi.criandoapi.domain.user.AuthenticationDto;
 * import edu.criandoapi.criandoapi.domain.user.RegisterDto;
 * import edu.criandoapi.criandoapi.domain.user.User;
 * import io.swagger.v3.oas.annotations.parameters.RequestBody;
 * import jakarta.validation.Valid;
 * 
 * @RestController
 * 
 * @RequestMapping("/auth")
 * public class AuthenticationController {
 * 
 * @Autowired
 * private AuthenticationManager authenticationManager;
 * 
 * @Autowired
 * private UserRepository userRepository;
 * 
 * @PostMapping("/login")
 * public ResponseEntity login(@RequestBody @Valid AuthenticationDto data) {
 * var userNamePassword = new UsernamePasswordAuthenticationToken(data.login(),
 * data.password());
 * var auth = this.authenticationManager.authenticate(userNamePassword);
 * 
 * return ResponseEntity.ok().build();
 * }
 * 
 * @PostMapping("/memes")
 * public ResponseEntity<?> memes(@RequestBody RegisterDto model) {
 * 
 * if (this.userRepository.findByLogin(model.login()) != null) {
 * return ResponseEntity.badRequest().build();
 * }
 * 
 * String encryptedPassword = new
 * BCryptPasswordEncoder().encode(model.password());
 * User newUser = new User(model.login(), encryptedPassword, model.role());
 * 
 * this.userRepository.save(newUser);
 * 
 * return ResponseEntity.ok().build();
 * }
 * }
 */