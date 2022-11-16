package kz.iitu.miras_aigera_diploma.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import kz.iitu.miras_aigera_diploma.model.dto.ForgotPasswordRequestDTO;
import kz.iitu.miras_aigera_diploma.model.dto.UserInfoDTO;
import kz.iitu.miras_aigera_diploma.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
@Tag(name = "Users", description = "Users API")
public class UserController {

  private final UserService userService;

  @GetMapping("/all")
  @Operation(summary = "Method to get all users")
  public ResponseEntity<List<UserInfoDTO>> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }

  @GetMapping("/{id}")
  @Operation(summary = "Method to get user by id")
  public ResponseEntity<UserInfoDTO> getUser(
      @Parameter(description = "User id") @PathVariable Long id) {
    return ResponseEntity.ok(userService.getUser(id));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Method to delete user by id")
  public ResponseEntity<?> deleteUser(
      @Parameter(description = "User id") @PathVariable Long id) {
    userService.deleteUser(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/forgot")
  public ResponseEntity<String> forgotPassword(
      @Valid @RequestBody ForgotPasswordRequestDTO forgotPasswordRequestDTO) {
    return ResponseEntity.ok(userService.forgotPassword(forgotPasswordRequestDTO));
  }

  @PostMapping("/validate")
  public ResponseEntity<String> validateResetCode(@Valid @RequestParam String resetCode) {
    return ResponseEntity.ok(userService.validateResetCode(resetCode));
  }

}
