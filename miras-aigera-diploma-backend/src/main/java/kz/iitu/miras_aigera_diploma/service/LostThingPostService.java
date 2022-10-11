package kz.iitu.miras_aigera_diploma.service;

import java.util.List;
import kz.iitu.miras_aigera_diploma.model.dto.LostThingPostDto;
import kz.iitu.miras_aigera_diploma.model.entity.LostThingPost;

public interface LostThingPostService {

  LostThingPostDto saveLostThingPost(LostThingPostDto lostThingPostDto);

  LostThingPostDto getLostThingPost(Long id);

  void deleteLostThingPost(Long id);

  List<LostThingPost> getAllLostThingPosts();

  void approveLostThingsPost(Long id, boolean approved);
}
