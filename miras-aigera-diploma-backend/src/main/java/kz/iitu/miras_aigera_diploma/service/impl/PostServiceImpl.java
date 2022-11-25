package kz.iitu.miras_aigera_diploma.service.impl;

import java.util.List;
import java.util.Objects;
import kz.iitu.miras_aigera_diploma.converter.PostCreateDtoConverter;
import kz.iitu.miras_aigera_diploma.converter.PostInfoDtoConverter;
import kz.iitu.miras_aigera_diploma.exceptions.NotFoundException;
import kz.iitu.miras_aigera_diploma.model.Constants.ApiMessages;
import kz.iitu.miras_aigera_diploma.model.dto.PostCreateDto;
import kz.iitu.miras_aigera_diploma.model.dto.PostInfoDto;
import kz.iitu.miras_aigera_diploma.model.dto.PostUpdateDto;
import kz.iitu.miras_aigera_diploma.model.entity.Post;
import kz.iitu.miras_aigera_diploma.repository.PostCategoryRepository;
import kz.iitu.miras_aigera_diploma.repository.PostRepository;
import kz.iitu.miras_aigera_diploma.service.PostService;
import kz.iitu.miras_aigera_diploma.util.PostSpec;
import kz.iitu.miras_aigera_diploma.util.must_have.dto_util.PageDTO;
import kz.iitu.miras_aigera_diploma.util.must_have.query.CommonSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;
  private final PostInfoDtoConverter postInfoDtoConverter;
  private final PostCreateDtoConverter postCreateDtoConverter;
  private final PostCategoryRepository postCategoryRepository;

  private final List<String> POST_SEARCH_FIELDS = List.of(
      Post.Fields.details,
      Post.Fields.district,
      Post.Fields.city,
      Post.Fields.dateTime,
      Post.Fields.description
  );

  @Override
  public PostCreateDto savePost(PostCreateDto postCreateDto) {
    Post post = postCreateDtoConverter.convert(postCreateDto);
    postRepository.save(post);
    log.info("Post {} saved", post);
    return postCreateDto;
  }

  @Override
  public PostUpdateDto updatePost(PostUpdateDto postUpdateDto) {
    Post post = postRepository.findById(postUpdateDto.getId())
        .orElseThrow(() -> new NotFoundException(
            ApiMessages.ID_NOT_FOUND, HttpStatus.NOT_FOUND));
    post.setCity(postUpdateDto.getCity())
        .setDistrict(postUpdateDto.getDistrict())
        .setDateTime(postUpdateDto.getDateTime())
        .setPostCategory(postCategoryRepository.findByName(
            postUpdateDto.getPostCategory()))
        .setDescription(postUpdateDto.getDescription())
        .setDetails(postUpdateDto.getDetails());
    postRepository.save(post);
    log.info("Post updated to {}", post);
    return postUpdateDto;
  }

  @Override
  public PostInfoDto getPost(Long id) {
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(
            ApiMessages.ID_NOT_FOUND, HttpStatus.NOT_FOUND));
    log.info("Getting post {}", post);
    return postInfoDtoConverter.convert(post);
  }

  @Override
  public void deletePost(Long id) {
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(
            ApiMessages.ID_NOT_FOUND, HttpStatus.NOT_FOUND));
    postRepository.delete(post);
    log.info("Lost Thing Post with id {} deleted", id);
  }

  @Override
  public void approvePost(Long id, Boolean approved) {
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(
            ApiMessages.ID_NOT_FOUND, HttpStatus.NOT_FOUND));
    post.setApproved(approved);
    postRepository.save(post);
    log.info("Post approve status {}", post);
  }

  @Override
  public PageDTO<PostInfoDto> getAllPosts(String city, Boolean approved, String query,
      Pageable pageable) {
    Specification<Post> postSpecification = CommonSpec.search(query,
        POST_SEARCH_FIELDS.toArray(String[]::new));

    if (Objects.nonNull(city)) {
      postSpecification.and(PostSpec.cityFilter(city));
    }

    if (Objects.nonNull(approved)) {
      postSpecification.and(PostSpec.approvedFilter(approved));
    }

    Page<Post> all = postRepository.findAll(postSpecification, pageable);
    return postInfoDtoConverter.convert(all);
  }
}
