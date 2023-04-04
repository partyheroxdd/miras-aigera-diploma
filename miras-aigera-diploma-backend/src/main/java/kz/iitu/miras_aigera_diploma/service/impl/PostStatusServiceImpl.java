package kz.iitu.miras_aigera_diploma.service.impl;

import java.util.List;
import kz.iitu.miras_aigera_diploma.converter.post_status.PostStatusListInfoDtoConverter;
import kz.iitu.miras_aigera_diploma.model.dto.post_status.PostStatusListInfoDto;
import kz.iitu.miras_aigera_diploma.repository.PostStatusRepository;
import kz.iitu.miras_aigera_diploma.service.PostStatusService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PostStatusServiceImpl implements PostStatusService {

  PostStatusRepository postStatusRepository;

  PostStatusListInfoDtoConverter postStatusListInfoDtoConverter;

  @Override
  @Transactional(readOnly = true)
  public List<PostStatusListInfoDto> findAll() {
    return postStatusListInfoDtoConverter.convert(postStatusRepository.findAll());
  }
}
