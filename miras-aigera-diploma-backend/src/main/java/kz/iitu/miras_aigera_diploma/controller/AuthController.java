package kz.iitu.miras_aigera_diploma.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import kz.iitu.miras_aigera_diploma.model.dto.user.UserLoginDto;
import kz.iitu.miras_aigera_diploma.model.dto.user.UserRegisterDto;
import kz.iitu.miras_aigera_diploma.security.AccessToken;
import kz.iitu.miras_aigera_diploma.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Tag(name = "Authorization API", description = "Methods for user authorization")
public class AuthController {

  AuthService authService;

  @PostMapping("/register")
  @Operation(summary = "Method to registration user")
  public ResponseEntity<AccessToken> register(
      @Valid @RequestBody UserRegisterDto userRegisterDto) {
    AccessToken accessToken = authService.register(userRegisterDto);
    return ResponseEntity.ok(accessToken);
  }

  @PostMapping("/login")
  @Operation(summary = "Method to login user")
  public ResponseEntity<AccessToken> login(
      @Valid @RequestBody UserLoginDto userLoginDto) {
    AccessToken accessToken = authService.login(userLoginDto);
    return ResponseEntity.ok(accessToken);
  }

}
