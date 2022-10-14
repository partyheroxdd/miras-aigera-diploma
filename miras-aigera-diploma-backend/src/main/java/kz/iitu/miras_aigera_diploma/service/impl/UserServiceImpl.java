package kz.iitu.miras_aigera_diploma.service.impl;

import java.util.ArrayList;
import java.util.List;
import kz.iitu.miras_aigera_diploma.model.dto.UserInfoDTO;
import kz.iitu.miras_aigera_diploma.model.entity.User;
import kz.iitu.miras_aigera_diploma.repository.UserRepository;
import kz.iitu.miras_aigera_diploma.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public UserInfoDTO getUser(Long id) {
    User user = userRepository.findUserById(id);
    log.info("Get user with id {}", id);
    return UserInfoDTO.builder()
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
  public List<UserInfoDTO> getAllUsers() {
    List<User> users = userRepository.findAll();
    List<UserInfoDTO> userInfoDTOList = new ArrayList<>();
    users.forEach(user -> userInfoDTOList.add(UserInfoDTO.builder()
        .id(user.getId())
        .username(user.getUsername())
        .fullName(user.getFullName())
        .address(user.getAddress())
        .phone(user.getPhone())
        .build()));
    return userInfoDTOList;
  }
}
