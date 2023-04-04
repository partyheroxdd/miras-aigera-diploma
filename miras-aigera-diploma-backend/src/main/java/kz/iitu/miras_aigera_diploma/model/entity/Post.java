package kz.iitu.miras_aigera_diploma.model.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "posts")
@FieldNameConstants
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post extends BaseEntity {

  String number;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "city_id")
  City city;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "district_id")
  District district;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "status_id")
  PostStatus status;

  LocalDateTime dateTime;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  PostCategory category;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  User user;

  String description;

  String additionalInfo;

  @CreationTimestamp
  Timestamp createdAt;

  @UpdateTimestamp
  Timestamp updatedAt;
}
