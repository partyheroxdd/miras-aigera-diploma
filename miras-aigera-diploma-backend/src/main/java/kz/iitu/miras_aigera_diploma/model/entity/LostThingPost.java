package kz.iitu.miras_aigera_diploma.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "lost_things_posts")
public class LostThingPost {

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

  @Column(name = "category")
  private String category;

  @Column(name = "description")
  private String description;

  @Column(name = "details")
  private String details;

  @Column(name = "approved")
  private boolean approved;

  @CreationTimestamp
  @Column(name = "created_at")
  @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
  private LocalDateTime updatedAt;
}
