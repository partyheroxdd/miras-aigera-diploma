package kz.iitu.miras_aigera_diploma.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kz.iitu.miras_aigera_diploma.converter.PostConverter;
import kz.iitu.miras_aigera_diploma.exceptions.NotFoundException;
import kz.iitu.miras_aigera_diploma.model.Constants.ApiMessages;
import kz.iitu.miras_aigera_diploma.model.dto.PostDto;
import kz.iitu.miras_aigera_diploma.model.entity.Post;
import kz.iitu.miras_aigera_diploma.model.entity.PostCategory;
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
  private final PostCategoryRepository postCategoryRepository;
  private final PostConverter postConverter;
  private final List<String> POST_SEARCH_FIELDS = List.of(
      Post.Fields.details,
      Post.Fields.district,
      Post.Fields.city);

  @Override
  public PostDto savePost(PostDto postDto) {
    PostCategory postCategory = postCategoryRepository.findByName(postDto.getPostCategory());
    if (Objects.isNull(postCategory)) {
      throw new NotFoundException(ApiMessages.POST_CATEGORY_NOT_FOUND, HttpStatus.BAD_REQUEST);
    }
    Post post = Post.builder()
        .city(postDto.getCity())
        .district(postDto.getDistrict())
        .dateTime(postDto.getDateTime())
        .postCategory(postCategory)
        .description(postDto.getDescription())
        .details(postDto.getDetails())
        .approved(false)
        .build();

    post = postRepository.save(post);
    log.info("Lost thing post with id {} saved", post.getId());
    return postDto;

  }

  @Override
  public PostDto getPost(Long id) {
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(
            ApiMessages.ID_NOT_FOUND, HttpStatus.NOT_FOUND));
    PostDto postDto = PostDto.builder()
        .city(post.getCity())
        .district(post.getDistrict())
        .dateTime(post.getDateTime())
        .postCategory(post.getPostCategory().getName())
        .description(post.getDescription())
        .details(post.getDetails())
        .build();
    log.info("Getting post with id {}", id);
    return postDto;
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
  public List<PostDto> getAllApprovedPosts() {
    List<Post> posts = postRepository.findPostsByApprovedIsTrue();
    List<PostDto> postDtoList = new ArrayList<>();
    posts.forEach(post -> postDtoList.add(PostDto.builder()
        .id(post.getId())
        .city(post.getCity())
        .district(post.getDistrict())
        .dateTime(post.getDateTime())
        .postCategory(post.getPostCategory().getName())
        .description(post.getDescription())
        .details(post.getDetails())
        .build()));
    return postDtoList;
  }

  @Override
  public void approvePost(Long id, boolean approved) {
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(
            ApiMessages.ID_NOT_FOUND, HttpStatus.NOT_FOUND));
    post.setApproved(approved);
    postRepository.save(post);
    log.info("Post approve status {}", approved);
  }

  @Override
  public PageDTO<PostDto> getAllPosts(String city, String query, Pageable pageable) {
    Specification<Post> postSpecification = CommonSpec.search(query,
        POST_SEARCH_FIELDS.toArray(String[]::new));

    if (Objects.nonNull(city)) {
      postSpecification.and(PostSpec.cityFilter(city));
    }

    Page<Post> all = postRepository.findAll(postSpecification, pageable);
    return postConverter.convert(all);
  }
}
