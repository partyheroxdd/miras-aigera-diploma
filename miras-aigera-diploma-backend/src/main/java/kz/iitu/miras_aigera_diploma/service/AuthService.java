package kz.iitu.miras_aigera_diploma.service;

import kz.iitu.miras_aigera_diploma.model.dto.user.UserLoginDto;
import kz.iitu.miras_aigera_diploma.model.dto.user.UserRegisterDto;
import kz.iitu.miras_aigera_diploma.security.AccessToken;

public interface AuthService {

  AccessToken register(UserRegisterDto userRegisterDto);

  AccessToken login(UserLoginDto userLoginDto);

}
