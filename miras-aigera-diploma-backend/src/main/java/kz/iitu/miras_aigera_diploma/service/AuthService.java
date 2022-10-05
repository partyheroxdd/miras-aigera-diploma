package kz.iitu.miras_aigera_diploma.service;

import kz.iitu.miras_aigera_diploma.Security.AccessToken;
import kz.iitu.miras_aigera_diploma.model.dto.UserLoginDto;
import kz.iitu.miras_aigera_diploma.model.dto.UserRegisterDto;

public interface AuthService {

  AccessToken register(UserRegisterDto userRegisterDto);

  AccessToken login(UserLoginDto userLoginDto);


}
