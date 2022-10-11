package kz.iitu.miras_aigera_diploma.model.entity;

import java.time.LocalDate;
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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "lost_peoples_posts")
public class LostPeoplePost {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "city")
  private String city;

  @Column(name = "district")
  private String district;

  @Column(name = "datetime")
  private LocalDateTime dateTime;

  @Column(name = "fullname")
  private String fullName;

  @Column(name = "birthday")
  private LocalDate birthday;

  @Column(name = "identity_data")
  private String identityData;

  @Column(name = "place_of_residence")
  private String placeOfResidence;

  @Column(name = "type_of_activity")
  private String typeOfActivity;

  @Column(name = "image_url")
  private String imageUrl;

  @Column(name = "approved")
  private boolean approved;

  @CreationTimestamp
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;
}
