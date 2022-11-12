package kz.iitu.miras_aigera_diploma.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import kz.iitu.miras_aigera_diploma.exceptions.NotFoundException;
import kz.iitu.miras_aigera_diploma.model.Constants.ApiMessages;
import kz.iitu.miras_aigera_diploma.model.dto.LostThingPostDto;
import kz.iitu.miras_aigera_diploma.model.entity.LostThingPost;
import kz.iitu.miras_aigera_diploma.repository.LostThingPostRepository;
import kz.iitu.miras_aigera_diploma.service.LostThingPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LostThingPostServiceImpl implements LostThingPostService {

  private final LostThingPostRepository lostThingPostRepository;

  @Override
  public LostThingPostDto saveLostThingPost(LostThingPostDto lostThingPostDto) {
    if (lostThingPostRepository.existsByDateTimeBetween(lostThingPostDto.getDateTime(),
        LocalDateTime.now())) {
      throw new NotFoundException("Post is already exists", HttpStatus.BAD_REQUEST);
    }
    LostThingPost lostThingPost = LostThingPost.builder()
        .city(lostThingPostDto.getCity())
        .district(lostThingPostDto.getDistrict())
        .dateTime(lostThingPostDto.getDateTime())
        .category(lostThingPostDto.getCategory())
        .description(lostThingPostDto.getDescription())
        .details(lostThingPostDto.getDetails())
        .approved(false)
        .build();

    lostThingPost = lostThingPostRepository.save(lostThingPost);
    log.info("Lost thing post with id {} saved", lostThingPost.getId());
    return lostThingPostDto;

  }

  @Override
  public LostThingPostDto getLostThingPost(Long id) {
    LostThingPost lostThingPost = lostThingPostRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(
            ApiMessages.ID_NOT_FOUND, HttpStatus.NOT_FOUND));
    LostThingPostDto lostThingPostDto = LostThingPostDto.builder()
        .city(lostThingPost.getCity())
        .district(lostThingPost.getDistrict())
        .dateTime(lostThingPost.getDateTime())
        .category(lostThingPost.getCategory())
        .description(lostThingPost.getDescription())
        .details(lostThingPost.getDetails())
        .build();
    log.info("Getting post with id {}", id);
    return lostThingPostDto;
  }

  @Override
  public void deleteLostThingPost(Long id) {
    LostThingPost lostThingPost = lostThingPostRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(
            ApiMessages.ID_NOT_FOUND, HttpStatus.NOT_FOUND));
    lostThingPostRepository.delete(lostThingPost);
    log.info("Lost Thing Post with id {} deleted", id);
  }

  @Override
  public List<LostThingPost> getAllLostThingPosts() {
    return lostThingPostRepository.findLostThingPostsByApprovedIsTrue();
  }

  @Override
  public void approveLostThingsPost(Long id, boolean approved) {
    LostThingPost lostThingPost = lostThingPostRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(
            ApiMessages.ID_NOT_FOUND, HttpStatus.NOT_FOUND));
    lostThingPost.setApproved(approved);
    lostThingPostRepository.save(lostThingPost);
    log.info("Lost thing Post approve status {}", approved);
  }
}
