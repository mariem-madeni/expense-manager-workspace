package org.example.expensemanager.auth;

import lombok.RequiredArgsConstructor;
import org.example.expensemanager.dto.LoginRequest;
import org.example.expensemanager.dto.LoginResponse;
import org.example.expensemanager.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

  private final AuthenticationManager authManager;
  private final UserDetailsService userDetailsService;
  private final JwtService jwtService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
    authManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
    );
    UserDetails user = userDetailsService.loadUserByUsername(request.getEmail());
    String token = jwtService.generateToken(user);
    String role = user.getAuthorities().iterator().next().getAuthority();
    return ResponseEntity.ok(new LoginResponse(token, role, user.getUsername()));
  }
}
