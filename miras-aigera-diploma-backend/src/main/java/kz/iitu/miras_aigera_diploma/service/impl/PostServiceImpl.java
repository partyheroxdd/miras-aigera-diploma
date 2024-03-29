package kz.iitu.miras_aigera_diploma.service.impl;

import java.util.List;
import java.util.Objects;
import kz.iitu.miras_aigera_diploma.converter.post.PostCreateDtoConverter;
import kz.iitu.miras_aigera_diploma.converter.post.PostInfoDtoConverter;
import kz.iitu.miras_aigera_diploma.converter.post.PostListInfoDtoConverter;
import kz.iitu.miras_aigera_diploma.exceptions.DiplomaCoreException;
import kz.iitu.miras_aigera_diploma.model.constants.ApiMessages;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostChangeStatusDto;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostCreateDto;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostInfoDto;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostListInfoDto;
import kz.iitu.miras_aigera_diploma.model.dto.post.PostSearchDto;
import kz.iitu.miras_aigera_diploma.model.entity.Post;
import kz.iitu.miras_aigera_diploma.model.entity.User;
import kz.iitu.miras_aigera_diploma.repository.PostRepository;
import kz.iitu.miras_aigera_diploma.repository.PostStatusRepository;
import kz.iitu.miras_aigera_diploma.service.CDNMinioService;
import kz.iitu.miras_aigera_diploma.service.PostService;
import kz.iitu.miras_aigera_diploma.service.UserService;
import kz.iitu.miras_aigera_diploma.util.JwtUtil;
import kz.iitu.miras_aigera_diploma.util.must_have.specification.SpecificationBuilder;
import kz.iitu.miras_aigera_diploma.util.spec.PostSpec;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class PostServiceImpl implements PostService {

  PostRepository postRepository;
  PostStatusRepository postStatusRepository;

  UserService userService;
  CDNMinioService cdnMinioService;

  PostInfoDtoConverter postInfoDtoConverter;
  PostListInfoDtoConverter postListInfoDtoConverter;
  PostCreateDtoConverter postCreateDtoConverter;

  @Override
  @Transactional
  public void save(PostCreateDto postCreateDto, MultipartFile file) {
    try {
      Post post = postCreateDtoConverter.convert(postCreateDto);
      post.setImageUrl(cdnMinioService.uploadFile(file));
      postRepository.save(post);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }

  @Override
  @Transactional
  public void changeStatus(PostChangeStatusDto postChangeStatusDto) {
    Post post = getPost(postChangeStatusDto.getId());
    post.setStatus(postStatusRepository.findByCode(postChangeStatusDto.getStatusCode()));
    postRepository.save(post);
  }

  @Override
  @Transactional(readOnly = true)
  public PostInfoDto findById(Long id) {
    Post post = getPost(id);
    return postInfoDtoConverter.convert(post);
  }

  private Post getPost(Long id) {
    return postRepository.findById(id).orElseThrow(() -> new DiplomaCoreException(
        HttpStatus.BAD_REQUEST, ApiMessages.POST_NOT_FOUND, "Post with this id not found"));
  }

  @Override
  @Transactional(readOnly = true)
  public List<PostListInfoDto> findAll(PostSearchDto dto) {

    User user = userService.findByUsername(JwtUtil.getUsername());
    String role = JwtUtil.getRole();

    Specification<Post> postSpec = new SpecificationBuilder<>();

    if (Objects.isNull(dto.getDistrict()) && Objects.isNull(dto.getCity())) {
      switch (role) {
        case "ROLE_USER" -> postSpec.and(PostSpec.userFilter(user.getId()));
        case "ROLE_DISTRICT_POLICEMAN" -> postSpec.and(
            PostSpec.districtFilter(user.getDistrict().getName()));
        case "ROLE_PROSECUTOR" -> postSpec.and(PostSpec.cityFilter(user.getCity().getName()));
      }
    }
    if (Objects.nonNull(dto.getDateFrom()) && Objects.nonNull(dto.getDateTo())) {
      postSpec.and(PostSpec.dateFilter(dto.getDateFrom(), dto.getDateTo()));
    }
    if (Objects.nonNull(dto.getDistrict())) {
      postSpec.and(PostSpec.districtFilter(dto.getDistrict()));
    }
    if (Objects.nonNull(dto.getCity())) {
      postSpec.and(PostSpec.cityFilter(dto.getCity()));
    }
    if (Objects.nonNull(dto.getCategory())) {
      postSpec.and(PostSpec.categoryFilter(dto.getCategory()));
    }
    if (Objects.nonNull(dto.getStatus())) {
      postSpec.and(PostSpec.statusFilter(dto.getStatus()));
    }
    if (Objects.nonNull(dto.getQuery())) {
      postSpec.and(PostSpec.queryFilter(dto.getQuery()));
    }

    postSpec.and(PostSpec.postOrderByCreatedDate());

    return postListInfoDtoConverter.convert(postRepository.findAll(postSpec));
  }
}
