package kz.iitu.miras_aigera_diploma.model.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import kz.iitu.miras_aigera_diploma.model.enums.StatusCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "post_statuses")
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
public class PostStatus extends BaseEntity {

  String name;

  @Enumerated(EnumType.STRING)
  StatusCode code;
}
