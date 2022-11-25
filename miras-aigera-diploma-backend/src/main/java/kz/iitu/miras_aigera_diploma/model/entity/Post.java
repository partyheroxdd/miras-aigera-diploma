package kz.iitu.miras_aigera_diploma.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "posts")
@Accessors(chain = true)
@FieldNameConstants
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "city")
  private String city;

  @Column(name = "district")
  private String district;

  @Column(name = "datetime")
  @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
  private LocalDateTime dateTime;

  @ManyToOne
  private PostCategory postCategory;

  @Column(name = "description")
  private String description;

  @Column(name = "details")
  private String details;

  @Column(name = "approved")
  private Boolean approved;

  @CreationTimestamp
  @Column(name = "created_at")
  @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
  private LocalDateTime updatedAt;
}
