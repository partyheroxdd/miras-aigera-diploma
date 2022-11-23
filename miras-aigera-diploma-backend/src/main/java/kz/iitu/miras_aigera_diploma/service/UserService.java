package kz.iitu.miras_aigera_diploma.service;

import java.util.List;
import kz.iitu.miras_aigera_diploma.model.dto.ForgotPasswordRequestDto;
import kz.iitu.miras_aigera_diploma.model.dto.UserInfoDto;

public interface UserService {

  UserInfoDto getUser(Long id);

  void deleteUser(Long id);

  List<UserInfoDto> getAllUsers();

  String forgotPassword(ForgotPasswordRequestDto forgotPasswordRequestDTO);

  String validateResetCode(String resetCode);
}
