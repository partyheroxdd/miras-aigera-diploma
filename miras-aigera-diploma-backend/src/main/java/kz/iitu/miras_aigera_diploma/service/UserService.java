package kz.iitu.miras_aigera_diploma.service;

import java.util.List;
import kz.iitu.miras_aigera_diploma.model.dto.ForgotPasswordRequestDTO;
import kz.iitu.miras_aigera_diploma.model.dto.UserInfoDTO;

public interface UserService {

  UserInfoDTO getUser(Long id);

  void deleteUser(Long id);

  List<UserInfoDTO> getAllUsers();

  String forgotPassword(ForgotPasswordRequestDTO forgotPasswordRequestDTO);

  String validateResetCode(String resetCode);
}
