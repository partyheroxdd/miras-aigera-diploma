package kz.iitu.miras_aigera_diploma.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import kz.iitu.miras_aigera_diploma.model.dto.ForgotPasswordRequestDto;
import kz.iitu.miras_aigera_diploma.model.dto.UserInfoDto;
import kz.iitu.miras_aigera_diploma.model.entity.ForgotPasswordUser;
import kz.iitu.miras_aigera_diploma.model.entity.User;
import kz.iitu.miras_aigera_diploma.repository.ForgotPasswordUserRepository;
import kz.iitu.miras_aigera_diploma.repository.UserRepository;
import kz.iitu.miras_aigera_diploma.service.UserService;
import kz.iitu.miras_aigera_diploma.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private static final long EXPIRE_RESET_CODE_TIME_MINUTE = 5;
  private final UserRepository userRepository;
  private final ForgotPasswordUserRepository forgotPasswordUserRepository;

  @Override
  public UserInfoDto getUser(Long id) {
    User user = userRepository.findUserById(id);
    log.info("Get user {}", user);
    return UserInfoDto.builder()
        .id(user.getId())
        .username(user.getUsername())
        .fullName(user.getFullName())
        .address(user.getAddress())
        .phone(user.getPhone())
        .build();
  }

  @Override
  public void deleteUser(Long id) {
    userRepository.deleteById(id);
    log.info("Delete user with id {}", id);
  }

  @Override
  public List<UserInfoDto> getAllUsers() {
    List<User> users = userRepository.findAll();
    List<UserInfoDto> userInfoDtoList = new ArrayList<>();
    users.forEach(user -> userInfoDtoList.add(UserInfoDto.builder()
        .id(user.getId())
        .username(user.getUsername())
        .fullName(user.getFullName())
        .address(user.getAddress())
        .phone(user.getPhone())
        .build()));
    return userInfoDtoList;
  }

  @Override
  public String forgotPassword(
      ForgotPasswordRequestDto forgotPasswordRequestDTO) {
    ForgotPasswordUser forgotPasswordUser = ForgotPasswordUser.builder()
        .email(forgotPasswordRequestDTO.getEmail())
        .resetCode(generateResetCode())
        .creationTime(LocalDateTime.now())
        .build();
    log.info("Forgot password user email {} and resetCode {}", forgotPasswordUser.getEmail(),
        forgotPasswordUser.getResetCode());
    forgotPasswordUserRepository.save(forgotPasswordUser);
    return forgotPasswordUser.getResetCode();
  }

  @Override
  public String validateResetCode(String resetCode) {
    ForgotPasswordUser forgotPasswordUser = forgotPasswordUserRepository.findByResetCode(resetCode);
    if (isResetCodeExpired(forgotPasswordUser.getCreationTime())) {
      forgotPasswordUserRepository.delete(forgotPasswordUser);
      throw new RuntimeException();
    }
    return "Successfully validated!";
  }

  private String generateResetCode() {
    Random random = new Random();
    return String.format("%04d", random.nextInt(10000));
  }

  private boolean isResetCodeExpired(LocalDateTime creationTime) {
    Duration difference = Duration.between(creationTime, LocalDateTime.now());
    return difference.toMinutes() >= EXPIRE_RESET_CODE_TIME_MINUTE;
  }
}
