package kz.iitu.miras_aigera_diploma.service;

import kz.iitu.miras_aigera_diploma.model.dto.UserChangePasswordDto;
import kz.iitu.miras_aigera_diploma.model.dto.UserLoginDto;
import kz.iitu.miras_aigera_diploma.model.dto.UserMeInfoDto;
import kz.iitu.miras_aigera_diploma.model.dto.UserRegisterDto;
import kz.iitu.miras_aigera_diploma.security.AccessToken;

public interface AuthService {

  AccessToken register(UserRegisterDto userRegisterDto);

  AccessToken login(UserLoginDto userLoginDto);

  void changePassword(UserChangePasswordDto userChangePasswordDTO);

  UserMeInfoDto getMe();
}
