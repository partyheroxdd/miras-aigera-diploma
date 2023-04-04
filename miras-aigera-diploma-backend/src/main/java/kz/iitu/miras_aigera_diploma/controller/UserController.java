package kz.iitu.miras_aigera_diploma.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import kz.iitu.miras_aigera_diploma.model.dto.user.UserChangePasswordDto;
import kz.iitu.miras_aigera_diploma.model.dto.user.UserInfoDto;
import kz.iitu.miras_aigera_diploma.model.dto.user.UserMeInfoDto;
import kz.iitu.miras_aigera_diploma.model.dto.user.UserUpdateDto;
import kz.iitu.miras_aigera_diploma.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
@Tag(name = "Users API", description = "Methods for work with users")
@SecurityRequirement(name = "Bearer Authentication")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {

  UserService userService;

  @GetMapping("/profile")
  @Operation(summary = "Method to get user profile")
  public ResponseEntity<UserInfoDto> getUserInfo() {
    return ResponseEntity.ok(userService.getUserProfile());
  }

  @GetMapping("/me")
  @Operation(summary = "Method to get my information")
  public ResponseEntity<UserMeInfoDto> getMe() {
    return ResponseEntity.ok(userService.getMe());
  }

  @PutMapping("/profile")
  @Operation(summary = "Method to update user profile")
  public ResponseEntity<String> updateUserProfile(@Valid @RequestBody UserUpdateDto userUpdateDto) {
    userService.updateUserProfile(userUpdateDto);
    return ResponseEntity.ok("User profile updated successfully");
  }

  @PutMapping("/change_password")
  @Operation(summary = "Method to change user password")
  public ResponseEntity<String> changePassword(
      @Valid @RequestBody UserChangePasswordDto userChangePasswordDto) {
    userService.changePassword(userChangePasswordDto);
    return ResponseEntity.ok("User password updated successfully");
  }
}
