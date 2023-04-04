package kz.iitu.miras_aigera_diploma.service;

import kz.iitu.miras_aigera_diploma.model.dto.user.UserChangePasswordDto;
import kz.iitu.miras_aigera_diploma.model.dto.user.UserInfoDto;
import kz.iitu.miras_aigera_diploma.model.dto.user.UserMeInfoDto;
import kz.iitu.miras_aigera_diploma.model.dto.user.UserUpdateDto;
import kz.iitu.miras_aigera_diploma.model.entity.User;

public interface UserService {

  UserInfoDto getUserProfile();

  UserMeInfoDto getMe();

  void updateUserProfile(UserUpdateDto userUpdateDto);

  User findByUsername(String username);

  void changePassword(UserChangePasswordDto userChangePasswordDTO);
}
