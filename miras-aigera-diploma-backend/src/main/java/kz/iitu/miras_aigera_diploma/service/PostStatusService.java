package kz.iitu.miras_aigera_diploma.service;

import java.util.List;
import kz.iitu.miras_aigera_diploma.model.dto.post_status.PostStatusListInfoDto;

public interface PostStatusService {

  List<PostStatusListInfoDto> findAll();

}
