package kz.iitu.miras_aigera_diploma.service.impl;

import kz.iitu.miras_aigera_diploma.converter.user.UserInfoDtoConverter;
import kz.iitu.miras_aigera_diploma.converter.user.UserMeInfoDtoConverter;
import kz.iitu.miras_aigera_diploma.converter.user.UserUpdateDtoConverter;
import kz.iitu.miras_aigera_diploma.exceptions.DiplomaCoreException;
import kz.iitu.miras_aigera_diploma.exceptions.security.CustomSecurityException;
import kz.iitu.miras_aigera_diploma.model.constants.ApiMessages;
import kz.iitu.miras_aigera_diploma.model.dto.user.UserChangePasswordDto;
import kz.iitu.miras_aigera_diploma.model.dto.user.UserInfoDto;
import kz.iitu.miras_aigera_diploma.model.dto.user.UserMeInfoDto;
import kz.iitu.miras_aigera_diploma.model.dto.user.UserUpdateDto;
import kz.iitu.miras_aigera_diploma.model.entity.User;
import kz.iitu.miras_aigera_diploma.repository.UserRepository;
import kz.iitu.miras_aigera_diploma.service.UserService;
import kz.iitu.miras_aigera_diploma.util.JwtUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

  UserRepository userRepository;

  PasswordEncoder passwordEncoder;

  UserInfoDtoConverter userInfoDtoConverter;
  UserMeInfoDtoConverter userMeInfoDtoConverter;
  UserUpdateDtoConverter userUpdateDtoConverter;

  @Override
  @Transactional(readOnly = true)
  public UserInfoDto getUserProfile() {
    return userInfoDtoConverter.convert(findByUsername(JwtUtil.getUsername()));
  }

  @Override
  @Transactional(readOnly = true)
  public UserMeInfoDto getMe() {
    return userMeInfoDtoConverter.convert(findByUsername(JwtUtil.getUsername()));
  }

  @Override
  @Transactional
  public void updateUserProfile(UserUpdateDto userUpdateDto) {
    User user = findByUsername(JwtUtil.getUsername());
    userUpdateDtoConverter.fill(userUpdateDto, user);
    userRepository.save(user);
  }

  @Override
  @Transactional(readOnly = true)
  public User findByUsername(String username) {
    return userRepository.findByUsername(username).orElseThrow(
        () -> new DiplomaCoreException(HttpStatus.BAD_REQUEST, ApiMessages.USER_NOT_FOUND,
            "User with this username not found"));
  }

  @Override
  @Transactional
  public void changePassword(UserChangePasswordDto userChangePasswordDTO) {
    User user = findByUsername(userChangePasswordDTO.getUsername());
    if (!passwordEncoder.matches(userChangePasswordDTO.getPassword(), user.getPassword())) {
      throw new CustomSecurityException(ApiMessages.BAD_CREDENTIALS, HttpStatus.BAD_REQUEST);
    }
    user.setPassword(passwordEncoder.encode(userChangePasswordDTO.getNewPassword()));

    userRepository.save(user);
  }
}
