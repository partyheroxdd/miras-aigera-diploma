package kz.iitu.miras_aigera_diploma.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import kz.iitu.miras_aigera_diploma.Security.AccessToken;
import kz.iitu.miras_aigera_diploma.model.dto.UserLoginDto;
import kz.iitu.miras_aigera_diploma.model.dto.UserRegisterDto;
import kz.iitu.miras_aigera_diploma.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<AccessToken> register(@RequestBody UserRegisterDto userRegisterDto) {
    AccessToken accessToken = authService.register(userRegisterDto);
    return ResponseEntity.ok(accessToken);

  }

  @PostMapping("/login")
  public ResponseEntity<AccessToken> login(@RequestBody UserLoginDto userLoginDto) {
    AccessToken accessToken = authService.login(userLoginDto);
    return ResponseEntity.ok(accessToken);
  }


}
