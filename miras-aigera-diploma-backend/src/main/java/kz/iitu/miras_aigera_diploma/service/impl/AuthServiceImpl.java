package kz.iitu.miras_aigera_diploma.service.impl;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import kz.iitu.miras_aigera_diploma.exceptions.security.CustomSecurityException;
import kz.iitu.miras_aigera_diploma.model.Constants.ApiMessages;
import kz.iitu.miras_aigera_diploma.model.dto.UserChangePasswordDTO;
import kz.iitu.miras_aigera_diploma.model.dto.UserLoginDto;
import kz.iitu.miras_aigera_diploma.model.dto.UserRegisterDto;
import kz.iitu.miras_aigera_diploma.model.entity.Role;
import kz.iitu.miras_aigera_diploma.model.entity.User;
import kz.iitu.miras_aigera_diploma.repository.RoleRepository;
import kz.iitu.miras_aigera_diploma.repository.UserRepository;
import kz.iitu.miras_aigera_diploma.security.AccessToken;
import kz.iitu.miras_aigera_diploma.security.ITokenProvider;
import kz.iitu.miras_aigera_diploma.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final ITokenProvider tokenProvider;

  private final PasswordEncoder passwordEncoder;

  private final UserRepository userRepository;

  private final RoleRepository roleRepository;

  private final AuthenticationManager authenticationManager;

  @Override
  public AccessToken register(UserRegisterDto userRegisterDto) {
    if (!userRegisterDto.getUsername().matches("\\d+")) {
      throw new CustomSecurityException(ApiMessages.INVALID_USERNAME, HttpStatus.BAD_REQUEST);
    }
    String username = null;
    Set<Role> roles = null;
    try {
      checkUserExistsWithUserName(userRegisterDto.getUsername());
      User user = User.builder()
          .username(userRegisterDto.getUsername())
          .fullName(userRegisterDto.getFullName())
          .address(userRegisterDto.getAddress())
          .location(userRegisterDto.getLocation())
          .position(userRegisterDto.getPosition())
          .phone(userRegisterDto.getPhone())
          .password(passwordEncoder.encode(userRegisterDto.getPassword()))
          .roles(getRoles(userRegisterDto.getRoles(), userRegisterDto))
          .build();
      username = user.getUsername();
      roles = user.getRoles();
      userRepository.save(user);
      log.info("User {} successfully registered with role {}", userRegisterDto.getUsername(),
          userRegisterDto.getRoles());
    } catch (Exception e) {
      log.error(e.getMessage());
    }

    return tokenProvider.createToken(username, roles);
  }

  @Override
  public AccessToken login(UserLoginDto userLoginDto) {
    String username = userLoginDto.getUsername();
    String password = userLoginDto.getPassword();
    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, password));
      Set<Role> roles = userRepository.findByUsername(username).get().getRoles();
      log.info("User successfully login {}", userLoginDto.getUsername());
      return tokenProvider.createToken(username, roles);

    } catch (AuthenticationException exception) {
      log.error(exception.getMessage());
      throw new CustomSecurityException(ApiMessages.BAD_CREDENTIALS, HttpStatus.BAD_REQUEST);

    }

  }

  private void checkUserExistsWithUserName(String username) {
    if (userRepository.existsByUsername(username)) {
      throw new CustomSecurityException(ApiMessages.USER_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
    }
  }

  private Set<Role> getRoles(String[] roles, UserRegisterDto userRegisterDto) {
    Set<Role> userRoles = new HashSet<>();
    for (String role : roles) {
      if (Objects.nonNull(userRegisterDto.getPosition()) && Objects.nonNull(
          userRegisterDto.getLocation())) {
        role = "ROLE_POLICEMAN";
      }
      userRoles.add(roleRepository.findByName(role));
    }
    return userRoles;
  }

  @Override
  public void changePassword(UserChangePasswordDTO userChangePasswordDTO) {
    User user = userRepository.findByUsername(userChangePasswordDTO.getUsername()).orElseThrow(
        () -> new CustomSecurityException(ApiMessages.BAD_CREDENTIALS, HttpStatus.BAD_REQUEST));
    log.info("Find user {} to change password", user.getUsername());
    if (!passwordEncoder.matches(userChangePasswordDTO.getPassword(), user.getPassword())) {
      throw new CustomSecurityException(ApiMessages.BAD_CREDENTIALS, HttpStatus.BAD_REQUEST);
    }

    user.setPassword(passwordEncoder.encode(userChangePasswordDTO.getReTypedPassword()));

    userRepository.save(user);
  }
}
