package kz.iitu.miras_aigera_diploma.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import kz.iitu.miras_aigera_diploma.model.dto.user.UserChangePasswordDto;
import kz.iitu.miras_aigera_diploma.model.dto.user.UserLoginDto;
import kz.iitu.miras_aigera_diploma.model.dto.user.UserMeInfoDto;
import kz.iitu.miras_aigera_diploma.model.dto.user.UserRegisterDto;
import kz.iitu.miras_aigera_diploma.security.AccessToken;
import kz.iitu.miras_aigera_diploma.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authorization API", description = "Methods for authorization")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  @Operation(summary = "Method to registration user")
  public ResponseEntity<AccessToken> register(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Request Body of User Register DTO")
      @Valid @RequestBody UserRegisterDto userRegisterDto) {
    AccessToken accessToken = authService.register(userRegisterDto);
    return ResponseEntity.ok(accessToken);
  }

  @PostMapping("/login")
  @Operation(summary = "Method to login user")
  public ResponseEntity<AccessToken> login(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Request Body of User Login DTO")
      @Valid @RequestBody UserLoginDto userLoginDto) {
    AccessToken accessToken = authService.login(userLoginDto);
    return ResponseEntity.ok(accessToken);
  }

  @PostMapping("/password")
  @Operation(summary = "Method to change password")
  public ResponseEntity<?> changePassword(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Request body of User Change Password DTO")
      @Valid @RequestBody UserChangePasswordDto userChangePasswordDTO) {
    authService.changePassword(userChangePasswordDTO);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/me")
  @Operation(summary = "Method to get my credentials")
  public ResponseEntity<UserMeInfoDto> getMe() {
    return ResponseEntity.ok(authService.getMe());
  }

}
