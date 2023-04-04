package kz.iitu.miras_aigera_diploma.service.impl;

import java.util.HashSet;
import java.util.Set;
import kz.iitu.miras_aigera_diploma.converter.user.UserRegisterDtoConverter;
import kz.iitu.miras_aigera_diploma.exceptions.DiplomaCoreException;
import kz.iitu.miras_aigera_diploma.exceptions.security.CustomSecurityException;
import kz.iitu.miras_aigera_diploma.model.constants.ApiMessages;
import kz.iitu.miras_aigera_diploma.model.dto.user.UserLoginDto;
import kz.iitu.miras_aigera_diploma.model.dto.user.UserRegisterDto;
import kz.iitu.miras_aigera_diploma.model.entity.Role;
import kz.iitu.miras_aigera_diploma.model.entity.User;
import kz.iitu.miras_aigera_diploma.repository.UserRepository;
import kz.iitu.miras_aigera_diploma.security.AccessToken;
import kz.iitu.miras_aigera_diploma.security.ITokenProvider;
import kz.iitu.miras_aigera_diploma.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {

  ITokenProvider tokenProvider;

  AuthenticationManager authenticationManager;

  UserRepository userRepository;

  UserRegisterDtoConverter userRegisterDtoConverter;

  @Override
  public AccessToken register(UserRegisterDto userRegisterDto) {
    if (!userRegisterDto.getUsername().matches("^\\d{12}$")) {
      throw new CustomSecurityException(ApiMessages.INVALID_USERNAME, HttpStatus.BAD_REQUEST);
    }

    Set<Role> roles = new HashSet<>();
    checkUserExisting(userRegisterDto.getUsername(), userRegisterDto.getPhoneNumber());
    User user = userRegisterDtoConverter.convert(userRegisterDto);
    String username = user.getUsername();
    roles.add(user.getRole());
    userRepository.save(user);

    return tokenProvider.createToken(username, roles);
  }

  @Override
  @Transactional
  public AccessToken login(UserLoginDto userLoginDto) {
    String username = userLoginDto.getUsername();
    String password = userLoginDto.getPassword();
    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, password));
      Role role = userRepository.findByUsername(username).orElseThrow(
              () -> new DiplomaCoreException(HttpStatus.BAD_REQUEST, ApiMessages.USER_NOT_FOUND,
                  "User with this username not found"))
          .getRole();
      Set<Role> roles = new HashSet<>();
      roles.add(role);
      return tokenProvider.createToken(username, roles);
    } catch (AuthenticationException exception) {
      log.error(exception.getMessage());
      throw new DiplomaCoreException(HttpStatus.BAD_REQUEST, ApiMessages.BAD_CREDENTIALS,
          "Authorization error, please check the correctness of the data entered");
    }
  }

  private void checkUserExisting(String username, String phoneNumber) {
    if (userRepository.existsByUsername(username)) {
      throw new DiplomaCoreException(HttpStatus.BAD_REQUEST, ApiMessages.USER_ALREADY_EXISTS,
          "User already exists with username");
    }
    if (userRepository.existsByPhoneNumber(phoneNumber)) {
      throw new DiplomaCoreException(HttpStatus.BAD_REQUEST, ApiMessages.PHONE_NUMBER_EXISTS,
          "User already exists with phone number");
    }
  }
}
